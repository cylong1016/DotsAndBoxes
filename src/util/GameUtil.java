package util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.JFrame;

import data.DataConfig;
import sound.PlayWave;
import ui.label.Line;

/**
 * 各种乱七八糟
 * 
 * @author 陈云龙
 * @since 2014 / 3 / 16 2 : 35 AM
 */
public class GameUtil {

	/**
	 * 
	 * @param jf
	 *            JFrame对象
	 * @return 窗口左上角坐标
	 */
	public static Point setFrameCenter (JFrame jf) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = (int) ((screen.getWidth() - jf.getWidth()) / 2);
		int y = (int) ((screen.getHeight() - jf.getHeight()) / 2);
		jf.setLocation(x, y - 20);
		return new Point(x, y - 20);
	}

	/**
	 * 将Line集合中的line放到Line数组里
	 * 
	 * @param lines
	 *            Line集合
	 * @return Line[]
	 * @since 2014 / 3 / 24 1 : 16 AM
	 */
	public static Line[] mapToArr (Hashtable<String, Line> lines) {
		Line[] linesArr = null;
		int[] key_int = null;
		ArrayList<String> keys = new ArrayList<String>();
		Iterator<String> it = lines.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			keys.add(key);
		}
		key_int = new int[keys.size()];
		// 将String的key转化成int放到数组里
		for (int i = 0; i < key_int.length; i++) {
			key_int[i] = Integer.parseInt(keys.get(i));
		}
		// 从小到大排序
		sort(key_int);
		// 取出集合中的line，放到Line数组里
		linesArr = new Line[key_int.length];
		for (int i = 0; i < key_int.length; i++) {
			String str = null;
			if (key_int[i] < 10) {
				str = "00" + Integer.toString(key_int[i]);
			} else if (key_int[i] < 100) {
				str = "0" + Integer.toString(key_int[i]);
			} else {
				str = Integer.toString(key_int[i]);
			}
			linesArr[i] = lines.get(str);
		}

		return linesArr;
	}

	/**
	 * 选择排序法
	 * 
	 * @param arr
	 *            int数组
	 * @since 2014 / 3 / 24 1 : 16 AM
	 */
	public static void sort (int arr[]) {
		for (int i = 0; i < arr.length - 1; i++) {
			int min = arr[i];
			int minIndex = i;

			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) {
					min = arr[j];
					minIndex = j;
				}
			}
			// 退出第二个for时候就找到最小值
			int temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
	}

	/**
	 * 睡眠方法
	 * 
	 * @param time
	 *            睡眠时间
	 * @since 2014 / 4 / 10 1 : 16 AM
	 */
	public static void sleep (long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 鼠标移动到按钮上的声音
	 * 
	 * @author cylong
	 * @version May 20, 2014 6:18:00 PM
	 */
	public static void startMoveButtonSound () {
		if (!DataConfig.isMute()) {
			new PlayWave(DataConfig.getSounds() + "光标.wav").start();
		}
	}

	/**
	 * 鼠标点击的声音
	 * 
	 * @author cylong
	 * @version May 25, 2014 2:09:50 PM
	 */
	public static void startClickSound () {
		if (!DataConfig.isMute()) {
			new PlayWave(DataConfig.getSounds() + "点击.wav").start();
		}
	}
}
