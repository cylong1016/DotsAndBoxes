package sound;

import data.DataConfig;

/**
 * ��Ϸ��ȫ��������
 * 
 * @author cylong
 * @version May 25, 2014 1:52:14 PM
 */
public class AllSounds {

	/**
	 * ��Ϸ�������ı�������
	 */
	public static PlayMP3 GAME_PANEL_BGM = new PlayMP3(DataConfig.getSounds() + "panel_game_bgm.mp3");
	/**
	 * ��ʼ�˵���������
	 */
	public static PlayMP3 START_BGM = new PlayMP3(DataConfig.getSounds() + "start_bgm.mp3");
}