package ui.panel.game;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import ui.AllImages;
import ui.panel.AbstractPanel;
import util.GameUtil;

/**
 * 下棋面板按esc后出现的面板的背景
 * 
 * @author cylong
 * @version May 20, 2014 4:43:53 AM
 */
public class PanelGameMenuBg extends AbstractPanel implements Runnable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** 画笔透明度 */
	private float hyaline = 0.0f;
	/**是否显示*/
	private boolean isShow;

	/**
	 * @version May 20, 2014  2:47:20 PM
	 * @param isShow the isShow to set
	 */
	public void setShow (boolean isShow) {
		this.isShow = isShow;
	}

	public PanelGameMenuBg (int width, int height) {
		// 初始化面板
		this.initPanel();
		this.setFocusable(false);
	}

	@Override
	public void paint (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, hyaline));
		g2.drawImage(AllImages.IMG_MENU_BG, 0, 0, this);
	}

	@Override
	public void run () {
		while (true) {
			GameUtil.sleep(50);
			if(isShow) {
				hyaline += 0.04f;
				if (hyaline > 1) {
					hyaline = 1;
				}
			} else {
				hyaline -= 0.04f;
				if (hyaline < 0) {
					hyaline = 0.01f;
				}
			}
		}
	}

}
