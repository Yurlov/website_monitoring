package website.tool.monitoring.service;

import website.tool.monitoring.domain.Url;

import java.util.List;

public interface UrlService {
    void addUrl(Url url) throws Exception;
    void delete(Long id);
    void update(Long id,Url url) throws Exception;
    Url pause(Long id);
    List<Url> findAll();
}
