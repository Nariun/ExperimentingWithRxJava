package observables.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Jonathan Brites on 19/11/2016.
 */
public class ObservableUtil {
    private ObservableUtil(){}

    public static void log(final String string){
        System.out.println(Thread.currentThread().getName() + ": " + string);
    }

    public static String createStringFromStacktrace(final Throwable throwable){
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
}
