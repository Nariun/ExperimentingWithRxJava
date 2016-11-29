package kruidvat;

import rx.Observable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Created by jonathanbrites on 29/11/2016.
 */
public class ClpeMock {
    private WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    public Observable<ClpeData> getPromotionValues(boolean shouldCrash){
        return Observable.<ClpeData>create(s -> {
            Runnable r = () -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        for (ProductEnum productEnum : ProductEnum.values()) {
                            s.onNext(webServiceTemplate.getData(productEnum));
                        }
                    }
                    if (shouldCrash) {
                        s.onError(new Exception("Something crashed"));
                    }
                    s.onCompleted();
                } catch (Exception ex){
                    s.onError(ex);
                }
            };
            Thread thread = new Thread(r);
            thread.start();
        });
    }
}
