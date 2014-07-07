package cn.edu.sjtu.se.dslab.AutoJobMan.web.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "staff")
public class Staff implements Serializable {
	public Staff() {
	}

	public Staff(String username, String password, String telephone,
			String realname, int authority) {
		this.username = username;
		this.password = password;
		this.tel = telephone;
		this.realname = realname;
		this.auth = authority;
		System.out.print("staff ok\n");
	}

	private static final long serialVersionUID = 11L;
	/* properties */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "telephone", nullable = true)
	private String tel;
	@Column(name = "realname", nullable = true)
	private String realname;

	@OneToMany(mappedBy = "staff", cascade = { CascadeType.REFRESH,
			CascadeType.REMOVE }, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<JobInfo> jobInfos;

	@Column(name = "auth", nullable = true)
	private int auth; // 0 1 
	
	/**
	 * 员工所在部门
	 */
	@Column(name = "department", nullable = true)
	private String department;
	
	@Column(name = "deleted", nullable = true)
	private boolean deleted;
	
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public List<JobInfo> getJobInfos() {
		return jobInfos;
	}

	public void setJobInfos(List<JobInfo> jobInfos) {
		this.jobInfos = jobInfos;
	}
}
