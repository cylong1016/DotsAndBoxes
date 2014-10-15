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
		 * �ر�ͼƬ�����Ͻ�����
		 */
		public int exitX = 0;
		public int exitY = 0;
		/**
		 * ��ùر�ͼƬ�Ŀ�͸�
		 */
		public final int WINDOW_EXIT_H = AllImages.WINDOW_EXIT.getHeight(null);
		public final int WINDOW_EXIT_W = AllImages.WINDOW_EXIT.getWidth(null);
		/**
		 * �ж��Ƿ��ƶ����رհ�ť��
		 */
		public boolean isMoveExit = false;
		/**
		 * �رչ���������ŵĴ���
		 */
		public int TIMES_EXIT = 0;

		public PanelJoinHome (FrameJoinHome frameJoinHome) {
			this.frameJoinHome = frameJoinHome;
			this.setLayout(null);
			this.setSize(300, 200);
			this.setDragable();
			// ���ùرհ�ť������
			this.exitX = getWidth() - WINDOW_EXIT_W - 1;
			this.exitY = 1;
			// ����panelΪ͸��
			this.setOpaque(false);
			// ���ò��ֹ���Ϊ���Բ���
			this.setLayout(null);
			// ע����������
			this.addMouseListener(this);
			// ע������ƶ�������
			this.addMouseMotionListener(this);
			new Thread(this).start();
		}

		public void paint (Graphics g) {
			g.drawImage(AllImages.IMG_IP_ERROR, 0, 0, getWidth(), getHeight(), this);
			// �����رհ�ť
			g.drawImage(AllImages.WINDOW_EXIT, exitX, exitY, null);
			if (isMoveExit) {
				g.drawImage(AllImages.WINDOW_EXIT1, exitX, exitY, null);
			}
		}

		public Point loc = null;
		public Point tmp = null;
		public boolean isDragged = false;

		/**
		 * ���ÿ����϶�����
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
			// �ж�����Ƿ��ƶ����رհ�ť�ķ�Χ��
			if (arg0.getX() > exitX && arg0.getX() < exitX + WINDOW_EXIT_W && arg0.getY() > exitY && arg0.getY() < exitY + WINDOW_EXIT_H) {
				isMoveExit = true;
				GameUtil.startMoveButtonSound();
			} else {
				isMoveExit = false;
				TIMES_EXIT = 0;
			}
		}

		/**
		 * �߳���Ĺرպ���С����ť�ķ���
		 */
		protected void isClick () {
			// �ж��Ƿ񲥷Ź���ƶ����ر��ϵ�����
			if (isMoveExit && TIMES_EXIT == 0) {
				// �ж���Ϸ�Ƿ���
				if (!DataConfig.isMute()) {
					GameUtil.startMoveButtonSound();
					TIMES_EXIT++;
				}
			}

		}

		@Override
		public void mouseClicked (MouseEvent arg0) {
			// �ж�����Ƿ����رհ�ť
			if (arg0.getX() > exitX && arg0.getX() < exitX + WINDOW_EXIT_W && arg0.getY() > exitY && arg0.getY() < exitY + WINDOW_EXIT_H) {
				frameJoinHome.setVisible(false);
			}
		}

		/**
		 * �������ַ�����ʵ��λ����������0����
		 * 
		 * @param x
		 *            ���Ͻ�x����
		 * @param y
		 *            ���Ͻ�y����
		 * @param num
		 *            ��Ҫ��ʾ������
		 * @param maxBit
		 *            ���λ��
		 * @param g
		 *            ����
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
