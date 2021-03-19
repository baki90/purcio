package purcio.purcio.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import purcio.purcio.order.domain.Order;

import javax.persistence.*;

/**
 * 상품에 대한 댓글
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_order_id")
    private Order productOrder; // 주문한 건

    private String title; // 댓글 제목
    private String content; // 댓글 이름
    private String photoUrl; // 사진 주소
}
