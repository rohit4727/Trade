package com.iris.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iris.scheduler.entity.JobScheduler;

/**
 * @author anchal.handa
 *
 */

public class SchedularTestUtil {

	
	public static List<String> retrieveAllData() {

		return Arrays.asList(new String[] { "1,,Job2emptyfilepath,2018-07-11 11:09:06,0", //filepathmissing
				"2,E:/Gen/gen.tx,Job106emptyfilepath,2018-07-11 11:10:06,0",			//Wrongfilepath
				"3,E:/Gen/gen.bat,Job4emptyfilepath,2018-07-11 11:11:06,0",                           //jobnamemissing
				",E:/Gen/gen.bat,Job5emptyfilepath,2018-07-11 11:12:06,1",		  //jobid missing
				"5,E:/Gen/gen.bat,Job107emptyfilepath,2018-07-11 11:13:06,2",		 //status value is 2
				"",     															//empty row
				"7,null,Job6emptyfilepath,2018-07-11 11:13:06,0",                //null file path

		});
	}

	
	public static JobScheduler getJobSchedular(int index) throws ParseException {
		
		String retrivejob=retrieveAllData().get(index);
		JobScheduler job = new JobScheduler();
		job.setId(new Long(retrivejob.split(",")[0]));
		job.setBatchFilePath(retrivejob.split(",")[1]);
		job.setJobName(retrivejob.split(",")[2]);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date(sdf.parse(retrivejob.split(",")[3]).getTime());
		job.setScheduleDate(date);
		job.setStatus(retrivejob.split(",")[4]);
		return job;

	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
