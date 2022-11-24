package me.hero.minicommerce.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CommonErrorHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Void
      > onIllegalArgumentException(IllegalArgumentException exception) {
    log.warn("[{}] : 잘못된 인자를 사용하였습니다.", exception.getClass());
    return ResponseEntity.badRequest().body(null);
  }

}
