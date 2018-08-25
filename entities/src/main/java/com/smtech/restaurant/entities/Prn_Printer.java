package com.smtech.restaurant.entities;

import com.smtech.restaurant.enums.PrinterBrand;
import com.smtech.restaurant.enums.PrinterType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

//打印机
@Entity
public class Prn_Printer {

    private Long pID;

    public void setPID(Long pID) {
        this.pID = pID;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    private PrinterType type;

    public void setType(PrinterType type) {
        this.type = type;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="Type")
    public PrinterType getType() {
        return type;
    }

    private Long linePrnTime;

    public void setLinePrnTime(Long linePrnTime) {
        this.linePrnTime = linePrnTime;
    }

    @Basic
    @Column(name="LinePrnTime", columnDefinition="BIGINT")
    public Long getLinePrnTime() {
        return linePrnTime;
    }

    private Long curPaperTime;

    public void setCurPaperTime(Long curPaperTime) {
        this.curPaperTime = curPaperTime;
    }

    @Basic
    @Column(name="CurPaperTime", columnDefinition="BIGINT")
    public Long getCurPaperTime() {
        return curPaperTime;
    }

    private boolean used;


    private String physicsName;

    public void setPhysicsName(String physicsName) {
        this.physicsName = physicsName;
    }

    @Basic
    @Column(name="PhysicsName")
    public String getPhysicsName() {
        return physicsName;
    }

    private BigDecimal paperWidth;

    public void setPaperWidth(BigDecimal paperWidth) {
        this.paperWidth = paperWidth;
    }

    @Basic
    @Column(name="PaperWidth", precision=19, scale=10)
    public BigDecimal getPaperWidth() {
        if(paperWidth == null){
            return BigDecimal.ZERO;
        }
        return paperWidth.setScale(10, BigDecimal.ROUND_HALF_EVEN);
    }
    private BigDecimal paperHigh;

    public void setPaperHigh(BigDecimal paperHigh) {
        this.paperHigh = paperHigh;
    }

    @Basic
    @Column(name="PaperHigh", precision=19, scale=10)
    public BigDecimal getPaperHigh() {
        if(paperHigh == null){
            return BigDecimal.ZERO;
        }
        return paperHigh.setScale(10, BigDecimal.ROUND_HALF_EVEN);
    }
    private BigDecimal topMargin;

    public void setTopMargin(BigDecimal topMargin) {
        this.topMargin = topMargin;
    }

    @Basic
    @Column(name="TopMargin", precision=19, scale=10)
    public BigDecimal getTopMargin() {
        if(topMargin == null){
            return BigDecimal.ZERO;
        }
        return topMargin.setScale(10, BigDecimal.ROUND_HALF_EVEN);
    }
    private BigDecimal bottomMargin;

    public void setBottomMargin(BigDecimal bottomMargin) {
        this.bottomMargin = bottomMargin;
    }

    @Basic
    @Column(name="BottomMargin", precision=19, scale=10)
    public BigDecimal getBottomMargin() {
        if(bottomMargin == null){
            return BigDecimal.ZERO;
        }
        return bottomMargin.setScale(10, BigDecimal.ROUND_HALF_EVEN);
    }
    private BigDecimal leftMargin;

    public void setLeftMargin(BigDecimal leftMargin) {
        this.leftMargin = leftMargin;
    }

    @Basic
    @Column(name="LeftMargin", precision=19, scale=10)
    public BigDecimal getLeftMargin() {
        if(leftMargin == null){
            return BigDecimal.ZERO;
        }
        return leftMargin.setScale(10, BigDecimal.ROUND_HALF_EVEN);
    }
    private BigDecimal rightMargin;

    public void setRightMargin(BigDecimal rightMargin) {
        this.rightMargin = rightMargin;
    }

    @Basic
    @Column(name="RightMargin", precision=19, scale=10)
    public BigDecimal getRightMargin() {
        if(rightMargin == null){
            return BigDecimal.ZERO;
        }
        return rightMargin.setScale(10, BigDecimal.ROUND_HALF_EVEN);
    }
    private Long baudRate;

    public void setBaudRate(Long baudRate) {
        this.baudRate = baudRate;
    }

    @Basic
    @Column(name="BaudRate", columnDefinition="BIGINT")
    public Long getBaudRate() {
        return baudRate;
    }

    private Long dataBits;

    public void setDataBits(Long dataBits) {
        this.dataBits = dataBits;
    }

    @Basic
    @Column(name="DataBits", columnDefinition="BIGINT")
    public Long getDataBits() {
        return dataBits;
    }

    private Long stopBits;

    public void setStopBits(Long stopBits) {
        this.stopBits = stopBits;
    }

    @Basic
    @Column(name="StopBits", columnDefinition="BIGINT")
    public Long getStopBits() {
        return stopBits;
    }

    private Long blankLineNum;

    public void setBlankLineNum(Long blankLineNum) {
        this.blankLineNum = blankLineNum;
    }

    @Basic
    @Column(name="BlankLineNum", columnDefinition="BIGINT")
    public Long getBlankLineNum() {
        return blankLineNum;
    }

    private Long flowControl;

    public void setFlowControl(Long flowControl) {
        this.flowControl = flowControl;
    }

    @Basic
    @Column(name="FlowControl", columnDefinition="BIGINT")
    public Long getFlowControl() {
        return flowControl;
    }

    private PrinterBrand brand;

    public void setBrand(PrinterBrand brand) {
        this.brand = brand;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="Brand")
    public PrinterBrand getBrand() {
        return brand;
    }

    private Boolean beep;

    public void setBeep(Boolean beep) {
        this.beep = beep;
    }

    @Basic
    @Column(name="Beep")
    public Boolean getBeep() {
        if(beep == null){
            return false;
        }
        return beep;
    }

    private Boolean prnLabel;

    public void setPrnLabel(Boolean prnLabel) {
        this.prnLabel = prnLabel;
    }

    @Basic
    @Column(name="PrnLabel")
    public Boolean getPrnLabel() {
        if(prnLabel == null){
            return false;
        }
        return prnLabel;
    }

    private Boolean prnTest;

    public void setPrnTest(Boolean prnTest) {
        this.prnTest = prnTest;
    }

    @Basic
    @Column(name="PrnTest")
    public Boolean getPrnTest() {
        if(prnTest == null){
            return false;
        }
        return prnTest;
    }

    private Date prnTestDate;

    public void setPrnTestDate(Date prnTestDate) {
        this.prnTestDate = prnTestDate;
    }

    @Basic
    @Column(name="PrnTestDate")
    public Date getPrnTestDate() {
        return prnTestDate;
    }

    @Transient
    public String getDspName(){
        if (getID() == null) {
            return getName();
        }
        return String.format("(%s)%s", getID(), getName());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Prn_Printer)) {
            return false;
        }
        Long selfId = getPID();
        Long otherId = ((Prn_Printer) other).getPID();
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