/*
 * This file is generated by jOOQ.
 */
package bg.unisofia.fmi.robotcourse.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class History implements Serializable {

    private static final long serialVersionUID = 163052353;

    private Integer id;
    private String  requestTime;
    private String  payload;

    public History() {}

    public History(History value) {
        this.id = value.id;
        this.requestTime = value.requestTime;
        this.payload = value.payload;
    }

    public History(
        Integer id,
        String  requestTime,
        String  payload
    ) {
        this.id = id;
        this.requestTime = requestTime;
        this.payload = payload;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequestTime() {
        return this.requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getPayload() {
        return this.payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("History (");

        sb.append(id);
        sb.append(", ").append(requestTime);
        sb.append(", ").append(payload);

        sb.append(")");
        return sb.toString();
    }
}