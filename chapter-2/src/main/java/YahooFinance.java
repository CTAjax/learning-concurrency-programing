import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author ajax
 */
public class YahooFinance {
    public static void main(String[] args) throws IOException {
        getPrice();
    }

    public static void getPrice() throws IOException {
        final URL url = new URL("https://www.baidu.com");
        final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        final String data = reader.readLine();
        System.out.println(data);
    }
}
