package com.smtech.restaurant.common.print;

import com.smtech.restaurant.common.StackTraceToString;
import com.smtech.restaurant.entities.Prn_PrintJob;
import com.smtech.restaurant.entities.Prn_Printer;
import com.smtech.restaurant.enums.PrinterType;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;


/**
 * 打印机
 *
 * 1,处理打印任务,使用打印任务处理器PrintJobHandlerBase来处理打印任务
 */
public class PrinterEx implements Runnable {

	private Logger logger = LoggerFactory.getLogger(getClass());

    public class ComparatorJobFile implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            if (!(o1 instanceof File) || !(o2 instanceof File)) {
                return 0;
            }
            File f1 = (File) o1;
            File f2 = (File) o2;
            String name1 = f1.getName();
            String name2 = f2.getName();
            if (!name1.endsWith(".job") || !name2.endsWith(".job")) {
                return 0;
            }
            try {
                name1 = name1.replace(".job", "");
                name2 = name2.replace(".job", "");
                long i1 = Long.valueOf(name1);
                long i2 = Long.valueOf(name2);
                return (int) (i1 - i2);
            } catch (Exception e) {
                logger.error(StackTraceToString.getExceptionTrace(e));
                return 0;
            }
        }
    }




public void afterPropertiesSet() throws Exception {
	new Thread(this).start();
}

@Override
public void run() {
	if (true) {
		// 使用文件的方式保存打印任务
		runWithFile();
		return;
	}

	int i = 0;
	while (true) {
		try {
			if (getJobCount() > 0) {
				handleJobs();
				decJobCount();
			}
			if (i % 3 == 0) {
				updatePrinterStatus(getJobHandler().getStatus());
			}

			long time = System.currentTimeMillis();
			Thread.sleep(100 * (time % 3 + 1));// 随机暂停0.1、0.2、0.3秒
			i++;
			if (i >= 100) {// 5秒钟强制分发任务
				i = 0;
				incJobCount();
			}
		} catch (Exception e) {
			logger.error(StackTraceToString.getExceptionTrace(e));
			sleepEx(1000);
		}
	}
}

private void runWithFile() {
	while (true) {
		try {
			handleJobs();
			updatePrinterStatus(getJobHandler().getStatus());
			Thread.sleep(100);
		} catch (Exception e) {
			logger.error(StackTraceToString.getExceptionTrace(e));
			sleepEx(1000);
		}
	}
}

/**
 * 处理打印任务
 * 
 * @param job
 * @return 处理成功返回TRUE，否则返回FALSE
 */
public synchronized Boolean ProcessJob(Prn_PrintJob job) {
	// 生成时间超过4小时的任务不打印
	long timeGap = System.currentTimeMillis() - job.getCreateTime().getTime();
	if (timeGap >= PrnJobManager.g_overTime) {
		logger.error(String.format("[%s_%d] need not to print,becase of it had been over time %d second", job
					.getType().toString(), job.getPID(), timeGap));
		return true;
	}

	if (getNotNeedPrn()) {
		return true;
	}
	PrintJobHandlerBase handler = getJobHandler();
	logger.info(String.format("begin to processJob[%s_%d]", job.getType().toString(), job.getPID()));
	PrintJobHandlerBase.PrinterStatus status = PrintJobHandlerBase.PrinterStatus.BUSY;
	while (status == PrintJobHandlerBase.PrinterStatus.BUSY) {
		status = handler.getStatus();
		if (status == PrintJobHandlerBase.PrinterStatus.FAULT) {// 打印机当前处于故障状态
			updatePrinterStatus(status);
			return false;
		} else if (status == PrintJobHandlerBase.PrinterStatus.NORMAL) {
			// 更新打印次数
			updateJobPrintCount(job);
			// 处理打印任务
			if (!handler.handle(job)) {
				logger.error(String.format("handle job[%s_%d] fail,try to reprint", job.getType().toString(),
							job.getPID()));
				status = PrintJobHandlerBase.PrinterStatus.BUSY;
				sleepEx(100 * 5);
				continue;
			}
			updatePrinterStatus(status);
			return true;
		}
		sleepEx(100 * 5);
	}
	updatePrinterStatus(status);
	return true;
}


