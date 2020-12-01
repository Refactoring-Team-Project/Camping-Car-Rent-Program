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
    MainView _mainView;
    View thisView;
    Model dataModel;
    Object column[];

    public Controller() {
        setMainView();
        initModel();
        initView();
        setColumnName();
    }

    public void setMainView() {
        this._mainView = AppManager.getInstance().getView();
        //this._mainView.addButtonListener("", new MainButtonListener());
    }

    abstract public void initModel();
    abstract public void initView();
    abstract public void setColumnName();

    //abstract public class ButtonListener implements ActionListener {};

    public void setModel() {
        for(int i = 0; i< thisView.inputField.length; i++) {
            String value = thisView.inputField[i].getText();
            if(value.length() > 0) {
                setModelColumn(thisView.fieldName[i].getText(), value);
            }
            else throw new NullPointerException();
        }
    }
    abstract public void setModelColumn(String column, String value);

    public class mainMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            _mainView.setCurRow(thisView.DBResult.getSelectedRow());
            _mainView.setCurCol(thisView.DBResult.getSelectedColumn());
        }
    }

    public class mainButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           mainViewButtonEvent(e);
        }
    }

    public void mainViewButtonEvent(ActionEvent e) {
        _mainView.changePanel(thisView);

        setDataTableColumnName();
        getDataTableValues();

        _mainView.revalidate();
        _mainView.repaint();
    }

    public void setDataTableColumnName() {
        thisView.tableModel.setDataVector(null, column);
    }
    public void getDataTableValues() {
        ArrayList<Object[]> arr = dataModel.select(_mainView.getConn());
        for (int i = 0; i < arr.size(); i++) {
            thisView.tableModel.addRow(arr.get(i));
        }

    }
}
