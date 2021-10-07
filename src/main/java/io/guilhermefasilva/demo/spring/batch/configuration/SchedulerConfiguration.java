package io.guilhermefasilva.demo.spring.batch.configuration;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableScheduling
public class SchedulerConfiguration {
	
	@Autowired
	 private JobLauncher jobLauncher;
	
	@Autowired
	 private Job job;
	
	SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss.S");
	
	@Scheduled(fixedDelay = 5000, initialDelay = 5000)
	public void scheduleByFixerRate() throws Exception{
		log.info("Batch Job Starting");
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("time", format.format(Calendar.getInstance().getTime())).toJobParameters();
		jobLauncher.run(job, jobParameters);
		log.info("Batch job Executed Success");
		
	}

}
