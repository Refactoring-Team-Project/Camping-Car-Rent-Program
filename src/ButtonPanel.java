import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ButtonPanel extends JPanel {
   JButton btnInput, btnDelete, btnUpdate, btnReturn,btnRequest, btnRent;

   ButtonPanel() {
      btnInput = new JButton("입력");
      btnDelete = new JButton("삭제");
      btnUpdate = new JButton("변경");
      btnReturn = new JButton("반환");
      btnRent = new JButton("대여");
      btnRequest = new JButton("수리요청");
   }

   void update() {
      removeAll();
      add(btnInput);
      add(btnDelete);
      add(btnUpdate);
   }
   
   void carReturn() {
      removeAll();
      add(btnReturn);
   }
   
   void repairRequest() {
      removeAll();
      add(btnRequest);
   }
   void repairList() {
      removeAll();
      add(btnDelete);
      add(btnUpdate);
   }
   
   void carRent() {
      removeAll();
      add(btnRent);
   }
   
}