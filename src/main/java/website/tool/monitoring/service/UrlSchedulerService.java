package website.tool.monitoring.service;

import website.tool.monitoring.domain.Url;

public interface UrlSchedulerService {
    void sendUrl(Url url);
}
