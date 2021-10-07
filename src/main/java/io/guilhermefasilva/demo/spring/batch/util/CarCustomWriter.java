package io.guilhermefasilva.demo.spring.batch.util;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import io.guilhermefasilva.demo.spring.batch.models.AutoBoot;

@Component
public class CarCustomWriter extends FlatFileItemWriter<AutoBoot>{
	
	public CarCustomWriter() {
		setResource(new FileSystemResource("src/main/resources/ouputBatch.csv"));
		setLineAggregator(getDelimitedLineAggregator());
	}
	
	
	public DelimitedLineAggregator<AutoBoot> getDelimitedLineAggregator(){
		BeanWrapperFieldExtractor<AutoBoot> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<>();
		beanWrapperFieldExtractor.setNames(new String[] {"id", "name", "car"});
		DelimitedLineAggregator<AutoBoot> delimitedLineAggregator = new DelimitedLineAggregator<>();
		delimitedLineAggregator.setDelimiter(",");
		delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
		
		return delimitedLineAggregator;
		
	}

}
