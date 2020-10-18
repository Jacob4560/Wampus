import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class GUI {

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


    public GUI(){
        frame = new JFrame();
        frame.setSize(1280,720);
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
        titleImg.setBounds(0,175, 1280, 250);
        panel.add(titleImg);
    }

    public void description(String text){
        removeOld(description);
        description = new JTextArea(text);
        description.setLineWrap(true);
        description.setBounds(160, 425, 960, 100);
        description.setFont(new Font("Serif", Font.PLAIN, 16));
        panel.add(description);
    }

    public void title(String text){
        removeOld(titleText);
        titleText = new JLabel(text, SwingConstants.CENTER);
        titleText.setBounds(0, 50, 1280, 75);
        titleText.setFont(new Font("Serif", Font.PLAIN, 48));
        panel.add(titleText);
    }

    public void rating(String text){
        removeOld(rating);
        rating = new JLabel("Rating: " + text, SwingConstants.CENTER);
        rating.setBounds(560, 500, 160, 75);
        rating.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(rating);
    }

    public void removeOld(Component comp){
        if (comp != null){
            panel.remove(comp);
        }
    }
    public void genres(String text){
        removeOld(genres);
        genres = new JLabel("Genres: " + text.substring(1,text.length()-1), SwingConstants.CENTER);
        genres.setBounds(440, 580, 400, 75);
        genres.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(genres);
    }

    public void runtime(int time){
        removeOld(runtime);
        runtime = new JLabel("Runtime: " + time + " minutes", SwingConstants.CENTER);
        runtime.setBounds(540, 540, 200, 75);
        runtime.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(runtime);
    }

    public void score(int metacriticScore){
        removeOld(score);
        score = new JLabel("" + metacriticScore, SwingConstants.CENTER);
        score.setBounds(496, 365, 60, 60);
        score.setFont(new Font("Serif", Font.PLAIN, 20));
        score.setOpaque(true);
        if (metacriticScore >= 61){
            score.setBackground(Color.green);
        } else if (metacriticScore >= 40){
            score.setBackground(Color.yellow);
        } else {
            score.setBackground(Color.red);
        }
        panel.add(score);
    }

    public void refresh(){
        frame.validate();
        frame.repaint();
    }
}