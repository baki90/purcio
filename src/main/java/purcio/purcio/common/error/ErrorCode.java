package purcio.purcio.common.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    NOT_BLANK("ERROR_CODE_0001","필수값이 누락되었습니다"),
    NO_ARG("ERROR_CODE_0002", "유효하지 않은 값입니다."),
    Illegal_ARG("ERROR_CODE_0003", "작업 수행이 불가합니다.")
    ;

    private String code;
    private String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}