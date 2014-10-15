package main;

import service.ServiceGame;
import ui.panel.start.PanelStartPre;
import ui.window.FrameGame;
import control.GameControl;
import data.DataGame;

/**
 * Dots and Boxes游戏主入口
 * 
 * @author CoolCode
 * @since 2014 / 3 / 16 2 : 33 AM
 */
public class Main {
	/**
	 * 程序主入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 创建游戏数据库
		DataGame dataGame = new DataGame();
		// 创建游戏FrameGame框架
		FrameGame frameGame = new FrameGame();
		// 创建游戏逻辑块
		ServiceGame serviceGame = new ServiceGame(dataGame);
		// 创建游戏控制器（连接游戏框架与游戏逻辑块）
		GameControl gameControl = new GameControl(serviceGame, frameGame);
		// 创建游戏开始动画
		PanelStartPre panelStartPre = new PanelStartPre(frameGame, gameControl, dataGame);
		gameControl.setPanelStartPre(panelStartPre);
		frameGame.setPanel(panelStartPre);
	}
}
