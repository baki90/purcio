package purcio.purcio.common.error;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    // valid 유효성 검사를 통과하지 못한 것을 관리하는 핸들러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        log.warn("[MethodArgumentNotValidException 발생] url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
        ErrorResponse errorResponse = makeErrorResponse(e.getBindingResult());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // findBy를 하였는데, 특정 값이 조회되지 않았을 때 반환하는 에러
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> noSuchElementException(NoSuchElementException e, HttpServletRequest request){
        log.warn("[NoSuchElementException 발생] url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());

        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.NO_ARG.getCode(), ErrorCode.NO_ARG.getDescription());

        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // 처리가 불가한 값이 넘어왔을 때
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException e, HttpServletRequest request){
        log.warn("[NoSuchElementException 발생] url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());

        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.Illegal_ARG.getCode(),
                ErrorCode.Illegal_ARG.getDescription(), e.getMessage());

        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse makeErrorResponse(BindingResult bindingResult){
        String code = "";
        String description = "";
        String detail = "";

        if(bindingResult.hasErrors()){
            detail = bindingResult.getFieldError().getDefaultMessage();
            String bindResultCode = bindingResult.getFieldError().getCode();

            switch (bindResultCode){
                case "NotBlank":
                    code = ErrorCode.NOT_BLANK.getCode();
                    description = ErrorCode.NOT_BLANK.getDescription();
                    break;
            }
        }

        return new ErrorResponse(code, description, detail);
    }
}