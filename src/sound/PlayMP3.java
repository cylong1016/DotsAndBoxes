package sound;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 可以播放MP3 wav ogg格式的音频 <br />
 * 可以调节音量和暂停<br />
 * 这个类是网上找的啦~【然后根据自己的意愿修改了下注释】
 * 
 * @author 陈云龙
 * @since 2014 / 3 / 17<br />
 *        2 : 35 AM
 */
public class PlayMP3 implements Runnable {

	/**
	 * 是否为暂停状态
	 */
	private boolean isPause = true;
	/**
	 * 声音的范围在0～2.0
	 */
	private static double volume = 2.0;
	/**
	 * 用于控制声音的浮点值范围
	 */
	private static FloatControl floatControl = null;
	/**
	 * 文件路径
	 */
	private String filePath = null;
	/**
	 * 是否循环
	 */
	private boolean loop = true;

	/**
	 * 构造函数，初始化音乐的路径
	 * 
	 * @param filePath
	 */
	public PlayMP3 (String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 设置是否循环播放
	 * 
	 * @param loop
	 *            true为循环<br />
	 *            false为不循环<br />
	 *            默认不循环
	 */
	public void setLoop (boolean loop) {
		this.loop = loop;
	}

	@Override
	public void run () {
		do {
			// 做个循环yeah【因为似乎没在api里找到自带的loop方法【大概是没看到233
			try {
				// if (DataConfig.isMute() || stop) {
				// break;
				// }
				init();
				play();
				// if (DataConfig.isMute() || stop) {
				// break;
				// }
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (loop);
	}

	/**
	 * 初始化音乐的相关数据，其实代码几乎都是固定的
	 */
	// 音频输入流, 与FileInputStream类似
	AudioInputStream audioInputStream = null;
	SourceDataLine sourceDataLine = null;

	public void init () throws Exception {

		// 1、将文件转换为音频输入流
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
		// 2、获取这个文件的音频格式
		AudioFormat audioFormat = audioInputStream.getFormat();
		// mp3 ogg wav 转码
		// 这里判断audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED
		// 是因为MP3格式作为有损格式它丢掉了脉冲编码调制（PCM）的音频数据，所以PCM_SIGNED或PCM_SIGNED都可以
		if (filePath.endsWith(".ogg") || (filePath.endsWith(".mp3") || (filePath.endsWith(".wav") & audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED))) {
			// 新建一个规定的音频格式
			audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,// 编码格式
					audioFormat.getSampleRate(), // 原来的波形采样率
					16,// 16位双字节采集
					audioFormat.getChannels(),// 原来的声道数
					// 下面这个是每帧中的字节数，每声道每帧字节数2字节，再加上双声道所以用声道数（2）*字节数（2）
					audioFormat.getChannels() * 2, audioFormat.getSampleRate(), // 每秒的帧数
					false);// ………………
			// 将当前的音频流转换到指定的格式
			audioInputStream = AudioSystem.getAudioInputStream(audioFormat, audioInputStream);
		}
		// 3、设置音频行的信息
		Info info = new Info(SourceDataLine.class, audioFormat);
		// 4、似乎是建立音频行【已经开始到底层的实现了我不行了
		sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
		sourceDataLine.open(audioFormat);// 将音频数据流读入音频行
		sourceDataLine.start();
		// 初始化FloatControl， FloatControl.Type.MASTER_GAIN是某一行上总音量的控件
		floatControl = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
		// 设置为默认的音量大小
		setVolume(volume, false);
	}

	/**
	 * 播放音频文件
	 */
	public void play () {
		try {
			int nByte = 0;
			final int SIZE = 1024;// 缓冲1KB
			byte[] buffer = new byte[SIZE];
			while (nByte != -1) {
				// if (DataConfig.isMute() || stop) {
				// break;
				// }
				// 当你调用了stop之后，音频数据依旧会传输到sourceDataLine中
				// 只是会被抛弃掉而已233【可以在这里加一条下面的语句，
				// System.out.println(buffer);//可以看见stop之后buffer依旧在改变
				if (!isPause) {
					continue;
				}
				// 将音频数据写入混频器……好吧我已经不行了233
				sourceDataLine.write(buffer, 0, nByte);
				nByte = audioInputStream.read(buffer, 0, SIZE);
			}
			// 结束后停止
			sourceDataLine.stop();
			// 清空缓冲区【其实应该没什么必要【不！我之前错了！这个还是有必要的
			sourceDataLine.drain();
			sourceDataLine.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置声音的大小
	 * 
	 * @param newVolume
	 * <br />
	 *            如果直接输入电压值, 范围为0.0 ~ 2.0<br />
	 *            如果输入为百分比, 范围为0.0 ~ 100.0<br />
	 * @param isPercent
	 * <br />
	 *            如果输入为百分比, 则此处为true
	 * @see FloatControl.Type.MASTER_GAIN
	 */
	public static void setVolume (Double newVolume, boolean isPercent) {
		if (isPercent) {
			// 将百分比转化到0.0~2.0
			volume = newVolume / 50.0;
		}
		// 这里有个分贝的计算公式 dB = 20lg(E/Er) E-实际测量电压值，Er-参考电压值
		// 所输入的newVolume如果表示电压值则为E/Er
		// 详细见FloatControl.Type.MASTER_GAIN的API文档……太多字了orz
		float dB = (float) (Math.log(volume == 0.0 ? 0.0001 : volume) / Math.log(10.0) * 20.0);
		floatControl.setValue(dB);
	}

	/**
	 * 暂停的方法<br />
	 * 其实就是很简单的一个调用，重点在于stop到底干了什么【其实stop根本就不是真正的停止<br />
	 * 我的看法是调用了stop之后让sourceDataLine不进行播放而已【详细见第二个蓝色条纹
	 */
	public void pause () {
		if (isPause) {
			sourceDataLine.stop();
		} else {
			sourceDataLine.start();
		}
		isPause = !isPause;
		// sourceDataLine.stop();
		// isPause = false;
	}

	/**
	 * 暂停后开始
	 * 
	 * @author cylong
	 * @version May 25, 2014 4:04:42 PM
	 */
	public void start () {
		sourceDataLine.start();
		isPause = true;
	}

	public static void main (String[] args) {
		final PlayMP3 musicPlay = new PlayMP3("sounds/sounds1/panel_game_bgm.mp3");
		musicPlay.setLoop(true);

		JFrame jFrame = new JFrame();
		jFrame.setSize(100, 80);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点击红叉退出
		jFrame.setResizable(false);// 不可改变大小
		jFrame.setLocationRelativeTo(null);// 界面居中【需要在setSize之后

		final JSlider slider1 = new JSlider(JSlider.HORIZONTAL, 0, 100, 80);
		// 加监听……【我喜欢用这种匿名内部类233【不解释了用着就是了233
		slider1.addChangeListener(new ChangeListener() {

			public void stateChanged (ChangeEvent e) {
				PlayMP3.setVolume((double) slider1.getValue(), true);
			}
		});
		jFrame.add(slider1, BorderLayout.NORTH);
		jFrame.setVisible(true);

		final JButton test = new JButton("click");
		test.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked (MouseEvent e) {
				musicPlay.pause();
			}
		});
		jFrame.add(test, BorderLayout.SOUTH);

		new Thread(musicPlay).start();
	}

}