public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setPrinter(Prn_Printer printer) {
	this.printer = printer;
}

public Prn_Printer getPrinter() {
	return printer;
}


private void sleepEx(long time) {
	try {
		Thread.sleep(time);
	} catch (Exception e) {
		logger.error(StackTraceToString.getExceptionTrace(e));
	}
}

/**
 * 处理打印任务
 * 
 * @return
 */
private void handleJobs() {
	if (true) {
		// 使用文件的方式保存打印任务
		handleJobsWithFile();
		return;
	}

//	// 获取需要处理的打印任务
//	String sql = null;
//	sql = "SELECT PID\n";
//	sql += "FROM Prn_PrintJob\n";
//	sql += "WHERE Inited = 1\n";// 已经初始化
//	sql += "      AND Printed <> 1\n";// 未打印
//	sql += String.format("      AND CurPrinterPID = %d\n", printer.getPID());// 分配给本打印机
//	sql += "      AND (strftime('%s','now') - strftime('%s',CreateTime))/(60*60) <= 4\n";// 未超时
//	sql += "ORDER BY CreateTime ASC\n";
//	Long pid = ComDateUtil.toLong(spring.executeSqlRetObj(sql));
//	if (pid == null) {
//		return;
//	}
//
//	String whereSql = String.format("where pID = %d\n", pid);
//	Prn_PrintJob job = (Prn_PrintJob) spring.loadBean("Prn_PrintJob", whereSql);
//	if (job == null) {
//		return;
//	}
//
//	Boolean processSucc = ProcessJob(job);
//	if (processSucc) {// 打印成功
//		if (job.getXfcpName() == null) {
//			sql = "DELETE FROM Prn_PrintJobItem\n";
//			sql += String.format("WHERE Job = %d", job.getPID());
//			spring.executeUpdateSql(sql);
//			sql = "DELETE FROM Prn_PrintJob\n";
//			sql += String.format("WHERE PID = %d", job.getPID());
//			spring.executeUpdateSql(sql);
//		} else {
//			sql = "UPDATE Prn_PrintJob\n";
//			sql += "SET Printed = 1\n";
//			// sql +=
//			// "    ,PrintTime = datetime(strftime('%s','now'), 'unixepoch', 'localtime')\n";
//			sql += String.format("    ,PrintTime = %s\n", DateUtil.strftimeNow());
//			sql += String.format("WHERE PID = %d\n", job.getPID());
//			spring.executeUpdateSql(sql);
//			// 将打印任务转移到扩展表
//			BeanCopyer bc = new BeanCopyer();
//			// bc.setSessionFactory(getSessionFactory());
//			bc.copy("Prn_PrintJob", "", "Ex", job.getPID());
//		}
//
//	} else {// 打印失败
//		sql = "UPDATE Prn_PrintJob\n";
//		sql += "SET CurPrinterPID = NULL\n";
//		sql += String.format("WHERE PID = %d\n", job.getPID());
//		spring.executeUpdateSql(sql);
//	}
}

/**
 * 处理打印任务(文件模式)
 * 
 * @return
 */
private void handleJobsWithFile() {
	// 获取需要处理的打印任务
	String dirPath = PrnJobManager.getPrinterDir(getPrinter().getID());
	File dir = new File(dirPath);
	List<File> files = new Vector<File>();
	for (File f : dir.listFiles()) {
		files.add(f);
	}
	Collections.sort(files, new ComparatorJobFile());
	for (File jobFileTmp : files) {
		String jobFileName = jobFileTmp.getName();
		if (jobFileName.endsWith(".job")) {
			handleJobsWithFileOneJob(jobFileTmp);
		} else if (jobFileName.endsWith(".tmp")) {
			long timeGap = System.currentTimeMillis() - PrnJobManager.genTimeInJobName(jobFileName);
			if (timeGap >= PrnJobManager.g_overTime) {// 删除生成时间超过1小时的文件
				logger.error(String.format("Rmv %s case of over time", jobFileTmp.getAbsoluteFile()));
				jobFileTmp.delete();
			}
		}
	}
}

/**
 * 处理打印任务(文件模式)
 * 
 * @return
 */
private void handleJobsWithFileOneJob(File jobFile) {
    //TODO
}

