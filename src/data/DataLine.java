package data;

import java.io.Serializable;

/**
 * �ߵ�����
 * 
 * @author cylong
 * @version May 24, 2014 12:52:39 AM
 */
public class DataLine implements Serializable{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/**
	 * ���ڵڼ��������
	 */
	private int step = 0;
	/**
	 * �ߵ���ɫ<br />
	 * 0Ϊ����<br />
	 * 1�����һ<br />
	 * 2����Ҷ������
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
	 * ��ʼ����
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
