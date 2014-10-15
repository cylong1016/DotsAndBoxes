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
 * ��Ϸ��ʼ�˵�<br />
 * �̳����Զ����PanelUserDefined
 * 
 * @author ������
 * @since 2014 / 3 / 22 4 : 02 AM
 * 
 */
public class PanelStart extends AbstractPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * ��ʼ��Ϸ��ť
	 */
	private LabelButton startGame = null;
	/**
	 * ������ť
	 */
	private LabelButton help = null;

	/**
	 * ���췽��
	 * 
	 * @param frameGame
	 *            ������Ϸ�����
	 * @param gameControl
	 *            ������Ϸ������
	 * @param dataGame
	 *            ��Ϸ����
	 */
	public PanelStart(FrameGame frameGame, GameControl gameControl, DataGame dataGame) {
		super(frameGame, gameControl, dataGame);
		// ��ʼ�����
		super.initPanel();
		// ��������߳�
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
	/** ��̬ͼƬ�����Ͻ�x���� */
	private int mvPictureX = 0;

	@Override
	public void paint(Graphics g) {
		// ��������̬��ͼƬ���ġ�
		g.drawImage(AllImages.IMG_PANEL_START_BG_STATIC, 0, 0, null);
		// ��ʼ�����
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
				// ��Ӱ�ť
				this.addLabel();
			}
		}
	}

	/** ��ʼ��Ϸ��ť������ */
	private int startGameX = 0;
	private int startGameY = 95;
	/** ��ʼ��ť�˶����ٶ� */
	private int startGameSp = 20;

	/**
	 * ��Ӱ�ť~~~
	 */
	private void addLabel() {
		if (startGame == null) {
			// ��ӿ�ʼ��ť
			this.startGame = new LabelButton(startGameX, startGameY, 183, 73, AllImages.IMG_START_GAME, gameControl, "StartGame", true);
			this.add(startGame);
			// ��Ӱ�����ť
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
