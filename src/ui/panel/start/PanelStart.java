package ui.panel.start;

import java.awt.Graphics;

import ui.AllImages;
import ui.label.LabelButton;
import ui.label.LabelIcon;
import ui.panel.AbstractPanel;
import ui.window.FrameGame;
import util.GameUtil;
import control.GameControl;
import data.DataGame;

/**
 * 游戏开始菜单<br />
 * 继承了自定义的PanelUserDefined
 * 
 * @author 陈云龙
 * @since 2014 / 3 / 22 4 : 02 AM
 * 
 */
public class PanelStart extends AbstractPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * 开始游戏按钮
	 */
	private LabelButton startGame = null;
	/**
	 * 帮助按钮
	 */
	private LabelButton help = null;

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
	public PanelStart(FrameGame frameGame, GameControl gameControl, DataGame dataGame) {
		super(frameGame, gameControl, dataGame);
		// 初始化面板
		super.initPanel();
		// 启动面板线程
		new Thread(this).start();
		new AddIcon().start();
	}

	private int x = 38;
	private int y = 145;
	private int interval = 90;
	/**
	 * 
	 * @author cylong
	 * @version May 30, 2014  2:29:22 AM
	 */
	private void addIcon () {
		int index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				icon = new LabelIcon(x + j * interval, y + i * (interval + 12), 70, 70, AllImages.IMG_ICON[index]);
				this.add(icon);
				index++;
				GameUtil.sleep(80);
			}
		}
	}

	private class AddIcon extends Thread{
		public void run() {
			while(true) {
				if(icon == null) {
					PanelStart.this.addIcon();
				} else {
					break;
				}
			}
		}
	}
	/** 动态图片的左上角x坐标 */
	private int mvPictureX = 0;

	@Override
	public void paint(Graphics g) {
		// 画背景静态的图片【心】
		g.drawImage(AllImages.IMG_PANEL_START_BG_STATIC, 0, 0, null);
		// 初始化面板
		super.initPanel(g);

	}
	private LabelIcon icon = null;
	private int addLabelDelay = 0;
	@Override
	public void run() {
		while (true) {
			this.repaint();
			this.isClick();
			GameUtil.sleep(20);
			if (mvPictureX < width) {
				mvPictureX += 1;
			} else {
				mvPictureX = 0;
			}
			this.addLabelDelay ++;
			if(addLabelDelay > 100) {
				// 添加按钮
				this.addLabel();
			}
		}
	}

	/** 开始游戏按钮的坐标 */
	private int startGameX = 0;
	private int startGameY = 95;
	/** 开始按钮运动的速度 */
	private int startGameSp = 20;

	/**
	 * 添加按钮~~~
	 */
	private void addLabel() {
		if (startGame == null) {
			// 添加开始按钮
			this.startGame = new LabelButton(startGameX, startGameY, 183, 73, AllImages.IMG_START_GAME, gameControl, "StartGame", true);
			this.add(startGame);
			// 添加帮助按钮
			this.help = new LabelButton(startGameX, startGameY, 183, 73, AllImages.IMG_HELP, gameControl, "Help", true);
			this.add(help);
		}
		if (startGame != null && startGameSp > 0) {
			startGameX += startGameSp;
			if (startGameX >= 380) {
				startGameSp -= 1;
			}
		}
		this.startGame.setLocation(startGameX, startGameY - 50);
		this.help.setLocation(startGameX, startGameY + 40);
	}
}
