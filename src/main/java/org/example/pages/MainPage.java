package org.example.pages;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.example.components.ThemeComboBox;
import org.example.components.Word;
import org.example.components.WordDialog;
import org.example.components.WordPanel;
import org.example.utils.BookHandler;
import org.example.utils.Cache;
import org.example.utils.SceneManager;
import org.example.utils.YouDao;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/

// 主要页面
public class MainPage extends JPanel {
    // 用于展示单词
    private JPanel wordListArea;
    private JComboBox<String> bookList;

    public MainPage() {
        setLayout(new BorderLayout());
    }

    // 生命周期函数，在该页面被唤醒的时候调用
    public void show() {
        BookHandler.loadBooks(Cache.username);
        display();
        updateWordList();
    }

    public void updateWordList() {
        wordListArea.removeAll();
        wordListArea.setLayout(new BorderLayout());
        JPanel wordPanelContainer = new JPanel();
        wordPanelContainer.setLayout(new BoxLayout(wordPanelContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(wordPanelContainer, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        wordListArea.add(scrollPane, BorderLayout.CENTER);
        String selectedBook = (String) bookList.getSelectedItem();
        if (selectedBook != null) {
            for (Word word : Cache.books.get(selectedBook)) {
                WordPanel wordPanel = new WordPanel(wordListArea.getWidth(), word);
                wordPanelContainer.add(wordPanel);

                wordPanel.addBtn2(e -> {
                    Cache.books.get(selectedBook).remove(word);
                    updateWordList();
                });
            }
        }
        revalidate();
        repaint();
    }

    private void display() {
        // 设置主面板，布局方式为BorderLayout
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // 添加顶部菜单栏
        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(menu, BorderLayout.NORTH);
        // 添加查询栏
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        // 添加单词展示栏
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);

        ThemeComboBox themeComboBox = new ThemeComboBox();
        menu.add(themeComboBox);

        JButton addButton = new JButton("添加单词");
        addButton.addActionListener(e -> {
            String selectedBook = (String) bookList.getSelectedItem();

            if (selectedBook != null) {
                WordDialog dialog = new WordDialog("添加单词", new Word("", "", ""));
                dialog.addOKListener(e1 -> {
                    String newWord = dialog.wordField.getText();
                    String newPartOfSpeech = dialog.partOfSpeechField.getText();
                    String newDefinition = dialog.definitionField.getText();
                    if (!newWord.isEmpty() && !newPartOfSpeech.isEmpty() && !newDefinition.isEmpty()) {
                        Cache.books.get(selectedBook).add(new Word(newWord, newPartOfSpeech, newDefinition));
                        BookHandler.saveBooks(Cache.username);
                        updateWordList();
                        dialog.wordField.setText("");
                        dialog.partOfSpeechField.setText("");
                        dialog.definitionField.setText("");
                        dialog.dispose();
                    }
                });
            }
        });
        menu.add(addButton);

        JButton newBookButton = new JButton("新建单词本");
        newBookButton.addActionListener(e -> {
            String newBook = JOptionPane.showInputDialog(MainPage.this.getTopLevelAncestor(), "输入新单词本名称");
            if (newBook != null && !newBook.isEmpty()) {
                if (Cache.books.containsKey(newBook)) {
                    JOptionPane.showMessageDialog(MainPage.this.getTopLevelAncestor(), "已存在同名单词本");
                } else {
                    Cache.books.put(newBook, new ArrayList<>());
                    bookList.addItem(newBook);
                    BookHandler.saveBooks(Cache.username);
                }
            }
        });
        menu.add(newBookButton);

        JButton gameButton = new JButton("小游戏");
        gameButton.addActionListener(e -> SceneManager.getInstance().changeScene("game"));
        menu.add(gameButton);

        add(topPanel, BorderLayout.NORTH);

        bookList = new JComboBox<>();
        for (String bookName : Cache.books.keySet()) {
            bookList.addItem(bookName);
        }
        bookList.addActionListener(e -> updateWordList());
        buttonPanel.add(bookList);

        JTextField searchField = new JTextField(10);
        buttonPanel.add(searchField);

        JButton searchButton = new JButton("本地查询");
        searchButton.addActionListener(e -> {
            String selectedBook = (String) bookList.getSelectedItem();
            if (selectedBook != null) {
                String searchWord = searchField.getText();
                if (!searchWord.isEmpty()) {
                    boolean found = false;
                    ArrayList<Word> result = new ArrayList<>();
                    for (Word word : Cache.books.get(selectedBook)) {
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
                        JOptionPane.showMessageDialog(MainPage.this.getTopLevelAncestor(), "查询结果\n" + endStr);
                    } else {
                        JOptionPane.showMessageDialog(MainPage.this.getTopLevelAncestor(), "单词本中不包含该单词");
                    }
                }
            }
        });
        buttonPanel.add(searchButton);

        JButton webSearchButton = new JButton("联网查询");
        webSearchButton.addActionListener(e -> {
            try {
                if(searchField.getText().trim().isEmpty()) {
                    System.out.println("查询词不能为空");
                    return;
                }
                String result = YouDao.getInstance().translate(searchField.getText(), "英文", "中文");
                JSONObject jsonObject = JSONObject.parseObject(result);

                String query = jsonObject.getString("query");
                JSONArray translationArray = jsonObject.getJSONArray("translation");
                JSONArray basicExplainsArray = jsonObject.getJSONObject("basic").getJSONArray("explains");
                JSONArray webArray = jsonObject.getJSONArray("web");

                StringBuilder message = new StringBuilder();
                message.append("待查内容: ").append(query).append("\n\n");
                message.append("释义: \n");
                for (int i = 0; i < translationArray.size(); i++) {
                    message.append(i + 1).append(". ").append(translationArray.getString(i)).append("\n");
                }
                message.append("\n");
                message.append("基本释义: \n");
                for (int i = 0; i < basicExplainsArray.size(); i++) {
                    message.append(i + 1).append(". ").append(basicExplainsArray.getString(i)).append("\n");
                }
                message.append("\n");
                message.append("网络释义: \n");
                for (int i = 0; i < webArray.size(); i++) {
                    JSONObject webObj = webArray.getJSONObject(i);
                    JSONArray valueArray = webObj.getJSONArray("value");
                    String key = webObj.getString("key");
                    message.append(key).append(": ");
                    for (int j = 0; j < valueArray.size(); j++) {
                        message.append(valueArray.getString(j)).append("; ");
                    }
                    message.append("\n");
                }

                JOptionPane.showMessageDialog(null, message.toString(), "翻译结果", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException ex) {
                System.out.println("查询失败");
            }
        });
        buttonPanel.add(webSearchButton);
        wordListArea = new JPanel();
        wordListArea.setLayout(new BoxLayout(wordListArea, BoxLayout.Y_AXIS));

        wordListArea.setLayout(new BorderLayout());
        JPanel wordPanelContainer = new JPanel();
        wordPanelContainer.setLayout(new BoxLayout(wordPanelContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(wordPanelContainer);
        wordListArea.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(wordListArea);
    }
}
