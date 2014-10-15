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
 * 横向的线的类
 * 
 * @author 陈云龙
 * @since 2014 / 3 / 19 8 : 34 PM
 */
public class Line extends JLabel implements Externalizable {

	private static final long serialVersionUID = 1L;

	/**
	 * 游戏控制器
	 */
	private GameControl gameControl = null;
	/**
	 * 线在第几步被点击
	 */
	private int step = 0;
	/**
	 * 点击该线后是否形成方块
	 */
	private String formatRect = null;
	/**
	 * 线的颜色<br />
	 * 0为不画<br />
	 * 1是红色，玩家一<br />
	 * 2是蓝色，玩家二或电脑
	 */
	private int lineColor = 0;
	/**
	 * 线的方向<br />
	 * 0是横<br />
	 * 1是纵
	 */
	private int lineDirect = 0;
	/**
	 * 线是否被点击
	 */
	private boolean isClicked = false;
	/**
	 * 鼠标是否移到线上
	 */
	private boolean isMoved = false;
	/**
	 * 线的编号
	 */
	private String serialNumber = null;
	/**
	 * 上一个被点击的线的编号
	 */
	private String preShow = null;
	/**
	 * 后一个被点击的线的编号
	 */
	private String afterShow = null;

	/**
	 * 为序列化准备的啊！！！！<br />
	 * 疯掉 了
	 */
	public Line() {

	}

	/**
	 * 构造方法
	 * 
	 * @param x
	 *            线左上角的x坐标
	 * @param y
	 *            线左上角的y坐标
	 * @param width
	 *            线的宽
	 * @param height
	 *            线的高
	 * @param lineDirect
	 *            线的方向
	 */
	public Line(int x, int y, int width, int height, int lineDirect, GameControl gameControl) {
		this.gameControl = gameControl;
		// 设置方向
		this.lineDirect = lineDirect;
		// 设置标签的位置和大小
		this.setBounds(x, y, width, height);
	}

	@Override
	public void paint(Graphics g) {
		// 如果光标移动到该线上就显示出该线
		if (this.isMoved && this.lineDirect == 0) {
			g.drawImage(AllImages.IMG_HORIZONTA_NONE_LINE.getImage(), 0, 0, getWidth(), getHeight(), null);
		} else if (this.isMoved && this.lineDirect == 1) {
			g.drawImage(AllImages.IMG_VERTICAL_NONE_LINE.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
		// 该线被点击后设置color为1或者2，就显示出线了
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
	 * 初始化线
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
				// 将前一条线设置为此线的线一条被点击的线
				this.setPreShow(dataGame.getJustShow());
				// 将此线设置为前一条线的后一条线
				Line preLne = dataGame.getLinesList().get(getPreShow());
				preLne.setAfterShow(this.serialNumber);
			}
			// 将此线标记为刚刚点击
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
