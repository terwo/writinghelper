package ui;

import model.IdiomCollection;
import model.IdiomEntry;
import model.Tags;

import javax.swing.table.*;
import java.util.ArrayList;

// Represents the model that the table is based off of
public class MyIdiomCollectionTableModel extends AbstractTableModel {
    protected static String[] COLUMN_NAMES = {"Idiom", "Meaning", "Reading", "Comments", "Tags"}; // column names
    private IdiomCollection idiomCollection; // idiom collection the model uses

    public MyIdiomCollectionTableModel() {
        idiomCollection = new IdiomCollection();
    }

    @Override
    public int getRowCount() {
        return idiomCollection.getCollection().size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }


    // EFFECTS: allows for all information other than the idiom name editable
    public boolean isCellEditable(int row, int col) {
        return (col >= 1);
    }

    // EFFECTS: returns the value at a certain cell
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        IdiomEntry ie = idiomCollection.getCollection().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ie.getIdiom();
            case 1:
                return ie.getMeaning();
            case 2:
                return ie.getReading();
            case 3:
                return ie.getComment();
            case 4:
                return ie.getTags().getOneTag(0);
        }
        return null;
    }

    // EFFECTS: changes the value at the given cell
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        IdiomEntry ie = idiomCollection.getCollection().get(rowIndex);
        if (columnIndex == 1) {
            String meaning = (String) value;
            ie.setMeaning(meaning);
        }
        if (columnIndex == 2) {
            String reading = (String) value;
            ie.setReading(reading);
        }
        if (columnIndex == 3) {
            String experience = (String) value;
            ie.editStory(experience);
        }
        if (columnIndex == 4) {
            Tags oneTag = ie.getTags();
            String oneTagString = oneTag.getOneTag(0);
            oneTag.removeTag(oneTagString);
            String tagString = (String) value;
            oneTag.addTag(tagString);
            ie.setTags(oneTag);
        }

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    // MODIFIES: this
    // EFFECTS: adds a new idiom entry into the collection shown in the table
    public void add(IdiomEntry idiomEntry) {
        int index = idiomCollection.getCollection().size();
        idiomCollection.addIdiomEntry(idiomEntry);
        fireTableRowsInserted(index, index);
    }

    public IdiomCollection getIdiomCollection() {
        return idiomCollection;
    }
}
