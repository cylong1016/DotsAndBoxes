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
 * 游戏总控制器
 * 
 * @author 陈云龙
 * @since 2014/3/21 1 : 06 AM
 */
public class GameControl implements Runnable {

	/**
	 * 游戏开始菜单界面
	 */
	private AbstractPanel panelStart = null;
	/**
	 * 游戏开始动画界面
	 */
	private PanelStartPre panelStartPre = null;

	private PanelAboutUs panelAboutUs = null;

	public void setPanelStartPre (PanelStartPre panelStartPre) {
		this.panelStartPre = panelStartPre;
	}

	/**
	 * 与磁盘的数据传输
	 */
	private DataTransmission dataDisk = null;
	/**
	 * 游戏主框架
	 */
	private FrameGame frameGame = null;
	/**
	 * 游戏的业务逻辑
	 */
	private ServiceGame serviceGame = null;
	/**
	 * 点击开始游戏后的选择面板
	 */
	private PanelModeChoose panelModeChoose = null;
	/**
	 * 点击联机对战后的选择面板
	 */
	private PanelConnectHome panelConnectHome = null;
	/**
	 * 游戏面板
	 */
	private PanelGame panelGame = null;
	/**
	 * 特殊模式面板
	 */
	private PanelSpecialMode panelSpecialMode = null;

	/**
	 * 构造方法
	 * 
	 * @param frameGame
	 *            游戏主框架
	 * @param serviceGame
	 *            业务逻辑
	 */
	public GameControl (ServiceGame serviceGame, FrameGame frameGame) {
		// 初始化游戏主框架
		this.frameGame = frameGame;
		// 初始化业务逻辑
		this.serviceGame = serviceGame;
		// 初始化数据传输
		this.dataDisk = new DataDisk();
		// 线程，用来判断游戏是否结束
		new Thread(this).start();
	}

	/**
	 * 在鼠标点击一条线的时候显示该线
	 * 
	 * @param o
	 *            鼠标获得的资源
	 */
	public void showLine (Object o) {
		this.serviceGame.showLine(o);
	}

	/**
	 * 开始菜单
	 */
	public void startMenu () {
		// 播放背景音乐
		if (!DataConfig.isMute()) {
			AllSounds.START_BGM = new PlayMP3(DataConfig.getSounds() + "start_bgm.mp3");
			new Thread(AllSounds.START_BGM).start();
		}

		// 创建游戏开始菜单界面
		this.panelStart = new PanelStart(this.frameGame, this, this.serviceGame.getDataGame());
		this.frameGame.remove(this.panelStartPre);
		this.frameGame.setPanel(this.panelStart);
	}

	/**
	 * 新游戏
	 */
	public void startGame () {
		LabelButton.playMusic = false;
		// 创建游戏面板
		// 安装控制器到面板和游戏框架
		this.panelGame = new PanelGame(this.frameGame, this, this.serviceGame.getDataGame());
		// }
		this.frameGame.remove(this.panelModeChoose);
		this.frameGame.setPanel(this.panelGame);
	}

	/**
	 * 联机对战
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
	 * 创建房间
	 */
	public void createHome () {
		GameServer gameServer = new GameServer();
		gameServer.startServer();
		this.serviceGame.setGameServer(gameServer);
		// 使玩家一开始下
		this.serviceGame.getDataGame().getPlayer2().setClick(true);
		this.startGame();
		this.frameGame.remove(this.panelConnectHome);
	}

	/**
	 * 房间IP
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
	 * 加入房间
	 */
	public void joinHome () {
		GameClient gameClient = new GameClient(ip);
		gameClient.startConnect();
		this.serviceGame.setGameClient(gameClient);
		this.startGame();
		this.frameGame.remove(this.panelConnectHome);
	}

	/**
	 * 选择新游戏后选择游戏模式面板
	 */
	public void startChoose () {
		// 初始化开始后的选择面板
		this.panelModeChoose = new PanelModeChoose(this.frameGame, this, this.serviceGame.getDataGame());
		// this.panelProgressChoose.setVisible(false);
		this.frameGame.remove(this.panelStart);
		this.frameGame.setPanel(this.panelModeChoose);
	}

	/**
	 * 返回主菜单
	 */
	public void gotoMainMenu () {

		// 修复线程没有结束的bug
		this.panelGame.setBack(true);
		this.frameGame.remove(this.panelGame);
		this.panelGame = null;
		this.panelStart = new PanelStart(this.frameGame, this, this.serviceGame.getDataGame());
		this.frameGame.setPanel(this.panelStart);
		// 初始化游戏数据
		this.serviceGame.getDataGame().initGame();
	}

	/**
	 * 悔棋
	 */
	public void takeBack () {
		this.serviceGame.takeBack();
	}

	/**
	 * 鼠标移动到某一行的时候显示出一条线
	 * 
	 * @param o
	 *            鼠标点击的对象实例
	 */
	public void mouseEnteredLine (Object o) {
		this.serviceGame.mouseEnteredLine(o);
	}

	/**
	 * 鼠标移开后隐藏线
	 * 
	 * @param o
	 *            鼠标点击的对象实例
	 */
	public void mouseExitedLine (Object o) {
		this.serviceGame.mouseExitedLine(o);
	}

	public ServiceGame getServiceGame () {
		return this.serviceGame;
	}

	/**
	 * 重新开始游戏
	 */
	public void restartGame () {
		this.serviceGame.getDataGame().initGame();
	}

	/**
	 * 保存棋盘
	 */
	public void saveChess () {
		this.dataDisk.saveData(this.serviceGame.getDataGame(), 0);
	}

	/**
	 * 读取棋盘
	 * 
	 * @since 2014 / 4 / 15 1:04 AM
	 */
	public void loadChess () {

		// 读取进度
		this.serviceGame.setDataGame(this.dataDisk.loadData(0));
	}

	@Override
	public void run () {
		while (true) {
			GameUtil.sleep(5);
			// 判断游戏是否结束
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
	 * 开启特殊模式
	 */
	public void startSpcialMode () {
		this.panelSpecialMode = new PanelSpecialMode(this.frameGame, this, this.serviceGame.getDataGame());
		this.frameGame.remove(this.panelModeChoose);
		this.frameGame.setPanel(this.panelSpecialMode);
		this.panelSpecialMode.repaint();
	}

	/**
	 * 特殊模式的计分模式
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
	 * 特殊模式的三足鼎立模式
	 */
	public void startThreePlayersModel () {

	}

	/**
	 * 特殊模式的无连下模式
	 */
	public void noContinuousMode () {

	}

	/**
	 * 从游戏模式选择回到开始菜单
	 */
	public void restartMenu () {
		// TODO
		// 播放背景音乐
		// if(!DataConfig.isMute()) {
		// AllSounds.START_BGM = new PlayMP3(DataConfig.getSounds() +
		// "start_bgm.mp3");
		// new Thread(AllSounds.START_BGM).start();
		// }

		// 创建游戏开始菜单界面
		// this.panelStart = new PanelStart(frameGame, this,
		// this.serviceGame.getDataGame());
		this.frameGame.remove(this.panelModeChoose);
		this.frameGame.setPanel(this.panelStart);
	}

	/**
	 * 从网络连接回到游戏模式选择
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
	 * 关于我们
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
	 * 从关于我们回到开始菜单
	 * 
	 * @author cylong
	 * @version May 28, 2014 12:45:16 PM
	 */
	public void returnStartPanel () {
		// 播放背景音乐
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
