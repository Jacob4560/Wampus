import java.awt.*;
import java.io.File;

public class Item {
    private int metascore;
    private double userscore;
    private List genres;
    private File image;
    private String description;

    public Item(int mscore, int uscore, List givenGenres, File picture, String desc){
        metascore = mscore;
        userscore = uscore;
        genres = givenGenres;
        image = picture;
        description = desc;
    }

    public int getMetascore() {
        return metascore;
    }

    public double getUserscore() {
        return userscore;
    }

    public List getGenres(){
        return genres;
    }

    public File getImage(){
        return image;
    }

    public String getDescription(){
        return description;
    }
}