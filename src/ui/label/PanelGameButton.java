package ui.label;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import control.GameControl;
import data.DataConfig;

/**
 * 游戏下棋面板的按钮
 * 
 * @author cylong
 * @version May 20, 2014 3:19:39 AM
 */
public class PanelGameButton extends LabelButton {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * @param x
	 *            标签左上角x坐标
	 * @param y
	 *            标签左上角y坐标
	 * @param width
	 *            标签的宽
	 * @param height
	 *            标签的高
	 * @param im1
	 *            鼠标未移动到标签上显示的图片
	 * @param im2
	 *            鼠标移动到标签上显示的图片
	 * @param gameControl
	 *            连接游戏控制器
	 * @param method
	 *            该标签调用的方法
	 * @author cylong
	 * @version May 20, 2014 3:22:10 AM
	 */
	public PanelGameButton (int x, int y, int width, int height, Image im1, Image im2, GameControl gameControl, String method, boolean b) {
		super(x, y, width, height, im1, gameControl, method, b);
		this.im2 = im2;
		this.isClick = DataConfig.isMute();
	}
	
	/** 图片扩大的大小 */
	private int expend = 2;
	@Override
	public void paint(Graphics g) {
		if(isMove && !isClick) {
			g.drawImage(im1, 0, 0, getWidth(), getHeight(), this);
		} else if(!isMove && !isClick) {
			g.drawImage(im1, expend, expend, getWidth() - (expend << 1), getHeight() - (expend << 1), this);
		} else if(isMove && isClick) {
			g.drawImage(im2, 0, 0, getWidth(), getHeight(), this);
		} else if(!isMove && isClick) {
			g.drawImage(im2, expend, expend, getWidth() - (expend << 1), getHeight() - (expend << 1), this);
		}
	}
	
	@Override
	public void mouseClicked (MouseEvent e) {
		this.isClick = !this.isClick;
		this.repaint();
		// 悔棋
		if (method.equals("TakeBack")) {
			this.gameControl.takeBack();
		}
		// 控制游戏暂停
		if(method.equals("SetPause")) {
			DataConfig.setPause(this.isClick);
		}
		// 设置是否静音
		if(method.equals("SetMute")) {
			DataConfig.setMute(this.isClick);
		}
	}

}
