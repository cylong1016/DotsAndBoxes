package ui.label;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import data.DataGame;

/**
 * �Ⱥ���ѡ��İ�ť
 * 
 * @author cylong
 * @version May 19, 2014 4:12:57 AM
 */
public class LabelFirstSecond extends LabelSetting {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** �Ⱥ���ѡ�� */
	private int firstSecond;
	/** ��ǰ��ʾ�ı�ǩ��ֻ����ʾһ�� */
	private static int current;

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
	 * @param firstSecond
	 *            <p>
	 *            �Ⱥ���ѡ��
	 *            </p>
	 *            <li>0������</li> <li>1������</li>
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
