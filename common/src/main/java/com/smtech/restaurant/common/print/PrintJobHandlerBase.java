package com.smtech.restaurant.common.print;

import com.smtech.restaurant.entities.Prn_PrintJob;
import com.smtech.restaurant.entities.Prn_PrintStyle;
import com.smtech.restaurant.entities.Prn_Printer;
import com.smtech.restaurant.entities.Prn_StyleContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 打印任务处理器基类
 */
public abstract class PrintJobHandlerBase  {
	public abstract Boolean handle(Prn_PrintJob job);

	public void setPrinterEx(PrinterEx printerEx) {
		this.printerEx = printerEx;
	}

	public PrinterEx getPrinterEx() {
		return printerEx;
	}

	public void setCurJob(Prn_PrintJob curJob) {
		this.curJob = curJob;
	}

	public Prn_Printer getPrinter() {
		return getPrinterEx().getPrinter();
	}

	public Prn_PrintJob getCurJob() {
		return curJob;
	}

	public Prn_PrintStyle getPrintStyle() {
		return printStyle;
	}

	public void setPrintContents(List<Prn_StyleContent> printContents) {
		this.printContents = printContents;
	}

	public List<Prn_StyleContent> getPrintContents() {
		return printContents;
	}

	public void setAllParas(Map<String, Object> allParas) {
		this.allParas = allParas;
	}

	public Map<String, Object> getAllParas() {
		return allParas;
	}

	public void setCurPrnContent(Prn_StyleContent curPrnContent) {
		this.curPrnContent = curPrnContent;
	}

	public Prn_StyleContent getCurPrnContent() {
		return curPrnContent;
	}

	public abstract PrinterStatus getStatus();

	public abstract Boolean jobIsFinished();

	protected abstract Boolean handleText();

	protected abstract Boolean handleSqlQuery();

	protected abstract Boolean handleLine();

	protected abstract Boolean handleCutPaper();

	protected abstract Boolean handleBlankLine();

	protected abstract Boolean handleImg();



	/**
	 * 替换字符串中的参数
	 *
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected String replaceStrWithParas(String str) {
		if (str == null) {
			logger.error("The input para is null,please check it");
			return null;
		}
		String newStr = str;
		Set<String> parasInStr = getParasInStr(str);
		for (String paraName : parasInStr) {
			Map m = getAllParas();
			Object o = m.get(paraName);
			String strVal = getStrValue(o);
			if (strVal == null) {
				logger.error(String.format("there is not any para which named [%s]", paraName));
				strVal = "-";
			}
			if (paraName.equals("{$KAITAISHIJIAN}")) {
				String[] vals = strVal.split(" ");
				if ((vals != null) && (vals.length > 2)) {
					strVal = strVal.split(" ")[1];
					if (strVal.length() >= 9) {
						strVal = strVal.substring(0, 8);
					}
				}
			}
			newStr = newStr.replace(paraName, strVal);
		}
		return newStr;
	}

	protected String getStrValue(Object paraVal) {
		if (paraVal == null) {
			return " ";
		}
		String strVal = "";// 字符串格式表示的参数值
		if (paraVal instanceof Integer) {
			strVal = String.valueOf(paraVal);
		} else if (paraVal instanceof Long) {
			strVal = String.valueOf(paraVal);
		} else if (paraVal instanceof String) {
			strVal = (String) paraVal;
		} else if (paraVal instanceof Boolean) {
			strVal = (Boolean) paraVal ? "1" : "0";
		} else if (paraVal instanceof BigDecimal) {
//			int numPoint = QJCSInMemManager.getInstance().getIntValue(QJCSManager.g_DaYingDanXiaoShuWeiShu, 2);
//			strVal = ComDateUtil.toBigDecimal(paraVal).setScale(numPoint, BigDecimal.ROUND_HALF_EVEN).toString();
		} else if (paraVal instanceof Timestamp) {
			SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
			strVal = sdfTime.format(paraVal);
		} else if (paraVal instanceof Date) {
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
			strVal = sdfDate.format(paraVal);
		} else if (paraVal instanceof Double) {
			strVal = Double.valueOf(paraVal.toString()).toString();
		} else {
			logger.warn("Can't deal the datatype:" + paraVal.getClass().getName());
			strVal = paraVal.toString();
		}

		strVal = strVal.trim();
		if (strVal.equals("")) {
			strVal = " ";
		}
		return strVal;
	}

	/**
	 * 获取某字符串中的参数信息
	 * 
	 * @param str
	 * @return
	 */
	protected Set<String> getParasInStr(String str) {
		Set<String> setRet = new HashSet<String>();
		Pattern pt = Pattern.compile("(\\{[@$]\\w+\\})");
		Matcher m = pt.matcher(str);
		while (m.find()) {
			setRet.add(m.group());
		}
		return setRet;
	}



