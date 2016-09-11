import java.util.*;

public class Backtrack
{
	Sudoku sud;

	public Backtrack(Sudoku s)
	{
		sud = new Sudoku(s);
	}

	//Main backtrack method
	public int doBacktrack()
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
			Sudoku.Cell current = sud.grid[row][column];
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
						sud.grid = null;
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
					sud.grid[row][column].setValue(++val);
					stack.push(val);
					if (checkHorizontal(row, column) && checkVertical(row, column) && checkSquare(row, column))
					{
						size++;
						break second;
					}
					stack.pop();
				}
				sud.grid[row][column].setValue(0);
				size--;
				stack.pop();
				if (stack.empty())
				{
					//System.out.println("No possible solution!");
					sud.grid = null;
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
				if (!sud.grid[row][column].getPerm())
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
				if (!sud.grid[row][column].getPerm())
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
			int num = sud.grid[row][i].getValue();
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
	//Instead of checking all values for duplicates, it compares one value against all others
	private boolean checkHorizontal(int row, int column)
	{
		int num = sud.grid[row][column].getValue();
		for (int i = 0; i < 9; i++)
		{
			if (i != column && sud.grid[row][i].getValue() == num)
			{
				return false;
			}
		}
		return true;
	}

	//Returns false if duplicate numbers on same vertical line / true if no duplicates
	private boolean checkVertical(int row, int column)
	{
		int num = sud.grid[row][column].getValue();
		for (int i = 0; i < 9; i++)
		{
			if (i != row && sud.grid[i][column].getValue() == num)
			{
				return false;
			}
		}
		return true;
	}

	private boolean checkSquare(int row, int column)
	{
		int num = sud.grid[row][column].getValue();
		int x = column / 3;	//Horizontal 3x3 square
		int y = row / 3;	//Vertical 3x3 square

		for (int i = 3*y; i < 3*y+3; i++)		//Row
		{
			for (int j = 3*x; j < 3*x+3; j++)	//Column
			{
				if (i != row || j != column)	//If not current cell
				{
					if (sud.grid[i][j].getValue() == num)
					{
						return false;
					}
				}
			}
		}
		return true;
	}
}
