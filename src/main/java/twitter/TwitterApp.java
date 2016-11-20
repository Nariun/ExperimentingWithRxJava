package twitter;

import rx.Observable;
import twitter4j.Status;

import static observables.utils.ObservableUtil.createStringFromStacktrace;

/**
 * Created by Jonathan Brites on 20/11/2016.
 */
public class TwitterApp {
    public static void main(String[] args) {
        TwitterSubject subject = new TwitterSubject();
        Observable<Status> observable = subject.observe();

        observable.subscribe(TwitterApp::handleStatus,
                throwable -> createStringFromStacktrace(throwable));
    }

    public static void handleStatus(final Status status){
        final StringBuilder sb = new StringBuilder("[Tweet]{");
        sb.append("Text: " + status.getText());
        sb.append(System.lineSeparator());
        sb.append("User: " + status.getUser().getName() + " -> " + status.getUser().getDescription());
        sb.append("}");
        System.out.println(sb.toString());
    }
}
