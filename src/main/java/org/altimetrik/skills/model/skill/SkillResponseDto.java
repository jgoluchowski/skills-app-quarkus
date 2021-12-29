package org.altimetrik.skills.model.skill;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SkillResponseDto {

  @NonNull
  private String id;
  @NonNull
  private String name;
  private String tagId;
}
