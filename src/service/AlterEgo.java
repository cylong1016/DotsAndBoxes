package service;

import ui.label.Line;

/**
 * @author 徐缘【神级人物→_→】
 * @version Apr 7, 2014<br>
 *          写的好棒~~~
 * 
 * @version May 20, 2014 5:58:42 PM <br>
 *          修复BUG
 *          <ol>
 *          <li>fix环形的问题</li>
 *          <li>fix做出牺牲的时候，总是牺牲最多格子的问题</li>
 *          <li>fix不划线的问题</li>
 *          </ol>
 * 
 * @version May 20, 2014 6:59:06 PM <br>
 *          不知道哪里又加强了→_→
 * 
 */
public class AlterEgo {

	public static Line play (Line[] lines, int level) {
		AlterEgo.Set(lines);
		AlterEgo.play_switch(AlterEgo.getPart(), level);
		Line line = AlterEgo.Submit(lines);
		return line;
	}

	private static int[] Lines;
	private static int nLine;
	private static int size;
	private static int Line[][][];
	private static int Block[][];

	/**
	 * Initialize AlterEgo
	 */
	private static void Set (Line[] lines) {
		AlterEgo.nLine = lines.length;

		AlterEgo.Lines = new int[AlterEgo.nLine];
		for (int i = 0; i < AlterEgo.nLine; i++) {
			if (lines[i].isClicked()) {
				AlterEgo.Lines[i] = 1;
			} else {
				AlterEgo.Lines[i] = 0;
			}
		}

		int emptyLine = 0;
		for (int i = 0; i < AlterEgo.nLine; i++) {
			if (AlterEgo.Lines[i] == 0) {
				emptyLine++;
			}
		}
		if (emptyLine == 0) {
			return;
		}
		AlterEgo.size = (int) (Math.sqrt(AlterEgo.nLine / 2));

		AlterEgo.newLine();
		AlterEgo.setLine();

		AlterEgo.newBlock();
		AlterEgo.setBlock();
	}

	private static Line Submit (Line[] lines) {
		AlterEgo.setLineset();
		for (int i = 0; i < AlterEgo.nLine; i++) {
			if (AlterEgo.Lines[i] == 1 && lines[i].isClicked() == false) {
				lines[i].setLineColor(2);
				return lines[i];
			}
		}
		return null;
	}

	/**
	 * create Line
	 */
	private static void newLine () {
		AlterEgo.Line = new int[2][][];
		AlterEgo.Line[0] = new int[AlterEgo.size + 1][AlterEgo.size];
		AlterEgo.Line[1] = new int[AlterEgo.size][AlterEgo.size + 1];
	}

	/**
	 * Lines-->Line
	 */
	private static void setLine () {
		int i = 0;
		for (int j = 0; j < AlterEgo.size + 1; j++) {
			for (int k = 0; k < AlterEgo.size; k++) {
				AlterEgo.Line[0][j][k] = AlterEgo.Lines[i];
				i++;
			}
		}
		for (int j = 0; j < AlterEgo.size; j++) {
			for (int k = 0; k < AlterEgo.size + 1; k++) {
				AlterEgo.Line[1][j][k] = AlterEgo.Lines[i];
				i++;
			}
		}
	}

	/**
	 * Line-->Lines
	 */
	private static void setLineset () {
		int i = 0;
		for (int j = 0; j < AlterEgo.size + 1; j++) {
			for (int k = 0; k < AlterEgo.size; k++) {
				AlterEgo.Lines[i] = AlterEgo.Line[0][j][k];
				i++;
			}
		}
		for (int j = 0; j < AlterEgo.size; j++) {
			for (int k = 0; k < AlterEgo.size + 1; k++) {
				AlterEgo.Lines[i] = AlterEgo.Line[1][j][k];
				i++;
			}
		}
	}

	private static void newBlock () {
		AlterEgo.Block = new int[AlterEgo.size][AlterEgo.size];
	}

