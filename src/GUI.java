import javax.swing.*;


public class GUI {

    public static void main(String[] args) {

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
    }

}