package data;

import sound.PlayMP3;

/**
 * �����������Ϸ�ĸ�����������<br />
 * ��������Ϸ�������๲��ģ��������г�Ա���Ǿ�̬��<br />
 * ����ȫ�Ǿ�̬�ĺ��񲻺á������ܷ����_����
 * 
 * @author ������
 * @since 2014 / 3 / 19 7 : 02 PM
 */
public class DataConfig {

	/**
	 * ��Ϸ�Ƿ���
	 */
	private static boolean isMute = false;
	/**
	 * ��Ϸ�Ƿ���ͣ
	 */
	private static boolean isPause = false;
	/**
	 * Ƥ��ѡ��
	 */
	private static String skin = "images/skin1/";
	/**
	 * ����ѡ��
	 */
	private static String sounds = "sounds/sounds1/";
	/**
	 * ���̴�С��X * X��
	 */
	private static int chessBoardSize = 6;

	/**
	 * �ָ�Ĭ��DataConfig����
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
