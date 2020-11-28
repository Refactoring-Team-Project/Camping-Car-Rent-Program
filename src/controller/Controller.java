package controller;

import common.AppManager;
import common.Constants;
import model.Model;
import view.MainView;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    }

    public void setMainView() {
        this._mainView = AppManager.getInstance().getView();
        //this._mainView.addButtonListener("", new listener);
    }

    abstract public void initModel();
    abstract public void initView();

    abstract public class ButtonListener implements ActionListener();

    public void dataInsert() {
        setModel();
        Model.insert(_mainView.getConn());
        thisView.fieldReset();
    }

    public void dataDelete() {
        if (_mainView.getCurRow() != -1) {
            dataModel.delete(_mainView.getConn(), thisView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
            thisView.fieldReset();
        } else {
            JOptionPane.showMessageDialog(null, "삭제할 데이터를 선택해 주세요.");
        }
    }

    public void dataUpdate() {
        if(_mainView.getCurRow() != -1) {
            setModel();
            dataModel.update(_mainView.getConn(), thisView.DBResult.getModel().getValueAt(_mainView.getCurRow(), 0));
            thisView.fieldReset();
        }

        else JOptionPane.showMessageDialog(null, "변경할 데이터를 선택해 주세요.");
    }

    public void setModel() {
        for(int i = 0; i< thisView.InputField.length; i++) {
            String value = thisView.InputField[i].getText();
            if(value.length() > 0) {
                setModelColumn(thisView.FieldName[i].getText(), value);
            }
            else throw new NullPointerException();
        }
    }
    abstract public void setModelColumn(String column, String value);

    public class MainButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            _mainView.changePanel(thisView);

            setDataTableColumnName();
            getDataTableValues();

            _mainView.revalidate();
            _mainView.repaint();
        }
    }

    public void setDataTableColumnName() {
        thisView.tableModel.setDataVector(null, column);
    }
    public void getDataTableValues() {
        ArrayList<Object[]> arr = dataModel.select(_mainView.getConn());
        for (int i = 1; i < arr.size(); i++) {
            thisView.tableModel.addRow(arr.get(i));
        }

    }
}
