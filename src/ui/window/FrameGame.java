package ui.window;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.GameUtil;

/**
 * �Լ���Frame
 * 
 * @author ������
 * @since 2014 / 3 / 16 2 : 33 AM
 */
public class FrameGame extends JFrame {

	/**
	 * �����嵽frame��
	 * 
	 * @param panel
	 */
	public void setPanel (JPanel panel) {
		// ������
		this.add(panel);
		this.setVisible(true);
	}

	private static final long serialVersionUID = 1L;
	/**
	 * ����Ŀ�
	 */
	public static final int WIDTH = 800;
	/**
	 * ����ĸ�
	 */
	public static final int HEIGHT = 600;

	/**
	 * ���췽��
	 * 
	 * @param panel
	 *            ���һ��panel
	 */
	public FrameGame () {
		// ȥ��Ĭ�ϵı߿�
		this.setUndecorated(true);
		// ����͸��ɫ
		this.setBackground(new Color(0, 0, 0, 0.0f));
		// ���ô���Ŀ�͸�
		this.setSize(WIDTH, HEIGHT);
		// ���ھ���
		GameUtil.setFrameCenter(this);
		this.setLayout(null);
		// �����ƶ�����
		this.setDragable();
	}

	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;

	/**
	 * ���ÿ����϶�����
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
