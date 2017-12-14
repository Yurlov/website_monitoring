package website.tool.monitoring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import website.tool.monitoring.domain.Result;
import website.tool.monitoring.domain.Status;
import website.tool.monitoring.domain.Url;
import website.tool.monitoring.repository.UrlRepository;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UrlServiceImpl implements UrlService {
    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    private UrlRepository urlRepository;

    @Override
    public List<Url> findAll() {
        return urlRepository.findAll();
    }

    @Override
    @Transactional
    public void addUrl(Url url) throws Exception {
        Pattern p = Pattern.compile("^(https?:\\/\\/)?(www)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})(\\/)?");
        Matcher m = p.matcher(url.getNameUrl());
        if(!m.find()){
            throw new Exception("Url not available");
        }
        if(url.getNameUrl().startsWith("www")) {
            url.setNameUrl("http://" + url.getNameUrl());
        }
        if(!url.getNameUrl().startsWith("www")&& !url.getNameUrl().startsWith("http")){
            url.setNameUrl("http://www."+url.getNameUrl());
        }
        if(url.getNameUrl()==""||url.getExtra()==""||url.getMaxSize()==""||url.getMinSize()==""||url.getResponseCode()==""||url.getTimeToResponseFromServer()==""||url.getPeriod()==null){
            throw new Exception("Fields was empty");
                    }
        urlRepository.save(url);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        urlRepository.delete(id);
    }

    @Override
    public Url pause(Long id) {
       Url url =  urlRepository.findOne(id);
       if(url.getPauseOrPlay()){
           url.setPauseOrPlay(false);
           url.getResult().setStatus(Status.REPAIR);
       }else {
           url.setPauseOrPlay(true);
       }
       urlRepository.save(url);
       return urlRepository.findOne(id);
    }

    @Override
    @Transactional
    public void update(Long id, Url url) {
    Url old = urlRepository.getOne(id);
        if(!Objects.equals(url.getExtra(), "")){
            old.setExtra(url.getExtra());
        }
        if(!Objects.equals(url.getMaxSize(), "")){
            old.setMaxSize(url.getMaxSize());
        }
        if (!Objects.equals(url.getMinSize(), "")){
            old.setMinSize(url.getMinSize());
        }
        if(!Objects.equals(String.valueOf(url.getResponseCode()), "")){
            old.setResponseCode(url.getResponseCode());
        }
        if(url.getPeriod()!=null){
            old.setPeriod(url.getPeriod());
        }
        if(!Objects.equals(url.getTimeToResponseFromServer(), "")){
            old.setTimeToResponseFromServer(url.getTimeToResponseFromServer());
        }
        urlRepository.save(old);
    }
}
