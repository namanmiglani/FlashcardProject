package ui;

import java.io.FileNotFoundException;
import javax.swing.*;

public class Main {
//    public static void main(String[] args) {
//        try {
//            new FlashcardApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("file not found");
//        }
//    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Menu();
            }
        });
    }
}
