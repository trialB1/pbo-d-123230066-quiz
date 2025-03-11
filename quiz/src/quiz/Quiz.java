package quiz;  

import quiz.pages.HalamanLogin;
import javax.swing.*;

public class Quiz {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HalamanLogin().setVisible(true);
        });
    }
}