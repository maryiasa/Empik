package com.empik.apiTests;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

@Log4j2
public class ApiTests {

    private static final Logger log = LogManager.getLogger(ApiTests.class);

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://www.empik.com";
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

}