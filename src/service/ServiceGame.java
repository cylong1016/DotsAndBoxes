package service;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import server.GameClient;
import server.GameServer;
import ui.label.Line;
import ui.label.Rect;
import util.GameUtil;
import data.DataConfig;
import data.DataGame;

/**
 * ��Ϸ��ҵ���߼�
 * 
 * @author ������
 * @since 2014 / 3 / 20 6 : 37 PM
 */
public class ServiceGame implements Runnable {

	/**
	 * ��Ϸ���ݿ�
	 */
	private DataGame dataGame = null;
	/**
	 * �жϵ��Ե�ǰ�����Ƿ����
	 */
	private boolean isHandling = false;
	/**
	 * ��������
	 */
	private GameServer gameServer = null;

	/**
	 * �ͻ���
	 */
	private GameClient gameClient = null;
	/**
	 * ҵ���߼��߳�
	 */
	private Thread service = null;
	/**
	 * ����ʱ�߳�
	 */
	private Countdown countdown = null;

	/**
	 * ��ʼ���������
	 */
	public void initPlayerData () {
		this.dataGame.getPlayer1().setClick(false);
		this.dataGame.getPlayer2().setClick(false);
		this.dataGame.getPlayer1().setFormRect(false);
		this.dataGame.getPlayer2().setFormRect(false);
	}

	public ServiceGame (final DataGame dataGame) {
		if (service == null) {
			// �����߳�
			// ��Ҫ��Ϊ��ʹ���Կ����ӳ�����
			service = new Thread(this);
			service.start();
		}
		this.dataGame = dataGame;
	}

	/**
	 * �������һ���ߵ�ʱ����ʾ����
	 * 
	 * @param o
	 *            �������Դ
	 */
	public void showLine (Object o) {
		if (DataConfig.isPause()) {
			return;
		}
		if (countdown == null) {
			// ����ʱ�߳�
			countdown = new Countdown();
			countdown.start();

		}
		if (dataGame.getGameMode() == 0) {
			// ������Ϸ
			this.startSingleGame(o);
		} else if (dataGame.getGameMode() == 1) {
			// ˫����Ϸ
			this.startTwoGame(o);
		} else if (dataGame.getGameMode() == 2) {
			// ������ս
			this.startConnectGame(o);
		}
		// �ж���Ϸ�Ƿ����
		this.dataGame.setWinner(this.isGameover());
	}

	/**
	 * ������Ϸ
	 */
	private void startSingleGame (Object o) {
		// ����������ڴ�������Ҳ��ܲ���
		if (isHandling) {
			return;
		}
		this.initPlayerData();
		// ���1��
		this.dataGame.getPlayer1().setClick(this.playerShowLine(1, o));
		// �ж��Ƿ�����γɷ���,���γɷ���ͼӷ�
		this.dataGame.getPlayer1().setFormRect(this.formRect(1));
	}

	/**
	 * ˫����Ϸ
	 */
	private void startTwoGame (Object o) {
		if ((dataGame.getPlayer2().isClick() && !dataGame.getPlayer2().isFormRect()) || dataGame.getPlayer1().isFormRect()) {
			// ���1��
			this.initPlayerData();
			this.dataGame.getPlayer1().setClick(this.playerShowLine(1, o));
			// �ж��Ƿ�����γɷ���,���γɷ���
			this.dataGame.getPlayer1().setFormRect(this.formRect(1));
		} else if ((dataGame.getPlayer1().isClick() && !dataGame.getPlayer1().isFormRect()) || dataGame.getPlayer2().isFormRect()) {
			// ���2��
			this.initPlayerData();
			this.dataGame.getPlayer2().setClick(this.playerShowLine(2, o));
			// �ж��Ƿ�����γɷ���,���γɷ���
			this.dataGame.getPlayer2().setFormRect(this.formRect(2));
		}

	}

