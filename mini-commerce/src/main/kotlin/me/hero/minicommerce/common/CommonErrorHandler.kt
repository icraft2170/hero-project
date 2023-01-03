package me.hero.minicommerce.common

import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
@Slf4j
class CommonErrorHandler {
    @ExceptionHandler(IllegalArgumentException::class)
    fun onIllegalArgumentException(exception: IllegalArgumentException): ResponseEntity<Unit> {
        log.warn("[{}] : 잘못된 인자를 사용하였습니다.", exception.javaClass)
        return ResponseEntity.badRequest()
            .body(Unit)
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
}