package ui.label;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import data.DataGame;

/**
 * 先后手选择的按钮
 * 
 * @author cylong
 * @version May 19, 2014 4:12:57 AM
 */
public class LabelFirstSecond extends LabelSetting {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 先后手选择 */
	private int firstSecond;
	/** 当前显示的标签，只能显示一个 */
	private static int current;

	/**
	 * @param x
	 *            左上角x坐标
	 * @param y
	 *            左上角y坐标
	 * @param width
	 *            宽
	 * @param height
	 *            高
	 * @param img
	 *            表示棋盘大小的图片
	 * @param imgBg
	 *            点击后的背景图片
	 * @param dataGame
	 *            游戏数据库
	 * @param firstSecond
	 *            <p>
	 *            先后手选择
	 *            </p>
	 *            <li>0：先手</li> <li>1：后手</li>
	 * @author cylong
	 * @version May 19, 2014 4:14:14 AM
	 */
	public LabelFirstSecond (int x, int y, int width, int height, Image img, Image imgBg, DataGame dataGame, int firstSecond) {
		super(x, y, width, height, img, imgBg, dataGame);
		this.firstSecond = firstSecond;
	}

	@Override
	public void paint (Graphics g) {
		if (isMoved || current == this.firstSecond) {
			g.drawImage(imgBg, 0, 0, getWidth(), getHeight(), this);
		}
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	@Override
	public void mouseClicked (MouseEvent e) {
		this.dataGame.setFirstSecond(firstSecond);
		if(firstSecond == 1) {
			this.dataGame.getPlayer1().setClick(true);
		} else {
			this.dataGame.getPlayer1().setClick(false);
		}
		current = firstSecond;
	}

}
