import java.io.InputStream;
import java.net.URL;
import java.util.List;

import Class.HttpClientRequest;
import Factory.StickerFactory;
import Obj.Content;
import models.NasaApiContent;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-01&end_date=2022-07-20";

        var http = new HttpClientRequest();
        String data = http.getData(url);

        var apiContent = new NasaApiContent();
        List<Content> contents = apiContent.getContents(data);

        var stickerFactory = new StickerFactory();
        for (int i = 0; i < 3; i++) {

            Content content = contents.get(i);

            System.out.println("\u001B[32m" + content.getTitle() + "\u001B[0m");
            System.out.println("Image: " + content.getUrlImage());
            System.out.println();

            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            stickerFactory.create(inputStream, "stickers/" + content.getTitle() + ".png");
        }
    }
}
