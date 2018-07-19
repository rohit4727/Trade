
package com.iris.scheduler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.iris.scheduler.constants.IControllerConstants;
import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.runner.ScheduledTasks;
import com.iris.scheduler.service.SchedulerService;

/**
 * @author anchal.handa
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ScheduledJobTest {

	private MockMvc mockMvc;
	@Autowired
	WebApplicationContext wac;

	@Autowired
	SchedulerService service;

	@Autowired
	ScheduledTasks scheduledTasks;

	@Before
	public void setUp() throws Exception {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testdatabyCurDate() {
		List<JobScheduler> findbycurDate = service.findbycurDate();
		assertNotNull(findbycurDate);

	}

	@Test
	public void testFilePath() throws ParseException {

		// missing file path
		assertFalse(service.checkfilepath(SchedularTestUtil.getJobSchedular(0).getBatchFilePath()));

		// wrong file path
		assertFalse(service.checkfilepath(SchedularTestUtil.getJobSchedular(1).getBatchFilePath()));

		// correct file path
		assertTrue(service.checkfilepath(SchedularTestUtil.getJobSchedular(2).getBatchFilePath()));

	}

	@Test
	public void testRuncmd() {

		service.runcmd("E:/Gen/gen.bat", new Long(1));
		service.runcmd("E:/Gen/gen.ba", new Long(1));

	}

	@Test
	public void testSaveStatus() {
		assertNotNull(service.findbyjobId(new Long(1)));

	}

	@Test
	public void testfindbyjobId() {
		service.savestatus(service.findbyjobId(new Long(1)));

	}


}
