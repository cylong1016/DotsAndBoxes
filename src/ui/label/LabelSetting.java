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
 * ��ʼ��Ϸ�����̴�С���Ⱥ���ѡ��ȱ�ǩ
 * 
 * @author cylong
 * @version May 18, 2014 12:06:26 AM
 */
public class LabelSetting extends JLabel implements MouseListener {

	private static final long serialVersionUID = 1L;
	/** �Ƿ��ƶ�����ǩ���� */
	protected boolean isMoved;
	/** ��ʾ��ͼƬ */
	protected Image img;
	/** ����ͼƬ */
	protected Image imgBg;
	/** ��Ϸ���� */
	protected DataGame dataGame;

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
