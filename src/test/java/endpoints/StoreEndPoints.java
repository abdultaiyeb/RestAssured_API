package endpoints;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import payloads.Store;

public class StoreEndPoints {

    public static Response createStore(Store payload) {
        Response response = given()
                .contentType("application/json")
                .accept("application/json")
                .body(payload)
                .when()
                .post(Routes.Store.POST_URL);

        return response;
    }

    public static Response getStore(int orderId) {
        Response response = given()
                .pathParam("orderId", orderId)
                .when()
                .get(Routes.Store.GET_URL);

        return response;
    }

   

    public static Response deleteStore(int orderId) {
        Response response = given()
                .pathParam("orderId", orderId)
                .when()
                .delete(Routes.Store.DELETE_URL);

        return response;
    }
}
