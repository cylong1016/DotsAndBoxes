package ui.window;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.GameUtil;

/**
 * 自己的Frame
 * 
 * @author 陈云龙
 * @since 2014 / 3 / 16 2 : 33 AM
 */
public class FrameGame extends JFrame {

	/**
	 * 添加面板到frame上
	 * 
	 * @param panel
	 */
	public void setPanel (JPanel panel) {
		// 添加面板
		this.add(panel);
		this.setVisible(true);
	}

	private static final long serialVersionUID = 1L;
	/**
	 * 窗体的宽
	 */
	public static final int WIDTH = 800;
	/**
	 * 窗体的高
	 */
	public static final int HEIGHT = 600;

	/**
	 * 构造方法
	 * 
	 * @param panel
	 *            添加一个panel
	 */
	public FrameGame () {
		// 去掉默认的边框
		this.setUndecorated(true);
		// 创建透明色
		this.setBackground(new Color(0, 0, 0, 0.0f));
		// 设置窗体的宽和高
		this.setSize(WIDTH, HEIGHT);
		// 窗口居中
		GameUtil.setFrameCenter(this);
		this.setLayout(null);
		// 可以移动窗口
		this.setDragable();
	}

	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;

	/**
	 * 设置可以拖动窗体
	 */
	public void setDragable () {
		this.addMouseListener(new MouseAdapter() {

			public void mouseReleased (java.awt.event.MouseEvent e) {
				isDragged = false;
			}

			public void mousePressed (java.awt.event.MouseEvent e) {
				tmp = new Point(e.getX(), e.getY());
				isDragged = true;
			}

		});

		this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

			public void mouseDragged (java.awt.event.MouseEvent e) {
				if (isDragged) {
					loc = new Point(getLocation().x + e.getX() - tmp.x, getLocation().y + e.getY() - tmp.y);
					setLocation(loc);
				}
			}
		});
	}
}