	/**
	 * 将sql中，消费单、消费菜品等表名替换成反结账的表名
	 * 
	 * @param sql
	 * @return
	 */
	protected String replaceBillInfoToFJZ(String sql) {
		sql = sql.replaceAll("(?i)XiaoFeiDanEx ", "XiaoFeiDanFJZ ");
		sql = sql.replaceAll("(?i)Bi_TblInBillEx ", "Bi_TblInBillFJZ ");
		sql = sql.replaceAll("(?i)FuKuanQingKuangEx ", "FuKuanQingKuangFJZ ");
		sql = sql.replaceAll("(?i)XiaoFeiCaiPingEx ", "XiaoFeiCaiPingFJZ ");
		sql = sql.replaceAll("(?i)ViewXfcpEx ", "ViewXfcpFJZ ");
		sql = sql.replaceAll("(?i)CaiShiFaEx ", "CaiShiFaFJZ ");
		sql = sql.replaceAll("(?i)BuWeiInShiFaEx ", "BuWeiInShiFaFJZ ");
		sql = sql.replaceAll("(?i)ZuoFaInShiFaEx ", "ZuoFaInShiFaFJZ ");
		sql = sql.replaceAll("(?i)KuoWeiInShiFaEx ", "KuoWeiInShiFaFJZ ");
		sql = sql.replaceAll("(?i)YaoQiuInShiFaEx ", "YaoQiuInShiFaFJZ ");

		sql = sql.replaceAll("(?i)XiaoFeiDanEx \n", "XiaoFeiDanFJZ \n");
		sql = sql.replaceAll("(?i)Bi_TblInBillEx \n", "Bi_TblInBillFJZ \n");
		sql = sql.replaceAll("(?i)FuKuanQingKuangEx \n", "FuKuanQingKuangFJZ \n");
		sql = sql.replaceAll("(?i)XiaoFeiCaiPingEx \n", "XiaoFeiCaiPingFJZ \n");
		sql = sql.replaceAll("(?i)ViewXfcpEx \n", "ViewXfcpFJZ \n");
		sql = sql.replaceAll("(?i)CaiShiFaEx \n", "CaiShiFaFJZ \n");
		sql = sql.replaceAll("(?i)BuWeiInShiFaEx \n", "BuWeiInShiFaFJZ \n");
		sql = sql.replaceAll("(?i)ZuoFaInShiFaEx \n", "ZuoFaInShiFaFJZ \n");
		sql = sql.replaceAll("(?i)KuoWeiInShiFaEx \n", "KuoWeiInShiFaFJZ \n");
		sql = sql.replaceAll("(?i)YaoQiuInShiFaEx \n", "YaoQiuInShiFaFJZ \n");

		sql = sql.replaceAll("(?i)XiaoFeiDanEx\r \n", "XiaoFeiDanFJZ\r \n");
		sql = sql.replaceAll("(?i)Bi_TblInBillEx\r \n", "Bi_TblInBillFJZ\r \n");
		sql = sql.replaceAll("(?i)FuKuanQingKuangEx\r \n", "FuKuanQingKuangFJZ\r \n");
		sql = sql.replaceAll("(?i)XiaoFeiCaiPingEx\r \n", "XiaoFeiCaiPingFJZ\r \n");
		sql = sql.replaceAll("(?i)ViewXfcpEx\r \n", "ViewXfcpFJZ\r \n");
		sql = sql.replaceAll("(?i)CaiShiFaEx\r \n", "CaiShiFaFJZ\r \n");
		sql = sql.replaceAll("(?i)BuWeiInShiFaEx\r \n", "BuWeiInShiFaFJZ\r \n");
		sql = sql.replaceAll("(?i)ZuoFaInShiFaEx\r \n", "ZuoFaInShiFaFJZ\r \n");
		sql = sql.replaceAll("(?i)KuoWeiInShiFaEx\r \n", "KuoWeiInShiFaFJZ\r \n");
		sql = sql.replaceAll("(?i)YaoQiuInShiFaEx\r \n", "YaoQiuInShiFaFJZ\r \n");
		return sql;
	}

