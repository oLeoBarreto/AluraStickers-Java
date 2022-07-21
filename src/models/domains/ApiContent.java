package models.domains;
import java.util.List;

import Obj.Content;

public interface ApiContent {
    public List<Content> getContents(String data);
}
