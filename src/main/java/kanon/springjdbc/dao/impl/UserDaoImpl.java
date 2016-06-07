package kanon.springjdbc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import kanon.springjdbc.dao.UserDao;
import kanon.springjdbc.entities.User;

public class UserDaoImpl implements UserDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public User getById() {
		return null;
	}

	public int add(User user) {
		return 0;
	}

	public int delete(String id) {
		return 0;
	}
	
}
