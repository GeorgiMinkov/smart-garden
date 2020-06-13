package bg.unisofia.fmi.robotcourse.repository;

import java.util.Optional;

import bg.unisofia.fmi.robotcourse.tables.pojos.Configuration;

public interface JooqConfiguration {
	public void create(Optional<Configuration> src);
	
	public void update(Optional<Configuration> src);
	
	public String retrieveParamByName(String paramName);

}
