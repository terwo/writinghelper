package ui;

import java.io.FileNotFoundException;

// Runs the WritingHelper
// Referenced TellerApp and JsonSerializationDemo
public class Main {
    public static void main(String[] args) {
        try {
            new WritingHelper();
        } catch (FileNotFoundException fe) {
            System.out.println("Unable to run helper: file not found");
        }
    }
}
