package data;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Iterator;

import ui.label.Line;
import ui.label.Rect;

/**
 * ��Ϸ���һЩ����
 * 
 * @author ������
 * @since 2014 / 3 / 20 5 : 13 PM
 */
public class DataGame implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * ��Ϸģʽ<br />
	 * 0: ���� 1:˫�� 2������
	 */
	private int gameMode = 0;
	/**
	 * <p>
	 * �Ⱥ���ѡ��
	 * </p>
	 * <li>0������</li> <li>1������</li>
	 */
	private int firstSecond;

	/**
	 * ��Ϸ�Ѷ�
	 */
	private int level = 2;

	/**
	 * ���̴�С��X * X��
	 */
	private int chessBoardSize = DataConfig.getChessBoardSize();
	/** ����ļ��� */
	private Hashtable<String, Rect> rectList = new Hashtable<String, Rect>();
	/**
	 * ���ߵļ���
	 */
	private Hashtable<String, Line> linesList = new Hashtable<String, Line>();
	/**
	 * ����������
	 */
	private int step = 0;
	/**
	 * �ձ�������ߵı��
	 */
	private String justShow = null;
	/**
	 * ���1
	 */
	private Player player1 = new Player();
	/**
	 * ���2
	 */
	private Player player2 = new Player();
	/**
	 * Ӯ��
	 */
	private int winner = 0;
	/**
	 * ��ʱ��
	 */
	private int countdown = 90;

	/**
	 * ��ʼ����Ϸ����
	 */
	public void initGame () {
		this.countdown = 90;
		this.player2.setClick(true); // Ϊ��ʹ���һ����
		this.player1.setScore(0);
		this.player2.setScore(0);
		this.step = 0;
		this.justShow = null;
		Iterator<String> it = this.linesList.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			this.linesList.get(key).initLine();
		}
		it = this.rectList.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			this.rectList.get(key).initRect();
		}
	}

	/**
	 * @version May 24, 2014 3:07:47 AM
	 * @return the countdown
	 */
	public int getCountdown () {
		return this.countdown;
	}

	/**
	 * @version May 24, 2014 3:07:47 AM
	 * @param countdown
	 *            the countdown to set
	 */
	public void setCountdown (int countdown) {
		this.countdown = countdown;
	}

	public Hashtable<String, Line> getLinesList () {
		return linesList;
	}

	public void setLinesList (Hashtable<String, Line> linesList) {
		this.linesList = linesList;
	}

	public Hashtable<String, Rect> getRectList () {
		return rectList;
	}

	public void setRectList (Hashtable<String, Rect> rectList) {
		this.rectList = rectList;
	}

	public int getStep () {
		return step;
	}

	public void setStep (int step) {
		// ����С���㰡~
		if (step >= 0) {
			this.step = step;
		}
	}

	public String getJustShow () {
		return justShow;
	}

	public void setJustShow (String justShow) {
		this.justShow = justShow;
	}

	public int getGameMode () {
		return gameMode;
	}

	public void setGameMode (int gameMode) {
		this.gameMode = gameMode;
	}

	public int getChessBoardSize () {
		return chessBoardSize;
	}

	public void setChessBoardSize (int chessBoardSize) {
		// ͬʱ�޸�
		DataConfig.setChessBoardSize(chessBoardSize);
		this.chessBoardSize = chessBoardSize;
	}

	public int getWinner () {
		return winner;
	}

	public void setWinner (int winner) {
		this.winner = winner;
	}

	public Player getPlayer1 () {
		return player1;
	}

	public void setPlayer1 (Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2 () {
		return player2;
	}

	public void setPlayer2 (Player player2) {
		this.player2 = player2;
	}

	/**
	 * @version May 19, 2014 4:26:10 AM
	 * @return the firstSecond
	 */
	public int getFirstSecond () {
		return this.firstSecond;
	}

	/**
	 * @version May 19, 2014 4:26:10 AM
	 * @param firstSecond
	 *            the firstSecond to set
	 */
	public void setFirstSecond (int firstSecond) {
		this.firstSecond = firstSecond;
	}

	/**
	 * @version May 19, 2014 10:24:04 PM
	 * @return the level
	 */
	public int getLevel () {
		return this.level;
	}

	/**
	 * @version May 19, 2014 10:24:04 PM
	 * @param level
	 *            the level to set
	 */
	public void setLevel (int level) {
		this.level = level;
	}

}
