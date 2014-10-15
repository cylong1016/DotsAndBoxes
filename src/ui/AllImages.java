package ui;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

import data.DataConfig;

/**
 * ȫ��ͼƬ<br />
 * ��������뻹�ǰ�ͼƬ���ŵ�һ��ɡ�_��<br />
 * 
 * @author ������<br />
 * @since 2014 / 4 / 2 4:26 PM
 * 
 */
public class AllImages {
	/**
	 * Сwindow�ı���
	 */
	public static final Image WINDOW_BG = new ImageIcon(DataConfig.getSkin() + "panel_start/window_bg.png").getImage();
	/**
	 * ���Ͻǹرհ�ťͼƬ
	 */
	public static final Image WINDOW_EXIT = new ImageIcon(DataConfig.getSkin() + "frame/exit.png").getImage();
	public static final Image WINDOW_EXIT1 = new ImageIcon(DataConfig.getSkin() + "frame/exit1.png").getImage();
	/**
	 * ���Ͻ���С����ťͼƬ
	 */
	public static final Image WINDOW_MIN = new ImageIcon(DataConfig.getSkin() + "frame/min.png").getImage();
	public static final Image WINDOW_MIN1 = new ImageIcon(DataConfig.getSkin() + "frame/min1.png").getImage();
	/**
	 * �����
	 */
	public static final Image IMG_CURSOR = new ImageIcon(DataConfig.getSkin() + "frame/cursor.png").getImage();
	/**
	 * ��������ͼƬ��0-9��
	 */
	public static final Image IMG_NUM_SCORE = new ImageIcon(DataConfig.getSkin() + "panel_game/number_socre.png").getImage();
	/**
	 * ÿһ���������ֵĳ��Ϳ�
	 */
	public static final int IMG_NUM_SCORE_W = IMG_NUM_SCORE.getWidth(null) / 10;
	public static final int IMG_NUM_SCORE_H = IMG_NUM_SCORE.getHeight(null);
	/**
	 * ��Ϸ������������ͼƬ��0-9��
	 */
	public static final Image IMG_NUM_GAME_OVER = new ImageIcon(DataConfig.getSkin() + "panel_game/number_game_over.png").getImage();
	/**
	 * ��Ϸ����ÿһ���������ֵĳ��Ϳ�
	 */
	public static final int IMG_NUM_GAME_OVER_W = IMG_NUM_GAME_OVER.getWidth(null) / 10;
	public static final int IMG_NUM_GAME_OVER_H = IMG_NUM_GAME_OVER.getHeight(null);
	/**
	 * ��ʱ����ͼƬ��0-9��
	 */
	public static final Image IMG_NUM_COUNTDOWN = new ImageIcon(DataConfig.getSkin() + "panel_game/number_countdown.png").getImage();
	/**
	 * ÿһ����ʱ���ֵĳ��Ϳ�
	 */
	public static final int IMG_NUM_COUNTDOWN_W = IMG_NUM_COUNTDOWN.getWidth(null) / 10;
	public static final int IMG_NUM_COUNTDOWN_H = IMG_NUM_COUNTDOWN.getHeight(null);
	/**
	 * ��ʼ�����ļ�
	 */
	public static final File[] FILE_STARTS= getFiles(DataConfig.getSkin() + "panel_start/starts");
	/**
	 * ��ʼ����ͼƬ
	 */
	public static final Image[] IMG_STARTS = new Image[FILE_STARTS.length];
	/**
	 * ��Ϸ��ʼ�Ķ���ͼƬ
	 */
	public static final Image IMG_START_PRE = new ImageIcon(DataConfig.getSkin() + "panel_start/start_pre.png").getImage();
	/**
	 * ��ʼ�˵� dots and boxes��ͼƬ�ļ�
	 */
	public static final File[] FILE_DOTS_AND_BOXES = getFiles(DataConfig.getSkin() + "panel_start/dots_and_boxes");
	/**
	 * ��ʼ�˵� dots and boxes��ͼƬ
	 */
	public static final Image[] IMG_DOTS_AND_BOXES = new Image[FILE_DOTS_AND_BOXES.length];
	static {
		for (int i = 0; i < IMG_DOTS_AND_BOXES.length; i++) {
			IMG_DOTS_AND_BOXES[i] = new ImageIcon(FILE_DOTS_AND_BOXES[i].getPath()).getImage();
		}
	}
	/**
	 * ��ʼ�˵�������ͼƬ�ļ�
	 */
	public static final File[] FILE_ROBOTS = getFiles(DataConfig.getSkin() + "panel_start/robots");
	/**
	 * ��ʼ�˵�������ͼƬ
	 */
	public static final Image[] IMG_ROBOTS = new Image[FILE_ROBOTS.length];
	static {
		for (int i = 0; i < IMG_ROBOTS.length; i++) {
			IMG_ROBOTS[i] = new ImageIcon(FILE_ROBOTS[i].getPath()).getImage();
		}
	}
	/**
	 * ��ť�ı߿�
	 */
	public static final Image IMG_LABEL_BORDER = new ImageIcon(DataConfig.getSkin() + "panel_start/border.png").getImage();
	/**
	 * ��ʼ�˵�������ť
	 */
	public static final Image IMG_HELP = new ImageIcon(DataConfig.getSkin() + "panel_start/help.png").getImage();
	/**
	 * ��ʼ�˵����ð�ť
	 */
	public static final Image IMG_SET = new ImageIcon(DataConfig.getSkin() + "panel_start/set.png").getImage();
	
