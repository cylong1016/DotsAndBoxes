package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import data.DataGame;

/**
 * 双人游戏的服务器
 * 
 * @author cylong
 * @since 2014 / 5 / 11 12:00 AM
 */
public class GameServer {

	private DataGame dataGame = null;
	/** 服务器端的socket */
	private Socket socket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private ServerSocket serverSocket = null;

	public void startServer () {
		try {
			// 在1994号端口监听
			serverSocket = new ServerSocket(1994);
			// 等待客户端来连接，accept()方法会返回一个socket连接
			socket = serverSocket.accept();
			// TODO
			System.out.println("有客户端连接到服务器！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DataGame getDataGame () {
		try {
			// 读取socket中传递的数据
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
