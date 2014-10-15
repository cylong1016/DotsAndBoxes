package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import data.DataGame;

/**
 * ˫����Ϸ�ķ�����
 * 
 * @author cylong
 * @since 2014 / 5 / 11 12:00 AM
 */
public class GameServer {

	private DataGame dataGame = null;
	/** �������˵�socket */
	private Socket socket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private ServerSocket serverSocket = null;

	public void startServer () {
		try {
			// ��1994�Ŷ˿ڼ���
			serverSocket = new ServerSocket(1994);
			// �ȴ��ͻ��������ӣ�accept()�����᷵��һ��socket����
			socket = serverSocket.accept();
			// TODO
			System.out.println("�пͻ������ӵ���������");
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
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(dataGame);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.dataGame = dataGame;
	}

	public void closeServer () {
		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
