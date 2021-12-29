package org.altimetrik.skills.exceptions;

import io.quarkus.security.UnauthorizedException;
import javax.annotation.Priority;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
@Priority(1)
public class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException> {

  @Override
  public Response toResponse(UnauthorizedException e) {
    log.error("Caught UnauthorizedException. Error message : {}", e.getMessage(), e);
    return Response.status(Response.Status.FORBIDDEN)
        .entity(new BaseExceptionFactory().create(e))
        .build();
  }
}