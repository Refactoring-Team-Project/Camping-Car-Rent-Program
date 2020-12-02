package controller;

import common.AppManager;
import common.Constants;
import model.Model;
import view.MainView;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class Controller {
    MainView mainView;
    View connectedView;
    Model getDataModel;
    Model setDataModel;
    Object columnName[];

    public Controller() {
        setMainView();
        initModel();
        initConnectedView();
        setColumnName();
    }
    /*** MainView와 연결 ***/
    public void setMainView() {
        this.mainView = AppManager.getInstance().getView();
        //this.mainView.addButtonListener("", new MainButtonListener());
    }

    /*** View, Model init ***/
    abstract public void initModel();
    abstract public void initConnectedView();

    public void connectedViewAddListener() {
        connectedView.addButtonListener(new connectedViewButtonListener());
        connectedView.addMouseListener(new connectedViewMouseListener());
    }
    /*** scrollpane tableModel에 보여질 columnName 설정* **/
    abstract public void setColumnName();

    /*** Model에 데이터 넘겨주기 ***/
    public void setModel() {
        for(int i = 0; i< connectedView.inputFields.length; i++) {
            String value = connectedView.inputFields[i].getText();
            if(value.length() > 0) {
                setModelEachColumn(connectedView.fieldName[i].getText(), value);
            }
            else throw new NullPointerException();
        }
    }

    /*** Model 각 column에 데이터 set ***/
    abstract public void setModelEachColumn(String column, String value);

    /*** MainView ButtonListener ***/
    public class mainButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           mainViewButtonEvent(e);
        }
    }

    public void mainViewButtonEvent(ActionEvent e) {
        mainView.changePanel(connectedView);

        setDataTableColumnName();
        getDataTableValues();
    }

    /*** ThisView Button Listener ***/
    public class connectedViewButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            connectedViewButtonEvent(e);
        }
    }

    public void connectedViewButtonEvent(ActionEvent e) {}

    public void inputButtonEvent() {
        setModel();
        setDataModel.insert(mainView.getConnection());
        connectedView.fieldReset();
    }

    public void deleteButtonEvent() {
        if (mainView.getCurrentRow() != -1) {
            setDataModel.delete(mainView.getConnection(), connectedView.DBResult.getModel().getValueAt(mainView.getCurrentRow(), 0));
            connectedView.fieldReset();
        } else {
            JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
        }
    }

    public void updateButtonEvent() {
        if(mainView.getCurrentRow() != -1) {
            setModel();
            setDataModel.update(mainView.getConnection(), connectedView.DBResult.getModel().getValueAt(mainView.getCurrentRow(), 0));
            connectedView.fieldReset();
        }
    }

    /*** ThisView Mouse Listener ***/
    public class connectedViewMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            mainView.setCurrentRow(connectedView.DBResult.getSelectedRow());
            mainView.setCurrentCol(connectedView.DBResult.getSelectedColumn());
            connectedViewMouseEvent(e);
        }
    }
    public void connectedViewMouseEvent(MouseEvent e) {}

    /*** 설정한 columnName으로 tableModel column set ***/
    public void setDataTableColumnName() {
        connectedView.tableModelOnScrollPane.setDataVector(null, columnName);
    }
    /*** tableModel에 Model에서 가져온 값 뿌려줌 ***/
    public void getDataTableValues() {
        ArrayList<Object[]> arr = getDataModel.select(mainView.getConnection());
        for (int i = 0; i < arr.size(); i++) {
            connectedView.tableModelOnScrollPane.addRow(arr.get(i));
        }

    }
}
