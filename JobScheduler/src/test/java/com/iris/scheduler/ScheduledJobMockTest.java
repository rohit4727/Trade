package com.iris.scheduler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.text.ParseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.iris.scheduler.entity.JobScheduler;
import com.iris.scheduler.service.SchedulerService;

@RunWith(MockitoJUnitRunner.class)
public class ScheduledJobMockTest {

	@Mock
	ScheduledTasks scheduledTasks;
	@Mock
	SchedulerService schedulerService;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	@MockBean
	JobScheduler jobScheduler;

	@Before
	public void setUp() throws Exception {

	}

	// Test case to handle file path for schedule job
	@Test()
	public void testFilePath() throws ParseException {

		// missing file path
		when(schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(0).getBatchFilePath())).thenReturn(false);
		assertFalse("Handle Missing File Path in database",
				schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(0).getBatchFilePath()));
		verify(schedulerService).checkfilepath(SchedularTestUtil.getJobSchedular(0).getBatchFilePath());

		// wrong file path
		when(schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(1).getBatchFilePath())).thenReturn(false);
		assertFalse("Handle Wrong File Path in database",
				schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(1).getBatchFilePath()));
		verify(schedulerService).checkfilepath(SchedularTestUtil.getJobSchedular(1).getBatchFilePath());

		// correct file path
		when(schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(2).getBatchFilePath())).thenReturn(true);
		assertTrue("Handle Correct File Path in database",
				schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(2).getBatchFilePath()));
		verify(schedulerService).checkfilepath(SchedularTestUtil.getJobSchedular(2).getBatchFilePath());
	}

	@Test(expected = NullPointerException.class)
	public void testFilePathWhenNull() throws ParseException {

		when(schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(2).getBatchFilePath()))
				.thenThrow(NullPointerException.class);

		schedulerService.checkfilepath(SchedularTestUtil.getJobSchedular(2).getBatchFilePath());
		verify(schedulerService).checkfilepath(SchedularTestUtil.getJobSchedular(2).getBatchFilePath());
	}

}
