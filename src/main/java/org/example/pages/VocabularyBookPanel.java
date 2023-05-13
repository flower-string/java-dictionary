package org.example.pages;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import org.example.components.WordDialog;
import org.example.tools.BookHandler;
import org.example.components.Word;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class VocabularyBookPanel extends JPanel {
    private JTextField newWordField;
    private JTextField newPartOfSpeechField;
    private JTextField newDefinitionField;
    private JPanel wordListArea;
    // 用于展示单词
    private JComboBox<String> bookList;
    // 映射，用来存单词本和单词
    private final HashMap<String, ArrayList<Word>> books;
    private final String username;
    private final BookHandler bookHandler;

    public VocabularyBookPanel(String username) {
        this.username = username;
        setLayout(new BorderLayout());

        books = new HashMap<>();
        bookHandler = new BookHandler(books);
        bookHandler.loadBooks(username);
        display();
        updateWordList();
    }

    private void updateWordList() {
        wordListArea.removeAll();
        wordListArea.setLayout(new BorderLayout());
        JPanel wordPanelContainer = new JPanel();
        wordPanelContainer.setLayout(new BoxLayout(wordPanelContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(wordPanelContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        wordListArea.add(scrollPane, BorderLayout.CENTER);

        String selectedBook = (String) bookList.getSelectedItem();
        if (selectedBook != null) {
            for (Word word : books.get(selectedBook)) {
                JPanel wordPanel = new JPanel();
                int wordPanelHeight = 50;
                wordPanel.setMinimumSize(new Dimension(wordListArea.getWidth(), wordPanelHeight));
                wordPanel.setMaximumSize(new Dimension(wordListArea.getWidth(), wordPanelHeight));
                wordPanel.setPreferredSize(new Dimension(wordListArea.getWidth(), wordPanelHeight));
                wordPanel.setLayout(new BorderLayout());
                wordPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
                wordPanel.add(new JLabel(word.toString()), BorderLayout.WEST);


                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

                JButton editButton = new JButton("修改");
                editButton.addActionListener(e -> {
                    WordDialog wordDialog = new WordDialog("修改单词", word);
                    wordDialog.addOKListener(e1 -> {
                        // 更新单词信息
                        word.setWord(wordDialog.wordField.getText());
                        word.setPartOfSpeech(wordDialog.partOfSpeechField.getText());
                        word.setDefinition(wordDialog.definitionField.getText());

                        // 关闭对话框并更新单词列表
                        wordDialog.dispose();
                        bookHandler.saveBooks(username);
                        updateWordList();
                    });
                });
                buttonPanel.add(editButton);

                JButton deleteButton = new JButton("删除");
                deleteButton.addActionListener(e -> {
                    books.get(selectedBook).remove(word);
                    updateWordList();
                });
                buttonPanel.add(deleteButton);

                wordPanel.add(buttonPanel, BorderLayout.EAST);
                wordPanelContainer.add(wordPanel);
            }
        }
        revalidate();
        repaint();
    }

    private void display() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JButton themeButton = new JButton("切换主题");
        topPanel.add(themeButton);
        themeButton.addActionListener(e -> {
            try {
                if (FlatLaf.isLafDark()) {
                    FlatLightLaf.install();
                } else {
                    FlatDarkLaf.install();
                }
                SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        bookList = new JComboBox<>();
        for (String bookName : books.keySet()) {
            bookList.addItem(bookName);
        }
        bookList.addActionListener(e -> updateWordList());
        topPanel.add(bookList);

        JButton newBookButton = new JButton("新建单词本");
        newBookButton.addActionListener(e -> {
            String newBook = JOptionPane.showInputDialog(VocabularyBookPanel.this.getTopLevelAncestor(), "输入新单词本名称");
            if (newBook != null && !newBook.isEmpty()) {
                if (books.containsKey(newBook)) {
                    JOptionPane.showMessageDialog(VocabularyBookPanel.this.getTopLevelAncestor(), "已存在同名单词本");
                } else {
                    books.put(newBook, new ArrayList<>());
                    bookList.addItem(newBook);
                    bookHandler.saveBooks(username);
                }
            }
        });
        topPanel.add(newBookButton);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel("单词"));
        newWordField = new JTextField(10);
        inputPanel.add(newWordField);
        inputPanel.add(new JLabel("词性"));
        newPartOfSpeechField = new JTextField(10);
        inputPanel.add(newPartOfSpeechField);
        inputPanel.add(new JLabel("释义"));
        newDefinitionField = new JTextField(10);
        inputPanel.add(newDefinitionField);
        centerPanel.add(inputPanel, BorderLayout.NORTH);

        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("添加");
        addButton.addActionListener(e -> {
            String selectedBook = (String) bookList.getSelectedItem();
            if (selectedBook != null) {
                String newWord = newWordField.getText();
                String newPartOfSpeech = newPartOfSpeechField.getText();
                String newDefinition = newDefinitionField.getText();
                if (!newWord.isEmpty() && !newPartOfSpeech.isEmpty() && !newDefinition.isEmpty()) {
                    books.get(selectedBook).add(new Word(newWord, newPartOfSpeech, newDefinition));
                    bookHandler.saveBooks(username);
                    updateWordList();
                    newWordField.setText("");
                    newPartOfSpeechField.setText("");
                    newDefinitionField.setText("");
                }
            }
        });
        buttonPanel.add(addButton);

        wordListArea = new JPanel();
        wordListArea.setLayout(new BoxLayout(wordListArea, BoxLayout.Y_AXIS));

        wordListArea.setLayout(new BorderLayout());
        JPanel wordPanelContainer = new JPanel();
        wordPanelContainer.setLayout(new BoxLayout(wordPanelContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(wordPanelContainer);
        wordListArea.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(wordListArea);

        JButton searchButton = new JButton("查询");
        searchButton.addActionListener(e -> {
            String selectedBook = (String) bookList.getSelectedItem();
            if (selectedBook != null) {
                String searchWord = JOptionPane.showInputDialog(VocabularyBookPanel.this.getTopLevelAncestor(), "输入要查询的单词");
                if (searchWord != null && !searchWord.isEmpty()) {
                    boolean found = false;
                    ArrayList<Word> result = new ArrayList<>();
                    for (Word word : books.get(selectedBook)) {
                        if (word.word.equals(searchWord)) {
                            found = true;
                            result.add(word);
                        }
                    }
                    if (found) {
                        StringBuilder endStr = new StringBuilder();
                        for(Word word: result) {
                            endStr.append(word.toString()).append("\n");
                        }
                        JOptionPane.showMessageDialog(VocabularyBookPanel.this.getTopLevelAncestor(), "查询结果\n" + endStr);
                    } else {
                        JOptionPane.showMessageDialog(VocabularyBookPanel.this.getTopLevelAncestor(), "单词本中不包含该单词");
                    }
                }
            }
        });
        buttonPanel.add(searchButton);

        JButton gameButton = new JButton("小游戏");
        gameButton.addActionListener(e -> {
            new VocabularyGame(username, bookList.getSelectedItem().toString());
        });
        buttonPanel.add(gameButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
