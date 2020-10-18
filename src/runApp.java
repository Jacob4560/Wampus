import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;
import java.util.LinkedList;

public class runApp {

    public static void main(String[] args) throws IOException {
        // Initiates the GUI.
        JFrame frame = new JFrame();
        frame.setSize(640,480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        frame.add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("Title");
        label.setBounds(50, 0, 100, 50);
        panel.add(label);

        frame.setVisible(true);
        // Connects to Metacritic for parsing reviews.
        //String genreChoice = new Scanner(System.in).next();
        Document d = Jsoup.connect("https://www.metacritic.com/browse/movies/genre/metascore/horror").get();
        LinkedList<Item> movies = new LinkedList<>();
        getItemInfo(d, movies);
        printAllItems(movies);
    }

    public static void printAllItems(LinkedList<Item> movies){
        for (Item movie: movies){
            System.out.println("Title: " + movie.getTitle());
            System.out.println("Score: " + movie.getMetaScore());
            System.out.println("Url: " + movie.getUrl());
            //System.out.println("User Score: " + movie.getUserscore());
        }
    }

    // Test method which reads the top 100 of a genre from Metacritic. Adds movie items to the
    // given LinkedList with a title and score.
    public static void getItemInfo(Document d, LinkedList<Item> movies){
        Elements titles = d.select(".clamp-summary-wrap h3");
        Elements scores = d.select(".metascore_anchor");
        Elements urls = d.select("a.title");
        for (int i = 0; i < titles.size(); i++){
            Item movie = new Item();
            movie.setTitle(titles.eq(i).text());
            // Each movie has 3 score elements, so we have to use 3*i.
            movie.setMetaScore(Integer.parseInt(scores.eq(3*i).text()));
            //.setUserscore(Double.valueOf(scores.eq(3*i+2).text()));
            movie.setUrl(urls.eq(i).attr("href"));
            movies.add(movie);
        }
    }
}
