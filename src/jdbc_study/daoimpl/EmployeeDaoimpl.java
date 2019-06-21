package jdbc_study.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jdbc_study.dao.EmployeeDao;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;
import jdbc_study.jdbc.MySQLjdbcUtill;

public class EmployeeDaoimpl implements EmployeeDao {
	static final Logger log = LogManager.getLogger();
	
	@Override
	public List<Employee> selectEmployeeByAll() throws SQLException {
		String sql = "SELECT empno, empname, title, manager, salary, dno, pic FROM employee";
		List<Employee> lists = new ArrayList<Employee>();;
		try(Connection conn = MySQLjdbcUtill.getConnection(); //연결 conn
			PreparedStatement pstmt = conn.prepareStatement(sql); //sql날리기 prepared
			ResultSet rs = pstmt.executeQuery()){
			
			log.trace(pstmt); //sql문이 제대로 나오는지 확인
			
			while(rs.next()) {
				lists.add(getEmployee(rs));
			}
		}
		return lists;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		return new Employee(rs.getInt("empno"), rs.getString("empname"), rs.getString("title"), new Employee(rs.getInt("manager")), 
			   rs.getInt("salary"), new Department(rs.getInt("dno")), rs.getBytes("pic"));		
	}

	@Override
	public Employee selectEmployeeByNo(Employee employee) throws SQLException { //검색하게해줌~
		log.trace("selectEmployeeByNo()"); 
		String sql = "SELECT empno, empname, title, manager, salary, dno, pic  FROM employee where empno=?";
		Employee newEm = null;
		
		try(Connection conn = MySQLjdbcUtill.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){		
			pstmt.setInt(1,	employee.getEmpNo());
			
			try(ResultSet rs = pstmt.executeQuery()){
				log.trace(pstmt); //sql문이 제대로 나오는지 확인
				
				while(rs.next()) {
					newEm = getEmployee(rs);
				}
					
			return newEm;
			}
				
				
		}		
		
	}

	@Override
	public int insertEmployee(Employee employee) throws SQLException {
		log.trace("insertEmployee()"); //로그호출을 위한 문장
		String sql = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?)";//한꺼번에 다 넣으니깐 생략함 (empno, empname, title, manager, salary, dno, pic)
		
		try(Connection conn = MySQLjdbcUtill.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1, employee.getEmpNo()); //첫번째자리에 empno 들어감
			pstmt.setString(2, employee.getEmpName());
			pstmt.setString(3, employee.getTitle());
			pstmt.setInt(4, employee.getManager().getEmpNo());
			pstmt.setInt(5, employee.getSalary());
			pstmt.setInt(6, employee.getDno().getDeptNo()); //type이 Department(employee.getDno())
			pstmt.setBytes(7, employee.getPic());
			System.out.println(pstmt);
			return 	pstmt.executeUpdate();
		}
	}

	@Override
	public int deleteEmployee(Employee employee) throws SQLException {
		log.trace("deleteEmployee()"); 
		String sql = "DELETE FROM employee WHERE empno=?";
		
		try(Connection conn = MySQLjdbcUtill.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, employee.getEmpNo());
			return pstmt.executeUpdate();
		}
		
	
	}

	@Override
	public int updateEmployee(Employee employee) throws SQLException {
		log.trace("updateEmployee()"); 
		String sql = "UPDATE employee SET empname=?, title=?, manager=?, salary=?, dno=?, pic=? WHERE empno=?";
		try(Connection conn = MySQLjdbcUtill.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, employee.getEmpName());
			pstmt.setString(2, employee.getTitle());
			pstmt.setInt(3, employee.getManager().getEmpNo());
			pstmt.setInt(4, employee.getSalary());
			pstmt.setInt(5, employee.getDno().getDeptNo());
			pstmt.setBytes(6, employee.getPic());
			pstmt.setInt(7, employee.getEmpNo()); 
			System.out.println(pstmt);
			return 	pstmt.executeUpdate();
		}
	}

}















