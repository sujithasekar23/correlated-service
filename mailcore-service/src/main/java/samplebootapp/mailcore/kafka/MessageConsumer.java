package samplebootapp.mailcore.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import samplebootapp.mailcore.domain.EmailRequest;
import samplebootapp.mailcore.util.TestMailUtil;

public class MessageConsumer {

	@Autowired
	private KafkaTemplate<String, String> msgTemplate;

	@Value("${kafka.topic}")
	String myTopic;

	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServers;

	@KafkaListener(topics = "${kafka.topic}")
	public void receive(String payload) {
		TestMailUtil.sendTestEmail(payload);
	}
}
