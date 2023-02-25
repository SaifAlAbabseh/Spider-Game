package game;

import events.Listener;
import javax.swing.*;
import java.awt.*;

public class GameIntro extends JFrame {

    private JButton startButton;
    private JPanel startButtonPanel;

    public GameIntro() {
        initEverything("Start");
    }

    public GameIntro(Thread thread){
        thread.interrupt();
        initEverything("Restart");
    }

    private void initEverything(String str){
        startButton = new JButton(str);
        startButton.addActionListener(new Listener("startButton", this));
        startButtonPanel = new JPanel();
        startButtonPanel.add(startButton);
        add(new JLabel());
        add(startButtonPanel);
        add(new JLabel());
        styleStartButton();
        initWindow();
    }

    private void styleStartButton(){
        startButton.setFont(new Font("Arial", Font.BOLD, 25));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.RED);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.setFocusPainted(false);
        startButton.setPreferredSize(new Dimension(200, 60));
    }

    private void initWindow(){
        setTitle("Spider Game");
        setLayout(new GridLayout(3,1));
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
