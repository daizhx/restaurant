package com.smtech.restaurant.entities;

import com.smtech.restaurant.enums.PrintBillType;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//打印样式
@Entity
public class Prn_PrintStyle {

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

	private PrintBillType type;

	public void setType(PrintBillType type) {
		this.type = type;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="Type")
	public PrintBillType getType() {
		return type;
	}

	private String sourceString;

	public void setSourceString(String sourceString) {
		this.sourceString = sourceString;
	}

	public String getSourceString() {
		return sourceString;
	}

	private Long prnNum;

	public void setPrnNum(Long prnNum) {
		this.prnNum = prnNum;
	}

	@Basic
	@Column(name="PrnNum", columnDefinition="BIGINT")
	public Long getPrnNum() {
		return prnNum;
	}

	@Transient
	public String getDspName(){
		if (getID() == null) {
			return getName();
		}
		return String.format("(%s)%s", getID(), getName());
	}
	private Set<Prn_StyleContent> contents = new HashSet<Prn_StyleContent>();

	public void addContent(Prn_StyleContent content){
		contents.add(content);
	}
	
	private void setContents(Set<Prn_StyleContent> contents) {
		this.contents = contents;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="style")
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	@Column(name="Content")
	public Set<Prn_StyleContent> getContents() {
		return contents;
	}
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Prn_PrintStyle)) {
			return false;
		}
		Long selfId = getPID();
		Long otherId = ((Prn_PrintStyle) other).getPID();
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
