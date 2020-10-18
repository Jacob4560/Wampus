import org.jsoup.select.Elements;

import java.awt.*;
import java.io.File;

public class Item {

    // These fields can be found from the top genre page.
    private int metaScore;
    private String title;
    private String description;
    private String url;
    private File image;

    // These fields can be found on the unique movie page (can be found in url field).
    private int runtime;
    private String rating;
    private List actors;
    private String director;
    private List genres;

    public String getTitle() { return title; }

    public String getUrl(){ return url; }

    public int getMetaScore() {
        return metaScore;
    }

    public List getGenres() { return genres; }

    public File getImage() { return image; }

    public String getDescription() { return description; }

    public void setMetaScore(int score) { metaScore = score; }

    public void setGenres(List genres) { this.genres = genres; }

    public void setImage(File image) { this.image = image; }

    public void setDescription(String description) { this.description = description; }

    public void setTitle(String title) { this.title = title; }

    public void setUrl(String url) { this.url = url; }
}