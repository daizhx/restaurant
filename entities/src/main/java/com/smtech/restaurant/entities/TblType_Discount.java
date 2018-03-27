package com.smtech.restaurant.entities;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;

@Entity
public class TblType_Discount {

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
	private TableType tblType;

	public void setTblType(TableType tblType) {
		this.tblType = tblType;
	}

	@ManyToOne
	@JoinColumn(name="TblType")
	public TableType getTblType() {
		return tblType;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof TblType_Discount)) {
			return false;
		}
		Integer selfId = getPID();
		Integer otherId = ((TblType_Discount) other).getPID();
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
