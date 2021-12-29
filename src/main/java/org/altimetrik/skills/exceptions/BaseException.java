package org.altimetrik.skills.exceptions;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.slf4j.MDC;

@Getter
@Setter
public abstract class BaseException extends RuntimeException {

  private static final String TRACE_ID = "traceId";

  @NonNull
  private final String traceId;

  protected BaseException(String reason) {
    super(reason);
    this.traceId = MDC.get(TRACE_ID);
  }

  protected BaseException(String reason, Throwable throwable) {
    super(reason, throwable);
    this.traceId = MDC.get(TRACE_ID);
  }

}
