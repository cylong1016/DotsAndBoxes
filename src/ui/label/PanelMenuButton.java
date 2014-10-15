package ui.label;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

import control.GameControl;

/**
 * 下棋面板中的菜单按钮
 * 
 * @author cylong
 * @version May 20, 2014 3:16:36 PM
 */
public class PanelMenuButton extends LabelButton {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** 按钮是否可用 */
	private boolean isUnabled = false;
	
	/**
	 * @version May 20, 2014  3:59:04 PM
	 * @param isEnabled the isEnabled to set
	 */
	public void setUnabled (boolean isUnabled) {
		this.isUnabled = isUnabled;
	}

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
	 * @version May 20, 2014 3:17:22 PM
	 */
	public PanelMenuButton (int x, int y, int width, int height, Image im1, Image im2, GameControl gameControl, String method, boolean b) {
		super(x, y, width, height, im1, gameControl, method, b);
		this.im2 = im2;
	}

	/** 图片扩大的大小 */
	private int expend = 2;

	@Override
	public void paint (Graphics g) {
		if (isMove && !isUnabled) {
			g.drawImage(im2, 0, 0, getWidth(), getHeight(), null);
		} else {
			g.drawImage(im1, expend, expend, getWidth() - (expend << 1), getHeight() - (expend << 1), null);
		}
	}

	@Override
	public void mouseClicked (MouseEvent e) {
		if(isUnabled) {
			return;
		}
		// 游戏读取棋盘
		if (method.equals("LoadData")) {
			this.gameControl.loadChess();
		}
		// 游戏面板的主菜单
		if (method.equals("MainMenu")) {
			this.gameControl.gotoMainMenu();
		}
		// 重新开始游戏
		if (method.equals("RestartGame")) {
			this.gameControl.restartGame();
		}
		// 游戏面板的保存
		if (method.equals("SaveChess")) {
			this.gameControl.saveChess();
		}
		// 退出系统
		if (method.equals("Exit")) {
			System.exit(0);
		}

	}
}
