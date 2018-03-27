package com.smtech.restaurant.entities;

import com.smtech.restaurant.enums.TableStatus;

import javax.persistence.*;

@Entity
public class Table {

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

	private String zuoTaiID;

	public void setZuoTaiID(String zuoTaiID) {
		this.zuoTaiID = zuoTaiID;
	}

	@Basic
	@Column(name="ZuoTaiID")
	public String getZuoTaiID() {
		return zuoTaiID;
	}

	private String zuoTaiName;

	public void setZuoTaiName(String zuoTaiName) {
		this.zuoTaiName = zuoTaiName;
	}

	@Basic
	@Column(name="ZuoTaiName")
	public String getZuoTaiName() {
		return zuoTaiName;
	}

	private TableStatus zhuangTai;

	public void setZhuangTai(TableStatus zhuangTai) {
		this.zhuangTai = zhuangTai;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="ZhuangTai")
	public TableStatus getZhuangTai() {
		return zhuangTai;
	}

	private Boolean orderable;

	public void setOrderable(Boolean orderable) {
		this.orderable = orderable;
	}

	@Basic
	@Column(name="Orderable")
	public Boolean getOrderable() {
		if(orderable == null){
		return false;
		}
		return orderable;
	}

	private String zuoTaiName2;

	public void setZuoTaiName2(String zuoTaiName2) {
		this.zuoTaiName2 = zuoTaiName2;
	}

	@Basic
	@Column(name="ZuoTaiName2")
	public String getZuoTaiName2() {
		return zuoTaiName2;
	}

	private String zuoTaiName3;

	public void setZuoTaiName3(String zuoTaiName3) {
		this.zuoTaiName3 = zuoTaiName3;
	}

	@Basic
	@Column(name="ZuoTaiName3")
	public String getZuoTaiName3() {
		return zuoTaiName3;
	}

	private Boolean hide;

	public void setHide(Boolean hide) {
		this.hide = hide;
	}

	@Basic
	@Column(name="Hide")
	public Boolean getHide() {
		if(hide == null){
		return false;
		}
		return hide;
	}

	private Long lightNo;

	public void setLightNo(Long lightNo) {
		this.lightNo = lightNo;
	}

	@Basic
	@Column(name="LightNo", columnDefinition="BIGINT")
	public Long getLightNo() {
		return lightNo;
	}

	private String taiPaiKaHao;

	public void setTaiPaiKaHao(String taiPaiKaHao) {
		this.taiPaiKaHao = taiPaiKaHao;
	}

	@Basic
	@Column(name="TaiPaiKaHao")
	public String getTaiPaiKaHao() {
		return taiPaiKaHao;
	}

	private String jiaoBanHao;

	public void setJiaoBanHao(String jiaoBanHao) {
		this.jiaoBanHao = jiaoBanHao;
	}

	@Basic
	@Column(name="JiaoBanHao")
	public String getJiaoBanHao() {
		return jiaoBanHao;
	}

	private Long kaiTaiCiShu;

	public void setKaiTaiCiShu(Long kaiTaiCiShu) {
		this.kaiTaiCiShu = kaiTaiCiShu;
	}

	@Basic
	@Column(name="KaiTaiCiShu", columnDefinition="BIGINT")
	public Long getKaiTaiCiShu() {
		return kaiTaiCiShu;
	}

	@Transient
	public String getDspName(){
		if (getZuoTaiID() == null) {
			return getZuoTaiName();
		}
		return String.format("(%s)%s", getZuoTaiID(), getZuoTaiName());
	}
	private ZuoTaiQuYu taiQu;

	public void setTaiQu(ZuoTaiQuYu taiQu) {
		this.taiQu = taiQu;
	}

	@ManyToOne
	@JoinColumn(name="TaiQu")
	public ZuoTaiQuYu getTaiQu() {
		return taiQu;
	}

	private TableType leiXing;

	public void setLeiXing(TableType leiXing) {
		this.leiXing = leiXing;
	}

	@ManyToOne
	@JoinColumn(name="LeiXing")
	public TableType getLeiXing() {
		return leiXing;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Table)) {
			return false;
		}
		Long selfId = getPID();
		Long otherId = ((Table) other).getPID();
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
