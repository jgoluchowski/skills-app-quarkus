package org.altimetrik.skills.model.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSkillRequestDto {

  @NotEmpty
  private String skillId;
  @NotNull
  private Hype hype;
  @NotNull
  private ExperienceLevel experienceLevel;
}
