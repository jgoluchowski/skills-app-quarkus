package org.altimetrik.skills.user;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.altimetrik.skills.user.UserRequestDtoFaker.TEST_USER_REQUEST_DTO;
import static org.altimetrik.skills.user.UserRequestDtoFaker.TEST_USER_REQUEST_DTO_2;
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
import org.altimetrik.skills.controller.UserController;
import org.altimetrik.skills.model.user.UserResponseDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestHTTPEndpoint(UserController.class)
class UserControllerTest extends ApplicationBaseFT {

  @Test
  @TestSecurity(user = "test")
  void createAndFetchNewUser() {
    UserResponseDto user = given().contentType(JSON)
        .body(TEST_USER_REQUEST_DTO)
        .when().post()
        .thenReturn().as(UserResponseDto.class);

    UserResponseDto responseDto =
        given().contentType(JSON)
            .when().get("{id}", user.getId())
            .thenReturn().as(UserResponseDto.class);

    assertAll(
        () -> assertEquals(responseDto.getCompanyEmail(), TEST_USER_REQUEST_DTO.getCompanyEmail()),
        () -> assertEquals(responseDto.getPrivateEmail(), TEST_USER_REQUEST_DTO.getPrivateEmail()),
        () -> assertEquals(responseDto.getName(), TEST_USER_REQUEST_DTO.getName()),
        () -> assertEquals(responseDto.getSurname(), TEST_USER_REQUEST_DTO.getSurname()),
        () -> assertEquals(responseDto.getRole(), TEST_USER_REQUEST_DTO.getRole())
    );
  }

  @Test
  @TestSecurity(user = "test")
  void getAllUsers() {
    given().contentType(JSON)
        .body(TEST_USER_REQUEST_DTO)
        .when().post()
        .then().statusCode(OK);

    given().contentType(JSON)
        .body(TEST_USER_REQUEST_DTO_2)
        .when().post()
        .then().statusCode(OK);

    List<UserResponseDto> list = given().contentType(JSON)
        .when().get()
        .then().extract().body()
        .jsonPath().getList(".", UserResponseDto.class);

    assertEquals(2, list.size());
  }

  @Test
  @TestSecurity(user = "test")
  void deleteUser() {
    UserResponseDto user = given().contentType(JSON)
        .body(TEST_USER_REQUEST_DTO)
        .when().post()
        .thenReturn().as(UserResponseDto.class);

    given().contentType(JSON)
        .when().delete("{id}", user.getId())
        .then().statusCode(NO_CONTENT);

    given().contentType(JSON)
        .when().get("{id}", user.getId())
        .then().statusCode(NOT_FOUND);
  }
}
