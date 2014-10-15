package ui.panel.game;

import java.awt.Graphics;

import ui.AllImages;
import ui.label.PanelMenuButton;
import ui.panel.AbstractPanel;
import control.GameControl;
import data.DataGame;

/**
 * 下棋面板的菜单面板
 * 
 * @author cylong
 * @since 2014 / 5 / 17 5:54 AM
 */
public class PanelGameMenu extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * 返回主菜单按钮
	 */
	private PanelMenuButton mainMenu = null;
	/**
	 * 重新开始按钮
	 */
	private PanelMenuButton restartGame = null;
	/**
	 * 保存棋盘按钮
	 */
	private PanelMenuButton saveChess = null;
	/**
	 * 读取棋盘按钮
	 */
	private PanelMenuButton loadChess = null;
	/**
	 * 退出按钮
	 */
	private PanelMenuButton exit = null;
	/**
	 * 第一个按钮的x坐标
	 */
	private int buttonX = 50;
	/**
	 * 第一个按钮的y坐标
	 */
	private int buttonY = 75;
	/**
	 * 按钮的宽
	 */
	private int buttonW = 154;
	/**
	 * 按钮的高
	 */
	private int buttonH = 44;
	/**
	 * 按钮的间隙
	 */
	private int buttonInterval = 35;

	/**
	 * @param width
	 * @param height
	 * @param gameControl
	 * @param dataGame
	 * @author cylong
	 * @version May 17, 2014 11:25:47 PM
	 * @version May 20, 2014 5:02:56 AM
	 */
	public PanelGameMenu (int x, int y, int width, int height, GameControl gameControl, DataGame dataGame) {
		this.setLayout(null);
		this.setOpaque(false);
		this.setBounds(x, y, width, height);
		// 返回主菜单按钮
		this.mainMenu = new PanelMenuButton(buttonX, buttonY + (buttonH + buttonInterval) * 3, buttonW, buttonH, AllImages.IMG_MAIN_MENU, AllImages.IMG_MAIN_MENU1, gameControl, "MainMenu", true);
		this.add(mainMenu);
		// 退出按钮
		this.exit = new PanelMenuButton(buttonX, buttonY + (buttonH + buttonInterval) * 4, buttonW, buttonH, AllImages.IMG_EXIT, AllImages.IMG_EXIT1, gameControl, "Exit", true);
		this.add(exit);
		// 重新开始按钮
		this.restartGame = new PanelMenuButton(buttonX, buttonY, buttonW, buttonH, AllImages.IMG_RESTART_GAME, AllImages.IMG_RESTART_GAME1, gameControl, "RestartGame", true);
		this.add(restartGame);
		// 保存棋盘
		this.saveChess = new PanelMenuButton(buttonX, buttonY + (buttonH + buttonInterval), buttonW, buttonH, AllImages.IMG_SAVE_CHESS, AllImages.IMG_SAVE_CHESS1, gameControl, "SaveChess", true);
		this.add(saveChess);
		// 添加读取进度按钮
		this.loadChess = new PanelMenuButton(buttonX, buttonY + ((buttonH + buttonInterval) << 1), buttonW, buttonH, AllImages.IMG_LOAD_CHESS, AllImages.IMG_LOAD_CHESS1, gameControl, "LoadData", true);
		this.add(loadChess);
		// 双人游戏不可以保存读取棋盘
		if (dataGame.getGameMode() != 0) {
			this.saveChess.setUnabled(true);
			this.loadChess.setUnabled(true);
			// 联机对战不可以重新开始
			if (dataGame.getGameMode() == 2) {
				this.restartGame.setUnabled(true);
			}
		}
	}

	public void paint (Graphics g) {
		g.drawImage(AllImages.IMG_MENU, 0, 0, this);
		super.paint(g);
	}

	/**
	 * @author cylong
	 * @version May 20, 2014 2:34:58 PM
	 */
	@Override
	public void run () {
	}

}
