package org.altimetrik.skills.exceptions;

import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@SuperBuilder
@Schema(description = "RequestValidationExceptionResponseDto")
public class RequestValidationExceptionResponseDto extends BaseExceptionResponseDto {

  @Schema(required = true)
  private final List<InvalidField> invalidFields;

}
