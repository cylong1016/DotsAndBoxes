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
 * 游戏结束后蹦出的面板
 * 
 * @author 陈云龙
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
		 * 窗体透明值
		 */
		private float hyalineValue = 0f;

		@Override
		public void run () {
			while (true) {
				GameUtil.sleep(10);
				if (hyalineValue <= 1.0f) {
					// 窗体透明（！！）（此处引用了com.sun.awt.AWTUtilities，需引包）
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
		
		public PanelGameOver(int winner,int score, DialogGameOver frameGameOver, PanelGame panelGame) {
			this.winner = winner;
			this.score = score;
			this.frameGameOver = frameGameOver;
			this.panelGame = panelGame;
			this.setLayout(null);
			this.setSize(347, 90);
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
		public void paint(Graphics g) {
			g.drawImage(AllImages.IMG_GAME_OVER_BG, 0, 0, this);
			if(winner == 1) {
				g.drawImage(AllImages.IMG_WINNER_PLAYER1, 0, 0, this);
			} else {
				g.drawImage(AllImages.IMG_WINNER_PLAYER2, 0, 0, this);
			}
			this.drawNumber(215, 49, score, 2, AllImages.IMG_NUM_GAME_OVER, AllImages.IMG_NUM_GAME_OVER_W,  AllImages.IMG_NUM_GAME_OVER_H, g);
			// 画出关闭按钮
			g.drawImage(AllImages.WINDOW_EXIT, exitX, exitY, null);
			if (isMoveExit) {
				g.drawImage(AllImages.WINDOW_EXIT1, exitX, exitY, null);
			}
			
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
						loc = new Point(frameGameOver.getLocation().x + e.getX() - tmp.x, frameGameOver.getLocation().y + e.getY() - tmp.y);
						frameGameOver.setLocation(loc);
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
				frameGameOver.setVisible(false);
				panelGame.setGameOver(false);
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
