package org.altimetrik.skills.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@SuperBuilder
@Schema(description = "BaseExceptionResponseDto")
public class BaseExceptionResponseDto {

  private final String traceId;

  private final String message;

}