	/**
	 * ��ʼ�˵�����ͼƬ
	 */
	public static final Image IMG_PANEL_START_BG = new ImageIcon(DataConfig.getSkin() + "panel_start/game_start_bg.png").getImage();
	/**
	 * ��ʼ�˵�����ͼƬ�����ǡ�
	 */
	public static final Image IMG_PANEL_START_BG_MV = new ImageIcon(DataConfig.getSkin() + "panel_start/game_start_bg_mv.png").getImage();
	/**
	 * ��ʼ�˵�����ͼƬ���ġ�
	 */
	public static final Image IMG_PANEL_START_BG_STATIC = new ImageIcon(DataConfig.getSkin() + "panel_start/game_start_bg_static.png").getImage();
	/**
	 * ��Ϸ�˵���ʼ��ť��ͼƬ
	 */
	public static final Image IMG_START_GAME = new ImageIcon(DataConfig.getSkin() + "panel_start/start.png").getImage();
	/**
	 * ��Ϸ�˵�ģʽѡ�񱳾���ͼƬ
	 */
	public static final Image IMG_MODEL_CHOOSE = new ImageIcon(DataConfig.getSkin() + "panel_start/model_choose_bg.png").getImage();
	/**
	 * ��ʼ�˵�ѡ��ͷ��ͼƬ�ļ�
	 */
	public static final File[] FILE_HEADS = getFiles(DataConfig.getSkin() + "head");
	/**
	 * ��ʼ�˵�������ͼƬ
	 */
	public static final ImageIcon[] IMG_HEADS = new ImageIcon[FILE_HEADS.length];
	static {
		for (int i = 0; i < IMG_HEADS.length; i++) {
			IMG_HEADS[i] = new ImageIcon(FILE_HEADS[i].getPath());
		}
	}
	/**
	 * ��������ͼƬ�ļ�
	 */
	public static final File[] FILE_ABOUT_US = getFiles(DataConfig.getSkin() + "about_us");
	/**
	 * ��������ͼƬ
	 */
	public static final ImageIcon[] IMG_ABOUT_US = new ImageIcon[FILE_ABOUT_US.length];
	static {
		for (int i = 0; i < IMG_ABOUT_US.length; i++) {
			IMG_ABOUT_US[i] = new ImageIcon(FILE_ABOUT_US[i].getPath());
		}
	}
	/**
	 * ��ʼ�˵�ͼ��ͼƬ�ļ�
	 */
	public static final File[] FILE_ICON = getFiles(DataConfig.getSkin() + "panel_start/icon");
	/**
	 * ��ʼ�˵�ͼ��ͼƬ
	 */
	public static final Image[] IMG_ICON = new Image[FILE_ICON.length];
	static {
		for (int i = 0; i < IMG_ICON.length; i++) {
			IMG_ICON[i] = new ImageIcon(FILE_ICON[i].getPath()).getImage();
		}
	}
	/**
	 * �Ⱥ���ѡ���ͼƬ�ļ�����
	 */
	public static final File[] FILE_FIRST_SECOND = getFiles(DataConfig.getSkin() + "panel_start/first_second");
	/**
	 * �Ⱥ���ѡ���ͼƬ����
	 */
	public static final Image[] IMG_FIRST_SECOND = new Image[FILE_FIRST_SECOND.length];
	static {
		for (int i = 0; i < IMG_FIRST_SECOND.length; i++) {
			IMG_FIRST_SECOND[i] = new ImageIcon(FILE_FIRST_SECOND[i].getPath()).getImage();
		}
	}
	
