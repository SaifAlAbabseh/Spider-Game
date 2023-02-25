package game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameTimer extends Thread{

    private JPanel timerViewPanel;
    private JLabel timerView;
    private Game game;

    public GameTimer(Game game){
        timerViewPanel = new JPanel();
        timerViewPanel.setBackground(Color.WHITE);
        timerViewPanel.setBorder(new LineBorder(Color.RED, 5));
        timerView = new JLabel(":30");
        styleTimer();
        timerViewPanel.add(timerView);
        this.game = game;
        game.add(timerViewPanel, BorderLayout.SOUTH);
    }

    private void styleTimer(){
        timerView.setFont(new Font("Arial", Font.BOLD, 40));
        timerView.setForeground(Color.RED);
    }

    @Override
    public void run(){
        int seconds = 30;
        while(seconds > 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.exit(0);
            }
            seconds--;
            if(seconds >= 10){
                timerView.setText(":" + seconds);
            }
            if(seconds < 10){
                timerView.setText(":0" + seconds);
            }
        }
        timerIsDone();
    }

    private void timerIsDone(){
        game.dispose();
        JOptionPane.showMessageDialog(null,  "You've ate " + game.getHowMuchAte() + " in 30 seconds.", "Result", JOptionPane.PLAIN_MESSAGE);
        new GameIntro(this);
    }
}
