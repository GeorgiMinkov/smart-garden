package bg.unisofia.fmi.robotcourse.repository;

import java.util.List;

import bg.unisofia.fmi.robotcourse.tables.pojos.History;

public interface JooqHistory {
	
	public List<History> getHistoryByRequestTime();
}
