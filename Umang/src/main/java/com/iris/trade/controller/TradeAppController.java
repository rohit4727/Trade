package com.iris.trade.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.iris.trade.bean.JobScheduler;
import com.iris.trade.bean.ResponseBean;
import com.iris.trade.bean.Trade;
import com.iris.trade.constants.IControllerConstants;
import com.iris.trade.response.bean.JobShedulerResponse;
import com.iris.trade.util.TradeAppPropertyUtil;

/**
 * 
 * @author pushpendra.singh
 *
 */
@Controller
@RequestMapping(IControllerConstants.TRADE_APP)
public class TradeAppController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/")
	public String Trades(Model model) {
		return "index";
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	TradeAppPropertyUtil tradeAppProperty;

	/**
	 * 
	 * @param jobScheduler
	 *            This method will run / schedule Job
	 * @return JobShedulerResponse
	 */
	@PostMapping(IControllerConstants.SCHEDULE_OR_RUN_JOB)
	@ResponseBody
	public JobShedulerResponse sheduleJob(@Valid @RequestBody JobScheduler jobScheduler) {

		JobShedulerResponse jobShedulerResponse = new JobShedulerResponse();

		jobShedulerResponse.setStatusCode(HttpStatus.NOT_MODIFIED.toString());
		jobShedulerResponse.setMessage(IControllerConstants.SCHEDULE_JOB_FAILURE);

		try {
			if (jobScheduler.getRunFrequency().equals("0")) {
				ResponseBean responseBean = restTemplate.postForObject(tradeAppProperty.getScheduleJobRestAPI(),
						jobScheduler, ResponseBean.class);
				jobShedulerResponse.setStatusCode(responseBean.getStatuscode());
				jobShedulerResponse.setMessage(responseBean.getMessage());
			} else {
				ResponseBean responseBean = restTemplate.postForObject(tradeAppProperty.getRunJobRestAPI(), jobScheduler,
						ResponseBean.class);
				jobShedulerResponse.setStatusCode(responseBean.getStatuscode());
				jobShedulerResponse.setMessage(responseBean.getMessage());
			}
		} catch (Exception ex) {

		}

		return jobShedulerResponse;
	}

	/**
	 * 
	 * @param jobScheduler
	 *            This method will update schedule Job Details
	 * @return JobShedulerResponse
	 */
	@PostMapping(IControllerConstants.UPDATE_JOB_SCHEDULE_DETAILS)
	@ResponseBody
	public JobShedulerResponse updateScheduleJobDetails(@Valid @RequestBody JobScheduler jobScheduler) {

		JobShedulerResponse jobShedulerResponse = new JobShedulerResponse();

		jobShedulerResponse.setStatusCode(HttpStatus.NOT_MODIFIED.toString());
		jobShedulerResponse.setMessage(IControllerConstants.SCHEDULE_JOB_FAILURE);

		try {

			ResponseBean responseBean = restTemplate.postForObject(
					tradeAppProperty.getUpdateJobSchedulerDetail() + IControllerConstants.SLASH + jobScheduler.getId(), jobScheduler,
					ResponseBean.class);
			jobShedulerResponse.setStatusCode(responseBean.getStatuscode());
			jobShedulerResponse.setMessage(responseBean.getMessage());

		} catch (Exception ex) {

		}

		return jobShedulerResponse;
	}

	/**
	 * This method will delete job schedule details
	 * 
	 * @param jobId
	 * @return JobShedulerResponse
	 */
	@PostMapping(IControllerConstants.DELETE_JOB_SCHEDULE_DETAIL)
	@ResponseBody
	public JobShedulerResponse deleteScheduleJobDetail(@PathVariable(value = IControllerConstants.ID) Long jobId) {

		JobShedulerResponse jobShedulerResponse = new JobShedulerResponse();

		jobShedulerResponse.setStatusCode(HttpStatus.NOT_MODIFIED.toString());
		jobShedulerResponse.setMessage(IControllerConstants.DELETE_JOB_FAILED);

		try {

			String uri = tradeAppProperty.getDeleteJobScheduler();
		    Map<String, Object> uriVars = new HashMap<String, Object>();
		    uriVars.put(IControllerConstants.ID, jobId);
		   
		    restTemplate.delete(uri, uriVars);

			jobShedulerResponse.setStatusCode(HttpStatus.OK.toString());
			jobShedulerResponse.setMessage(IControllerConstants.DELETE_SUCCESS);

		} catch (Exception ex) {

		}

		return jobShedulerResponse;
	}

	/**
	 * This method will get All schedule Job List
	 * 
	 * @return List<JobScheduler>
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(IControllerConstants.GET_ALL_JOB_SCHEDULE_DETAILS)
	@ResponseBody
	public List<JobScheduler> getAllJobScheduleDetails() {

		List<JobScheduler> jobSchedulerDetailList = new ArrayList<>();

		try {

			jobSchedulerDetailList = restTemplate.getForObject(tradeAppProperty.getAllJobScheduleDetails(), List.class);

		} catch (Exception ex) {

		}

		return jobSchedulerDetailList;
	}
	
	/**
	 * This method will get All schedule Job List
	 * 
	 * @return List<JobScheduler>
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(IControllerConstants.GET_LIVE_FEED_DATA)
	@ResponseBody
	public List<Trade> getLiveFeedData(@PathVariable(value = IControllerConstants.SECURITY) String security) {

		List<Trade> liveFeedDataList = new ArrayList<>();

		try {
			
			liveFeedDataList = restTemplate.getForObject(tradeAppProperty.getLiveFeedData() + IControllerConstants.SLASH + security, List.class);

		} catch (Exception ex) {

		}

		return liveFeedDataList;
	}

}
