package bg.unisofia.fmi.robotcourse.dto;

import lombok.Data;

@Data
public class Measure {
	
	private String temperature; // TODO
	private String moisture;
	private String waterLevel;
	private String humidity;
	private String moisterLevelForWater;
}
