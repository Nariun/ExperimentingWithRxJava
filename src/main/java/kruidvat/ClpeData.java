package kruidvat;

import java.math.BigDecimal;

/**
 * Created by jonathanbrites on 29/11/2016.
 */
public class ClpeData {
    BigDecimal promotion;
    ProductEnum product;

    public ClpeData(BigDecimal promotion, ProductEnum product) {
        this.promotion = promotion;
        this.product = product;
    }

    public BigDecimal getPromotion() {
        return promotion;
    }

    public void setPromotion(BigDecimal promotion) {
        this.promotion = promotion;
    }

    public ProductEnum getProduct() {
        return product;
    }

    public void setProduct(ProductEnum product) {
        this.product = product;
    }
}
