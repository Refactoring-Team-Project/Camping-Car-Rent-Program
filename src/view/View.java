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
    public JPanel updatePanel, buttonPanel;
    public JButton[] buttons;
    public JLabel[] fieldName;
    public JTextField[] inputField;

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
        for (int i = 0; i < fieldName.length; i++) {
            updatePanel.add(fieldName[i]);
            updatePanel.add(inputField[i]);
        }
        add(updatePanel);
    }

    //여기서 fielidName, InputField new하고 for문으로 이름설정해주기
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

    //btnInput, Delete 같은거 여기서 new하고 buttons배열에 넣어주기
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
        for (JTextField inputField : inputField) {
            inputField.setText("");
        }
        repaint();
    }
}
