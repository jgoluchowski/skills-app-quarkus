package org.altimetrik.skills.model.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
@Builder
public class TagRequestDto {

  @NotBlank
  @Schema(example = "Tag")
  private String tagName;

  @JsonIgnore
  private String hidden;

}
