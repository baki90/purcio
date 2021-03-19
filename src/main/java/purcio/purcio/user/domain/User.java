package purcio.purcio.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * User 도메인
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;

    private String name;

    @Column(nullable = false)
    private String email;

    private String picture;

    private String nickName; // 닉네임
    private String phoneNumber; // 휴대폰 번호
    private Integer age; // 나이
    private String sex; // 성별

    private Address address; // 거주지

    @Builder
    public User(String name, String email, String picture, String nickName, String phoneNumber, Integer age, String sex, Address address) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.sex = sex;
        this.address = address;
    }

    public void updateUser(String name, String email, String picture, String nickName, String phoneNumber, Integer age, String sex, Address address) {
        if(this.name != null) this.name = name;
        if(this.email != null) this.email = email;
        if(this.picture != null) this.picture = picture;
        if(this.nickName != null) this.nickName = nickName;
        if(this.phoneNumber != null) this.phoneNumber = phoneNumber;
        if(this.age != null) this.age = age;
        if(this.sex != null) this.sex = sex;
        if(this.address != null) this.address = address;
    }


}
