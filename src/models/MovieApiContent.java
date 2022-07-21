package models;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Class.JsonParser;
import Obj.Content;
import models.domains.ApiContent;

public class MovieApiContent implements ApiContent{

    public List<Content> getContents(String data) {

        var jsonParser = new JsonParser();
        List<Map<String, String>> attributesList = jsonParser.parser(data);

        List<Content> contents = new ArrayList<>();

        for (Map<String, String> attributes : attributesList) {
            String title = attributes.get("title");
            String urlImage = attributes.get("image")
                    .replaceAll("(@+)(.*).jpg$", "$1.jpg");

            var content = new Content(title, urlImage);

            contents.add(content);
        }

        return contents;
    }

}
