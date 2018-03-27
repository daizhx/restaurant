package com.smtech.restaurant.entities;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
public class MN_CaiPinAndTableType {

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

	private BigDecimal price;

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Basic
	@Column(name="Price", precision=19, scale=10)
	public BigDecimal getPrice() {
		if(price == null){
			return BigDecimal.ZERO;
		}
		return price.setScale(10, BigDecimal.ROUND_HALF_EVEN);
		}
	private String unit;

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Basic
	@Column(name="Unit")
	public String getUnit() {
		return unit;
	}

	private BigDecimal oldPrice;

	public void setOldPrice(BigDecimal oldPrice) {
		this.oldPrice = oldPrice;
	}

	@Basic
	@Column(name="OldPrice", precision=19, scale=10)
	public BigDecimal getOldPrice() {
		if(oldPrice == null){
			return BigDecimal.ZERO;
		}
		return oldPrice.setScale(10, BigDecimal.ROUND_HALF_EVEN);
		}
	@Transient
	public String getDspName(){
		if (getID() == null) {
			return getName();
		}
		return String.format("(%s)%s", getID(), getName());
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

	private TableType tableType;

	public void setTableType(TableType tableType) {
		this.tableType = tableType;
	}

	@ManyToOne
	@JoinColumn(name="TableType")
	public TableType getTableType() {
		return tableType;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof MN_CaiPinAndTableType)) {
			return false;
		}
		Long selfId = getPID();
		Long otherId = ((MN_CaiPinAndTableType) other).getPID();
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
