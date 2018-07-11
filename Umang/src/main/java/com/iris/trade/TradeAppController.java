package com.iris.trade;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.iris.trade.bean.JobScheduler;
import com.iris.trade.constants.IControllerConstants;
import com.iris.trade.response.bean.JobShedulerResponse;

@Controller
public class TradeAppController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/")
	public String Trades(Model model) 
	{
		return "index";
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@PostMapping(IControllerConstants.SCHEDULE_JOB)
	@ResponseBody
	public JobShedulerResponse sheduleJob(@Valid @RequestBody JobScheduler jobScheduler) {
		
		JobShedulerResponse jobShedulerResponse = new JobShedulerResponse();
		jobShedulerResponse.setStatusCode(HttpStatus.OK.toString());
		jobShedulerResponse.setMessage(IControllerConstants.SCHEDULE_JOB_SUCCESS);
		
		jobScheduler = restTemplate.postForObject("http://172.16.14.32:8090/jobScheduler/createJobScheduler",jobScheduler , JobScheduler.class) ;
		return jobShedulerResponse;
	}
	
	
}
