package cn.bronzeware.muppet.websiterelease.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bronzeware.core.ioc.annotation.Autowired;
import cn.bronzeware.muppet.websiterelease.jdbc.JdbcSourceScan;
import cn.bronzeware.muppet.websiterelease.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
@Repository
public class JdbcTemplateDao {

	@org.springframework.beans.factory.annotation.Autowired
	private JdbcTemplate jdbcTemplate;
	
	@org.springframework.beans.factory.annotation.Autowired
	private JdbcSourceScan jdbcSourceScan;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Test
	public void rowCallbackHandler(){
		String sql = "select * from tb_user";
		List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getString("user_id"));
				
				return user;
			}
		});
		logger.debug(logger.isDebugEnabled());
		logger.debug(jdbcSourceScan.toString());
		logger.debug(users);
	}
	
}
