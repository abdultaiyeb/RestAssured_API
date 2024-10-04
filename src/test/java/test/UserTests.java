package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import endpoints.UserEndPoints;
import io.restassured.response.Response;
import payloads.User;

public class UserTests {
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		faker= new Faker();
		userPayload= new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());	
		
logger= LogManager.getLogger(this.getClass());
		
		logger.debug("debugging.....");
	}
	
	@Test (priority=1)
	public void testPostUser(){
		logger.info("********** Creating user  ***************");
		Response response= UserEndPoints.createUser(userPayload);
		response.then().log().all();
	//	Assert.assertEquals(response.getStatusCode(), 201);
		
		logger.info("**********User is created  ***************");
		
	}
	
	@Test (priority=2)
	public void testGetUser() {
		logger.info("********** Reading User Info ***************");
		
		Response response= UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**********User info  is displayed ***************");
		
	}
	
	@Test (priority=3)
	public void testUpdateUser() {
		logger.info("********** Updating User ***************");
		
	    userPayload.setFirstname(faker.name().firstName());
	    userPayload.setLastname(faker.name().lastName());
	    userPayload.setEmail(faker.internet().safeEmailAddress());

	    Response response = UserEndPoints.updateUser(userPayload, this.userPayload.getUsername());
	    response.then().log().all();

	    // checking data after update
	    Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
	    responseAfterUpdate.then().log().all();
	    Assert.assertEquals(response.getStatusCode(), 200, "Update request failed.");

	    logger.info("********** User updated ***************");
	}

	
	
	@Test (priority=4)
	
	public void testDeleteUser() {
		logger.info("**********   Deleting User  ***************");
		
		Response response= UserEndPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200, "Expected HTTP status code 200 for successful deletion.");
		
		logger.info("********** User deleted ***************");
	}
	
	
	
	
	
	
	
	
	
	
	
}
