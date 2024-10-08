package com.empik.apiTests;

import com.empik.utils.CookieHandlerUtil;
import io.qameta.allure.restassured.AllureRestAssured;
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

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;


@Log4j2
public class ApiTests {

    String customUrl;
    String pageCookie;
    int productCount;
    double totalAmount;
    int productCountAfterInc;
    double totalAmountAfterInc;

    private static final Logger log = LogManager.getLogger(ApiTests.class);

    @BeforeMethod
    public void setUpApiTests() {
        RestAssured.baseURI = "https://www.empik.com";
        customUrl = "https://empik.com/bestsellery";
        pageCookie = CookieHandlerUtil.getCookieUsingCookieHandler(customUrl);
    }

    @Test
    public void getPopularCategories() {
        log.info("getPopularCategories - START");
        given()
                .filter(new AllureRestAssured())
                .config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri().queryParam("limit", 5).when().get("ajax/diuna/popularCategories").then().log().status()
                .statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getPopularCategoriesJsonSchema.json"));
        log.info("getPopularCategories - END");
    }

    @Test
    public void getGam() {
        log.info("getGam - START");
        given()
                .filter(new AllureRestAssured())
                .config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri().when().get("ajax2/gam").then().log().status()
                .statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getGamJsonSchema.json"));
        log.info("getGam - END");
    }

    @Test
    public void getChatBot () {
        log.info("getChatBot - START");
        given()
                .filter(new AllureRestAssured())
                .config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri().when().get("https://waw.chat.getzowie.com/api/v1/herochat-plugin/instances/55ba5f792e694813b99f99671946396a/multilingual/livechat")
                .then().log().status()
                .statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getChatBotJsonSchema.json"));
        log.info("getChatBot - END");
    }

    @Test
    public void getLimitsConfiguration() {
        log.info("getLimitsConfiguration - START");
        given()
                .filter(new AllureRestAssured())
                .config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri().queryParam("sourceSystemChannel", "PORTAL_DESKTOP").when().get("gateway/api/carts/limits-configuration").then().log().status()
                .statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getLimitsConfigurationJsonSchema.json"));
        log.info("getLimitsConfiguration - END");
    }

    @Test
    public void putSnapshots() {
        log.info("putSnapshots - START");
        File file = new File("src/test/resources/json/snapshots.json");
        given()
                .filter(new AllureRestAssured())
                .config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri().contentType(ContentType.JSON).body(file).when().put("gateway/api/kotoserver/snapshots").then().log().status()
                .statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/putSnapshotsJsonSchema.json"));
        log.info("putSnapshots - END");
    }

    @Test
    public void postAddItemToCart() {
        log.info("postAddItemToCart - START");
        File file = new File("src/test/resources/json/addProductsToCart.json");
        Response postAddItemToCart =
                given()
                        .filter(new AllureRestAssured())
                        .config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                        .log().uri().cookie(pageCookie).contentType(ContentType.JSON).body(file).when().post("gateway/api/graphql/cart").then().log().status()
                        .statusCode(200).assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/postAddItemToCartJsonSchema.json"))
                        .extract().response();

        productCount = Integer.parseInt(postAddItemToCart.jsonPath().getString("data.addItemsToCart.offers.summary.productCount"));
        totalAmount = Double.parseDouble((postAddItemToCart.jsonPath().getString("data.addItemsToCart.offers.summary.totalAmount")));

        log.info("productCount: " + productCount);
        log.info("totalAmount: " + totalAmount);

        assertTrue(productCount > 0);
        log.info("postAddItemToCart - END");
    }

    @Test
    public void postCartItemQuantityIncrease() {
        log.info("postCartItemQuantityIncrease - START");
        File quantityIncrease = new File("src/test/resources/json/quantityIncrease.json");
        postAddItemToCart();
        Response increaseItemQuantity =
        given()
                .filter(new AllureRestAssured())
                .config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri().cookie(pageCookie).contentType(ContentType.JSON).body(quantityIncrease).when().post("gateway/api/graphql/cart").then().log().status()
                .statusCode(200).assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/postCartItemQuantityIncreaseJsonSchema.json"))
                .extract().response();

        productCountAfterInc = Integer.parseInt(increaseItemQuantity.jsonPath().getString("data.increaseItemQuantity.offers.summary.productCount"));
        totalAmountAfterInc = Double.parseDouble(increaseItemQuantity.jsonPath().getString("data.increaseItemQuantity.offers.summary.totalAmount"));

        log.info("productCountAfterInc: " + productCountAfterInc);
        log.info("totalAmountAfterInc: " + totalAmountAfterInc);

        assertTrue((productCount * 2) == productCountAfterInc);
        assertTrue((totalAmount * 2) == totalAmountAfterInc);

        log.info("postCartItemQuantityIncrease - END");
    }

}