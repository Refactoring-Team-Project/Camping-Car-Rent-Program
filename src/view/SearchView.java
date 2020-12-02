package view;

import javax.swing.JOptionPane;

import common.AppManager;

public class SearchView extends View{

	public SearchView() {
		AppManager.getInstance().setSearchView(this);
	}

	@Override
	public void initUpdateDataPanel() {}

	@Override
	public void initButtonPanel() {}

	@Override
	public void setInputFieldName() { }

	@Override
	public void setButtons() { }

	public String searchInput(String str) {
		return JOptionPane.showInputDialog(str);
	}
}