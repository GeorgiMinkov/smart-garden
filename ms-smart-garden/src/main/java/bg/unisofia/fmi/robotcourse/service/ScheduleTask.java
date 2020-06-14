package bg.unisofia.fmi.robotcourse.service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import bg.unisofia.fmi.robotcourse.dto.Measure;
import bg.unisofia.fmi.robotcourse.repository.JooqConfiguration;
import bg.unisofia.fmi.robotcourse.repository.JooqHistory;
import bg.unisofia.fmi.robotcourse.tables.pojos.History;

@Component
public class ScheduleTask {

	private final RestTemplate restTemplate;
	
	private final JooqConfiguration configurationRepository;
	
	private final JooqHistory historyRepository;
	
	@Autowired
	public ScheduleTask(RestTemplate restTemplate, JooqConfiguration configurationRepository, JooqHistory historyRepository) {
		this.restTemplate = restTemplate;
		this.configurationRepository = configurationRepository;
		this.historyRepository = historyRepository;
	}
	
	@Scheduled(fixedRate = 360000)
	public void triggerWaterService() {
	  // call pyhton endpoint
	    long now = System.currentTimeMillis() / 1000;
	    System.out.println(
	      "schedule tasks using cron jobs - " + now);
	    
	    HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		String moistureLevel = configurationRepository.retrieveParamByName("mositure-level") == null ? "50" :  configurationRepository.retrieveParamByName("mositure-level");
		HttpEntity<Object> entity = new HttpEntity<>(null, headers);

		String url = "http://localhost:5000/waterCheck/" + moistureLevel;

		System.out.println(url);
		String response = restTemplate
				.exchange(url, HttpMethod.GET, entity, String.class).getBody();
		
		JSONObject json = new JSONObject(response);
		Measure payload = new Measure();
		payload.setMoisture(json.getString("moisture"));
		payload.setTemperature(json.getString("temperture"));
		payload.setHumidity(json.getString("humidity"));
		payload.setWaterLevel("44");
		
		History history = new History();
		history.setPayload(payload.toString());
		history.setRequestTime(new Timestamp(System.currentTimeMillis()).toString());

		historyRepository.create(Optional.ofNullable(history));
	}
}
