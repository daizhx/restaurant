package com.smtech.restaurant.entities;

import com.smtech.restaurant.enums.ComPrnFontSize;
import com.smtech.restaurant.enums.PrintBillItemType;
import javafx.print.PrintColor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Prn_PrintJobItem implements Serializable{

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

	private PrintBillItemType contentType;

	public void setContentType(PrintBillItemType contentType) {
		this.contentType = contentType;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="ContentType")
	public PrintBillItemType getContentType() {
		return contentType;
	}

	private String printContent;

	public void setPrintContent(String printContent) {
		this.printContent = printContent;
	}

	public String getPrintContent() {
		return printContent;
	}

	private String width;

	public void setWidth(String width) {
		this.width = width;
	}

	@Basic
	@Column(name="Width")
	public String getWidth() {
		return width;
	}

	private String align;

	public void setAlign(String align) {
		this.align = align;
	}

	@Basic
	@Column(name="Align")
	public String getAlign() {
		return align;
	}

	private String fontName;

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	@Basic
	@Column(name="FontName")
	public String getFontName() {
		return fontName;
	}

	private PrintColor fontColor;

	public void setFontColor(PrintColor fontColor) {
		this.fontColor = fontColor;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="FontColor")
	public PrintColor getFontColor() {
		return fontColor;
	}

	private Long fontSize;

	public void setFontSize(Long fontSize) {
		this.fontSize = fontSize;
	}

	@Basic
	@Column(name="FontSize", columnDefinition="BIGINT")
	public Long getFontSize() {
		return fontSize;
	}

	private ComPrnFontSize comFontSize;

	public void setComFontSize(ComPrnFontSize comFontSize) {
		this.comFontSize = comFontSize;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="ComFontSize")
	public ComPrnFontSize getComFontSize() {
		return comFontSize;
	}

	private Boolean bold;

	public void setBold(Boolean bold) {
		this.bold = bold;
	}

	@Basic
	@Column(name="Bold")
	public Boolean getBold() {
		if(bold == null){
		return false;
		}
		return bold;
	}

	private Boolean italic;

	public void setItalic(Boolean italic) {
		this.italic = italic;
	}

	@Basic
	@Column(name="Italic")
	public Boolean getItalic() {
		if(italic == null){
		return false;
		}
		return italic;
	}

	private Boolean underline;

	public void setUnderline(Boolean underline) {
		this.underline = underline;
	}

	@Basic
	@Column(name="Underline")
	public Boolean getUnderline() {
		if(underline == null){
		return false;
		}
		return underline;
	}

	private Boolean strikethrough;

	public void setStrikethrough(Boolean strikethrough) {
		this.strikethrough = strikethrough;
	}

	@Basic
	@Column(name="Strikethrough")
	public Boolean getStrikethrough() {
		if(strikethrough == null){
		return false;
		}
		return strikethrough;
	}

	private String condition;

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCondition() {
		return condition;
	}

	@Transient
	public String getDspName(){
		if (getID() == null) {
			return getName();
		}
		return String.format("(%s)%s", getID(), getName());
	}
	private Prn_PrintJob job;

	public void setJob(Prn_PrintJob job) {
		this.job = job;
	}

	@ManyToOne
	@JoinColumn(name="Job")
	public Prn_PrintJob getJob() {
		return job;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Prn_PrintJobItem)) {
			return false;
		}
		Long selfId = getPID();
		Long otherId = ((Prn_PrintJobItem) other).getPID();
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
