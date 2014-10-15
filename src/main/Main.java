package main;

import service.ServiceGame;
import ui.panel.start.PanelStartPre;
import ui.window.FrameGame;
import control.GameControl;
import data.DataGame;

/**
 * Dots and Boxes��Ϸ�����
 * 
 * @author CoolCode
 * @since 2014 / 3 / 16 2 : 33 AM
 */
public class Main {
	/**
	 * ���������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// ������Ϸ���ݿ�
		DataGame dataGame = new DataGame();
		// ������ϷFrameGame���
		FrameGame frameGame = new FrameGame();
		// ������Ϸ�߼���
		ServiceGame serviceGame = new ServiceGame(dataGame);
		// ������Ϸ��������������Ϸ�������Ϸ�߼��飩
		GameControl gameControl = new GameControl(serviceGame, frameGame);
		// ������Ϸ��ʼ����
		PanelStartPre panelStartPre = new PanelStartPre(frameGame, gameControl, dataGame);
		gameControl.setPanelStartPre(panelStartPre);
		frameGame.setPanel(panelStartPre);
	}
}
