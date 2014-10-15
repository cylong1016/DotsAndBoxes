package ui.panel.game;

import java.awt.Graphics;

import ui.AllImages;
import ui.label.PanelMenuButton;
import ui.panel.AbstractPanel;
import control.GameControl;
import data.DataGame;

/**
 * �������Ĳ˵����
 * 
 * @author cylong
 * @since 2014 / 5 / 17 5:54 AM
 */
public class PanelGameMenu extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * �������˵���ť
	 */
	private PanelMenuButton mainMenu = null;
	/**
	 * ���¿�ʼ��ť
	 */
	private PanelMenuButton restartGame = null;
	/**
	 * �������̰�ť
	 */
	private PanelMenuButton saveChess = null;
	/**
	 * ��ȡ���̰�ť
	 */
	private PanelMenuButton loadChess = null;
	/**
	 * �˳���ť
	 */
	private PanelMenuButton exit = null;
	/**
	 * ��һ����ť��x����
	 */
	private int buttonX = 50;
	/**
	 * ��һ����ť��y����
	 */
	private int buttonY = 75;
	/**
	 * ��ť�Ŀ�
	 */
	private int buttonW = 154;
	/**
	 * ��ť�ĸ�
	 */
	private int buttonH = 44;
	/**
	 * ��ť�ļ�϶
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
		// �������˵���ť
		this.mainMenu = new PanelMenuButton(buttonX, buttonY + (buttonH + buttonInterval) * 3, buttonW, buttonH, AllImages.IMG_MAIN_MENU, AllImages.IMG_MAIN_MENU1, gameControl, "MainMenu", true);
		this.add(mainMenu);
		// �˳���ť
		this.exit = new PanelMenuButton(buttonX, buttonY + (buttonH + buttonInterval) * 4, buttonW, buttonH, AllImages.IMG_EXIT, AllImages.IMG_EXIT1, gameControl, "Exit", true);
		this.add(exit);
		// ���¿�ʼ��ť
		this.restartGame = new PanelMenuButton(buttonX, buttonY, buttonW, buttonH, AllImages.IMG_RESTART_GAME, AllImages.IMG_RESTART_GAME1, gameControl, "RestartGame", true);
		this.add(restartGame);
		// ��������
		this.saveChess = new PanelMenuButton(buttonX, buttonY + (buttonH + buttonInterval), buttonW, buttonH, AllImages.IMG_SAVE_CHESS, AllImages.IMG_SAVE_CHESS1, gameControl, "SaveChess", true);
		this.add(saveChess);
		// ��Ӷ�ȡ���Ȱ�ť
		this.loadChess = new PanelMenuButton(buttonX, buttonY + ((buttonH + buttonInterval) << 1), buttonW, buttonH, AllImages.IMG_LOAD_CHESS, AllImages.IMG_LOAD_CHESS1, gameControl, "LoadData", true);
		this.add(loadChess);
		// ˫����Ϸ�����Ա����ȡ����
		if (dataGame.getGameMode() != 0) {
			this.saveChess.setUnabled(true);
			this.loadChess.setUnabled(true);
			// ������ս���������¿�ʼ
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
