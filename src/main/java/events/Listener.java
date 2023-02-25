package events;

import game.Game;
import game.GameIntro;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements ActionListener, KeyListener {

    private String objName;
    private GameIntro gameIntro;
    private Game game;
    private int topLeftIndex = 0, topRightIndex = 0, middleIndex = 0, bottomLeftIndex = 0, bottomRightIndex = 0;

    public Listener(Game game){
        this.game = game;
    }

    public Listener(String objName, GameIntro gameIntro){
        this.objName = objName;
        this.gameIntro = gameIntro;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(objName.equals("startButton")){
            gameIntro.dispose();
            new Game();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent event) {
        boolean temp = false;
        for (int i = 0; i < game.getLabels().length; i++) {
            if(game.getLabels()[i].getBackground() == Color.GREEN)
                temp = true;
            if(game.getLabels()[i].getBackground() == Color.gray){
                if(i + 21 < game.getLabels().length && game.getLabels()[i + 21].getBackground() == Color.black)
                    topLeftIndex = i;
                if(i + 19 < game.getLabels().length && game.getLabels()[i + 19].getBackground() == Color.black)
                    topRightIndex = i;
                if(i - 19 >= 0 && game.getLabels()[i - 19].getBackground() == Color.black)
                    bottomLeftIndex = i;
                if(i - 21 >= 0 && game.getLabels()[i - 21].getBackground() == Color.black)
                    bottomRightIndex = i;
            }
            if(game.getLabels()[i].getBackground() == Color.black)
                middleIndex = i;
        }
        if(!temp){
            game.randomPos();
            game.getLabels()[game.getFoodIndex()].setBackground(Color.GREEN);
        }

        // // // // // //

        if(event.getKeyCode() == KeyEvent.VK_UP){
            if(topLeftIndex - 20 >= 0)
                setDirections(20, '-');
            generateFood();
        }
        else if(event.getKeyCode() == KeyEvent.VK_DOWN){
            if(bottomRightIndex + 20 <= 399)
                setDirections(20, '+');
            generateFood();
        }
        else if(event.getKeyCode() == KeyEvent.VK_RIGHT){
            int temp1 = 19;
            int temp2 = 59;
            for (int i = 0; i <= 390; i += 20) {
                if(topRightIndex >= i && topRightIndex <= i + 19)
                    break;
                temp1 += 20;
                temp2 += 20;
            }
            if(topRightIndex < temp1 && bottomRightIndex < temp2)
                setDirections(1, '+');
            generateFood();
        }
        else if(event.getKeyCode() == KeyEvent.VK_LEFT){
            int temp1 = 0;
            int temp2 = 40;
            for (int i = 0; i <= 390; i += 20) {
                if(topLeftIndex >= i && topLeftIndex <= i + 19)
                    break;
                temp1 += 20;
                temp2 += 20;
            }
            if(topLeftIndex > temp1 && bottomLeftIndex > temp2)
                setDirections(1, '-');
            generateFood();
        }
    }

    private void setDirections(int value, char operation){
        if(operation == '-'){
            game.getLabels()[topLeftIndex - value].setBackground(Color.gray);
            game.getLabels()[topRightIndex - value].setBackground(Color.gray);
            game.getLabels()[middleIndex - value].setBackground(Color.black);
            game.getLabels()[bottomLeftIndex - value].setBackground(Color.gray);
            game.getLabels()[bottomRightIndex - value].setBackground(Color.gray);
        }else if(operation == '+'){
            game.getLabels()[topLeftIndex + value].setBackground(Color.gray);
            game.getLabels()[topRightIndex + value].setBackground(Color.gray);
            game.getLabels()[middleIndex + value].setBackground(Color.black);
            game.getLabels()[bottomLeftIndex + value].setBackground(Color.gray);
            game.getLabels()[bottomRightIndex + value].setBackground(Color.gray);
        }
        game.getLabels()[topLeftIndex].setBackground(Color.WHITE);
        game.getLabels()[topRightIndex].setBackground(Color.WHITE);
        game.getLabels()[middleIndex].setBackground(Color.WHITE);
        game.getLabels()[bottomLeftIndex].setBackground(Color.WHITE);
        game.getLabels()[bottomRightIndex].setBackground(Color.WHITE);
    }

    private void generateFood(){
        boolean temptemp = false;
        for (int i = 0; i < game.getLabels().length; i++)
            if(game.getLabels()[i].getBackground() == Color.GREEN){
                temptemp = true;
                break;
            }
        if(!temptemp)
            setNewState();
    }

    private void setNewState(){
        game.randomPos();
        game.setHowMuchAte(game.getHowMuchAte() + 1);
        game.getHowMuchLabel().setText("You've ate " + game.getHowMuchAte());
        game.getLabels()[game.getFoodIndex()].setBackground(Color.GREEN);
    }
}
