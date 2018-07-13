package com.iris.scheduler;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	SchedulerRestControllerTest.class, 
	RunJobTest.class
})
public class ScheduleJobApiTestSuite {

}
