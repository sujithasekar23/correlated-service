package samplebootapp.mailcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import samplebootapp.mailcore.domain.EmailRequest;
import samplebootapp.mailcore.kafka.MessageConsumer;
import samplebootapp.mailcore.kafka.MessageProducer;
import samplebootapp.mailcore.util.TestMailUtil;

@SpringBootApplication
@ComponentScan("samplebootapp.mailcore")
@EnableAutoConfiguration
@RestController
@EnableDiscoveryClient

public class SpringBootStarterMain {
	@Autowired
	MessageProducer messageProducer;

	@Autowired
	MessageConsumer messageConsumer;

	@RequestMapping("/info")
	public String start() {
		return "mailcore-service";
	}

	@RequestMapping(value = "/mailcore/sendMail", method = RequestMethod.POST)
	public String sendMail(@RequestBody String inputJson) {
		Gson gson = new Gson();
		EmailRequest rqstObj = gson.fromJson(inputJson, EmailRequest.class);

		if (("yes").equalsIgnoreCase(rqstObj.getSendThruKafka())) {
			System.out.println("Sending Email through Kafka.");
			messageProducer.send(inputJson);
		} else {
			System.out.println("Not sending through Kafka. Emailing directly");
			TestMailUtil.sendTestEmail(inputJson);
		}

		return "Yay! " + rqstObj.getName() + ", Your mail is sent successfully! \n To: " + rqstObj.getEmail()
				+ " Through kafka: " + rqstObj.getSendThruKafka();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootStarterMain.class, args);
	}

}
