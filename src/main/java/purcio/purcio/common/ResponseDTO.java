package purcio.purcio.common;

import lombok.Builder;
import lombok.Data;

@Data
public class ResponseDTO<T> {

    private String message; // 상세 메세지
    private T content; // 반환할 내용

    @Builder
    public ResponseDTO(String message, T content) {
        this.message = message;
        this.content = content;
    }
}
