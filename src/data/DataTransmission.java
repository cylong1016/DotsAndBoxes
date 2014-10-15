package data;

/**
 * 数据保存和读取
 * 
 * @author 陈云龙
 * @since 2014 / 4 / 12 11:02 PM
 */
public interface DataTransmission {

	/**
	 * 保存数据
	 * 
	 * @param dataGame
	 * @param i
	 *            第几个记录文件
	 */
	void saveData(DataGame dataGame, int i);

	/**
	 * 读取数据
	 * 
	 * @param i
	 *            第几个记录文件
	 * @return
	 */
	DataGame loadData(int i);
}
