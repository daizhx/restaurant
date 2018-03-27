package com.smtech.restaurant.entities;


import org.hibernate.annotations.Cascade;

import java.util.*;
import javax.persistence.*;


@Entity
public class ZuoTaiQuYu {

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

	private String zuoTaiQuYuID;

	public void setZuoTaiQuYuID(String zuoTaiQuYuID) {
		this.zuoTaiQuYuID = zuoTaiQuYuID;
	}

	@Basic
	@Column(name="ZuoTaiQuYuID")
	public String getZuoTaiQuYuID() {
		return zuoTaiQuYuID;
	}

	private String zuoTaiQuYuName;

	public void setZuoTaiQuYuName(String zuoTaiQuYuName) {
		this.zuoTaiQuYuName = zuoTaiQuYuName;
	}

	@Basic
	@Column(name="ZuoTaiQuYuName")
	public String getZuoTaiQuYuName() {
		return zuoTaiQuYuName;
	}

	private Long showOrder;

	public void setShowOrder(Long showOrder) {
		this.showOrder = showOrder;
	}

	@Basic
	@Column(name="ShowOrder", columnDefinition="BIGINT")
	public Long getShowOrder() {
		return showOrder;
	}

	private String zuoTaiQuYuName2;

	public void setZuoTaiQuYuName2(String zuoTaiQuYuName2) {
		this.zuoTaiQuYuName2 = zuoTaiQuYuName2;
	}

	@Basic
	@Column(name="ZuoTaiQuYuName2")
	public String getZuoTaiQuYuName2() {
		return zuoTaiQuYuName2;
	}

	private String zuoTaiQuYuName3;

	public void setZuoTaiQuYuName3(String zuoTaiQuYuName3) {
		this.zuoTaiQuYuName3 = zuoTaiQuYuName3;
	}

	@Basic
	@Column(name="ZuoTaiQuYuName3")
	public String getZuoTaiQuYuName3() {
		return zuoTaiQuYuName3;
	}

	@Transient
	public String getDspName(){
		if (getZuoTaiQuYuID() == null) {
			return getZuoTaiQuYuName();
		}
		return String.format("(%s)%s", getZuoTaiQuYuID(), getZuoTaiQuYuName());
	}
	private Set<Table> zuoTs = new HashSet<Table>();

	public void addZuoT(Table zuoT){
		zuoTs.add(zuoT);
	}
	
	private void setZuoTs(Set<Table> zuoTs) {
		this.zuoTs = zuoTs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="taiQu")
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@Column(name="ZuoT")
	public Set<Table> getZuoTs() {
		return zuoTs;
	}
	private Set<TaiQuCaiPin> taiQuCaiPs = new HashSet<TaiQuCaiPin>();

	public void addTaiQuCaiP(TaiQuCaiPin taiQuCaiP){
		taiQuCaiPs.add(taiQuCaiP);
	}
	
	private void setTaiQuCaiPs(Set<TaiQuCaiPin> taiQuCaiPs) {
		this.taiQuCaiPs = taiQuCaiPs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="zuoTaiQuY")
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@Column(name="TaiQuCaiP")
	public Set<TaiQuCaiPin> getTaiQuCaiPs() {
		return taiQuCaiPs;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ZuoTaiQuYu)) {
			return false;
		}
		Long selfId = getPID();
		Long otherId = ((ZuoTaiQuYu) other).getPID();
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
