import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;

public class runApp {

    public static void main(String[] args) throws IOException {
        // Initiates the GUI.
        GUI gui = new GUI();
        // Connects to Metacritic for parsing reviews.
        //String genreChoice = new Scanner(System.in).next();
        Document d = Jsoup.connect("https://www.metacritic.com/browse/movies/genre/metascore/horror").get();
        LinkedList<Item> items = new LinkedList<>();
        getItemInfo(d, items);
        printAllItems(items);
        gui.titleImage(items.get(4).getImageUrl());
        gui.title(items.get(4).getTitle());
        gui.description(items.get(4).getDescription());
        gui.rating(items.get(4).getRating());
        gui.thumbs();

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
    // given LinkedList with a title, score, and url.
    public static void getItemInfo(Document d, LinkedList<Item> items) throws IOException {
        Elements titles = d.select(".clamp-summary-wrap h3");
        Elements scores = d.select(".metascore_anchor");
        Elements summary = d.select(".summary");
        Elements urls = d.select("a.title");
        for (int i = 0; i < titles.size()/20; i++){
            Item item = new Item();
            item.setTitle(titles.eq(i).text());
            item.setDescription(summary.eq(i).text());
            // Each item has 3 score elements, so we have to use 3*i.
            item.setMetaScore(Integer.parseInt(scores.eq(3*i).text()));
            item.setUrl(urls.eq(i).attr("href"));
            items.add(item);
        }
        for (Item item : items) {
            System.out.println("https://www.metacritic.com/" + item.getUrl());
            Document d2 = Jsoup.connect("https://www.metacritic.com/" + item.getUrl()).get();
            Elements genres = d2.select(".genres span > span");
            item.setRating(d2.select(".rating > span").eq(1).text());
            String runtime = d2.select(".runtime > span").eq(1).text();
            item.setImageUrl(d2.select(".summary_img").eq(0).attr("src"));
            item.setRuntime(Integer.parseInt(runtime.substring(0, runtime.indexOf(" "))));
            for (int i = 0; i < genres.size(); i++){
                item.genres.add((genres.eq(i).text()));
            }

        }

    }
}

