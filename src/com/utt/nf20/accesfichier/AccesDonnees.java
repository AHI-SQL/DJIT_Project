package com.utt.nf20.accesfichier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.collections.map.MultiValueMap;

/**
 *
 * @author dod
 */

public class AccesDonnees 
{
	private int cursor = 0;
	private ArrayList<Integer> cout = new ArrayList<Integer>();
	private ArrayList<Integer> listeSommets = new ArrayList<Integer>();
	private MultiValueMap listeVoisins = new MultiValueMap();

	public int convertStringIntoInteger(String line) 
	{
		String stringOnlyWithNumbers = "";
		
		for (int i = 0; i < line.length(); i++) 
		{
			if (Character.isDigit(line.charAt(i)) || line.charAt(i) == '-') 
			{
				stringOnlyWithNumbers += line.charAt(i);
			}
		}
		
		return Integer.parseInt(stringOnlyWithNumbers);
	}

	private void moveCursorToNextCharacter(String line) 
	{
		boolean stop = false;
		
		while (!stop && cursor < line.length()) 
		{
			if (Character.isDigit(line.charAt(cursor)) || line.charAt(cursor) == '-') 
			{
				stop = true;
			} 
			else 
			{
				cursor++;
			}
		}
	}

	public String getAttributeFromString(String line) 
	{
		String s = "";
		boolean stop = false;
		
		while (!stop && cursor < line.length()) 
		{
			if (Character.isDigit(line.charAt(cursor)) || line.charAt(cursor) == '-') 
			{
				s += line.charAt(cursor);
			} 
			else 
			{
				stop = true;
			}

			cursor++;
		}
		
		return s;
	}

	public void addArc(String line) 
	{
		cursor = 0;

		// On cherche le sommet origine
		int origin = Integer.parseInt(getAttributeFromString(line));

		// On passe au prochain nombre
		moveCursorToNextCharacter(line);

		// On cherche le sommet destination
		int destination = Integer.parseInt(getAttributeFromString(line));

		// On passe au prochain nombre
		moveCursorToNextCharacter(line);

		// On cherche le cout
		int cost = Integer.parseInt(getAttributeFromString(line));
		
		cout.add(cost);

		if (!listeSommets.contains(origin))
		{
			listeSommets.add(origin);
		}

		listeVoisins.put(origin, destination);
	}

	public String getInformationFromFile()
	{
		BufferedReader fileReader = null;
		String line = null;
		String sommet = null;

		try
		{
			fileReader = new BufferedReader(new FileReader("C:\\Users\\dod\\Desktop\\NF20\\inst_v1000_s1.dat"));
		} 
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(AccesDonnees.class.getName()).log(Level.SEVERE, null, ex);
		}
		try
		{
			while ((line = fileReader.readLine()) != null)
			{
				if (line.startsWith("NB_NODES"))
				{
					sommet = line.substring("NB_NODES".length());
				}
				else if (line.startsWith("NB_ARCS"))
				{
					String s = line.substring("NB_ARCS".length());
				}
				else if (line.startsWith("LIST_OF_ARCS"))
				{
					//line = fileReader.readLine();
					while (!line.startsWith("END"))
					{
						line = fileReader.readLine();
						if (!line.equals("END"))
						{
							addArc(line);
						}
					}
				}
			}
			fileReader.close();
		}
		catch (IOException ex)
		{
			Logger.getLogger(AccesDonnees.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return sommet;
	}
	
	public ArrayList<Integer> getCout()
	{
		return cout;
	}

	public ArrayList<Integer> getListeSommets()
	{
		return listeSommets;
	}

	public MultiValueMap getListePredecesseur()
	{
		return listeVoisins;
	}
}
