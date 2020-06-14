package bg.unisofia.fmi.robotcourse.repository;

import java.util.List;
import java.util.Optional;

import bg.unisofia.fmi.robotcourse.tables.pojos.History;

public interface JooqHistory {

	public void create(Optional<History> src);
	
	public List<History> getHistoryByRequestTime();
}
