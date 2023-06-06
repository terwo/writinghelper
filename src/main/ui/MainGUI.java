package ui;

import model.Event;
import model.EventLog;

// Runs WritingHelper as a graphical user interface
public class MainGUI {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WritingHelperGUI();
            }
        });
    }
}
