package ui.label;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import ui.AllImages;
import util.GameUtil;
import control.GameControl;

/**
 * ��Ϸ��ť<br />
 * �̳���JLabel
 * 
 * @author ������
 * @since 2014 / 4 / 7 4 : 45 AM
 */
public class LabelButton extends JLabel implements MouseListener {

	public static final long serialVersionUID = 1L;
	public static boolean playMusic = true;
	/**
	 * ��Ϸ������
	 */
	protected GameControl gameControl = null;
	/**
	 * �Ƿ��ƶ�����ǩ��
	 */
	protected boolean isMove = false;
	/**
	 * �Ƿ񱻵��
	 */
	protected boolean isClick = false;
	/**
	 * ͼƬ1
	 */
	protected Image im1 = null;
	/**
	 * ͼƬ2
	 */
	protected Image im2 = null;
	/**
	 * ��ͼ��ʽ
	 */
	protected boolean b = false;
	/**
	 * ��ǩ���õķ���
	 */
	protected String method = null;

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
	 */
	public LabelButton (int x, int y, int width, int height, Image im1, GameControl gameControl, String method, boolean b) {
		this.im1 = im1;
		this.im2 = AllImages.IMG_LABEL_BORDER;
		this.b = b;
		this.method = method;
		this.gameControl = gameControl;
		// ���ñ�ǩ�Ĵ�С��λ��
		this.setBounds(x, y, width, height);
		this.addMouseListener(this);
	}

	public LabelButton() {
		
	}
	@Override
	public void paint (Graphics g) {
		if (b) {
			if (isMove) {
				g.drawImage(im1, 0, 0, getWidth(), getHeight(), null);
				g.drawImage(im2, 0, 0, getWidth(), getHeight(), null);
			} else {
				g.drawImage(im1, 3, 2, getWidth() - 6, getHeight() - 4, null);
			}
		} else {
			if (isMove) {
				g.drawImage(im1, 0, 0, getWidth(), getHeight(), null);
			} else {
				g.drawImage(im1, 3, 3, getWidth() - 6, getHeight() - 6, null);
			}
		}
		if (isClick) {
			g.drawImage(im1, 0, 0, getWidth(), getHeight(), null);
			isClick = false;
			isMove = false;
		}
	}

	@Override
	public void mouseClicked (MouseEvent e) {
		this.isClick = true;
		GameUtil.startClickSound();
		this.repaint();
		// ��ʼ��Ϸ��ť��������Ϸģʽѡ��
		if (method.equals("StartGame")) {
			this.gameControl.startChoose();
		}
		// ������Ϸ
		if (method.equals("SinglePlayer")) {
			this.gameControl.getServiceGame().getDataGame().setGameMode(0);
			if (gameControl.getServiceGame().getDataGame().getFirstSecond() == 0) {
				// ʹ���һ��ʼ��
				this.gameControl.getServiceGame().getDataGame().getPlayer2().setClick(true);
			} else {
				// ʹ��Ҷ���ʼ��
				this.gameControl.getServiceGame().getDataGame().getPlayer1().setClick(true);
			}
			this.gameControl.startGame();
		}
		// ˫����Ϸ
		if (method.equals("TwoPlayers")) {
			this.gameControl.getServiceGame().getDataGame().setGameMode(1);
			if (gameControl.getServiceGame().getDataGame().getFirstSecond() == 0) {
				// ʹ���һ��ʼ��
				this.gameControl.getServiceGame().getDataGame().getPlayer2().setClick(true);
			} else {
				// ʹ��Ҷ���ʼ��
				this.gameControl.getServiceGame().getDataGame().getPlayer1().setClick(true);
			}
			this.gameControl.startGame();
		}
		// ������ս
		if (method.equals("TwoPlayersConnect")) {
			this.gameControl.getServiceGame().getDataGame().setGameMode(2);
			this.gameControl.startConnectGame();
		}
		// ��������
		if (method.equals("CreateHome")) {
			this.gameControl.createHome();
		}
		// ���뷿��
		if (method.equals("JoinHome")) {
			this.gameControl.joinHome();
		}
		// ����ģʽ
		if (method.equals("SpcialMode")) {
			this.gameControl.startScoreModel();
		}
		// ����ģʽ�ļƷ�ģʽ
		if (method.equals("ScoreModel")) {
			this.gameControl.startScoreModel();
		}
		if(method.equals("GoToModelChoose")) {
			this.gameControl.goToModelChoose();
		}
		if(method.equals("Help")) {
			this.gameControl.startAboutUs();
		}

	}

	@Override
	public void mousePressed (MouseEvent e) {
	}

	@Override
	public void mouseReleased (MouseEvent e) {
	}

	@Override
	public void mouseEntered (MouseEvent e) {
		this.isMove = true;
		GameUtil.startMoveButtonSound();
		this.repaint();
	}

	@Override
	public void mouseExited (MouseEvent e) {
		this.isMove = false;
		this.repaint();
	}

}