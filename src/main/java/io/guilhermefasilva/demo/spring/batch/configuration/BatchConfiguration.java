package io.guilhermefasilva.demo.spring.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.guilhermefasilva.demo.spring.batch.models.AutoBoot;
import io.guilhermefasilva.demo.spring.batch.util.CarCustomWriter;
import io.guilhermefasilva.demo.spring.batch.util.CarCustomReader;

@EnableBatchProcessing
@Configuration
public class BatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
		
	@Autowired
	private CarCustomReader carCustomReader;
	
	@Autowired
	CarCustomWriter carCustomWriter;

	@Bean
	public Job createJob() {
		return jobBuilderFactory.get("New Job Car")
				.incrementer(new RunIdIncrementer())
				.flow(createStep()).end().build();
	}

	@Bean
	public Step createStep() {
		return stepBuilderFactory.get("New Step Car")
				.<AutoBoot, AutoBoot> chunk(1)
				.reader(carCustomReader)
				.writer(carCustomWriter)
				.build();
	}

}
