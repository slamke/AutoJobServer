package cn.edu.sjtu.se.dslab.AutoJobMan.web.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "wksite")
public class WorkSite implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5729632350448997016L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	/**
	 * 编号
	 */
	@Column(name = "num")
	private String num;
	/**
	 * 工地简称
	 */
	@Column(name = "sitename")
	private String sitename;
	

	@Column(name = "descr",columnDefinition = "text")
	private String description;

	/**
	 * 添加时间
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "time")
	private Date time;
	
	@OneToMany(mappedBy = "site", cascade = { CascadeType.REFRESH,
			CascadeType.REMOVE }, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<JobInfo> joinfos;
	
	@Column(name = "deleted",columnDefinition = "bit")
	private boolean deleted;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}



	public int getId() {
		return id;
	}



	public List<JobInfo> getJoinfos() {
		return joinfos;
	}



	public void setJoinfos(List<JobInfo> joinfos) {
		this.joinfos = joinfos;
	}



	public void setId(int id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	

	public String getSitename() {
		return sitename;
	}



	public void setSitename(String sitename) {
		this.sitename = sitename;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}




