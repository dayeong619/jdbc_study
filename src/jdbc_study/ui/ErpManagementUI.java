package jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErpManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnList;
	private JButton btnDelete;

	
	public ErpManagementUI() {
		initComponents();
	}
	private void initComponents() {
		setTitle("부서관리메뉴");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 96);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 5, 10, 0));
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		contentPane.add(btnAdd);
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(this);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(this);
		contentPane.add(btnDelete);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		contentPane.add(btnSearch);
		
		btnList = new JButton("목록");
		btnList.addActionListener(this);
		contentPane.add(btnList);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnDelete) {
			actionPerformedBtnDelete(arg0);
		}
		if (arg0.getSource() == btnList) {
			actionPerformedBtnList(arg0);
		}
		if (arg0.getSource() == btnUpdate) {
			actionPerformedBtnUpdate(arg0);
		}
		if (arg0.getSource() == btnAdd) {
			actionPerformedBtnAdd(arg0);
		}
		if (arg0.getSource() == btnSearch) {
			actionPerformedBtnSearch(arg0);
		}
	}
	protected void actionPerformedBtnSearch(ActionEvent arg0) {
		
	}
	protected void actionPerformedBtnAdd(ActionEvent arg0) {
		DepartmentUI frame = new DepartmentUI();
		frame.setVisible(true);
	}
	
	protected void actionPerformedBtnUpdate(ActionEvent arg0) {	
		UpdateUI frame = new UpdateUI();
		frame.setVisible(true);					
	}
	
	protected void actionPerformedBtnList(ActionEvent arg0) {
		
	}
	protected void actionPerformedBtnDelete(ActionEvent arg0) {
		DeleteUI frame = new DeleteUI();
		frame.setVisible(true);				
	}
}



