	/**
	 * 将sql中，消费单、消费菜品等表名替换成反结算的表名
	 * 
	 * @param sql
	 * @return
	 */
	protected String replaceBillInfoToFJS(String sql) {
		sql = sql.replaceAll("(?i)XiaoFeiDan ", "XiaoFeiDanFJS ");
		sql = sql.replaceAll("(?i)Bi_TblInBill ", "Bi_TblInBillFJS ");
		sql = sql.replaceAll("(?i)FuKuanQingKuang ", "FuKuanQingKuangFJS ");
		sql = sql.replaceAll("(?i)XiaoFeiCaiPing ", "XiaoFeiCaiPingFJS ");
		sql = sql.replaceAll("(?i)ViewXfcp ", "ViewXfcpFJS ");
		sql = sql.replaceAll("(?i)CaiShiFa ", "CaiShiFaFJS ");
		sql = sql.replaceAll("(?i)BuWeiInShiFa ", "BuWeiInShiFaFJS ");
		sql = sql.replaceAll("(?i)ZuoFaInShiFa ", "ZuoFaInShiFaFJS ");
		sql = sql.replaceAll("(?i)KuoWeiInShiFa ", "KuoWeiInShiFaFJS ");
		sql = sql.replaceAll("(?i)YaoQiuInShiFa ", "YaoQiuInShiFaFJS ");

		sql = sql.replaceAll("(?i)XiaoFeiDan \n", "XiaoFeiDanFJS \n");
		sql = sql.replaceAll("(?i)Bi_TblInBill \n", "Bi_TblInBillFJS \n");
		sql = sql.replaceAll("(?i)FuKuanQingKuang \n", "FuKuanQingKuangFJS \n");
		sql = sql.replaceAll("(?i)XiaoFeiCaiPing \n", "XiaoFeiCaiPingFJS \n");
		sql = sql.replaceAll("(?i)ViewXfcp \n", "ViewXfcpFJS \n");
		sql = sql.replaceAll("(?i)CaiShiFa \n", "CaiShiFaFJS \n");
		sql = sql.replaceAll("(?i)BuWeiInShiFa \n", "BuWeiInShiFaFJS \n");
		sql = sql.replaceAll("(?i)ZuoFaInShiFa \n", "ZuoFaInShiFaFJS \n");
		sql = sql.replaceAll("(?i)KuoWeiInShiFa \n", "KuoWeiInShiFaFJS \n");
		sql = sql.replaceAll("(?i)YaoQiuInShiFa \n", "YaoQiuInShiFaFJS \n");
		return sql;
	}


	public void setNeedReplaceToFJZ(Boolean needReplaceToFJZ) {
		this.needReplaceToFJZ = needReplaceToFJZ;
	}

	public Boolean getNeedReplaceToFJZ() {
		return needReplaceToFJZ;
	}

	public void setNeedReplaceToFJS(Boolean needReplaceToFJS) {
		this.needReplaceToFJS = needReplaceToFJS;
	}

	public Boolean getNeedReplaceToFJS() {
		return needReplaceToFJS;
	}

	/**
	 * 打印机状态
	 */
	public static enum PrinterStatus {
		NORMAL, FAULT, BUSY
	}

	// 是否需要替换成反结账的打印样式
	private Boolean needReplaceToFJZ = false;

	// 是否需要替换成反结算的打印样式
	private Boolean needReplaceToFJS = false;

	// 当前正在处理的打印任务相关信息
	protected Prn_PrintJob curJob;
	protected Prn_PrintStyle printStyle;// 当前的打印样式
	protected List<Prn_StyleContent> printContents;// 当前样式中的打印内容;
	protected Prn_StyleContent curPrnContent;// 当前正在处理的打印项
	protected Map<String, Object> allParas = new HashMap<String, Object>();

	protected PrinterEx printerEx;
	protected static Logger logger = LoggerFactory.getLogger(PrintJobHandlerBase.class);
}