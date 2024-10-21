package endpoints;

public class Routes {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    // User module
    public static class User {
        public static String POST_URL = BASE_URL + "/user";
        public static String GET_URL = BASE_URL + "/user/{username}";
        public static String UPDATE_URL = BASE_URL + "/user/{username}";
        public static String DELETE_URL = BASE_URL + "/user/{username}";
    }

    // Store module
    public static class Store {
        public static String POST_URL = BASE_URL + "/store/order";
        public static String GET_URL = BASE_URL + "/store/order/{orderId}";
        public static String DELETE_URL = BASE_URL + "/store/order/{orderId}";
    }

    // Pet module
    public static class Pet {
        public static String POST_URL = BASE_URL + "/pet";
        public static String GET_URL = BASE_URL + "/pet/{petId}";
        public static String UPDATE_URL = BASE_URL + "/pet";
        public static String DELETE_URL = BASE_URL + "/pet/{petId}";
    }
}
