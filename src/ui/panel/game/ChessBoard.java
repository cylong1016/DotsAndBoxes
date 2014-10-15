package ui.panel.game;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;

import ui.AllImages;
import ui.label.Dot;
import ui.label.Line;
import ui.label.Rect;
import ui.window.FrameGame;
import control.GameControl;
import data.DataConfig;
import data.DataGame;

/**
 * ��Ϸ������
 * 
 * @author cylong
 * @since 2014 / 3 / 19 5 : 30 PM
 * 
 */
public class ChessBoard extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	/**
	 * ��Ϸ������
	 */
	private GameControl gameControl = null;
	/**
	 * ��Ϸ����
	 */
	private DataGame dataGame = null;
	/**
	 * ���̵Ŀ�
	 */
	private final int BOARD_WIDTH = 500;
	/**
	 * ���̵ĸ�
	 */
	private final int BOARD_HEIGHT = BOARD_WIDTH;
	/**
	 * ���̵��ڱ߾�
	 */
	private final int PADDING = 60;
	/**
	 * ���̵Ĵ�С��ÿ�е�ĸ�����
	 */
	private final int DOTS_NUMBER = DataConfig.getChessBoardSize();
	/**
	 * ���̵�ļ���
	 */
	private Dot[][] dots = new Dot[DOTS_NUMBER][DOTS_NUMBER];
	/**
	 * ���ͼƬ��С
	 */
	private final int IMG_DOTS_SIZE = 180 / DOTS_NUMBER;
	/**
	 * ���ͼƬ���ڱ߾�
	 */
	private final int IMG_DOTS_PADDING = IMG_DOTS_SIZE / 6;
	/**
	 * ����֮��ļ�϶
	 */
	private final int DOTS_INTERVAL = (BOARD_WIDTH - IMG_DOTS_SIZE * DOTS_NUMBER - (PADDING << 1)) / (DOTS_NUMBER - 1);
	/**
	 * ��һ�����x����
	 */
	private final int DOTS_X = PADDING;
	/**
	 * ��һ�����y����
	 */
	private final int DOTS_Y = PADDING;
	/**
	 * ���̵Ĵ�С��ÿ�к��ߵĸ�����
	 */
	private final int HORIZONTAL_LINES_NUMBER = DOTS_NUMBER - 1;
	/**
	 * ���ߵĿ�
	 */
	private final int HORIZONTAL_LINES_WIDTH = DOTS_INTERVAL + (IMG_DOTS_PADDING << 1);
	/**
	 * ���ߵĸ�
	 */
	private final int HORIZONTAL_LINES_HEIGHT = IMG_DOTS_SIZE;
	/**
	 * ��DtoGame����ߵļ���
	 */
	private Hashtable<String, Line> linesList = null;
	/**
	 * ��һ�����ߵ�x����
	 */
	private final int HORIZONTAL_LINES_X = IMG_DOTS_SIZE + DOTS_X - IMG_DOTS_PADDING;
	/**
	 * ��һ�����ߵ�y����
	 */
	private final int HORIZONTAL_LINES_Y = DOTS_Y;
	/**
	 * ������֮��ļ�϶
	 */
	private final int HORIZONTAL_LINES_INTERVAL = IMG_DOTS_SIZE - (IMG_DOTS_PADDING << 1);
	/**
	 * ���̵Ĵ�С��ÿ�����ߵĸ�����
	 */
	private final int VERTICAL_LINES_NUMBER = DOTS_NUMBER - 1;
	/**
	 * ���ߵĿ�
	 */
	private final int VERTICAL_LINES_WIDTH = IMG_DOTS_SIZE;
	/**
	 * ���ߵĸ�
	 */
	private final int VERTICAL_LINES_HEIGHT = DOTS_INTERVAL + (IMG_DOTS_PADDING << 1);
	/**
	 * ��һ�����ߵ�x����
	 */
	private final int VERTICAL_LINES_X = DOTS_X;
	/**
	 * ��һ�����ߵ�y����
	 */
	private final int VERTICAL_LINES_Y = IMG_DOTS_SIZE + DOTS_Y - IMG_DOTS_PADDING;
	/**
	 * ������֮��ļ�϶
	 */
	private final int VERTICAL_LINES_INTERVAL = IMG_DOTS_SIZE - (IMG_DOTS_PADDING << 1);
	/**
	 * ���̷���ļ���
	 */
	private Hashtable<String, Rect> rectList = null;
	/**
	 * ����ĸ���
	 */
	private final int RECT_NUMBER = DOTS_NUMBER - 1;
	/**
	 * ����Ĵ�С
	 */
	private final int RECT_SIZE = HORIZONTAL_LINES_WIDTH;
	/**
	 * ��һ�������x����
	 */
	private final int RECT_X = VERTICAL_LINES_X + VERTICAL_LINES_WIDTH - IMG_DOTS_PADDING;
	/**
	 * ��һ�������y����
	 */
	private final int RECT_Y = HORIZONTAL_LINES_Y + HORIZONTAL_LINES_HEIGHT - IMG_DOTS_PADDING;
	/**
	 * ������֮��ļ�϶
	 */
	private final int RECT_INTERVAL = IMG_DOTS_SIZE - (IMG_DOTS_PADDING << 1);
	/**
	 * ���̵����Ͻ�x����
	 */
	private final int CHESS_BOARD_X = 240;
	/**
	 * ���̵����Ͻ�y����
	 */
	private final int CHESS_BOARD_Y = 50;

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
	public ChessBoard (FrameGame frameGame, GameControl gameControl) {
		this.gameControl = gameControl;
		dataGame = gameControl.getServiceGame().getDataGame();
		linesList = dataGame.getLinesList();
		rectList = dataGame.getRectList();
		// ����Ϊ���Բ���
		this.setLayout(null);
		// ��Ϊ͸��
		this.setOpaque(false);
		// ���ô�С��λ��
		this.setBounds(CHESS_BOARD_X, CHESS_BOARD_Y, BOARD_WIDTH, BOARD_HEIGHT);
		// ��������ϵĵ�
		this.initDots();
		// ���������ϵķ���
		this.initRect();
		// ��������ϵĺ���
		this.initHorizontalLines();
		// ��������ϵ�����
		this.initVerticalLines();
	}

	/**
	 * ��������ӵ�Panel��
	 */
	private void initRect () {
		for (int i = 0; i < RECT_NUMBER; i++) {
			for (int j = 0; j < RECT_NUMBER; j++) {
				Rect rect = new Rect(RECT_X + j * (RECT_INTERVAL + RECT_SIZE), RECT_Y + i * (RECT_INTERVAL + RECT_SIZE), RECT_SIZE);
				rect.setSerialNumber(Integer.toString(i) + Integer.toString(j));
				// ��������뵽���߼�����
				this.rectList.put(Integer.toString(i) + Integer.toString(j), rect);
				// ��������뵽Panel��
				this.add(rect);
			}
		}
	}

	/**
	 * �����������ӵ�Panel��
	 */
	private void initVerticalLines () {
		for (int i = 0; i < VERTICAL_LINES_NUMBER; i++) {
			for (int j = 0; j < DOTS_NUMBER; j++) {
				Line line = new Line(VERTICAL_LINES_X + j * (DOTS_INTERVAL + IMG_DOTS_SIZE), VERTICAL_LINES_Y + i * (VERTICAL_LINES_INTERVAL + VERTICAL_LINES_HEIGHT), VERTICAL_LINES_WIDTH,
						VERTICAL_LINES_HEIGHT, 1, gameControl);
				// �ߵı��
				String serialNumber = 1 + Integer.toString(i) + Integer.toString(j);
				line.setSerialNumber(serialNumber);
				// �����߼��뵽���߼�����
				this.linesList.put(serialNumber, line);
				line.addMouseListener(this);
				// �����߼��뵽Panel��
				this.add(line);
				Hashtable<String, Rect> rectList = dataGame.getRectList();
				// ����Hashtable��key��value
				Iterator<String> it = rectList.keySet().iterator();
				// ���߼��뵽��Ӧ�ķ�����
				while (it.hasNext()) {
					String key = it.next();
					Rect rect = rectList.get(key);
					Vector<Line> lines = rect.getLines();
					char[] charKey = key.toCharArray();
					int[] intKey = { Integer.parseInt(String.valueOf(charKey[0])), Integer.parseInt(String.valueOf(charKey[1])) };
					if (i == intKey[0] && j == intKey[1]) {
						lines.add(line);
					}
					if (i == intKey[0] && j == (intKey[1] + 1)) {
						lines.add(line);
					}
				}
			}
		}

	}

	/**
	 * �����������ӵ�Panel��
	 */
	private void initHorizontalLines () {
		for (int i = 0; i < DOTS_NUMBER; i++) {
			for (int j = 0; j < HORIZONTAL_LINES_NUMBER; j++) {
				Line line = new Line(HORIZONTAL_LINES_X + j * (HORIZONTAL_LINES_INTERVAL + HORIZONTAL_LINES_WIDTH), HORIZONTAL_LINES_Y + i * (DOTS_INTERVAL + IMG_DOTS_SIZE), HORIZONTAL_LINES_WIDTH,
						HORIZONTAL_LINES_HEIGHT, 0, gameControl);
				// �ߵı��
				String serialNumber = 0 + Integer.toString(i) + Integer.toString(j);
				line.setSerialNumber(serialNumber);
				// �����߼��뵽���߼�����
				this.linesList.put(serialNumber, line);
				line.addMouseListener(this);
				// �����߼��뵽Panel��
				this.add(line);
				Hashtable<String, Rect> rectList = dataGame.getRectList();
				// ����Hashtable��key��value
				Iterator<String> it = rectList.keySet().iterator();
				// ���߼��뵽��Ӧ�ķ�����
				while (it.hasNext()) {
					String key = it.next();
					Rect rect = rectList.get(key);
					Vector<Line> lines = rect.getLines();
					char[] charKey = key.toCharArray();
					int[] intKey = { Integer.parseInt(String.valueOf(charKey[0])), Integer.parseInt(String.valueOf(charKey[1])), };
					if (i == intKey[0] && j == intKey[1]) {
						lines.add(line);
					}
					if (i == (intKey[0] + 1) && j == intKey[1]) {
						lines.add(line);
					}
				}
			}
		}
	}

	/**
	 * ������ӵ�Panel��
	 * @author cylong
	 * @version May 22, 2014  9:26:52 PM
	 */
	private void initDots () {
		for (int i = 0; i < dots.length; i++) {
			for (int j = 0; j < dots[i].length; j++) {
				dots[i][j] = new Dot(DOTS_X + i * (DOTS_INTERVAL + IMG_DOTS_SIZE), DOTS_Y + j * (DOTS_INTERVAL + IMG_DOTS_SIZE), IMG_DOTS_SIZE);
				// ������뵽panel��
				this.add(dots[i][j]);
			}
		}
	}

	/**
	 * @author cylong
	 * @version May 19, 2014  2:24:38 AM
	 */
	@Override
	public void paint(Graphics g) {
		// �����̱���
		g.drawImage(AllImages.IMG_CHESS_BOARD_BG, 0, 0, this);
		super.paint(g);
	}
	@Override
	public void mouseClicked (MouseEvent e) {
		// �����������ʾ����
		this.gameControl.getServiceGame().showLine(e.getSource());
		this.repaint();
	}

	@Override
	public void mouseEntered (MouseEvent e) {
		// ����ƶ�����ǩ�Ͻ�����ʾ����
		this.gameControl.getServiceGame().mouseEnteredLine(e.getSource());
		this.repaint();
	}

	@Override
	public void mouseExited (MouseEvent e) {
		// ����뿪��ǩ�Ͻ�������
		this.gameControl.getServiceGame().mouseExitedLine(e.getSource());
		this.repaint();
	}

	@Override
	public void mousePressed (MouseEvent e) {
	}

	@Override
	public void mouseReleased (MouseEvent e) {
	}

}
