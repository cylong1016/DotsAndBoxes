package ui;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

import data.DataConfig;

/**
 * 全部图片<br />
 * 最后想了想还是把图片都放到一块吧→_→<br />
 * 
 * @author 陈云龙<br />
 * @since 2014 / 4 / 2 4:26 PM
 * 
 */
public class AllImages {
	/**
	 * 小window的背景
	 */
	public static final Image WINDOW_BG = new ImageIcon(DataConfig.getSkin() + "panel_start/window_bg.png").getImage();
	/**
	 * 右上角关闭按钮图片
	 */
	public static final Image WINDOW_EXIT = new ImageIcon(DataConfig.getSkin() + "frame/exit.png").getImage();
	public static final Image WINDOW_EXIT1 = new ImageIcon(DataConfig.getSkin() + "frame/exit1.png").getImage();
	/**
	 * 右上角最小化按钮图片
	 */
	public static final Image WINDOW_MIN = new ImageIcon(DataConfig.getSkin() + "frame/min.png").getImage();
	public static final Image WINDOW_MIN1 = new ImageIcon(DataConfig.getSkin() + "frame/min1.png").getImage();
	/**
	 * 鼠标光标
	 */
	public static final Image IMG_CURSOR = new ImageIcon(DataConfig.getSkin() + "frame/cursor.png").getImage();
	/**
	 * 分数数字图片（0-9）
	 */
	public static final Image IMG_NUM_SCORE = new ImageIcon(DataConfig.getSkin() + "panel_game/number_socre.png").getImage();
	/**
	 * 每一个分数数字的长和宽
	 */
	public static final int IMG_NUM_SCORE_W = IMG_NUM_SCORE.getWidth(null) / 10;
	public static final int IMG_NUM_SCORE_H = IMG_NUM_SCORE.getHeight(null);
	/**
	 * 游戏结束分数数字图片（0-9）
	 */
	public static final Image IMG_NUM_GAME_OVER = new ImageIcon(DataConfig.getSkin() + "panel_game/number_game_over.png").getImage();
	/**
	 * 游戏结束每一个分数数字的长和宽
	 */
	public static final int IMG_NUM_GAME_OVER_W = IMG_NUM_GAME_OVER.getWidth(null) / 10;
	public static final int IMG_NUM_GAME_OVER_H = IMG_NUM_GAME_OVER.getHeight(null);
	/**
	 * 计时数字图片（0-9）
	 */
	public static final Image IMG_NUM_COUNTDOWN = new ImageIcon(DataConfig.getSkin() + "panel_game/number_countdown.png").getImage();
	/**
	 * 每一个计时数字的长和宽
	 */
	public static final int IMG_NUM_COUNTDOWN_W = IMG_NUM_COUNTDOWN.getWidth(null) / 10;
	public static final int IMG_NUM_COUNTDOWN_H = IMG_NUM_COUNTDOWN.getHeight(null);
	/**
	 * 开始动画文件
	 */
	public static final File[] FILE_STARTS= getFiles(DataConfig.getSkin() + "panel_start/starts");
	/**
	 * 开始动画图片
	 */
	public static final Image[] IMG_STARTS = new Image[FILE_STARTS.length];
	/**
	 * 游戏开始的动画图片
	 */
	public static final Image IMG_START_PRE = new ImageIcon(DataConfig.getSkin() + "panel_start/start_pre.png").getImage();
	/**
	 * 开始菜单 dots and boxes的图片文件
	 */
	public static final File[] FILE_DOTS_AND_BOXES = getFiles(DataConfig.getSkin() + "panel_start/dots_and_boxes");
	/**
	 * 开始菜单 dots and boxes的图片
	 */
	public static final Image[] IMG_DOTS_AND_BOXES = new Image[FILE_DOTS_AND_BOXES.length];
	static {
		for (int i = 0; i < IMG_DOTS_AND_BOXES.length; i++) {
			IMG_DOTS_AND_BOXES[i] = new ImageIcon(FILE_DOTS_AND_BOXES[i].getPath()).getImage();
		}
	}
	/**
	 * 开始菜单机器人图片文件
	 */
	public static final File[] FILE_ROBOTS = getFiles(DataConfig.getSkin() + "panel_start/robots");
	/**
	 * 开始菜单机器人图片
	 */
	public static final Image[] IMG_ROBOTS = new Image[FILE_ROBOTS.length];
	static {
		for (int i = 0; i < IMG_ROBOTS.length; i++) {
			IMG_ROBOTS[i] = new ImageIcon(FILE_ROBOTS[i].getPath()).getImage();
		}
	}
	/**
	 * 按钮的边框
	 */
	public static final Image IMG_LABEL_BORDER = new ImageIcon(DataConfig.getSkin() + "panel_start/border.png").getImage();
	/**
	 * 开始菜单帮助按钮
	 */
	public static final Image IMG_HELP = new ImageIcon(DataConfig.getSkin() + "panel_start/help.png").getImage();
	/**
	 * 开始菜单设置按钮
	 */
	public static final Image IMG_SET = new ImageIcon(DataConfig.getSkin() + "panel_start/set.png").getImage();
	
