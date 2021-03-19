package purcio.purcio.product.dto.shop;

import lombok.Data;

@Data
public class ShopUpdateReqDTO {
    private String name; // 쇼핑몰 이름
    private String picture; // 쇼핑몰 대표 이미지
    private String shopNumber; // 전화번호
}
