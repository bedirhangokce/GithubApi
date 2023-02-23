import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Connector {
    static int numberOfConnections = 0;
    /**
     * Creates connection with given url and returns response
     * @param url to make connection
     * @return response
     * @throws IOException
     */
    static String connect(String url) throws IOException {
        System.out.println("Connection creating with " + url);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("Authorization", "token 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");

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Failed to connect to Github API (response code " + responseCode + ") " + connection.getResponseMessage());
        }

        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();
        numberOfConnections++;
        return response.toString();
    }
}
