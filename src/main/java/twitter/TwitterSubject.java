package twitter;

import rx.Observable;
import rx.subjects.PublishSubject;
import twitter.utils.TwitterAuthenticationUtil;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;

/**
 * Created by Jonathan Brites on 20/11/2016.
 */
public class TwitterSubject {
    //PublishSubject is used to have multiple subscribers using 1 http connection, without caching
    private final PublishSubject<Status> subject = PublishSubject.create();

    public TwitterSubject(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        try {
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(TwitterAuthenticationUtil.getProperty("OAuthConsumerKey"))
                    .setOAuthConsumerSecret(TwitterAuthenticationUtil.getProperty("OAuthConsumerSecret"))
                    .setOAuthAccessToken(TwitterAuthenticationUtil.getProperty("OAuthAccessToken"))
                    .setOAuthAccessTokenSecret(TwitterAuthenticationUtil.getProperty("OAuthAccessTokenSecret"));
        } catch (IOException e) {
            System.out.println("Something bad happened while loading properties: " + e.getMessage());
            e.printStackTrace();
        }


        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        twitterStream.addListener(new StatusListener() {
            @Override
            public void onStatus(Status status) {
                if("en".equals(status.getLang()))
                subject.onNext(status);
            }

            @Override
            public void onException(Exception e) {
                subject.onError(e);
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
//                System.out.println("Deletion: Not implemented");
            }

            @Override
            public void onTrackLimitationNotice(int i) {
//                System.out.println("LimitNotice: Not implemented");
            }

            @Override
            public void onScrubGeo(long l, long l1) {
//                System.out.println("ScrubGeo: Not implemented");
            }

            @Override
            public void onStallWarning(StallWarning stallWarning) {
//                System.out.println("Stallwarning: Not implemented");
            }
        });
        twitterStream.sample();
    }

    public Observable<Status> observe(){
        return subject;
    }
}