	/**
	 * ������ս
	 * 
	 * @since 2014 / 5 / 11 3:23 AM
	 */
	private void startConnectGame (Object o) {
		if (gameServer != null) {
			if ((dataGame.getPlayer2().isClick() && !dataGame.getPlayer2().isFormRect()) || dataGame.getPlayer1().isFormRect()) {
				// ���1��
				this.initPlayerData();
				this.dataGame.getPlayer1().setClick(this.playerShowLine(1, o));
				// �ж��Ƿ�����γɷ���,���γɷ���
				this.dataGame.getPlayer1().setFormRect(this.formRect(1));
				this.gameServer.setDataGame(dataGame);
			}
		}
		if (gameClient != null) {
			if ((dataGame.getPlayer1().isClick() && !dataGame.getPlayer1().isFormRect()) || dataGame.getPlayer2().isFormRect()) {
				// ���2��
				this.initPlayerData();
				this.dataGame.getPlayer2().setClick(this.playerShowLine(2, o));
				// �ж��Ƿ�����γɷ���,���γɷ���
				this.dataGame.getPlayer2().setFormRect(this.formRect(2));
				this.gameClient.setDataGame(dataGame);
			}
		}
	}

	/**
	 * �����, ���жϸ�����Ƿ��γɷ���,<br />
	 * ����һ��������������ֵ�����������
	 * 
	 * @param playern
	 *            1Ϊ���һ����Ϊ��Ҷ�
	 * @param o
	 *            ����õĶ���
	 * @return ����Ƿ����ɹ�
	 */
	private boolean playerShowLine (int playern, Object o) {
		// �ж��Ƿ����ɹ�
		boolean b = false;
		// �������ߵļ���
		Hashtable<String, Line> LinesList = this.dataGame.getLinesList();
		// ����Hashtable��key��value
		Iterator<String> it = LinesList.keySet().iterator();
		// hasNext()����һ������ֵ
		while (it.hasNext()) {
			// ȡ��key
			String key = (String) it.next();
			// ͨ��keyȡ��value
			Line tempLine = LinesList.get(key);
			if (tempLine == o && !tempLine.isClicked()) {
				// ����ɹ�
				b = true;
				// �趨Ϊ�ѵ��,������˭�����
				tempLine.setLineColor(playern);
				GameUtil.startClickSound();
				// ����һ��
				this.dataGame.setStep(dataGame.getStep() + 1);
				// ��ֵ
				tempLine.setStep(dataGame.getStep());
				dataGame.setCountdown(90);
			}
		}
		return b;
	}

	/**
	 * ������
	 */
	private void computerShowLine () {
		// �������ߵļ���
		Hashtable<String, Line> LinesList = this.dataGame.getLinesList();
		Line[] lines = GameUtil.mapToArr(LinesList);
		boolean isLastLine = true;
		for (int i = 0; i < lines.length; i++) {
			if (!lines[i].isClicked()) {
				isLastLine = false;
			}
		}
		if (!isLastLine) {
			Line line = AlterEgo.play(lines, dataGame.getLevel());
			line.setStep(dataGame.getStep());
			// ������1
			this.dataGame.setStep(dataGame.getStep() + 1);
		}
	}