	/**
	 * Line-->Block
	 */
	private static void setBlock () {
		for (int i = 0; i < AlterEgo.size; i++) {
			for (int j = 0; j < AlterEgo.size; j++) {
				AlterEgo.Block[i][j] = 0;
				if (AlterEgo.Line[0][i][j] == 0) {
					AlterEgo.Block[i][j]++;
				}
				if (AlterEgo.Line[0][i + 1][j] == 0) {
					AlterEgo.Block[i][j]++;
				}
				if (AlterEgo.Line[1][i][j] == 0) {
					AlterEgo.Block[i][j]++;
				}
				if (AlterEgo.Line[1][i][j + 1] == 0) {
					AlterEgo.Block[i][j]++;
				}
			}
		}
	}

	private static void draw (int x, int y, int z) {
		AlterEgo.Line[x][y][z] = 1;
		AlterEgo.setBlock();
	}

	private static void draw (int id) {
		int x = id / (AlterEgo.nLine / 2);
		int y;
		int z;
		if (x == 0) {
			y = id / AlterEgo.size;
			z = id % AlterEgo.size;
		} else {
			id -= AlterEgo.nLine / 2;
			y = id / (AlterEgo.size + 1);
			z = id % (AlterEgo.size + 1);
		}
		AlterEgo.draw(x, y, z);
	}

	private static void draw (int i, int j) {
		if (AlterEgo.Line[0][i][j] == 0) {
			AlterEgo.draw(0, i, j);
		}
		if (AlterEgo.Line[1][i][j] == 0) {
			AlterEgo.draw(1, i, j);
		}
		if (AlterEgo.Line[0][i + 1][j] == 0) {
			AlterEgo.draw(0, i + 1, j);
		}
		if (AlterEgo.Line[1][i][j + 1] == 0) {
			AlterEgo.draw(1, i, j + 1);
		}
	}

	private static void clear () {
		AlterEgo.setLine();
		AlterEgo.setBlock();
	}

	private static int nBlock (int value_of_Block) {
		AlterEgo.setBlock();
		int n = 0;
		for (int i = 0; i < AlterEgo.size; i++) {
			for (int j = 0; j < AlterEgo.size; j++) {
				if (AlterEgo.Block[i][j] == value_of_Block) {
					n++;
				}
			}
		}
		return n;
	}

	/**
	 * <pre>
	 * judge Part
	 * Part0: BugPart. Exist nSpareLine,and nBlock(1)>0;
	 * 		--0:Rnd;
	 * 		--1:GetSquare(=3-1);
	 * Part1: The Beginning. nSpareLine>0;
	 * 		--0:Rnd;
	 * 		--1:Avoid the opponent to enter Part0;
	 * 		--2:SuperHighSchoolLevel Judgment(Contemporary the same as 1-1);
	 * Part2: Should make a sacrifice. nBlock(1)==0;
	 * 		--0:Rnd;
	 * 		--1:Use the least sacrifice;
	 * 		--2:Study to Use Counter;
	 * Part3: Catch the block (with strategy);
	 * 		--0:Rnd;
	 * 		--1:GetSquare;
	 * 		--2:GetSquare,Use Sacrifice;
	 * 		--3:GetSquare,Use Counter.
	 * </pre>
	 */
	private static int getPart () {
		int nSpareLine = AlterEgo.getnSpareLine();
		int nBlock1 = AlterEgo.nBlock(1);

		if (nSpareLine > 0) {
			if (nBlock1 > 0) {
				return 0;
			} else {
				return 1;
			}
		} else {
			if (nBlock1 == 0) {
				return 2;
			} else {
				return 3;
			}
		}
	}

	private static boolean isSpare (int n) {
		int i = n / (AlterEgo.nLine / 2);
		int j;
		int k;
		if (i == 0) {
			j = n / AlterEgo.size;
			k = n % AlterEgo.size;
		} else {
			n -= AlterEgo.nLine / 2;
			j = n / (AlterEgo.size + 1);
			k = n % (AlterEgo.size + 1);
		}
		return AlterEgo.isSpare(i, j, k);
	}

