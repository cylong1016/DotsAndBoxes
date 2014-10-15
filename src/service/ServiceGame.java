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
 * 游戏的业务逻辑
 * 
 * @author 陈云龙
 * @since 2014 / 3 / 20 6 : 37 PM
 */
public class ServiceGame implements Runnable {

	/**
	 * 游戏数据库
	 */
	private DataGame dataGame = null;
	/**
	 * 判断电脑当前步骤是否完成
	 */
	private boolean isHandling = false;
	/**
	 * 服务器端
	 */
	private GameServer gameServer = null;

	/**
	 * 客户端
	 */
	private GameClient gameClient = null;
	/**
	 * 业务逻辑线程
	 */
	private Thread service = null;
	/**
	 * 倒计时线程
	 */
	private Countdown countdown = null;

	/**
	 * 初始化玩家数据
	 */
	public void initPlayerData () {
		this.dataGame.getPlayer1().setClick(false);
		this.dataGame.getPlayer2().setClick(false);
		this.dataGame.getPlayer1().setFormRect(false);
		this.dataGame.getPlayer2().setFormRect(false);
	}

	public ServiceGame (final DataGame dataGame) {
		if (service == null) {
			// 启动线程
			// 主要是为了使电脑可以延迟下棋
			service = new Thread(this);
			service.start();
		}
		this.dataGame = dataGame;
	}

	/**
	 * 在鼠标点击一条线的时候显示该线
	 * 
	 * @param o
	 *            鼠标获得资源
	 */
	public void showLine (Object o) {
		if (DataConfig.isPause()) {
			return;
		}
		if (countdown == null) {
			// 倒计时线程
			countdown = new Countdown();
			countdown.start();

		}
		if (dataGame.getGameMode() == 0) {
			// 单人游戏
			this.startSingleGame(o);
		} else if (dataGame.getGameMode() == 1) {
			// 双人游戏
			this.startTwoGame(o);
		} else if (dataGame.getGameMode() == 2) {
			// 联机对战
			this.startConnectGame(o);
		}
		// 判断游戏是否结束
		this.dataGame.setWinner(this.isGameover());
	}

	/**
	 * 单人游戏
	 */
	private void startSingleGame (Object o) {
		// 如果电脑正在处理，则玩家不能操作
		if (isHandling) {
			return;
		}
		this.initPlayerData();
		// 玩家1下
		this.dataGame.getPlayer1().setClick(this.playerShowLine(1, o));
		// 判断是否可以形成方块,并形成方块和加分
		this.dataGame.getPlayer1().setFormRect(this.formRect(1));
	}

	/**
	 * 双人游戏
	 */
	private void startTwoGame (Object o) {
		if ((dataGame.getPlayer2().isClick() && !dataGame.getPlayer2().isFormRect()) || dataGame.getPlayer1().isFormRect()) {
			// 玩家1下
			this.initPlayerData();
			this.dataGame.getPlayer1().setClick(this.playerShowLine(1, o));
			// 判断是否可以形成方块,并形成方块
			this.dataGame.getPlayer1().setFormRect(this.formRect(1));
		} else if ((dataGame.getPlayer1().isClick() && !dataGame.getPlayer1().isFormRect()) || dataGame.getPlayer2().isFormRect()) {
			// 玩家2下
			this.initPlayerData();
			this.dataGame.getPlayer2().setClick(this.playerShowLine(2, o));
			// 判断是否可以形成方块,并形成方块
			this.dataGame.getPlayer2().setFormRect(this.formRect(2));
		}

	}

	/**
	 * 联机对战
	 * 
	 * @since 2014 / 5 / 11 3:23 AM
	 */
	private void startConnectGame (Object o) {
		if (gameServer != null) {
			if ((dataGame.getPlayer2().isClick() && !dataGame.getPlayer2().isFormRect()) || dataGame.getPlayer1().isFormRect()) {
				// 玩家1下
				this.initPlayerData();
				this.dataGame.getPlayer1().setClick(this.playerShowLine(1, o));
				// 判断是否可以形成方块,并形成方块
				this.dataGame.getPlayer1().setFormRect(this.formRect(1));
				this.gameServer.setDataGame(dataGame);
			}
		}
		if (gameClient != null) {
			if ((dataGame.getPlayer1().isClick() && !dataGame.getPlayer1().isFormRect()) || dataGame.getPlayer2().isFormRect()) {
				// 玩家2下
				this.initPlayerData();
				this.dataGame.getPlayer2().setClick(this.playerShowLine(2, o));
				// 判断是否可以形成方块,并形成方块
				this.dataGame.getPlayer2().setFormRect(this.formRect(2));
				this.gameClient.setDataGame(dataGame);
			}
		}
	}

