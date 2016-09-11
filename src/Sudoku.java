import java.io.*;
import java.util.*;


public class Sudoku
{
	Cell[][] grid = new Cell[9][9];
	int blankCount = 0;

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

	public Sudoku(Sudoku sudokuCopy)
	{
		this.grid = sudokuCopy.grid;
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
