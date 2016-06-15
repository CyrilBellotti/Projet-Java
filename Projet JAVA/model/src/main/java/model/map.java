package model;

import java.io.FileReader;
import java.io.IOException;

public class map 
{
	private char map [][];
	private int map1[][];
	private int niveau;
	
	public map()
	{
		this.niveau = 1;
		this.map = new char[12][20];
		int n = 1;
		for (int y = 0; y<12; y++)
		{
			for (int x = 0; x<20; x++)
			{
				map[y][x] = readmap(n);
				//System.out.print(uneMap[y][x]);
				//convertMap(y,x);
				n++;
			}
		}
		//System.out.println(uneMap[1][1]);
	}
	
	public char readmap(int n)
	{
		FileReader fr = null;
		char str = 0;
		try 
		{
			switch(niveau)
			{
			case 1:
				fr = new FileReader("Niveau 1.txt");
				break;
			case 2:
				fr = new FileReader("Niveau 2.txt");
				break;
			case 3:
				fr = new FileReader("Niveau 3.txt");
				break;
			case 4:
				fr = new FileReader("Niveau 4.txt");
				break;
			case 5:
				fr = new FileReader("Niveau 5.txt");
				break;
			}
			fr.skip(n);
			if((n = fr.read()) != -1)
			{
			    str = (char)n;
			}
		}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				try 
				{
					fr.close();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
		return str;
	}

	public void convertMap(int y,int x)
	{
		this.map1 = new int [12][20];
		switch(getMap(y,x))
		{
		case 'S':
			setMap1(y,x,3);
			break;
		case 'G':
			setMap1(y,x,10);
			break;
		case 'C':
			setMap1(y,x,2);
			break;
		case '*':
			setMap1(y,x,12);
			break;
		case'|':
			setMap1(y,x,11);
			break;
		case '-':
			setMap1(y,x,5);
			break;
		case 'O':
			setMap1(y,x,1);
			break;
		case 'I':
			setMap1(y,x,6);
			break;
		case 'J':
			setMap1(y,x,7);
			break;
		case 'K':
			setMap1(y,x,8);
			break;
		case 'L':
			setMap1(y,x,9);
			break;
		case 'P':
			setMap1(y,x,3);
			break;
		}
	}
	
	public char getMap(int y, int x) 
	{
		return (map[y][x]);
	}
	
	public int getMap1(int y, int x) 
	{
		return (map1[y][x]);
	}

	public void setMap1(int y, int x, int i) {
		this.map1[y][x] = i;
	}
	
	public int getNiveau()
	{
		return niveau;
		
	}
}