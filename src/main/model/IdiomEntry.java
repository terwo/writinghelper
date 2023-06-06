package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents an idiom entry having an idiom, meaning, reading, comment, and tags
public class IdiomEntry implements Writable {
    private String idiom;        // what the idiom is
    private String meaning;      // idiom meaning in English
    private String reading;      // idiom reading
    private String comment;      // experience with the idiom
    private Tags tags;           // tags attached to idiom


    // EFFECTS: constructs new idiom entry with name, meaning, reading, comments, and tags
    public IdiomEntry(String name, String meaning, String read, String comments, Tags tags) {
        this.idiom = name;
        this.meaning = meaning;
        this.reading = read;
        this.comment = comments;
        this.tags = tags;
    }


    public String getIdiom() {
        return idiom;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getReading() {
        return reading;
    }

    public String getComment() {
        return comment;
    }

    public Tags getTags() {
        return tags;
    }

    public void setIdiom(String idiom) {
        this.idiom = idiom;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    // MODIFIES: this
    // EFFECTS:  edits comment to include new experience
    public String editStory(String experience) {
        comment = experience;
        EventLog.getInstance().logEvent(new Event("Comments have been edited"));
        return comment;
    }

    // from Thingy in JsonSerializationDemo
    // EFFECTS: returns idiom entry as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("idiom", idiom);
        json.put("meaning", meaning);
        json.put("reading", reading);
        json.put("comment", comment);
        json.put("tags", tags.toJson());
        return json;
    }

}
