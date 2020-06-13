package bg.unisofia.fmi.robotcourse.repository.impl;

import java.util.List;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

import bg.unisofia.fmi.robotcourse.Tables;
import bg.unisofia.fmi.robotcourse.repository.JooqHistory;
import bg.unisofia.fmi.robotcourse.tables.pojos.History;

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
}
