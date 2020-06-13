package bg.unisofia.fmi.robotcourse.service;

import java.util.Collections;

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

@Component
public class ScheduleTask {

	private final RestTemplate restTemplate;
	
	private final JooqConfiguration configurationRepository;
	
	@Autowired
	public ScheduleTask(RestTemplate restTemplate, JooqConfiguration configurationRepository) {
		this.restTemplate = restTemplate;
		this.configurationRepository = configurationRepository;
	}
	
	@Scheduled(cron = "0 * * * * *")
	public void triggerWaterService() {
	  // call pyhton endpoint
	    long now = System.currentTimeMillis() / 1000;
	    System.out.println(
	      "schedule tasks using cron jobs - " + now);
	    
	    HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		String moistureLevel = configurationRepository.retrieveParamByName("mositure-level");
		HttpEntity<String> entity = new HttpEntity<>(moistureLevel, headers);

		String url = "http://localhost:5000/waterCheck";

		System.out.println(url);
		String response = restTemplate
				.exchange(url, HttpMethod.GET, entity, String.class).getBody();
		
		JSONObject json = new JSONObject(response);
		Measure test = new Measure();
		test.setMoisture(json.getString("moisture"));
		test.setTemperature("34");
		test.setWaterLevel("44");
	}
}
