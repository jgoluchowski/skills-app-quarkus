package org.altimetrik.skills.skill;

import static org.altimetrik.skills.skill.SkillFaker.TEST_SKILL;
import static org.altimetrik.skills.skill.SkillFaker.TEST_SKILL_2;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.quarkus.test.junit.QuarkusTest;
import java.util.List;
import org.altimetrik.skills.ApplicationBaseFT;
import org.altimetrik.skills.exceptions.ObjectNotFoundException;
import org.altimetrik.skills.model.skill.Skill;
import org.altimetrik.skills.service.SkillService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@QuarkusTest
class SkillServiceTest extends ApplicationBaseFT {

  @Autowired
  SkillService skillService;

  @Test
  void saveUserAndGetItById() {
    Skill skill = skillService.saveSkill(TEST_SKILL).await().indefinitely();

    Skill skillById = getSkill(skill.getId());

    assertAll(
        () -> assertEquals(skillById.getName(), TEST_SKILL.getName())
    );
  }

  @Test
  void saveMultipleUsersAndGetList() {
    skillService.saveSkill(TEST_SKILL).await().indefinitely();
    skillService.saveSkill(TEST_SKILL_2).await().indefinitely();

    List<Skill> skills = skillService.getAllSkills().collect().asList().await().indefinitely();

    assertEquals(2, skills.size());
  }

  @Test
  void saveAndUpdateUser() {
    Skill skill = skillService.saveSkill(TEST_SKILL).await().indefinitely();
    Skill updatedSkill = skillService.updateSkill(skill.getId(), TEST_SKILL_2).await()
        .indefinitely();

    assertAll(
        () -> assertEquals(updatedSkill.getName(), TEST_SKILL_2.getName())
    );
  }

  @Test
  void saveAndDeleteUser() {
    ObjectId id = skillService.saveSkill(TEST_SKILL).await().indefinitely().getId();

    skillService.deleteSkill(id).await().indefinitely();

    assertAll(
        () -> assertEquals(0,
            skillService.getAllSkills().collect().asList().await().indefinitely().size()),
        () -> assertThrows(ObjectNotFoundException.class, () -> getSkill(id))
    );
  }

  private Skill getSkill(ObjectId id) {
    return skillService.getSkillById(id).await().indefinitely();
  }

}
