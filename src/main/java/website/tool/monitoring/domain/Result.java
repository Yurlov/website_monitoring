package website.tool.monitoring.domain;

import javax.persistence.*;

@Entity
@Table
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idResult;
    @Column(name = "responseTime")
    private Long timeToResponseFromServer;
    @Column(name = "code")
    private String responseCode;
    private String size;
    private String extra;
    @Enumerated(EnumType.STRING)
    private Status status;
    public Result() {
    }

    public Result(Long timeToResponseFromServer, String responseCode, String size, String extra, Status status) {
        this.timeToResponseFromServer = timeToResponseFromServer;
        this.responseCode = responseCode;
        this.size = size;
        this.extra = extra;
        this.status = status;
    }

    public Long getIdResult() {
        return idResult;
    }

    public void setIdResult(Long idResult) {
        this.idResult = idResult;
    }

    public Long getTimeToResponseFromServer() {
        return timeToResponseFromServer;
    }

    public void setTimeToResponseFromServer(Long timeToResponseFromServer) {
        this.timeToResponseFromServer = timeToResponseFromServer;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
