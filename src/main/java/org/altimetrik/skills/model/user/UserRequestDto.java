package org.altimetrik.skills.model.user;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
@Builder
public class UserRequestDto {

  @Email
  @NotBlank
  @Schema(example = "test@mail.com")
  private String privateEmail;
  @Email
  @Schema(example = "test@mail.com")
  private String companyEmail;
  @NotBlank
  @Schema(example = "Jan")
  private String name;
  @NotBlank
  @Schema(example = "Nowak")
  private String surname;
  @NotNull
  private UserRole role;
  private Optional<List<@Valid UserSkillRequestDto>> skillDocuments;
}
