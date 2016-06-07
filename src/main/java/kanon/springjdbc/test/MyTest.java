package kanon.springjdbc.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kanon.springjdbc.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring.xml")
public class MyTest 
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * public int update(String sql,Object... args) throws DataAccessException
	 * 执行insert、update、delete
	 */
	@Test
	public void updateTest()
	{
		String sql = "insert into USER(id,name,age,sex) values(?,?,?,?)";
		Integer i = jdbcTemplate.update(sql, 1,"TOM",13,"男");
		System.out.println(i);
		
		sql = "update USER set name=? where id = ?";
		i = jdbcTemplate.update(sql,"TOM 2",1);
		System.out.println(i);
		
		sql = "delete from USER where id =?";
		i = jdbcTemplate.update(sql,1);
		System.out.println(i);
	}
	
	/**
	 * 执行批量更新: 批量的 INSERT, UPDATE, DELETE
	 * public int[] batchUpdate(String sql, List<Object[]> batchArgs)
	 */
	@Test
	public void batchUpdateTest()
	{
		List<Object[]> args = new ArrayList<>();
		String sql = "insert into USER (id,name,age,sex) values(?,?,?,?)";
		for(int i = 0;i<5 ; i++){
			args.add(new Object[]{i,"Tom "+i,i,"男"});
		}
		jdbcTemplate.batchUpdate(sql,args);
		args.clear();

		sql = "update USER set name = ? where id = ?";
		for(int i = 0 ;i <5 ;i++)
		{
			args.add(new Object[]{"Tom "+i+1,i});
		}
		jdbcTemplate.batchUpdate(sql,args);
		
		args.clear();
		
		sql = "delete from USER where id = ?";
		for(int j = 0;j<5;j++)
		{
			args.add(new Object[]{j});
		}
		jdbcTemplate.batchUpdate(sql,args);
		
	}
	
	
	/**
	 * 从数据库中获取一条记录, 实际得到对应的一个对象
	 * 注意不是调用 queryForObject(String sql, Class<Employee> requiredType, Object... args) 方法!
	 * 而需要调用 queryForObject(String sql, RowMapper<Employee> rowMapper, Object... args)
	 * 1. 其中的 RowMapper 指定如何去映射结果集的行, 常用的实现类为 BeanPropertyRowMapper
	 * 2. 使用 SQL 中列的别名完成列名和类的属性名的映射. 例如 last_name lastName
	 * 3. 不支持级联属性. JdbcTemplate 到底是一个 JDBC 的小工具, 而不是 ORM 框架
	 */
	@Test
	public void testQueryForObject(){
		String sql = "select * from USER where id = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper,1);
		System.out.println(user);
	}
	

}