	/**
	 * �Ѷ�ѡ���ͼƬ�ļ�����
	 */
	public static final File[] FILE_LEVEL_CHOOSE = getFiles(DataConfig.getSkin() + "panel_start/level");
	/**
	 * �Ѷ�ѡ���ͼƬ����
	 */
	public static final Image[] IMG_LEVEL_CHOOSE = new Image[FILE_LEVEL_CHOOSE.length];
	static {
		for (int i = 0; i < IMG_LEVEL_CHOOSE.length; i++) {
			IMG_LEVEL_CHOOSE[i] = new ImageIcon(FILE_LEVEL_CHOOSE[i].getPath()).getImage();
		}
	}
	
	/**
	 * ���̴�Сѡ���ͼƬ�ļ�����
	 */
	public static final File[] FILE_CHESS_BOARD_SIZE = getFiles(DataConfig.getSkin() + "panel_start/chess_board_size");
	/**
	 * ���̴�Сѡ���ͼƬ����
	 */
	public static final Image[] IMG_CHESS_BOARD_SIZE = new Image[FILE_CHESS_BOARD_SIZE.length];
	/**
	 * ����ѡ���ͼƬ�ı���
	 */
	public static final Image IMG_SETTING_BG = new ImageIcon(DataConfig.getSkin() + "panel_start/setting_bg.png").getImage();
	static {
		for (int i = 0; i < IMG_CHESS_BOARD_SIZE.length; i++) {
			IMG_CHESS_BOARD_SIZE[i] = new ImageIcon(FILE_CHESS_BOARD_SIZE[i].getPath()).getImage();
		}
	}
	/**
	 * ����ģʽ��ťͼƬ
	 */
	public static final Image IMG_SPECIAL_MODE = new ImageIcon(DataConfig.getSkin() + "panel_start/special_mode.png").getImage();
	/**
	 * ����ģʽ�мƷ�ģʽ��ť
	 */
	public static final Image IMG_SCORE = new ImageIcon(DataConfig.getSkin() + "panel_start/score.png").getImage();
	/**
	 * ������Ϸ��ťͼƬ
	 */
	public static final Image IMG_SINGLE_PLAYER = new ImageIcon(DataConfig.getSkin() + "panel_start/single_player.png").getImage();
	/**
	 * ˫����Ϸ��ťͼƬ
	 */
	public static final Image IMG_TWO_PLAYERS = new ImageIcon(DataConfig.getSkin() + "panel_start/two_players.png").getImage();
	/**
	 * ������ս��ťͼƬ
	 */
	public static final Image IMG_TWO_PLAYERS_CONNECT = new ImageIcon(DataConfig.getSkin() + "panel_start/two_players_connect.png").getImage();
	/**
	 * �ȴ���Ҽ���ͼƬ
	 */
	public static final Image IMG_WAIT = new ImageIcon(DataConfig.getSkin() + "panel_start/wait.png").getImage();
	/**
	 * IP�������ͼƬ
	 */
	public static final Image IMG_IP_ERROR = new ImageIcon(DataConfig.getSkin() + "panel_start/ip_error.png").getImage();
	/**
	 * �������䰴ťͼƬ
	 */
	public static final Image IMG_CREATE_HOME = new ImageIcon(DataConfig.getSkin() + "panel_start/create_home.png").getImage();
	/**
	 * ���뷿��ͼƬ
	 */
	public static final Image IMG_JOIN_HOME = new ImageIcon(DataConfig.getSkin() + "panel_start/join_home.png").getImage();
	/**
	 * �������ı���
	 */
	public static final Image IMG_CONNECT_BG = new ImageIcon(DataConfig.getSkin() + "panel_start/connect_bg.png").getImage();
	/**
	 * ����IP
	 */
	public static final Image IMG_LOCAL_IP = new ImageIcon(DataConfig.getSkin() + "panel_start/local_ip.png").getImage();
	/**
	 * ����IP
	 */
	public static final Image IMG_JOIN_IP = new ImageIcon(DataConfig.getSkin() + "panel_start/join_ip.png").getImage();
	/**
	 * ���1ͼƬ
	 */
	public static final Image IMG_PANEL_GAME_PLAYER1 = new ImageIcon(DataConfig.getSkin() + "panel_game/player1.png").getImage();
	/**
	 * ���2ͼƬ
	 */
	public static final Image IMG_PANEL_GAME_PLAYER2 = new ImageIcon(DataConfig.getSkin() + "panel_game/player2.png").getImage();
	/**
	 * ����ͼƬ
	 */
	public static final Image IMG_PANEL_GAME_SCORE = new ImageIcon(DataConfig.getSkin() + "panel_game/score.png").getImage();
	/**
	 * ������屳��ͼƬ�ļ�
	 */
	public static final File[] FILE_PANEL_GAME_BG = getFiles(DataConfig.getSkin() + "panel_game/background");
	/**
	 * ������屳��ͼƬ
	 */
	public static final Image[] IMG_PANEL_GAME_BG = new Image[FILE_PANEL_GAME_BG.length];
	/**
	 * �������߿�ͼƬ
	 */
	public static final Image IMG_PANEL_GAME_BORDER = new ImageIcon(DataConfig.getSkin() + "panel_game/border.png").getImage();
	/**
	 * ���������������ť
	 */
	public static final Image IMG_VOLUME_ON = new ImageIcon(DataConfig.getSkin() + "panel_game/volume_on.png").getImage();
	/**
	 * ������������ذ�ť
	 */
	public static final Image IMG_VOLUME_OFF = new ImageIcon(DataConfig.getSkin() + "panel_game/volume_off.png").getImage();
	/**
	 * ���������ͣ��ť
	 */
	public static final Image IMG_PAUSE = new ImageIcon(DataConfig.getSkin() + "panel_game/pause.png").getImage();
	/**
	 * ������忪ʼ��ť
	 */
	public static final Image IMG_START = new ImageIcon(DataConfig.getSkin() + "panel_game/start.png").getImage();
	/**
	 * �������˵�
	 */
	public static final Image IMG_MENU = new ImageIcon(DataConfig.getSkin() + "panel_game/menu.png").getImage();
	/**
	 * �������˵�����
	 */
	public static final Image IMG_MENU_BG = new ImageIcon(DataConfig.getSkin() + "panel_game/menu_background.png").getImage();
	/**
	 * ������屣�水ť
	 */
	public static final Image IMG_SAVE_CHESS = new ImageIcon(DataConfig.getSkin() + "panel_game/save_chess.png").getImage();
	public static final Image IMG_SAVE_CHESS1 = new ImageIcon(DataConfig.getSkin() + "panel_game/save_chess1.png").getImage();
	/**
	 * ��ȡ���Ȱ�ť
	 */
	public static final Image IMG_LOAD_CHESS = new ImageIcon(DataConfig.getSkin() + "panel_game/load_chess.png").getImage();
	public static final Image IMG_LOAD_CHESS1 = new ImageIcon(DataConfig.getSkin() + "panel_game/load_chess1.png").getImage();
	/**
	 * ����������¿�ʼ��ť
	 */
	public static final Image IMG_RESTART_GAME = new ImageIcon(DataConfig.getSkin() + "panel_game/restart_game.png").getImage();
	public static final Image IMG_RESTART_GAME1 = new ImageIcon(DataConfig.getSkin() + "panel_game/restart_game1.png").getImage();
	/**
	 * ����������˵���ť
	 */
	public static final Image IMG_MAIN_MENU = new ImageIcon(DataConfig.getSkin() + "panel_game/main_menu.png").getImage();
	public static final Image IMG_MAIN_MENU1 = new ImageIcon(DataConfig.getSkin() + "panel_game/main_menu1.png").getImage();
	/**
	 * ���������尴ť
	 */
	public static final Image IMG_TAKE_BACK = new ImageIcon(DataConfig.getSkin() + "panel_game/take_back.png").getImage();
	public static final Image IMG_TAKE_BACK1 = new ImageIcon(DataConfig.getSkin() + "panel_game/take_back1.png").getImage();
	/**
	 * ��������˳���ť
	 */
	public static final Image IMG_EXIT = new ImageIcon(DataConfig.getSkin() + "panel_game/exit.png").getImage();
	public static final Image IMG_EXIT1 = new ImageIcon(DataConfig.getSkin() + "panel_game/exit1.png").getImage();
	
