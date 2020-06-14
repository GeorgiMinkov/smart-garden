package bg.unisofia.fmi.robotcourse.repository.impl;

import java.util.List;
import java.util.Optional;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bg.unisofia.fmi.robotcourse.Tables;
import bg.unisofia.fmi.robotcourse.repository.JooqHistory;
import bg.unisofia.fmi.robotcourse.tables.pojos.History;
import bg.unisofia.fmi.robotcourse.tables.records.HistoryRecord;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class JooqHisoryImpl implements JooqHistory {

	private final DSLContext create;
	
	@Autowired
	public JooqHisoryImpl(DSLContext create) {
		this.create = create;
	}
	
	@Override
	public List<History> getHistoryByRequestTime() {
		return create.select().from(Tables.HISTORY).orderBy(Tables.HISTORY.REQUEST_TIME.desc()).fetchInto(History.class);
	}

	@Override
	public void create(Optional<History> src) {
		if (!src.isPresent()) {
			log.info("No history inserted");
			return;
		}
		
		HistoryRecord record = create.newRecord(Tables.HISTORY, src.get());
		record.store();
		log.info("New History record saved");
		
	}
}
