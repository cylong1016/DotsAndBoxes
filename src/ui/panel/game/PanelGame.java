package ui.panel.game;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

import ui.AllImages;
import ui.label.Line;
import ui.label.PanelGameButton;
import ui.panel.AbstractPanel;
import ui.window.FrameGame;
import util.GameUtil;
import control.GameControl;
import data.DataGame;

/**
 * ��Ϸ�������panel<br />
 * �̳����� �����PanelUserDefined
 * 
 * @author ������
 * @since 2014 / 3 / 19 9 : 46 AM
 */
public class PanelGame extends AbstractPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	/**
	 * �˵����
	 */
	private PanelGameMenu panelGameMenu = null;
	/**
	 * �������
	 */
	private PanelBackground background = null;
	/**
	 * ��Ϸ����
	 */
	private ChessBoard chessBoard = null;
	/**
	 * �˵�����ƫ����
	 */
	private int menuOffset = -250;
	/**
	 * �˵����ĵ�ǰx����
	 */
	private int currentX = menuOffset;
	/**
	 * �˵��������x����
	 */
	private int maxX = 0;
	/**
	 * �˵�����ƶ����ٶ�
	 */
	private int expendSpeed = 25;
	/**
	 * �Ƿ���ʾ�˵�
	 */
	private boolean isShowMenu = false;
	/**
	 * �ߵļ���
	 */
	private Hashtable<String, Line> LineList = null;
	/**
	 * ���尴ť
	 */
	private PanelGameButton takeBack = null;
	/**
	 * �������ذ�ť
	 */
	private PanelGameButton volume = null;
	/**
	 * ��ͣ��ť
	 */
	private PanelGameButton pause = null;
	/**
	 * ��ť��С
	 */
	private int buttonSize = 54;
	/**
	 * �Ƿ񷵻����˵�
	 */
	private boolean isBack = false;
	/**
	 * ��Ϸ�Ƿ����
	 */
	private boolean isGameOver = false;
	
	/**
	 * @version May 24, 2014  7:02:57 PM
	 * @return the isGameOver
	 */
	public boolean isGameOver () {
		return this.isGameOver;
	}

	
	/**
	 * @version May 24, 2014  7:02:57 PM
	 * @param isGameOver the isGameOver to set
	 */
	public void setGameOver (boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	/**
	 * @param isBack
	 *            the isBack to set
	 */
	public void setBack (boolean isBack) {
		this.isBack = isBack;
	}

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
	public PanelGame (FrameGame frameGame, GameControl gameControl, DataGame dataGame) {
		super(frameGame, gameControl, dataGame);
		// ��ʼ�����
		super.initPanel();
		// �˵����
		this.panelGameMenu = new PanelGameMenu(currentX, 0, width, height, gameControl, dataGame);
		this.add(this.panelGameMenu);
		// ������ս�����Ի������ͣ
		if (dataGame.getGameMode() != 2) {
			// ���尴ť
			this.takeBack = new PanelGameButton(160, 212, buttonSize, buttonSize, AllImages.IMG_TAKE_BACK, AllImages.IMG_TAKE_BACK, gameControl, "TakeBack", true);
			this.add(takeBack);
			this.volume = new PanelGameButton(155, 278, buttonSize, buttonSize, AllImages.IMG_VOLUME_ON, AllImages.IMG_VOLUME_OFF, gameControl, "SetMute", true);
			this.add(volume);
			this.pause = new PanelGameButton(155, 337, buttonSize, buttonSize, AllImages.IMG_PAUSE, AllImages.IMG_START, gameControl, "SetPause", true);
			this.add(pause);
		}
		// ��������, ����ҿ�����װ��������
		this.chessBoard = new ChessBoard(this.frameGame, gameControl);
		this.add(chessBoard);
		this.LineList = this.dataGame.getLinesList();
		// �������
		this.background = new PanelBackground(width, height);
		this.add(background);
		// this.requestFocus();
		// ��������߳�
		new Thread(this).start();
	}

	/**
	 * Ϊ��ʹ������˸Ч��
	 */
	private int showLine = 0;

	@Override
	public void paint (Graphics g) {
		// ��ʼ�����
		this.initPanel(g);
		// ���߿�ͼƬ
		g.drawImage(AllImages.IMG_PANEL_GAME_BORDER, 0, 0, this);
		if (this.dataGame.getGameMode() != 2) {
			// �øյ����������˸Ч��
			if (this.dataGame.getJustShow() != null) {
				Line lineJustShow = this.LineList.get(this.dataGame.getJustShow());
				if (this.showLine % 50 == 0) {
					lineJustShow.setVisible(true);
				} else if (this.showLine % 25 == 0) {
					lineJustShow.setVisible(false);
				}
				if (lineJustShow.getPreShow() != null) {
					Line LinePrevShow = this.LineList.get(lineJustShow.getPreShow());
					if (!LinePrevShow.isVisible()) {
						LinePrevShow.setVisible(true);
					}
				}
			}
		}
		if (currentX == menuOffset) {
			// �������1����
			super.drawNumber(130, 177, this.dataGame.getPlayer1().getScore(), 2, AllImages.IMG_NUM_SCORE,AllImages.IMG_NUM_SCORE_W, AllImages.IMG_NUM_SCORE_H, g);
			// �������1
			g.drawImage(AllImages.IMG_PANEL_GAME_PLAYER1, 62, 150, this);
			// �������2����
			this.drawNumber(130, 422, this.dataGame.getPlayer2().getScore(), 2, AllImages.IMG_NUM_SCORE,AllImages.IMG_NUM_SCORE_W, AllImages.IMG_NUM_SCORE_H, g);
			// ������ͼƬ
			g.drawImage(AllImages.IMG_PANEL_GAME_SCORE, 60, 178, this);
			// �������2
			g.drawImage(AllImages.IMG_PANEL_GAME_PLAYER2, 62, 395, this);
			// ������ͼƬ
			g.drawImage(AllImages.IMG_PANEL_GAME_SCORE, 60, 423, this);
			if(dataGame.getGameMode() != 2) {
				// ��������ʱ
				this.drawNumber(50, 280, dataGame.getCountdown(), 2, AllImages.IMG_NUM_COUNTDOWN, AllImages.IMG_NUM_COUNTDOWN_W, AllImages.IMG_NUM_COUNTDOWN_H, g);
			}
		}
		if (isShowMenu || isGameOver) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, hyaline));
			g2.drawImage(AllImages.IMG_MENU_BG, 0, 0, this);
		}
	}

	@Override
	public void mouseMoved (MouseEvent arg0) {
		super.mouseMoved(arg0);
		if (arg0.getX() - 10 < this.maxX) {
			this.isShowMenu = true;
		} else if (isShowMenu && arg0.getX() > 250) {
			this.isShowMenu = false;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 * 
	 * @author cylong
	 * @version May 17, 2014 11:02:31 PM
	 */
	@Override
	public void run () {
		while (true) {
			// �������˵���ʱ����˳��߳�
			if (this.isBack) {
				break;
			}
			GameUtil.sleep(20);
			this.repaint();
			// ʹ���µ�������
			this.showLine++;
			if (this.showLine > 10000) {
				this.showLine = 0;
			}
			super.isClick();
			if ((this.isShowMenu && this.currentX <= this.maxX) || isGameOver) {
				this.currentX += this.expendSpeed;
				if (currentX > 0) {
					currentX = 0;
				}
			} else if ((!this.isShowMenu && this.currentX > menuOffset) || !isGameOver) {
				this.currentX -= this.expendSpeed;
				if (currentX < menuOffset) {
					currentX = menuOffset;
				}
			}
			this.panelGameMenu.setLocation(currentX, 0);
			if (isShowMenu || isGameOver) {
				hyaline += 0.1f;
				if (hyaline > 1) {
					hyaline = 1;
				}
			} else {
				hyaline -= 0.04f;
				if (hyaline < 0) {
					hyaline = 0f;
				}
			}
		}
	}

}
