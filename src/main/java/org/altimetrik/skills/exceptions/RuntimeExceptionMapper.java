package org.altimetrik.skills.exceptions;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
@RequiredArgsConstructor
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

  private final BaseExceptionFactory baseExceptionFactory;

  @Override
  public Response toResponse(RuntimeException e) {
    log.error("Caught RuntimeException. Error message : {}", e.getMessage(), e);
    return Response.status(INTERNAL_SERVER_ERROR).entity(baseExceptionFactory.create(e)).build();
  }
}