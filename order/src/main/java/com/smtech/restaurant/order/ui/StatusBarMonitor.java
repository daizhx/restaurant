package com.smtech.restaurant.order.ui;

import com.smtech.restaurant.common.StackTraceToString;
import com.smtech.swing.common.btns.ButtonWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 状态栏监控器
 */
@Component
public class StatusBarMonitor implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		(new Thread(new Task())).start();
		connectMonitor();
	}

	private class Task implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
					updateStatusbars();
				} catch (Exception e) {
					logger.error(StackTraceToString.getExceptionTrace(e));
				}
			}
		}
	}

	/**
	 * 网络连接监控
	 */
	private void connectMonitor() {
		ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
		exec.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				updateConnectStatus();
			}

		}, 0, 10, TimeUnit.SECONDS);
	}

	private boolean isConnect() {
		boolean connect = false;
		Runtime runtime = Runtime.getRuntime();
		Process process;

		try {
			process = runtime.exec("ping " + "www.baidu.com");
			InputStream is = process.getInputStream();
			InputStreamReader isReader = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isReader);
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			isReader.close();
			br.close();
			if (null != sb && !sb.toString().equals("")) {
				if (sb.toString().indexOf("TTL") > 0) {
					// 网络畅通
					connect = true;
				} else {
					// 网络不畅通
					connect = false;
				}
			}
		} catch (IOException e) {
			logger.error(StackTraceToString.getExceptionTrace(e));
		}

		return connect;
	}

	public void addTimeLable(JLabel l) {
		addTimeLable(new Object[] { l, null });
	}

	public void addTimeLable(final Object[] l) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				timeLabels.add(l);
			}
		});
	}

	public void addUserLable(final JLabel l) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				userLabels.add(l);
			}
		});
	}

	public void addCanDuanLable(final JLabel l) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				canDuanLabels.add(l);
			}
		});
	}

	public void addWiFiBtn(final ButtonWrapper wifiBtn) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				wifiBtns.add(wifiBtn);
			}

		});
	}

	private void updateStatusbars() {
		// 時間
		final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
		setStrTime(formatter.format(cal.getTime()));

		// 餐段
		String curYcsd = "";
//		KaiTaiAction kttAction = getActionManager().getKaiTaiAction();
//		if (kttAction != null) {
//			Object[] canduan = kttAction.getDanQianCanDuan();
//			if (canduan != null) {
//				curYcsd = (String) canduan[1];
//			}
//		}

		String strCanDuanTmp = "班次";

		// 操作員
//		XiTongYongHu currUser = GlobalInfo.getInstance().getUser();
		String strUserTmp = "";
		// 班次
		setStrCanDuan(strCanDuanTmp);
		setStrUser(strUserTmp);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				for (Object[] v : timeLabels) {
					JLabel l = (JLabel) v[0];
					SimpleDateFormat sdf = (SimpleDateFormat) v[1];
					if (sdf != null) {
						l.setText(sdf.format(cal.getTime()));
					} else {
						l.setText(getStrTime());
					}

				}
				for (JLabel l : userLabels) {
					l.setText(getStrUser());
				}
				for (JLabel l : canDuanLabels) {
					l.setText(getStrCanDuan());
				}
			}
		});

	}

	/**
	 * 更新网络连接状态
	 * 
	 * 通过不断ping www.baidu.com这一个动作来标识网络是否联通 由于是一个IO流操作，防止阻塞其他线程的UI更新，把这个动作放到
	 * 单独的线程里面更新
	 */
	private void updateConnectStatus() {
		setConnect(isConnect());
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				for (ButtonWrapper wifiBtn : wifiBtns) {
					if (getConnect()) {
						wifiBtn.setIcon("wifi.png", 20, 20);
					} else {
						wifiBtn.setIcon("wifi_duanwang.png", 20, 20);
					}
				}

			}

		});
	}




	public void setStrTime(String strTime) {
		synchronized (lockOfPara) {
			this.strTime = strTime;
		}
	}

	public String getStrTime() {
		synchronized (lockOfPara) {
			return strTime;
		}
	}

	public void setStrCanDuan(String strCanDuan) {
		synchronized (lockOfPara) {
			this.strCanDuan = strCanDuan;
		}
	}

	public String getStrCanDuan() {
		synchronized (lockOfPara) {
			return strCanDuan;
		}
	}

	public void setStrUser(String strUser) {
		synchronized (lockOfPara) {
			this.strUser = strUser;
		}
	}

	public String getStrUser() {
		synchronized (lockOfPara) {
			return strUser;
		}
	}

	public void setConnect(boolean connect) {
		synchronized (lockOfPara) {
			this.connect = connect;
		}
	}

	public boolean getConnect() {
		synchronized (lockOfPara) {
			return connect;
		}
	}

	private String strTime = "";
	private String strCanDuan = "";
	private String strUser = "";
	private boolean connect = false;
	private final Object lockOfPara = new Object();

	private final List<Object[]> timeLabels = new Vector<Object[]>();
	private final List<JLabel> userLabels = new Vector<JLabel>();
	private final List<JLabel> canDuanLabels = new Vector<JLabel>();
	private final List<ButtonWrapper> wifiBtns = new Vector<ButtonWrapper>(); // wifi状态
	protected Logger logger = LoggerFactory.getLogger(StatusBarMonitor.class); // 日志类
}