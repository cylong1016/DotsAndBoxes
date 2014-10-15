/**
 * @version May 18, 2014  12:06:26 AM
 */
package ui.label;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import data.DataGame;

/**
 * 开始游戏后棋盘大小、先后手选择等标签
 * 
 * @author cylong
 * @version May 18, 2014 12:06:26 AM
 */
public class LabelSetting extends JLabel implements MouseListener {

	private static final long serialVersionUID = 1L;
	/** 是否移动到标签上面 */
	protected boolean isMoved;
	/** 显示的图片 */
	protected Image img;
	/** 背景图片 */
	protected Image imgBg;
	/** 游戏数据 */
	protected DataGame dataGame;

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
	 * @version May 18, 2014 12:07:33 AM
	 */
	public LabelSetting (int x, int y, int width, int height, Image img, Image imgBg, DataGame dataGame) {
		this.img = img;
		this.imgBg = imgBg;
		this.dataGame = dataGame;
		this.setBounds(x, y, width, height);
		this.addMouseListener(this);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 * 
	 * @author cylong
	 * @version May 18, 2014 12:09:13 AM
	 */
	@Override
	public void mouseEntered (MouseEvent e) {
		this.isMoved = true;
	}

	@Override
	public void mouseExited (MouseEvent e) {
		this.isMoved = false;
	}

	@Override
	public void mouseClicked (MouseEvent e) {
	}

	@Override
	public void mousePressed (MouseEvent e) {
	}

	@Override
	public void mouseReleased (MouseEvent e) {
	}

}
