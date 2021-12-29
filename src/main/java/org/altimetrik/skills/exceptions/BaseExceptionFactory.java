package org.altimetrik.skills.exceptions;

import javax.validation.ConstraintViolationException;
import lombok.Builder;
import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Getter
@Builder
@Component
@Schema(description = "BaseExceptionResponseDto")
public class BaseExceptionFactory {

  public static final String TRACE_ID = "traceId";

  public BaseExceptionResponseDto create(BaseException exception) {
    return BaseExceptionResponseDto.builder()
        .traceId(exception.getTraceId())
        .message(exception.getMessage())
        .build();
  }

  public BaseExceptionResponseDto create(RuntimeException exception) {
    return BaseExceptionResponseDto.builder()
        .traceId(MDC.get(TRACE_ID))
        .message(exception.getMessage())
        .build();
  }

  public RequestValidationExceptionResponseDto create(ConstraintViolationException exception) {
    return RequestValidationExceptionResponseDto.builder()
        .traceId(MDC.get(TRACE_ID))
        .message(exception.getMessage())
        .invalidFields(exception.getConstraintViolations().stream()
            .map(e -> InvalidField.of(e.getPropertyPath().toString(), e.getInvalidValue(),
                e.getMessage()))
            .toList())
        .build();
  }

}
