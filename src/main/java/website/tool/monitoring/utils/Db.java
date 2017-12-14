package website.tool.monitoring.utils;

import website.tool.monitoring.domain.Result;
import website.tool.monitoring.domain.Status;
import website.tool.monitoring.domain.Url;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class Db {
    private static final SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static  List<Url> fillDb() throws ParseException {
        Url url1 = new Url("https://mafia.ua/ua/kiev",sp.parse("2018-05-22 22:22"),"1111/2222/3333","200","200","500000","2",new Result(0L,"0","0","0", Status.OK));
        Url url2 = new Url("http://www.vk.com",sp.parse("2018-05-22 22:22"),"1111/2222/3333","200","200","500000","2",new Result(0L,"0","0","0", Status.OK));
        Url url3 = new Url("http://stackoverflow.com",sp.parse("2018-05-22 22:22"),"1111/2222/3333","200","200","500000","2", new Result(0L,"0","0","0", Status.OK));
        Url url4 = new Url("http://gmail.com",sp.parse("2018-05-22 22:22"),"1111/2222/3333","200","200","500000","2", new Result(0L,"0","0","0", Status.OK));
        Url url5 = new Url("https://www.providesupport.com",sp.parse("2018-05-22 22:22"),"1111/2222/3333","200","200","500000","2", new Result(0L,"0","0","0", Status.OK));
        return Arrays.asList(url1,url2,url3,url4,url5);
    }

}
