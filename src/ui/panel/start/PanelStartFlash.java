package ui.panel.start;

import ui.panel.AbstractPanel;

/**
 * ���뿪ʼ�˵��Ķ���
 * 
 * @author cylong
 * @since 2014 / 5 / 15 12:28 AM
 */
public abstract class PanelStartFlash extends AbstractPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * ͼƬ���Ͻ�����
	 */
	protected int imgX = 0;
	protected int imgY = 0;

	/**
	 * ͼƬ�������������
	 */
	protected int imageIndex = 0;

	/**
	 * @param frameGame
	 * @param gameControl
	 * @param dataGame
	 */
	public PanelStartFlash() {
		// ����Panel��С
		this.setSize(width, height);
		// ����panelΪ͸��
		this.setOpaque(false);
		// ���ò��ֹ���Ϊ���Բ���
		this.setLayout(null);
		new Thread(this).start();
	}

	/**
	 * ����ͼƬ������
	 */
	protected void addImgIndex(int lenght) {
		if (imageIndex < lenght) {
			imageIndex++;
		}
	}

}
