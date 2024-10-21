package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.StoreEndPoints;
import io.restassured.response.Response;
import payloads.Store;

import java.sql.Date;

public class StoreTests {
    Faker faker;
    Store storePayload;
    public Logger logger;

    @BeforeClass
    public void setup() {
        faker = new Faker();
        storePayload = new Store();

        storePayload.setId(faker.number().randomDigitNotZero());
        storePayload.setQuantity(faker.number().numberBetween(1, 10));
        storePayload.setShipDate(new Date(System.currentTimeMillis()));
        storePayload.setStatus("placed");
        storePayload.setComplete(false);

        logger = LogManager.getLogger(this.getClass());
        logger.debug("Store setup complete with generated data.");
    }

    @Test(priority = 1)
    public void testPostStore() {
        logger.info("********** Creating store order ***************");
        Response response = StoreEndPoints.createStore(storePayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200, "Expected HTTP status code 200 for successful creation.");

        logger.info("********** Store order created ***************");
    }

    @Test(priority = 2)
    public void testGetStore() {
        logger.info("********** Reading Store Info ***************");

        Response response = StoreEndPoints.getStore(this.storePayload.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200, "Expected HTTP status code 200 for successful retrieval.");

        logger.info("********** Store info displayed ***************");
    }

   

    @Test(priority = 3)
    public void testDeleteStore() {
        logger.info("********** Deleting Store ***************");

        Response response = StoreEndPoints.deleteStore(this.storePayload.getId());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected HTTP status code 200 for successful deletion.");

        logger.info("********** Store deleted ***************");
    }
}