	/**
	 * ���̵��ͼƬ
	 */
	public static final ImageIcon IMG_DOT = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/dot.png");
	/**
	 * ���һ�����ߵ�ͼƬ
	 */
	public static final ImageIcon IMG_HORIZONTA_P1_LINE = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/h_p1_line.png");
	/**
	 * ��Ҷ������ߵ�ͼƬ
	 */
	public static final ImageIcon IMG_HORIZONTAL_P2_LINE = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/h_p2_line.png");
	/**
	 * ���һ�����ߵ�ͼƬ
	 */
	public static final ImageIcon IMG_VERTICAL_P1_LINE = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/v_p1_line.png");
	/**
	 * ��Ҷ������ߵ�ͼƬ
	 */
	public static final ImageIcon IMG_VERTICAL_P2_LINE = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/v_p2_line.png");
	/**
	 * ���һ�����ڲ���ͼƬ
	 */
	public static final ImageIcon IMG_P1_INSIDE = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/p1_inside.png");
	/**
	 * ��Ҷ������ڲ���ͼƬ
	 */
	public static final ImageIcon IMG_P2_INSIDE = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/p2_inside.png");
	/**
	 * ����ƶ�������ʱ�������
	 */
	public static final ImageIcon IMG_HORIZONTA_NONE_LINE = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/h_none_line.png");
	/**
	 * ����ƶ�������ʱ�������
	 */
	public static final ImageIcon IMG_VERTICAL_NONE_LINE = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/v_none_line.png");
	/**
	 * ���̵ı���
	 */
	public static final Image IMG_CHESS_BOARD_BG = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/chess_board_bg.png").getImage();
	/**
	 * ��Ϸ�������ı���
	 */
	public static final Image IMG_GAME_OVER_BG = new ImageIcon(DataConfig.getSkin() + "panel_game/game_over_bg.png").getImage();
	/**
	 * ���1ʤ��ͼƬ
	 */
	public static final Image IMG_WINNER_PLAYER1 = new ImageIcon(DataConfig.getSkin() + "panel_game/winner_player1.png").getImage();
	/**
	 * ���2ʤ��ͼƬ
	 */
	public static final Image IMG_WINNER_PLAYER2 = new ImageIcon(DataConfig.getSkin() + "panel_game/winner_player2.png").getImage();

	/**
	 * �õ�һ���ļ����������ͼƬ�ļ�
	 * 
	 * @param filePath
	 *            ͼƬ���ڵ��ļ���
	 * @return File[]
	 */
	private static File[] getFiles(String filePath) {
		File file = new File(filePath);
		File[] files = file.listFiles();
		return files;
	}
}
