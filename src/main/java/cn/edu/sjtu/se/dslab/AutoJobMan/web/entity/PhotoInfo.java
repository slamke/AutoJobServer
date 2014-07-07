package cn.edu.sjtu.se.dslab.AutoJobMan.web.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "photoinfo")
public class PhotoInfo implements Serializable {
	private static final long serialVersionUID = 13L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "path", nullable = false)
	private String path;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time")
	private Date time;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "jobinfoid")
	private JobInfo info; // 可以为空

	@Column(name = "type", nullable = true)
	private String type; // 五种类型（开工前找）


	@Column(name = "description", nullable = true,length=1000)
	private String description; 
	
	/**
	 * 自拍
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "staffid")
	private Staff staff;

	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public JobInfo getInfo() {
		return info;
	}

	public void setInfo(JobInfo info) {
		this.info = info;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
}