	/**
	 * 开始菜单背景图片
	 */
	public static final Image IMG_PANEL_START_BG = new ImageIcon(DataConfig.getSkin() + "panel_start/game_start_bg.png").getImage();
	/**
	 * 开始菜单背景图片【星星】
	 */
	public static final Image IMG_PANEL_START_BG_MV = new ImageIcon(DataConfig.getSkin() + "panel_start/game_start_bg_mv.png").getImage();
	/**
	 * 开始菜单背景图片【心】
	 */
	public static final Image IMG_PANEL_START_BG_STATIC = new ImageIcon(DataConfig.getSkin() + "panel_start/game_start_bg_static.png").getImage();
	/**
	 * 游戏菜单开始按钮的图片
	 */
	public static final Image IMG_START_GAME = new ImageIcon(DataConfig.getSkin() + "panel_start/start.png").getImage();
	/**
	 * 游戏菜单模式选择背景的图片
	 */
	public static final Image IMG_MODEL_CHOOSE = new ImageIcon(DataConfig.getSkin() + "panel_start/model_choose_bg.png").getImage();
	/**
	 * 开始菜单选择头像图片文件
	 */
	public static final File[] FILE_HEADS = getFiles(DataConfig.getSkin() + "head");
	/**
	 * 开始菜单机器人图片
	 */
	public static final ImageIcon[] IMG_HEADS = new ImageIcon[FILE_HEADS.length];
	static {
		for (int i = 0; i < IMG_HEADS.length; i++) {
			IMG_HEADS[i] = new ImageIcon(FILE_HEADS[i].getPath());
		}
	}
	/**
	 * 关于我们图片文件
	 */
	public static final File[] FILE_ABOUT_US = getFiles(DataConfig.getSkin() + "about_us");
	/**
	 * 关于我们图片
	 */
	public static final ImageIcon[] IMG_ABOUT_US = new ImageIcon[FILE_ABOUT_US.length];
	static {
		for (int i = 0; i < IMG_ABOUT_US.length; i++) {
			IMG_ABOUT_US[i] = new ImageIcon(FILE_ABOUT_US[i].getPath());
		}
	}
	/**
	 * 开始菜单图标图片文件
	 */
	public static final File[] FILE_ICON = getFiles(DataConfig.getSkin() + "panel_start/icon");
	/**
	 * 开始菜单图标图片
	 */
	public static final Image[] IMG_ICON = new Image[FILE_ICON.length];
	static {
		for (int i = 0; i < IMG_ICON.length; i++) {
			IMG_ICON[i] = new ImageIcon(FILE_ICON[i].getPath()).getImage();
		}
	}
	/**
	 * 先后手选择的图片文件数组
	 */
	public static final File[] FILE_FIRST_SECOND = getFiles(DataConfig.getSkin() + "panel_start/first_second");
	/**
	 * 先后手选择的图片数组
	 */
	public static final Image[] IMG_FIRST_SECOND = new Image[FILE_FIRST_SECOND.length];
	static {
		for (int i = 0; i < IMG_FIRST_SECOND.length; i++) {
			IMG_FIRST_SECOND[i] = new ImageIcon(FILE_FIRST_SECOND[i].getPath()).getImage();
		}
	}
	
	/**
	 * 难度选择的图片文件数组
	 */
	public static final File[] FILE_LEVEL_CHOOSE = getFiles(DataConfig.getSkin() + "panel_start/level");
	/**
	 * 难度选择的图片数组
	 */
	public static final Image[] IMG_LEVEL_CHOOSE = new Image[FILE_LEVEL_CHOOSE.length];
	static {
		for (int i = 0; i < IMG_LEVEL_CHOOSE.length; i++) {
			IMG_LEVEL_CHOOSE[i] = new ImageIcon(FILE_LEVEL_CHOOSE[i].getPath()).getImage();
		}
	}
	
