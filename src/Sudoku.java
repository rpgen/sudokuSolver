import java.io.*;
import java.util.*;


public class Sudoku
{
	Cell[][] grid = new Cell[9][9];
	int blankCount = 0;

	class Cell
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

		Cell(Cell c)
		{
			this.value = c.value;
			this.perm = c.perm;
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

	public Sudoku(Sudoku sCopy)
	{
		this.grid = sCopy.grid;
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

	public void betterPrint()
	{

	}
}
