package quiz.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HalamanLogin extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private String username;

    public HalamanLogin() {
        setTitle("Halaman Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); 
        
        JLabel welcomeLabel = new JLabel("Selamat datang di aplikasi 123230066");
        welcomeLabel.setBounds(40, 5, 220, 20);
        add(welcomeLabel);

        JLabel labelUsername = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel labelPassword = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        labelUsername.setBounds(20, 35, 80, 20);
        usernameField.setBounds(100, 35, 150, 20);
        labelPassword.setBounds(20, 65, 80, 20);
        passwordField.setBounds(100, 65, 150, 20);
        loginButton.setBounds(100, 105, 100, 30);

        add(labelUsername);
        add(usernameField);
        add(labelPassword);
        add(passwordField);
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(HalamanLogin.this, "Username tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");
                } else if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(HalamanLogin.this, "Password tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");
                } else if (!password.equals("pbo-d")) {
                    JOptionPane.showMessageDialog(HalamanLogin.this, "Password salah!", "Error", JOptionPane.ERROR_MESSAGE);
                }else {
                    dispose();
                    new HalamanUtama(username).setVisible(true);
                }
            }
        });

        setVisible(true);
    }
}