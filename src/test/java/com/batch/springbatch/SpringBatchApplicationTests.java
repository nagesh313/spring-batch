package com.batch.springbatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBatchTest
public class SpringBatchApplicationTests {
    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;
    @Test
    public void runBatch() throws Exception {
        JobExecution exe = this.jobLauncherTestUtils.launchJob();
        assertEquals(exe.getStatus(), BatchStatus.COMPLETED);
    }

}
