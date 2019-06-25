package jdbc_study.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import jdbc_study.dto.Department;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class DepartmentListUI extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	private List<Department> deptList;
	private JPopupMenu popupMenu; //마우스 오른쪽 눌렀을 때 팝업메뉴
	private JMenuItem mntmPopUpdate; //마우스 오른쪽 눌렀을 때 추가 
	private JMenuItem mntmPopDelete;
	
	private ErpManagementUI parent;
	
	public DepartmentListUI() {
		initComponents();
	}
	
	public void setDepartmentList(List<Department> deptList) {
		this.deptList = deptList;
	}

	private void initComponents() {
		setTitle("부서 목록");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "부서 목록", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		popupMenu = new JPopupMenu(); //오른쪽 마우스 눌렀을 때 팝업메뉴 생성

		mntmPopUpdate = new JMenuItem("수정"); //오른쪽 마우스 눌럿을 때 팝업메뉴 중 수정
		mntmPopUpdate.addActionListener(this);
		popupMenu.add(mntmPopUpdate);
		
		mntmPopDelete = new JMenuItem("삭제"); //오른쪽 마우스 눌럿을 때 팝업메뉴 중 삭제
		popupMenu.add(mntmPopDelete);
		
		table.setComponentPopupMenu(popupMenu); //테이블에 팝업메뉴 달기
		scrollPane.setComponentPopupMenu(popupMenu); //테이블 밖에서 팝업메뉴 달기
	}

	public void reloadData() {
		table.setModel(new DefaultTableModel(getRows(),getColumnNames()));
		
		// 부서번호, 부서명은 가운데 정렬
		tableCellAlignment(SwingConstants.CENTER, 0,1);
		// 위치(층)은 우측 정렬
		tableCellAlignment(SwingConstants.RIGHT, 2);	
		// 부서번호, 부서명, 위치 의 폭을 (100, 200, 70)으로 가능하면 설정 
		tableSetWidth(100, 200, 70);
	}

	private Object[][] getRows() {
		Object[][] rows = new Object[deptList.size()][];
		for(int i=0; i<deptList.size(); i++) {
			rows[i] = deptList.get(i).toArray();
		}
		return rows;
	}

	private String[] getColumnNames() {
		return new String[] {"부서번호", "부서명", "위치(층)"};
	}

	
	// 테이블 셀 내용의 정렬
	protected void tableCellAlignment(int align, int... idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);

		TableColumnModel model = table.getColumnModel();
		for (int i = 0; i < idx.length; i++) {
			model.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}

	// 테이블 셀의 폭 설정
	protected void tableSetWidth(int... width) {
		TableColumnModel cModel = table.getColumnModel();

		for (int i = 0; i < width.length; i++) {
			cModel.getColumn(i).setPreferredWidth(width[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int i = table.getSelectedRow();
		// JOptionPane.showMessageDialog(null, "i= "+i); 찍어볼 수 있음. 테이블선택하고 컨텐트페인선택했을때 -1,1,2 등 등
		
		if (table.getModel().getRowCount() == 0) {	// 부서정보가 존재하지 않을 경우. getModel은 현재 띄어진 데이터임. 
			parent.showDepartmentUI(); //부서추가를 띄우도록 
			return; 
		}
		if (i  < 0 || i > table.getModel().getRowCount()-1) { //선택하지 않은 경우. 
			JOptionPane.showMessageDialog(null, "선택된 부서가 없습니다.");
			return;
		}
		
		int deptNo = (int) table.getModel().getValueAt(i, 0);
		JOptionPane.showMessageDialog(null, "deptNo = " + deptNo);
		
		JOptionPane.showMessageDialog(null, deptList.indexOf(new Department(deptNo))); //못찾으면 1, 찾으면 0 (equals오버라이딩 deptNo만)
		JOptionPane.showMessageDialog(null, deptList.get(deptList.indexOf(new Department(deptNo)))); //찾은 내용을 투스트링호출해서 보여줌 (deptno=,dept=name,floor) 
		
		parent.showDepartmentUI(deptNo); 
	}

	public void setErpManagementUI(ErpManagementUI erpManagementUI) {
		this.parent = erpManagementUI;
	}
	
}
