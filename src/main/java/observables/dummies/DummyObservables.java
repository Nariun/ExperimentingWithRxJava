package observables.dummies;


import rx.Observable;
import rx.Subscriber;

import java.util.Arrays;

import static observables.utils.ObservableUtil.*;

/**
 * Created by Jonathan Brites on 19/11/2016.
 */
public class DummyObservables {
    public static void main(String[] args) {
        final Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                log("Observable said he's done");
            }

            @Override
            public void onError(Throwable throwable) {
                log("Something went wrong with the observable!");
                log(createStringFromStacktrace(throwable));
            }

            @Override
            public void onNext(String s) {
                log("Observable told me: " + s);
            }
        };

//        subscribeToErrorObservable(subscriber);
//        subscribeToNeverObservable(subscriber);
//        subscribeToEmpty(subscriber);
//        subscribeToJustObservable(subscriber);
//        subscribeToFromObservable(subscriber);
        subscribeToRangeObservableWithLambas();

    }

    private static void subscribeToFromObservable(final Subscriber<String> subscriber) {
        final Observable<String> fromObservable = Observable.from(Arrays.asList("First message", "Second message", "Third message", "Fourth message"));
        subscribeToObservable(fromObservable, subscriber);
    }

    private static void subscribeToJustObservable(final Subscriber<String> subscriber) {
        final Observable<String> justObservable = Observable.just("I only sent one value");
        subscribeToObservable(justObservable, subscriber);
    }

    private static void subscribeToEmpty(final Subscriber<String> subscriber) {
        final Observable<String> emptyObservable = Observable.empty();
        subscribeToObservable(emptyObservable, subscriber);
    }

    private static void subscribeToNeverObservable(final Subscriber<String> subscriber) {
        final Observable<String> neverObservable = Observable.never();
        subscribeToObservable(neverObservable, subscriber);
    }

    private static void subscribeToErrorObservable(final Subscriber<String> subscriber){
        final Observable<String> errorObservable = Observable.error(new Exception("This error will destroy the world!"));
        subscribeToObservable(errorObservable, subscriber);
    }

    private static void subscribeToObservable(final Observable<String> observable, final Subscriber<String> subscriber){
        log("Before subscription");
        observable.subscribe(subscriber);
        log("After subscription");
    }

    private static void subscribeToRangeObservableWithLambas() {
        final Observable<Integer> rangeObservable = Observable.range(5,20);
        log("Before subscription");
        rangeObservable.subscribe(value -> log(value.toString()),
                throwable -> log(createStringFromStacktrace(throwable)),
                () -> log("Observable said he's done"));
        log("After subscription");
    }
}
