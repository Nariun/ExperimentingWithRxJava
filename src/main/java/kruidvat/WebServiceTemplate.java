package kruidvat;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Created by JonathanBrites on 29/11/2016.
 */
public class WebServiceTemplate {
    public ClpeData getData(final ProductEnum productEnum){
        return new ClpeData(generateRandomBigDecimal(), productEnum);
    }

    private BigDecimal generateRandomBigDecimal(){
        BigDecimal bigDecimal = new BigDecimal(new Random().nextFloat(), new MathContext(2, RoundingMode.HALF_UP));
        if(bigDecimal.floatValue() < 0.10){
            throw new RuntimeException("The data stream went horribly wrong");
        }
        return bigDecimal;
    }
}
