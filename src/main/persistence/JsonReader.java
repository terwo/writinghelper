package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// from JsonSerializationDemo
// Represents a reader that reads IdiomCollection from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads IdiomCollection from file and returns it;
    // throws IOException if an error occurs reading data from file
    public IdiomCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseIdiomCollection(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses IdiomCollection from JSON object and returns it
    private IdiomCollection parseIdiomCollection(JSONObject jsonObject) {
        IdiomCollection ic = new IdiomCollection();
        addIdiomEntries(ic, jsonObject);
        return ic;
    }

    // MODIFIES: ic
    // EFFECTS: parses entries from JSON object and adds them to IdiomCollection
    private void addIdiomEntries(IdiomCollection ic, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("idioms");
        for (Object json : jsonArray) {
            JSONObject nextIdiomEntry = (JSONObject) json;
            addIdiomEntry(ic, nextIdiomEntry);
        }
    }

    // MODIFIES: ic
    // EFFECTS: parses idiomEntry from JSON object and adds it to IdiomCollection
    private void addIdiomEntry(IdiomCollection ic, JSONObject jsonObject) {
        String idiom = jsonObject.getString("idiom");
        String meaning = jsonObject.getString("meaning");
        String reading = jsonObject.getString("reading");
        String comment = jsonObject.getString("comment");
        JSONObject tempArray = jsonObject.getJSONObject("tags");
        JSONArray tagsJson = tempArray.getJSONArray("tags");
        Tags tags = new Tags();
        for (int i = 0; i < tagsJson.length(); i++) {
            tags.addTag(tagsJson.getString(i));
        }
        IdiomEntry ie = new IdiomEntry(idiom, meaning, reading, comment, tags);
        ic.addIdiomEntry(ie);
    }
}

