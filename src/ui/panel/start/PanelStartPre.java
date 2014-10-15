package ui.panel.start;

import java.awt.Graphics;

import ui.AllImages;
import ui.panel.AbstractPanel;
import ui.window.FrameGame;
import util.GameUtil;
import control.GameControl;
import data.DataGame;

/**
 * 游戏最开始的动画
 * 
 * @author cylong
 * @since 2014 / 5 / 14 4:27 PM
 */
public class PanelStartPre extends AbstractPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	/**
	 * pre图片的宽
	 */
	private int preWidth = 10;
	/**
	 * pre图片的高
	 */
	private int preHeight = 0;
	/**
	 * pre图片的左上角x坐标
	 */
	private int imgPreX = 0;
	/**
	 * pre图片左上角y坐标
	 */
	private int imgPreY = 0;

	/**
	 * 背景图片的宽
	 */
	private int bgWidth = 0;
	/**
	 * 背景图片的高
	 */
	private int bgHeight = 0;
	/**
	 * 背景图片的左上角x坐标
	 */
	private int imgBgX = 0;
	/**
	 * 背景图片左上角y坐标
	 */
	private int imgbgY = 0;
	/**
	 * 两张图片的间隔
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

	/** 图片移动速度 */
	private int mvSpeed = 4;

	/** 画第二张图片 */
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
			// 进入开始菜单界面
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
