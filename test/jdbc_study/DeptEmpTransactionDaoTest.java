package jdbc_study;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.dao.DepartmentTransactionDao;
import jdbc_study.dao.EmployeeDao;
import jdbc_study.daoimpl.DepartmentDaoImpl;
import jdbc_study.daoimpl.DeptEmpTransactionDaoImpl;
import jdbc_study.daoimpl.EmployeeDaoImpl;
import jdbc_study.dto.Department;
import jdbc_study.dto.Employee;

public class DeptEmpTransactionDaoTest {
	static final Logger log = LogManager.getLogger();
	static DepartmentTransactionDao dao; //static은 static만 접근가능
	static DepartmentDao dao1;
	static EmployeeDao dao2;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println();
		log.trace("Start DeptEmpTransactionDaoTest");
		dao = new DeptEmpTransactionDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		log.trace("Start DeptEmpTransactionDaoTest");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		System.out.println();
	}
	
	@Test
	public void test0transactionInsertEmpAndDept() throws SQLException {
		dao1 = new DepartmentDaoImpl();
		dao2 = new EmployeeDaoImpl();
		dao2.deleteEmployee(new Employee(1005));
		dao1.deleteDepartment(new Department(7));
		
	}

	@Test
	public void test1transactionInsertEmpAndDept() throws SQLException{
		log.trace("test1");
		Department dept = new Department(1, "영업", 1);
		Employee emp = new Employee(1004, "서현진", "사원", new Employee(1033), 1500000, dept, null);
		
		int result = dao.trInsertEmpAndDept(emp, dept);
		log.trace(result);
		
		Assert.assertNotEquals(2, result);
		
	}
	
	@Test
	public void test2transactionInsertEmpAndDept() throws SQLException {
		log.trace("test2");
		Department dept = new Department(8, "akzp", 1);
		Employee emp = new Employee(1003, "조민희", "과장", new Employee(4377), 3000000, dept, null);
		
		int result = dao.trInsertEmpAndDept(emp, dept);
		log.trace(result);
		
		Assert.assertNotEquals(2, result);
		
	}

	@Test
	public void test3transactionInsertEmpAndDept() throws SQLException {
		log.trace("test3");
		Department dept = new Department(7, "김다영", 17);
		Employee emp = new Employee(1005, "이유영", "사원", new Employee(1003), 1500000, dept, null);
		
		int result = dao.trInsertEmpAndDept(emp, dept);
		log.trace(String.format("result %d", result));
		
		Assert.assertEquals(2, result);
		
		
	}

	

}















