public class SudokuTester
{
	public static void main(String[] args)
	{
		//Sudoku grid = new Sudoku();
		Sudoku su = new Sudoku("grid1.txt");
		su.printGrid();

		Backtrack bt = new Backtrack(su);
		int loopCount = bt.doBacktrack();

		System.out.println();
		su.printGrid();
		System.out.println();
		System.out.println(loopCount + " loops!");
	}
}
