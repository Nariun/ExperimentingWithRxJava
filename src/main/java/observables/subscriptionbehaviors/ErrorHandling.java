package observables.subscriptionbehaviors;

import rx.Observable;

import static observables.utils.ObservableUtil.createStringFromStacktrace;
import static observables.utils.ObservableUtil.log;

/**
 * Created by Jonathan Brites on 19/11/2016.
 */
public class ErrorHandling {
    public static void main(String[] args) {
        Observable<String> observable = Observable.<String>create(subscriber ->{
            try {
                subscriber.onNext(doSomethingThatThrowsAnException());
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
//        Observable<String> observable = Observable.fromCallable(() -> doSomethingThatThrowsAnException());


        observable.subscribe(value -> log(value),
                throwable -> log(createStringFromStacktrace(throwable)),
                () -> log("I'm done"));
    }

    private static String doSomethingThatThrowsAnException() throws Exception {
        throw new Exception("Something went terribly wrong");
    }


}
