package org.example.components;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/
// 单词类，包含单词本身，词性和释义
public class Word {
    public String word;
    public String partOfSpeech;
    public String definition;

    public Word(String word, String partOfSpeech, String definition) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.definition = definition;
    }

    // toString方法用于决定单词的展示效果
    public String toString() {
        return word + " (" + partOfSpeech + ") - " + definition;
    }

    // 对应的get和set方法
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
