package ui.panel.game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import ui.AllImages;
import ui.panel.AbstractPanel;
import util.GameUtil;

/**
 * �������ı���
 * 
 * @author cylong
 * @version May 19, 2014 11:09:52 PM
 */
public class PanelBackground extends AbstractPanel implements Runnable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** ����ͼƬָ�� */
	private int imgIndex;
	/** ����͸���� */
	private float hyaline = 0.0f;

	public PanelBackground (int width, int height) {
		this.setOpaque(false);
		this.setBounds(0, 0, width, height);
		this.width = width;
		this.height = height;
		// �����ʼ������ͼƬ��ָ��
		imgIndex = (int) (Math.random() * AllImages.IMG_PANEL_GAME_BG.length);
		new Thread(this).start();
	}

	public void paint (Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, hyaline));
		// ������ͼƬ
		if(AllImages.IMG_PANEL_GAME_BG[imgIndex] == null) {
			AllImages.IMG_PANEL_GAME_BG[imgIndex] = new ImageIcon(AllImages.FILE_PANEL_GAME_BG[imgIndex].getPath()).getImage();
		}
		g2.drawImage(AllImages.IMG_PANEL_GAME_BG[imgIndex], 0, 0, width, height, this);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 * 
	 * @author cylong
	 * @version May 19, 2014 11:10:36 PM
	 */
	@Override
	public void run () {
		while (true) {
			GameUtil.sleep(50);
			// ����͸����
			hyaline += 0.04f;
			if (hyaline > 1) {
				hyaline = 1;
				// �����߳�
				break;
			}
		}
	}

}
