package purcio.purcio.product.domain;

/**
 * 상품에 대한 카테고리
 */
public enum Category {
    YARN("실"),
    DESIGN("도안"),
    NEEDLE("바늘"),
    ADDITIONAL("부자재")
    ;

    private String krName;
    Category(String krName) {this.krName = krName;}
    public String getKrName() {return krName;}
}
