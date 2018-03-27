package com.smtech.restaurant.entities;


import javax.persistence.*;

@Entity
public class TaiQuCaiPin {

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

	private String iD;

	public void setID(String iD) {
		this.iD = iD;
	}

	@Basic
	@Column(name="ID")
	public String getID() {
		return iD;
	}

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name="Name")
	public String getName() {
		return name;
	}

	@Transient
	public String getDspName(){
		if (getID() == null) {
			return getName();
		}
		return String.format("(%s)%s", getID(), getName());
	}
	private ZuoTaiQuYu zuoTaiQuY;

	public void setZuoTaiQuY(ZuoTaiQuYu zuoTaiQuY) {
		this.zuoTaiQuY = zuoTaiQuY;
	}

	@ManyToOne
	@JoinColumn(name="ZuoTaiQuY")
	public ZuoTaiQuYu getZuoTaiQuY() {
		return zuoTaiQuY;
	}

	private Food caiP;

	public void setCaiP(Food caiP) {
		this.caiP = caiP;
	}

	@ManyToOne
	@JoinColumn(name="CaiP")
	public Food getCaiP() {
		return caiP;
	}

	private Prn_Department chuPingBM;

	public void setChuPingBM(Prn_Department chuPingBM) {
		this.chuPingBM = chuPingBM;
	}

	@ManyToOne
	@JoinColumn(name="ChuPingBM")
	public Prn_Department getChuPingBM() {
		return chuPingBM;
	}



	@Override
	public boolean equals(Object other) {
		if (!(other instanceof TaiQuCaiPin)) {
			return false;
		}
		Long selfId = getPID();
		Long otherId = ((TaiQuCaiPin) other).getPID();
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
