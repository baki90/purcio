package purcio.purcio.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import purcio.purcio.common.model.BaseEntity;
import purcio.purcio.order.domain.Cart;
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
    private String password;
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

    @OneToOne(mappedBy = "user")
    private Cart cart;

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * 유저가 장바구니를 조회한다.
     * 만일 장바구니가 없으면, 생성한다.
     * @return Cart(장바구니)
     */
    public Cart getCart() {
        if(this.cart == null) {
           this.cart = Cart.createCart(this);
        }
        return this.cart;
    }

    /**
     * 유저를 생성합니다.
     * @param name 유저 이름
     * @param email 유저 이메일 (not null)
     * @param picture 프로필 사진
     * @param nickName 닉네임
     * @param phoneNumber 휴대폰번호
     * @param age 나이
     * @param sex 성별
     * @param address 주소
     * @return 생성된 유저의 객체
     */
    // TODO: set 함수를 통해 blank 값에 대한 처리 필요
    public static User createUser(String name, String password, String email, String picture, String nickName, String phoneNumber, Integer age, String sex, Address address) {
        User user = new User();
        user.name = name;
        user.password = password;
        user.email = email;
        user.picture = picture;
        user.nickName = nickName;
        user.phoneNumber = phoneNumber;
        user.age = age;
        user.sex = sex;
        user.address = address;

        return user;
    }

    /**
     * 유저를 업데이트한다.
     * 입력받은 값이 null이 아닐 경우에만 업데이트한다.
     * @param name
     * @param password
     * @param picture
     * @param nickName
     * @param phoneNumber
     * @param age
     * @param sex
     * @param address
     */
    public void updateUser(String name, String password, String picture, String nickName, String phoneNumber, Integer age, String sex, Address address) {
        super.update();
        if(name != null) this.name = name;
        if(password != null) this.password = password;
        if(picture != null) this.picture = picture;
        if(nickName != null) this.nickName = nickName;
        if(phoneNumber != null) this.phoneNumber = phoneNumber;
        if(age != null) this.age = age;
        if(sex != null) this.sex = sex;
        if(address != null) this.address.updateAddress(address);
    }


}
