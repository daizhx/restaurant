package com.smtech.restaurant.entities;

import com.smtech.restaurant.enums.PrintBillType;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

//打印任务
@Entity
public class Prn_PrintJob implements Serializable {

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

    private PrintBillType type;

    public void setType(PrintBillType type) {
        this.type = type;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="Type")
    public PrintBillType getType() {
        return type;
    }

    private Timestamp createTime;

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name="CreateTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    private Timestamp printTime;

    public void setPrintTime(Timestamp printTime) {
        this.printTime = printTime;
    }

    @Basic
    @Column(name="PrintTime")
    public Timestamp getPrintTime() {
        return printTime;
    }

    private Timestamp delayPrintTime;

    public void setDelayPrintTime(Timestamp delayPrintTime) {
        this.delayPrintTime = delayPrintTime;
    }

    @Basic
    @Column(name="DelayPrintTime")
    public Timestamp getDelayPrintTime() {
        return delayPrintTime;
    }

    private String prnQueueName;

    public void setPrnQueueName(String prnQueueName) {
        this.prnQueueName = prnQueueName;
    }

    @Basic
    @Column(name="PrnQueueName")
    public String getPrnQueueName() {
        return prnQueueName;
    }

    private Long printCount;

    public void setPrintCount(Long printCount) {
        this.printCount = printCount;
    }

    @Basic
    @Column(name="PrintCount", columnDefinition="BIGINT")
    public Long getPrintCount() {
        return printCount;
    }

    private java.sql.Blob paras;

    public void setParas(java.sql.Blob paras) {
        try {
            this.paras = new SerialBlob(paras);
        } catch (Exception e) {
//            logger.error(StackTraceToString.getExceptionTrace(e));
        }
    }

    @Basic
    @Column(name="Paras")
    public java.sql.Blob getParas() {
        return paras;
    }

    private String xfdID;

    public void setXfdID(String xfdID) {
        this.xfdID = xfdID;
    }

    @Basic
    @Column(name="XfdID")
    public String getXfdID() {
        return xfdID;
    }

    private String xfdName;

    public void setXfdName(String xfdName) {
        this.xfdName = xfdName;
    }

    @Basic
    @Column(name="XfdName")
    public String getXfdName() {
        return xfdName;
    }

    private String oldXfdName;

    public void setOldXfdName(String oldXfdName) {
        this.oldXfdName = oldXfdName;
    }

    @Basic
    @Column(name="OldXfdName")
    public String getOldXfdName() {
        return oldXfdName;
    }

    private Boolean inited;

    public void setInited(Boolean inited) {
        this.inited = inited;
    }

    @Basic
    @Column(name="Inited")
    public Boolean getInited() {
        if(inited == null){
            return false;
        }
        return inited;
    }

    private Long curPrinterPID;

    public void setCurPrinterPID(Long curPrinterPID) {
        this.curPrinterPID = curPrinterPID;
    }

    @Basic
    @Column(name="CurPrinterPID", columnDefinition="BIGINT")
    public Long getCurPrinterPID() {
        return curPrinterPID;
    }

    private Boolean printed;

    public void setPrinted(Boolean printed) {
        this.printed = printed;
    }

    @Basic
    @Column(name="Printed")
    public Boolean getPrinted() {
        if(printed == null){
            return false;
        }
        return printed;
    }

    private String xfcpName;

    public void setXfcpName(String xfcpName) {
        this.xfcpName = xfcpName;
    }

    @Basic
    @Column(name="XfcpName")
    public String getXfcpName() {
        return xfcpName;
    }

    private String buMen;

    public void setBuMen(String buMen) {
        this.buMen = buMen;
    }

    @Basic
    @Column(name="BuMen")
    public String getBuMen() {
        return buMen;
    }

    private Boolean isCPBML;

    public void setIsCPBML(Boolean isCPBML) {
        this.isCPBML = isCPBML;
    }

    @Basic
    @Column(name="IsCPBML")
    public Boolean getIsCPBML() {
        if(isCPBML == null){
            return false;
        }
        return isCPBML;
    }

    private Long rePrintCount;

    public void setRePrintCount(Long rePrintCount) {
        this.rePrintCount = rePrintCount;
    }

    @Basic
    @Column(name="RePrintCount", columnDefinition="BIGINT")
    public Long getRePrintCount() {
        return rePrintCount;
    }

    @Transient
    public String getDspName(){
        if (getID() == null) {
            return getName();
        }
        return String.format("(%s)%s", getID(), getName());
    }
    private Set<Prn_PrintJobItem> items = new LinkedHashSet<Prn_PrintJobItem>();

    public void addItem(Prn_PrintJobItem item){
        items.add(item);
    }

    private void setItems(Set<Prn_PrintJobItem> items) {
        this.items = items;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="job")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @Column(name="Item")
    public Set<Prn_PrintJobItem> getItems() {
        return items;
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Prn_PrintJob)) {
            return false;
        }
        Long selfId = getPID();
        Long otherId = ((Prn_PrintJob) other).getPID();
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
    @Transient
    public void setParasEx(Map parasEx) {
        this.parasEx = parasEx;
    }

    @Transient
    public Map getParasEx() {
        return parasEx;
    }

    @Transient
    private Map parasEx;


}