package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a collection of idiom entries
public class IdiomCollection implements Writable {
    private List<IdiomEntry> collection; // collection of idiom entries


    // EFFECTS: constructs new idiom collection with no entries
    public IdiomCollection() {
        this.collection = new ArrayList();
    }


    // REQUIRES: idiom name is unique
    // MODIFIES: this
    // EFFECTS:  add new idiom entry to collection
    public List<IdiomEntry> addIdiomEntry(IdiomEntry i) {
        collection.add(i);
        EventLog.getInstance().logEvent(new Event("New idiom entry has been made"));
        return collection;
    }

    public List<IdiomEntry> getCollection() {
        return collection;
    }


    // REQUIRES: string is a valid tag
    // EFFECTS:  brings up list of idiom names that have matching tag
    public List<String> searchByTags(String t) {
        List<String> names = new ArrayList<>();
        for (IdiomEntry next : collection) {
            if (next.getTags().contains(t)) {
                names.add(next.getIdiom());
            }
        }
        EventLog.getInstance().logEvent(new Event("Searched for idioms with tag " + t));
        return names;
    }


    // REQUIRES: string matches one of idiom names in collection
    // EFFECTS:  bring up details of certain idiom for which given string matches idiom's name
    public IdiomEntry showDetails(String n) {
        for (IdiomEntry next : collection) {
            if (next.getIdiom().equals(n)) {
                return next;
            }
        }
        EventLog.getInstance().logEvent(new Event("Showed details for idiom " + n));
        return null;
    }

    // from IncidentQueue Lab5
    // EFFECTS: returns true if no current idiom entries in collection, false otherwise
    public boolean isEmpty() {
        return (0 == collection.size());
    }

    // from JsonSerializationDemo
    // returns this idiom collection as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("idioms", collectionToJson());
        return json;
    }

    // from JsonSerializationDemo
    // EFFECTS: returns entries in this idiom collection as a JSON array
    private JSONArray collectionToJson() {
        JSONArray jsonArray = new JSONArray();

        for (IdiomEntry ie : collection) {
            jsonArray.put(ie.toJson());
        }

        return jsonArray;
    }

}
