package org.altimetrik.skills.tag;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.altimetrik.skills.tag.TagRequestDtoFaker.TEST_TAG_2_REQUEST_DTO;
import static org.altimetrik.skills.tag.TagRequestDtoFaker.TEST_TAG_REQUEST_DTO;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.NOT_FOUND;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.NO_CONTENT;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.OK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import java.util.List;
import org.altimetrik.skills.ApplicationBaseFT;
import org.altimetrik.skills.controller.TagController;
import org.altimetrik.skills.model.tag.TagResponseDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestHTTPEndpoint(TagController.class)
class TagControllerTest extends ApplicationBaseFT {

  @Test
  @TestSecurity(user = "test")
  void createAndFetchNewTag() {
    TagResponseDto tag = given().contentType(JSON)
        .body(TEST_TAG_REQUEST_DTO)
        .when().post()
        .thenReturn().as(TagResponseDto.class);

    TagResponseDto responseDto =
        given().contentType(JSON)
            .when().get("{id}", tag.getId())
            .thenReturn().as(TagResponseDto.class);

    assertAll(
        () -> assertEquals(TEST_TAG_REQUEST_DTO.getTagName(), responseDto.getName())
    );
  }

  @Test
  @TestSecurity(user = "test")
  void getAllTags() {
    given().contentType(JSON)
        .body(TEST_TAG_REQUEST_DTO)
        .when().post()
        .then().statusCode(OK);

    given().contentType(JSON)
        .body(TEST_TAG_2_REQUEST_DTO)
        .when().post()
        .then().statusCode(OK);

    List<TagResponseDto> list = given().contentType(JSON)
        .when().get()
        .then().extract().body()
        .jsonPath().getList(".", TagResponseDto.class);

    assertEquals(2, list.size());
  }

  @Test
  @TestSecurity(user = "test")
  void deleteTag() {
    TagResponseDto tag = given().contentType(JSON)
        .body(TEST_TAG_REQUEST_DTO)
        .when().post()
        .thenReturn().as(TagResponseDto.class);

    given().contentType(JSON)
        .when().delete("{id}", tag.getId())
        .then().statusCode(NO_CONTENT);

    given().contentType(JSON)
        .when().get("{id}", tag.getId())
        .then().statusCode(NOT_FOUND);
  }
}
