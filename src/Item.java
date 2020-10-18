import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

public class Item implements Comparable<Item> {

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
    public LinkedHashMap<String, Double> genreRatingsList;

    public Item() {
        genres = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getUrl() {
        return url;
    }

    public int getMetaScore() {
        return metaScore;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setMetaScore(int score) {
        metaScore = score;
    }

    //public void setGenres(ArrayList<String> genres) { this.genres = genres; }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    private double computeScore(Item i) {
        double score = 0;
        for (String genre : i.getGenres()) {
            if (genreRatingsList.containsKey(genre)) {
                score += genreRatingsList.get(genre);
            }
        }
        return score / i.getGenres().size();
    }

    @Override
    public int compareTo(Item o) {
        double otherScore = computeScore(o);
        double thisScore = computeScore(this);

        return Double.compare(thisScore, otherScore);
    }
}