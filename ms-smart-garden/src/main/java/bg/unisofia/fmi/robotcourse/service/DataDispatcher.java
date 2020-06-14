package bg.unisofia.fmi.robotcourse.service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import bg.unisofia.fmi.robotcourse.dto.Measure;
import bg.unisofia.fmi.robotcourse.repository.JooqConfiguration;
import bg.unisofia.fmi.robotcourse.repository.JooqHistory;
import bg.unisofia.fmi.robotcourse.tables.pojos.Configuration;
import bg.unisofia.fmi.robotcourse.tables.pojos.History;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController("/garden")
public class DataDispatcher {

	private final RestTemplate restTemplate;

	private final JooqHistory historyRepository;

	private final JooqConfiguration configurationRepository;

	@Autowired
	public DataDispatcher(RestTemplate restTemplate, JooqHistory historyRepository,
			JooqConfiguration configurationRepository) {
		super();
		this.restTemplate = restTemplate;
		this.historyRepository = historyRepository;
		this.configurationRepository = configurationRepository;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/getData")
	public ResponseEntity<Measure> getData() {

		log.info(">>>>>>>>>>>>payload");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<Object> entity = new HttpEntity<>(null, headers);

		String url = "http://localhost:5000/data";

		System.out.println(url);
		String response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();

		JSONObject json = new JSONObject(response);
		Measure payload = new Measure();
		payload.setMoisture(json.getString("moisture"));
		payload.setTemperature(json.getString("temperture"));
		payload.setHumidity(json.getString("humidity"));
		payload.setWaterLevel("44");
		payload.setMoisterLevelForWater(configurationRepository.retrieveParamByName("moisture-level"));
		payload.setWateringStatus(json.getString("text"));
		
		History history = new History();
		history.setPayload(payload.toString());
		history.setRequestTime(new Timestamp(System.currentTimeMillis()).toString());
		history.setHumidity(payload.getHumidity());
		history.setMoisture(payload.getMoisture());
		history.setTemperature(payload.getTemperature());
		history.setWateringStatus(payload.getWateringStatus());
		
		historyRepository.create(Optional.ofNullable(history));

		return new ResponseEntity<Measure>(payload, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/configuration/{moisture}")
	public ResponseEntity<String> configuration(@PathVariable String moisture) {

		log.info(">>>>>>>>>>>>payload1111 " + moisture);
		Configuration configuration = new Configuration();
		configuration.setParamName("mositure-level");
		configuration.setParamValue(moisture);

		String value = configurationRepository.retrieveParamByName(configuration.getParamName());
		if (value == null) {
			configurationRepository.create(Optional.ofNullable(configuration));
		} else {
			configurationRepository.update(Optional.ofNullable(configuration));
		}

		return new ResponseEntity<String>("UPDATED", HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/history")
	public ResponseEntity<List<History>> getHistory() {
		List<History> history = historyRepository.getHistoryByRequestTime();
		return new ResponseEntity<List<History>>(history, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/createHistory")
	public ResponseEntity<String> createHistory() {
		History test = new History();
		test.setPayload("{payload}");
		test.setRequestTime(new Timestamp(System.currentTimeMillis()).toString());
		
		historyRepository.create(Optional.ofNullable(test));
	
		
		return new ResponseEntity<String>(test.toString(), HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/triggerWaterService")
	public ResponseEntity<Measure> triggerWaterService() {
		// call pyhton endpoint

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		String moistureLevel = configurationRepository.retrieveParamByName("mositure-level") == null ? "50"
				: configurationRepository.retrieveParamByName("mositure-level");
		HttpEntity<Object> entity = new HttpEntity<>(null, headers);

		String url = "http://localhost:5000/waterCheck/" + moistureLevel;

		System.out.println(url);
		String response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();

		JSONObject json = new JSONObject(response);
		Measure payload = new Measure();
		payload.setMoisture(json.getString("moisture"));
		payload.setTemperature(json.getString("temperture"));
		payload.setHumidity(json.getString("humidity"));
		payload.setWaterLevel("44");
		payload.setMoisterLevelForWater(configurationRepository.retrieveParamByName("moisture-level"));
		payload.setWateringStatus(json.getString("text"));
		
		History history = new History();
		history.setPayload(payload.toString());
		history.setRequestTime(new Timestamp(System.currentTimeMillis()).toString());
		history.setHumidity(payload.getHumidity());
		history.setMoisture(payload.getMoisture());
		history.setTemperature(payload.getTemperature());
		history.setWateringStatus(payload.getWateringStatus());

		historyRepository.create(Optional.ofNullable(history));
		
		return new ResponseEntity<Measure>(payload, HttpStatus.OK);
	}
}
