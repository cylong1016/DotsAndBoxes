package ui.panel.start;

import java.awt.Graphics;

import ui.AllImages;
import util.GameUtil;

/**
 * 画机器人
 * 
 * @author cylong
 * @since 2014 / 5 / 15 12：14 AM
 */
public class PanelStartRobot extends PanelStartFlash {

	private static final long serialVersionUID = 1L;

	private int robot0X = -300;
	private int robot0Y = 0;
	private int robot1X = 0;
	private int robot1Y = -400;
	private int robot2X = 0;
	private int robot2Y = 300;
	private int robot3X = 300;
	private int robot3Y = 0;
	private boolean drawrobot0 = false;
	private boolean drawrobot1 = false;
	private boolean drawrobot2 = false;
	private boolean drawrobot3 = false;
	private double speed = 5;
	private boolean robot2Back = false;

	@Override
	public void paint(Graphics g) {
		// 画机器人
		this.drawRobot(0, robot0X, robot0Y, drawrobot0, g);
		this.drawRobot(2, robot2X, robot2Y, drawrobot2, g);
		this.drawRobot(1, robot1X, robot1Y, drawrobot1, g);
		this.drawRobot(3, robot3X, robot3Y, drawrobot3, g);
	}

	private void drawRobot(int index, int x, int y, boolean draw, Graphics g) {
		if (draw) {
			g.drawImage(AllImages.IMG_ROBOTS[index], x, y, getWidth(), getHeight(), null);
		}
	}

	@Override
	public void run() {
		drawrobot0 = true;
		while (true) {
			GameUtil.sleep(10);

			if (drawrobot0 && robot0X < 0) {
				robot0X += speed;
			} else if (drawrobot0) {
				drawrobot2 = true;
			}

			if (drawrobot2 && robot2Y > 0) {
				robot2Y -= speed;
			} else if (drawrobot2) {
				drawrobot3 = true;
			}

			if (drawrobot3 && robot3X > 0) {
				robot3X -= speed;
			} else if (drawrobot3) {
				drawrobot1 = true;
			}

			if (drawrobot1 && robot1Y < 0 && !robot2Back) {
				robot1Y += (speed * 2);
			} else if (drawrobot1 && speed > 0 && !robot2Back) {
				speed -= 0.05;
				robot1Y += (speed * 2);
				if (speed <= 0) {
					robot2Back = true;
				}
			} else if (drawrobot1 && speed < 5 && robot2Back) {
				speed += 0.05;
				robot1Y -= (speed * 2);
				if(robot1Y < 0) {
					robot1Y = 0;
				}
			} else if(drawrobot1) {
				break;
			}
			this.addImgIndex(AllImages.IMG_ROBOTS.length);
			this.repaint();
		}
	}

}
