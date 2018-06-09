package samplebootapp.mailcore.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

	@Autowired
	private KafkaTemplate<String, String> msgTemplate;

	@Value("${kafka.topic}")
	String myTopic;

	public void send(String message) {

		msgTemplate.send(myTopic, message);
	}
}
