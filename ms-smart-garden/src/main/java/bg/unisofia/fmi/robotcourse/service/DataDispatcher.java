package bg.unisofia.fmi.robotcourse.service;

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
		
		log.info(">>>>>>>>>>>>TEST");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<Object> entity = new HttpEntity<>(null, headers);

		String url = "http://localhost:5000/data";

		System.out.println(url);
		String response = restTemplate
				.exchange(url, HttpMethod.GET, entity, String.class).getBody();
		
		JSONObject json = new JSONObject(response);
		Measure test = new Measure();
		test.setMoisture(json.getString("moisture"));
		test.setTemperature("34");
		test.setWaterLevel("44");
		
		return new ResponseEntity<Measure>(test, HttpStatus.OK);
	}

	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/configuration/{moisture}")
	public ResponseEntity<Measure> configuration(@PathVariable String moisture) {
		
		log.info(">>>>>>>>>>>>TEST1111 " + moisture);
		Configuration configuration = new Configuration();
		configuration.setParamName("mositure-level");
		configuration.setParamValue(moisture);
		
		configurationRepository.update(Optional.ofNullable(configuration));
		
		Measure test = new Measure();
		test.setMoisture("40");
		test.setTemperature("34");
		test.setWaterLevel("44");
		
		return new ResponseEntity<Measure>(test, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/history")
	public ResponseEntity<List<History>> getHistory() {
		List<History> history = historyRepository.getHistoryByRequestTime();
		return new ResponseEntity<List<History>>(history, HttpStatus.OK);
	}
	
}
