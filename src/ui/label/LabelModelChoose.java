package ui.label;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import control.GameControl;

/**
 * ��Ϸģʽѡ��İ�ť
 * 
 * @author cylong
 * @version May 24, 2014 11:07:23 PM
 */
public class LabelModelChoose extends LabelButton implements Runnable {

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
	 * 
	 * @author cylong
	 * @version May 24, 2014 11:07:55 PM
	 */

	@Override
	public void mouseClicked (MouseEvent e) {
		this.isClick = true;
		this.repaint();
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
			this.gameControl.startSpcialMode();
		}
		// ����ģʽ�ļƷ�ģʽ
		if (method.equals("ScoreModel")) {
			this.gameControl.startScoreModel();
		}

	}

	/** ͼƬ������label�е�x���� */
	private int imageX;
	/** ͼƬ������label�е�y���� */
	private int imageY;

	/** ͼƬ����Ŀ�� */
	private int width;
	/** ͼƬ����ĸ߶� */
	private int height;

	/** ��ʼ�Ƕ� */
	private double degree;
	/** ��ת��ֹͣʱ�ļ��ٶȣ����ٶ� */
	private double rotateAcceleration = -0.05;
	/** ��תʱ���ٶȣ����ٶ� */
	private double rotateSpeed = 2.0;
	private double rotateBorder;
	/** ��ת���ĵ�x���� */
	private int rotateCenterX;
	/** ��ת���ĵ�y���� */
	private int rotateCenterY;

	/** label�ƶ����ٶ� */
	private double moveSpeed = 2.0;

	/** ����ʱͼƬ�����������ֵ */
	private int x;
	/** ����ʱͼƬ�����������ֵ */
	private int y;

	/** �ж���ת�Ƿ���� */
	private boolean rotateEnd = false;

	/**
	 * ����һ��label
	 * 
	 * @param width
	 *            ͼƬ����Ŀ��
	 * @param height
	 *            ͼƬ����ĸ߶�
	 * @param x
	 *            ͼƬ�����x����
	 * @param y
	 *            ͼƬ�����y����
	 * @param rotateDirect
	 *            ��ת����trueΪ��ʱ�������ƶ���falseΪ˳ʱ�������ƶ�
	 * 
	 * @author lwpeng
	 * @version May 25, 2014 12:57:18 AM
	 */
	public LabelModelChoose (int x, int y, int width, int height, Image im1, GameControl gameControl, String method, boolean rotateDirect) {
		super(x, y, width, height, im1, gameControl, method, true);
		this.setVisible(false);
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;

		int h = height / 2;

		rotateCenterY = (int) Math.sqrt((width - h) * (width - h) + h * h) + 1;
		imageY = rotateCenterY - h;

		/* ����label��С */
		int size = rotateCenterY + (int) (height * 0.75);
		this.setSize(size, size);
		this.setOpaque(false);

		/* ������ת������� */
		if (rotateDirect) {
			degree = 90.0;
			imageX = rotateCenterY - width + h;
			rotateCenterX = rotateCenterY;
			this.setLocation(-imageX + x + 120, -imageY + y);
		} else {
			degree = -90.0;
			rotateCenterX = (int) (height * 0.75);
			imageX = height - rotateCenterX;
			rotateSpeed = -rotateSpeed;
			rotateAcceleration = -rotateAcceleration;
			moveSpeed = -moveSpeed;
			this.setLocation(x - 120 - imageX, y - imageY);
		}
		rotateBorder = rotateSpeed * rotateSpeed / rotateAcceleration / 2;

		this.addMouseListener(this);
	}

	@Override
	public void paint (Graphics g) {

		/* ���û��� */
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		/* ʾ�� */
		g2d.rotate(degree * Math.PI / 180, rotateCenterX, rotateCenterY);
		// g2d.drawImage(i, imageX, imageY, width, height, null);
		if (b) {
			if (isMove) {
				g2d.drawImage(im1, imageX, imageY, width, height, null);
				g2d.drawImage(im2, imageX, imageY, width, height, null);
			} else {
				g2d.drawImage(im1, imageX, imageY, width, height, null);
			}
		} else {
			if (isMove) {
				g2d.drawImage(im1, imageX, imageY, width, height, null);
			} else {
				g2d.drawImage(im1, imageX, imageY, width, height, null);
			}
		}
		if (isClick) {
			g2d.drawImage(im1, imageX, imageY, width, height, null);
			isClick = false;
			isMove = false;
		}
	}

	/**
	 * ������ת�����Ƿ����
	 * 
	 * @return
	 */
	public boolean isRotateEnd () {
		return rotateEnd;
	}

	public void run () {
		this.setVisible(true);
		double x = this.getX();
		int y = this.getY();
		boolean b;
		for (;;) {
			if (b = Math.abs(degree) > Math.abs(moveSpeed)) {
				if (Math.abs(degree) <= Math.abs(rotateBorder)) {
					rotateSpeed += rotateAcceleration;
				}
				degree -= rotateSpeed;
			} else {
				degree = 0;
			}
			x -= moveSpeed;
			this.setLocation((int) x, y);
			repaint();
			if (!b) {
				imageX = imageY = 0;
				this.setLocation(this.x, this.y);
				this.setSize(width, height);
				repaint();
				rotateEnd = true;
				return;
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
