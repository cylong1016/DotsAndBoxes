package ui.window;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JDialog;
import javax.swing.JPanel;

import ui.AllImages;
import ui.panel.game.PanelGame;
import util.GameUtil;

import com.sun.awt.AWTUtilities;

import data.DataConfig;

/**
 * ��Ϸ������ĳ������
 * 
 * @author ������
 * @since 2014 / 4 / 13 1:09 PM
 */
public class DialogGameOver extends JDialog {

	private static final long serialVersionUID = 1L;
	private PanelGameOver panelGameOver;
	private int width = 0;
	private int maxWidth = 347;
	private int height = 90;
	
	public DialogGameOver (int winner, int score, PanelGame panelGame) {
		this.panelGameOver = new PanelGameOver(winner, score, this, panelGame);
		this.add(panelGameOver);
		this.setLayout(null);
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		new HyalineValue().start();
		new ExtendFrame().start();
		this.setVisible(true);
	}

	

	private class HyalineValue extends Thread {

		/**
		 * ����͸��ֵ
		 */
		private float hyalineValue = 0f;

		@Override
		public void run () {
			while (true) {
				GameUtil.sleep(10);
				if (hyalineValue <= 1.0f) {
					// ����͸�������������˴�������com.sun.awt.AWTUtilities����������
					AWTUtilities.setWindowOpacity(DialogGameOver.this, hyalineValue);
					hyalineValue += 0.0125f;
				} else {
					hyalineValue = 1.0f;
					AWTUtilities.setWindowOpacity(DialogGameOver.this, hyalineValue);
					break;
				}
				
			}
		}
	}
	private class ExtendFrame extends Thread {
		public void run() {
			while(true) {
				GameUtil.sleep(10);
				if(width < maxWidth) {
					width += 10;
				} else {
					width = maxWidth;
				}
				DialogGameOver.this.setSize(width, height);
				DialogGameOver.this.setLocationRelativeTo(null);
				if(width == maxWidth) {
					break;
				}
			}
		}
	}
	private class PanelGameOver extends JPanel implements Runnable, MouseListener, MouseMotionListener {
		
		/** serialVersionUID */
		private static final long serialVersionUID = 1L;
		private int winner;
		private int score;
		private DialogGameOver frameGameOver;
		private PanelGame panelGame;
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
		
		public PanelGameOver(int winner,int score, DialogGameOver frameGameOver, PanelGame panelGame) {
			this.winner = winner;
			this.score = score;
			this.frameGameOver = frameGameOver;
			this.panelGame = panelGame;
			this.setLayout(null);
			this.setSize(347, 90);
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
		public void paint(Graphics g) {
			g.drawImage(AllImages.IMG_GAME_OVER_BG, 0, 0, this);
			if(winner == 1) {
				g.drawImage(AllImages.IMG_WINNER_PLAYER1, 0, 0, this);
			} else {
				g.drawImage(AllImages.IMG_WINNER_PLAYER2, 0, 0, this);
			}
			this.drawNumber(215, 49, score, 2, AllImages.IMG_NUM_GAME_OVER, AllImages.IMG_NUM_GAME_OVER_W,  AllImages.IMG_NUM_GAME_OVER_H, g);
			// �����رհ�ť
			g.drawImage(AllImages.WINDOW_EXIT, exitX, exitY, null);
			if (isMoveExit) {
				g.drawImage(AllImages.WINDOW_EXIT1, exitX, exitY, null);
			}
			
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
						loc = new Point(frameGameOver.getLocation().x + e.getX() - tmp.x, frameGameOver.getLocation().y + e.getY() - tmp.y);
						frameGameOver.setLocation(loc);
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
				frameGameOver.setVisible(false);
				panelGame.setGameOver(false);
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
		protected void drawNumber (int x, int y, int num, int maxBit, Image number, int w, int h, Graphics g) {
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
		 * (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 *
		 * @author cylong
		 * @version May 24, 2014  6:05:56 PM
		 */
		@Override
		public void run () {
			while(true) {
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