	/**
	 * 棋盘大小选择的图片文件数组
	 */
	public static final File[] FILE_CHESS_BOARD_SIZE = getFiles(DataConfig.getSkin() + "panel_start/chess_board_size");
	/**
	 * 棋盘大小选择的图片数组
	 */
	public static final Image[] IMG_CHESS_BOARD_SIZE = new Image[FILE_CHESS_BOARD_SIZE.length];
	/**
	 * 设置选择的图片的背景
	 */
	public static final Image IMG_SETTING_BG = new ImageIcon(DataConfig.getSkin() + "panel_start/setting_bg.png").getImage();
	static {
		for (int i = 0; i < IMG_CHESS_BOARD_SIZE.length; i++) {
			IMG_CHESS_BOARD_SIZE[i] = new ImageIcon(FILE_CHESS_BOARD_SIZE[i].getPath()).getImage();
		}
	}
	/**
	 * 特殊模式按钮图片
	 */
	public static final Image IMG_SPECIAL_MODE = new ImageIcon(DataConfig.getSkin() + "panel_start/special_mode.png").getImage();
	/**
	 * 特殊模式中计分模式按钮
	 */
	public static final Image IMG_SCORE = new ImageIcon(DataConfig.getSkin() + "panel_start/score.png").getImage();
	/**
	 * 单人游戏按钮图片
	 */
	public static final Image IMG_SINGLE_PLAYER = new ImageIcon(DataConfig.getSkin() + "panel_start/single_player.png").getImage();
	/**
	 * 双人游戏按钮图片
	 */
	public static final Image IMG_TWO_PLAYERS = new ImageIcon(DataConfig.getSkin() + "panel_start/two_players.png").getImage();
	/**
	 * 联机对战按钮图片
	 */
	public static final Image IMG_TWO_PLAYERS_CONNECT = new ImageIcon(DataConfig.getSkin() + "panel_start/two_players_connect.png").getImage();
	/**
	 * 等待玩家加入图片
	 */
	public static final Image IMG_WAIT = new ImageIcon(DataConfig.getSkin() + "panel_start/wait.png").getImage();
	/**
	 * IP输入错误图片
	 */
	public static final Image IMG_IP_ERROR = new ImageIcon(DataConfig.getSkin() + "panel_start/ip_error.png").getImage();
	/**
	 * 创建房间按钮图片
	 */
	public static final Image IMG_CREATE_HOME = new ImageIcon(DataConfig.getSkin() + "panel_start/create_home.png").getImage();
	/**
	 * 加入房间图片
	 */
	public static final Image IMG_JOIN_HOME = new ImageIcon(DataConfig.getSkin() + "panel_start/join_home.png").getImage();
	/**
	 * 联机面板的背景
	 */
	public static final Image IMG_CONNECT_BG = new ImageIcon(DataConfig.getSkin() + "panel_start/connect_bg.png").getImage();
	/**
	 * 本机IP
	 */
	public static final Image IMG_LOCAL_IP = new ImageIcon(DataConfig.getSkin() + "panel_start/local_ip.png").getImage();
	/**
	 * 加入IP
	 */
	public static final Image IMG_JOIN_IP = new ImageIcon(DataConfig.getSkin() + "panel_start/join_ip.png").getImage();
	/**
	 * 玩家1图片
	 */
	public static final Image IMG_PANEL_GAME_PLAYER1 = new ImageIcon(DataConfig.getSkin() + "panel_game/player1.png").getImage();
	/**
	 * 玩家2图片
	 */
	public static final Image IMG_PANEL_GAME_PLAYER2 = new ImageIcon(DataConfig.getSkin() + "panel_game/player2.png").getImage();
	/**
	 * 分数图片
	 */
	public static final Image IMG_PANEL_GAME_SCORE = new ImageIcon(DataConfig.getSkin() + "panel_game/score.png").getImage();
	/**
	 * 下棋面板背景图片文件
	 */
	public static final File[] FILE_PANEL_GAME_BG = getFiles(DataConfig.getSkin() + "panel_game/background");
	/**
	 * 下棋面板背景图片
	 */
	public static final Image[] IMG_PANEL_GAME_BG = new Image[FILE_PANEL_GAME_BG.length];
	/**
	 * 下棋面板边框图片
	 */
	public static final Image IMG_PANEL_GAME_BORDER = new ImageIcon(DataConfig.getSkin() + "panel_game/border.png").getImage();
	/**
	 * 下棋面板声音开按钮
	 */
	public static final Image IMG_VOLUME_ON = new ImageIcon(DataConfig.getSkin() + "panel_game/volume_on.png").getImage();
	/**
	 * 下棋面板声音关按钮
	 */
	public static final Image IMG_VOLUME_OFF = new ImageIcon(DataConfig.getSkin() + "panel_game/volume_off.png").getImage();
	/**
	 * 下棋面板暂停按钮
	 */
	public static final Image IMG_PAUSE = new ImageIcon(DataConfig.getSkin() + "panel_game/pause.png").getImage();
	/**
	 * 下棋面板开始按钮
	 */
	public static final Image IMG_START = new ImageIcon(DataConfig.getSkin() + "panel_game/start.png").getImage();
	/**
	 * 下棋面板菜单
	 */
	public static final Image IMG_MENU = new ImageIcon(DataConfig.getSkin() + "panel_game/menu.png").getImage();
	/**
	 * 下棋面板菜单背景
	 */
	public static final Image IMG_MENU_BG = new ImageIcon(DataConfig.getSkin() + "panel_game/menu_background.png").getImage();
	/**
	 * 下棋面板保存按钮
	 */
	public static final Image IMG_SAVE_CHESS = new ImageIcon(DataConfig.getSkin() + "panel_game/save_chess.png").getImage();
	public static final Image IMG_SAVE_CHESS1 = new ImageIcon(DataConfig.getSkin() + "panel_game/save_chess1.png").getImage();
	/**
	 * 读取进度按钮
	 */
	public static final Image IMG_LOAD_CHESS = new ImageIcon(DataConfig.getSkin() + "panel_game/load_chess.png").getImage();
	public static final Image IMG_LOAD_CHESS1 = new ImageIcon(DataConfig.getSkin() + "panel_game/load_chess1.png").getImage();
	/**
	 * 下棋面板重新开始按钮
	 */
	public static final Image IMG_RESTART_GAME = new ImageIcon(DataConfig.getSkin() + "panel_game/restart_game.png").getImage();
	public static final Image IMG_RESTART_GAME1 = new ImageIcon(DataConfig.getSkin() + "panel_game/restart_game1.png").getImage();
	/**
	 * 下棋面板主菜单按钮
	 */
	public static final Image IMG_MAIN_MENU = new ImageIcon(DataConfig.getSkin() + "panel_game/main_menu.png").getImage();
	public static final Image IMG_MAIN_MENU1 = new ImageIcon(DataConfig.getSkin() + "panel_game/main_menu1.png").getImage();
	/**
	 * 下棋面板悔棋按钮
	 */
	public static final Image IMG_TAKE_BACK = new ImageIcon(DataConfig.getSkin() + "panel_game/take_back.png").getImage();
	public static final Image IMG_TAKE_BACK1 = new ImageIcon(DataConfig.getSkin() + "panel_game/take_back1.png").getImage();
	/**
	 * 下棋面板退出按钮
	 */
	public static final Image IMG_EXIT = new ImageIcon(DataConfig.getSkin() + "panel_game/exit.png").getImage();
	public static final Image IMG_EXIT1 = new ImageIcon(DataConfig.getSkin() + "panel_game/exit1.png").getImage();
	
