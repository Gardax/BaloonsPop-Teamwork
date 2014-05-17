package BaloonsPopGame;

public class Score
{
	private java.util.ArrayList<Player> players;

	public Score()
	{
		this.players = new java.util.ArrayList<Player>();
	}

	public final boolean IsGoodEnough(int moves)
	{
		if (this.players.size() < 5)
		{
			return true;
		}
		if (this.players.get(4).Points < moves)
		{
			return true;
		}
		return false;
	}

	public final void AddPlayer(String name, int moves)
	{
		if (this.players.size() == 5)
		{
			this.players.remove(4);
			this.players.add(new Player(name, moves));
		}
		else
		{
			this.players.add(new Player(name, moves));
		}
	}

	
	public final int Count()
	{
		return this.players.size();
	}

	public final void Sort()
	{
		java.util.Collections.sort(players, (x, y) -> x.Points.CompareTo(y.Points));
	}

	public final void PrintScoreBoard()
	{
		System.out.println("---------TOP FIVE SCORES-----------");
		for (int i = 0; i < players.size(); ++i)
		{
			System.out.println("%1$s.%2$s - %3$s" + "\r\n", i + 1, players.get(i).Name, players.get(i).Points);
		}
		System.out.println("-----------------------------------");


	}
}