package jdbc_study;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_study.dao.DepartmentDao;
import jdbc_study.daoimpl.DepartmentDaoImpl;
import jdbc_study.dto.Department;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {
	static final Logger log = LogManager.getLogger();
	static DepartmentDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception { //테스트하면 먼저 실행되는 
		log.trace("setUpBeforeClass()");
		dao = new DepartmentDaoImpl();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception { //테스트 끝날 때 실행
		log.trace("setUpBeforeClass()");
		dao = null;
		
	}

	@Test
	public void testSelectDepartmentByAll() { //이 메소드를 테스트하겠따
		List <Department> lists = dao.selectDepartmentByAll();
		for(Department d : lists) {
			System.out.println(d);
		}
		Assert.assertNotEquals(0, lists.size()); //0과다르면 성공함
	}
	
	@Test
	public void testSelectDepartmentByNo() throws SQLException { //이 메소드를 테스트하겠따
		Department selDept = new Department();
		selDept.setDeptNo(1);
		Department department = dao.selectDepartmentByNo(selDept);
		log.trace(String.format("A selected Department %s has been inserted.", department));
		Assert.assertNotNull(department);
	}
	
	@Test
	public void test03InsertDepartment() throws SQLException {
		Department newDept = new Department(5, "마케팅", 40);
		int res = dao.insertDepartment(newDept);
		Assert.assertNotEquals(-1, res);	
	}
	
	@Test
	public void test04DeleteDepartment() throws SQLException {
		Department newDept = new Department(5);
		int res = dao.insertDepartment(newDept);
		Assert.assertNotEquals(-1, res);	
	}
	
	@Test
	public void test05UpdateDepartment() throws SQLException {
		Department newDept = new Department(5);
		int res = dao.insertDepartment(newDept);
		Assert.assertNotEquals(-1, res);	
	}
	
}














