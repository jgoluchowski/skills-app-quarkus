package org.altimetrik.skills.model.skill;

import org.springframework.stereotype.Component;

@Component
public class SkillFactory {

  public Skill create(SkillRequestDto skillRequestDto) {
    return Skill.builder()
        .name(skillRequestDto.getName())
        .tagId(skillRequestDto.getTagId())
        .build();
  }

  public SkillResponseDto create(Skill skill) {
    return SkillResponseDto.builder()
        .id(skill.getId().toString())
        .name(skill.getName())
        .tagId(skill.getTagId())
        .build();
  }

}
