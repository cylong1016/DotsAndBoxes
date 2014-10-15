package data;

import java.io.Serializable;

/**
 * 线的数据
 * 
 * @author cylong
 * @version May 24, 2014 12:52:39 AM
 */
public class DataLine implements Serializable{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/**
	 * 线在第几步被点击
	 */
	private int step = 0;
	/**
	 * 线的颜色<br />
	 * 0为不画<br />
	 * 1是玩家一<br />
	 * 2是玩家二或电脑
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
	 * 初始化线
	 */
	public void initLine() {
		step = 0;
		lineColor = 0;
		isClicked = false;
		preShow = null;
	}

	
	/**
	 * @version May 24, 2014  12:58:50 AM
	 * @return the step
	 */
	public int getStep () {
		return this.step;
	}

	
	/**
	 * @version May 24, 2014  12:58:50 AM
	 * @param step the step to set
	 */
	public void setStep (int step) {
		this.step = step;
	}

	
	/**
	 * @version May 24, 2014  12:58:50 AM
	 * @return the lineColor
	 */
	public int getLineColor () {
		return this.lineColor;
	}

	
	/**
	 * @version May 24, 2014  12:58:50 AM
	 * @param lineColor the lineColor to set
	 */
	public void setLineColor (int lineColor) {
		this.lineColor = lineColor;
	}

	
	/**
	 * @version May 24, 2014  12:58:50 AM
	 * @return the lineDirect
	 */
	public int getLineDirect () {
		return this.lineDirect;
	}

	
	/**
	 * @version May 24, 2014  12:58:50 AM
	 * @param lineDirect the lineDirect to set
	 */
	public void setLineDirect (int lineDirect) {
		this.lineDirect = lineDirect;
	}

	
	/**
	 * @version May 24, 2014  12:58:50 AM
	 * @return the isClicked
	 */
	public boolean isClicked () {
		return this.isClicked;
	}

	
	/**
	 * @version May 24, 2014  12:58:50 AM
	 * @param isClicked the isClicked to set
	 */
	public void setClicked (boolean isClicked) {
		this.isClicked = isClicked;
	}

	
	/**
	 * @version May 24, 2014  12:58:50 AM
	 * @return the isMoved
	 */
	public boolean isMoved () {
		return this.isMoved;
	}

	
	/**
	 * @version May 24, 2014  12:58:50 AM
	 * @param isMoved the isMoved to set
	 */
	public void setMoved (boolean isMoved) {
		this.isMoved = isMoved;
	}

	
	/**
	 * @version May 24, 2014  12:58:50 AM
	 * @return the serialNumber
	 */
	public String getSerialNumber () {
		return this.serialNumber;
	}

	
	/**
	 * @version May 24, 2014  12:58:50 AM
	 * @param serialNumber the serialNumber to set
	 */
	public void setSerialNumber (String serialNumber) {
		this.serialNumber = serialNumber;
	}

	
	/**
	 * @version May 24, 2014  12:58:50 AM
	 * @return the preShow
	 */
	public String getPreShow () {
		return this.preShow;
	}

	
	/**
	 * @version May 24, 2014  12:58:50 AM
	 * @param preShow the preShow to set
	 */
	public void setPreShow (String preShow) {
		this.preShow = preShow;
	}

	
	/**
	 * @version May 24, 2014  12:58:50 AM
	 * @return the afterShow
	 */
	public String getAfterShow () {
		return this.afterShow;
	}

	
	/**
	 * @version May 24, 2014  12:58:50 AM
	 * @param afterShow the afterShow to set
	 */
	public void setAfterShow (String afterShow) {
		this.afterShow = afterShow;
	}
	
}