	private static boolean isSpare (int i, int j, int k) {
		if (i == 0) {
			if (AlterEgo.Line[0][j][k] == 1) {
				return false;
			}
			if (j != AlterEgo.size) {
				if (AlterEgo.Line[0][j + 1][k] + AlterEgo.Line[1][j][k] + AlterEgo.Line[1][j][k + 1] >= 2) {
					return false;
				}
			}
			if (j != 0) {
				if (AlterEgo.Line[0][j - 1][k] + AlterEgo.Line[1][j - 1][k] + AlterEgo.Line[1][j - 1][k + 1] >= 2) {
					return false;
				}
			}
			return true;
		} else // if(i==1)
		{
			if (AlterEgo.Line[1][j][k] == 1) {
				return false;
			}
			if (k != AlterEgo.size) {
				if (AlterEgo.Line[0][j][k] + AlterEgo.Line[0][j + 1][k] + AlterEgo.Line[1][j][k + 1] >= 2) {
					return false;
				}
			}
			if (k != 0) {
				if (AlterEgo.Line[0][j][k - 1] + AlterEgo.Line[0][j + 1][k - 1] + AlterEgo.Line[1][j][k - 1] >= 2) {
					return false;
				}
			}
			return true;
		}
	}

	private static int getnSpareLine () {
		int nSpareLine = 0;

		for (int j = 0; j < AlterEgo.size + 1; j++) {
			for (int k = 0; k < AlterEgo.size; k++) {
				if (AlterEgo.Line[0][j][k] == 1) {
					continue;
				}
				if (j != AlterEgo.size) {
					if (AlterEgo.Line[0][j + 1][k] + AlterEgo.Line[1][j][k] + AlterEgo.Line[1][j][k + 1] >= 2) {
						continue;
					}
				}
				if (j != 0) {
					if (AlterEgo.Line[0][j - 1][k] + AlterEgo.Line[1][j - 1][k] + AlterEgo.Line[1][j - 1][k + 1] >= 2) {
						continue;
					}
				}
				nSpareLine++;
			}
		}

		for (int j = 0; j < AlterEgo.size; j++) {
			for (int k = 0; k < AlterEgo.size + 1; k++) {
				if (AlterEgo.Line[1][j][k] == 1) {
					continue;
				}
				if (k != AlterEgo.size) {
					if (AlterEgo.Line[0][j][k] + AlterEgo.Line[0][j + 1][k] + AlterEgo.Line[1][j][k + 1] >= 2) {
						continue;
					}
				}
				if (k != 0) {
					if (AlterEgo.Line[0][j][k - 1] + AlterEgo.Line[0][j + 1][k - 1] + AlterEgo.Line[1][j][k - 1] >= 2) {
						continue;
					}
				}
				nSpareLine++;
			}
		}
		return nSpareLine;
	}

	private static int HeadOfRoute (int Blocki, int Blockj) {
		if (AlterEgo.Block[Blocki][Blockj] != 1) {
			return 0;
		}

		int i = Blocki, j = Blockj;
		int n = 1;

		while (true) {
			if (AlterEgo.Block[i][j] == 0) {
				AlterEgo.clear();
				return -n;
			}
			if (AlterEgo.Block[i][j] >= 2) {
				AlterEgo.clear();
				return n - 1;
			}
			if (AlterEgo.Line[0][i][j] == 0) {
				if (i == 0) {
					AlterEgo.clear();
					return n;
				}
				AlterEgo.draw(0, i, j);
				i--;
			} else if (AlterEgo.Line[0][i + 1][j] == 0) {
				if (i == AlterEgo.size - 1) {
					AlterEgo.clear();
					return n;
				}
				AlterEgo.draw(0, i + 1, j);
				i++;
			} else if (AlterEgo.Line[1][i][j] == 0) {
				if (j == 0) {
					AlterEgo.clear();
					return n;
				}
				AlterEgo.draw(1, i, j);
				j--;
			} else // if(Line[1][i][j+1]==0)
			{
				if (j == AlterEgo.size - 1) {
					AlterEgo.clear();
					return n;
				}
				AlterEgo.draw(1, i, j + 1);
				j++;
			}
			n++;
		}
	}

