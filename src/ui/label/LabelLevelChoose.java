package ui.label;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import data.DataGame;

/**
 * 难度选择
 * 
 * @author cylong
 * @version May 19, 2014 9:51:10 PM
 */
public class LabelLevelChoose extends LabelSetting {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** 当前显示的标签，只能显示一个 */
	private static int current;
	/**难度*/
	private int level;

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
	 * @author cylong
	 * @version May 19, 2014 10:20:57 PM
	 */
	public LabelLevelChoose (int x, int y, int width, int height, Image img, Image imgBg, DataGame dataGame, int level) {
		super(x, y, width, height, img, imgBg, dataGame);
		this.level = level;
		current = dataGame.getLevel();
	}

	@Override
	public void paint (Graphics g) {
		if (isMoved || current == this.level) {
			g.drawImage(imgBg, 0, 0, getWidth(), getHeight(), this);
		}
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

	@Override
	public void mouseClicked (MouseEvent e) {
		current = this.level;
		this.dataGame.setLevel(level);
	}
	
	/**
	 * @version May 19, 2014  10:25:51 PM
	 * @return the level
	 */
	public int getLevel () {
		return this.level;
	}

	
	/**
	 * @version May 19, 2014  10:25:51 PM
	 * @param level the level to set
	 */
	public void setLevel (int level) {
		this.level = level;
	}
	
}
