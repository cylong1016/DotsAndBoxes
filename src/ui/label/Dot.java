package ui.label;

import java.awt.Graphics;

import javax.swing.JLabel;

import ui.AllImages;

/**
 * �����ϵĵ�
 * 
 * @author ������
 * @since 2014 / 3 / 18<br />
 *        11 : 36 AM
 */
public class Dot extends JLabel {
 
	private static final long serialVersionUID = 1L;

	/**
	 * ���췽��
	 * 
	 * @param x
	 *            �����ϵ��x����
	 * @param y
	 *            �����ϵ��y����
	 * @param size
	 *            �����ϵ��ֱ��
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
