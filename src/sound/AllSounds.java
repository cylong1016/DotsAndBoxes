package sound;

import data.DataConfig;

/**
 * 游戏里全部的声音
 * 
 * @author cylong
 * @version May 25, 2014 1:52:14 PM
 */
public class AllSounds {

	/**
	 * 游戏下棋界面的背景音乐
	 */
	public static PlayMP3 GAME_PANEL_BGM = new PlayMP3(DataConfig.getSounds() + "panel_game_bgm.mp3");
	/**
	 * 开始菜单背景音乐
	 */
	public static PlayMP3 START_BGM = new PlayMP3(DataConfig.getSounds() + "start_bgm.mp3");
}