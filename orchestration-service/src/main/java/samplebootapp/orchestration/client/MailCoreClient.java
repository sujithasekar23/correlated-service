package samplebootapp.orchestration.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("mailcore-service")
@Component
public interface MailCoreClient {
	@RequestMapping(method = RequestMethod.POST, value = "/mailcore/sendMail", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String sendMail(@RequestBody String inputJson);
}
