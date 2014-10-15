package ui.panel.start;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import sound.PlayWave;
import ui.AllImages;
import ui.label.LabelButton;
import ui.label.LabelFirstSecond;
import ui.label.LabelLevelChoose;
import ui.label.LabelSizeChoose;
import ui.panel.AbstractPanel;
import ui.window.FrameGame;
import util.GameUtil;
import control.GameControl;
import data.DataConfig;
import data.DataGame;

/**
 * 开始游戏后的选择
 * 
 * @author 陈云龙
 * @since 2014 / 4 / 7 4:27 AM
 */
public class PanelModeChoose extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * 单人游戏按钮
	 */
	private LabelButton singlePlayer;
	/**
	 * 双人游戏按钮
	 */
	private LabelButton twoPlayers;
	/**
	 * 联机对战
	 */
	private LabelButton towPlayersConnect;
	/**
	 * 特殊模式
	 */
	private LabelButton specialMode;
	/**
	 * 棋盘大小选择的按钮集合
	 */
	private LabelSizeChoose[] sizeChoose;
	/**
	 * 先后手选择的按钮集合
	 */
	private LabelFirstSecond[] firstSecondChoose;
	/**
	 * 难度选择的数组
	 */
	private LabelLevelChoose[] levelChoose;
	/**
	 * 按钮的左上角x坐标
	 */
	private int x = 510;
	/**
	 * 按钮左上角y坐标
	 */
	private int y = 120;
	/**
	 * 按钮的宽
	 */
	private int labelWidth = 223;
	/**
	 * 按钮的高
	 */
	private int labelHeight = 46;
	/**
	 * 两个按钮之间的间隙
	 */
	private int interval = 49;

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
	public PanelModeChoose (FrameGame frameGame, GameControl gameControl, DataGame dataGame) {
		super(frameGame, gameControl, dataGame);
		this.width = 800;
		this.height = 0;
		singlePlayer = new LabelButton(x, y, labelWidth, labelHeight, AllImages.IMG_SINGLE_PLAYER, gameControl, "SinglePlayer", true);
		this.add(singlePlayer);
		twoPlayers = new LabelButton(x, y + labelHeight + interval, labelWidth, labelHeight, AllImages.IMG_TWO_PLAYERS, gameControl, "TwoPlayers", true);
		this.add(twoPlayers);
		towPlayersConnect = new LabelButton(x, y + (labelHeight + interval << 1), labelWidth, labelHeight, AllImages.IMG_TWO_PLAYERS_CONNECT, gameControl, "TwoPlayersConnect", true);
		this.add(towPlayersConnect);
		specialMode = new LabelButton(x, y + (labelHeight + interval) * 3, labelWidth, labelHeight, AllImages.IMG_SPECIAL_MODE, gameControl, "SpcialMode", true);
		this.add(specialMode);
		// 初始化面板
		this.initPanel();
		this.setLocation((FrameGame.WIDTH - width) >> 1, (FrameGame.HEIGHT - maxHeight) >> 1);
		this.initLabelSizeChoose();
		this.initFirstSecondChoose();
		this.initLevelChoose();
		new Thread(this).start();
	}

	/**
	 * 初始化难度选择按钮
	 * 
	 * @author cylong
	 * @version May 19, 2014 10:28:47 PM
	 */
	private void initLevelChoose () {
		levelChoose = new LabelLevelChoose[AllImages.IMG_LEVEL_CHOOSE.length];
		for (int i = 0; i < levelChoose.length; i++) {
			levelChoose[i] = new LabelLevelChoose(152 + i * 81, 283, 62, 35, AllImages.IMG_LEVEL_CHOOSE[i], AllImages.IMG_SETTING_BG, dataGame, i + 1);
			this.add(levelChoose[i]);
		}
	}

	/**
	 * 先后手选择的按钮数组
	 * 
	 * @author cylong
	 * @version May 19, 2014 4:32:08 AM
	 */
	private void initFirstSecondChoose () {
		firstSecondChoose = new LabelFirstSecond[AllImages.IMG_FIRST_SECOND.length];
		for (int i = 0; i < firstSecondChoose.length; i++) {
			firstSecondChoose[i] = new LabelFirstSecond(193 + i * 81, 350, 62, 35, AllImages.IMG_FIRST_SECOND[i], AllImages.IMG_SETTING_BG, dataGame, i);
			this.add(firstSecondChoose[i]);
		}
	}

	/**
	 * 初始化棋盘大小选择的按钮数组
	 * 
	 * @author cylong
	 * @version May 18, 2014 12:26:29 AM
	 */
	private void initLabelSizeChoose () {
		sizeChoose = new LabelSizeChoose[AllImages.IMG_CHESS_BOARD_SIZE.length];
		for (int i = 0; i < sizeChoose.length; i++) {
			sizeChoose[i] = new LabelSizeChoose(112 + i * 81, 216, 62, 35, AllImages.IMG_CHESS_BOARD_SIZE[i], AllImages.IMG_SETTING_BG, dataGame, i + 6);
			this.add(sizeChoose[i]);
		}
	}

	/** 图片左上角y坐标 */
	private int ImgY = height;
	/** 画笔透明度 */
	private float hyaline;

	@Override
	public void paint (Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, hyaline));
		g2.drawImage(AllImages.IMG_MODEL_CHOOSE, 0, 0, this);
		super.paint(g);
		// 初始化面板
		this.initPanel(g);
	}

	/**
	 * 初始化Panel（绘图）
	 * 
	 * @param g
	 *            一只画笔
	 */
	protected void initPanel (Graphics g) {
		// 画出关闭按钮
		g.drawImage(AllImages.WINDOW_EXIT, exitX, exitY, null);
		if (isMoveExit) {
			g.drawImage(AllImages.WINDOW_EXIT1, exitX, exitY, null);
		}
	}

	private boolean isExit = false;

	@Override
	public void mouseClicked (MouseEvent arg0) {
		// 判断鼠标是否点击关闭按钮
		if (arg0.getX() > exitX && arg0.getX() < exitX + WINDOW_EXIT_W && arg0.getY() > exitY && arg0.getY() < exitY + WINDOW_EXIT_H) {
			// this.gameControl.restartMenu();
			this.isExit = true;
		}
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

	private int maxHeight = 600;
	private int expendSpeed = 30;

	@Override
	public void run () {
		while (true) {
			if (isExit && ImgY > 0) {
				this.ImgY -= expendSpeed;
			} else if (isExit) {
				this.gameControl.restartMenu();
				// 防止点击关闭按钮的时候总是有声音
				break;
			}
			this.isClick();
			this.repaint();
			hyaline += 0.03f;
			if (hyaline > 1) {
				hyaline = 1;
			}
			if (!isExit && height < maxHeight) {
				this.height += expendSpeed;
			}
			this.setSize(width, height);
			GameUtil.sleep(20);
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
				new PlayWave(DataConfig.getSounds() + "光标.wav").start();
				TIMES_EXIT++;
			}
		}
	}
}
