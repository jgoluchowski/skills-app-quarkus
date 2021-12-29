package org.altimetrik.skills.model.user;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponseDto {

  @NonNull
  private String id;
  @NonNull
  private String privateEmail;
  private String companyEmail;
  @NonNull
  private String name;
  @NonNull
  private String surname;
  @NonNull
  private UserRole role;
  private List<UserSkillResponseDto> skillDocuments;
}
