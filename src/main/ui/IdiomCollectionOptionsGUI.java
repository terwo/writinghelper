package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the options in main writing helper frame
public class IdiomCollectionOptionsGUI extends JPanel {
    private JButton entryButton; // the make entry button
    private JTextField idiomText; // the text field to enter an idiom's name
    private JTextField meaningText; // the text field to enter an idiom's meaning
    private JTextField readingText; // the text field to enter an idiom's reading
    private JTextField commentsText; // the text field to enter one's comments about an idiom
    private JTextField tagsText; // the tag associated with an idiom
    private JLabel searchLabel; // a label to tell the user to search in the following text field
    private JTextField filterText; // the text field that allows for sorting
    private JButton saveButton; // the save button that brings up options to save the current table to which file
    private JButton loadButton; // the load button that brings up options which file to load from
    private IdiomCollectionTableGUI idiomCollectionTableGUI; // the table the options are linked to


    public IdiomCollectionOptionsGUI(IdiomCollectionTableGUI idiomCollectionTableGUI) {
        this.idiomCollectionTableGUI = idiomCollectionTableGUI;
        createButtons();
        add(entryButton);
        add(idiomText);
        add(meaningText);
        add(readingText);
        add(commentsText);
        add(tagsText);
        add(Box.createVerticalStrut(5));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(Box.createVerticalStrut(5));
        searchLabel = new JLabel("Search by tags:", SwingConstants.TRAILING);
        add(searchLabel);
        add(filterText);
        searchLabel.setLabelFor(filterText);
        add(filterText);
        add(Box.createVerticalStrut(15));
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(Box.createVerticalStrut(15));
        add(saveButton);
        add(Box.createVerticalStrut(15));
        add(loadButton);
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    // EFFECTS: instantiates each of the buttons
    public void createButtons() {
        createEntryButtonAndTexts();
        createSearchBox();
        createSaveButton();
        createLoadButton();
    }

    // referenced // https://stackoverflow.com/questions/3549206/how-to-add-row-in-jtable
    // MODIFIES: this
    // EFFECTS: constructs the entry button and its associated text fields
    public void createEntryButtonAndTexts() {
        entryButton = new JButton("Make Entry");
        EntryListener entryListener = new EntryListener(this, entryButton);
        entryButton.setActionCommand("Make Entry");
        entryButton.addActionListener(entryListener);
        entryButton.setEnabled(false);

        idiomText = new JTextField(10);
        idiomText.addActionListener(entryListener);
        idiomText.getDocument().addDocumentListener(entryListener);

        meaningText = new JTextField(10);
        meaningText.addActionListener(entryListener);
        meaningText.getDocument().addDocumentListener(entryListener);

        readingText = new JTextField(10);
        readingText.addActionListener(entryListener);
        readingText.getDocument().addDocumentListener(entryListener);

        commentsText = new JTextField(10);
        commentsText.addActionListener(entryListener);
        commentsText.getDocument().addDocumentListener(entryListener);

        tagsText = new JTextField(10);
        tagsText.addActionListener(entryListener);
        tagsText.getDocument().addDocumentListener(entryListener);
    }

    // MODIFIES: this
    // EFFECTS: constructs the search field
    public void createSearchBox() {

        filterText = new JTextField(10);
        filterText.getDocument().addDocumentListener(
                new DocumentListener() {
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }

                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
    }

    // EFFECTS: makes the entered text filter entries by their tags
    private void newFilter() {
        RowFilter<MyIdiomCollectionTableModel, Object> rf;
        try {
            rf = RowFilter.regexFilter(filterText.getText(), 4);
            idiomCollectionTableGUI.getModel().getIdiomCollection().searchByTags(filterText.getText());
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        idiomCollectionTableGUI.getSorter().setRowFilter(rf);
    }

    // MODIFIES: this
    // EFFECTS: constructs the save button
    public void createSaveButton() {
        saveButton = new JButton("Save");
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idiomCollectionTableGUI.getWritingHelperGUI().saveWritingHelperGUI();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: constructs the load button
    public void createLoadButton() {
        loadButton = new JButton("Load");
        loadButton.setActionCommand("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idiomCollectionTableGUI.getWritingHelperGUI().loadWritingHelperGUI();
            }
        });
    }

    public JTextField getIdiomText() {
        return idiomText;
    }

    public JTextField getReadingText() {
        return readingText;
    }

    public JTextField getMeaningText() {
        return meaningText;
    }

    public JTextField getCommentsText() {
        return commentsText;
    }

    public JTextField getTagsText() {
        return tagsText;
    }

    public IdiomCollectionTableGUI getIdiomCollectionTableGUI() {
        return idiomCollectionTableGUI;
    }

}
