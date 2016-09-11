/**
 * Created by PhenomNomNom on 11-Sep-16.
 */
public class Cell
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
