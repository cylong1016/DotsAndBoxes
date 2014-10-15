package ui.label;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import ui.AllImages;
import util.GameUtil;
import control.GameControl;

/**
 * 游戏按钮<br />
 * 继承了JLabel
 * 
 * @author 陈云龙
 * @since 2014 / 4 / 7 4 : 45 AM
 */
public class LabelButton extends JLabel implements MouseListener {

	public static final long serialVersionUID = 1L;
	public static boolean playMusic = true;
	/**
	 * 游戏控制器
	 */
	protected GameControl gameControl = null;
	/**
	 * 是否移动到标签上
	 */
	protected boolean isMove = false;
	/**
	 * 是否被点击
	 */
	protected boolean isClick = false;
	/**
	 * 图片1
	 */
	protected Image im1 = null;
	/**
	 * 图片2
	 */
	protected Image im2 = null;
	/**
	 * 绘图方式
	 */
	protected boolean b = false;
	/**
	 * 标签调用的方法
	 */
	protected String method = null;

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
	 */
	public LabelButton (int x, int y, int width, int height, Image im1, GameControl gameControl, String method, boolean b) {
		this.im1 = im1;
		this.im2 = AllImages.IMG_LABEL_BORDER;
		this.b = b;
		this.method = method;
		this.gameControl = gameControl;
		// 设置标签的大小和位置
		this.setBounds(x, y, width, height);
		this.addMouseListener(this);
	}

	public LabelButton() {
		
	}
	@Override
	public void paint (Graphics g) {
		if (b) {
			if (isMove) {
				g.drawImage(im1, 0, 0, getWidth(), getHeight(), null);
				g.drawImage(im2, 0, 0, getWidth(), getHeight(), null);
			} else {
				g.drawImage(im1, 3, 2, getWidth() - 6, getHeight() - 4, null);
			}
		} else {
			if (isMove) {
				g.drawImage(im1, 0, 0, getWidth(), getHeight(), null);
			} else {
				g.drawImage(im1, 3, 3, getWidth() - 6, getHeight() - 6, null);
			}
		}
		if (isClick) {
			g.drawImage(im1, 0, 0, getWidth(), getHeight(), null);
			isClick = false;
			isMove = false;
		}
	}

	@Override
	public void mouseClicked (MouseEvent e) {
		this.isClick = true;
		GameUtil.startClickSound();
		this.repaint();
		// 开始游戏按钮，进入游戏模式选择
		if (method.equals("StartGame")) {
			this.gameControl.startChoose();
		}
		// 单人游戏
		if (method.equals("SinglePlayer")) {
			this.gameControl.getServiceGame().getDataGame().setGameMode(0);
			if (gameControl.getServiceGame().getDataGame().getFirstSecond() == 0) {
				// 使玩家一开始下
				this.gameControl.getServiceGame().getDataGame().getPlayer2().setClick(true);
			} else {
				// 使玩家二开始下
				this.gameControl.getServiceGame().getDataGame().getPlayer1().setClick(true);
			}
			this.gameControl.startGame();
		}
		// 双人游戏
		if (method.equals("TwoPlayers")) {
			this.gameControl.getServiceGame().getDataGame().setGameMode(1);
			if (gameControl.getServiceGame().getDataGame().getFirstSecond() == 0) {
				// 使玩家一开始下
				this.gameControl.getServiceGame().getDataGame().getPlayer2().setClick(true);
			} else {
				// 使玩家二开始下
				this.gameControl.getServiceGame().getDataGame().getPlayer1().setClick(true);
			}
			this.gameControl.startGame();
		}
		// 联机对战
		if (method.equals("TwoPlayersConnect")) {
			this.gameControl.getServiceGame().getDataGame().setGameMode(2);
			this.gameControl.startConnectGame();
		}
		// 创建房间
		if (method.equals("CreateHome")) {
			this.gameControl.createHome();
		}
		// 加入房间
		if (method.equals("JoinHome")) {
			this.gameControl.joinHome();
		}
		// 特殊模式
		if (method.equals("SpcialMode")) {
			this.gameControl.startScoreModel();
		}
		// 特殊模式的计分模式
		if (method.equals("ScoreModel")) {
			this.gameControl.startScoreModel();
		}
		if(method.equals("GoToModelChoose")) {
			this.gameControl.goToModelChoose();
		}
		if(method.equals("Help")) {
			this.gameControl.startAboutUs();
		}

	}

	@Override
	public void mousePressed (MouseEvent e) {
	}

	@Override
	public void mouseReleased (MouseEvent e) {
	}

	@Override
	public void mouseEntered (MouseEvent e) {
		this.isMove = true;
		GameUtil.startMoveButtonSound();
		this.repaint();
	}

	@Override
	public void mouseExited (MouseEvent e) {
		this.isMove = false;
		this.repaint();
	}

}