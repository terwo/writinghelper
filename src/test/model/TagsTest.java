package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the Tags class
class TagsTest {
    private Tags testTags;

    @BeforeEach
    void setup() {
        testTags = new Tags();
    }

    @Test
    void testTagsConstructor() {
        assertEquals("happiness", testTags.identifiers.get(0));
        assertEquals("sadness", testTags.identifiers.get(1));
        assertEquals("chance", testTags.identifiers.get(2));
        assertEquals("anger", testTags.identifiers.get(3));
        assertEquals("inequality", testTags.identifiers.get(4));
        assertEquals("bravery", testTags.identifiers.get(5));
        assertEquals("diligence", testTags.identifiers.get(6));
        assertEquals("love", testTags.identifiers.get(7));
        assertEquals("nature", testTags.identifiers.get(8));
    }

    @Test
    void testGetIdentifiers() {
        assertTrue(Tags.getIdentifiers().contains("happiness"));
        assertFalse(Tags.getIdentifiers().contains("blame"));
    }

    @Test
    void addTagEmptyTags() {
        assertTrue(testTags.isEmpty());
        testTags.addTag("chance");
        assertEquals("chance", testTags.getOneTag(0));
    }

    @Test
    void addTagMultipleTags() {
        assertTrue(testTags.isEmpty());
        testTags.addTag("chance");
        assertEquals("chance", testTags.getOneTag(0));
        testTags.addTag("inequality");
        assertEquals("inequality", testTags.getOneTag(1));
        assertTrue(testTags.contains("chance"));
        assertFalse(testTags.isEmpty());
    }

    @Test
    void removeTagOnce() {
        testTags.addTag("happiness");
        testTags.addTag("chance");
        testTags.addTag("inequality");
        assertEquals(3, testTags.length());
        testTags.removeTag("happiness");
        assertEquals(2, testTags.length());
        assertEquals("chance", testTags.getOneTag(0));
        assertEquals("inequality", testTags.getOneTag(1));
    }

    @Test
    void removeTagMultipleOnes() {
        testTags.isEmpty();
        testTags.addTag("happiness");
        testTags.addTag("sadness");
        testTags.addTag("chance");
        testTags.addTag("inequality");
        assertEquals(4, testTags.length());
        testTags.removeTag("sadness");
        testTags.removeTag("happiness");
        assertEquals(2, testTags.length());
        assertEquals("chance", testTags.getOneTag(0));
        assertEquals("inequality", testTags.getOneTag(1));
        assertEquals(2,testTags.getTagStrings().size());
    }
}
