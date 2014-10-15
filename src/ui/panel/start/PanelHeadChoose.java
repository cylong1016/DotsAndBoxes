package ui.panel.start;

import java.awt.Color;

import javax.swing.JButton;

import ui.AllImages;
import ui.panel.AbstractPanel;
import ui.window.FrameGame;
import control.GameControl;
import data.DataGame;

/**
 * ͷ��ѡ��
 * 
 * @author cylong
 * @version May 17, 2014 5:05:20 PM
 */
public class PanelHeadChoose extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	/** ͷ��ť���� */
	private JButton[] heads = new JButton[AllImages.IMG_HEADS.length];
	/** ��ť�Ĵ�С */
	private int size = 100;
	/** ��һ����ť��x���� */
	private int x = 10;
	/** ��һ����ť��y���� */
	private int y = 10;
	/** ������ť֮��ļ�϶ */
	private int interval = 10;

	/**
	 * @param frameGame
	 * @param gameControl
	 * @param dataGame
	 */
	public PanelHeadChoose (FrameGame frameGame, GameControl gameControl, DataGame dataGame) {
		super(frameGame, gameControl, dataGame);
		this.setLayout(null);
		this.initButton();
	}

	/**
	 * ��ʼ��ͷ��ť
	 */
	private void initButton () {
		for (int i = 0; i < heads.length; i++) {
			heads[i] = new JButton(AllImages.IMG_HEADS[i]);
			heads[i].setBounds(x + (size + interval) * i, y, size, size);
			heads[i].setBorder(null);
			heads[i].setBackground(Color.WHITE);
			heads[i].setOpaque(false);
			this.add(heads[i]);
		}
	}

	@Override
	public void run () {

	}

}