	/**
	 * �ж��Ƿ��γɷ��飬���ӷ�
	 * 
	 * @param color
	 *            �γɷ������ɫ
	 * @return boolean �Ƿ��γɷ���
	 */
	private boolean formRect (int color) {
		// �������ߵļ���
		Hashtable<String, Line> LinesList = this.dataGame.getLinesList();
		// ��ֹ��ǰ�߲����ڳ���bug
		if (this.dataGame.getJustShow() == null) {
			return false;
		}
		// �õ���ǰ�������
		Line curLine = LinesList.get(this.dataGame.getJustShow());
		// �ж��Ƿ��γɷ���
		boolean b = false;
		Hashtable<String, Rect> rectList = this.dataGame.getRectList();
		Iterator<String> it = rectList.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Rect rect = rectList.get(key);
			Vector<Line> lines = rect.getLines();
			// �ж�ĳһ��������������Ƿ񶼱����
			boolean b1 = true;
			for (Line line : lines) {
				if (!line.isClicked()) {
					b1 = false;
				}
			}
			if (!rect.isShow() && b1) {
				b = b1;
				rect.setInsideColor(color);
				curLine.setFormatRect(rect.getSerialNumber());
				// �ӷ�
				if (color == 1) {
					this.dataGame.getPlayer1().setScore(this.dataGame.getPlayer1().getScore() + rect.getScore());
				} else if (color == 2) {
					this.dataGame.getPlayer2().setScore(this.dataGame.getPlayer2().getScore() + rect.getScore());
				}
			}
		}
		return b;
	}

	/**
	 * �������
	 * 
	 * @since 2014 / 4 / 9
	 */
	public void takeBack () {
		// ��ֹ��Ϸ�տ�ʼʱ��û�и��µ���
		if (this.dataGame.getJustShow() == null) {
			return;
		}
		// �������ߵļ���
		Hashtable<String, Line> LinesList = this.dataGame.getLinesList();
		// �������Զ�ս
		if (dataGame.getGameMode() == 0) {
			// ��������Ż����
			if (isHandling) {
				return;
			}
			// ��������µ���
			while (LinesList.get(this.dataGame.getJustShow()).getLineColor() == 2) {
				this.takeBackOnce(LinesList);
			}
			// �������µ���
			this.takeBackOnce(LinesList);
		} else if (dataGame.getGameMode() == 1) {
			this.initPlayerData();
			// ����˫�˶�ս
			// �����ȡ����ǰ��ҵ�һ��
			this.takeBackOnce(LinesList);
			// �ж�ȡ����������˭��
			Line line = LinesList.get(this.dataGame.getJustShow());
			if (line.getFormatRect() == null) {
				if (line.getLineColor() == 1) {
					this.dataGame.getPlayer1().setClick(true);
				} else {
					this.dataGame.getPlayer2().setClick(true);
				}
			} else {
				if (line.getLineColor() == 1) {
					this.dataGame.getPlayer1().setFormRect(true);
				} else {
					this.dataGame.getPlayer2().setFormRect(true);
				}
			}
		}
	}

	/**
	 * ����һ��,���ж��Ƿ�ȡ���γɵķ���Ȼ�����
	 * 
	 * @since 2014 / 4 / 9
	 */
	private void takeBackOnce (Hashtable<String, Line> LinesList) {
		// ����һ��
		this.dataGame.setStep(this.dataGame.getStep() - 1);
		// �õ���ǰ����
		Line curLine = LinesList.get(this.dataGame.getJustShow());
		// ��ֹ��һ����û��ǰһ���ߵĿ�ָ���쳣
		if (curLine.getPreShow() == null) {
			// ȡ������
			curLine.initLine();
			// ����JustShow��Ϊ��
			this.dataGame.setJustShow(null);
			return;
		}
		String preKey = curLine.getPreShow();
		// ����ǰ�����������Ϊ�ո�ȡ�����ߵ�ǰһ����
		this.dataGame.setJustShow(preKey);
		// �ж��Ƿ�ȡ���Ѿ���ʾ�ķ���
		Hashtable<String, Rect> rectList = this.dataGame.getRectList();
		Iterator<String> it = rectList.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Rect rect = rectList.get(key);
			// �����Ѿ�����ʾ���ж��Ƿ�ȡ����ʾ
			if (rect.getInsideColor() != 0) {
				// �õ��뷽��ƥ��������߼���
				Vector<Line> lines = rect.getLines();
				for (int i = 0; i < lines.size(); i++) {
					Line line = lines.get(i);
					// == �Ƚϵ��������Ƿ����
					if (line == curLine) {
						// ��Ӧ��Ҽ�һ��
						if (rect.getInsideColor() == 1) {
							this.dataGame.getPlayer1().setScore(this.dataGame.getPlayer1().getScore() - 1);
						} else {
							this.dataGame.getPlayer2().setScore(this.dataGame.getPlayer2().getScore() - 1);
						}
						// ȡ����ʾ�ķ���
						rect.initRect();
					}
				}
			}
		}
		// ȡ������
		curLine.initLine();
	}

	/**
	 * �ж���Ϸ�Ƿ����
	 * 
	 * @since 2014 / 4 / 13 12:57 PM
	 * @return 0��δ���� 1�����һʤ�� 2����Ҷ�ʤ��
	 */
	private int isGameover () {
		// �������ߵļ���
		Hashtable<String, Line> LinesList = this.dataGame.getLinesList();
		Iterator<String> it = LinesList.keySet().iterator();
		// hasNext()����һ������ֵ
		while (it.hasNext()) {
			// ȡ��key
			String key = it.next();
			// ͨ��keyȡ��value
			Line tempLine = LinesList.get(key);
			if (!tempLine.isClicked()) {
				return 0;
			}
		}
		if (dataGame.getPlayer1().getScore() > dataGame.getPlayer2().getScore()) {
			return 1;
		} else {
			return 2;
		}
	}

	/**
	 * ����ƶ���ĳһ�е�ʱ����ʾ��һ����
	 * 
	 * @param o
	 *            ������Ķ���ʵ��
	 */
	public void mouseEnteredLine (Object o) {
		// ����������ڴ�������Ҳ��ܲ���
		if (isHandling) {
			return;
		}
		// �������ߵļ���
		Hashtable<String, Line> LinesList = this.dataGame.getLinesList();
		// ����Hashtable��key��value
		// Iterator����
		Iterator<String> it = LinesList.keySet().iterator();
		// hasNext()����һ������ֵ
		while (it.hasNext()) {
			// ȡ��key
			String key = it.next();
			// ͨ��keyȡ��value
			Line tempLine = LinesList.get(key);
			if (!tempLine.isClicked() && tempLine == o) {
				tempLine.setMoved(true);
				break;
			}
		}
	}

	/**
	 * ����ƿ���������
	 * 
	 * @param o
	 *            ������Ķ���ʵ��
	 */
	public void mouseExitedLine (Object o) {
		// �������ߵļ���
		Hashtable<String, Line> LinesList = this.dataGame.getLinesList();
		// ����Hashtable��key��value
		// Iterator����
		Iterator<String> it = LinesList.keySet().iterator();
		// hasNext()����һ������ֵ
		while (it.hasNext()) {
			// ȡ��key
			String key = it.next();
			// ͨ��keyȡ��value
			Line tempLine = LinesList.get(key);
			tempLine.setMoved(false);
		}
	}

	/**
	 * �߳�<br />
	 * Ϊ���ӳٵ�������
	 */
	@Override
	public void run () {
		while (true) {
			GameUtil.sleep(5);
			if (dataGame.getGameMode() == 2) {
				if (gameServer != null) {
					this.setDataGame(gameServer.getDataGame());
				}
				if (gameClient != null) {
					this.setDataGame(gameClient.getDataGame());
				}
			}
			// �����˫����Ϸ����ִ���������
			if (dataGame.getGameMode() != 0) {
				continue;
			}
			this.isHandling = true;
			// ���1����ɹ���δ�γɷ��飬�������
			if (dataGame.getPlayer1().isClick() && !dataGame.getPlayer1().isFormRect()) {
				dataGame.setCountdown(90);
				this.dataGame.getPlayer1().setClick(false);
				// �ӳٵ�������ʱ��
				GameUtil.sleep(300);
				// �õ�����
				this.computerShowLine();
				// �ж��Ƿ��γɷ���,���γɷ���
				boolean isComputerFormRect = this.formRect(2);
				// �γɷ������Լ�����
				while (isComputerFormRect) {
					// �ж���Ϸ�Ƿ����
					this.dataGame.setWinner(this.isGameover());
					// ��Ϸ����������
					if (this.dataGame.getWinner() != 0) {
						break;
					}
					// �ӳٵ�������ʱ��
					GameUtil.sleep(300);
					dataGame.setCountdown(90);
					this.computerShowLine();
					isComputerFormRect = this.formRect(2);
				}
			}
			this.isHandling = false;
		}
	}

	public DataGame getDataGame () {
		return dataGame;
	}

	/**
	 * ���ڶ�ȡ���ݵģ�д���ҿ����ˣ�����~~<br />
	 * ������е����⣬��ú���������ģ�����������
	 * 
	 * @since 2014 / 4 / 15
	 * @param dataGame
	 */
	public void setDataGame (DataGame dataGame) {
		// // ��ʼ��û���������
		// dataGame.setDotsList(this.dataGame.getDotsList());
		// ��ʼ����
		DataGame dataGameTemp = this.getDataGame();
		Hashtable<String, Line> linesList = dataGame.getLinesList();
		Hashtable<String, Line> linesListTemp = dataGameTemp.getLinesList();
		Iterator<String> it = linesList.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Line line = linesList.get(key);
			Iterator<String> itTemp = linesListTemp.keySet().iterator();
			while (itTemp.hasNext()) {
				String keyTemp = itTemp.next();
				Line lineTemp = linesListTemp.get(keyTemp);
				if (line.getSerialNumber().equals(lineTemp.getSerialNumber())) {
					this.copyLine(line, lineTemp);
				}
			}
		}
		dataGame.setLinesList(linesListTemp);

		// ��ʼ������
		Hashtable<String, Rect> rectListTemp = dataGameTemp.getRectList();
		Hashtable<String, Rect> rectList = dataGame.getRectList();
		it = rectList.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Rect rect = rectList.get(key);
			Iterator<String> itTemp = rectListTemp.keySet().iterator();
			while (itTemp.hasNext()) {
				String keyTemp = itTemp.next();
				Rect rectTemp = rectListTemp.get(keyTemp);
				if (rect.getSerialNumber().equals(rectTemp.getSerialNumber())) {
					this.copyRect(rect, rectTemp);
					for (int i = 0; i < rect.getLines().size(); i++) {
						for (int j = 0; j < rectTemp.getLines().size(); j++) {
							if (rect.getLines().get(i).getSerialNumber().equals(rectTemp.getLines().get(j).getSerialNumber())) {
								Line line = rect.getLines().get(i);
								Line lineTemp = rectTemp.getLines().get(j);
								this.copyLine(line, lineTemp);
							}
						}
					}
				}
			}
		}
		dataGame.setRectList(this.dataGame.getRectList());
		this.dataGame = dataGame;
	}

	/**
	 * ������
	 * 
	 * @param line
	 *            ���б�����Ϣ����
	 * @param lineTemp
	 */
	private void copyLine (Line line, Line lineTemp) {
		lineTemp.setStep(line.getStep());
		lineTemp.setAfterShow(line.getAfterShow());
		lineTemp.setSerialNumber(line.getSerialNumber());
		lineTemp.setPreShow(line.getPreShow());
		lineTemp.setLineColor(line.getLineColor());
		lineTemp.setFormatRect(line.getFormatRect());
	}

	/**
	 * ���Ʒ���
	 * 
	 * @param rect
	 *            ���б�����Ϣ�ķ���
	 * @param rectTemp
	 */
	private void copyRect (Rect rect, Rect rectTemp) {
		rectTemp.setSerialNumber(rect.getSerialNumber());
		rectTemp.setInsideColor(rect.getInsideColor());
	}

	public GameServer getGameServer () {
		return gameServer;
	}

	public void setGameServer (GameServer gameServer) {
		this.gameServer = gameServer;
	}

	public GameClient getGameClient () {
		return gameClient;
	}

	public void setGameClient (GameClient gameClient) {
		this.gameClient = gameClient;
	}

	private class Countdown extends Thread {

		@Override
		public void run () {
			while (true) {
				GameUtil.sleep(1000);
				if (DataConfig.isPause()) {
					continue;
				}
				dataGame.setCountdown(dataGame.getCountdown() - 1);
				if (dataGame.getCountdown() <= 0) {
					dataGame.setCountdown(90);
				}
			}
		}
	}

}
