package jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.daoimpl.DepartmentDaoImpl;
import jdbc_study.dto.Department;
import jdbc_study.ui.content.PanelDepartment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DepartmentUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private JButton btnClear;
	private PanelDepartment pContent;
	
	private DepartmentDao dao;
	
	public DepartmentUI() {
		dao = new DepartmentDaoImpl();
		initComponents();
	}
	private void initComponents() {
		setTitle("부서관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pContent = new PanelDepartment();
		contentPane.add(pContent, BorderLayout.NORTH);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn, BorderLayout.SOUTH);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtn.add(btnClear);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnClear) {
			actionPerformedBtnClear(arg0);
		}
		if (arg0.getSource() == btnAdd) {
			actionPerformedBtnAdd(arg0);
		}
	}
	protected void actionPerformedBtnAdd(ActionEvent arg0) {
//		1. pContent에서 getDepartment() 호출
//		2. dao에다 insertDepartment() 호출
//		3. 완료메세지 출력
		
		Department newDept = pContent.getDepartment();
		int res;
		try {
			res = dao.insertDepartment(newDept);
			if (res != -1) {
				JOptionPane.showMessageDialog(null, String.format("%s가 추가되었습니다", newDept.getDeptName()));
				pContent.ClearTextField();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	protected void actionPerformedBtnClear(ActionEvent arg0) {
		pContent.ClearTextField();
	}
}


















