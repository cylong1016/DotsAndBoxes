package ui.panel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import control.GameControl;
import data.DataGame;
import ui.AllImages;
import ui.window.FrameGame;
import util.GameUtil;

/**
 * 关于我们面板
 * 
 * @author cylong
 * @version May 28, 2014 12:30:00 PM
 */
public class PanelAboutUs extends AbstractPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private int index;
	public PanelAboutUs(FrameGame frameGame, GameControl gameControl, DataGame dataGame) {
		super(frameGame, gameControl, dataGame);
		initPanel();
		new Thread(this).start();
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Graphics2D g2 = (Graphics2D) g;
		if (hyaline > 1) {
			hyaline = 1;
		}
		if(hyaline < 0) {
			hyaline = 0;
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, hyaline));
		g2.drawImage(AllImages.IMG_ABOUT_US[index].getImage(), 0, 0, this);
		initPanel(g);
	}
	
	@Override
	public void mouseClicked (MouseEvent arg0) {
		// 判断鼠标是否点击关闭按钮
		if (arg0.getX() > exitX && arg0.getX() < exitX + WINDOW_EXIT_W && arg0.getY() > exitY && arg0.getY() < exitY + WINDOW_EXIT_H) {
			GameUtil.startClickSound();
			this.gameControl.returnStartPanel();
		}
		// 判断鼠标是否点击最小化按钮
		if (arg0.getX() > minX && arg0.getX() < minX + WINDOW_MIN_W && arg0.getY() > minY && arg0.getY() < minY + WINDOW_MIN_H) {
			GameUtil.startClickSound();
			frameGame.setExtendedState(JFrame.ICONIFIED);
		}
	}
	private int time;
	private boolean show = true;
	/**
	 * @author cylong
	 * @version May 28, 2014  12:31:15 PM
	 */
	@Override
	public void run () {
		while(true) {
			GameUtil.sleep(100);
			if(show) {
				hyaline += 0.1f;
			} else {
				hyaline -= 0.1f;
			}
			time++;
			if(time % 100 - 91 == 0) {
				show = false;
			}
			if(time % 100 == 0) {
				index++;
				show = true;
			}
			if(index >= AllImages.IMG_ABOUT_US.length) {
				this.gameControl.returnStartPanel();
				break;
			}
			this.repaint();
		}
	}

}
