package data;

import java.io.Serializable;

/**
 * @author cylong
 * @since 2014 / 5 / 12 9:10 PM
 */
public class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * ��ҵķ���
	 */
	private int score = 0;
	/**
	 * �ж�����Ƿ����ɹ�
	 */
	private boolean Click = false;
	/**
	 * �ж�����Ƿ��γɷ���
	 */
	private boolean isFormRect = false;
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isClick() {
		return Click;
	}
	public void setClick(boolean click) {
		Click = click;
	}
	public boolean isFormRect() {
		return isFormRect;
	}
	public void setFormRect(boolean isFormRect) {
		this.isFormRect = isFormRect;
	}
	
}
