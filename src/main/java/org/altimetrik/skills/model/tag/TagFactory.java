package org.altimetrik.skills.model.tag;

import org.altimetrik.skills.model.skill.SkillRequestDto;
import org.springframework.stereotype.Component;

@Component
public class TagFactory {

  public Tag create(TagRequestDto tagRequestDto) {
    return Tag.builder()
        .name(tagRequestDto.getTagName())
        .build();
  }

  public Tag create(SkillRequestDto tagRequestDto) {
    return Tag.builder()
        .name(tagRequestDto.getName())
        .build();
  }

  public TagResponseDto create(Tag tag) {
    return TagResponseDto.builder()
        .id(tag.getId().toString())
        .name(tag.getName())
        .build();
  }

}
