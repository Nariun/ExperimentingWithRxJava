package kruidvat;

import rx.Observable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;

/**
 * Created by jonathanbrites on 29/11/2016.
 */
public class ClpeApp {
    public static void main(String[] args) {
        ClpeMock clpeMock = new ClpeMock();
        Observable<ClpeData> promotionValues = clpeMock.getPromotionValues(false);

        promotionValues.subscribe(
                ClpeApp::updateProduct,
                ClpeApp::printStackTrace,
                ClpeApp::endNormally);



        System.out.println("Continuing my code with promotion values handled");
        System.out.println("I have a lot of stuff that needs to be done");
    }

    private static void updateProduct(final ClpeData clpeData) {
        BigDecimal multiplication = clpeData.getProduct().price.multiply(clpeData.getPromotion());
        BigDecimal discount = clpeData.getProduct().price.subtract(multiplication);
        System.out.println(clpeData.getProduct().value + " updated with price value: "
                + multiplication.toString() + ", discount given: " + discount.toString());
    }

    private static void printStackTrace(final Throwable throwable){
        throwable.printStackTrace(System.out);
    }

    private static void endNormally(){
        System.out.println("Ended normally");
    }
}
