package ui.panel.start;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import sound.PlayWave;
import ui.AllImages;
import ui.label.LabelButton;
import ui.label.LabelFirstSecond;
import ui.label.LabelLevelChoose;
import ui.label.LabelSizeChoose;
import ui.panel.AbstractPanel;
import ui.window.FrameGame;
import util.GameUtil;
import control.GameControl;
import data.DataConfig;
import data.DataGame;

/**
 * ��ʼ��Ϸ���ѡ��
 * 
 * @author ������
 * @since 2014 / 4 / 7 4:27 AM
 */
public class PanelModeChoose extends AbstractPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * ������Ϸ��ť
	 */
	private LabelButton singlePlayer;
	/**
	 * ˫����Ϸ��ť
	 */
	private LabelButton twoPlayers;
	/**
	 * ������ս
	 */
	private LabelButton towPlayersConnect;
	/**
	 * ����ģʽ
	 */
	private LabelButton specialMode;
	/**
	 * ���̴�Сѡ��İ�ť����
	 */
	private LabelSizeChoose[] sizeChoose;
	/**
	 * �Ⱥ���ѡ��İ�ť����
	 */
	private LabelFirstSecond[] firstSecondChoose;
	/**
	 * �Ѷ�ѡ�������
	 */
	private LabelLevelChoose[] levelChoose;
	/**
	 * ��ť�����Ͻ�x����
	 */
	private int x = 510;
	/**
	 * ��ť���Ͻ�y����
	 */
	private int y = 120;
	/**
	 * ��ť�Ŀ�
	 */
	private int labelWidth = 223;
	/**
	 * ��ť�ĸ�
	 */
	private int labelHeight = 46;
	/**
	 * ������ť֮��ļ�϶
	 */
	private int interval = 49;

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
	public PanelModeChoose (FrameGame frameGame, GameControl gameControl, DataGame dataGame) {
		super(frameGame, gameControl, dataGame);
		this.width = 800;
		this.height = 0;
		singlePlayer = new LabelButton(x, y, labelWidth, labelHeight, AllImages.IMG_SINGLE_PLAYER, gameControl, "SinglePlayer", true);
		this.add(singlePlayer);
		twoPlayers = new LabelButton(x, y + labelHeight + interval, labelWidth, labelHeight, AllImages.IMG_TWO_PLAYERS, gameControl, "TwoPlayers", true);
		this.add(twoPlayers);
		towPlayersConnect = new LabelButton(x, y + (labelHeight + interval << 1), labelWidth, labelHeight, AllImages.IMG_TWO_PLAYERS_CONNECT, gameControl, "TwoPlayersConnect", true);
		this.add(towPlayersConnect);
		specialMode = new LabelButton(x, y + (labelHeight + interval) * 3, labelWidth, labelHeight, AllImages.IMG_SPECIAL_MODE, gameControl, "SpcialMode", true);
		this.add(specialMode);
		// ��ʼ�����
		this.initPanel();
		this.setLocation((FrameGame.WIDTH - width) >> 1, (FrameGame.HEIGHT - maxHeight) >> 1);
		this.initLabelSizeChoose();
		this.initFirstSecondChoose();
		this.initLevelChoose();
		new Thread(this).start();
	}

	/**
	 * ��ʼ���Ѷ�ѡ��ť
	 * 
	 * @author cylong
	 * @version May 19, 2014 10:28:47 PM
	 */
	private void initLevelChoose () {
		levelChoose = new LabelLevelChoose[AllImages.IMG_LEVEL_CHOOSE.length];
		for (int i = 0; i < levelChoose.length; i++) {
			levelChoose[i] = new LabelLevelChoose(152 + i * 81, 283, 62, 35, AllImages.IMG_LEVEL_CHOOSE[i], AllImages.IMG_SETTING_BG, dataGame, i + 1);
			this.add(levelChoose[i]);
		}
	}

	/**
	 * �Ⱥ���ѡ��İ�ť����
	 * 
	 * @author cylong
	 * @version May 19, 2014 4:32:08 AM
	 */
	private void initFirstSecondChoose () {
		firstSecondChoose = new LabelFirstSecond[AllImages.IMG_FIRST_SECOND.length];
		for (int i = 0; i < firstSecondChoose.length; i++) {
			firstSecondChoose[i] = new LabelFirstSecond(193 + i * 81, 350, 62, 35, AllImages.IMG_FIRST_SECOND[i], AllImages.IMG_SETTING_BG, dataGame, i);
			this.add(firstSecondChoose[i]);
		}
	}

	/**
	 * ��ʼ�����̴�Сѡ��İ�ť����
	 * 
	 * @author cylong
	 * @version May 18, 2014 12:26:29 AM
	 */
	private void initLabelSizeChoose () {
		sizeChoose = new LabelSizeChoose[AllImages.IMG_CHESS_BOARD_SIZE.length];
		for (int i = 0; i < sizeChoose.length; i++) {
			sizeChoose[i] = new LabelSizeChoose(112 + i * 81, 216, 62, 35, AllImages.IMG_CHESS_BOARD_SIZE[i], AllImages.IMG_SETTING_BG, dataGame, i + 6);
			this.add(sizeChoose[i]);
		}
	}

	/** ͼƬ���Ͻ�y���� */
	private int ImgY = height;
	/** ����͸���� */
	private float hyaline;

	@Override
	public void paint (Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, hyaline));
		g2.drawImage(AllImages.IMG_MODEL_CHOOSE, 0, 0, this);
		super.paint(g);
		// ��ʼ�����
		this.initPanel(g);
	}

	/**
	 * ��ʼ��Panel����ͼ��
	 * 
	 * @param g
	 *            һֻ����
	 */
	protected void initPanel (Graphics g) {
		// �����رհ�ť
		g.drawImage(AllImages.WINDOW_EXIT, exitX, exitY, null);
		if (isMoveExit) {
			g.drawImage(AllImages.WINDOW_EXIT1, exitX, exitY, null);
		}
	}

	private boolean isExit = false;

	@Override
	public void mouseClicked (MouseEvent arg0) {
		// �ж�����Ƿ����رհ�ť
		if (arg0.getX() > exitX && arg0.getX() < exitX + WINDOW_EXIT_W && arg0.getY() > exitY && arg0.getY() < exitY + WINDOW_EXIT_H) {
			// this.gameControl.restartMenu();
			this.isExit = true;
		}
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
	}

	private int maxHeight = 600;
	private int expendSpeed = 30;

	@Override
	public void run () {
		while (true) {
			if (isExit && ImgY > 0) {
				this.ImgY -= expendSpeed;
			} else if (isExit) {
				this.gameControl.restartMenu();
				// ��ֹ����رհ�ť��ʱ������������
				break;
			}
			this.isClick();
			this.repaint();
			hyaline += 0.03f;
			if (hyaline > 1) {
				hyaline = 1;
			}
			if (!isExit && height < maxHeight) {
				this.height += expendSpeed;
			}
			this.setSize(width, height);
			GameUtil.sleep(20);
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
				new PlayWave(DataConfig.getSounds() + "���.wav").start();
				TIMES_EXIT++;
			}
		}
	}
}
