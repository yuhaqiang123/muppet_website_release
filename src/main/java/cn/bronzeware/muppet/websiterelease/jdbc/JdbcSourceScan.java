package cn.bronzeware.muppet.websiterelease.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcSourceScan {

	
	private List<DataSource> dataSources;
	
	
	public List<DataSource> getDataSources() {
		return dataSources;
	}


	public void setDataSources(List<DataSource> dataSources) {
		
		this.dataSources = dataSources;
	}

	public void setDataSources(DataSource dataSource){
		this.dataSources = new ArrayList<DataSource>(1);
		this.dataSources.add(dataSource);
	}
	
	

	public static void main(String[] args){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		
	}
	
	@Override
	public String toString(){
		return String.valueOf(this.dataSources.size());
	}
	
}
