package game;

import events.Listener;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    private JLabel[] labels;
    private JLabel howMuchLabel;
    private JPanel centerPanel;
    private int howMuchAte = 0;
    private int foodIndex;

    public Game(){
        setTitle("Spider Game");
        howMuchLabel = new JLabel("You've ate 0");
        centerPanel = new JPanel(new GridLayout(20,20));
        howMuchLabel.setFont(new Font("Arial",Font.ITALIC,30));
        howMuchLabel.setHorizontalAlignment(SwingConstants.CENTER);
        howMuchLabel.setOpaque(true);
        howMuchLabel.setBackground(Color.red);
        labels = new JLabel[400];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setOpaque(true);
            centerPanel.add(labels[i]);
            if(i == 0 || i == 2 || i == 40 || i == 42){
                labels[i].setBackground(Color.gray);
                continue;
            }
            if(i == 21){
                labels[i].setBackground(Color.black);
                continue;
            }
            labels[i].setBackground(Color.WHITE);
        }
        add(centerPanel);
        add(howMuchLabel,BorderLayout.NORTH);
        randomPos();
        labels[foodIndex].setBackground(Color.GREEN);
        setSize(600,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        addTimerView();
        addKeyListener(new Listener(this));
    }

    public void randomPos(){
        foodIndex = (int)(Math.random() * 400);
        int before = -1;
        for (int i = 0; i < labels.length; i++)
            if(labels[i].getBackground() == Color.GREEN)
                before = i;
        while(foodIndex == before)
            foodIndex = (int)(Math.random() * 400);
    }

    public void addTimerView(){
        new GameTimer(this).start();
    }

    public JLabel[] getLabels() {
        return labels;
    }

    public int getHowMuchAte() {
        return howMuchAte;
    }

    public void setHowMuchAte(int howMuchAte) {
        this.howMuchAte = howMuchAte;
    }

    public JLabel getHowMuchLabel(){
        return howMuchLabel;
    }

    public int getFoodIndex() {
        return foodIndex;
    }
}
