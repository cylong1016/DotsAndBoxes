package ui.panel;

import java.awt.Graphics;

import ui.AllImages;
import ui.label.LabelButton;
import ui.window.FrameGame;
import util.GameUtil;
import control.GameControl;
import data.DataGame;

/**
 * ѡ������ģʽ���
 * 
 * @author cylong
 * @since 2014 / 5 / 15 4��41 PM
 */
public class PanelSpecialMode extends AbstractPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * ����ģʽ
	 */
	private LabelButton scoreModel = null;

	public PanelSpecialMode(FrameGame frameGame, GameControl gameControl, DataGame dataGame) {
		super(frameGame, gameControl, dataGame);
		this.scoreModel = new LabelButton(10, 10, 100, 40, AllImages.IMG_SCORE, gameControl, "ScoreModel", true);
		this.add(scoreModel);
		this.initPanel();
		this.setOpaque(true);
		// ��������߳�
		new Thread(this).start();
	}

	@Override
	public void paint(Graphics g) {
		this.initPanel(g);
	}

	@Override
	public void run() {
		while (true) {
			super.isClick();
			this.repaint();
			GameUtil.sleep(50);
		}
	}

}
