package com.smtech.restaurant.entities;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;

@Entity
public class ZiDongDianCai {

	private Integer pID;

	public void setPID(Integer pID) {
		this.pID = pID;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PID")
	public Integer getPID() {
		return pID;
	}

	private Integer pIDTmp;

	public void setPIDTmp(Integer pIDTmp) {
		this.pIDTmp = pIDTmp;
	}

	@Basic
	@Column(name="PIDTmp")
	public Integer getPIDTmp() {
		return pIDTmp;
	}

	private String ziDongDianCaiID;

	public void setZiDongDianCaiID(String ziDongDianCaiID) {
		this.ziDongDianCaiID = ziDongDianCaiID;
	}

	@Basic
	@Column(name="ZiDongDianCaiID")
	public String getZiDongDianCaiID() {
		return ziDongDianCaiID;
	}

	private String ziDongDianCaiName;

	public void setZiDongDianCaiName(String ziDongDianCaiName) {
		this.ziDongDianCaiName = ziDongDianCaiName;
	}

	@Basic
	@Column(name="ZiDongDianCaiName")
	public String getZiDongDianCaiName() {
		return ziDongDianCaiName;
	}

	private BigDecimal shuLiang;

	public void setShuLiang(BigDecimal shuLiang) {
		this.shuLiang = shuLiang;
	}

	@Basic
	@Column(name="ShuLiang", precision=19, scale=10)
	public BigDecimal getShuLiang() {
		if(shuLiang == null){
			return BigDecimal.ZERO;
		}
		return shuLiang.setScale(10, BigDecimal.ROUND_HALF_EVEN);
		}
	private String danWei;

	public void setDanWei(String danWei) {
		this.danWei = danWei;
	}

	@Basic
	@Column(name="DanWei")
	public String getDanWei() {
		return danWei;
	}

	@Transient
	public String getDspName(){
		if (getZiDongDianCaiID() == null) {
			return getZiDongDianCaiName();
		}
		return String.format("(%s)%s", getZiDongDianCaiID(), getZiDongDianCaiName());
	}
	private Food cai;

	public void setCai(Food cai) {
		this.cai = cai;
	}

	@ManyToOne
	@JoinColumn(name="Cai")
	public Food getCai() {
		return cai;
	}

	private TableType zuoTaiLX;

	public void setZuoTaiLX(TableType zuoTaiLX) {
		this.zuoTaiLX = zuoTaiLX;
	}

	@ManyToOne
	@JoinColumn(name="ZuoTaiLX")
	public TableType getZuoTaiLX() {
		return zuoTaiLX;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ZiDongDianCai)) {
			return false;
		}
		Integer selfId = getPID();
		Integer otherId = ((ZiDongDianCai) other).getPID();
		if (selfId == null || otherId == null) {
			return false;
		}

		return selfId.equals(otherId);
	}

	@Override
	public int hashCode() {
		Integer selfId = getPID();
		if (selfId != null) {
			return selfId;
		} else {
			return super.hashCode();
		}
	}
}
