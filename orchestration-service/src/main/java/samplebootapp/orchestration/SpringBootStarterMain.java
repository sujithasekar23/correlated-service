package samplebootapp.orchestration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import samplebootapp.orchestration.client.MailCoreClient;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@ComponentScan({"samplebootapp.orchestration","samplebootapp.orchestration.client"})
@EnableAutoConfiguration
@RestController
@EnableEurekaClient
@EnableFeignClients(basePackages = {"samplebootapp.orchestration.client"})
public class SpringBootStarterMain {
	
	@Autowired
	MailCoreClient mailCoreClient;
	
	@RequestMapping("/info")
	public String info() {
		return "orchestration-service";
	}
	
	@RequestMapping(value = "/orchestration/sendMail", method = RequestMethod.POST)
	public String sendMail(@RequestBody String inputJson) {
		String returnMsg = mailCoreClient.sendMail(inputJson);
		return returnMsg ;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootStarterMain.class, args);
	}

}
