package observables.dummies;

import rx.Observable;
import rx.Subscriber;

import static observables.utils.ObservableUtil.*;

/**
 * Created by Jonathan Brites on 19/11/2016.
 */
public class CreatingTheDummyObservables {
    public static void main(String[] args) {
//        creatingTheNeverObservable();
//        creatingTheEmptyObservable();
        creatingTheJustObservable();
    }

    private static void creatingTheJustObservable() {
        Observable<String> justObservable = Observable.create(subscriber -> {
            log("Just observable created");
            subscriber.onNext("I'm gonna give everyone this!");
            subscriber.onCompleted();
            log("Just observable finished");
        });

        justObservable.subscribe(value -> log(value));
    }

    private static void creatingTheNeverObservable(){
        Observable<String> neverObservable = Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(Subscriber<? super String> subscriber) {
                log("I'm created but I will not do something!");
            }
        });

        neverObservable.subscribe(value -> log(value));
    }

    private static void creatingTheEmptyObservable(){
        Observable<String> emptyObservable = Observable.create(subscriber -> {
            log("Empty observable created");
            subscriber.onCompleted();
            log("Empty observable finished");
        });

        emptyObservable.subscribe(value -> onNext(value),
                throwable -> onError(throwable),
                () -> onCompleted());
    }

    private static void onCompleted() {
        log("Observable said he's done");
    }

    private static void onError(Throwable throwable) {
        log("Something went wrong with the observable!");
        log(createStringFromStacktrace(throwable));
    }

    private static void onNext(String s) {
        log("Observable told me: " + s);
    }
}
