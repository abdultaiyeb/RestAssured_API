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
	            .post(Routes.post_url); 

	    return response;
	}

    public static Response readUser(String username) {
        Response response = given()
                .pathParam("username", username)
                .when()
                .get(Routes.get_url); 

        return response;
    }

    public static Response updateUser(User payload, String username) {
        Response response = given()
                .contentType("application/json")
                .accept("application/json")
                .body(payload)
                .pathParam("username", username)
                .when()
                .put(Routes.update_url);

        return response;
    }

    public static Response deleteUser(String username) {
        Response response = given()
                .pathParam("username", username)
                .when()
                .delete(Routes.delete_url);

        return response;
    }
}