	/**
	 * 玩家下, 并判断该玩家是否形成方块,<br />
	 * 增加一步，并将步数赋值给被点击的线
	 * 
	 * @param playern
	 *            1为玩家一，二为玩家二
	 * @param o
	 *            鼠标获得的对象
	 * @return 玩家是否点击成功
	 */
	private boolean playerShowLine (int playern, Object o) {
		// 判断是否点击成功
		boolean b = false;
		// 棋盘上线的集合
		Hashtable<String, Line> LinesList = this.dataGame.getLinesList();
		// 遍历Hashtable的key和value
		Iterator<String> it = LinesList.keySet().iterator();
		// hasNext()返回一个布尔值
		while (it.hasNext()) {
			// 取出key
			String key = (String) it.next();
			// 通过key取出value
			Line tempLine = LinesList.get(key);
			if (tempLine == o && !tempLine.isClicked()) {
				// 点击成功
				b = true;
				// 设定为已点击,并设置谁点击的
				tempLine.setLineColor(playern);
				GameUtil.startClickSound();
				// 增加一步
				this.dataGame.setStep(dataGame.getStep() + 1);
				// 赋值
				tempLine.setStep(dataGame.getStep());
				dataGame.setCountdown(90);
			}
		}
		return b;
	}

	/**
	 * 电脑下
	 */
	private void computerShowLine () {
		// 棋盘上线的集合
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
			// 步数加1
			this.dataGame.setStep(dataGame.getStep() + 1);
		}
	}

	/**
	 * 判断是否形成方块，并加分
	 * 
	 * @param color
	 *            形成方块的颜色
	 * @return boolean 是否形成方块
	 */
	private boolean formRect (int color) {
		// 棋盘上线的集合
		Hashtable<String, Line> LinesList = this.dataGame.getLinesList();
		// 防止当前线不存在出现bug
		if (this.dataGame.getJustShow() == null) {
			return false;
		}
		// 得到当前点击的线
		Line curLine = LinesList.get(this.dataGame.getJustShow());
		// 判断是否形成方块
		boolean b = false;
		Hashtable<String, Rect> rectList = this.dataGame.getRectList();
		Iterator<String> it = rectList.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Rect rect = rectList.get(key);
			Vector<Line> lines = rect.getLines();
			// 判断某一个方块的所有线是否都被点击
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
				// 加分
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
	 * 悔棋操作
	 * 
	 * @since 2014 / 4 / 9
	 */
	public void takeBack () {
		// 防止游戏刚开始时候没有刚下的线
		if (this.dataGame.getJustShow() == null) {
			return;
		}
		// 棋盘上线的集合
		Hashtable<String, Line> LinesList = this.dataGame.getLinesList();
		// 玩家与电脑对战
		if (dataGame.getGameMode() == 0) {
			// 电脑下完才会悔棋
			if (isHandling) {
				return;
			}
			// 清除电脑下的线
			while (LinesList.get(this.dataGame.getJustShow()).getLineColor() == 2) {
				this.takeBackOnce(LinesList);
			}
			// 清除玩家下的线
			this.takeBackOnce(LinesList);
		} else if (dataGame.getGameMode() == 1) {
			this.initPlayerData();
			// 本机双人对战
			// 点击后取消当前玩家的一步
			this.takeBackOnce(LinesList);
			// 判断取消后现在由谁下
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
	 * 悔棋一步,并判断是否取消形成的方块然后减分
	 * 
	 * @since 2014 / 4 / 9
	 */
	private void takeBackOnce (Hashtable<String, Line> LinesList) {
		// 减少一步
		this.dataGame.setStep(this.dataGame.getStep() - 1);
		// 得到当前的线
		Line curLine = LinesList.get(this.dataGame.getJustShow());
		// 防止第一条线没有前一条线的空指针异常
		if (curLine.getPreShow() == null) {
			// 取消该线
			curLine.initLine();
			// 并将JustShow设为空
			this.dataGame.setJustShow(null);
			return;
		}
		String preKey = curLine.getPreShow();
		// 将当前点击的线设置为刚刚取消的线的前一条线
		this.dataGame.setJustShow(preKey);
		// 判断是否取消已经显示的方块
		Hashtable<String, Rect> rectList = this.dataGame.getRectList();
		Iterator<String> it = rectList.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Rect rect = rectList.get(key);
			// 方块已经被显示则判断是否取消显示
			if (rect.getInsideColor() != 0) {
				// 得到与方块匹配的四条线集合
				Vector<Line> lines = rect.getLines();
				for (int i = 0; i < lines.size(); i++) {
					Line line = lines.get(i);
					// == 比较的是引用是否相等
					if (line == curLine) {
						// 对应玩家减一分
						if (rect.getInsideColor() == 1) {
							this.dataGame.getPlayer1().setScore(this.dataGame.getPlayer1().getScore() - 1);
						} else {
							this.dataGame.getPlayer2().setScore(this.dataGame.getPlayer2().getScore() - 1);
						}
						// 取消显示的方块
						rect.initRect();
					}
				}
			}
		}
		// 取消该线
		curLine.initLine();
	}

	/**
	 * 判断游戏是否结束
	 * 
	 * @since 2014 / 4 / 13 12:57 PM
	 * @return 0：未结束 1：玩家一胜利 2：玩家二胜利
	 */
	private int isGameover () {
		// 棋盘上线的集合
		Hashtable<String, Line> LinesList = this.dataGame.getLinesList();
		Iterator<String> it = LinesList.keySet().iterator();
		// hasNext()返回一个布尔值
		while (it.hasNext()) {
			// 取出key
			String key = it.next();
			// 通过key取出value
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
	 * 鼠标移动到某一行的时候显示出一条线
	 * 
	 * @param o
	 *            鼠标点击的对象实例
	 */
	public void mouseEnteredLine (Object o) {
		// 如果电脑正在处理，则玩家不能操作
		if (isHandling) {
			return;
		}
		// 棋盘上线的集合
		Hashtable<String, Line> LinesList = this.dataGame.getLinesList();
		// 遍历Hashtable的key和value
		// Iterator迭代
		Iterator<String> it = LinesList.keySet().iterator();
		// hasNext()返回一个布尔值
		while (it.hasNext()) {
			// 取出key
			String key = it.next();
			// 通过key取出value
			Line tempLine = LinesList.get(key);
			if (!tempLine.isClicked() && tempLine == o) {
				tempLine.setMoved(true);
				break;
			}
		}
	}

	/**
	 * 鼠标移开后隐藏线
	 * 
	 * @param o
	 *            鼠标点击的对象实例
	 */
	public void mouseExitedLine (Object o) {
		// 棋盘上线的集合
		Hashtable<String, Line> LinesList = this.dataGame.getLinesList();
		// 遍历Hashtable的key和value
		// Iterator迭代
		Iterator<String> it = LinesList.keySet().iterator();
		// hasNext()返回一个布尔值
		while (it.hasNext()) {
			// 取出key
			String key = it.next();
			// 通过key取出value
			Line tempLine = LinesList.get(key);
			tempLine.setMoved(false);
		}
	}

	/**
	 * 线程<br />
	 * 为了延迟电脑下棋
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
			// 如果是双人游戏，则不执行下面代码
			if (dataGame.getGameMode() != 0) {
				continue;
			}
			this.isHandling = true;
			// 玩家1点击成功且未形成方块，则电脑下
			if (dataGame.getPlayer1().isClick() && !dataGame.getPlayer1().isFormRect()) {
				dataGame.setCountdown(90);
				this.dataGame.getPlayer1().setClick(false);
				// 延迟电脑下棋时间
				GameUtil.sleep(300);
				// 让电脑下
				this.computerShowLine();
				// 判断是否形成方块,并形成方块
				boolean isComputerFormRect = this.formRect(2);
				// 形成方块后电脑继续下
				while (isComputerFormRect) {
					// 判断游戏是否结束
					this.dataGame.setWinner(this.isGameover());
					// 游戏结束就跳出
					if (this.dataGame.getWinner() != 0) {
						break;
					}
					// 延迟电脑下棋时间
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
	 * 用于读取数据的，写的我快吐了！好乱~~<br />
	 * 悔棋会有点问题，泥煤！问题在哪！！！？？？
	 * 
	 * @since 2014 / 4 / 15
	 * @param dataGame
	 */
	public void setDataGame (DataGame dataGame) {
		// // 初始化没保存的数据
		// dataGame.setDotsList(this.dataGame.getDotsList());
		// 初始化线
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

		// 初始化方块
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
	 * 复制线
	 * 
	 * @param line
	 *            含有保存信息的线
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
	 * 复制方块
	 * 
	 * @param rect
	 *            含有保存信息的方块
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
