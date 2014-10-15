package control;

import java.util.Hashtable;
import java.util.Iterator;

import server.GameClient;
import server.GameServer;
import service.ServiceGame;
import sound.AllSounds;
import sound.PlayMP3;
import ui.label.LabelButton;
import ui.label.Rect;
import ui.panel.AbstractPanel;
import ui.panel.PanelAboutUs;
import ui.panel.PanelSpecialMode;
import ui.panel.game.PanelGame;
import ui.panel.start.PanelConnectHome;
import ui.panel.start.PanelModeChoose;
import ui.panel.start.PanelStart;
import ui.panel.start.PanelStartPre;
import ui.window.DialogGameOver;
import ui.window.FrameGame;
import util.GameUtil;
import data.DataConfig;
import data.DataDisk;
import data.DataTransmission;

/**
 * ��Ϸ�ܿ�����
 * 
 * @author ������
 * @since 2014/3/21 1 : 06 AM
 */
public class GameControl implements Runnable {

	/**
	 * ��Ϸ��ʼ�˵�����
	 */
	private AbstractPanel panelStart = null;
	/**
	 * ��Ϸ��ʼ��������
	 */
	private PanelStartPre panelStartPre = null;

	private PanelAboutUs panelAboutUs = null;

	public void setPanelStartPre (PanelStartPre panelStartPre) {
		this.panelStartPre = panelStartPre;
	}

	/**
	 * ����̵����ݴ���
	 */
	private DataTransmission dataDisk = null;
	/**
	 * ��Ϸ�����
	 */
	private FrameGame frameGame = null;
	/**
	 * ��Ϸ��ҵ���߼�
	 */
	private ServiceGame serviceGame = null;
	/**
	 * �����ʼ��Ϸ���ѡ�����
	 */
	private PanelModeChoose panelModeChoose = null;
	/**
	 * ���������ս���ѡ�����
	 */
	private PanelConnectHome panelConnectHome = null;
	/**
	 * ��Ϸ���
	 */
	private PanelGame panelGame = null;
	/**
	 * ����ģʽ���
	 */
	private PanelSpecialMode panelSpecialMode = null;

	/**
	 * ���췽��
	 * 
	 * @param frameGame
	 *            ��Ϸ�����
	 * @param serviceGame
	 *            ҵ���߼�
	 */
	public GameControl (ServiceGame serviceGame, FrameGame frameGame) {
		// ��ʼ����Ϸ�����
		this.frameGame = frameGame;
		// ��ʼ��ҵ���߼�
		this.serviceGame = serviceGame;
		// ��ʼ�����ݴ���
		this.dataDisk = new DataDisk();
		// �̣߳������ж���Ϸ�Ƿ����
		new Thread(this).start();
	}

	/**
	 * �������һ���ߵ�ʱ����ʾ����
	 * 
	 * @param o
	 *            ����õ���Դ
	 */
	public void showLine (Object o) {
		this.serviceGame.showLine(o);
	}

	/**
	 * ��ʼ�˵�
	 */
	public void startMenu () {
		// ���ű�������
		if (!DataConfig.isMute()) {
			AllSounds.START_BGM = new PlayMP3(DataConfig.getSounds() + "start_bgm.mp3");
			new Thread(AllSounds.START_BGM).start();
		}

		// ������Ϸ��ʼ�˵�����
		this.panelStart = new PanelStart(this.frameGame, this, this.serviceGame.getDataGame());
		this.frameGame.remove(this.panelStartPre);
		this.frameGame.setPanel(this.panelStart);
	}

	/**
	 * ����Ϸ
	 */
	public void startGame () {
		LabelButton.playMusic = false;
		// ������Ϸ���
		// ��װ��������������Ϸ���
		this.panelGame = new PanelGame(this.frameGame, this, this.serviceGame.getDataGame());
		// }
		this.frameGame.remove(this.panelModeChoose);
		this.frameGame.setPanel(this.panelGame);
	}

	/**
	 * ������ս
	 * 
	 * @since 2014 / 5 / 11 3:23 AM
	 */
	public void startConnectGame () {
		this.panelConnectHome = new PanelConnectHome(this);
		this.frameGame.remove(this.panelModeChoose);
		this.frameGame.setPanel(this.panelConnectHome);
		this.panelConnectHome.repaint();
	}

	/**
	 * ��������
	 */
	public void createHome () {
		GameServer gameServer = new GameServer();
		gameServer.startServer();
		this.serviceGame.setGameServer(gameServer);
		// ʹ���һ��ʼ��
		this.serviceGame.getDataGame().getPlayer2().setClick(true);
		this.startGame();
		this.frameGame.remove(this.panelConnectHome);
	}

	/**
	 * ����IP
	 */
	private String ip = null;

	/**
	 * @version May 26, 2014 4:29:29 AM
	 * @param ip
	 *            the ip to set
	 */
	public void setIp (String ip) {
		this.ip = ip;
	}

	/**
	 * ���뷿��
	 */
	public void joinHome () {
		GameClient gameClient = new GameClient(ip);
		gameClient.startConnect();
		this.serviceGame.setGameClient(gameClient);
		this.startGame();
		this.frameGame.remove(this.panelConnectHome);
	}

	/**
	 * ѡ������Ϸ��ѡ����Ϸģʽ���
	 */
	public void startChoose () {
		// ��ʼ����ʼ���ѡ�����
		this.panelModeChoose = new PanelModeChoose(this.frameGame, this, this.serviceGame.getDataGame());
		// this.panelProgressChoose.setVisible(false);
		this.frameGame.remove(this.panelStart);
		this.frameGame.setPanel(this.panelModeChoose);
	}

