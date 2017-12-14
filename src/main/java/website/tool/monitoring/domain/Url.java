package website.tool.monitoring.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "URLInfo")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name ="name")
    private String nameUrl;
    @Column(name = "period")
    private Date period;
    @Column(name = "responseTime",nullable = false)
    private String timeToResponseFromServer;
    @Column(name = "code",nullable = false)
    private String responseCode;
    @Column(nullable = false,name = "min")
    private String minSize;
    @Column(name = "max",nullable = false)
    private String maxSize;
    @Column(nullable = false)
    private String extra;
    private Boolean pauseOrPlay;
    @OneToOne(cascade = CascadeType.ALL)
    private Result result;

    public Url() {
    }

    public Url(String nameUrl, Date period, String timeToResponseFromServer, String responseCode, String minSize, String maxSize, String extra, Result result) {
        this.nameUrl = nameUrl;
        this.period = period;
        this.timeToResponseFromServer = timeToResponseFromServer;
        this.responseCode = responseCode;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.extra = extra;
        this.result = result;
    }

    public Url(Date period, String timeToResponseFromServer, String responseCode, String minSize, String maxSize, String extra) {
        this.period = period;
        this.timeToResponseFromServer = timeToResponseFromServer;
        this.responseCode = responseCode;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.extra = extra;
    }

    public Url(String nameUrl, Date period, String timeToResponseFromServer, String responseCode, String minSize, String maxSize, String extra) {
        this.nameUrl = nameUrl;
        this.period = period;
        this.timeToResponseFromServer = timeToResponseFromServer;
        this.responseCode = responseCode;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.extra = extra;
    }

    public Url(String nameUrl, Date period, String timeToResponseFromServer, String responseCode, String minSize, String maxSize, String extra, Boolean pauseOrPlay, Result result) {
        this.nameUrl = nameUrl;
        this.period = period;
        this.timeToResponseFromServer = timeToResponseFromServer;
        this.responseCode = responseCode;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.extra = extra;
        this.pauseOrPlay = pauseOrPlay;
        this.result = result;
    }

    public Boolean getPauseOrPlay() {
        return pauseOrPlay;
    }

    public void setPauseOrPlay(Boolean pauseOrPlay) {
        this.pauseOrPlay = pauseOrPlay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public String getTimeToResponseFromServer() {
        return timeToResponseFromServer;
    }

    public void setTimeToResponseFromServer(String timeToResponseFromServer) {
        this.timeToResponseFromServer = timeToResponseFromServer;
    }

    public String getNameUrl() {
        return nameUrl;
    }

    public void setNameUrl(String nameUrl) {
        this.nameUrl = nameUrl;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMinSize() {
        return minSize;
    }

    public void setMinSize(String minSize) {
        this.minSize = minSize;
    }

    public String getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(String maxSize) {
        this.maxSize = maxSize;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


}
