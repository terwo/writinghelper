package ui;


import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.text.html.HTML;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

// Application to keep track of idioms to help with writing
// Referenced TellerApp
public class WritingHelper {
    private static final String JSON_STORE = "./data/writingHelper.json";
    private IdiomCollection helper;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // json parts from JsonSerializationDemo
    // EFFECTS: runs the writing helper
    public WritingHelper() throws FileNotFoundException {
        input = new Scanner(System.in);
        helper = new IdiomCollection();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runHelper();

    }

    // from TellerApp
    // MODIFIES: this
    // EFFECTS: processes the user's input
    private void runHelper() {
        boolean stillWriting = true;
        String command = null;

        init();

        while (stillWriting) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("x")) {
                stillWriting = false;
            } else {
                processCommand(command);
            }
        }

        printLog();
        System.out.println("\nCome back later!");
    }

    // from TellerApp
    // MODIFIES: this
    // EFFECTS:  processes the user's input
    private void processCommand(String command) {
        if (command.equals("m")) {
            makeEntry();
        } else if (command.equals("s")) {
            doSearch();
        } else if (command.equals("f")) {
            saveWritingHelper();
        } else if (command.equals("l")) {
            loadWritingHelper();
        } else {
            System.out.println("Please make a valid selection :(");
        }
    }

    // referenced TellerApp
    // MODIFIES: this
    // EFFECTS:  initializes the writing helper with a few idiom entries
    public void init() {
        helper = new IdiomCollection();
        Tags tagsHappiness = new Tags();
        tagsHappiness.addTag("happiness");
        Tags tagsBravery = new Tags();
        tagsBravery.addTag("bravery");
        Tags tagsNature = new Tags();
        tagsNature.addTag("nature");
        Tags tagsLove = new Tags();
        tagsLove.addTag("love");
        helper.addIdiomEntry(new IdiomEntry("笑門来福",
                "good fortune and happiness will come to the home of those who smile",
                "しょうもんらいふく", "From browsing the internet", tagsHappiness));
        helper.addIdiomEntry(new IdiomEntry("忠勇無双", "of peerless loyalty and bravery",
                "ちゅうゆうむそう", "", tagsBravery));
        helper.addIdiomEntry(new IdiomEntry("桜花爛漫","riot of cherry blossoms",
                "おうからんまん", "Would like to see cherry blossoms one day ...", tagsNature));
        helper.addIdiomEntry(new IdiomEntry("愛多憎至",
                "receiving too much love and grace will birth hatred and jealousy",
                "あいたぞうし", "", tagsLove));
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    // from TellerApp
    // EFFECTS: displays menu of options to user
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tm, to make an entry");
        System.out.println("\ts, to search by tag");
        System.out.println("\tf, to save writing helper to file");
        System.out.println("\tl, to load writing helper from file");
        System.out.println("\tor x, to leave");
    }

    // from JsonSerializationDemo
    // EFFECTS: saves the workroom to file
    private void saveWritingHelper() {
        try {
            jsonWriter.open();
            jsonWriter.write(helper);
            jsonWriter.close();
            System.out.println("Saved your writing helper to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // from JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWritingHelper() {
        try {
            helper = jsonReader.read();
            System.out.println("Loaded your writing helper from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS:  makes an idiom entry
    private void makeEntry() {
        System.out.println("Enter idiomatic expression");
        String name = input.next();
        System.out.println("Enter meaning");
        String meaning = input.next();
        System.out.println("Enter reading");
        String read = input.next();
        System.out.println("Enter any comments");
        String comments = input.next();
        System.out.println("Add one tag from:");
        for (String next : Tags.getIdentifiers()) {
            System.out.println(next);
        }
        String tag = input.next();
        Tags myTag = new Tags();
        myTag.addTag(tag);

        helper.addIdiomEntry(new IdiomEntry(name, meaning,
                read, comments, myTag));

        System.out.println("Entry added successfully!");

    }


    // EFFECTS: bring up list of idiom names that match with tag
    private void doSearch() {
        for (String t : Tags.getIdentifiers()) {
            System.out.println(t);
        }

        System.out.println("Enter the desired tag you would like to search idioms for");
        String inquiry = input.next();

        if (helper.searchByTags(inquiry).isEmpty()) {
            System.out.println("No matches found! Make some entries first");
        } else {
            for (String myIdiom : helper.searchByTags(inquiry)) {
                System.out.println(myIdiom);
            }
            System.out.println("Enter the name of the idiom you'd like to view in detail");
            String lookAt = input.next();
            presentDetails(lookAt);
            displayEntryOptions(helper.showDetails(lookAt));

        }
    }


    // EFFECTS: displays details of singular entry
    private void presentDetails(String lookAt) {
        System.out.println("Expression: ");
        System.out.println(helper.showDetails(lookAt).getIdiom());
        System.out.println("Meaning: ");
        System.out.println(helper.showDetails(lookAt).getMeaning());
        System.out.println("Reading: ");
        System.out.println(helper.showDetails(lookAt).getReading());
        System.out.println("Comments: ");
        System.out.println(helper.showDetails(lookAt).getComment());
        System.out.println("Tags: ");
        for (String myTag : helper.showDetails(lookAt).getTags().getTagStrings()) {
            System.out.println(myTag);
        }
    }


    // EFFECTS: displays user options when presented with an idiom entry
    private void displayEntryOptions(IdiomEntry ie) {
        System.out.println("Press 'a' to add a new tag");
        System.out.println("Press 'r' to remove a tag");
        System.out.println("Press 'e' to edit your comment");
        System.out.println("Press 'l' to go back to the home menu");
        String choose = input.next();
        if (choose.equals("a")) {
            doAddTag(ie);
        } else if (choose.equals("r")) {
            doRemoveTag(ie);
        } else if (choose.equals("e")) {
            doEditComment(ie);
        } else if (choose.equals("l")) {
            System.out.println("Going back to home menu!");
        }

    }


    // MODIFIES: this
    // EFFECTS: adds given tag to the tags field in idiom entry
    private void doAddTag(IdiomEntry ie) {
        System.out.println("Add one of these tags:");
        for (String t : Tags.getIdentifiers()) {
            System.out.println(t);
        }
        String whatToAdd = input.next();
        ie.getTags().addTag(whatToAdd);
        System.out.println("Successfully added new tag!");
    }


    // MODIFIES: this
    // EFFECTS: removes tag from the tags field in idiom entry
    private void doRemoveTag(IdiomEntry ie) {
        System.out.println("Remove one of these tags");
        for (String t : ie.getTags().getTagStrings()) {
            System.out.println(t);
        }
        String whatToRemove = input.next();
        ie.getTags().removeTag(whatToRemove);
        System.out.println("Miraculously removed a nasty tag :)");
    }


    // MODIFIES: this
    // EFFECTS: update the entry's comments field
    private void doEditComment(IdiomEntry ie) {
        System.out.println("How would you like to edit your comment?");
        System.out.println(ie.getComment());
        String newComment = input.next();
        ie.editStory(newComment);
        System.out.println("Let's keep track of meaningful experiences :)");
    }


    // EFFECTS: prints the events logged onto console
    public void printLog() {
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.toString());
        }

    }


}
