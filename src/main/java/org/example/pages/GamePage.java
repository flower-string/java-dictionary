package org.example.pages;

/**
 * author 2107090411 刘敬超
 * version 1.0.0
 **/

import org.example.components.Word;
import org.example.utils.Cache;
import org.example.utils.SceneManager;
import org.example.utils.WordRender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

//class SoundUtils {
//    public static void playSound(String soundFile) {
//        try {
//            // Open an audio input stream.
//            URL url = SoundUtils.class.getResource("src\\main\\java\\org\\example\\" + soundFile);
//            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
//
//            // Get a sound clip resource.
//            Clip = AudioSystem.getClip();
//
//            // Open audio clip and load samples from the audio input stream.
//            clip.open(audioIn);
//            clip.start();
//        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//            e.printStackTrace();
//        }
//    }
//}

public class GamePage extends JPanel {
    private JLabel wordLabel;
    private JTextField inputField;
    private String currentWord;
    private List<Word> vocabulary;
    private int score;
    private final JLabel scoreLabel;

    public GamePage() {
        setLayout(new BorderLayout());
        score = 0;
        wordLabel = new JLabel();
        scoreLabel = new JLabel("Score: " + score);
    }

    @Override
    public void show() {
        this.start();
    }

    public void start() {
        try {
            // Load vocabulary from a file or database
            display();
            vocabulary = WordRender.readVocabulary(Cache.username, "cet");

            // Display a random word
            showRandomWord();
//            SoundUtils.playSound("bg.mp3");
        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
        }
    }

    private void showRandomWord() {
        // Check if wordLabel is null to avoid NullPointerException
        if (wordLabel != null) {
            try {
                // Avoid race conditions in multithreaded environments
                int index = (int) (Math.random() * vocabulary.size());
                currentWord = vocabulary.get(index).word;
                wordLabel.setText(vocabulary.get(index).definition);
            } catch (Exception e) {
                // Handle any exceptions that may occur
                e.printStackTrace();
            }
        }
    }

    private void checkSpelling() {
        String userInput = inputField.getText();

        // Ignore case sensitivity
        if (userInput.equalsIgnoreCase(currentWord)) {
            JOptionPane.showMessageDialog(this, "拼对了");
            score++;
        } else {
            JOptionPane.showMessageDialog(this, "拼错了");
        }

        // Display a new word
        showRandomWord();
        inputField.setText("");
        scoreLabel.setText("Score: " + score);
    }

    private void display() {
        // Create labels, text fields, and buttons
        JPanel above = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wordLabel = new JLabel();
        above.add(wordLabel);

        inputField = new JTextField(10);
        inputField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton checkButton = new JButton("Check");
        JButton nextButton = new JButton("下一个");
        JButton closeButton = new JButton("关闭");
        bottom.add(checkButton);
        bottom.add(nextButton);
        bottom.add(closeButton);

        // Create score panel
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel scoreLabel = new JLabel("Score:");
        JLabel scoreValueLabel = new JLabel(Integer.toString(score));
        scorePanel.add(scoreLabel);
        scorePanel.add(scoreValueLabel);

        checkButton.addActionListener(e -> {
//                SoundUtils.playSound("button_click.wav");
            checkSpelling();
        });

        nextButton.addActionListener(e -> {
//                SoundUtils.playSound("button_click.wav");
            showRandomWord();
        });

        closeButton.addActionListener(e -> SceneManager.getInstance().changeScene("main"));

        // Add components to the window
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 20, 20, 20);
        center.add(inputField, gbc);

        gbc.gridy++;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        center.add(scorePanel, gbc);

        add(above, BorderLayout.NORTH);
        add(center, BorderLayout.WEST);
        add(bottom, BorderLayout.SOUTH);

        // Add event listeners to the buttons
        checkButton.addActionListener(e -> checkSpelling());
        nextButton.addActionListener(e -> showRandomWord());

        // Update score label
        scoreValueLabel.setText(Integer.toString(score));
    }
}