private PrintJobHandlerBase getJobHandler() {
	if (jobHandler != null) {
		return jobHandler;
	}
	// 根据打印机的类型，获取相应的打印机
//	if (printer.getType() == PrinterType.Com || printer.getType() == PrinterType.OPOS_LPT
//				|| printer.getType() == PrinterType.OPOS_USB || printer.getType() == PrinterType.OPOS_NET) {
//		jobHandler = new PortHandler();
//	} else {
//		if (printer.getPrnLabel()) {
//			jobHandler = new LabelHandler();
//		} else {
//			jobHandler = new GraphicsHandler();
//		}
//	}
//	try {
//		// jobHandler.setSessionFactory(getSessionFactory());
//		jobHandler.setPrinterEx(this);
//		jobHandler.afterPropertiesSet();
//	} catch (Exception e) {
//		logger.error(StackTraceToString.getExceptionTrace(e));
//	}
	return jobHandler;
}

/**
 * 更新当前打印任务的打印次数
 */
private void updateJobPrintCount(Prn_PrintJob job) {
	String sql = "";
	sql += "UPDATE Prn_PrintJob\n";
	sql += "SET PrintCount = ifnull(PrintCount,0) + 1\n";
	sql += String.format("WHERE PID = %s\n", job.getPID());
//	SpringContextAdapter.getInstance().executeUpdateSql(sql);
}

private void updatePrinterStatus(PrintJobHandlerBase.PrinterStatus status) {
	if (status.equals(getCurStatus())) {// 状态一致不需要更新
		return;
	}
	setCurStatus(status);

	String str = "ZC";
	if (status == PrintJobHandlerBase.PrinterStatus.FAULT) {
		str = "GZ";
	}
	String sql = "UPDATE Prn_Printer\n";
	sql += String.format("SET Status = '%s'\n", str);
	sql += String.format("WHERE PID = %d\n", getPrinter().getPID());
//	SpringContextAdapter.getInstance().executeUpdateSql(sql);
}

private void setCurStatus(PrintJobHandlerBase.PrinterStatus curStatus) {
	synchronized (lockOfCurStatus) {
		this.curStatus = curStatus;
	}

}

private PrintJobHandlerBase.PrinterStatus getCurStatus() {
	synchronized (lockOfCurStatus) {
		return curStatus;
	}
}

private PrintJobHandlerBase.PrinterStatus curStatus;// 当前状态
private Object lockOfCurStatus = new Object();

private long getJobCount() {
	synchronized (lockOfJobCount) {
		return jobCount;
	}
}

public void incJobCount() {
	synchronized (lockOfJobCount) {
		jobCount++;
	}
}

public void decJobCount() {
	synchronized (lockOfJobCount) {
		jobCount--;
		if (jobCount < 0) {
			jobCount = 0;
		}
	}
}

/**
 * 打印机名称是默认打印机，但系统没有安装打印机的话，不需要打印
 * 
 * @return
 */
private Boolean getNotNeedPrn() {
	synchronized (lockNotNeedPrn) {
		if (notNeedPrn != null) {
			return notNeedPrn;
		}
		if (printer.getType() == PrinterType.Com) {
			notNeedPrn = false;
			return notNeedPrn;
		}
		String defName = "默认打印机";
		if (!defName.equals(printer.getPhysicsName()) && !"默认打印机".equals(printer.getPhysicsName())) {
			notNeedPrn = false;
			return notNeedPrn;
		}

		PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
		if (ps != null) {
			notNeedPrn = false;
			return notNeedPrn;
		}

		logger.error(String.format(
					"[%s] is defult printer,but there is not any printer in system, so all print job will be deleted",
					printer.getName()));
		notNeedPrn = true;
		return notNeedPrn;
	}
}

private long jobCount = 1;// 打印任务信号量
private Object lockOfJobCount = new Object();

// 当前正在使用本打印机的队列
//private PrinterQueueEx curQueue;

private Prn_Printer printer;
private PrintJobHandlerBase jobHandler;
// 打印机名称是默认打印机，但系统没有安装打印机的话，不需要打印
private Boolean notNeedPrn;
private Object lockNotNeedPrn = new Object();

private SessionFactory sessionFactory;
}