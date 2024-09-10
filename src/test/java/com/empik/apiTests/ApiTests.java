package com.empik.apiTests;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

@Log4j2
public class ApiTests {

    private static final Logger log = LogManager.getLogger(ApiTests.class);

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://www.empik.com";
    }

    public String preCondition() {
        Response response = given().when().get("https://www.empik.com").then().extract().response();
        Map<String, String> map = new HashMap<>(response.getCookies());
        return map.toString();
    }

    @Test
    public void getPopularCategories() {
        log.info("getPopularCategories - START");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .queryParam("limit", 5)
                .when().get("ajax/diuna/popularCategories")
                .then().log().status()
                .statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getPopularCategoriesJsonSchema.json"));
        log.info("getPopularCategories - END");
    }

    @Test
    public void getGam() {
        log.info("getGam - START");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .when().get("ajax2/gam")
                .then().log().status()
                .statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getGamJsonSchema.json"));
        log.info("getGam - END");
    }

    @Test
    public void getChatBot () {
        log.info("getChatBot - START");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .when().get("https://waw.chat.getzowie.com/api/v1/herochat-plugin/instances/55ba5f792e694813b99f99671946396a/multilingual/livechat")
                .then().log().status()
                .statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getChatBotJsonSchema.json"));
        log.info("getChatBot - END");
    }

    @Test
    public void getLimitsConfiguration() {
        log.info("getLimitsConfiguration - START");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .queryParam("sourceSystemChannel", "PORTAL_DESKTOP")
                .when().get("gateway/api/carts/limits-configuration")
                .then().log().status()
                .statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getLimitsConfigurationJsonSchema.json"));
        log.info("getLimitsConfiguration - END");
    }

    @Test
    public void getItemNotFound() {
        log.info("getItemNotFound - START");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .when().get("pakiet-harry-potter-tomy-1-7-rowling-j-k,p1397131470")
                .then().log().status()
                .statusCode(404);
        log.info("getItemNotFound - END");
    }

    @Test
    public void putSnapshots() {
        log.info("putSnapshots - START");
        File file = new File("src/test/resources/json/snapshots.json");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .contentType(ContentType.JSON).body(file)
                .when().put("gateway/api/kotoserver/snapshots")
                .then().log().status()
                .statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/putSnapshotsJsonSchema.json"));
        log.info("putSnapshots - END");
    }

    @Test(priority = 1)
    public void postQuantityIncreaseNoItemsInCart() {
        log.info("postQuantityIncreaseNoItemsInCart - START");
        File file = new File("src/test/resources/json/quantityIncrease.json");
        Response response =
                given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                        .log().uri()
                        .contentType(ContentType.JSON).body(file)
                        .when().post("gateway/api/graphql/cart")
                        .then().log().status()
                        .statusCode(200)
                        .extract().response();

        assertTrue(response.getBody().asString().contains("\"errorType\":\"ValidationError\""));
        log.info("postQuantityIncreaseNoItemsInCart - END");
    }

    @Test
    public void postItemActionsInCart() {
        log.info("postItemActionsInCart - START");
        String cookies = this.preCondition();
        File addToCart = new File("src/test/resources/json/addProductsToCart.json");
        File quantityIncrease = new File("src/test/resources/json/quantityIncrease.json");
        File quantityDecrease = new File("src/test/resources/json/quantityDecrease.json");
        String path = "gateway/api/graphql/cart";

        Response postaddToCart =
                given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri().cookie(cookies).contentType(ContentType.JSON).body(addToCart).when().post(path).then().log().status().statusCode(200).extract().response();
        Response postQuantityIncrease =
                given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri().cookie(cookies).contentType(ContentType.JSON).body(quantityIncrease).when().post(path).then().log().status().statusCode(200).extract().response();
        Response postQuantityDecrease =
                given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri().cookie(cookies).contentType(ContentType.JSON).body(quantityDecrease).when().post(path).then().log().status().statusCode(200).extract().response();

        String postaddToCartResult = postaddToCart.getBody().asString();
        String postQuantityIncreaseResult = postaddToCart.getBody().asString();
        String postQuantityDecreaseResult = postaddToCart.getBody().asString();

        log.info("postItemActionsInCart - END");

    }

    @Test(priority = 2)
    public void postAddItemToCart() {
        log.info("postAddItemToCart - START");
        String cookies = this.preCondition();
        File file = new File("src/test/resources/json/addProductsToCart.json");
        Response postAddItemToCart =
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .cookie(cookies)
                .contentType(ContentType.JSON).body(file)
                .when().post("gateway/api/graphql/cart")
                .then().log().status()
                .statusCode(200).extract().response();

        String result = postAddItemToCart.getBody().asString();
        log.info("result: " + result);

        log.info("postAddItemToCart - END");
    }

    @Test(priority = 3)
    public void postCartItemQuantityIncrease() {
        log.info("postCartItemQuantityIncrease - START");
        String cookies = this.preCondition();
        File addToCart = new File("src/test/resources/json/addProductsToCart.json");
        File quantityIncrease = new File("src/test/resources/json/quantityIncrease.json");

        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri().cookie(cookies).contentType(ContentType.JSON).body(addToCart).when().post("gateway/api/graphql/cart").then().log().status().statusCode(200);

        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri().cookie(cookies).contentType(ContentType.JSON).body(quantityIncrease).when().post("gateway/api/graphql/cart").then().log().status().statusCode(200);
        log.info("postCartItemQuantityIncrease - END");
    }

}