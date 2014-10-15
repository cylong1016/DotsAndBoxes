package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * ���ݱ��浽���̺ʹӴ��̶�ȡ<br />
 * ���л��õ��Һ�ʹ�࣬��ȫ�ҵ���~~
 * 
 * @author ������
 * @since 2014 / 4 / 12 11:05 PM
 * 
 */
public class DataDisk implements DataTransmission {

	/**
	 * �����л�Ҫʵ������ӿڰ�����������Externalizable <br />
	 * ��ֱ���ˣ��˷��Һó�ʱ�� ����һ���ؼ��֣�����transient
	 * 
	 * @see data.DataTransmission#saveData(data.DataGame)
	 * 
	 */
	@Override
	public void saveData (DataGame dataGame, int i) {

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("data/diskRecord" + i + ".dat"));
			oos.writeObject(dataGame);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.flush();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public DataGame loadData (int i) {
		ObjectInputStream ois = null;
		DataGame dataGame = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("data/diskRecord" + i + ".dat"));
			dataGame = (DataGame) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return dataGame;
	}

}
