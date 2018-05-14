package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel implements ActionListener {
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.setColor(Color.decode("#f8f9fa"));
        g2d.fillRect(0,0, getWidth(), getHeight());
    }
    private JLabel portLabel;
    private JLabel notifyLabel,notifyLabel2;
    private JTextField portField;
    private JButton startButton, stopButton;
    private OurHttpServer ourHttpServer;
    public StartPanel(OurHttpServer ourHttpServer) {
        this.ourHttpServer = ourHttpServer;
        setSize(new Dimension(300,250));
        setLayout(null);
        portLabel = new JLabel("Enter the port number: ");
        portLabel.setBounds(10,10,280,50);

        notifyLabel = new JLabel("The http server successfully works,");
        notifyLabel.setBounds(10,10,280,30);
        notifyLabel2 = new JLabel("the server started running on port: ");
        notifyLabel2.setBounds(10,30,280,50);

        portField = new JTextField();
        portField.setBounds(10,70,280,40);
        //start button
        startButton = new JButton("Start");
        startButton.setBackground(Color.decode("#007bff"));
        startButton.setFocusPainted(false);
        startButton.setForeground(Color.WHITE);
        startButton.setBorderPainted(false);
        startButton.setBounds(210,130,80,50);
        startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        startButton.addActionListener(this);

        //stop button

        stopButton = new JButton("Stop");
        stopButton.setBackground(Color.decode("#dc3545"));
        stopButton.setFocusPainted(false);
        stopButton.setForeground(Color.WHITE);
        stopButton.setBorderPainted(false);
        stopButton.setBounds(90,100,120,50);
        stopButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        stopButton.addActionListener(this);

        add(portLabel);
        add(portField);
        add(startButton);
    }
    public int parsePort(String value) {
        int port = 0;
        try{
            port = Integer.parseInt(value);
        }catch (Exception e){
            port = 80;
        }
        return port;
    }
    public boolean isValidPort(int port){
        if(port != 0 && port > 1024 && port < 65536) {
            return true;
        }else{
            return false;
        }
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == startButton){
            if (isValidPort(parsePort(portField.getText().trim()))){
                this.removeAll();
                this.add(notifyLabel);
                this.add(notifyLabel2);
                this.add(stopButton);
                repaint();
                revalidate();
                ourHttpServer.Start(parsePort(portField.getText().trim()));
                notifyLabel2.setText(notifyLabel2.getText() + portField.getText().trim());
            }else{
                portLabel.setForeground(Color.decode("#dc3545"));
                portLabel.setText("please enter valid port number: ");
            }
        }else if (actionEvent.getSource() == stopButton) {
            ourHttpServer.Stop();
            this.removeAll();
            this.add(portLabel);
            this.add(portField);
            this.add(startButton);
            repaint();
            revalidate();
        }
    }
}
