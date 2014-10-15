package ui.label;

import java.awt.Graphics;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.swing.JLabel;

import ui.AllImages;
import control.GameControl;
import data.DataGame;

/**
 * ������ߵ���
 * 
 * @author ������
 * @since 2014 / 3 / 19 8 : 34 PM
 */
public class Line extends JLabel implements Externalizable {

	private static final long serialVersionUID = 1L;

	/**
	 * ��Ϸ������
	 */
	private GameControl gameControl = null;
	/**
	 * ���ڵڼ��������
	 */
	private int step = 0;
	/**
	 * ������ߺ��Ƿ��γɷ���
	 */
	private String formatRect = null;
	/**
	 * �ߵ���ɫ<br />
	 * 0Ϊ����<br />
	 * 1�Ǻ�ɫ�����һ<br />
	 * 2����ɫ����Ҷ������
	 */
	private int lineColor = 0;
	/**
	 * �ߵķ���<br />
	 * 0�Ǻ�<br />
	 * 1����
	 */
	private int lineDirect = 0;
	/**
	 * ���Ƿ񱻵��
	 */
	private boolean isClicked = false;
	/**
	 * ����Ƿ��Ƶ�����
	 */
	private boolean isMoved = false;
	/**
	 * �ߵı��
	 */
	private String serialNumber = null;
	/**
	 * ��һ����������ߵı��
	 */
	private String preShow = null;
	/**
	 * ��һ����������ߵı��
	 */
	private String afterShow = null;

	/**
	 * Ϊ���л�׼���İ���������<br />
	 * ��� ��
	 */
	public Line() {

	}

	/**
	 * ���췽��
	 * 
	 * @param x
	 *            �����Ͻǵ�x����
	 * @param y
	 *            �����Ͻǵ�y����
	 * @param width
	 *            �ߵĿ�
	 * @param height
	 *            �ߵĸ�
	 * @param lineDirect
	 *            �ߵķ���
	 */
	public Line(int x, int y, int width, int height, int lineDirect, GameControl gameControl) {
		this.gameControl = gameControl;
		// ���÷���
		this.lineDirect = lineDirect;
		// ���ñ�ǩ��λ�úʹ�С
		this.setBounds(x, y, width, height);
	}

	@Override
	public void paint(Graphics g) {
		// �������ƶ��������Ͼ���ʾ������
		if (this.isMoved && this.lineDirect == 0) {
			g.drawImage(AllImages.IMG_HORIZONTA_NONE_LINE.getImage(), 0, 0, getWidth(), getHeight(), null);
		} else if (this.isMoved && this.lineDirect == 1) {
			g.drawImage(AllImages.IMG_VERTICAL_NONE_LINE.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
		// ���߱����������colorΪ1����2������ʾ������
		if (getLineColor() == 2 && getLineDirect() == 0) {
			g.drawImage(AllImages.IMG_HORIZONTAL_P2_LINE.getImage(), 0, 0, getWidth(), getHeight(), null);
		} else if (getLineColor() == 2 && getLineDirect() == 1) {
			g.drawImage(AllImages.IMG_VERTICAL_P2_LINE.getImage(), 0, 0, getWidth(), getHeight(), null);
		} else if (getLineColor() == 1 && getLineDirect() == 1) {
			g.drawImage(AllImages.IMG_VERTICAL_P1_LINE.getImage(), 0, 0, getWidth(), getHeight(), null);
		} else if (getLineColor() == 1 && getLineDirect() == 0) {
			g.drawImage(AllImages.IMG_HORIZONTA_P1_LINE.getImage(), 0, 0, getWidth(), getHeight(), null);
		}

	}

	/**
	 * ��ʼ����
	 */
	public void initLine() {
		step = 0;
		lineColor = 0;
		isClicked = false;
		preShow = null;
		formatRect = null;
		this.setVisible(true);
	}

	public int getLineColor() {
		return lineColor;
	}

	public void setLineColor(int lineColor) {
		DataGame dataGame = gameControl.getServiceGame().getDataGame();
		this.lineColor = lineColor;
		if (lineColor != 0) {
			isClicked = true;
			if (dataGame.getJustShow() != null) {
				// ��ǰһ��������Ϊ���ߵ���һ�����������
				this.setPreShow(dataGame.getJustShow());
				// ����������Ϊǰһ���ߵĺ�һ����
				Line preLne = dataGame.getLinesList().get(getPreShow());
				preLne.setAfterShow(this.serialNumber);
			}
			// �����߱��Ϊ�ոյ��
			dataGame.setJustShow(this.serialNumber);
		} else {
			isClicked = false;
		}
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(step);
		out.writeInt(lineColor);
		out.writeObject(formatRect);
		out.writeObject(serialNumber);
		out.writeObject(preShow);
		out.writeObject(afterShow);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.step = in.readInt();
		this.lineColor = in.readInt();
		this.formatRect = (String) in.readObject();
		this.serialNumber = (String) in.readObject();
		this.preShow = (String) in.readObject();
		this.afterShow = (String) in.readObject();
	}

	public int getLineDirect() {
		return lineDirect;
	}

	public void setLineDirect(int lineDirect) {
		this.lineDirect = lineDirect;
	}

	public boolean isClicked() {
		return isClicked;
	}

	public boolean isMoved() {
		return isMoved;
	}

	public void setMoved(boolean isMoved) {
		this.isMoved = isMoved;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getPreShow() {
		return preShow;
	}

	public void setPreShow(String prevShow) {
		this.preShow = prevShow;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getFormatRect() {
		return formatRect;
	}

	public void setFormatRect(String formatRect) {
		this.formatRect = formatRect;
	}

	public String getAfterShow() {
		return afterShow;
	}

	public void setAfterShow(String afterShow) {
		this.afterShow = afterShow;
	}

}
