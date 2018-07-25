package com.iris.scheduler;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * 
 * @author pushpendra.singh
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	SchedulerRestControllerTest.class, 
	RunJobTest.class,
	ScheduledJobTest.class
})
public class ScheduleJobApiTestSuite {

}
