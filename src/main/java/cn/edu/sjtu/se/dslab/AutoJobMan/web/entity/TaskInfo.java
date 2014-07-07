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

/**
 * 任务单
 * @author sunke
 *
 */
@Entity
@Table(name = "taskinfo")
public class TaskInfo implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 21L;
	public TaskInfo(){};
	public TaskInfo(String num,Staff staff,WorkSite site,String path,Staff addStaff,Date addDate){
		setNum(num);
		setChargeStaff(staff);
		setInfo(site);
		setPath(path);
		setAddStaff(addStaff);
		setAddTime(addDate);
		setState(false);
		setDeleted(false);
	}
	/**
	 * 主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/**
	 * 任务单号
	 */
	@Column(name = "num", nullable = false)
	private String num;

	/**
	 * 任务单图片路径
	 */
	@Column(name = "path", nullable = false)
	private String path;

	/**
	 * 任务单添加时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "addtime")
	private Date addTime;

	/**
	 * 完工时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "finishtime")
	private Date finishTime;
	
	/**
	 * 开始时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "starttime")
	private Date startTime;
	/**
	 * 完工时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deadline")
	private Date deadline;
	/**
	 * 添加任务单员工
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "addstaffid")
	private Staff addStaff;
	
	/**
	 * 任务单负责人
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "chgstaffid")
	private Staff chargeStaff;
	/**
	 * 任务单完成状态
	 */
	@Column(name = "state", nullable = false)
	private boolean state;
	
	/**
	 * 对应项目
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "siteid")
	private WorkSite info;
	
	/**
	 * 评分
	 */
	@Column(name = "scorefinish", nullable = false)
	private int scoreFinish;
	
	@Column(name = "scorephoto", nullable = false)
	private int scorePhoto;
	
	@Column(name = "scoreconclusion", nullable = false)
	private int scoreConclusion;
	
	@Column(name = "deleted",columnDefinition = "bit")
	private boolean deleted;
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	
	

	public int getId() {
		return id;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Staff getAddStaff() {
		return addStaff;
	}

	public void setAddStaff(Staff addStaff) {
		this.addStaff = addStaff;
	}

	public Staff getChargeStaff() {
		return chargeStaff;
	}

	public void setChargeStaff(Staff chargeStaff) {
		this.chargeStaff = chargeStaff;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public WorkSite getInfo() {
		return info;
	}
	public void setInfo(WorkSite info) {
		this.info = info;
	}
	public int getScoreFinish() {
		return scoreFinish;
	}
	
	public void setScoreFinish(int scoreFinish) {
		this.scoreFinish = scoreFinish;
	}
	
	public int getScorePhoto() {
		return scorePhoto;
	}
	
	public void setScorePhoto(int scorePhoto) {
		this.scorePhoto = scorePhoto;
	}
	
	public int getScoreConclusion() {
		return scoreConclusion;
	}
	
	public void setScoreConclusion(int scoreConclusion) {
		this.scoreConclusion = scoreConclusion;
	}
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
}
