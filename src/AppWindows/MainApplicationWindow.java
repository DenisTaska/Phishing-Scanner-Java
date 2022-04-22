package AppWindows;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The type Main application window.
 */
public class MainApplicationWindow extends JFrame {

    private JTextArea textArea1;
    private JTextArea textArea2;
    private JPanel mainPanel;
    /**
     * The Working area.
     */
    static JTextArea workingArea;
    /**
     * The Lighter.
     */
    Highlighter lighter;


    /**
     * Instantiates a new Main application window.
     *
     * @throws FileNotFoundException the file not found exception
     * @throws BadLocationException  the bad location exception
     */
    public MainApplicationWindow() throws FileNotFoundException, BadLocationException {
        initialize();
    }

    /**
     * Initialize.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public void initialize() throws FileNotFoundException {

        setContentPane(mainPanel);
        setTitle("Phishing Scanner Application");
        setSize(1180, 985);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        workingArea = textArea1;
        lighter = workingArea.getHighlighter();
        workingArea.setHighlighter(lighter);

        File file = new File("MyPhishingEmail.txt");
        Scanner sc = new Scanner(file);
        String fileContent = "";

        while (sc.hasNextLine()) {
            fileContent = fileContent.concat(sc.nextLine() + "\n");
        }
        textArea1.setText(fileContent);
    }

    /**
     * Print text field results.
     *
     * @param text the text
     */
    public void print_text_field_results(String text) {
        textArea2.setText(text);
    }

    /**
     * Find word risk 1.
     *
     * @param word the word
     * @throws BadLocationException the bad location exception
     */
//Green Highlight
    public void findWordRisk1(String word) throws BadLocationException {
        String text = workingArea.getText().toLowerCase();
        int index = text.indexOf(word);
        if (index >= 0) { //check if found
            while (index >= 0) {
                lighter.addHighlight(index, index + word.length(), new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN));
                index = text.indexOf(word, index + 1);
            }
        }
    }

    /**
     * Find word risk 2.
     *
     * @param word the word
     * @throws BadLocationException the bad location exception
     */
//Yellow Highlight
    public void findWordRisk2(String word) throws BadLocationException {
        String text = workingArea.getText().toLowerCase();
        int index = text.indexOf(word);
        if (index >= 0) { //check if found
            while (index >= 0) {
                lighter.addHighlight(index, index + word.length(), new DefaultHighlighter.DefaultHighlightPainter(Color.ORANGE));
                index = text.indexOf(word, index + 1);
            }
        }
    }

    /**
     * Find word risk 3.
     *
     * @param word the word
     * @throws BadLocationException the bad location exception
     */
//Red Highlight
    public void findWordRisk3(String word) throws BadLocationException {
        String text = workingArea.getText().toLowerCase();
        int index = text.indexOf(word);
        if (index >= 0) { //check if found
            while (index >= 0) {
                lighter.addHighlight(index, index + word.length(), new DefaultHighlighter.DefaultHighlightPainter(Color.RED));
                index = text.indexOf(word, index + 1);
            }
        }
    }
}