	/**
	 * 棋盘点的图片
	 */
	public static final ImageIcon IMG_DOT = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/dot.png");
	/**
	 * 玩家一横向线的图片
	 */
	public static final ImageIcon IMG_HORIZONTA_P1_LINE = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/h_p1_line.png");
	/**
	 * 玩家二横向线的图片
	 */
	public static final ImageIcon IMG_HORIZONTAL_P2_LINE = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/h_p2_line.png");
	/**
	 * 玩家一纵向线的图片
	 */
	public static final ImageIcon IMG_VERTICAL_P1_LINE = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/v_p1_line.png");
	/**
	 * 玩家二纵向线的图片
	 */
	public static final ImageIcon IMG_VERTICAL_P2_LINE = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/v_p2_line.png");
	/**
	 * 玩家一方块内部的图片
	 */
	public static final ImageIcon IMG_P1_INSIDE = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/p1_inside.png");
	/**
	 * 玩家二方块内部的图片
	 */
	public static final ImageIcon IMG_P2_INSIDE = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/p2_inside.png");
	/**
	 * 鼠标移动到线上时横向的线
	 */
	public static final ImageIcon IMG_HORIZONTA_NONE_LINE = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/h_none_line.png");
	/**
	 * 鼠标移动到线上时纵向的线
	 */
	public static final ImageIcon IMG_VERTICAL_NONE_LINE = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/v_none_line.png");
	/**
	 * 棋盘的背景
	 */
	public static final Image IMG_CHESS_BOARD_BG = new ImageIcon(DataConfig.getSkin() + "panel_game/chess_board/chess_board_bg.png").getImage();
	/**
	 * 游戏结束面板的背景
	 */
	public static final Image IMG_GAME_OVER_BG = new ImageIcon(DataConfig.getSkin() + "panel_game/game_over_bg.png").getImage();
	/**
	 * 玩家1胜利图片
	 */
	public static final Image IMG_WINNER_PLAYER1 = new ImageIcon(DataConfig.getSkin() + "panel_game/winner_player1.png").getImage();
	/**
	 * 玩家2胜利图片
	 */
	public static final Image IMG_WINNER_PLAYER2 = new ImageIcon(DataConfig.getSkin() + "panel_game/winner_player2.png").getImage();

	/**
	 * 得到一个文件夹里的所有图片文件
	 * 
	 * @param filePath
	 *            图片所在的文件夹
	 * @return File[]
	 */
	private static File[] getFiles(String filePath) {
		File file = new File(filePath);
		File[] files = file.listFiles();
		return files;
	}
}
