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
import com.iris.trade.bean.ResponseBean;
import com.iris.trade.bean.TradeAppPropertyBean;
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
	
	@Autowired
	TradeAppPropertyBean tradeAppProperty;
	
	@PostMapping(IControllerConstants.SCHEDULE_OR_RUN_JOB)
	@ResponseBody
	public JobShedulerResponse sheduleJob(@Valid @RequestBody JobScheduler jobScheduler) {
		
		JobShedulerResponse jobShedulerResponse = new JobShedulerResponse();
		if(jobScheduler.getRunFrequency().equals("0")){
			jobShedulerResponse.setStatusCode(HttpStatus.NOT_MODIFIED.toString());
			jobShedulerResponse.setMessage(IControllerConstants.SCHEDULE_JOB_FAILURE);
			jobScheduler = restTemplate.postForObject(tradeAppProperty.scheduleJobRestAPI,jobScheduler , JobScheduler.class) ;
			
			if(jobScheduler.getId() != null) {
				jobShedulerResponse.setStatusCode(HttpStatus.OK.toString());
				jobShedulerResponse.setMessage(IControllerConstants.SCHEDULE_JOB_SUCCESS);
			}
		} else {
			ResponseBean responseBean = restTemplate.postForObject(tradeAppProperty.runJobRestAPI, jobScheduler , ResponseBean.class) ;
			jobShedulerResponse.setStatusCode(responseBean.getStatuscode());
			jobShedulerResponse.setMessage(responseBean.getMessage());
		}
		
		return jobShedulerResponse;
	}
	
		
}
