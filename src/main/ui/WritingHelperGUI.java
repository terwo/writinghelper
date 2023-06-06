package ui;

import model.Event;
import model.EventLog;
import model.IdiomCollection;
import model.IdiomEntry;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Iterator;
import java.util.Vector;

// Represents the GUI version of the WritingHelper, with different functionality
public class WritingHelperGUI extends JFrame {
    private IdiomCollectionTableGUI myTable; // the table which displays the data
    private static final String JSON_STORE = "./data/writingHelper.json";
    private JsonWriter jsonWriter; // JSONWriter for the writing helper
    private JsonReader jsonReader; // JSONReader for the writing helper
    private EventLog eventLog;


    public WritingHelperGUI() {

        super("Writing Helper");
        eventLog = EventLog.getInstance();
        // from https://stackoverflow.com/questions/20998762/how-to-have-different-behavior-if
        // -the-user-close-a-java-swing-application-using
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {
                for (Event next : eventLog) {
                    System.out.println(next.toString());
                }
                System.exit(0);
            }
        });

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        // from https://stackoverflow.com/questions/1614772/how-to-change-jframe-icon
        File file = new File("./data/yamazakura-cherry-blossom-4.jpg");
        String path = file.getAbsolutePath();

        ImageIcon img = new ImageIcon(path);

        setIconImage(img.getImage());

        myTable = new IdiomCollectionTableGUI(this);
        myTable.setOpaque(true);
        setContentPane(myTable);

        pack();
        setVisible(true);
    }

    // EFFECTS: saves the idiom collection used in the table to file
    public void saveWritingHelperGUI() {
        try {
            jsonWriter.open();
            jsonWriter.write(myTable.model.getIdiomCollection());
            jsonWriter.close();
            System.out.println("Saved your writing helper to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // MODIFIES: MyIdiomCollectionTableModel
    // EFFECTS: loads idiom collection from file and then populates table
    public void loadWritingHelperGUI() {
        try {
            IdiomCollection ic = myTable.model.getIdiomCollection();
            ic = jsonReader.read();
            for (IdiomEntry next : ic.getCollection()) {
                myTable.model.add(next);
            }
            System.out.println("Loaded your writing helper from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public IdiomCollectionTableGUI getTable() {
        return myTable;
    }
}
