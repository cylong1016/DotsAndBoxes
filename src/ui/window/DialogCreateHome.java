package ui.window;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JDialog;
import javax.swing.JPanel;

import ui.AllImages;
import util.GameUtil;
import data.DataConfig;

/**
 * 创建房间时候的等待面板
 * 
 * @author cylong
 * @version May 25, 2014 12:00:01 AM
 */
public class DialogCreateHome extends JDialog {

	public static void main (String[] args) {
		new DialogCreateHome();
	}

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private PanelCreateHome panelCreatHome;
	private int width = 0;
	private int maxWidth = 300;
	private int height = 200;

	public DialogCreateHome () {
		panelCreatHome = new PanelCreateHome(this);
		this.add(panelCreatHome);
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		this.setLayout(null);
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		new ExtendFrame().start();
	}

	private class ExtendFrame extends Thread {

		public void run () {
			while (true) {
				GameUtil.sleep(10);
				if (width < maxWidth) {
					width += 10;
				} else {
					width = maxWidth;
				}
				DialogCreateHome.this.setSize(width, height);
				DialogCreateHome.this.setLocationRelativeTo(null);
				if (width == maxWidth) {
					break;
				}
			}
		}
	}

	private class PanelCreateHome extends JPanel implements Runnable, MouseListener, MouseMotionListener {

		/** serialVersionUID */
		private static final long serialVersionUID = 1L;
		private DialogCreateHome frameCreateHome = null;
		/**
		 * 关闭图片的左上角坐标
		 */
		private int exitX = 0;
		private int exitY = 0;
		/**
		 * 获得关闭图片的宽和高
		 */
		private final int WINDOW_EXIT_H = AllImages.WINDOW_EXIT.getHeight(null);
		private final int WINDOW_EXIT_W = AllImages.WINDOW_EXIT.getWidth(null);
		/**
		 * 判断是否移动到关闭按钮上
		 */
		private boolean isMoveExit = false;
		/**
		 * 关闭光标声音播放的次数
		 */
		private int TIMES_EXIT = 0;

		public PanelCreateHome (DialogCreateHome frameCreateHome) {
			this.frameCreateHome = frameCreateHome;
			this.setLayout(null);
			this.setSize(300, 200);
			this.setDragable();
			// 设置关闭按钮的坐标
			this.exitX = getWidth() - WINDOW_EXIT_W - 1;
			this.exitY = 1;
			// 设置panel为透明
			this.setOpaque(false);
			// 设置布局管理为绝对布局
			this.setLayout(null);
			// 注册鼠标监听器
			this.addMouseListener(this);
			// 注册鼠标移动监听器
			this.addMouseMotionListener(this);
			new Thread(this).start();
		}

		public void paint (Graphics g) {
			g.drawImage(AllImages.IMG_CONNECT_BG, 0, 0, getWidth(), getHeight(), this);
			// 画出关闭按钮
			g.drawImage(AllImages.WINDOW_EXIT, exitX, exitY, null);
			if (isMoveExit) {
				g.drawImage(AllImages.WINDOW_EXIT1, exitX, exitY, null);
			}
			g.drawImage(AllImages.IMG_WAIT, 0, 0, this);
		}

		private Point loc = null;
		private Point tmp = null;
		private boolean isDragged = false;

		/**
		 * 设置可以拖动窗体
		 */
		protected void setDragable () {
			this.addMouseListener(new MouseAdapter() {

				public void mouseReleased (java.awt.event.MouseEvent e) {
					isDragged = false;
				}

				public void mousePressed (java.awt.event.MouseEvent e) {
					tmp = new Point(e.getX(), e.getY());
					isDragged = true;
				}

			});

			this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

				public void mouseDragged (java.awt.event.MouseEvent e) {
					if (isDragged) {
						loc = new Point(frameCreateHome.getLocation().x + e.getX() - tmp.x, frameCreateHome.getLocation().y + e.getY() - tmp.y);
						frameCreateHome.setLocation(loc);
					}
				}
			});
		}

		@Override
		public void mouseMoved (MouseEvent arg0) {
			// 判断鼠标是否移动到关闭按钮的范围内
			if (arg0.getX() > exitX && arg0.getX() < exitX + WINDOW_EXIT_W && arg0.getY() > exitY && arg0.getY() < exitY + WINDOW_EXIT_H) {
				isMoveExit = true;
			} else {
				isMoveExit = false;
				TIMES_EXIT = 0;
			}
		}

		/**
		 * 线程里的关闭和最小化按钮的方法
		 */
		protected void isClick () {
			// 判断是否播放光标移动到关闭上的声音
			if (isMoveExit && TIMES_EXIT == 0) {
				// 判断游戏是否静音
				if (!DataConfig.isMute()) {
					GameUtil.startMoveButtonSound();
					TIMES_EXIT++;
				}
			}

		}

		@Override
		public void mouseClicked (MouseEvent arg0) {
			// 判断鼠标是否点击关闭按钮
			if (arg0.getX() > exitX && arg0.getX() < exitX + WINDOW_EXIT_W && arg0.getY() > exitY && arg0.getY() < exitY + WINDOW_EXIT_H) {
				frameCreateHome.setVisible(false);
			}
		}

		/**
		 * @author cylong
		 * @version May 24, 2014 6:05:56 PM
		 */
		@Override
		public void run () {
			while (true) {
				GameUtil.sleep(20);
				this.isClick();
				this.repaint();
			}
		}

		@Override
		public void mouseDragged (MouseEvent e) {
		}

		@Override
		public void mousePressed (MouseEvent e) {
		}

		@Override
		public void mouseReleased (MouseEvent e) {
		}

		@Override
		public void mouseEntered (MouseEvent e) {
		}

		@Override
		public void mouseExited (MouseEvent e) {
		}
	}

}
