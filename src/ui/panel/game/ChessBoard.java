package ui.panel.game;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;

import ui.AllImages;
import ui.label.Dot;
import ui.label.Line;
import ui.label.Rect;
import ui.window.FrameGame;
import control.GameControl;
import data.DataConfig;
import data.DataGame;

/**
 * 游戏的棋盘
 * 
 * @author cylong
 * @since 2014 / 3 / 19 5 : 30 PM
 * 
 */
public class ChessBoard extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	/**
	 * 游戏控制器
	 */
	private GameControl gameControl = null;
	/**
	 * 游戏数据
	 */
	private DataGame dataGame = null;
	/**
	 * 棋盘的宽
	 */
	private final int BOARD_WIDTH = 500;
	/**
	 * 棋盘的高
	 */
	private final int BOARD_HEIGHT = BOARD_WIDTH;
	/**
	 * 棋盘的内边距
	 */
	private final int PADDING = 60;
	/**
	 * 棋盘的大小（每行点的个数）
	 */
	private final int DOTS_NUMBER = DataConfig.getChessBoardSize();
	/**
	 * 棋盘点的集合
	 */
	private Dot[][] dots = new Dot[DOTS_NUMBER][DOTS_NUMBER];
	/**
	 * 点的图片大小
	 */
	private final int IMG_DOTS_SIZE = 180 / DOTS_NUMBER;
	/**
	 * 点的图片的内边距
	 */
	private final int IMG_DOTS_PADDING = IMG_DOTS_SIZE / 6;
	/**
	 * 各点之间的间隙
	 */
	private final int DOTS_INTERVAL = (BOARD_WIDTH - IMG_DOTS_SIZE * DOTS_NUMBER - (PADDING << 1)) / (DOTS_NUMBER - 1);
	/**
	 * 第一个点的x坐标
	 */
	private final int DOTS_X = PADDING;
	/**
	 * 第一个点的y坐标
	 */
	private final int DOTS_Y = PADDING;
	/**
	 * 棋盘的大小（每行横线的个数）
	 */
	private final int HORIZONTAL_LINES_NUMBER = DOTS_NUMBER - 1;
	/**
	 * 横线的宽
	 */
	private final int HORIZONTAL_LINES_WIDTH = DOTS_INTERVAL + (IMG_DOTS_PADDING << 1);
	/**
	 * 横线的高
	 */
	private final int HORIZONTAL_LINES_HEIGHT = IMG_DOTS_SIZE;
	/**
	 * 从DtoGame获得线的集合
	 */
	private Hashtable<String, Line> linesList = null;
	/**
	 * 第一条横线的x坐标
	 */
	private final int HORIZONTAL_LINES_X = IMG_DOTS_SIZE + DOTS_X - IMG_DOTS_PADDING;
	/**
	 * 第一条横线的y坐标
	 */
	private final int HORIZONTAL_LINES_Y = DOTS_Y;
	/**
	 * 各横线之间的间隙
	 */
	private final int HORIZONTAL_LINES_INTERVAL = IMG_DOTS_SIZE - (IMG_DOTS_PADDING << 1);
	/**
	 * 棋盘的大小（每行竖线的个数）
	 */
	private final int VERTICAL_LINES_NUMBER = DOTS_NUMBER - 1;
	/**
	 * 竖线的宽
	 */
	private final int VERTICAL_LINES_WIDTH = IMG_DOTS_SIZE;
	/**
	 * 竖线的高
	 */
	private final int VERTICAL_LINES_HEIGHT = DOTS_INTERVAL + (IMG_DOTS_PADDING << 1);
	/**
	 * 第一条竖线的x坐标
	 */
	private final int VERTICAL_LINES_X = DOTS_X;
	/**
	 * 第一条竖线的y坐标
	 */
	private final int VERTICAL_LINES_Y = IMG_DOTS_SIZE + DOTS_Y - IMG_DOTS_PADDING;
	/**
	 * 各竖线之间的间隙
	 */
	private final int VERTICAL_LINES_INTERVAL = IMG_DOTS_SIZE - (IMG_DOTS_PADDING << 1);
	/**
	 * 棋盘方块的集合
	 */
	private Hashtable<String, Rect> rectList = null;
	/**
	 * 方块的个数
	 */
	private final int RECT_NUMBER = DOTS_NUMBER - 1;
	/**
	 * 方块的大小
	 */
	private final int RECT_SIZE = HORIZONTAL_LINES_WIDTH;
	/**
	 * 第一个方块的x坐标
	 */
	private final int RECT_X = VERTICAL_LINES_X + VERTICAL_LINES_WIDTH - IMG_DOTS_PADDING;
	/**
	 * 第一个方块的y坐标
	 */
	private final int RECT_Y = HORIZONTAL_LINES_Y + HORIZONTAL_LINES_HEIGHT - IMG_DOTS_PADDING;
	/**
	 * 各方块之间的间隙
	 */
	private final int RECT_INTERVAL = IMG_DOTS_SIZE - (IMG_DOTS_PADDING << 1);
	/**
	 * 棋盘的左上角x坐标
	 */
	private final int CHESS_BOARD_X = 240;
	/**
	 * 棋盘的左上角y坐标
	 */
	private final int CHESS_BOARD_Y = 50;

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
	public ChessBoard (FrameGame frameGame, GameControl gameControl) {
		this.gameControl = gameControl;
		dataGame = gameControl.getServiceGame().getDataGame();
		linesList = dataGame.getLinesList();
		rectList = dataGame.getRectList();
		// 设置为绝对布局
		this.setLayout(null);
		// 设为透明
		this.setOpaque(false);
		// 设置大小和位置
		this.setBounds(CHESS_BOARD_X, CHESS_BOARD_Y, BOARD_WIDTH, BOARD_HEIGHT);
		// 添加棋盘上的点
		this.initDots();
		// 增加棋盘上的方块
		this.initRect();
		// 添加棋盘上的横线
		this.initHorizontalLines();
		// 添加棋盘上的竖线
		this.initVerticalLines();
	}

	/**
	 * 将方块添加到Panel上
	 */
	private void initRect () {
		for (int i = 0; i < RECT_NUMBER; i++) {
			for (int j = 0; j < RECT_NUMBER; j++) {
				Rect rect = new Rect(RECT_X + j * (RECT_INTERVAL + RECT_SIZE), RECT_Y + i * (RECT_INTERVAL + RECT_SIZE), RECT_SIZE);
				rect.setSerialNumber(Integer.toString(i) + Integer.toString(j));
				// 将方块加入到横线集合里
				this.rectList.put(Integer.toString(i) + Integer.toString(j), rect);
				// 将方块加入到Panel上
				this.add(rect);
			}
		}
	}

	/**
	 * 将竖向的线添加到Panel上
	 */
	private void initVerticalLines () {
		for (int i = 0; i < VERTICAL_LINES_NUMBER; i++) {
			for (int j = 0; j < DOTS_NUMBER; j++) {
				Line line = new Line(VERTICAL_LINES_X + j * (DOTS_INTERVAL + IMG_DOTS_SIZE), VERTICAL_LINES_Y + i * (VERTICAL_LINES_INTERVAL + VERTICAL_LINES_HEIGHT), VERTICAL_LINES_WIDTH,
						VERTICAL_LINES_HEIGHT, 1, gameControl);
				// 线的编号
				String serialNumber = 1 + Integer.toString(i) + Integer.toString(j);
				line.setSerialNumber(serialNumber);
				// 将横线加入到横线集合里
				this.linesList.put(serialNumber, line);
				line.addMouseListener(this);
				// 将横线加入到Panel上
				this.add(line);
				Hashtable<String, Rect> rectList = dataGame.getRectList();
				// 遍历Hashtable的key和value
				Iterator<String> it = rectList.keySet().iterator();
				// 将线加入到对应的方块内
				while (it.hasNext()) {
					String key = it.next();
					Rect rect = rectList.get(key);
					Vector<Line> lines = rect.getLines();
					char[] charKey = key.toCharArray();
					int[] intKey = { Integer.parseInt(String.valueOf(charKey[0])), Integer.parseInt(String.valueOf(charKey[1])) };
					if (i == intKey[0] && j == intKey[1]) {
						lines.add(line);
					}
					if (i == intKey[0] && j == (intKey[1] + 1)) {
						lines.add(line);
					}
				}
			}
		}

	}

	/**
	 * 将横向的线添加到Panel上
	 */
	private void initHorizontalLines () {
		for (int i = 0; i < DOTS_NUMBER; i++) {
			for (int j = 0; j < HORIZONTAL_LINES_NUMBER; j++) {
				Line line = new Line(HORIZONTAL_LINES_X + j * (HORIZONTAL_LINES_INTERVAL + HORIZONTAL_LINES_WIDTH), HORIZONTAL_LINES_Y + i * (DOTS_INTERVAL + IMG_DOTS_SIZE), HORIZONTAL_LINES_WIDTH,
						HORIZONTAL_LINES_HEIGHT, 0, gameControl);
				// 线的编号
				String serialNumber = 0 + Integer.toString(i) + Integer.toString(j);
				line.setSerialNumber(serialNumber);
				// 将横线加入到横线集合里
				this.linesList.put(serialNumber, line);
				line.addMouseListener(this);
				// 将横线加入到Panel上
				this.add(line);
				Hashtable<String, Rect> rectList = dataGame.getRectList();
				// 遍历Hashtable的key和value
				Iterator<String> it = rectList.keySet().iterator();
				// 将线加入到对应的方块内
				while (it.hasNext()) {
					String key = it.next();
					Rect rect = rectList.get(key);
					Vector<Line> lines = rect.getLines();
					char[] charKey = key.toCharArray();
					int[] intKey = { Integer.parseInt(String.valueOf(charKey[0])), Integer.parseInt(String.valueOf(charKey[1])), };
					if (i == intKey[0] && j == intKey[1]) {
						lines.add(line);
					}
					if (i == (intKey[0] + 1) && j == intKey[1]) {
						lines.add(line);
					}
				}
			}
		}
	}

	/**
	 * 将点添加到Panel上
	 * @author cylong
	 * @version May 22, 2014  9:26:52 PM
	 */
	private void initDots () {
		for (int i = 0; i < dots.length; i++) {
			for (int j = 0; j < dots[i].length; j++) {
				dots[i][j] = new Dot(DOTS_X + i * (DOTS_INTERVAL + IMG_DOTS_SIZE), DOTS_Y + j * (DOTS_INTERVAL + IMG_DOTS_SIZE), IMG_DOTS_SIZE);
				// 将点加入到panel里
				this.add(dots[i][j]);
			}
		}
	}

	/**
	 * @author cylong
	 * @version May 19, 2014  2:24:38 AM
	 */
	@Override
	public void paint(Graphics g) {
		// 画棋盘背景
		g.drawImage(AllImages.IMG_CHESS_BOARD_BG, 0, 0, this);
		super.paint(g);
	}
	@Override
	public void mouseClicked (MouseEvent e) {
		// 鼠标点击的线显示出来
		this.gameControl.getServiceGame().showLine(e.getSource());
		this.repaint();
	}

	@Override
	public void mouseEntered (MouseEvent e) {
		// 鼠标移动到标签上将线显示出来
		this.gameControl.getServiceGame().mouseEnteredLine(e.getSource());
		this.repaint();
	}

	@Override
	public void mouseExited (MouseEvent e) {
		// 鼠标离开标签上将线隐藏
		this.gameControl.getServiceGame().mouseExitedLine(e.getSource());
		this.repaint();
	}

	@Override
	public void mousePressed (MouseEvent e) {
	}

	@Override
	public void mouseReleased (MouseEvent e) {
	}

}
