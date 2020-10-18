import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class runApp {

    private static int currIndex;
    private static LinkedHashMap<String, Double> genreRatings;
    // Chooses how many movies from 1-100 to choose from.
    public static final int topNumber = 10;

    public static void main(String[] args) throws IOException, InterruptedException  {

        // Initiates the GUI.
        GUI gui = new GUI();
        // Wait for just a minute (this sucked to implement).
        String choice = chooseGenre(gui);
        clearInitial(gui);
        // Connects to Metacritic for parsing reviews.
        Document d = Jsoup.connect("https://www.metacritic.com/browse/movies/genre/metascore/" + choice).get();
        genreRatings = new LinkedHashMap<>();
        LinkedList<Item> items = new LinkedList<>();

        getItemInfo(d, items);

        printAllItems(items);
        thumbs(gui, items);

        initiateRatingGUI(gui, items, new Random());

    }

    public static String chooseGenre(GUI gui) throws InterruptedException{
        gui.wampusTitle();
        String[] genreArray = new String[]{"action","adventure","animation","biography","comedy","crime","documentary",
                            "drama","family","fantasy"};
        gui.genreChoices(genreArray);
        gui.startProgram();
        return gui.genreChoices.getSelectedItem().toString();
    }

    public static void clearInitial(GUI gui){
        gui.removeOld(gui.wampusTitle);
        gui.removeOld(gui.genreChoices);
        gui.removeOld(gui.startProgram);
        gui.refresh();
    }
    // Debug program which prints out all the items in the item list by showing
    // some of their values (rating, title, etc.).
    public static void printAllItems(LinkedList<Item> items){
        for (Item item: items){
            System.out.println("Title: " + item.getTitle());
            System.out.println("Score: " + item.getMetaScore());
            System.out.println("Url: " + item.getUrl());
            System.out.println("Genres: " + item.genres.toString());
            System.out.println("Rating: " + item.getRating());
            System.out.println("Runtime: " + item.getRuntime() + " minutes");
            System.out.println("Summary: " + item.getDescription());
            System.out.println();
        }
    }

    // Method which reads the top 100 of a genre from Metacritic. Adds items to the
    // given LinkedList with a title, score, etc.
    public static void getItemInfo(Document d, LinkedList<Item> items) throws IOException {
        Elements titles = d.select(".clamp-summary-wrap h3");
        Elements scores = d.select(".metascore_anchor");
        Elements summary = d.select(".summary");
        Elements urls = d.select("a.title");
        for (int i = 0; i < topNumber; i++){
            Item item = new Item();
            item.setTitle(titles.eq(i).text());
            item.setDescription(summary.eq(i).text());
            // Each item has 3 score elements, so we have to use 3*i.
            item.setMetaScore(Integer.parseInt(scores.eq(3*i).text()));
            item.setUrl(urls.eq(i).attr("href"));
            items.add(item);
        }

        // Goes to each item's invidiual page to parse for more information.
        int val = 0;
        for (Item item : items) {
            Document d2 = Jsoup.connect("https://www.metacritic.com/" + item.getUrl()).get();
            Elements genres = d2.select(".genres span > span");
            item.setRating(d2.select(".rating > span").eq(1).text());
            String runtime = d2.select(".runtime > span").eq(1).text();
            item.setImageUrl(d2.select(".summary_img").eq(0).attr("src"));
            item.setRuntime(Integer.parseInt(runtime.substring(0, runtime.indexOf(" "))));
            for (int i = 0; i < genres.size(); i++){
                item.genres.add((genres.eq(i).text()));
            }
            val++;
            System.out.println(val + "/" + topNumber);
        }

    }

    private static void initiateRatingGUI(GUI gui, LinkedList<Item> items, Random randy) throws IOException{
        int randomIndex = randy.nextInt(items.size());
        gui.titleImage(items.get(randomIndex).getImageUrl());
        gui.title(items.get(randomIndex).getTitle());
        gui.description(items.get(randomIndex).getDescription());
        gui.rating(items.get(randomIndex).getRating());
        gui.runtime(items.get(randomIndex).getRuntime());
        gui.genres(items.get(randomIndex).getGenres().toString());
        gui.score(items.get(randomIndex).getMetaScore());
        gui.refresh();
        currIndex = randomIndex;
    }

    public static void thumbs(GUI gui, LinkedList<Item> items) throws IOException{
        gui.removeOld(gui.thumbsUp);
        gui.removeOld(gui.thumbsDown);
        gui.thumbsUp = new JButton(new ImageIcon(ImageIO.read(runApp.class.getResource("img/thumbsup.png"))));
        gui.thumbsUp.setBounds(350, 200, 128, 128);
        gui.thumbsDown = new JButton(new ImageIcon(ImageIO.read(runApp.class.getResource("img/thumbsdown.png"))));
        gui.thumbsDown.setBounds(802, 200, 128, 128);
        gui.thumbsUp.setContentAreaFilled(false);
        gui.thumbsDown.setContentAreaFilled(false);
        gui.thumbsUp.addActionListener(e -> {
            try {
                if (!items.isEmpty()) {
                    rateItem(1.2, items);
                }
                if (items.size() > 0){
                    initiateRatingGUI(gui, items, new Random());
                } else {
                    //clear();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        gui.thumbsDown.addActionListener(e -> {
            try {
                if (!items.isEmpty()) {
                    rateItem(0.8, items);
                }
                if (items.size() > 0){
                    initiateRatingGUI(gui, items, new Random());
                } else {
                    //clear();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        gui.panel.add(gui.thumbsUp);
        gui.panel.add(gui.thumbsDown);
    }

    public static void rateItem(double factor, LinkedList<Item> items){
        for (String genre: items.get(currIndex).getGenres()){
            if (genreRatings.containsKey(genre)){
                genreRatings.put(genre, genreRatings.get(genre) * factor);
            } else{
                genreRatings.put(genre, factor);
            }
        }
        items.remove(currIndex);
    }
}

