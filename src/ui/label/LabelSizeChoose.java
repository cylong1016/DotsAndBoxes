package ui.label;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import data.DataGame;

/**
 * 开始游戏后棋盘大小、先后手选择等标签
 * 
 * @author cylong
 * @version May 17, 2014 9：52:44 PM
 */
public class LabelSizeChoose extends LabelSetting {

	private static final long serialVersionUID = 1L;
	/** 当前显示的标签，只能显示一个 */
	private static int current;
	/** 代表的棋盘大小 */
	private int boardSize;

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
	 * @param size
	 *            代表的棋盘大小
	 * @author cylong
	 * @version May 18, 2014 12:11:17 AM
	 */
	public LabelSizeChoose (int x, int y, int width, int height, Image img, Image imgBg, DataGame dataGame, int boardSize) {
		super(x, y, width, height, img, imgBg, dataGame);
		this.boardSize = boardSize;
		current = dataGame.getChessBoardSize();
	}

	@Override
	public void paint (Graphics g) {
		if (isMoved || current == this.boardSize) {
			g.drawImage(imgBg, 0, 0, getWidth(), getHeight(), this);
		}
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 * 
	 * @author cylong
	 * @version May 17, 2014 11:13:42 PM
	 */
	@Override
	public void mouseClicked (MouseEvent e) {
		current = this.boardSize;
		this.dataGame.setChessBoardSize(boardSize);
	}


	/**
	 * @version May 18, 2014 12:42:10 AM
	 * @return the boardSize
	 */
	public int getBoardSize () {
		return this.boardSize;
	}

	/**
	 * @version May 18, 2014 12:42:10 AM
	 * @param boardSize
	 *            the boardSize to set
	 */
	public void setBoardSize (int boardSize) {
		this.boardSize = boardSize;
	}


}
