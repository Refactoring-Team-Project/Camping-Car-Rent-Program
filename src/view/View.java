package view;

import common.AppManager;
import common.Constants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

abstract public class View extends JPanel {
    public DefaultTableModel tableModel;
    public JTable DBResult;
    JScrollPane scrollPane;
    public JPanel updatePanel, buttonPanel;
    public JButton[] buttons;
    public JLabel[] FieldName;
    public JTextField[] InputField;

    public View() {
        super.setLayout(new FlowLayout());
        setPreferredSize(new Dimension(780, 420));
        initScrollPane();
        initUpdatePanel();
        initButtonPanel();
        //자식 생성자에 appmanager연결 코드 넣어주기
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

    public void initUpdatePanel() {
        updatePanel = new JPanel();
        updatePanel.setPreferredSize(new Dimension(780, 60));
        setInputFiledName();
        for (int i = 0; i < FieldName.length; i++) {
            updatePanel.add(FieldName[i]);
            updatePanel.add(InputField[i]);
        }
        add(updatePanel);
    }

    abstract public void setInputFiledName();

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

    public void fieldReset() {
        for (JTextField inputField : InputField) {
            inputField.setText("");
        }
        repaint();
    }
}
