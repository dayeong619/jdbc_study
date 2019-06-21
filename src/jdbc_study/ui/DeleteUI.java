package jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dto.Department;
import jdbc_study.ui.content.PanelDepartment;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DeleteUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnClear;
	private JButton btnDelete;
	private PanelDepartment pContent;
	private DepartmentDao dao;
	public DeleteUI() {
		initComponents();
	}
	private void initComponents() {
		setTitle("부서관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pContent = new PanelDepartment();
		contentPane.add(pContent, BorderLayout.NORTH);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn, BorderLayout.SOUTH);
		
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(this);
		pBtn.add(btnDelete);
		
		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtn.add(btnClear);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDelete) {
			actionPerformedBtnDelete(e);
		}
		if (e.getSource() == btnClear) {
			actionPerformedBtnClear(e);
		}
	}
	protected void actionPerformedBtnClear(ActionEvent e) {
		pContent.ClearTextField();
		
	}
	protected void actionPerformedBtnDelete(ActionEvent e) {
		
		Department newDept = pContent.getDepartment();
		int res;
		try {
			res = dao.deleteDepartment(newDept);
			if (res != -1) {
				JOptionPane.showMessageDialog(null, String.format("%s가 삭제되었습니다", newDept.getDeptName()));
				pContent.ClearTextField();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}