	/**
	 * �������˵�
	 */
	public void gotoMainMenu () {

		// �޸��߳�û�н�����bug
		this.panelGame.setBack(true);
		this.frameGame.remove(this.panelGame);
		this.panelGame = null;
		this.panelStart = new PanelStart(this.frameGame, this, this.serviceGame.getDataGame());
		this.frameGame.setPanel(this.panelStart);
		// ��ʼ����Ϸ����
		this.serviceGame.getDataGame().initGame();
	}

	/**
	 * ����
	 */
	public void takeBack () {
		this.serviceGame.takeBack();
	}

	/**
	 * ����ƶ���ĳһ�е�ʱ����ʾ��һ����
	 * 
	 * @param o
	 *            ������Ķ���ʵ��
	 */
	public void mouseEnteredLine (Object o) {
		this.serviceGame.mouseEnteredLine(o);
	}

	/**
	 * ����ƿ���������
	 * 
	 * @param o
	 *            ������Ķ���ʵ��
	 */
	public void mouseExitedLine (Object o) {
		this.serviceGame.mouseExitedLine(o);
	}

	public ServiceGame getServiceGame () {
		return this.serviceGame;
	}

	/**
	 * ���¿�ʼ��Ϸ
	 */
	public void restartGame () {
		this.serviceGame.getDataGame().initGame();
	}

	/**
	 * ��������
	 */
	public void saveChess () {
		this.dataDisk.saveData(this.serviceGame.getDataGame(), 0);
	}

	/**
	 * ��ȡ����
	 * 
	 * @since 2014 / 4 / 15 1:04 AM
	 */
	public void loadChess () {

		// ��ȡ����
		this.serviceGame.setDataGame(this.dataDisk.loadData(0));
	}

	@Override
	public void run () {
		while (true) {
			GameUtil.sleep(5);
			// �ж���Ϸ�Ƿ����
			if (this.serviceGame.getDataGame().getWinner() != 0) {
				this.panelGame.setGameOver(true);
				if (serviceGame.getDataGame().getWinner() == 1) {
					new DialogGameOver(serviceGame.getDataGame().getWinner(), serviceGame.getDataGame().getPlayer1().getScore(), panelGame);
				} else {
					new DialogGameOver(serviceGame.getDataGame().getWinner(), serviceGame.getDataGame().getPlayer2().getScore(), panelGame);
				}
				this.dataDisk.saveData(this.serviceGame.getDataGame(), 9);
				this.serviceGame.getDataGame().setWinner(0);
			}
		}
	}

	/**
	 * ��������ģʽ
	 */
	public void startSpcialMode () {
		this.panelSpecialMode = new PanelSpecialMode(this.frameGame, this, this.serviceGame.getDataGame());
		this.frameGame.remove(this.panelModeChoose);
		this.frameGame.setPanel(this.panelSpecialMode);
		this.panelSpecialMode.repaint();
	}

	/**
	 * ����ģʽ�ļƷ�ģʽ
	 */
	public void startScoreModel () {
		this.serviceGame.getDataGame().getPlayer1().setClick(false);
		this.startGame();
		// this.frameGame.remove(this.panelSpecialMode);
		Hashtable<String, Rect> rectList = this.serviceGame.getDataGame().getRectList();
		Iterator<String> it = rectList.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Rect rect = rectList.get(key);
			rect.setScore((int) (Math.random() * 10));
			rect.setScoreModel(true);
		}
	}

	/**
	 * ����ģʽ�����㶦��ģʽ
	 */
	public void startThreePlayersModel () {

	}

	/**
	 * ����ģʽ��������ģʽ
	 */
	public void noContinuousMode () {

	}

	/**
	 * ����Ϸģʽѡ��ص���ʼ�˵�
	 */
	public void restartMenu () {
		// TODO
		// ���ű�������
		// if(!DataConfig.isMute()) {
		// AllSounds.START_BGM = new PlayMP3(DataConfig.getSounds() +
		// "start_bgm.mp3");
		// new Thread(AllSounds.START_BGM).start();
		// }

		// ������Ϸ��ʼ�˵�����
		// this.panelStart = new PanelStart(frameGame, this,
		// this.serviceGame.getDataGame());
		this.frameGame.remove(this.panelModeChoose);
		this.frameGame.setPanel(this.panelStart);
	}

	/**
	 * ���������ӻص���Ϸģʽѡ��
	 * 
	 * @author cylong
	 * @version May 26, 2014 2:40:45 AM
	 */
	public void goToModelChoose () {
		this.frameGame.remove(this.panelConnectHome);
		this.frameGame.setPanel(this.panelModeChoose);
		this.panelModeChoose.repaint();
	}

	/**
	 * ��������
	 * 
	 * @author cylong
	 * @version May 28, 2014 12:24:47 PM
	 */
	public void startAboutUs () {
		panelAboutUs = new PanelAboutUs(frameGame, this, serviceGame.getDataGame());
		this.frameGame.remove(panelStart);
		this.frameGame.setPanel(panelAboutUs);
		panelAboutUs.repaint();
	}

	/**
	 * �ӹ������ǻص���ʼ�˵�
	 * 
	 * @author cylong
	 * @version May 28, 2014 12:45:16 PM
	 */
	public void returnStartPanel () {
		// ���ű�������
		// TODO
		// if(!DataConfig.isMute()) {
		// AllSounds.START_BGM = new PlayMP3(DataConfig.getSounds() +
		// "start_bgm.mp3");
		// new Thread(AllSounds.START_BGM).start();
		// }
		//
		// if(AllSounds.START_BGM != null) {
		// AllSounds.START_BGM.stop();
		// }
		this.panelStart = new PanelStart(this.frameGame, this, this.serviceGame.getDataGame());
		this.frameGame.remove(this.panelAboutUs);
		this.frameGame.setPanel(this.panelStart);

	}

}
