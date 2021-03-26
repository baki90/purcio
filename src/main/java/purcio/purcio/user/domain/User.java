package purcio.purcio.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import purcio.purcio.common.model.BaseEntity;
import purcio.purcio.order.domain.Order;
import purcio.purcio.order.domain.OrderProduct;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * User 도메인
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String picture;

    @Column(name="nick_name" , unique=true)
    private String nickName; // 닉네임
    private String phoneNumber; // 휴대폰 번호
    private Integer age; // 나이
    private String sex; // 성별

    @Embedded
    private Address address; // 거주지

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

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
