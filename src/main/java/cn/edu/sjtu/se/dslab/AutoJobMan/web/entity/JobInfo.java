package cn.edu.sjtu.se.dslab.AutoJobMan.web.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "jobinfo")
public class JobInfo implements Serializable {
	public JobInfo() {
	}

	private static final long serialVersionUID = 12L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "staffid")
	private Staff staff;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "starttime")
	private  Calendar startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "endtime")
	private Calendar  endTime;

/*	@Column(name = "jobNum", columnDefinition = "text")
	private String jobNum;*/
	
	@Column(name = "brief", columnDefinition = "text")
	private String brief;

	@Column(name = "improvement", columnDefinition = "text")
	private String improvement;

	@Column(name = "problem", columnDefinition = "text")
	private String problem;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE }, mappedBy = "info", fetch = FetchType.EAGER)
	@Fetch( FetchMode.SUBSELECT)
	private Set<PhotoInfo> infos;
	

	@ManyToOne(cascade={ CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name = "wksiteid")
	private WorkSite site;

	@Column(name = "progress")
    private int progress;
	
	public WorkSite getSite() {  
		return site;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public void setSite(WorkSite site) {
		this.site = site;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	
	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getImprovement() {
		return improvement;
	}

	public void setImprovement(String improvement) {
		this.improvement = improvement;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public Set<PhotoInfo> getInfos() {
		return infos;
	}

	public void setInfos(Set<PhotoInfo> infos) {
		this.infos = infos;
	}
	
	

}
