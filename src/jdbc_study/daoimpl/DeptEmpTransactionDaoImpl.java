package jdbc_study.daoimpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.Connection;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;
import jdbc_study.jdbc.MySQLjdbcUtill;

public class DeptEmpTransactionDaoImpl implements DepartmentDao {
	static final Logger log = LogManager.getLogger();
	
	
	@Override
	public int trInsertEmpAndDept(Employee emp, Department dept){
		String deptSql = "INSERT INTO department(deptno, deptname, floor) VALUES(?, ?, ?)";
		String empSql = "INSERT INTO employee(empno, empname, title, manager, salary, dno) VALUES(?, ?, ?, ?, ?, ?)";
		
		int res = 0;
		try(Connection conn = MySQLjdbcUtill.getConnection();){
			conn.setAutoCommit(false);
					
			try(PreparedStatement dPstmt = conn.prepareStatement(deptSql);
				PreparedStatement ePstmt = conn.prepareStatement(empSql)){
				dPstmt.setInt(1, dept.getDeptNo());
				dPstmt.setString(2, dept.getDeptName());
				dPstmt.setInt(3, dept.getFloor());
				log.trace(dPstmt);
				res += dPstmt.executeUpdate();
				ePstmt.setInt(1, emp.getEmpNo());
				ePstmt.setString(2, emp.getEmpName());
				ePstmt.setString(3, emp.getTitle());
				ePstmt.setInt(4, emp.getManager().getEmpNo()); //매니저 
				ePstmt.setInt(5, emp.getSalary());
				ePstmt.setInt(6, emp.getDno().getDeptNo());
				log.trace(ePstmt);
				res += ePstmt.executeUpdate();
				
				if (res == 2) {
					conn.commit();
					conn.setAutoCommit(true);
					log.trace("result = " + res + " commit()");
				}else {
					conn.rollback();
					conn.setAutoCommit(true);
					log.trace("result = " + res + " rollback()");
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}


	@Override
	public List<Department> selectDepartmentByAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Department selectDepartmentByNo(Department dept) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int insertDepartment(Department dept) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int deleteDepartment(Department dept) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int updateDepartment(Department dept) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


}
