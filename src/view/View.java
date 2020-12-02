package view;

import common.AppManager;
import common.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

abstract public class View extends JPanel {
    public DefaultTableModel tableModel;
    public JTable DBResult;
    JScrollPane scrollPane;
    public JPanel updateDataPanel, buttonPanel;
    public JButton[] buttons;
    public JLabel[] fieldName;
    public JTextField[] inputFields;

    public View() {
        super.setLayout(new FlowLayout());
        setPreferredSize(new Dimension(780, 420));
        initScrollPane();
        initUpdateDataPanel();
        initButtonPanel();
    }

    public void initScrollPane() {
        tableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        DBResult = new JTable(tableModel);
        DBResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(DBResult);
        add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(780, 300));
    }

    public void initUpdateDataPanel() {
        updateDataPanel = new JPanel();
        updateDataPanel.setPreferredSize(new Dimension(780, 60));
        setInputFieldName();
        for (int i = 0; i < fieldName.length; i++) {
            updateDataPanel.add(fieldName[i]);
            updateDataPanel.add(inputFields[i]);
        }
        add(updateDataPanel);
    }

    abstract public void setInputFieldName();

    public void initButtonPanel() {
        buttonPanel = new JPanel();
        setButtons();
        for (int i = 0; i < buttons.length; i++) {
            buttonPanel.add(buttons[i]);
        }
        add(buttonPanel);
        buttonPanel.setPreferredSize(new Dimension(780, 50));
    }

    abstract public void setButtons();

    public void addButtonListener(ActionListener listener) {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(listener);
        }
    }
    public void addMouseListener(MouseListener listener) {
        DBResult.addMouseListener(listener);
    }

    public void fieldReset() {
        for (JTextField inputField : inputFields) {
            inputField.setText("");
        }
        repaint();
    }
}
