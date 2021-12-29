package org.altimetrik.skills.skill;

import org.altimetrik.skills.model.skill.SkillRequestDto;

public class SkillRequestDtoFaker {

  public static SkillRequestDto TEST_SKILL_REQUEST_DTO = SkillRequestDto.builder()
      .name("AWS")
      .build();

  public static SkillRequestDto TEST_SKILL_2_REQUEST_DTO = SkillRequestDto.builder()
      .name("AZURE")
      .build();
}
