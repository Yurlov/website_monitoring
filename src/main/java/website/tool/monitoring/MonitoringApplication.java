package website.tool.monitoring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import website.tool.monitoring.service.UrlSchedulerService;
import website.tool.monitoring.service.UrlService;
import website.tool.monitoring.utils.Db;

@ComponentScan("website.tool.monitoring")
@SpringBootApplication
@EnableScheduling
public class MonitoringApplication {
	public static void main(String[] args) {
		SpringApplication.run(MonitoringApplication.class, args);
	}

// 	@Bean
// 	public CommandLineRunner demo(final UrlService urlService, final UrlSchedulerService schedulerService) {
// 		return strings -> Db.fillDb().forEach(url -> {
//             try {
//                 urlService.addUrl(url);
//                 schedulerService.sendUrl(url);

//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         });
// 	}
}
