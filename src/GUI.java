import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.EventListener;

public class GUI implements EventListener {

    // This is not good. Use a list next time.
    public JFrame frame;
    public JPanel panel;
    public JLabel titleImg;
    public JTextArea description;
    public JLabel titleText;
    public JLabel rating;
    public JButton thumbsUp;
    public JButton thumbsDown;
    public JLabel runtime;
    public JLabel genres;
    public JLabel score;

    public JLabel wampusTitle;
    public JComboBox genreChoices;
    public JButton startProgram;
    public JTextField movieNumber;

    public JLabel loading;

    public JLabel moviesLeft;



    public boolean wait;

    public GUI() {
        frame = new JFrame();
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Wampus");

        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);
        frame.setVisible(true);
    }

    public void titleImage(String url) throws IOException {
        removeOld(titleImg);
        ImageIcon pic = new ImageIcon(ImageIO.read(new URL(url)));
        titleImg = new JLabel(pic);
        titleImg.setBounds(0, 175, 1280, 250);
        panel.add(titleImg);
    }

    public void description(String text) {
        removeOld(description);
        description = new JTextArea(text);
        description.setLineWrap(true);
        description.setBounds(160, 425, 960, 100);
        description.setFont(new Font("SansSerif", Font.PLAIN, 14));
        description.setEditable(false);
        panel.add(description);
    }

    public void title(String text) {
        removeOld(titleText);
        titleText = new JLabel(text, SwingConstants.CENTER);
        titleText.setBounds(0, 50, 1280, 75);
        titleText.setFont(new Font("Serif", Font.PLAIN, 48));
        panel.add(titleText);
    }

    public void rating(String text) {
        removeOld(rating);
        rating = new JLabel("Rating: " + text, SwingConstants.CENTER);
        rating.setBounds(560, 500, 160, 75);
        rating.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(rating);
    }

    public void removeOld(Component comp) {
        if (comp != null) {
            panel.remove(comp);
        }
    }

    public void genres(String text) {
        removeOld(genres);
        genres = new JLabel("Genres: " + text.substring(1, text.length() - 1), SwingConstants.CENTER);
        genres.setBounds(128, 580, 1024, 75);
        genres.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(genres);
    }

    public void runtime(int time) {
        removeOld(runtime);
        runtime = new JLabel("Runtime: " + time + " minutes", SwingConstants.CENTER);
        runtime.setBounds(540, 540, 200, 75);
        runtime.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(runtime);
    }

    public void score(int metacriticScore) {
        removeOld(score);
        score = new JLabel("" + metacriticScore, SwingConstants.CENTER);
        score.setBounds(496, 365, 60, 60);
        score.setFont(new Font("Serif", Font.PLAIN, 20));
        score.setOpaque(true);
        if (metacriticScore >= 61) {
            score.setBackground(Color.green);
        } else if (metacriticScore >= 40) {
            score.setBackground(Color.yellow);
        } else {
            score.setBackground(Color.red);
        }
        panel.add(score);
    }

    public void wampusTitle() {
        wampusTitle = new JLabel("WAMPUS", SwingConstants.CENTER);
        wampusTitle.setBounds(0, -100, 1280, 720);
        wampusTitle.setFont(new Font("Serif", Font.BOLD, 80));
        wampusTitle.setForeground(new Color(128, 0, 128));
        panel.add(wampusTitle);

    }

    public void genreChoices(String[] genres) throws InterruptedException {
        genreChoices = new JComboBox(genres);
        genreChoices.setBounds(320, 347, 640, 50);
        panel.add(genreChoices);
        refresh();
    }

    public void startProgram() throws InterruptedException {
        startProgram = new JButton("Search");
        startProgram.setBounds(320, 397, 640, 50);
        panel.add(startProgram);
        startProgram.addActionListener(new startButtonAction());
        wait = true;
        while(wait){
            Thread.sleep(100);
        }
    }

    public void loading(String text){
        loading = new JLabel(text, SwingConstants.CENTER);
        loading.setBounds(0,0,1280,720);
        loading.setFont(new Font("Serif", Font.BOLD, 60));
        panel.add(loading);
    }

    public void moviesLeft(int amount){
        removeOld(moviesLeft);
        moviesLeft = new JLabel("Movies left to sort: " + amount, SwingConstants.CENTER);
        moviesLeft.setBounds(0,0,1280,100);
        moviesLeft.setFont(new Font("Serif", Font.BOLD, 14));
        panel.add(moviesLeft);
    }

    public int movieNumber() throws InterruptedException {
        movieNumber = new JTextField("Enter the number of movies to scan (1-100)");
        movieNumber.setBounds(384, 448, 512, 20);
        movieNumber.setFont(new Font("SansSerif", Font.PLAIN, 14));
        panel.add(movieNumber);
        refresh();
        movieNumber.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wait = false;
            }
        });
        wait = true;
        while(wait){
            Thread.sleep(100);
        }
        return Integer.parseInt(movieNumber.getText());
    }

    public void refresh(){
        panel.revalidate();
        panel.repaint();
    }

    class startButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            wait = false;
        }

    }
}
