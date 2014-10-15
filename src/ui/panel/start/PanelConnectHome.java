/**
 * 
 */
package ui.panel.start;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.AllImages;
import ui.label.LabelButton;
import util.GameUtil;
import control.GameControl;

/**
 * 选择创建房间或者加入房间
 * 
 * @author cylong
 * @since 2014 / 5 / 11 12:23 AM
 */
public class PanelConnectHome extends JPanel implements Runnable, MouseListener {

	private static final long serialVersionUID = 1L;

	/**
	 * 游戏控制器
	 */
	public GameControl gameControl = null;
	/**
	 * 创建房间
	 */
	private LabelButton createHome = null;
	/**
	 * 加入房间
	 */
	private LabelButton joinHome = null;
	/**
	 * 输入框
	 */
	private JTextField text = null;
	/**
	 * 本机IP
	 */
	private String ip = null;
	/**
	 * Panel的宽
	 */
	private int width = 0;
	private int maxWidth = 300;
	/**
	 * Panel的高
	 */
	private int height = 340;
	private LabelButton exit = null;

	public PanelConnectHome (GameControl gameControl) {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 获得本机IP
		ip = addr.getHostAddress().toString();
		this.gameControl = gameControl;
		this.createHome = new LabelButton(50, 150, 221, 44, AllImages.IMG_CREATE_HOME, gameControl, "CreateHome", true);
		this.add(createHome);
		this.joinHome = new LabelButton(50, 30, 221, 44, AllImages.IMG_JOIN_HOME, gameControl, "JoinHome", true);
		this.add(joinHome);
		this.exit = new LabelButton(0, 0, 32, 32, AllImages.WINDOW_EXIT1, gameControl, "GoToModelChoose", false);
		this.add(exit);
		text = new JTextField();
		text.setBounds(141, 95, 130, 35);
		text.setBorder(null);
		text.setOpaque(false);
		this.add(text);
		this.setLayout(null);
		this.setOpaque(false);
		this.setBounds(200, 200, width, height);
		new Thread(this).start();
	}

	/** 画笔透明度 */
	protected float hyaline = 0.0f;
	private Font font = new Font("黑体", Font.BOLD, 20);
	private Color color = new Color(217, 217, 217);

	@Override
	public void paint (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, hyaline));
		g2.drawImage(AllImages.IMG_CONNECT_BG, 0, 0, getWidth(), getHeight(), this);
		g2.drawImage(AllImages.IMG_JOIN_IP, 50, 90, this);
		g2.drawImage(AllImages.IMG_LOCAL_IP, 52, 210, this);
		g2.drawImage(AllImages.IMG_LABEL_BORDER, 52, 270, this);
		g2.setFont(font);
		g2.setColor(color);
		g2.drawString(ip, 80, 297);
		super.paint(g);
	}

	/**
	 * @author cylong
	 * @version May 26, 2014 2:24:25 AM
	 */
	@Override
	public void run () {
		while (true) {
			GameUtil.sleep(20);
			if (width < maxWidth) {
				width += 10;
			} else {
				width = maxWidth;
			}
			this.setSize(width, height);
			hyaline += 0.1f;
			if (hyaline > 1) {
				hyaline = 1;
			}
			if (width == maxWidth) {
				break;
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @author cylong
	 * @version May 26, 2014 2:31:09 AM
	 */
	@Override
	public void mouseClicked (MouseEvent e) {
		if (e.getSource() == joinHome) {
			this.gameControl.setIp(text.getText());
		}
	}

	@Override
	public void mousePressed (MouseEvent e) {
	}

	@Override
	public void mouseReleased (MouseEvent e) {
	}

	@Override
	public void mouseEntered (MouseEvent e) {
	}

	@Override
	public void mouseExited (MouseEvent e) {
	}

}
