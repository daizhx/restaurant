package com.smtech.restaurant.common.print;

import com.smtech.restaurant.entities.Prn_PrintJob;

import java.io.*;
import java.sql.Timestamp;

/**
 * 打印任务管理器
 */
public class PrnJobManager {

	/**
	 * 创建文件夹
	 *
	 * @param dirName
	 */
	public static void makeDir(String dirName) {
		File dir = new File(dirName);
		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
	}

	/**
	 * 获取存放打印任务的文件夹目录
	 * 
	 * @return
	 */
	public static String getPrnJobDir() {
		String dirName = String.format("./prnJobs/");
		makeDir(dirName);
		return dirName;
	}

	/**
	 * 获取打印队列对应的目录
	 * 
	 * @return
	 */
	public static String getPrnQueueDir(String queueID) {
		String dirName = String.format("%s/queues/%s", getPrnJobDir(), queueID);
		makeDir(dirName);
		return dirName;
	}

	/**
	 * 获取打印机对应的目录
	 * 
	 * @return
	 */
	public static String getPrinterDir(String printerId) {
		String dirName = String.format("%s/printers/%s", getPrnJobDir(), printerId);
		makeDir(dirName);
		return dirName;
	}

	public static void saveJob(Prn_PrintJob job) {
		String dirName = getPrnQueueDir(job.getPrnQueueName());
		String fileName = String.format("%s/%s", dirName, genJobNameWithTime(job.getPID()));
		saveJob(job, fileName);
	}

	public static void saveJob(Prn_PrintJob job, String fileName) {
		try {
			FileOutputStream ostream = new FileOutputStream(fileName);
			ObjectOutputStream p = new ObjectOutputStream(ostream);
			p.writeObject(job);
			p.flush();
			ostream.close();
			Timestamp time = job.getDelayPrintTime();
			if (time == null) {
				time = new Timestamp(System.currentTimeMillis());
			}
			renameFile(fileName, genJobNameWithoutTime(fileName), time.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Prn_PrintJob readJob(String fileName) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			ObjectInputStream oin = new ObjectInputStream(fis);
			Prn_PrintJob job = (Prn_PrintJob) oin.readObject();
			return job;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 文件重命名
	 *
	 * @param oldname 原来的文件名
	 * @param newname 新文件名
	 */
	public static void renameFile(String oldname, String newname) {
		File oldfile = new File(oldname);
		File newfile = new File(newname);
		oldfile.renameTo(newfile);
		newfile.setLastModified(oldfile.lastModified());
	}

	private static void renameFile(String oldname, String newname, long time) {
		File oldfile = new File(oldname);
		File newfile = new File(newname);
		oldfile.renameTo(newfile);
		newfile.setLastModified(time);
	}

	/**
	 * 把当前时间拼装到打印任务文件名中
	 * 
	 * @param jobPid
	 * @return
	 */
	public static String genJobNameWithTime(Long jobPid) {
		long time = System.currentTimeMillis();
		return String.format("%d_%d.tmp", jobPid, time);
	}

	/**
	 * 去除打印任务文件名中的时间信息
	 * 
	 * @param fileName
	 * @return
	 */
	public static String genJobNameWithoutTime(String fileName) {
		int idxLine = fileName.lastIndexOf("_");
		int idxPoint = fileName.lastIndexOf(".");
		if (idxLine == -1 || idxPoint == -1) {
			return fileName;
		}
		return String.format("%s.job", fileName.substring(0, idxLine));
	}

	/**
	 * 提取保存在文件名称中的时间信息
	 * 
	 * @param fileName
	 * @return
	 */
	public static long genTimeInJobName(String fileName) {
		int idxLine = fileName.indexOf("_");
		int idxPoint = fileName.indexOf(".");
		if (idxLine == -1 || idxPoint == -1) {
			return System.currentTimeMillis() - 1000 * 60 * 60 * 24;
		}
		try {
			String strTime = fileName.substring(idxLine + 1, idxPoint);
			return Long.valueOf(strTime);
		} catch (Exception e) {
			e.printStackTrace();
			return System.currentTimeMillis() - 1000 * 60 * 60 * 24;
		}
	}

	public static long g_overTime = 1000 * 60 * 60 * 4;

	public static void main(String[] args) {
		String jobName = genJobNameWithTime(Long.valueOf(1234));
		System.out.println(jobName);
		System.out.println(System.currentTimeMillis() - genTimeInJobName(jobName));

		jobName = genJobNameWithoutTime(jobName);
		System.out.println(jobName);

		// Prn_PrintJob job = PrnJobManager.readJob("D:/D1/45560.job");
		// logger.error(String.valueOf(job.getPID()));
		// for (Prn_PrintJobItem curItem : job.getItems()) {
		// if (curItem.getContentType() != PrintBillItemType.Text) {
		// continue;
		// }
		// String text = curItem.getPrintContent();
		// String widthFull = curItem.getWidth();
		// if (widthFull == null) {
		// widthFull = "100%";
		// }
		// String alignFull = curItem.getAlign();
		// if (alignFull == null) {
		// alignFull = "1";
		// }
		// String[] txts = text.split("\\|");
		// String[] widths = widthFull.split("\\|");
		// String[] aligns = alignFull.split("\\|");
		// if (txts.length != widths.length || widths.length != aligns.length) {
		// logger.error(String
		// .format("The line[%s] of style[%s] is not correct,content's size is[%d], "
		// + "but width's size is[%d], align's size is[%d],and text is [%s]",
		// curItem.getID(), "", txts.length,
		// widths.length, aligns.length, text));
		// }
		// }
	}

}