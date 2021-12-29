package org.altimetrik.skills.exceptions;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
@RequiredArgsConstructor
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

  private final BaseExceptionFactory baseExceptionFactory;

  @Override
  public Response toResponse(ConstraintViolationException e) {
    log.error("Caught ConstraintViolationException. Error message : {}", e.getMessage(), e);
    return Response.status(BAD_REQUEST).entity(baseExceptionFactory.create(e)).build();
  }
}