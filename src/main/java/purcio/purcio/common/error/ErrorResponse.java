package purcio.purcio.common.error;


import lombok.Data;

@Data
public class ErrorResponse {

    private String code;

    private String description;

    private String detail;

    public ErrorResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public ErrorResponse(String code, String description, String detail) {
        this.code = code;
        this.description = description;
        this.detail = detail;
    }
}
