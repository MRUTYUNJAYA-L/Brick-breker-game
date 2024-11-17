package com.myc.bricks.breaker.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

    private boolean play=false;
    private int score=0;
    private int totalBricks=21;
    private Timer timer;
    private int delay=8;
    private int playerx =310;
    private int ballPosx =120;
    private int ballPosy =350;
    private int ballXDir =-1;
    private int ballyDir =-2;
    private MapGenerator map;

    public GamePlay(){
        map=new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer =new Timer(delay,this);
        timer.start();
    }
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);
        map.draw((Graphics2D)g);
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);

        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score,590,30);

        g.setColor(Color.yellow);
        g.fillRect(playerx,550,100,8);

        g.setColor(Color.GREEN);
        g.fillOval(ballPosx,ballPosy,20,20);

        if(ballPosy>570){
            play=false;
            ballXDir=0;
            ballyDir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game over score: "+score,190,300);

            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press enter to restart",190,340);
        }
        if(totalBricks==0){
            play = false;
            ballXDir = -2;
            ballXDir = -1;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game over :"+score,190,300);

            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press enter to restart",190,340);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            if(new Rectangle(ballPosx,ballPosy,20,20).intersects(new Rectangle(playerx,550,100,8))) {
                ballyDir = -ballyDir;
            }
            A:
            for(int i=0; i<map.map.length; i++){
                for(int j=0; j<map.map[0].length; j++){
                    if(map.map[i][j]>0){
                        int brickx=j*map.brickWidth+80;
                        int bricky=i*map.brickHight+50;
                        int brickswidth=map.brickWidth;
                        int bricksheight=map.brickHight;

                        Rectangle rect=new Rectangle(brickx,bricky,brickswidth,bricksheight);
                        Rectangle ballrect=new Rectangle(ballPosx,ballPosy,20,20);
                        Rectangle brickrect=rect;
                        if(ballrect.intersects(brickrect)){
                            map.setBrickValue(0,i,j);
                            totalBricks--;
                            score+=5;
                            if(ballPosx+19<=brickrect.x || ballPosx+1 >= brickrect.x+brickswidth){
                                ballXDir =-ballXDir;
                            }else{
                                ballyDir=-ballyDir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballPosx+=ballXDir;
            ballPosy+=ballPosy;
            if(ballPosx<0){
                ballXDir=-ballXDir;
            }
            if(ballPosx>670){
                ballXDir=-ballXDir;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(playerx>=600){
                playerx=600;
            }else{
                moveRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(playerx<10){
                playerx=10;
            }else{
                moveLeft();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                ballPosx=120;
                ballPosy=350;
                ballXDir=-1;
                ballPosy=-2;
                score=0;
                playerx=310;
                totalBricks=21;
                map=new MapGenerator(3,7);
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void moveRight(){
        play=true;
        playerx+=20;
}
public void moveLeft(){
        play=true;
        playerx-=20;
}

}
