package org.altimetrik.skills.model.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserSkillResponseDto {

  @NotEmpty
  private String skillName;
  @NotNull
  private Hype hype;
  @NotNull
  private ExperienceLevel experienceLevel;
}
