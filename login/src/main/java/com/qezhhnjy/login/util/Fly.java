package com.qezhhnjy.login.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Fly {
    public static void main(String[] args) {
        new Chessboard();
    }
}

//棋盘
class Chessboard extends MouseAdapter {
    private WeiqiFactory wf;
    private Graphics     g;
    private JRadioButton wz;
    private JRadioButton bz;

    Chessboard() {
        wf = new WeiqiFactory();
        JFrame f = new JFrame("享元模式在五子棋游戏中的应用");
        f.setBounds(100, 100, 500, 550);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel SouthJP = new JPanel();
        f.add("South", SouthJP);
        wz = new JRadioButton("白子");
        bz = new JRadioButton("黑子", true);
        ButtonGroup group = new ButtonGroup();
        group.add(wz);
        group.add(bz);
        SouthJP.add(wz);
        SouthJP.add(bz);
        JPanel CenterJP = new JPanel();
        CenterJP.setLayout(null);
        CenterJP.setSize(500, 500);
        CenterJP.addMouseListener(this);
        f.add("Center", CenterJP);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        g = CenterJP.getGraphics();
        g.setColor(Color.BLUE);
        int x  = 50;
        int y  = 50;
        int rw = 400;
        g.drawRect(x, y, rw, rw);
        for (int i = 1; i < 10; i++) {
            //绘制第i条竖直线
            int w = 40;
            g.drawLine(x + (i * w), y, x + (i * w), y + rw);
            //绘制第i条水平线
            g.drawLine(x, y + (i * w), x + rw, y + (i * w));
        }
    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        x = (x - 50) / 40 > 20 ? ((x - 50) / 40 + 1) * 40 + 50 : ((x - 50) / 40) * 40 + 50;
        y = (y - 50) / 40 > 20 ? ((y - 50) / 40 + 1) * 40 + 50 : ((y - 50) / 40) * 40 + 50;
        Point pt = new Point(x - 15, y - 15);
        if (wz.isSelected()) {
            ChessPieces c1 = wf.getChessPieces("w");
            c1.DownPieces(g, pt);
        } else if (bz.isSelected()) {
            ChessPieces c2 = wf.getChessPieces("b");
            c2.DownPieces(g, pt);
        }
    }
}

//抽象享元角色：棋子
interface ChessPieces {
    void DownPieces(Graphics g, Point pt);    //下子
}

//具体享元角色：白子
class WhitePieces implements ChessPieces {
    public void DownPieces(Graphics g, Point pt) {
        g.setColor(Color.WHITE);
        g.fillOval(pt.x, pt.y, 30, 30);
    }
}

//具体享元角色：黑子
class BlackPieces implements ChessPieces {
    public void DownPieces(Graphics g, Point pt) {
        g.setColor(Color.BLACK);
        g.fillOval(pt.x, pt.y, 30, 30);
    }
}

//享元工厂角色
class WeiqiFactory {
    private ArrayList<ChessPieces> qz;

    WeiqiFactory() {
        qz = new ArrayList<>();
        ChessPieces w = new WhitePieces();
        qz.add(w);
        ChessPieces b = new BlackPieces();
        qz.add(b);
    }

    public ChessPieces getChessPieces(String type) {
        if (type.equalsIgnoreCase("w")) {
            return qz.get(0);
        } else if (type.equalsIgnoreCase("b")) {
            return qz.get(1);
        } else {
            return null;
        }
    }
}