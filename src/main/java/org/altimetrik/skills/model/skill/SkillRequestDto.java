package org.altimetrik.skills.model.skill;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
@Builder
public class SkillRequestDto {

  @NotBlank
  @Schema(example = "AWS")
  private String name;
  private String tagId;
}
