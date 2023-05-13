package org.example.components;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/
public class Word {
    public String word;
    public String partOfSpeech;
    public String definition;

    public Word(String word, String partOfSpeech, String definition) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
    }

    public String toString() {
        return word + " (" + partOfSpeech + ") - " + definition;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}