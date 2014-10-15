package ui.panel.start;

import java.awt.Graphics;

import ui.AllImages;
import util.GameUtil;

/**
 * œ‘ æ Dots and Boxes
 * 
 * @author cylong
 * @since 2014 / 5 / 14 9:20
 */
public class PanelStartGameName extends PanelStartFlash {

	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		// ª≠Dots and Boxes
		for (int i = 0; i < imageIndex; i++) {
			g.drawImage(AllImages.IMG_DOTS_AND_BOXES[i], imgX, imgY, getWidth(), getHeight(), null);
		}
	}
	private int delay = 0;
	@Override
	public void run() {
		while (true) {
			GameUtil.sleep(250);
			delay ++;
			if(delay > 16) {
				this.addImgIndex(AllImages.IMG_DOTS_AND_BOXES.length);
			}
			this.repaint();
		}
	}

}
