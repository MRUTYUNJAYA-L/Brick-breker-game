package com.myc.bricks.breaker.game;

import javax.swing.*;

public class Main{
    public static void main(String args[]){
        JFrame obj=new JFrame();
        GamePlay gp=new GamePlay();
        obj.setBounds(10,10,700,600);
        obj.setTitle("Brick Breaker Game");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gp);
    }
}