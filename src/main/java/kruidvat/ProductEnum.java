package kruidvat;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by jonathanbrites on 29/11/2016.
 */
public enum ProductEnum {
    PRODUCT_ONE("Shampoo", new BigDecimal(14.99D, new MathContext(4, RoundingMode.HALF_UP))), PRODUCT_TWO("Candy", new BigDecimal(29.99D, new MathContext(4, RoundingMode.HALF_UP)));

    String value;
    BigDecimal price;

    ProductEnum(String value, BigDecimal bigDecimal){
        this.value = value;
        this.price = bigDecimal;
    }


}
