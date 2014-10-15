package ui.label;

import java.awt.Graphics;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Vector;

import javax.swing.JLabel;

import ui.AllImages;

/**
 * 线围成的方块
 * 
 * @author 陈云龙
 * @since 2014 / 3 / 20 10 : 06 PM
 */
public class Rect extends JLabel implements Externalizable {

	private static final long serialVersionUID = 1L;
	/**
	 * 方块的编号
	 */
	private String serialNumber = null;
	/**
	 * 方块内部的颜色<br />
	 * 0为空<br />
	 * 1是红色，玩家一 <br />
	 * 2是蓝色，玩家二或电脑
	 */
	private int insideColor = 0;
	/**
	 * 是否已经显示
	 */
	private boolean isShow = false;

	/**
	 * 每个方块对应四条线
	 */
	private Vector<Line> lines = new Vector<Line>();
	/**
	 * 方块代表的分数【默认为1】
	 */
	private int score = 1;
	/**
	 * 是否是计分模式
	 */
	private boolean isScoreModel = false;

	public Rect() {
	}

	/**
	 * 构造方法
	 * 
	 * @param x
	 *            方块的左上角x坐标
	 * @param y
	 *            方块的左上角y坐标
	 * @param size
	 *            方块的大小
	 */
	public Rect(int x, int y, int size) {
		// 设置位置和大小
		this.setBounds(x, y, size, size);
	}

	/**
	 * 画出方块内的图片
	 */
	@Override
	public void paint(Graphics g) {
		if (this.insideColor == 1) {
			g.drawImage(AllImages.IMG_P1_INSIDE.getImage(), 0, 0, getWidth(), getHeight(), null);
		} else if (this.insideColor == 2) {
			g.drawImage(AllImages.IMG_P2_INSIDE.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
		if (isScoreModel) {
			this.drawNumber(11, 13, score, 1, g);
		}
	}

	/**
	 * 初始化方块
	 */
	public void initRect() {
		insideColor = 0;
		isShow = false;
	}

	/**
	 * 画出数字方法
	 * 
	 * @param x
	 *            左上角x坐标
	 * @param y
	 *            左上角y坐标
	 * @param num
	 *            需要显示的数字
	 * @param maxBit
	 *            最大位数
	 * @param g
	 *            画笔
	 */
	protected void drawNumber(int x, int y, int num, int maxBit, Graphics g) {
		String strNum = Integer.toString(num);
		for (int i = 0; i < maxBit; i++) {
			if (maxBit - i <= strNum.length()) {
				int index = strNum.length() - (maxBit - i);
				int bit = strNum.charAt(index) - '0';
				g.drawImage(AllImages.IMG_NUM_SCORE, x + AllImages.IMG_NUM_SCORE_W * i, y, x + AllImages.IMG_NUM_SCORE_W * (i + 1), y + AllImages.IMG_NUM_SCORE_H, bit * AllImages.IMG_NUM_SCORE_W, 0, (bit + 1) * AllImages.IMG_NUM_SCORE_W, AllImages.IMG_NUM_SCORE_H, null);
			}
		}
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(serialNumber);
		out.writeInt(insideColor);
		out.writeObject(lines);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.serialNumber = (String) in.readObject();
		this.insideColor = in.readInt();
		this.lines = (Vector<Line>) in.readObject();
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public Vector<Line> getLines() {
		return lines;
	}

	public void setLines(Vector<Line> lines) {
		this.lines = lines;
	}

	public int getInsideColor() {
		return insideColor;
	}

	public void setInsideColor(int insideColor) {
		this.insideColor = insideColor;
		if (insideColor == 1 || insideColor == 2) {
			this.setShow(true);
		} else {
			this.setShow(false);
		}
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the isScoreModel
	 */
	public boolean isScoreModel() {
		return isScoreModel;
	}

	/**
	 * @param isScoreModel
	 *            the isScoreModel to set
	 */
	public void setScoreModel(boolean isScoreModel) {
		this.isScoreModel = isScoreModel;
	}
}
