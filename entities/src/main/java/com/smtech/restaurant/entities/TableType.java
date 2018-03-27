package com.smtech.restaurant.entities;

import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;

import com.smtech.restaurant.enums.OverTimeMode;
import org.hibernate.annotations.Cascade;

@Entity
public class TableType {

	private Long pID;

	public void setPID(Long pID) {
		this.pID = pID;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PID")
	public Long getPID() {
		return pID;
	}

	private Long pIDTmp;

	public void setPIDTmp(Long pIDTmp) {
		this.pIDTmp = pIDTmp;
	}

	@Basic
	@Column(name="PIDTmp", columnDefinition="BIGINT")
	public Long getPIDTmp() {
		return pIDTmp;
	}

	private String zuoTaiLeiXingID;

	public void setZuoTaiLeiXingID(String zuoTaiLeiXingID) {
		this.zuoTaiLeiXingID = zuoTaiLeiXingID;
	}

	@Basic
	@Column(name="ZuoTaiLeiXingID")
	public String getZuoTaiLeiXingID() {
		return zuoTaiLeiXingID;
	}

	private String zuoTaiLeiXingName;

	public void setZuoTaiLeiXingName(String zuoTaiLeiXingName) {
		this.zuoTaiLeiXingName = zuoTaiLeiXingName;
	}

	@Basic
	@Column(name="ZuoTaiLeiXingName")
	public String getZuoTaiLeiXingName() {
		return zuoTaiLeiXingName;
	}

	private BigDecimal fuWuFeiLv;

	public void setFuWuFeiLv(BigDecimal fuWuFeiLv) {
		this.fuWuFeiLv = fuWuFeiLv;
	}

	@Basic
	@Column(name="FuWuFeiLv", precision=19, scale=10)
	public BigDecimal getFuWuFeiLv() {
		if(fuWuFeiLv == null){
			return BigDecimal.ZERO;
		}
		return fuWuFeiLv.setScale(10, BigDecimal.ROUND_HALF_EVEN);
		}
	private BigDecimal zuiDiXiaoFei;

	public void setZuiDiXiaoFei(BigDecimal zuiDiXiaoFei) {
		this.zuiDiXiaoFei = zuiDiXiaoFei;
	}

	@Basic
	@Column(name="ZuiDiXiaoFei", precision=19, scale=10)
	public BigDecimal getZuiDiXiaoFei() {
		if(zuiDiXiaoFei == null){
			return BigDecimal.ZERO;
		}
		return zuiDiXiaoFei.setScale(10, BigDecimal.ROUND_HALF_EVEN);
		}
	private Long allowePeople;

	public void setAllowePeople(Long allowePeople) {
		this.allowePeople = allowePeople;
	}

	@Basic
	@Column(name="AllowePeople", columnDefinition="BIGINT")
	public Long getAllowePeople() {
		return allowePeople;
	}

	private Long overTime;

	public void setOverTime(Long overTime) {
		this.overTime = overTime;
	}

	@Basic
	@Column(name="OverTime", columnDefinition="BIGINT")
	public Long getOverTime() {
		return overTime;
	}

	private OverTimeMode overTimeMode;

	public void setOverTimeMode(OverTimeMode overTimeMode) {
		this.overTimeMode = overTimeMode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="OverTimeMode")
	public OverTimeMode getOverTimeMode() {
		return overTimeMode;
	}

	private BigDecimal jiFenJiShu;

	public void setJiFenJiShu(BigDecimal jiFenJiShu) {
		this.jiFenJiShu = jiFenJiShu;
	}

	@Basic
	@Column(name="JiFenJiShu", precision=19, scale=10)
	public BigDecimal getJiFenJiShu() {
		if(jiFenJiShu == null){
			return BigDecimal.ZERO;
		}
		return jiFenJiShu.setScale(10, BigDecimal.ROUND_HALF_EVEN);
		}
	private BigDecimal jiFenE;

	public void setJiFenE(BigDecimal jiFenE) {
		this.jiFenE = jiFenE;
	}

	@Basic
	@Column(name="JiFenE", precision=19, scale=10)
	public BigDecimal getJiFenE() {
		if(jiFenE == null){
			return BigDecimal.ZERO;
		}
		return jiFenE.setScale(10, BigDecimal.ROUND_HALF_EVEN);
		}
	@Transient
	public String getDspName(){
		if (getZuoTaiLeiXingID() == null) {
			return getZuoTaiLeiXingName();
		}
		return String.format("(%s)%s", getZuoTaiLeiXingID(), getZuoTaiLeiXingName());
	}
	private Set<TblType_Discount> discountTypes = new HashSet<TblType_Discount>();

	public void addDiscountType(TblType_Discount discountType){
		discountTypes.add(discountType);
	}
	
	private void setDiscountTypes(Set<TblType_Discount> discountTypes) {
		this.discountTypes = discountTypes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="tblType")
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@Column(name="DiscountType")
	public Set<TblType_Discount> getDiscountTypes() {
		return discountTypes;
	}
	private Set<ZiDongDianCai> ziDongDians = new HashSet<ZiDongDianCai>();

	public void addZiDongDian(ZiDongDianCai ziDongDian){
		ziDongDians.add(ziDongDian);
	}
	
	private void setZiDongDians(Set<ZiDongDianCai> ziDongDians) {
		this.ziDongDians = ziDongDians;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="zuoTaiLX")
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@Column(name="ZiDongDian")
	public Set<ZiDongDianCai> getZiDongDians() {
		return ziDongDians;
	}
	private Set<Table> zuoTs = new HashSet<Table>();

	public void addZuoT(Table zuoT){
		zuoTs.add(zuoT);
	}
	
	private void setZuoTs(Set<Table> zuoTs) {
		this.zuoTs = zuoTs;
	}
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof TableType)) {
			return false;
		}
		Long selfId = getPID();
		Long otherId = ((TableType) other).getPID();
		if (selfId == null || otherId == null) {
			return false;
		}

		return selfId.equals(otherId);
	}

	@Override
	public int hashCode() {
		Long selfId = getPID();
		if (selfId != null) {
			return selfId.intValue();
		} else {
			return super.hashCode();
		}
	}


}
