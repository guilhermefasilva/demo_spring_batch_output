package io.guilhermefasilva.demo.spring.batch.util;

import org.springframework.batch.item.ItemProcessor;

import io.guilhermefasilva.demo.spring.batch.models.AutoBoot;

public class CarProcessor implements ItemProcessor<AutoBoot, AutoBoot> {

	@Override
	public AutoBoot process(AutoBoot item) throws Exception {
		return item;
	}
	

}
