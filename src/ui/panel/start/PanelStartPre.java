package ui.panel.start;

import java.awt.Graphics;

import ui.AllImages;
import ui.panel.AbstractPanel;
import ui.window.FrameGame;
import util.GameUtil;
import control.GameControl;
import data.DataGame;

/**
 * ��Ϸ�ʼ�Ķ���
 * 
 * @author cylong
 * @since 2014 / 5 / 14 4:27 PM
 */
public class PanelStartPre extends AbstractPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	/**
	 * preͼƬ�Ŀ�
	 */
	private int preWidth = 10;
	/**
	 * preͼƬ�ĸ�
	 */
	private int preHeight = 0;
	/**
	 * preͼƬ�����Ͻ�x����
	 */
	private int imgPreX = 0;
	/**
	 * preͼƬ���Ͻ�y����
	 */
	private int imgPreY = 0;

	/**
	 * ����ͼƬ�Ŀ�
	 */
	private int bgWidth = 0;
	/**
	 * ����ͼƬ�ĸ�
	 */
	private int bgHeight = 0;
	/**
	 * ����ͼƬ�����Ͻ�x����
	 */
	private int imgBgX = 0;
	/**
	 * ����ͼƬ���Ͻ�y����
	 */
	private int imgbgY = 0;
	/**
	 * ����ͼƬ�ļ��
	 */
	private int delay = 150;

	public PanelStartPre(FrameGame frameGame, GameControl gameControl, DataGame dataGame) {
		super(frameGame, gameControl, dataGame);
		this.initPanel();
		this.imgPreX = (this.getWidth() >> 1) - 5;
		this.imgPreY = (this.getHeight() >> 1);
		this.imgBgX = (this.getWidth() >> 1);
		this.imgbgY = (this.getHeight() >> 1);
		new Thread(this).start();
		new Start().start();
	}

	public void paint(Graphics g) {
		if(!drawImg2) {
			g.drawImage(AllImages.IMG_START_PRE, imgPreX, imgPreY, imgPreX + preWidth, imgPreY + preHeight, imgPreX, imgPreY, imgPreX + preWidth, imgPreY + preHeight, null);
		}
		g.drawImage(AllImages.IMG_PANEL_START_BG_STATIC, imgBgX, imgbgY, imgBgX + bgWidth, bgHeight, 0, 0, bgWidth, bgHeight, null);
		initPanel(g);
	}

	/** ͼƬ�ƶ��ٶ� */
	private int mvSpeed = 4;

	/** ���ڶ���ͼƬ */
	private boolean drawImg2;

	@Override
	public void run() {
		while (true) {
			GameUtil.sleep(10);
			if (preHeight <= this.getHeight()) {
				this.preHeight += (mvSpeed << 1);
				this.imgPreY -= mvSpeed;
			}
			if (preWidth <= this.getWidth() && preHeight > this.getHeight()) {
				this.preWidth += (mvSpeed << 1);
				this.imgPreX -= mvSpeed;
			}
			if (preWidth > this.getWidth() && delay > 0) {
				delay--;
			}
			if (preWidth > this.getWidth() && this.imgBgX > 0 && delay <= 0) {
				this.imgBgX -= mvSpeed;
				this.imgbgY -= mvSpeed / 4 * 3;
				this.bgWidth += (mvSpeed << 1);
				this.bgHeight += (mvSpeed / 4 * 3 << 1);
			}
			// ���뿪ʼ�˵�����
			if (this.imgBgX <= 0) {
				this.gameControl.startMenu();
				break;
			}
			this.repaint();
			this.isClick();
		}
		
	}
	
	private class Start extends Thread {
		public void run() {
			while(true) {
				GameUtil.sleep(80);
			}
		}
	}
}
