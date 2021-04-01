package purcio.purcio.user.dto.user;

import lombok.Data;
import purcio.purcio.user.domain.Address;

@Data
public class UserUpdateReqDTO {

    private String name;
    private String password;
    private String picture;

    private String nickName; // 닉네임
    private String phoneNumber; // 휴대폰 번호
    private Integer age; // 나이
    private String sex; // 성별

    private Address address; // 거주지
}
