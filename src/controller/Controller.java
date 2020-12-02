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

    public void thisViewAddListener() {
        connectedView.addButtonListener(new thisViewButtonListener());
        connectedView.addMouseListener(new thisViewMouseListener());
    }
    /*** scrollpane tableModel에 보여질 columnName 설정* **/
    abstract public void setColumnName();

    /*** Model에 데이터 넘겨주기 ***/
    public void setModel() {
        for(int i = 0; i< connectedView.inputFields.length; i++) {
            String value = connectedView.inputFields[i].getText();
            if(value.length() > 0) {
                setModelColumn(connectedView.fieldName[i].getText(), value);
            }
            else throw new NullPointerException();
        }
    }

    /*** Model 각 column에 데이터 set ***/
    abstract public void setModelColumn(String column, String value);

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
    public class thisViewButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            thisViewButtonEvent(e);
        }
    }

    public void thisViewButtonEvent(ActionEvent e) {}

    public void inputButtonEvent() {
        setModel();
        setDataModel.insert(mainView.getConn());
        connectedView.fieldReset();
    }

    public void deleteButtonEvent() {
        if (mainView.getCurRow() != -1) {
            setDataModel.delete(mainView.getConn(), connectedView.DBResult.getModel().getValueAt(mainView.getCurRow(), 0));
            connectedView.fieldReset();
        } else {
            JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
        }
    }

    public void updateButtonEvent() {
        if(mainView.getCurRow() != -1) {
            setModel();
            setDataModel.update(mainView.getConn(), connectedView.DBResult.getModel().getValueAt(mainView.getCurRow(), 0));
            connectedView.fieldReset();
        }
    }

    /*** ThisView Mouse Listener ***/
    public class thisViewMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            mainView.setCurRow(connectedView.DBResult.getSelectedRow());
            mainView.setCurCol(connectedView.DBResult.getSelectedColumn());
            thisViewMouseEvent(e);
        }
    }
    public void thisViewMouseEvent(MouseEvent e) {}

    /*** 설정한 columnName으로 tableModel column set ***/
    public void setDataTableColumnName() {
        connectedView.tableModelOnScrollPane.setDataVector(null, columnName);
    }
    /*** tableModel에 Model에서 가져온 값 뿌려줌 ***/
    public void getDataTableValues() {
        ArrayList<Object[]> arr = getDataModel.select(mainView.getConn());
        for (int i = 0; i < arr.size(); i++) {
            connectedView.tableModelOnScrollPane.addRow(arr.get(i));
        }

    }
}
