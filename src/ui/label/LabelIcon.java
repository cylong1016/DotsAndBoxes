package ui.label;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JLabel;

import util.GameUtil;


/**
 * 
 * @author cylong
 * @version May 28, 2014  1:07:29 PM
 */
public class LabelIcon extends JLabel implements Runnable{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private float hyaline = 0f;
	private Image img = null;
	private boolean show = true;
	
	public LabelIcon(int x, int y, int width, int height, Image img) {
		this.setBounds(x, y, width, height);
		this.img = img;
		new Thread(this).start();
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, hyaline));
		g2.drawImage(img, 0, 0, this);
	}
	/**
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 *
	 * @author cylong
	 * @version May 30, 2014  2:22:37 AM
	 */
	@Override
	public void run () {
		while(true) {
			GameUtil.sleep(40);
			if(show) {
				hyaline += 0.025;
				if (hyaline > 1) {
					hyaline = 1;
				}
			} else {
				hyaline -= 0.05;
				if(hyaline < 0) {
					hyaline = 0;
				}
			}
			if(hyaline >= 1) {
				show = false;
			}
			if(hyaline <= 0) {
				show = true;
			}
			
		}
	}
	
}
