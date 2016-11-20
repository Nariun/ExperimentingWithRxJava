package twitter.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Jonathan Brites on 20/11/2016.
 */
public class TwitterAuthenticationUtil {

    public static String getProperty(final String property) throws IOException {
        final String value;

        final Properties prop = new Properties();
        final String filename = "twitterproperties/local.properties";
        try (final InputStream input = TwitterAuthenticationUtil.class.getClassLoader().getResourceAsStream(filename)) {
            prop.load(input);

            value = prop.getProperty(property);

        } catch (IOException ex) {
            throw ex;
        }

        return value;

    }
}
