package ui.panel.start;

import java.awt.Color;

import javax.swing.JButton;

import ui.AllImages;
import ui.panel.AbstractPanel;
import ui.window.FrameGame;
import control.GameControl;
import data.DataGame;

/**
 * 头像选择
 * 
 * @author cylong
 * @version May 17, 2014 5:05:20 PM
 */
public class PanelHeadChoose extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	/** 头像按钮数组 */
	private JButton[] heads = new JButton[AllImages.IMG_HEADS.length];
	/** 按钮的大小 */
	private int size = 100;
	/** 第一个按钮的x坐标 */
	private int x = 10;
	/** 都一个按钮的y坐标 */
	private int y = 10;
	/** 两个按钮之间的间隙 */
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
	 * 初始化头像按钮
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
