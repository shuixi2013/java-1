package by.jdbc.dbutils.dao;

import java.util.List;

public class EmployeeDAO extends BaseDAO {
	/**
	 * searchModel的属性,name,address等为查询参数
	 * 
	 * @param searchModel
	 * @return
	 */
	public List query(Employee searchModel) {
		String sql = "select * fromemployee where 1=1";

		// 如果雇员名字不为null,则将雇员名字加入where查询条件
		if (searchModel.getName() != null) {
			sql += "andemployee.name like ‘" + searchModel.getName() + "’ ";
		}
		return this.query(sql, Employee.class);
	}

	/**
	 * 修改雇员信息
	 * 
	 * @param emp
	 * @return
	 */
	public boolean edit(Employee emp) {
		String sql = "updat eemployee set "; // 注意: set加在外面

		// 如果name不为null,修改它的值到数据库
		if (emp.getName() != null) {
			sql += "employee.name=’" + emp.getName() + "’ ,";
		}

		// 如果address不为null,修改它的值到数据库
		if (emp.getAddress() != null) {
			sql += "employee.address=’" + emp.getAddress() + "’, ";
		}

		sql = sql.substring(0, sql.length() - 1); // 去掉最后一个","
		sql += "whereemployee.id=" + emp.getId();
		return this.update(sql);
	}

	/**
	 * 根据雇员ID删除雇员
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		String sql = "delete fromemployee where id =" + id;
		return this.update(sql);
	}

	/**
	 * 根据雇员ID查找雇员
	 * 
	 * @param id
	 * @return
	 */
	public Employee get(int id) {
		String sql = "select * fromemployee where id=" + id;
		return (Employee) this.get(sql, Employee.class);
	}
}

// Employee:

class Employee {
	private int id;
	private String name;
	private String address;

	/**
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param string
	 */
	public void setAddress(String string) {
		address = string;
	}

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

}

/*
 * 
 * 
 * Employeeemp=new Employee();
 * 
 * emp.setName("小李");
 * 
 * emp.setAddress("南宁市阳光新城");
 * 
 * EmployeeDAOdao=new EmployeeDAO();
 * 
 * dao.save(emp);
 * 
 * 使用代码大概是这样的
 */
