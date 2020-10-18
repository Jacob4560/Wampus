import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class GUI {

    public JFrame frame;
    public JPanel panel;

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
        ImageIcon pic = new ImageIcon(ImageIO.read(new URL(url)));
        JLabel image = new JLabel(pic);
        image.setBounds(0,175, 1280, 250);
        panel.add(image);
    }

    public void title(String text){
        // new ImageIcon(ImageIO.read(new URL("https://static.metacritic.com/images/products/movies/0/5d4f62de038a8b8cbabf13936dddda53-250h.jpg")))
        JLabel title = new JLabel(text, SwingConstants.CENTER);
        title.setBounds(0, 50, 1280, 50);
        title.setFont(new Font("Serif", Font.PLAIN, 32));
        panel.add(title);
    }

}