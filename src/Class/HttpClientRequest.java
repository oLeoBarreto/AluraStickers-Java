package Class;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpClientRequest {

    public String getData(String url) {

        try {

            URI apiUrl = URI.create(url);
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(apiUrl).GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            return response.body();

        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }

    }
}
