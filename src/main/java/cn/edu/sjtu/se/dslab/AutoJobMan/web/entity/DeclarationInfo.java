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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 员工申报信息
 * @author sunke
 *
 */
@Entity
@Table(name = "decinfo")
public class DeclarationInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	/**
	 * 申报人
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "staffid")
	private Staff staff;
	/**
	 * 申报日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time")
	private Date time;
	
	/**
	 * 申报标题（长度500）
	 */
	@Column(name = "title")
	private String title;
	
	/**
	 * 申报内容（长度1000）
	 */
	@Column(name = "content")
	private String content;
	
	/**
	 * 申报照片
	 */
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo")
	private PhotoInfo photo;
	
	/**
	 * 处理结果
	 */
	@Column(name = "result")
	private String result;
	
	/**
	 * 处理状态
	 */
	@Column(name = "state")
	private boolean state;

	@Column(name="cancel")
	private boolean cancel;
	
	public int getId() {
		return id;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PhotoInfo getPhoto() {
		return photo;
	}

	public void setPhoto(PhotoInfo photo) {
		this.photo = photo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
	
}
