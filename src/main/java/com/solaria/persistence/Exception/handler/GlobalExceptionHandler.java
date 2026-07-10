package com.solaria.persistence.Exception.handler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.solaria.persistence.Exception.BusinessException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Classe responsável por centralizar tratamento de exceções
 * Diferentes handlers foram implementados visando cobrir escopos de: Banco de Dados,
 * Regras de negócio, Jsons, BEAN validations e erros inesperados.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ProblemDetailFactory factory;

    public GlobalExceptionHandler(ProblemDetailFactory factory) {
        this.factory = factory;
    }

    // Handler responsável por BusinessException e todas as subclasses.

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ProblemDetail> handleBusinessException(BusinessException ex,
                                                                 HttpServletRequest request) {
        ProblemDetail body = factory.create(
                ex.getStatus(),
                ex.getMessage(),
                ex.getErrorCode(),
                ex.getProperties(),
                request);
        return ResponseEntity.status(ex.getStatus()).body(body);
    }

    // Handler responsável por exceções vindas do banco de dados.

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ProblemDetail> handleDataIntegrity(DataIntegrityViolationException ex,
                                                             HttpServletRequest request) {
        ProblemDetail body = factory.create(HttpStatus.CONFLICT,
                "A operação viola uma restrição do banco.",
                "DATA_INTEGRITY_VIOLATION",
                null,
                request);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<ProblemDetail> handleOptimisticLock(OptimisticLockingFailureException ex,
                                                              HttpServletRequest request) {
        ProblemDetail body = factory.create(HttpStatus.CONFLICT,
                "O registro foi modificado por outra operação simultânea. Recarregue e tente novamente.",
                "CONCURRENT_MODIFICATION",
                null,
                request);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleEntityNotFound(EntityNotFoundException ex,
                                                              HttpServletRequest request) {
        ProblemDetail body = factory.create(HttpStatus.NOT_FOUND,
                "Registro não encontrado.",
                "RESOURCE_NOT_FOUND",
                null,
                request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    // Handler para erros inesperados

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleUnexpected(Exception ex, HttpServletRequest request) {
        ProblemDetail body = factory.create(HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro interno inesperado. Se persistir, contate o suporte.",
                "INTERNAL_ERROR",
                null,
                request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    // Sobrescrita de handler responsável por BEAN validation / corpo da requisição.

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        List<Map<String, Object>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> {
                    Map<String, Object> entry = new LinkedHashMap<>();
                    entry.put("field", fe.getField());
                    entry.put("message", fe.getDefaultMessage());
                    return entry;
                })
                .toList();
        ProblemDetail body = factory.create(HttpStatus.BAD_REQUEST,
                "Erro de validação nos campos da requisição.",
                "VALIDATION_ERROR",
                Map.of("errors", errors),
                servletRequest(request));
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Sobrescrita de handler responsável por BEAN validation / parâmetros dos métodos.

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex,
                                                                            HttpHeaders headers,
                                                                            HttpStatusCode status,
                                                                            WebRequest request) {
        List<Map<String, Object>> errors = ex.getAllErrors()
                .stream()
                .map(err -> {
                    Map<String, Object> entry = new LinkedHashMap<>();
                    entry.put("message", err.getDefaultMessage());
                    return entry;
                })
                .toList();
        ProblemDetail body = factory.create(HttpStatus.BAD_REQUEST,
                "Erro de validação nos parâmetros da requisição.",
                "VALIDATION_ERROR",
                Map.of("errors", errors), servletRequest(request));
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Sobrescrita de Handler responsável por JSONS inválidos.

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ProblemDetail body = factory.create(HttpStatus.BAD_REQUEST,
                "Corpo da requisição malformado ou ilegível.",
                "MALFORMED_REQUEST",
                null,
                servletRequest(request));
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private HttpServletRequest servletRequest(WebRequest request) {
        return (request instanceof ServletWebRequest swr) ? swr.getRequest() : null;
    }
}
