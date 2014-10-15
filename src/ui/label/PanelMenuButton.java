package ui.label;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import control.GameControl;

/**
 * ��������еĲ˵���ť
 * 
 * @author cylong
 * @version May 20, 2014 3:16:36 PM
 */
public class PanelMenuButton extends LabelButton {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** ��ť�Ƿ���� */
	private boolean isUnabled = false;
	
	/**
	 * @version May 20, 2014  3:59:04 PM
	 * @param isEnabled the isEnabled to set
	 */
	public void setUnabled (boolean isUnabled) {
		this.isUnabled = isUnabled;
	}

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
	 * @version May 20, 2014 3:17:22 PM
	 */
	public PanelMenuButton (int x, int y, int width, int height, Image im1, Image im2, GameControl gameControl, String method, boolean b) {
		super(x, y, width, height, im1, gameControl, method, b);
		this.im2 = im2;
	}

	/** ͼƬ����Ĵ�С */
	private int expend = 2;

	@Override
	public void paint (Graphics g) {
		if (isMove && !isUnabled) {
			g.drawImage(im2, 0, 0, getWidth(), getHeight(), null);
		} else {
			g.drawImage(im1, expend, expend, getWidth() - (expend << 1), getHeight() - (expend << 1), null);
		}
	}

	@Override
	public void mouseClicked (MouseEvent e) {
		if(isUnabled) {
			return;
		}
		// ��Ϸ��ȡ����
		if (method.equals("LoadData")) {
			this.gameControl.loadChess();
		}
		// ��Ϸ�������˵�
		if (method.equals("MainMenu")) {
			this.gameControl.gotoMainMenu();
		}
		// ���¿�ʼ��Ϸ
		if (method.equals("RestartGame")) {
			this.gameControl.restartGame();
		}
		// ��Ϸ���ı���
		if (method.equals("SaveChess")) {
			this.gameControl.saveChess();
		}
		// �˳�ϵͳ
		if (method.equals("Exit")) {
			System.exit(0);
		}

	}
}