	private static int SacrificeofLine (int n) {
		int i = n / (AlterEgo.nLine / 2);
		int j;
		int k;
		if (i == 0) {
			j = n / AlterEgo.size;
			k = n % AlterEgo.size;
		} else {
			n -= AlterEgo.nLine / 2;
			j = n / (AlterEgo.size + 1);
			k = n % (AlterEgo.size + 1);
		}
		return AlterEgo.SacrificeofLine(i, j, k);
	}

	private static int SacrificeofLine (int i, int j, int k) {
		if (AlterEgo.Line[i][j][k] == 1) {
			return 0;
		}

		int m = 0, n = 0;
		if (i == 0) {
			AlterEgo.draw(i, j, k);
			if (j != 0) {
				m = AlterEgo.HeadOfRoute(j - 1, k);
			}
			AlterEgo.clear();
			AlterEgo.draw(i, j, k);
			if (j != AlterEgo.size) {
				n = AlterEgo.HeadOfRoute(j, k);
			}
		} else {
			AlterEgo.draw(i, j, k);
			if (k != 0) {
				m = AlterEgo.HeadOfRoute(j, k - 1);
			}
			AlterEgo.clear();
			AlterEgo.draw(i, j, k);
			if (k != AlterEgo.size) {
				n = AlterEgo.HeadOfRoute(j, k);
			}
		}
		AlterEgo.clear();
		if (m == 0 && n == 0) {
			return 0;
		}
		if (m >= -3 && m <= 1 && n >= -3 && n <= 1) {
			return -(Math.abs(m) + Math.abs(n));
		} else {
			return (Math.abs(m) + Math.abs(n));
		}
	}

	private static int nCounter () {
		int nc = 0;

		int a[] = new int[AlterEgo.nLine];
		int s = 0;

		for (int k = 0; k < AlterEgo.nLine; k++) {
			AlterEgo.play_Part_3_1();
		}

		for (int i = 0; i < AlterEgo.nLine; i++) {
			if (AlterEgo.Lines[i] == 1) {
				continue;
			}
			if (AlterEgo.SacrificeofLine(i) < 0) {
				AlterEgo.Lines[i] = 1;
				AlterEgo.setLine();
				AlterEgo.setBlock();
				a[s] = i;
				s++;
				nc++;
			}
			for (int k = 0; k < AlterEgo.nLine; k++) {
				AlterEgo.play_Part_3_1();
			}
			for (int j = 0; j < s; j++) {
				AlterEgo.draw(a[j]);
			}
		}
		for (int i = 0; i < s; i++) {
			AlterEgo.Lines[a[i]] = 0;
		}
		AlterEgo.setLine();
		AlterEgo.setBlock();
		AlterEgo.clear();
		return nc;
	}

	private static void play_switch (int Part, int level) {
		switch (level) {
		case 0:
			if (Part == 0) {
				AlterEgo.play_Part_0_0();
			}
			if (Part == 1) {
				AlterEgo.play_Part_1_0();
			}
			if (Part == 2) {
				AlterEgo.play_Part_2_0();
			}
			if (Part == 3) {
				AlterEgo.play_Part_3_0();
			}
			break;
		case 1:
			if (Part == 0) {
				AlterEgo.play_Part_0_1();
			}
			if (Part == 1) {
				AlterEgo.play_Part_1_1();
			}
			if (Part == 2) {
				AlterEgo.play_Part_2_0();
			}
			if (Part == 3) {
				AlterEgo.play_Part_3_1();
			}
			break;
		case 2:
			if (Part == 0) {
				AlterEgo.play_Part_0_1();
			}
			if (Part == 1) {
				AlterEgo.play_Part_1_1();
			}
			if (Part == 2) {
				AlterEgo.play_Part_2_1();
			}
			if (Part == 3) {
				AlterEgo.play_Part_3_2();
			}
			break;
		case 3:
			if (Part == 0) {
				AlterEgo.play_Part_0_1();
			}
			if (Part == 1) {
				AlterEgo.play_Part_1_2();
			}
			if (Part == 2) {
				AlterEgo.play_Part_2_2();
			}
			if (Part == 3) {
				AlterEgo.play_Part_3_3();
			}
			break;
		}
	}

