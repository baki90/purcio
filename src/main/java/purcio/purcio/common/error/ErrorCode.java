package purcio.purcio.common.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    NOT_BLANK("ERROR_CODE_0001","필수값이 누락되었습니다"),
    NOT_FOUND_ELEMENT("ERROR_CODE_0002", "해당하는 모델을 찾을 수 없습니다."),
    ILLEGAL_ARG("ERROR_CODE_0003", "유효하지 않은 값입니다..")
    ;

    private String code;
    private String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
