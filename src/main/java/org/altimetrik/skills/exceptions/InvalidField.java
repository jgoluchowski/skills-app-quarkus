package org.altimetrik.skills.exceptions;

import java.io.Serializable;
import lombok.Data;
import lombok.NonNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Schema(description = "InvalidField")
public class InvalidField implements Serializable {

  @NonNull
  @Schema(required = true)
  private final String field;

  @Schema()
  private final transient Object rejectedValue;

  @Schema()
  private final String validationMessage;

  public static InvalidField of(String field, Object rejectedValue, String validationMessage) {
    return new InvalidField(field, rejectedValue, validationMessage);
  }
}
