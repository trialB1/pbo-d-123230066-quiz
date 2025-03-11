package quiz.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HalamanUtama extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JComboBox<String> divisionComboBox;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JButton saveButton;
    private JList<String> dataList;
    private DefaultListModel<String> listModel;
    private JCheckBox importExportCheckBox;
    private JButton exportButton;
    private JButton importButton;
    private JPanel importExportPanel;
    private String username;
    
    public HalamanUtama(String username) {
        this.username = username;
        setTitle("Input Member By " + username);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JLabel firstNameLabel = new JLabel("Nama Depan:");
        firstNameField = new JTextField(20);
        
        JLabel lastNameLabel = new JLabel("Nama Belakang:");
        lastNameField = new JTextField(20);
        
        JLabel divisionLabel = new JLabel("Divisi:");
        String[] divisions = {"IT", "HRD", "Finance", "Marketing"};
        divisionComboBox = new JComboBox<String>(divisions);
        
        JLabel genderLabel = new JLabel("Jenis Kelamin:");
        
        maleRadioButton = new JRadioButton("Laki-laki");
        femaleRadioButton = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        
        saveButton = new JButton("Simpan Data");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(null);
        
        firstNameLabel.setBounds(20, 20, 100, 25);
        firstNameField.setBounds(20, 40, 300, 25);
        
        lastNameLabel.setBounds(340, 20, 100, 25);
        lastNameField.setBounds(340, 40, 300, 25);
        
        divisionLabel.setBounds(20, 100, 100, 25);
        divisionComboBox.setBounds(130, 100, 200, 25);
        
        genderLabel.setBounds(20, 135, 100, 25);
        maleRadioButton.setBounds(130, 135, 100, 25);
        femaleRadioButton.setBounds(230, 135, 100, 25);
        
        saveButton.setBounds(130, 170, 150, 30);
        
        inputPanel.add(firstNameLabel);
        inputPanel.add(firstNameField);
        inputPanel.add(lastNameLabel);
        inputPanel.add(lastNameField);
        inputPanel.add(divisionLabel);
        inputPanel.add(divisionComboBox);
        inputPanel.add(genderLabel);
        inputPanel.add(maleRadioButton);
        inputPanel.add(femaleRadioButton);
        inputPanel.add(saveButton);
        
        inputPanel.setPreferredSize(new Dimension(700, 220));
        
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("List Data:"));
        
        listModel = new DefaultListModel<>();
        dataList = new JList<>(listModel);
        dataList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dataList.setVisibleRowCount(10);
        
        JScrollPane scrollPane = new JScrollPane(dataList);
        listPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        JPanel checkBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        importExportCheckBox = new JCheckBox("Import / Export");
        importExportCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                importExportPanel.setVisible(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
        checkBoxPanel.add(importExportCheckBox);
        
        importExportPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        exportButton = new JButton("Export Data");
        importButton = new JButton("Import Data");
        
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportData();
            }
        });
        
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importData();
            }
        });
        
        importExportPanel.add(exportButton);
        importExportPanel.add(importButton);
        importExportPanel.setVisible(false);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(checkBoxPanel, BorderLayout.NORTH);
        bottomPanel.add(importExportPanel, BorderLayout.SOUTH);
        
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    public HalamanUtama() {
        this("Unknown");
    }
    
    private void saveData() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String division = (String) divisionComboBox.getSelectedItem();
        
        if (firstName.isEmpty() || lastName.isEmpty() || 
            (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())) {
            
            JOptionPane.showMessageDialog(this, "Semua data harus diisi!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String gender = maleRadioButton.isSelected() ? "Laki-laki" : "Perempuan";
        
        String listEntry = firstName + " " + lastName + " | " + division + " | " + gender;
        listModel.addElement(listEntry);
        
        clearForm();
    }
    
    private void clearForm() {
        firstNameField.setText("");
        lastNameField.setText("");
        divisionComboBox.setSelectedIndex(0);
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
    }
    
    private void exportData() {
        try {
            FileWriter writer = new FileWriter("result.txt");
            for (int i = 0; i < listModel.getSize(); i++) {
                writer.write(listModel.getElementAt(i));
                writer.write("\n");
            }
            writer.close();
            JOptionPane.showMessageDialog(this, "Data berhasil diekspor ke result.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saat mengekspor data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void importData() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                String line;
                
                listModel.clear();
                
                while ((line = reader.readLine()) != null) {
                    listModel.addElement(line);
                }
                reader.close();
                JOptionPane.showMessageDialog(this, "Data berhasil diimpor");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saat mengimpor data: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}