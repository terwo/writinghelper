package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents the list of tags one can add to their idiom entry
public class Tags implements Writable {

    protected static List<String> identifiers; // the static list of tag identifiers

    static {
        identifiers = new ArrayList();
        identifiers.add("happiness");
        identifiers.add("sorrow");
        identifiers.add("fortune");
        identifiers.add("anger");
        identifiers.add("peace");
        identifiers.add("bravery");
        identifiers.add("diligence");
        identifiers.add("love");
        identifiers.add("nature");
    }

    public static List<String> getIdentifiers() {
        return identifiers;
    }

    // END STATIC


    private List<String> tagStrings; // the list of tags in string form in a single idiom entry

    // EFFECTS: constructs a list of tags
    public Tags() {
        tagStrings = new ArrayList<>();
    }

    // EFFECTS: gets tagStrings field
    public List<String> getTagStrings() {
        return tagStrings;
    }

    // EFFECTS: gets tag in n position in tagStrings
    public String getOneTag(int n) {
        return tagStrings.get(n);
    }

    // REQUIRES: string is valid tag from identifiers
    // MODIFIES: this
    // EFFECTS:  adds tag to tagStrings
    public List<String> addTag(String tag) {
        tagStrings.add(tag);
        EventLog.getInstance().logEvent(new Event("Created new tag " + tag));
        return tagStrings;
    }

    // REQUIRES: string is tag in tagStrings, and tagStrings is not already empty
    // MODIFIES: this
    // EFFECTS:  removes tag from tagStrings
    public List<String> removeTag(String tag) {
        tagStrings.remove(tag);
        EventLog.getInstance().logEvent(new Event("Removed tag " + tag));
        return tagStrings;
    }

    // from IncidentQueue Lab5
    // EFFECTS: returns true if no current tags in tagStrings, false otherwise
    public boolean isEmpty() {
        return tagStrings.isEmpty();
    }

    // from IncidentQueue Lab5
    // EFFECTS: returns number of tags currently applied to tagStrings
    public int length() {
        return tagStrings.size();
    }

    // EFFECTS: returns true if given tag is in tagStrings
    public boolean contains(String t) {
        return tagStrings.contains(t);
    }

    // from JsonSerializationDemo
    // EFFECTS: returns these tags as a JSON object
    @Override
    public JSONObject toJson() {
        JSONArray jsonArray = new JSONArray();
        for (String next : tagStrings) {
            jsonArray.put(next);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tags", jsonArray);
        return jsonObject;
    }


}
