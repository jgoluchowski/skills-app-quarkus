package org.altimetrik.skills.exceptions;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
@RequiredArgsConstructor
public class ObjectNotFoundExceptionMapper implements ExceptionMapper<ObjectNotFoundException> {

  private final BaseExceptionFactory baseExceptionFactory;

  @Override
  public Response toResponse(ObjectNotFoundException e) {
    log.error("Caught RuntimeException. Error message : {}", e.getMessage(), e);
    return Response.status(NOT_FOUND).entity(baseExceptionFactory.create(e)).build();
  }
}