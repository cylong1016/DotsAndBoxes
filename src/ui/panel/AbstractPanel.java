package ui.panel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.AllImages;
import ui.window.FrameGame;
import util.GameUtil;
import control.GameControl;
import data.DataConfig;
import data.DataGame;

/**
 * �Զ����Panel<br />
 * �������²�java�Դ��ġ�
 * 
 * @author ������
 * @since 2014 / 3 / 19 6 : 45��PM
 */
public abstract class AbstractPanel extends JPanel implements Runnable, MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;
	/**
	 * ��Ϸ�����
	 */
	protected FrameGame frameGame;
	/**
	 * ��Ϸ������
	 */
	protected GameControl gameControl = null;
	/**
	 * ��Ϸ���ݿ�
	 */
	protected DataGame dataGame = null;

	/**
	 * ��ùر�ͼƬ�Ŀ�͸�
	 */
	protected static final int WINDOW_EXIT_H = AllImages.WINDOW_EXIT.getHeight(null);
	protected static final int WINDOW_EXIT_W = AllImages.WINDOW_EXIT.getWidth(null);
	/**
	 * �����С��ͼƬ�Ŀ�͸�
	 */
	protected static final int WINDOW_MIN_H = AllImages.WINDOW_MIN.getHeight(null);
	protected static final int WINDOW_MIN_W = AllImages.WINDOW_MIN.getWidth(null);
	/**
	 * Panel�Ŀ�
	 */
	protected int width = FrameGame.WIDTH;

	/**
	 * Panel�ĸ�
	 */
	protected int height = FrameGame.HEIGHT;
	/**
	 * �ر�ͼƬ�����Ͻ�����
	 */
	protected int exitX = 0;
	protected int exitY = 0;
	/**
	 * ��С��ͼƬ�����Ͻ�����
	 */
	protected int minX = 0;
	protected int minY = 0;
	/**
	 * �ж��Ƿ��ƶ����رհ�ť��
	 */
	protected boolean isMoveExit = false;
	/**
	 * �ж��Ƿ��ƶ�����С����ť��
	 */
	protected boolean isMoveMin = false;
	/**
	 * �رչ���������ŵĴ���
	 */
	protected static int TIMES_EXIT = 0;
	/**
	 * ��С������������ŵĴ���
	 */
	protected static int TIMES_MIN = 0;
	/**
	 * ����͸��ֵ
	 */
	protected float hyalineValue = 0f;
	/** ����͸���� */
	protected float hyaline = 0.0f;

	/**
	 * ���췽��
	 * 
	 * @param frameGame
	 *            ������Ϸ�����
	 * @param gameControl
	 *            ������Ϸ������
	 * @param dataGame
	 *            ��Ϸ����
	 */
	public AbstractPanel (FrameGame frameGame, GameControl gameControl, DataGame dataGame) {
		// ��frameGame��ܴ���panelGame
		// Ϊ��ʵ��JFrame������С�����϶��ȹ���
		this.frameGame = frameGame;
		// ��ʼ����Ϸ������
		this.gameControl = gameControl;
		// ��ʼ����Ϸ����
		this.dataGame = dataGame;
	}

	public AbstractPanel () {

	}

	/**
	 * ��ʼ��Panel��������
	 */
	protected void initPanel () {
		// ����Panel��С
		this.setSize(width, height);
		// ����panelΪ͸��
		this.setOpaque(false);
		// ���ò��ֹ���Ϊ���Բ���
		this.setLayout(null);
		// ע����������
		this.addMouseListener(this);
		// ע������ƶ�������
		this.addMouseMotionListener(this);
		// ���ùرհ�ť������
		this.exitX = this.width - WINDOW_EXIT_W - 1;
		this.exitY = 1;
		// ������С����ť������
		this.minX = this.width - WINDOW_EXIT_W - WINDOW_MIN_W - 10;
		this.minY = 1;
		// �����ƶ�����
		this.setDragable();
	}

	/**
	 * ��ʼ��Panel����ͼ��
	 * 
	 * @param g
	 *            һֻ����
	 */
	protected void initPanel (Graphics g) {
		// һ��Ҫ���ø���ķ������������ˣ���
		super.paint(g);
		// �����رհ�ť
		g.drawImage(AllImages.WINDOW_EXIT, exitX, exitY, null);
		if (isMoveExit) {
			g.drawImage(AllImages.WINDOW_EXIT1, exitX, exitY, null);
		}
		// ������С����ť��
		g.drawImage(AllImages.WINDOW_MIN, minX, exitY, null);
		if (isMoveMin) {
			g.drawImage(AllImages.WINDOW_MIN1, minX, exitY, null);
		}

	}
	/**
	 * ���û�����͸������Ч��
	 * 
	 * @param g
	 * @author cylong
	 * @version May 20, 2014 12:56:08 AM
	 */
	public void setPaintbrush (Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Graphics2D g2 = (Graphics2D) g;
		if (hyaline > 1) {
			hyaline = 1;
		}
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, hyaline));
	}

	/**
	 * ���ӻ��ʵ�͸����
	 * 
	 * @author cylong
	 * @version May 20, 2014 1:00:49 AM
	 */
	public void addHyaline () {
		hyaline += 0.1f;
		if (hyaline > 1) {
			hyaline = 1;
		}
	}

	/**
	 * �߳���Ĺرպ���С����ť�ķ���
	 */
	protected void isClick () {
		// �ж��Ƿ񲥷Ź���ƶ����ر��ϵ�����
		if (isMoveExit && TIMES_EXIT == 0) {
			// �ж���Ϸ�Ƿ���
			if (!DataConfig.isMute()) {
				GameUtil.startMoveButtonSound();
				TIMES_EXIT++;
			}
		}
		// �ж��Ƿ񲥷Ź���ƶ�����С���ϵ�����
		if (isMoveMin && TIMES_MIN == 0) {
			// �ж���Ϸ�Ƿ���
			if (!DataConfig.isMute()) {
				GameUtil.startMoveButtonSound();
				TIMES_MIN++;
			}
		}
	}

	@Override
	public void paint (Graphics g) {
		super.paint(g);
	}

	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;

	/**
	 * ���ÿ����϶�����
	 */
	protected void setDragable () {
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
					loc = new Point(frameGame.getLocation().x + e.getX() - tmp.x, frameGame.getLocation().y + e.getY() - tmp.y);
					frameGame.setLocation(loc);
				}
			}
		});
	}

	/**
	 * �������ַ�����ʵ��λ����������0����
	 * 
	 * @param x
	 *            ���Ͻ�x����
	 * @param y
	 *            ���Ͻ�y����
	 * @param num
	 *            ��Ҫ��ʾ������
	 * @param maxBit
	 *            ���λ��
	 * @param g
	 *            ����
	 */
	protected void drawNumber (int x, int y, int num, int maxBit, Image number, int w, int h, Graphics g) {
		String strNum = Integer.toString(num);
		for (int i = strNum.length(); i < maxBit; i++) {
			strNum = "0" + strNum;
		}
		for (int i = 0; i < strNum.length(); i++) {
			// if (maxBit - i <= strNum.length()) {
			int index = strNum.length() - (maxBit - i);
			int bit = strNum.charAt(index) - '0';
			g.drawImage(number, x + w * i, y, x + w * (i + 1), y + h, bit * w, 0, (bit + 1) * w, h, null);
			// }
		}
	}

	public int getWidth () {
		return width;
	}

	public void setWidth (int width) {
		this.width = width;
	}

	public int getHeight () {
		return height;
	}

	public void setHeight (int height) {
		this.height = height;
	}

	@Override
	public void mouseClicked (MouseEvent arg0) {
		// �ж�����Ƿ����رհ�ť
		if (arg0.getX() > exitX && arg0.getX() < exitX + WINDOW_EXIT_W && arg0.getY() > exitY && arg0.getY() < exitY + WINDOW_EXIT_H) {
			GameUtil.startClickSound();
			System.exit(0);
		}
		// �ж�����Ƿ�����С����ť
		if (arg0.getX() > minX && arg0.getX() < minX + WINDOW_MIN_W && arg0.getY() > minY && arg0.getY() < minY + WINDOW_MIN_H) {
			GameUtil.startClickSound();
			frameGame.setExtendedState(JFrame.ICONIFIED);
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
	}

	@Override
	public void mouseExited (MouseEvent e) {
	}

	@Override
	public void mouseDragged (MouseEvent e) {
	}

	@Override
	public void mouseMoved (MouseEvent arg0) {
		// �ж�����Ƿ��ƶ����رհ�ť�ķ�Χ��
		if (arg0.getX() > exitX && arg0.getX() < exitX + WINDOW_EXIT_W && arg0.getY() > exitY && arg0.getY() < exitY + WINDOW_EXIT_H) {
			isMoveExit = true;
		} else {
			isMoveExit = false;
			TIMES_EXIT = 0;
		}
		// �ж�����Ƿ��ƶ�����С����ť�ķ�Χ��
		if (arg0.getX() > minX && arg0.getX() < minX + WINDOW_MIN_W && arg0.getY() > minY && arg0.getY() < minY + WINDOW_MIN_H) {
			isMoveMin = true;
		} else {
			isMoveMin = false;
			TIMES_MIN = 0;
		}
	}

}
