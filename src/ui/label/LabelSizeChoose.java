package ui.label;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import data.DataGame;

/**
 * ��ʼ��Ϸ�����̴�С���Ⱥ���ѡ��ȱ�ǩ
 * 
 * @author cylong
 * @version May 17, 2014 9��52:44 PM
 */
public class LabelSizeChoose extends LabelSetting {

	private static final long serialVersionUID = 1L;
	/** ��ǰ��ʾ�ı�ǩ��ֻ����ʾһ�� */
	private static int current;
	/** ��������̴�С */
	private int boardSize;

	/**
	 * @param x
	 *            ���Ͻ�x����
	 * @param y
	 *            ���Ͻ�y����
	 * @param width
	 *            ��
	 * @param height
	 *            ��
	 * @param img
	 *            ��ʾ���̴�С��ͼƬ
	 * @param imgBg
	 *            �����ı���ͼƬ
	 * @param dataGame
	 *            ��Ϸ���ݿ�
	 * @param size
	 *            ��������̴�С
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