	private static void play_Part_0_0 () {
		while (true) {
			int n = (int) (Math.random() * AlterEgo.nLine);
			if (AlterEgo.Lines[n] != 0) {
				continue;
			}
			AlterEgo.draw(n);
			break;
		}
	}

	private static void play_Part_0_1 () {
		AlterEgo.play_Part_3_1();
	}

	private static void play_Part_1_0 () {
		AlterEgo.play_Part_0_0();
	}

	private static void play_Part_1_1 () {
		boolean[] LineisSpare = new boolean[AlterEgo.nLine];
		for (int i = 0; i < AlterEgo.nLine; i++) {
			LineisSpare[i] = AlterEgo.isSpare(i);
		}
		while (true) {
			int n = (int) (Math.random() * AlterEgo.nLine);
			if (LineisSpare[n] == false) {
				continue;
			}
			AlterEgo.draw(n);
			break;
		}
	}

	private static void play_Part_1_2 () {
		for (int i = 0; i < 10; i++) {
			int x = (int) (Math.random() * 2);
			int y = (int) (Math.random() * 2);
			if (y == 0) {
				y = AlterEgo.size - 1;
			}
			int z = (int) (Math.random() * AlterEgo.size);
			if (x == 0) {
				if (AlterEgo.isSpare(x, y, z)) {
					AlterEgo.draw(x, y, z);
					return;
				}
			} else {
				if (AlterEgo.isSpare(x, z, y)) {
					AlterEgo.draw(x, z, y);
					return;
				}

			}
		}

		AlterEgo.play_Part_1_1();
	}

	private static void play_Part_2_0 () {
		AlterEgo.play_Part_0_0();
	}

	private static void play_Part_2_1 () {
		int[] Sacris = new int[AlterEgo.nLine];
		for (int i = 0; i < AlterEgo.nLine; i++) {
			Sacris[i] = AlterEgo.SacrificeofLine(i);
		}
		int m = AlterEgo.nLine * 100;
		for (int i = 0; i < AlterEgo.nLine; i++) {
			Sacris[i] = Math.abs(Sacris[i]);
		}
		for (int i = 0; i < AlterEgo.nLine; i++) {
			if (Sacris[i] > 0 && Sacris[i] < m) {
				m = Sacris[i];
			}
		}
		for (int i = 0; i < AlterEgo.nLine; i++) {
			if (Sacris[i] == m) {
				AlterEgo.draw(i);
				return;
			}
		}
	}

	private static void play_Part_2_2 () {
		int[] Sacris = new int[AlterEgo.nLine];
		for (int i = 0; i < AlterEgo.nLine; i++) {
			Sacris[i] = AlterEgo.SacrificeofLine(i);
		}
		boolean ifCounter = false;
		int m;
		for (int i = 0; i < AlterEgo.nLine; i++) {
			if (Sacris[i] < 0) {
				ifCounter = true;
			}
		}
		if (ifCounter == true) {
			m = -AlterEgo.nLine - 1;
			for (int i = 0; i < AlterEgo.nLine; i++) {
				if (Sacris[i] < 0 && Sacris[i] > m) {
					m = Sacris[i];
				}
			}
			for (int i = 0; i < AlterEgo.nLine; i++) {
				if (Sacris[i] == m) {
					AlterEgo.draw(i);
					return;
				}
			}
		} else {
			m = AlterEgo.nLine + 100;
			for (int i = 0; i < AlterEgo.nLine; i++) {
				if (0 < Sacris[i] && Sacris[i] < m) {
					m = Sacris[i];
				}
			}
			for (int i = 0; i < AlterEgo.nLine; i++) {
				if (Sacris[i] == m) {
					AlterEgo.draw(i);
					return;
				}
			}
		}
	}

	private static void play_Part_3_0 () {
		AlterEgo.play_Part_0_0();
	}

	private static void play_Part_3_1 () {
		/**
		 * Mechanical occupy blocks.
		 */
		for (int i = 0; i < AlterEgo.size; i++) {
			for (int j = 0; j < AlterEgo.size; j++) {
				if (AlterEgo.Block[i][j] != 1) {
					continue;
				}
				AlterEgo.draw(i, j);
				return;
			}
		}
	}

