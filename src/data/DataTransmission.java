package data;

/**
 * ���ݱ���Ͷ�ȡ
 * 
 * @author ������
 * @since 2014 / 4 / 12 11:02 PM
 */
public interface DataTransmission {

	/**
	 * ��������
	 * 
	 * @param dataGame
	 * @param i
	 *            �ڼ�����¼�ļ�
	 */
	void saveData(DataGame dataGame, int i);

	/**
	 * ��ȡ����
	 * 
	 * @param i
	 *            �ڼ�����¼�ļ�
	 * @return
	 */
	DataGame loadData(int i);
}
