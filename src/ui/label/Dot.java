package ui.label;

import java.awt.Graphics;

import javax.swing.JLabel;

import ui.AllImages;

/**
 * 棋盘上的点
 * 
 * @author 陈云龙
 * @since 2014 / 3 / 18<br />
 *        11 : 36 AM
 */
public class Dot extends JLabel {
 
	private static final long serialVersionUID = 1L;

	/**
	 * 构造方法
	 * 
	 * @param x
	 *            棋盘上点的x坐标
	 * @param y
	 *            棋盘上点的y坐标
	 * @param size
	 *            棋盘上点的直径
	 * 
	 */
	public Dot(int x, int y, int size) {
		this.setBounds(x, y, size, size);
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(AllImages.IMG_DOT.getImage(), 0, 0, getWidth(), getHeight(), null);
	}
}
