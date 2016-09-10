import java.io.*;
import java.util.*;


public class Sudoku
{
	Cell[][] grid = new Cell[9][9];
	int blankCount = 0;

	private class Cell
	{
		int value;
		boolean perm;

		Cell()
		{
			value = 0;
			perm = false;
		}
		Cell(int v, boolean p)
		{
			value = v;
			perm = p;
		}

		int getValue()
		{
			return value;
		}
		void setValue(int num)
		{
			value = num;
		}
		boolean getPerm()
		{
			return perm;
		}
		void setPerm(boolean p)
		{
			perm = p;
		}
	}

	public Sudoku()
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				grid[i][j] = new Cell();
			}
		}
	}

	public Sudoku(String fileName)
	{
		initializeGrid(fileName);
	}

	private void initializeGrid(String fileName)
	{
		File file = new File(fileName);
		grid = new Cell[9][9];

		try
		{
			Scanner sc = new Scanner(file);
			for (int i = 0; i < 9; i++)
			{
				for (int j = 0; j < 9; j++)
				{
					int num = sc.nextInt();
					grid[i][j] = new Cell(num, true);
					if (num == 0)
						grid[i][j].setPerm(false);
				}
			}
			sc.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public void printGrid()
	{
		if (grid != null)
		{
			for (int i = 0; i < 9; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					System.out.print(grid[i][j].getValue() + " ");
				}
				System.out.print(grid[i][8].getValue() + "\n");
			}
		}
		else
		{
			System.out.println("No possible solution");
		}
	}

	//Uses a stack to brute force until it finds a valid Sudoku solution.
	public int backtrack()
	{
		Stack<Integer> stack = new Stack<>();
		int size = 0, loopCount = 0;
		boolean backwards = false;

		first:
		while (size < 81)
		{
			loopCount++;
			int row = size / 9;
			int column = size % 9;
			Cell current = grid[row][column];
			int val = current.getValue();
			boolean perm = current.getPerm();
			//stack.push(current.getValue());

			second:
			if (perm)
			{
				if (backwards)
				{
					size--;
					stack.pop();
					if (stack.empty())
					{
						//System.out.println("No possible solution!");
						grid = null;
						break first;
					}
				} else
				{
					size++;
					stack.push(val);
				}
			} else
			{
				while (val < 9)
				{
					backwards = false;
					grid[row][column].setValue(++val);
					stack.push(val);
					if (checkHorizontal(row, column) && checkVertical(row, column) && checkSquare(row, column))
					{
						size++;
						break second;
					}
					stack.pop();
				}
				grid[row][column].setValue(0);
				size--;
				stack.pop();
				if (stack.empty())
				{
					//System.out.println("No possible solution!");
					grid = null;
					break;
				}
				backwards = true;
			}
		}
		return loopCount;
	}

	//Returns array of the NEXT location of a non-permanent square
	//Returns null if there are no more.
	private int[] getNextBlank(int row, int column)
	{
		column++;
		while (row <= 8)
		{
			while (column <= 8)
			{
				if (!grid[row][column].getPerm())
				{
					return new int[]{row,column};
				}
			}
			column = 0;
			row++;
		}
		return null;
	}

	//Returns array of the PREVIOUS location of a non-permanent square
	//Returns null if there are no more.
	private int[] getLastBlank(int row, int column)
	{
		column--;
		while (row >= 0)
		{
			while (column >= 0)
			{
				if (!grid[row][column].getPerm())
				{
					return new int[]{row,column};
				}
			}
			column = 8;
			row--;
		}
		return null;
	}

	private boolean fullCheckHorizontal(int row)
	{
		boolean[] exists = new boolean[9];
		Arrays.fill(exists, false);
		for (int i = 0; i < 9; i++)
		{
			int num = grid[row][i].getValue();
			if (i != 0)
			{
				if (exists[num-1])
				{
					return false;
				}
				else
				{
					exists[num-1] = true;
				}
			}
		}
		return true;
	}

	//Returns false if duplicate numbers on same horizonal line / true if no duplicates
	private boolean checkHorizontal(int row, int column)
	{
		int num = grid[row][column].getValue();
		for (int i = 0; i < 9; i++)
		{
			if (i != column && grid[row][i].getValue() == num)
			{
				return false;
			}
		}
		return true;
	}

	//Returns false if duplicate numbers on same vertical line / true if no duplicates
	private boolean checkVertical(int row, int column)
	{
		int num = grid[row][column].getValue();
		for (int i = 0; i < 9; i++)
		{
			if (i != row && grid[i][column].getValue() == num)
			{
				return false;
			}
		}
		return true;
	}

	private boolean checkSquare(int row, int column)
	{
		int num = grid[row][column].getValue();
		int x = column / 3;	//Horizontal 3x3 square
		int y = row / 3;	//Vertical 3x3 square

		for (int i = 3*y; i < 3*y+3; i++)		//Row
		{
			for (int j = 3*x; j < 3*x+3; j++)	//Column
			{
				if (i != row || j != column)	//If not current cell
				{
					if (grid[i][j].getValue() == num)
					{
						return false;
					}
				}
			}
		}
		return true;
	}
}
