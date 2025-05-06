package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Search extends JFrame {
    private JPanel panel;
    private JLabel messageLabel;
    private JTextField titleTextField;
    private JButton searchButton;
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 200;
    private BookManager bookManager;

    public Search(BookManager manager) {
        bookManager = manager;

        setTitle("Search Books");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildPanel();
        add(panel);
        setVisible(true);
    }

    private void buildPanel() {
        messageLabel = new JLabel("Enter a book title:");
        titleTextField = new JTextField(30);
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ButtonListener());

        panel = new JPanel();
        panel.add(messageLabel);
        panel.add(titleTextField);
        panel.add(searchButton);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String title = titleTextField.getText();
            Book book = bookManager.searchBooks(title);

            if (book == null) {
                JOptionPane.showMessageDialog(null, "Book not found.");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Book found:\nTitle: " + book.getBookTitle() +
                                "\nPages: " + book.getNumberOfPages());
            }
        }
    }
}
