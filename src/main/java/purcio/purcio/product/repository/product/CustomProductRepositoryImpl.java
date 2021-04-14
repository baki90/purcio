package purcio.purcio.product.repository.product;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import purcio.purcio.product.domain.Category;
import purcio.purcio.product.domain.QProduct;
import purcio.purcio.product.dto.product.ProductRetrieveConditionDTO;
import purcio.purcio.product.dto.product.ProductRetrieveResponseDTO;
import purcio.purcio.product.repository.ShopRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomProductRepositoryImpl implements CustomProductRepository {
    private final JPAQueryFactory queryFactory;

    public CustomProductRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 동적 쿼리를 바탕으로 조회 조건에 따라 상품을 반환한다.
     * @param condition 조회 조건 & 정렬 조건
     * @param pageable 페이징 정보
     * @return 페이징된 상품 정보
     */
    @Override
    public Page<ProductRetrieveResponseDTO> retrieve(ProductRetrieveConditionDTO condition, Pageable pageable){
        QProduct product = QProduct.product;

        QueryResults<ProductRetrieveResponseDTO> products = queryFactory
                .select(Projections.constructor(ProductRetrieveResponseDTO.class,
                        product.name, product.category, product.price, product.heartProducts.size()))
                .from(product)
                .where(productCategoryEq(condition.getCategory())) // 카테고리별 조회
                .where(productNameContains(condition.getName())) // 이름 포함별 조회
                .where(productServerEq(condition.getShopId())) // 뜨개샵 별 조회
                .where(productMaxPriceLoe(condition.getMaxPrice()))// 가격 범위 조회
                .where(productMinPriceGoe(condition.getMinPrice()))
                .orderBy(productOrderSpecifier(condition.getProductOrderCriteria())) // 조회 기준으로 검색
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ProductRetrieveResponseDTO> data = products.getResults();
        long total = products.getTotal();

        return new PageImpl<ProductRetrieveResponseDTO>(data, pageable, total);
    }

    BooleanExpression productNameContains(String name){
        return name != null ? QProduct.product.name.contains(name) : null;
    }

    BooleanExpression productCategoryEq(Category category){
        return category != null ? QProduct.product.category.eq(category) : null;
    }

    BooleanExpression productServerEq(Long shopId){
        return shopId != null ? QProduct.product.shop.id.eq(shopId) : null;
    }

    BooleanExpression productMinPriceGoe(Integer minPrice){
        return minPrice != null? QProduct.product.price.goe(minPrice) : null;
    }

    BooleanExpression productMaxPriceLoe(Integer maxPrice){
        return maxPrice != null? QProduct.product.price.loe(maxPrice) : null;
    }

    /**
     * 조회 기준에 따라 정렬 기준 expression을 반환한다.
     * @param orderCriteria 조회 기준
     * @return 정렬 기준에 따른 조회 expression
     */
    OrderSpecifier productOrderSpecifier(ProductOrderCriteria orderCriteria){
        if(orderCriteria == null) return QProduct.product.createdAt.asc();

        if(orderCriteria.equals(orderCriteria.HEART_ASC)) {
            return QProduct.product.heartProducts.size().desc();
        }

        return QProduct.product.createdAt.asc();
    }
}
