package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the IdiomEntry class
class IdiomEntryTest {
    private IdiomEntry ie;

    @BeforeEach
    void setup() {
        Tags tags = new Tags();
        ie = new IdiomEntry("運否天賦", "leaving to chance",
                "うんぷてんぷ", "", tags);
    }

    @Test
    void testConstructor() {
        assertEquals("運否天賦", ie.getIdiom());
        assertEquals("leaving to chance", ie.getMeaning());
        assertEquals("うんぷてんぷ", ie.getReading());
        assertEquals("", ie.getComment());
        assertTrue(ie.getTags().isEmpty());
    }

    @Test
    void testEditEmptyComment() {
        assertEquals("", ie.getComment());
        ie.editStory("Went to beach");
        assertEquals("Went to beach", ie.getComment());

    }

    @Test
    void testEditCommentAlreadySomethingThere() {
        ie.editStory("Had fun");
        ie.editStory("Had fun and made food");
        assertEquals("Had fun and made food", ie.getComment());

    }


}