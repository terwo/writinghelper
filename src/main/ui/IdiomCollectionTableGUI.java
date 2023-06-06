package ui;

import model.IdiomEntry;
import model.Tags;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;

// Represents the table that will display data
public class IdiomCollectionTableGUI extends JPanel {
    protected JTable table; // having the panel be a table
    protected MyIdiomCollectionTableModel model; // the associated model the table is based off of
    private IdiomCollectionOptionsGUI idiomCollectionOptionsGUI; // the button pane
    private WritingHelperGUI writingHelperGUI; // the frame this table is associated with
    private TableRowSorter<MyIdiomCollectionTableModel> sorter; // the filterer that allows searching by tags
    
    public IdiomCollectionTableGUI(WritingHelperGUI writingHelperGUI) {
        super(new GridLayout(1,0));

        this.writingHelperGUI = writingHelperGUI;

        model = new MyIdiomCollectionTableModel();
        sorter = new TableRowSorter(model);
        table = new JTable(model);
        this.idiomCollectionOptionsGUI = new IdiomCollectionOptionsGUI(this);
        table.setRowSorter(sorter);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        setUpTagsColumn(table.getColumnModel().getColumn(4));
        add(scrollPane);
        add(idiomCollectionOptionsGUI);
    }

    // EFFECTS: makes the tags column a drop down list of options
    public void setUpTagsColumn(TableColumn tagsColumn) {

        JComboBox comboBox = new JComboBox();

        for (String next : Tags.getIdentifiers()) {
            comboBox.addItem(next);
        }
        tagsColumn.setCellEditor(new DefaultCellEditor(comboBox));

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click to view tags");
        tagsColumn.setCellRenderer(renderer);
    }

    // EFFECTS: adds a row with the data from the associated text fields into the table
    public void myAddRow(String idiom, String meaning, String reading, String comments, String tag) {
        Tags tagString = new Tags();
        tagString.addTag(tag);
        model.add(new IdiomEntry(idiom, meaning, reading, comments, tagString));
    }

    public TableRowSorter<MyIdiomCollectionTableModel> getSorter() {
        return sorter;
    }

    public MyIdiomCollectionTableModel getModel() {
        return model;
    }

    public WritingHelperGUI getWritingHelperGUI() {
        return writingHelperGUI;
    }

}
