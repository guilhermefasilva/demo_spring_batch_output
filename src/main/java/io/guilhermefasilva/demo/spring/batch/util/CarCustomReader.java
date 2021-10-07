package io.guilhermefasilva.demo.spring.batch.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import io.guilhermefasilva.demo.spring.batch.models.AutoBoot;

@Component
public class CarCustomReader extends JdbcCursorItemReader<AutoBoot> implements ItemReader<AutoBoot> {
	
	public CarCustomReader(@Autowired DataSource dataSource) {
		setDataSource(dataSource);
		setSql("SELECT * FROM tb_car");
		setFetchSize(100);
		setRowMapper(new CarRowMapper());
	}

	public class CarRowMapper implements RowMapper<AutoBoot>{
	@Override
	public AutoBoot mapRow(ResultSet rs, int rowNum) throws SQLException {
			AutoBoot autoBoot = new AutoBoot();
			autoBoot.setId(rs.getLong("Id"));
			autoBoot.setName(rs.getString("Name"));
			autoBoot.setCar(rs.getString("Car"));
		
		return autoBoot;
		}
	}
	

	

}
