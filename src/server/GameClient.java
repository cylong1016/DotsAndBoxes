package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import data.DataGame;

/**
 * ˫����Ϸ�Ŀͻ���
 * 
 * @author cylong
 * @since 2014 / 5 / 11 12:08 AM
 */
public class GameClient {

	private DataGame dataGame = null;
	/**
	 * ��ͻ������ӵ�Socket
	 */
	private Socket socket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private String ip = null;

	public GameClient (String ip) {
		this.ip = ip;
	}

	public void startConnect () {
		try {
			socket = new Socket(ip, 1994);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DataGame getDataGame () {
		try {
			// ��ȡsocket�д��ݵ�����
			ois = new ObjectInputStream(socket.getInputStream());
			dataGame = (DataGame) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataGame;
	}

	public void setDataGame (DataGame dataGame) {
		this.dataGame = dataGame;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(dataGame);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
