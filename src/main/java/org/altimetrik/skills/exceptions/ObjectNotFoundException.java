package org.altimetrik.skills.exceptions;

import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Schema(description = "ObjectNotFoundException")
public class ObjectNotFoundException extends BaseException {

  private static final String MESSAGE = "%s with id : %s not found";

  public ObjectNotFoundException(String entityName, String id) {
    super(String.format(MESSAGE, entityName, id));
  }

}