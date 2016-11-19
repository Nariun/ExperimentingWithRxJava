package observables.subscriptionbehaviors;

import rx.Observable;
import rx.Subscriber;

import static observables.utils.ObservableUtil.log;

/**
 * Created by Jonathan Brites on 19/11/2016.
 */
public class MultipleSubscriptionsPerObservable {
    public static void main(String[] args) {
        Observable<String> justObservable = Observable.<String>create(subscriber -> {
            log("justObservable created");
            subscriber.onNext("I am the only value that will be send");
            subscriber.onCompleted();
            log("justObservable is done");
        })
//                .cache()
                ;

        justObservable.subscribe(value -> log("First subscriber: " + value));
        justObservable.subscribe(value -> log("Second subscriber: " + value));
    }
}
