package ui.label;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import control.GameControl;

/**
 * 游戏模式选择的按钮
 * 
 * @author cylong
 * @version May 24, 2014 11:07:23 PM
 */
public class LabelModelChoose extends LabelButton implements Runnable {

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
	 * 
	 * @author cylong
	 * @version May 24, 2014 11:07:55 PM
	 */

	@Override
	public void mouseClicked (MouseEvent e) {
		this.isClick = true;
		this.repaint();
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
			this.gameControl.startSpcialMode();
		}
		// 特殊模式的计分模式
		if (method.equals("ScoreModel")) {
			this.gameControl.startScoreModel();
		}

	}

	/** 图片区域在label中的x坐标 */
	private int imageX;
	/** 图片区域在label中的y坐标 */
	private int imageY;

	/** 图片区域的宽度 */
	private int width;
	/** 图片区域的高度 */
	private int height;

	/** 初始角度 */
	private double degree;
	/** 旋转将停止时的加速度（角速度 */
	private double rotateAcceleration = -0.05;
	/** 旋转时的速度（角速度 */
	private double rotateSpeed = 2.0;
	private double rotateBorder;
	/** 旋转中心的x坐标 */
	private int rotateCenterX;
	/** 旋转中心的y坐标 */
	private int rotateCenterY;

	/** label移动的速度 */
	private double moveSpeed = 2.0;

	/** 结束时图片横坐标的期望值 */
	private int x;
	/** 结束时图片纵坐标的期望值 */
	private int y;

	/** 判断旋转是否结束 */
	private boolean rotateEnd = false;

	/**
	 * 创建一个label
	 * 
	 * @param width
	 *            图片区域的宽度
	 * @param height
	 *            图片区域的高度
	 * @param x
	 *            图片区域的x坐标
	 * @param y
	 *            图片区域的y坐标
	 * @param rotateDirect
	 *            旋转方向，true为逆时针向左移动，false为顺时针向左移动
	 * 
	 * @author lwpeng
	 * @version May 25, 2014 12:57:18 AM
	 */
	public LabelModelChoose (int x, int y, int width, int height, Image im1, GameControl gameControl, String method, boolean rotateDirect) {
		super(x, y, width, height, im1, gameControl, method, true);
		this.setVisible(false);
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;

		int h = height / 2;

		rotateCenterY = (int) Math.sqrt((width - h) * (width - h) + h * h) + 1;
		imageY = rotateCenterY - h;

		/* 设置label大小 */
		int size = rotateCenterY + (int) (height * 0.75);
		this.setSize(size, size);
		this.setOpaque(false);

		/* 设置旋转相关数据 */
		if (rotateDirect) {
			degree = 90.0;
			imageX = rotateCenterY - width + h;
			rotateCenterX = rotateCenterY;
			this.setLocation(-imageX + x + 120, -imageY + y);
		} else {
			degree = -90.0;
			rotateCenterX = (int) (height * 0.75);
			imageX = height - rotateCenterX;
			rotateSpeed = -rotateSpeed;
			rotateAcceleration = -rotateAcceleration;
			moveSpeed = -moveSpeed;
			this.setLocation(x - 120 - imageX, y - imageY);
		}
		rotateBorder = rotateSpeed * rotateSpeed / rotateAcceleration / 2;

		this.addMouseListener(this);
	}

	@Override
	public void paint (Graphics g) {

		/* 设置画笔 */
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		/* 示例 */
		g2d.rotate(degree * Math.PI / 180, rotateCenterX, rotateCenterY);
		// g2d.drawImage(i, imageX, imageY, width, height, null);
		if (b) {
			if (isMove) {
				g2d.drawImage(im1, imageX, imageY, width, height, null);
				g2d.drawImage(im2, imageX, imageY, width, height, null);
			} else {
				g2d.drawImage(im1, imageX, imageY, width, height, null);
			}
		} else {
			if (isMove) {
				g2d.drawImage(im1, imageX, imageY, width, height, null);
			} else {
				g2d.drawImage(im1, imageX, imageY, width, height, null);
			}
		}
		if (isClick) {
			g2d.drawImage(im1, imageX, imageY, width, height, null);
			isClick = false;
			isMove = false;
		}
	}

	/**
	 * 返回旋转动作是否结束
	 * 
	 * @return
	 */
	public boolean isRotateEnd () {
		return rotateEnd;
	}

	public void run () {
		this.setVisible(true);
		double x = this.getX();
		int y = this.getY();
		boolean b;
		for (;;) {
			if (b = Math.abs(degree) > Math.abs(moveSpeed)) {
				if (Math.abs(degree) <= Math.abs(rotateBorder)) {
					rotateSpeed += rotateAcceleration;
				}
				degree -= rotateSpeed;
			} else {
				degree = 0;
			}
			x -= moveSpeed;
			this.setLocation((int) x, y);
			repaint();
			if (!b) {
				imageX = imageY = 0;
				this.setLocation(this.x, this.y);
				this.setSize(width, height);
				repaint();
				rotateEnd = true;
				return;
			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
