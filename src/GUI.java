import javax.swing.*;


public class GUI {
    JFrame frame;

    public static void initiate(){

        JFrame frame = new JFrame();
        frame.setSize(640,480);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        frame.add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("Title");
        label.setBounds(0,0,640,480);
        panel.add(label);

        frame.setVisible(true);
    }

}