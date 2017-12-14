package website.tool.monitoring.controller;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import website.tool.monitoring.domain.Result;
import website.tool.monitoring.domain.Url;
import website.tool.monitoring.service.UrlSchedulerService;
import website.tool.monitoring.service.UrlService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 @author Viktor Yurlov
 */

@Controller
public class MainController {

    public MainController(UrlService urlService, UrlSchedulerService schedulerService) {
        this.urlService = urlService;
        this.schedulerService = schedulerService;
    }

    private UrlService urlService;

    private UrlSchedulerService schedulerService;

    private final SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("urls",urlService.findAll());
        return "index";
    }


    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public String  trialAll(Model model){
        model.addAttribute("urls",urlService.findAll());
        return "index :: urlList";
    }



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String  addUrl(@RequestParam String nameUrl,
                         @RequestParam  String extra,
                         @RequestParam  Integer minSize,
                         @RequestParam  Integer maxSize,
                         @RequestParam  String period,
                         @RequestParam  String responseTime,
                         @RequestParam  Integer responseCode) throws Exception {


        Url url = new Url(nameUrl,period==""?null: sp.parse(period),
               responseTime,
             String.valueOf(responseCode),String.valueOf(minSize),String.valueOf(maxSize),extra, new Result());
        urlService.addUrl(url);
        schedulerService.sendUrl(url);

        return "redirect:/";
    }
    @GetMapping(value = "/delete/{id}")
    public String deleteUrl(Model model,@PathVariable Long id){
        urlService.delete(id);
        model.addAttribute("urls",urlService.findAll());
        return "index :: urlList";
    }

    @GetMapping(value = "/pause/{id}")
    public String paused(Model model,@PathVariable Long id) throws Exception {
        schedulerService.sendUrl(urlService.pause(id));
        model.addAttribute("urls",urlService.findAll());
        return "index :: urlList";
    }

    @RequestMapping(value = "/update")
    public String updateUrl(@RequestParam  Long id,
                            @RequestParam String extra,
                            @RequestParam String minSize,
                            @RequestParam String maxSize,
                            @RequestParam String period,
                            @RequestParam String responseTime,
                            @RequestParam String responseCode) throws ParseException {
        Url newUrl = new Url(Objects.equals(period, "")?null:sp.parse(period),
               responseTime,
              responseCode,minSize,maxSize ,extra);
        urlService.update(id,newUrl);
        return "redirect:/";
    }

}
