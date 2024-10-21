package endpoints;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import payloads.User;

public class UserEndPoints {

	public static Response createUser(User payload) {
	    Response response = given()
	            .contentType("application/json")
	            .accept("application/json")
	            .body(payload)
	            .when()
	            .post(Routes.User.POST_URL); 

	    return response;
	}

    public static Response readUser(String username) {
        Response response = given()
                .pathParam("username", username)
                .when()
                .get(Routes.User.GET_URL); 

        return response;
    }

    public static Response updateUser(User payload, String username) {
        Response response = given()
                .contentType("application/json")
                .accept("application/json")
                .body(payload)
                .pathParam("username", username)
                .when()
                .put(Routes.User.UPDATE_URL);

        return response;
    }

    public static Response deleteUser(String username) {
        Response response = given()
                .pathParam("username", username)
                .when()
                .delete(Routes.User.DELETE_URL);

        return response;
    }
}
