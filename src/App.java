import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://api.themoviedb.org/3/trending/movie/day?api_key=9d882d8af9270abef6ca6ab3100ac6da";

        URI apiUrl = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(apiUrl).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        var jsonParser = new JsonParser();
        List<Map<String, String>> moviesList = jsonParser.parser(body);

        var stickerFactory = new StickerFactory();
        for (Map<String, String> movie : moviesList) {

            String movieTitle = movie.get("title");
            String movieImageUrl = "https://image.tmdb.org/t/p/w500" + movie.get("backdrop_path");

            System.out.println("\u001B[32m" + movieTitle + "\u001B[0m");
            System.out.println("Banner: " + movieImageUrl);
            System.out.println("Votos: " + movie.get("vote_average"));
            System.out.println();

            InputStream inputStream = new URL(movieImageUrl).openStream();
            stickerFactory.create(inputStream, "stickers/" + movieTitle.replaceAll(":", "") + ".png");
        }
    }
}