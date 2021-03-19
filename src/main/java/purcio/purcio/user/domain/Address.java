package purcio.purcio.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * 주소 VO
 */
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    private String city;
    private String district;
    private String street;
    private String zipcode;

    @Builder
    public Address(String city, String district, String street, String zipcode) {
        this.city = city;
        this.district = district;
        this.street = street;
        this.zipcode = zipcode;
    }
}
