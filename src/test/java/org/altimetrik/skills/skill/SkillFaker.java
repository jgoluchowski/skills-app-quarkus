package org.altimetrik.skills.skill;

import org.altimetrik.skills.model.skill.Skill;

public class SkillFaker {

  public static Skill TEST_SKILL = Skill.builder()
      .name("AWS")
      .build();

  public static Skill TEST_SKILL_2 = Skill.builder()
      .name("AZURE")
      .build();
}
