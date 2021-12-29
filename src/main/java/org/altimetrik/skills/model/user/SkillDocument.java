package org.altimetrik.skills.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkillDocument {

  @NonNull
  private String skillId;
  @NonNull
  private String skillName;
  @NonNull
  private Hype hype;
  @NonNull
  private ExperienceLevel experienceLevel;
}
