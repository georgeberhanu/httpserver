package com.company;

import javax.swing.*;
import java.awt.*;

public class OurFrame extends JFrame {
    private JPanel contentPane;
    public OurFrame(OurHttpServer ourHttpServer) {
        setTitle("Our Http server");
        setLayout(null);
        contentPane =  new StartPanel(ourHttpServer);
        setSize(contentPane.getSize());
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
}
