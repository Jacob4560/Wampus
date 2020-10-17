import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;

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
        String genreChoice = "crime";
        Document d =Jsoup.connect("https://www.metacritic.com/browse/movies/genre/metascore/" + genreChoice).get();
        Item movie = new Item();
        getAllMoviesAndRatings(d, d.select(".clamp-summary-wrap h3").size());
    }

    public static void getAllMoviesAndRatings(Document d, int x){
        Elements titles = d.select(".clamp-summary-wrap h3");
        Elements scores = d.select(".metascore_anchor");
        for (int i = 0; i < x; i++){
            System.out.println(titles.eq(i).text());
            System.out.println(scores.eq(3*i).text());
            System.out.println(scores.eq(3*i+2).text());
        }
    }
}
