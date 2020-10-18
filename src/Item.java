import org.jsoup.select.Elements;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Item {

    // These fields can be found from the top genre page.
    private int metaScore;
    private String title;
    private String description;
    private String url;

    // These fields can be found on the unique movie page (can be found in url field).
    private int runtime;
    private String rating;
    private String imageUrl;
    public ArrayList<String> genres;

    public Item(){
        genres = new ArrayList<>();
    }

    public String getTitle() { return title; }

    public int getRuntime(){ return runtime; }

    public String getUrl(){ return url; }

    public int getMetaScore() {
        return metaScore;
    }

    public ArrayList<String> getGenres() { return genres; }

    public String getDescription() { return description; }

    public String getRating(){ return rating; }

    public String getImageUrl(){ return imageUrl; }

    public void setMetaScore(int score) { metaScore = score; }

    //public void setGenres(ArrayList<String> genres) { this.genres = genres; }

    public void setDescription(String description) { this.description = description; }

    public void setTitle(String title) { this.title = title; }

    public void setUrl(String url) { this.url = url; }

    public void setRating(String rating){ this.rating = rating; }

    public void setRuntime(int runtime){ this.runtime = runtime; }

    public void setImageUrl(String imageUrl){ this.imageUrl = imageUrl; }
}