	private static void play_Part_3_2 () {
		int[][] BlockHR = new int[AlterEgo.size][AlterEgo.size];

		int n2 = 0;
		int n4 = 0;
		for (int i = 0; i < AlterEgo.size; i++) {
			for (int j = 0; j < AlterEgo.size; j++) {
				BlockHR[i][j] = AlterEgo.HeadOfRoute(i, j);
				if (BlockHR[i][j] == 0) {
					continue;
				} else if (BlockHR[i][j] == 2) {
					n2++;
				} else if (BlockHR[i][j] == -4) {
					n4++;
				} else {
					AlterEgo.draw(i, j);
					return;
				}
			}
		}
		n4 /= 2;
		if (n2 > 1) {
			for (int i = 0; i < AlterEgo.size; i++) {
				for (int j = 0; j < AlterEgo.size; j++) {
					if (BlockHR[i][j] != 0) {
						AlterEgo.draw(i, j);
						return;
					}
				}
			}
		} else if (n2 == 1 && n4 > 0) {
			for (int i = 0; i < AlterEgo.size; i++) {
				for (int j = 0; j < AlterEgo.size; j++) {
					if (BlockHR[i][j] == -4) {
						AlterEgo.draw(i, j);
						return;
					}
				}
			}
		} else if (n2 == 0 && n4 > 1) {
			for (int i = 0; i < AlterEgo.size; i++) {
				for (int j = 0; j < AlterEgo.size; j++) {
					if (BlockHR[i][j] != 0) {
						AlterEgo.draw(i, j);
						return;
					}
				}
			}
		} else if (n2 == 1 && n4 == 0 && ((AlterEgo.size * AlterEgo.size - AlterEgo.nBlock(0)) <= 4)) {
			for (int i = 0; i < AlterEgo.size; i++) {
				for (int j = 0; j < AlterEgo.size; j++) {
					if (BlockHR[i][j] != 0) {
						AlterEgo.draw(i, j);
						return;
					}
				}
			}
		} else if (n2 == 0 && n4 == 1 && ((AlterEgo.size * AlterEgo.size - AlterEgo.nBlock(0)) <= 8)) {
			for (int i = 0; i < AlterEgo.size; i++) {
				for (int j = 0; j < AlterEgo.size; j++) {
					if (BlockHR[i][j] != 0) {
						AlterEgo.draw(i, j);
						return;
					}
				}
			}
		} else // if(n2+n4==1)
		{
			for (int i = 0; i < AlterEgo.size; i++) {
				for (int j = 0; j < AlterEgo.size; j++) {
					if (BlockHR[i][j] != 0) {
						int li, lj = i, lk = j;

						if (AlterEgo.Line[0][i][j] == 0) {
							li = 0;
							i--;
						} else if (AlterEgo.Line[1][i][j] == 0) {
							li = 1;
							j--;
						} else if (AlterEgo.Line[0][i + 1][j] == 0) {
							li = 0;
							lj++;
							i++;
						} else /* if(Line[1][i][j+1]==0) */
						{
							li = 1;
							lk++;
							j++;
						}

						if (AlterEgo.Line[0][i][j] == 0 && (li != 0 || lj != i || lk != j)) {
							li = 0;
							lj = i;
							lk = j;
						} else if (AlterEgo.Line[1][i][j] == 0 && (li != 1 || lj != i || lk != j)) {
							li = 1;
							lj = i;
							lk = j;
						} else if (AlterEgo.Line[0][i + 1][j] == 0 && (li != 0 || lj != i + 1 || lk != j)) {
							li = 0;
							lj = i + 1;
							lk = j;
						} else /*
								 * if(Line[1][i][j+1]==0 && (li!=1 || lj!=i ||
								 * lk!=j+1))
								 */{
							li = 1;
							lj = i;
							lk = j + 1;
						}

						AlterEgo.draw(li, lj, lk);
						return;
					}
				}
			}
		}
	}

	private static void play_Part_3_3 () {
		int nc = AlterEgo.nCounter();
		if (nc % 2 == 0) {
			AlterEgo.play_Part_3_2();
		} else {
			AlterEgo.play_Part_3_1();
		}
	}
}