package org.altimetrik.skills.skill;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.altimetrik.skills.skill.SkillRequestDtoFaker.TEST_SKILL_2_REQUEST_DTO;
import static org.altimetrik.skills.skill.SkillRequestDtoFaker.TEST_SKILL_REQUEST_DTO;
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
import org.altimetrik.skills.controller.SkillController;
import org.altimetrik.skills.model.skill.SkillResponseDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestHTTPEndpoint(SkillController.class)
class SkillControllerTest extends ApplicationBaseFT {

  @Test
  @TestSecurity(user = "test")
  void createAndFetchNewSkill() {
    SkillResponseDto skill = given().contentType(JSON)
        .body(TEST_SKILL_REQUEST_DTO)
        .when().post()
        .thenReturn().as(SkillResponseDto.class);

    SkillResponseDto responseDto =
        given().contentType(JSON)
            .when().get("{id}", skill.getId())
            .thenReturn().as(SkillResponseDto.class);

    assertAll(
        () -> assertEquals(TEST_SKILL_REQUEST_DTO.getName(), responseDto.getName())
    );
  }

  @Test
  @TestSecurity(user = "test")
  void getAllSkills() {
    given().contentType(JSON)
        .body(TEST_SKILL_REQUEST_DTO)
        .when().post()
        .then().statusCode(OK);

    given().contentType(JSON)
        .body(TEST_SKILL_2_REQUEST_DTO)
        .when().post()
        .then().statusCode(OK);

    List<SkillResponseDto> list = given().contentType(JSON)
        .when().get()
        .then().extract().body()
        .jsonPath().getList(".", SkillResponseDto.class);

    assertEquals(2, list.size());
  }

  @Test
  @TestSecurity(user = "test")
  void deleteSkill() {
    SkillResponseDto skill = given().contentType(JSON)
        .body(TEST_SKILL_REQUEST_DTO)
        .when().post()
        .thenReturn().as(SkillResponseDto.class);

    given().contentType(JSON)
        .when().delete("{id}", skill.getId())
        .then().statusCode(NO_CONTENT);

    given().contentType(JSON)
        .when().get("{id}", skill.getId())
        .then().statusCode(NOT_FOUND);
  }
}
