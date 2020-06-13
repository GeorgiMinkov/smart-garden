package bg.unisofia.fmi.robotcourse.repository.impl;

import java.util.Optional;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bg.unisofia.fmi.robotcourse.Tables;
import bg.unisofia.fmi.robotcourse.repository.JooqConfiguration;
import bg.unisofia.fmi.robotcourse.tables.pojos.Configuration;
import bg.unisofia.fmi.robotcourse.tables.records.ConfigurationRecord;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class JooqConfigurationImpl implements JooqConfiguration {

	private final DSLContext create;
	
	@Autowired
	public JooqConfigurationImpl(DSLContext create) {
		this.create = create;
	}
	
	@Override
	public void create(Optional<Configuration> src) {
		if (!src.isPresent()) {
			log.info("No configuration presented");
			return;
		}
		
		ConfigurationRecord record = create.newRecord(Tables.CONFIGURATION, src.get());
		record.store();
		log.info("New configuration saved");
	}

	@Override
	public void update(Optional<Configuration> src) {
		create.update(Tables.CONFIGURATION)
			.set(Tables.CONFIGURATION.PARAM_VALUE, src.get().getParamValue())
			.where(Tables.CONFIGURATION.PARAM_NAME.eq(src.get().getParamName()))
			.execute();
		log.info("Update configuration table with {}", src.get());
	}

	@Override
	public String retrieveParamByName(String paramName) {
		log.info("Retrieve configuration parameter for {}", paramName);
		String value = create.select(Tables.CONFIGURATION.PARAM_VALUE)
				.from(Tables.CONFIGURATION)
				.where(Tables.CONFIGURATION.PARAM_NAME.eq(paramName)).fetchOneInto(String.class);
		return value;
	}

}
