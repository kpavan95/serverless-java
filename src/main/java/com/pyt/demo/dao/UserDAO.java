package com.pyt.demo.dao;

import java.util.List;
import java.util.logging.Logger;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.pyt.demo.model.User;

public class UserDAO {

	private static final Logger log = Logger.getLogger(UserDAO.class.getName());

	private static final String USER_TABLE_NAME = System.getenv("USER_TABLE_NAME");

	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).build();

	private final DynamoDBMapper mapper;

	public UserDAO() {
		this.mapper = new DynamoDBMapper(client);
	}

	public Boolean doesTableExists() {
		return client.describeTable(USER_TABLE_NAME).getTable().getTableStatus().equals("ACTIVE");
	}

	public List<User> fetchUsers() {
		return this.mapper.scan(User.class, new DynamoDBScanExpression());
	}

	public User fetchUser(String userId) {
		log.info("UserId : "+userId);
		User user = this.mapper.load(User.class, userId);
		if (user != null) {
			log.info("User - get(): User - " + user.toString());
		} else {
			log.info("User - get(): User - Not Found. : " + userId);
		}
		return user;
	}

	public void save(User user) {
		log.info("User - save(): " + user.toString());
		this.mapper.save(user);
	}

	public Boolean delete(String userId) {
		User user = null;

		// get user if exists
		user = fetchUser(userId);
		if (user != null) {
			log.info("User - delete(): " + user.toString());
			this.mapper.delete(user);
		} else {
			log.info("User - delete(): user - does not exist.");
			return false;
		}
		return true;
	}

}
