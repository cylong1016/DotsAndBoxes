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
 * ��������ʱ��ĵȴ����
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
		 * �ر�ͼƬ�����Ͻ�����
		 */
		private int exitX = 0;
		private int exitY = 0;
		/**
		 * ��ùر�ͼƬ�Ŀ�͸�
		 */
		private final int WINDOW_EXIT_H = AllImages.WINDOW_EXIT.getHeight(null);
		private final int WINDOW_EXIT_W = AllImages.WINDOW_EXIT.getWidth(null);
		/**
		 * �ж��Ƿ��ƶ����رհ�ť��
		 */
		private boolean isMoveExit = false;
		/**
		 * �رչ���������ŵĴ���
		 */
		private int TIMES_EXIT = 0;

		public PanelCreateHome (DialogCreateHome frameCreateHome) {
			this.frameCreateHome = frameCreateHome;
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
			g.drawImage(AllImages.IMG_CONNECT_BG, 0, 0, getWidth(), getHeight(), this);
			// �����رհ�ť
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
						loc = new Point(frameCreateHome.getLocation().x + e.getX() - tmp.x, frameCreateHome.getLocation().y + e.getY() - tmp.y);
						frameCreateHome.setLocation(loc);
					}
				}
			});
		}

		@Override
		public void mouseMoved (MouseEvent arg0) {
			// �ж�����Ƿ��ƶ����رհ�ť�ķ�Χ��
			if (arg0.getX() > exitX && arg0.getX() < exitX + WINDOW_EXIT_W && arg0.getY() > exitY && arg0.getY() < exitY + WINDOW_EXIT_H) {
				isMoveExit = true;
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
