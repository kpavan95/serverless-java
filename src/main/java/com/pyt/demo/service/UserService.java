package com.pyt.demo.service;

import java.util.List;

import com.pyt.demo.dao.UserDAO;
import com.pyt.demo.model.User;
import com.pyt.demo.util.RandomUtil;

public class UserService {

	private static final int ID_SIZE = Integer.parseInt(System.getenv("ID_SIZE"));

	public static List<User> fetchUsers() {
		UserDAO userDAO = new UserDAO();
		return userDAO.fetchUsers();
	}

	public static User fetchUser(String userId) {
		UserDAO userDAO = new UserDAO();
		return userDAO.fetchUser(userId);
	}

	public static User createUser(String name) {
		String userId = RandomUtil.getAlphaNumericString(ID_SIZE);
		User user = new User();
		user.setName(name);
		user.setUserId(userId);
		UserDAO userDAO = new UserDAO();
		userDAO.save(user);
		return fetchUser(userId);
	}

	public static void deleteUser(String userId) {
		UserDAO userDAO = new UserDAO();
		userDAO.delete(userId);
	}

}
