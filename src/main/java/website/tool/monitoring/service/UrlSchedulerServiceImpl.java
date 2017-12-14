package website.tool.monitoring.service;


import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import website.tool.monitoring.domain.Result;
import website.tool.monitoring.domain.Status;

import website.tool.monitoring.domain.Url;
import website.tool.monitoring.repository.UrlRepository;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UrlSchedulerServiceImpl implements UrlSchedulerService {

    public UrlSchedulerServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    private UrlRepository urlRepository;
    private final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    //every 9s send request to all ulrs
    @Scheduled(fixedRate = 9000)
    private void run() throws Exception  {
        urlRepository.findAll().parallelStream().forEach(this::sendUrl);
    }

    public void sendUrl(Url url)  {

        if(url.getPeriod().getTime() < new Date().getTime()){
            return;
        }
        if(url.getPauseOrPlay()==null){
            url.setPauseOrPlay(true);
        }
        if(!url.getPauseOrPlay()){
            return;
        }
        Long start = System.currentTimeMillis();
        ResponseEntity<String> rs = restTemplate.getForEntity(url.getNameUrl(), String.class);
        Long end = System.currentTimeMillis();
        Long timeResponse = end - start;
        url.getResult().setTimeToResponseFromServer(timeResponse);
        url.getResult().setResponseCode(String.valueOf(rs.getStatusCodeValue()));
        url.getResult().setSize(String.valueOf(rs.getBody().length()));
        url.getResult().setStatus(Status.OK);

        urlRepository.save(checkUrl(url,rs));

    }
    /*
    @return Url
     */
    private Url checkUrl(Url url,ResponseEntity rs){
      final List<String> allTimes = new ArrayList<>(3);

        //подстрока, которая должна содержаться в респонсе.
        //Если подстроки нету, статус URL-а должен быть CRITICAL.
        Pattern p = Pattern.compile(url.getExtra());
        Matcher m = p.matcher(rs.getBody().toString());

        if(!m.find()){
            url.getResult().setExtra("Not found");
            url.getResult().setStatus(Status.CRITICAL);
        }else {
            url.getResult().setExtra("Found");
        }

        //Ожидаемый HTTP response code
        if(!Objects.equals(url.getResult().getResponseCode(), url.getResponseCode())){
            url.getResult().setStatus(Status.CRITICAL);
        }

        //Ожидаемый диапазон размера респонса в байтах (min и max).
        // Если размер контента выходит за пределы допустимого диапазона, статус URL-а
        // должен быть CRITICAL.
        if(rs.getBody().toString().length() > Integer.parseInt(url.getMaxSize()) ||  rs.getBody().toString().length() < Integer.parseInt(url.getMinSize())){
            url.getResult().setStatus(Status.CRITICAL);
        }

        //Время ответа сервера (отдельные пороги для OK, WARNING, CRITICAL)
        allTimes.addAll(Arrays.asList(url.getTimeToResponseFromServer().split("/")));

        if (url.getResult().getTimeToResponseFromServer() > Long.parseLong(allTimes.get(0)) && url.getResult().getTimeToResponseFromServer() < Long.parseLong(allTimes.get(1))){
            if(url.getResult().getStatus()!=Status.CRITICAL)
                url.getResult().setStatus(Status.WARNING);
        } else if(url.getResult().getTimeToResponseFromServer() > Long.parseLong(allTimes.get(1))){
              url.getResult().setStatus(Status.CRITICAL);
           }
        return url;
    }
}
