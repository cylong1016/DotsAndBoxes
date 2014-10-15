package ui.panel.game;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

import ui.AllImages;
import ui.label.Line;
import ui.label.PanelGameButton;
import ui.panel.AbstractPanel;
import ui.window.FrameGame;
import util.GameUtil;
import control.GameControl;
import data.DataGame;

/**
 * 游戏主界面的panel<br />
 * 继承了自 定义的PanelUserDefined
 * 
 * @author 陈云龙
 * @since 2014 / 3 / 19 9 : 46 AM
 */
public class PanelGame extends AbstractPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	/**
	 * 菜单面板
	 */
	private PanelGameMenu panelGameMenu = null;
	/**
	 * 背景面板
	 */
	private PanelBackground background = null;
	/**
	 * 游戏棋盘
	 */
	private ChessBoard chessBoard = null;
	/**
	 * 菜单面板的偏移量
	 */
	private int menuOffset = -250;
	/**
	 * 菜单面板的当前x坐标
	 */
	private int currentX = menuOffset;
	/**
	 * 菜单面板的最大x坐标
	 */
	private int maxX = 0;
	/**
	 * 菜单面板移动的速度
	 */
	private int expendSpeed = 25;
	/**
	 * 是否显示菜单
	 */
	private boolean isShowMenu = false;
	/**
	 * 线的集合
	 */
	private Hashtable<String, Line> LineList = null;
	/**
	 * 悔棋按钮
	 */
	private PanelGameButton takeBack = null;
	/**
	 * 声音开关按钮
	 */
	private PanelGameButton volume = null;
	/**
	 * 暂停按钮
	 */
	private PanelGameButton pause = null;
	/**
	 * 按钮大小
	 */
	private int buttonSize = 54;
	/**
	 * 是否返回主菜单
	 */
	private boolean isBack = false;
	/**
	 * 游戏是否结束
	 */
	private boolean isGameOver = false;
	
	/**
	 * @version May 24, 2014  7:02:57 PM
	 * @return the isGameOver
	 */
	public boolean isGameOver () {
		return this.isGameOver;
	}

	
	/**
	 * @version May 24, 2014  7:02:57 PM
	 * @param isGameOver the isGameOver to set
	 */
	public void setGameOver (boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	/**
	 * @param isBack
	 *            the isBack to set
	 */
	public void setBack (boolean isBack) {
		this.isBack = isBack;
	}

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
	public PanelGame (FrameGame frameGame, GameControl gameControl, DataGame dataGame) {
		super(frameGame, gameControl, dataGame);
		// 初始化面板
		super.initPanel();
		// 菜单面板
		this.panelGameMenu = new PanelGameMenu(currentX, 0, width, height, gameControl, dataGame);
		this.add(this.panelGameMenu);
		// 联机对战不可以悔棋和暂停
		if (dataGame.getGameMode() != 2) {
			// 悔棋按钮
			this.takeBack = new PanelGameButton(160, 212, buttonSize, buttonSize, AllImages.IMG_TAKE_BACK, AllImages.IMG_TAKE_BACK, gameControl, "TakeBack", true);
			this.add(takeBack);
			this.volume = new PanelGameButton(155, 278, buttonSize, buttonSize, AllImages.IMG_VOLUME_ON, AllImages.IMG_VOLUME_OFF, gameControl, "SetMute", true);
			this.add(volume);
			this.pause = new PanelGameButton(155, 337, buttonSize, buttonSize, AllImages.IMG_PAUSE, AllImages.IMG_START, gameControl, "SetPause", true);
			this.add(pause);
		}
		// 创建棋盘, 将玩家控制器装到棋盘上
		this.chessBoard = new ChessBoard(this.frameGame, gameControl);
		this.add(chessBoard);
		this.LineList = this.dataGame.getLinesList();
		// 背景面板
		this.background = new PanelBackground(width, height);
		this.add(background);
		// this.requestFocus();
		// 启动面板线程
		new Thread(this).start();
	}

	/**
	 * 为了使线有闪烁效果
	 */
	private int showLine = 0;

	@Override
	public void paint (Graphics g) {
		// 初始化面板
		this.initPanel(g);
		// 画边框图片
		g.drawImage(AllImages.IMG_PANEL_GAME_BORDER, 0, 0, this);
		if (this.dataGame.getGameMode() != 2) {
			// 让刚点击的线有闪烁效果
			if (this.dataGame.getJustShow() != null) {
				Line lineJustShow = this.LineList.get(this.dataGame.getJustShow());
				if (this.showLine % 50 == 0) {
					lineJustShow.setVisible(true);
				} else if (this.showLine % 25 == 0) {
					lineJustShow.setVisible(false);
				}
				if (lineJustShow.getPreShow() != null) {
					Line LinePrevShow = this.LineList.get(lineJustShow.getPreShow());
					if (!LinePrevShow.isVisible()) {
						LinePrevShow.setVisible(true);
					}
				}
			}
		}
		if (currentX == menuOffset) {
			// 画出玩家1分数
			super.drawNumber(130, 177, this.dataGame.getPlayer1().getScore(), 2, AllImages.IMG_NUM_SCORE,AllImages.IMG_NUM_SCORE_W, AllImages.IMG_NUM_SCORE_H, g);
			// 画出玩家1
			g.drawImage(AllImages.IMG_PANEL_GAME_PLAYER1, 62, 150, this);
			// 画出玩家2分数
			this.drawNumber(130, 422, this.dataGame.getPlayer2().getScore(), 2, AllImages.IMG_NUM_SCORE,AllImages.IMG_NUM_SCORE_W, AllImages.IMG_NUM_SCORE_H, g);
			// 画分数图片
			g.drawImage(AllImages.IMG_PANEL_GAME_SCORE, 60, 178, this);
			// 画出玩家2
			g.drawImage(AllImages.IMG_PANEL_GAME_PLAYER2, 62, 395, this);
			// 画分数图片
			g.drawImage(AllImages.IMG_PANEL_GAME_SCORE, 60, 423, this);
			if(dataGame.getGameMode() != 2) {
				// 画出倒计时
				this.drawNumber(50, 280, dataGame.getCountdown(), 2, AllImages.IMG_NUM_COUNTDOWN, AllImages.IMG_NUM_COUNTDOWN_W, AllImages.IMG_NUM_COUNTDOWN_H, g);
			}
		}
		if (isShowMenu || isGameOver) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, hyaline));
			g2.drawImage(AllImages.IMG_MENU_BG, 0, 0, this);
		}
	}

	@Override
	public void mouseMoved (MouseEvent arg0) {
		super.mouseMoved(arg0);
		if (arg0.getX() - 10 < this.maxX) {
			this.isShowMenu = true;
		} else if (isShowMenu && arg0.getX() > 250) {
			this.isShowMenu = false;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 * 
	 * @author cylong
	 * @version May 17, 2014 11:02:31 PM
	 */
	@Override
	public void run () {
		while (true) {
			// 返回主菜单的时候就退出线程
			if (this.isBack) {
				break;
			}
			GameUtil.sleep(20);
			this.repaint();
			// 使刚下的线闪动
			this.showLine++;
			if (this.showLine > 10000) {
				this.showLine = 0;
			}
			super.isClick();
			if ((this.isShowMenu && this.currentX <= this.maxX) || isGameOver) {
				this.currentX += this.expendSpeed;
				if (currentX > 0) {
					currentX = 0;
				}
			} else if ((!this.isShowMenu && this.currentX > menuOffset) || !isGameOver) {
				this.currentX -= this.expendSpeed;
				if (currentX < menuOffset) {
					currentX = menuOffset;
				}
			}
			this.panelGameMenu.setLocation(currentX, 0);
			if (isShowMenu || isGameOver) {
				hyaline += 0.1f;
				if (hyaline > 1) {
					hyaline = 1;
				}
			} else {
				hyaline -= 0.04f;
				if (hyaline < 0) {
					hyaline = 0f;
				}
			}
		}
	}

}
