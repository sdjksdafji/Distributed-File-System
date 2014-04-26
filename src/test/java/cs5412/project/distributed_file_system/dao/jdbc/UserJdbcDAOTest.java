package cs5412.project.distributed_file_system.dao.jdbc;

import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cs5412.project.distributed_file_system.dao.UserDAO;
import cs5412.project.distributed_file_system.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class UserJdbcDAOTest {

	@Inject
	private UserDAO userDao;

	@Test
	public void testGetUserByUid() {
		int uid = 1;
		User u = userDao.getUserByUid(uid);
		if (u.getUsername().length() == 0) {
			fail("username is empty");
		} else {
			System.out.println("username: " + u.getUsername());
		}
	}

	@Test
	public void testCreateUser() {
		User u = new User();
		u.setUsername("testuser");
		u.setEmail("test@test.com");
		u.setHashedPassword("asdf");
		int uid = userDao.createUser(u);
		if (uid <= 0) {
			fail("create user fail");
		} else {
			System.out.println("created user uid=" + uid);
		}
	}

	@Test
	public void testUpdateUser() {
		User u = new User();
		u.setUsername("testuserUpdated");
		u.setEmail("test@test.com");
		u.setHashedPassword("asdf");
		u.setRootfid(1);
		u.setUid(1);
		boolean isSuccess = userDao.updateUser(u);
		if (!isSuccess) {
			fail("update user fail");
		}
	}
	
	@Test
	public void testUserLogin() {
		String username = "zf";
		String pw = "111";
		boolean isSuccess = userDao.userLogin(username, pw);
		if (!isSuccess) {
			fail("login user fail");
		}
	}
	
	
//
//	@Test
//	public void testDeleteUser() {
//		User u = new User();
//		u.setUsername("testuserForDelete");
//		u.setEmail("test@test.com");
//		u.setPassword("asdf");
//		int uid = userDao.createUser(u);
//		if (uid <= 0) {
//			fail("create user fail");
//		} else {
//			System.out.println("created user uid=" + uid);
//		}
//
//		u.setUid(uid);
//
//		boolean isSuccess = userDao.deleteUser(u);
//		if (!isSuccess) {
//			fail("remove user fail");
//		} else {
//			System.out.println("removed user uid=" + uid);
//		}
//	}

}
