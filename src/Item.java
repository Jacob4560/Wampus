import java.awt.*;
import java.io.File;

public class Item {
    private String title;
    private int metascore;
    private double userscore;
    private List genres;
    private File image;
    private String description;

    public String getTitle() {
        return title;
    }

    public int getMetascore() {
        return metascore;
    }

    public double getUserscore() {
        return userscore;
    }

    public List getGenres() {
        return genres;
    }

    public File getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setMetascore(int score) {
        metascore = score;
    }

    public void setUserscore(double score) {
        userscore = score;
    }

    public void setGenres(List genres) {
        this.genres = genres;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}