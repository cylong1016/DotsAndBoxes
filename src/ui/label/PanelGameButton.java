package ui.label;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import control.GameControl;
import data.DataConfig;

/**
 * ��Ϸ�������İ�ť
 * 
 * @author cylong
 * @version May 20, 2014 3:19:39 AM
 */
public class PanelGameButton extends LabelButton {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * @param x
	 *            ��ǩ���Ͻ�x����
	 * @param y
	 *            ��ǩ���Ͻ�y����
	 * @param width
	 *            ��ǩ�Ŀ�
	 * @param height
	 *            ��ǩ�ĸ�
	 * @param im1
	 *            ���δ�ƶ�����ǩ����ʾ��ͼƬ
	 * @param im2
	 *            ����ƶ�����ǩ����ʾ��ͼƬ
	 * @param gameControl
	 *            ������Ϸ������
	 * @param method
	 *            �ñ�ǩ���õķ���
	 * @author cylong
	 * @version May 20, 2014 3:22:10 AM
	 */
	public PanelGameButton (int x, int y, int width, int height, Image im1, Image im2, GameControl gameControl, String method, boolean b) {
		super(x, y, width, height, im1, gameControl, method, b);
		this.im2 = im2;
		this.isClick = DataConfig.isMute();
	}
	
	/** ͼƬ����Ĵ�С */
	private int expend = 2;
	@Override
	public void paint(Graphics g) {
		if(isMove && !isClick) {
			g.drawImage(im1, 0, 0, getWidth(), getHeight(), this);
		} else if(!isMove && !isClick) {
			g.drawImage(im1, expend, expend, getWidth() - (expend << 1), getHeight() - (expend << 1), this);
		} else if(isMove && isClick) {
			g.drawImage(im2, 0, 0, getWidth(), getHeight(), this);
		} else if(!isMove && isClick) {
			g.drawImage(im2, expend, expend, getWidth() - (expend << 1), getHeight() - (expend << 1), this);
		}
	}
	
	@Override
	public void mouseClicked (MouseEvent e) {
		this.isClick = !this.isClick;
		this.repaint();
		// ����
		if (method.equals("TakeBack")) {
			this.gameControl.takeBack();
		}
		// ������Ϸ��ͣ
		if(method.equals("SetPause")) {
			DataConfig.setPause(this.isClick);
		}
		// �����Ƿ���
		if(method.equals("SetMute")) {
			DataConfig.setMute(this.isClick);
		}
	}

}
