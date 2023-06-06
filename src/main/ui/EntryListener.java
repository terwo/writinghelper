package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the class that is responsible for making new entries
public class EntryListener implements ActionListener, DocumentListener {
    private boolean alreadyEnabled = false; // whether a button is enabled or not
    private JButton button; // the button that invokes the action to make an entry
    private IdiomCollectionOptionsGUI idiomCollectionOptionsGUI; // the associated options pane

    public EntryListener(IdiomCollectionOptionsGUI idiomCollectionOptionsGUI, JButton button) {
        this.button = button;
        this.idiomCollectionOptionsGUI = idiomCollectionOptionsGUI;
    }

    // EFFECTS: obtains info from all the text fields and ushers a new entry based on this data
    @Override
    public void actionPerformed(ActionEvent e) {
        String idiom = idiomCollectionOptionsGUI.getIdiomText().getText();
        String meaning = idiomCollectionOptionsGUI.getMeaningText().getText();
        String reading = idiomCollectionOptionsGUI.getReadingText().getText();
        String comments = idiomCollectionOptionsGUI.getCommentsText().getText();
        String tags = idiomCollectionOptionsGUI.getTagsText().getText();

        addIdiomInfo(idiom, meaning, reading, comments, tags);

        idiomCollectionOptionsGUI.getIdiomText().requestFocusInWindow();
        idiomCollectionOptionsGUI.getIdiomText().setText("");
        idiomCollectionOptionsGUI.getMeaningText().requestFocusInWindow();
        idiomCollectionOptionsGUI.getMeaningText().setText("");
        idiomCollectionOptionsGUI.getReadingText().requestFocusInWindow();
        idiomCollectionOptionsGUI.getReadingText().setText("");
        idiomCollectionOptionsGUI.getCommentsText().requestFocusInWindow();
        idiomCollectionOptionsGUI.getCommentsText().setText("");
        idiomCollectionOptionsGUI.getTagsText().requestFocusInWindow();
        idiomCollectionOptionsGUI.getTagsText().setText("");
    }

    // EFFECTS: enables the make entry button when there's some text added
    @Override
    public void insertUpdate(DocumentEvent e) {
        enableButton();
    }

    // EFFECTS: checks if the text field is empty or not
    @Override
    public void removeUpdate(DocumentEvent e) {
        handleEmptyTextField(e);

    }

    // EFFECTS: if there's still some text, don't disable the make entry button
    @Override
    public void changedUpdate(DocumentEvent e) {
        if (!handleEmptyTextField(e)) {
            enableButton();
        }
    }

    // MODIFIES: this
    // EFFECTS: allows the user to click on the make entry button
    private void enableButton() {
        if (!alreadyEnabled) {
            button.setEnabled(true);
        }
    }

    // MODIFIES: this
    // EFFECTS: manages the make entry button when there's an empty text field
    private boolean handleEmptyTextField(DocumentEvent e) {
        if (e.getDocument().getLength() <= 0) {
            button.setEnabled(false);
            alreadyEnabled = false;
            return true;
        }
        return false;
    }

    // MODIFIES: IdiomCollectionTableGUI
    // EFFECTS: passes along information from the text fields to make a new entry
    public void addIdiomInfo(String idiom, String meaning, String reading, String comments, String tag) {
        idiomCollectionOptionsGUI.getIdiomCollectionTableGUI().myAddRow(idiom, meaning, reading, comments, tag);
    }

}
