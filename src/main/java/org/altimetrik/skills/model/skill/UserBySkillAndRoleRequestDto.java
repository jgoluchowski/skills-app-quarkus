package org.altimetrik.skills.model.skill;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.altimetrik.skills.model.user.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBySkillAndRoleRequestDto {

  @NotEmpty
  private List<SkillFilter> skillFilters;
  @NotEmpty
  private List<UserRole> roles;
}
