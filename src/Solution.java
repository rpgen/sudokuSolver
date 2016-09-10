import java.util.*;

public class Solution
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int cities = sc.nextInt();
		int stations = sc.nextInt();

		if (cities == stations)
		{
			System.out.println(0);
			return;
		}
		int last = sc.nextInt();

		int max = last;

		for (int i = 1; i < stations; i++)
		{
			int next = sc.nextInt();
			if (next - last > max)
				max = next - last;
			last = next;
		}
		max = (max+1) /2;
		if (cities - last > max)
			max = (cities - last);

		System.out.println(max);
	}
}
