package kanon.springjdbc.dao;

import kanon.springjdbc.entities.User;

public interface UserDao {
	public User getById();
	public int add(User user);
	public int delete(String id);
}
