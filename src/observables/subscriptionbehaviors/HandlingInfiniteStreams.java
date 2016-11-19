package observables.subscriptionbehaviors;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;
import sun.awt.windows.ThemeReader;

import java.math.BigInteger;

import static observables.utils.ObservableUtil.createStringFromStacktrace;
import static observables.utils.ObservableUtil.log;

/**
 * Created by Jonathan Brites on 19/11/2016.
 */
public class HandlingInfiniteStreams {
    public static void main(String[] args) {
        Observable<BigInteger> observable = Observable.<BigInteger>create(subscriber -> {
            log("Observable created");
            Runnable r = () -> {
                BigInteger i = BigInteger.ZERO;
                while(!subscriber.isUnsubscribed()) {
                    subscriber.onNext(i);
                    i = i.add(BigInteger.ONE);
                }
            };
            Thread thread = new Thread(r);
            thread.start();
        });

        Subscription subscribe = observable.subscribe(
                value -> log(value.toString()),
                throwable -> createStringFromStacktrace(throwable)
                );
        log("Started sleeping for 2 milliseconds");
        try{
            Thread.sleep(2);
        } catch (InterruptedException ex) {
            //Ignored for demo purposes: NEVER SWALLOW EXCEPTIONS
        }
        log("finished sleeping");
        subscribe.unsubscribe();
    }
}
