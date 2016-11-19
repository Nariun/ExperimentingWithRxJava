package observables;


import rx.Observable;
import rx.Subscriber;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Jonathan Brites on 19/11/2016.
 */
public class DummyObservables {
    public static void main(String[] args) {
        final Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                log("I'm done");
            }

            @Override
            public void onError(Throwable throwable) {
                log("Something went wrong!");
                log(createStringFromStacktrace(throwable));
            }

            @Override
            public void onNext(String s) {
                log("Something came in: " + s);
            }
        };

        subscribeToErrorObservable(subscriber);
//        subscribeToNeverObservable(subscriber);
//        subscribeToEmpty(subscriber);

    }

    private static void subscribeToEmpty(final Subscriber<String> subscriber) {
        Observable<String> emptyObservable = Observable.empty();
        subscribeToObservable(emptyObservable, subscriber);
    }

    private static void subscribeToNeverObservable(final Subscriber<String> subscriber) {
        Observable<String> neverObservable = Observable.never();
        subscribeToObservable(neverObservable, subscriber);
    }

    private static void subscribeToErrorObservable(final Subscriber<String> subscriber){
        Observable<String> errorObservable = Observable.error(new Exception("This error will destroy the world!"));
        subscribeToObservable(errorObservable, subscriber);
    }

    private static void subscribeToObservable(final Observable<String> observable, final Subscriber<String> subscriber){
        log("Before subscription");
        observable.subscribe(subscriber);
        log("After subscription");
    }

    private static void log(final String string){
        System.out.println(Thread.currentThread() + " :" + string);
    }

    private static String createStringFromStacktrace(final Throwable throwable){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
}
