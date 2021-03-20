package purcio.purcio.product.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import purcio.purcio.common.model.BaseEntity;
import purcio.purcio.order.domain.Order;

import javax.persistence.*;

/**
 * 상품에 대한 댓글
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // 주문한 건

    private String title; // 댓글 제목
    private String content; // 댓글 이름
    private String photoUrl; // 사진 주소
}
