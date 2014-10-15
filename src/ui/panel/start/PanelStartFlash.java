package ui.panel.start;

import ui.panel.AbstractPanel;

/**
 * 进入开始菜单的动画
 * 
 * @author cylong
 * @since 2014 / 5 / 15 12:28 AM
 */
public abstract class PanelStartFlash extends AbstractPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * 图片左上角坐标
	 */
	protected int imgX = 0;
	protected int imgY = 0;

	/**
	 * 图片在数组里的索引
	 */
	protected int imageIndex = 0;

	/**
	 * @param frameGame
	 * @param gameControl
	 * @param dataGame
	 */
	public PanelStartFlash() {
		// 设置Panel大小
		this.setSize(width, height);
		// 设置panel为透明
		this.setOpaque(false);
		// 设置布局管理为绝对布局
		this.setLayout(null);
		new Thread(this).start();
	}

	/**
	 * 增加图片的索引
	 */
	protected void addImgIndex(int lenght) {
		if (imageIndex < lenght) {
			imageIndex++;
		}
	}

}
