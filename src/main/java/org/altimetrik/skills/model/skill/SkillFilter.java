package org.altimetrik.skills.model.skill;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.altimetrik.skills.model.user.ExperienceLevel;
import org.altimetrik.skills.model.user.Hype;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillFilter {

  private String skillId;
  private List<ExperienceLevel> experienceLevels;
  private List<Hype> hypes;
}
