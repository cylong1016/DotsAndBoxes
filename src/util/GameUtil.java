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
 * �������߰���
 * 
 * @author ������
 * @since 2014 / 3 / 16 2 : 35 AM
 */
public class GameUtil {

	/**
	 * 
	 * @param jf
	 *            JFrame����
	 * @return �������Ͻ�����
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
	 * ��Line�����е�line�ŵ�Line������
	 * 
	 * @param lines
	 *            Line����
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
		// ��String��keyת����int�ŵ�������
		for (int i = 0; i < key_int.length; i++) {
			key_int[i] = Integer.parseInt(keys.get(i));
		}
		// ��С��������
		sort(key_int);
		// ȡ�������е�line���ŵ�Line������
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
	 * ѡ������
	 * 
	 * @param arr
	 *            int����
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
			// �˳��ڶ���forʱ����ҵ���Сֵ
			int temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
	}

	/**
	 * ˯�߷���
	 * 
	 * @param time
	 *            ˯��ʱ��
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
	 * ����ƶ�����ť�ϵ�����
	 * 
	 * @author cylong
	 * @version May 20, 2014 6:18:00 PM
	 */
	public static void startMoveButtonSound () {
		if (!DataConfig.isMute()) {
			new PlayWave(DataConfig.getSounds() + "���.wav").start();
		}
	}

	/**
	 * �����������
	 * 
	 * @author cylong
	 * @version May 25, 2014 2:09:50 PM
	 */
	public static void startClickSound () {
		if (!DataConfig.isMute()) {
			new PlayWave(DataConfig.getSounds() + "���.wav").start();
		}
	}
}
