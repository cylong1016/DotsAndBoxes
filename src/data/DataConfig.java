package data;

import sound.PlayMP3;

/**
 * 这里有这个游戏的各种配置数据<br />
 * 数据是游戏中所有类共享的，所以类中成员都是静态的<br />
 * 但是全是静态的好像不好【不过很方便→_→】
 * 
 * @author 陈云龙
 * @since 2014 / 3 / 19 7 : 02 PM
 */
public class DataConfig {

	/**
	 * 游戏是否静音
	 */
	private static boolean isMute = false;
	/**
	 * 游戏是否暂停
	 */
	private static boolean isPause = false;
	/**
	 * 皮肤选择
	 */
	private static String skin = "images/skin1/";
	/**
	 * 声音选择
	 */
	private static String sounds = "sounds/sounds1/";
	/**
	 * 棋盘大小（X * X）
	 */
	private static int chessBoardSize = 6;

	/**
	 * 恢复默认DataConfig数据
	 */
	public static void initConfig () {
		DataConfig.isPause = false;
		DataConfig.isMute = false;
		DataConfig.skin = "images/skin1/";
		DataConfig.sounds = "sounds/sounds1/";
	}

	public static int getChessBoardSize () {
		return chessBoardSize;
	}

	public static void setChessBoardSize (int chessBoardSize) {
		DataConfig.chessBoardSize = chessBoardSize;
	}

	public static String getSounds () {
		return sounds;
	}

	public static void setSounds (String sounds) {
		DataConfig.sounds = sounds;
	}

	public static String getSkin () {
		return skin;
	}

	public static void setSkin (String skin) {
		DataConfig.skin = skin;
	}

	public static boolean isMute () {
		return isMute;
	}

	public static void setMute (boolean isMute) {
		if (isMute) {
			PlayMP3.setVolume(0.0, true);
		} else {
			PlayMP3.setVolume(80.0, true);
		}
		DataConfig.isMute = isMute;
	}

	/**
	 * @version May 20, 2014 3:53:42 AM
	 * @return the isPause
	 */
	public static boolean isPause () {
		return isPause;
	}

	/**
	 * @version May 20, 2014 3:53:42 AM
	 * @param isPause
	 *            the isPause to set
	 */
	public static void setPause (boolean isPause) {
		DataConfig.isPause = isPause;
	}

}
