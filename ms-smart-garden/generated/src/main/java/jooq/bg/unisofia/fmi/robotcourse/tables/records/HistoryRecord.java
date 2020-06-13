/*
 * This file is generated by jOOQ.
 */
package bg.unisofia.fmi.robotcourse.tables.records;


import bg.unisofia.fmi.robotcourse.tables.History;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class HistoryRecord extends UpdatableRecordImpl<HistoryRecord> implements Record3<Integer, String, String> {

    private static final long serialVersionUID = -1825974352;

    /**
     * Setter for <code>weather-dispatcher.HISTORY.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>weather-dispatcher.HISTORY.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>weather-dispatcher.HISTORY.REQUEST_TIME</code>.
     */
    public void setRequestTime(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>weather-dispatcher.HISTORY.REQUEST_TIME</code>.
     */
    public String getRequestTime() {
        return (String) get(1);
    }

    /**
     * Setter for <code>weather-dispatcher.HISTORY.PAYLOAD</code>.
     */
    public void setPayload(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>weather-dispatcher.HISTORY.PAYLOAD</code>.
     */
    public String getPayload() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return History.HISTORY.ID;
    }

    @Override
    public Field<String> field2() {
        return History.HISTORY.REQUEST_TIME;
    }

    @Override
    public Field<String> field3() {
        return History.HISTORY.PAYLOAD;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getRequestTime();
    }

    @Override
    public String component3() {
        return getPayload();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getRequestTime();
    }

    @Override
    public String value3() {
        return getPayload();
    }

    @Override
    public HistoryRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public HistoryRecord value2(String value) {
        setRequestTime(value);
        return this;
    }

    @Override
    public HistoryRecord value3(String value) {
        setPayload(value);
        return this;
    }

    @Override
    public HistoryRecord values(Integer value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached HistoryRecord
     */
    public HistoryRecord() {
        super(History.HISTORY);
    }

    /**
     * Create a detached, initialised HistoryRecord
     */
    public HistoryRecord(Integer id, String requestTime, String payload) {
        super(History.HISTORY);

        set(0, id);
        set(1, requestTime);
        set(2, payload);
    }
}
