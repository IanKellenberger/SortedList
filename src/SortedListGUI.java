import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortedListGUI {
    private SortedList sortedList;
    private JFrame frame;
    private JTextField inputField;
    private JButton addButton, searchButton;
    private JTextArea outputArea;

    public SortedListGUI() {
        sortedList = new SortedList();

        frame = new JFrame("SortedList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        inputField = new JTextField(20);
        addButton = new JButton("Add");
        searchButton = new JButton("Search");
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Input:"));
        inputPanel.add(inputField);
        inputPanel.add(addButton);
        inputPanel.add(searchButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new AddButtonListener());
        searchButton.addActionListener(new SearchButtonListener());

        frame.pack();
        frame.setVisible(true);
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputField.getText().trim();
            if (!input.isEmpty()) {
                sortedList.add(input);
                updateOutput();
                inputField.setText("");
            }
        }
    }

    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputField.getText().trim();
            if (!input.isEmpty()) {
                int result = sortedList.binarySearch(input);
                if (result >= 0) {
                    outputArea.append("Found: " + input + " at index " + result + "\n");
                } else {
                    int insertPosition = -result - 1;
                    outputArea.append("Not Found. Insert at index " + insertPosition + "\n");
                }
                inputField.setText("");
            }
        }
    }

    private void updateOutput() {
        outputArea.setText("Current List:\n");
        for (String s : sortedList.getList()) {
            outputArea.append(s + "\n");
        }
    }

    public static void main(String[] args) {
        new SortedListGUI();
    }
}
