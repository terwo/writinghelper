package persistence;


import org.json.JSONObject;

// from Writable interface from JsonSerializationDemo
// Represents the
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();

}
