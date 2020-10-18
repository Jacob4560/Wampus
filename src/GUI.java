import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;


public class GUI {

    public JFrame frame;
    public JPanel panel;
    public JLabel titleImg;
    public JTextArea description;
    public JLabel titleText;
    public JLabel rating;
    public JButton thumbsUp;
    public JButton thumbsDown;


    public GUI(){
        frame = new JFrame();
        frame.setSize(1280,720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

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
        refresh();
    }

    public void description(String text){
        removeOld(description);
        description = new JTextArea(text);
        description.setLineWrap(true);
        description.setBounds(160, 425, 960, 100);
        description.setFont(new Font("Serif", Font.PLAIN, 14));
        panel.add(description);
        refresh();
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
        rating.setFont(new Font("Serif", Font.PLAIN, 14));
        panel.add(rating);
    }

    public void thumbs(){
        removeOld(thumbsUp);
        removeOld(thumbsDown);
        ImageIcon up = new ImageIcon("/img/thumbsup.png");
        ImageIcon down = new ImageIcon("img/thumbsdown.png");
        thumbsUp = new JButton(up);
        thumbsUp.setBounds(0,500, 128, 128);
        thumbsDown = new JButton(down);
        thumbsDown.setBounds(0,0, 128, 128);
        panel.add(thumbsUp);
        panel.add(thumbsDown);
        refresh();
    }

    private void removeOld(Component comp){
        if (comp != null){
            panel.remove(comp);
        }
    }

    private void refresh(){
        frame.validate();
        frame.repaint();
    }

}