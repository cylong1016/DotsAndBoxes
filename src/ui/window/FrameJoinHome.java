package ui.window;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.AllImages;
import util.GameUtil;
import data.DataConfig;

/**
 * 
 * @author cylong
 * @version May 25, 2014 1:32:00 PM
 */
public class FrameJoinHome extends JFrame {

	public static void main (String[] args) {
		new FrameJoinHome();
	}

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	public int width = 0;
	public int maxWidth = 300;
	public int height = 200;
	private PanelJoinHome panelJoinHome;

	public FrameJoinHome () {
		panelJoinHome = new PanelJoinHome(this);
		this.add(panelJoinHome);
		this.setUndecorated(true);
		this.setLayout(null);
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		new ExtendFrame().start();
	}

	public class ExtendFrame extends Thread {

		public void run () {
			while (true) {
				GameUtil.sleep(10);
				if (width < maxWidth) {
					width += 10;
				} else {
					width = maxWidth;
				}
				FrameJoinHome.this.setSize(width, height);
				FrameJoinHome.this.setLocationRelativeTo(null);
				if (width == maxWidth) {
					break;
				}
			}
		}
	}

	public class PanelJoinHome extends JPanel implements Runnable, MouseListener, MouseMotionListener {

		/** serialVersionUID */
		public static final long serialVersionUID = 1L;
		public FrameJoinHome frameJoinHome = null;
		/**
		 * 关闭图片的左上角坐标
		 */
		public int exitX = 0;
		public int exitY = 0;
		/**
		 * 获得关闭图片的宽和高
		 */
		public final int WINDOW_EXIT_H = AllImages.WINDOW_EXIT.getHeight(null);
		public final int WINDOW_EXIT_W = AllImages.WINDOW_EXIT.getWidth(null);
		/**
		 * 判断是否移动到关闭按钮上
		 */
		public boolean isMoveExit = false;
		/**
		 * 关闭光标声音播放的次数
		 */
		public int TIMES_EXIT = 0;

		public PanelJoinHome (FrameJoinHome frameJoinHome) {
			this.frameJoinHome = frameJoinHome;
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
			g.drawImage(AllImages.IMG_IP_ERROR, 0, 0, getWidth(), getHeight(), this);
			// 画出关闭按钮
			g.drawImage(AllImages.WINDOW_EXIT, exitX, exitY, null);
			if (isMoveExit) {
				g.drawImage(AllImages.WINDOW_EXIT1, exitX, exitY, null);
			}
		}

		public Point loc = null;
		public Point tmp = null;
		public boolean isDragged = false;

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
						loc = new Point(frameJoinHome.getLocation().x + e.getX() - tmp.x, frameJoinHome.getLocation().y + e.getY() - tmp.y);
						frameJoinHome.setLocation(loc);
					}
				}
			});
		}

		@Override
		public void mouseMoved (MouseEvent arg0) {
			// 判断鼠标是否移动到关闭按钮的范围内
			if (arg0.getX() > exitX && arg0.getX() < exitX + WINDOW_EXIT_W && arg0.getY() > exitY && arg0.getY() < exitY + WINDOW_EXIT_H) {
				isMoveExit = true;
				GameUtil.startMoveButtonSound();
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
				frameJoinHome.setVisible(false);
			}
		}

		/**
		 * 画出数字方法，实际位数不足则用0代替
		 * 
		 * @param x
		 *            左上角x坐标
		 * @param y
		 *            左上角y坐标
		 * @param num
		 *            需要显示的数字
		 * @param maxBit
		 *            最大位数
		 * @param g
		 *            画笔
		 */
		public void drawNumber (int x, int y, int num, int maxBit, Image number, int w, int h, Graphics g) {
			String strNum = Integer.toString(num);
			for (int i = strNum.length(); i < maxBit; i++) {
				strNum = "0" + strNum;
			}
			for (int i = 0; i < strNum.length(); i++) {
				int index = strNum.length() - (maxBit - i);
				int bit = strNum.charAt(index) - '0';
				g.drawImage(number, x + w * i, y, x + w * (i + 1), y + h, bit * w, 0, (bit + 1) * w, h, null);
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

		/**
		 * @author cylong
		 * @version May 24, 2014 6:48:18 PM
		 */
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
