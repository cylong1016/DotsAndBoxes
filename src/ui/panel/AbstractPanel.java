package ui.panel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.AllImages;
import ui.window.FrameGame;
import util.GameUtil;
import control.GameControl;
import data.DataConfig;
import data.DataGame;

/**
 * 自定义的Panel<br />
 * 【不忍吐槽java自带的】
 * 
 * @author 陈云龙
 * @since 2014 / 3 / 19 6 : 45　PM
 */
public abstract class AbstractPanel extends JPanel implements Runnable, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	/**
	 * 游戏主框架
	 */
	protected FrameGame frameGame;
	/**
	 * 游戏控制器
	 */
	protected GameControl gameControl = null;
	/**
	 * 游戏数据库
	 */
	protected DataGame dataGame = null;

	/**
	 * 获得关闭图片的宽和高
	 */
	protected static final int WINDOW_EXIT_H = AllImages.WINDOW_EXIT.getHeight(null);
	protected static final int WINDOW_EXIT_W = AllImages.WINDOW_EXIT.getWidth(null);
	/**
	 * 获得最小化图片的宽和高
	 */
	protected static final int WINDOW_MIN_H = AllImages.WINDOW_MIN.getHeight(null);
	protected static final int WINDOW_MIN_W = AllImages.WINDOW_MIN.getWidth(null);
	/**
	 * Panel的宽
	 */
	protected int width = FrameGame.WIDTH;

	/**
	 * Panel的高
	 */
	protected int height = FrameGame.HEIGHT;
	/**
	 * 关闭图片的左上角坐标
	 */
	protected int exitX = 0;
	protected int exitY = 0;
	/**
	 * 最小化图片的左上角坐标
	 */
	protected int minX = 0;
	protected int minY = 0;
	/**
	 * 判断是否移动到关闭按钮上
	 */
	protected boolean isMoveExit = false;
	/**
	 * 判断是否移动到最小化按钮上
	 */
	protected boolean isMoveMin = false;
	/**
	 * 关闭光标声音播放的次数
	 */
	protected static int TIMES_EXIT = 0;
	/**
	 * 最小化光标声音播放的次数
	 */
	protected static int TIMES_MIN = 0;
	/**
	 * 窗体透明值
	 */
	protected float hyalineValue = 0f;
	/** 画笔透明度 */
	protected float hyaline = 0.0f;

	/**
	 * 构造方法
	 * 
	 * @param frameGame
	 *            连接游戏主框架
	 * @param gameControl
	 *            连接游戏控制器
	 * @param dataGame
	 *            游戏数据
	 */
	public AbstractPanel (FrameGame frameGame, GameControl gameControl, DataGame dataGame) {
		// 将frameGame框架传给panelGame
		// 为了实现JFrame可以最小化和拖动等功能
		this.frameGame = frameGame;
		// 初始化游戏控制器
		this.gameControl = gameControl;
		// 初始化游戏数据
		this.dataGame = dataGame;
	}

	public AbstractPanel () {

	}

	/**
	 * 初始化Panel（其他）
	 */
	protected void initPanel () {
		// 设置Panel大小
		this.setSize(width, height);
		// 设置panel为透明
		this.setOpaque(false);
		// 设置布局管理为绝对布局
		this.setLayout(null);
		// 注册鼠标监听器
		this.addMouseListener(this);
		// 注册鼠标移动监听器
		this.addMouseMotionListener(this);
		// 设置关闭按钮的坐标
		this.exitX = this.width - WINDOW_EXIT_W - 1;
		this.exitY = 1;
		// 设置最小化按钮的坐标
		this.minX = this.width - WINDOW_EXIT_W - WINDOW_MIN_W - 10;
		this.minY = 1;
		// 可以移动窗口
		this.setDragable();
	}

	/**
	 * 初始化Panel（绘图）
	 * 
	 * @param g
	 *            一只画笔
	 */
	protected void initPanel (Graphics g) {
		// 一定要调用父类的方法【坑死我了！】
		super.paint(g);
		// 画出关闭按钮
		g.drawImage(AllImages.WINDOW_EXIT, exitX, exitY, null);
		if (isMoveExit) {
			g.drawImage(AllImages.WINDOW_EXIT1, exitX, exitY, null);
		}
		// 画出最小化按钮啊
		g.drawImage(AllImages.WINDOW_MIN, minX, exitY, null);
		if (isMoveMin) {
			g.drawImage(AllImages.WINDOW_MIN1, minX, exitY, null);
		}

	}
	/**
	 * 设置画笔有透明渐变效果
	 * 
	 * @param g
	 * @author cylong
	 * @version May 20, 2014 12:56:08 AM
	 */
	public void setPaintbrush (Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Graphics2D g2 = (Graphics2D) g;
		if (hyaline > 1) {
			hyaline = 1;
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, hyaline));
	}

	/**
	 * 增加画笔的透明度
	 * 
	 * @author cylong
	 * @version May 20, 2014 1:00:49 AM
	 */
	public void addHyaline () {
		hyaline += 0.1f;
		if (hyaline > 1) {
			hyaline = 1;
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
		// 判断是否播放光标移动到最小化上的声音
		if (isMoveMin && TIMES_MIN == 0) {
			// 判断游戏是否静音
			if (!DataConfig.isMute()) {
				GameUtil.startMoveButtonSound();
				TIMES_MIN++;
			}
		}
	}

	@Override
	public void paint (Graphics g) {
		super.paint(g);
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
					loc = new Point(frameGame.getLocation().x + e.getX() - tmp.x, frameGame.getLocation().y + e.getY() - tmp.y);
					frameGame.setLocation(loc);
				}
			}
		});
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
			// if (maxBit - i <= strNum.length()) {
			int index = strNum.length() - (maxBit - i);
			int bit = strNum.charAt(index) - '0';
			g.drawImage(number, x + w * i, y, x + w * (i + 1), y + h, bit * w, 0, (bit + 1) * w, h, null);
			// }
		}
	}

	public int getWidth () {
		return width;
	}

	public void setWidth (int width) {
		this.width = width;
	}

	public int getHeight () {
		return height;
	}

	public void setHeight (int height) {
		this.height = height;
	}

	@Override
	public void mouseClicked (MouseEvent arg0) {
		// 判断鼠标是否点击关闭按钮
		if (arg0.getX() > exitX && arg0.getX() < exitX + WINDOW_EXIT_W && arg0.getY() > exitY && arg0.getY() < exitY + WINDOW_EXIT_H) {
			GameUtil.startClickSound();
			System.exit(0);
		}
		// 判断鼠标是否点击最小化按钮
		if (arg0.getX() > minX && arg0.getX() < minX + WINDOW_MIN_W && arg0.getY() > minY && arg0.getY() < minY + WINDOW_MIN_H) {
			GameUtil.startClickSound();
			frameGame.setExtendedState(JFrame.ICONIFIED);
		}
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

	@Override
	public void mouseDragged (MouseEvent e) {
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
		// 判断鼠标是否移动到最小化按钮的范围内
		if (arg0.getX() > minX && arg0.getX() < minX + WINDOW_MIN_W && arg0.getY() > minY && arg0.getY() < minY + WINDOW_MIN_H) {
			isMoveMin = true;
		} else {
			isMoveMin = false;
			TIMES_MIN = 0;
		}
	}

}
