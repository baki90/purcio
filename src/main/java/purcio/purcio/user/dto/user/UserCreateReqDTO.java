package purcio.purcio.user.dto.user;

import lombok.Data;
import purcio.purcio.user.domain.Address;

import javax.validation.constraints.NotBlank;

@Data
public class UserCreateReqDTO {

    private String name;
    private String password;
    private String email;
    private String picture;

    private String nickName; // 닉네임
    private String phoneNumber; // 휴대폰 번호
    private Integer age; // 나이
    private String sex; // 성별

    private Address address; // 거주지

}
