package com.utt.nf20.graphe;

/**
 *
 * @author dod
 */

public class Arc 
{
	private Sommet sommetOrigine, sommetDestination;
	private int poids;

	public Arc()
	{
		this.sommetOrigine = new Sommet();
		this.sommetDestination = new Sommet();
		this.poids = 0;
	}
	
	public Arc(Sommet sommetOrigine, Sommet sommetDestination) 
	{
		this.sommetOrigine = sommetOrigine;
		this.sommetDestination = sommetDestination;
	}
	
	public Arc(Sommet sommetOrigine, Sommet sommetDestination, int poids) 
	{
		this.sommetOrigine = sommetOrigine;
		this.sommetDestination = sommetDestination;
		this.poids = poids;
	}
	
	public int getPoids() 
	{
		return poids;
	}

	public void setPoids(int poids) 
	{
		this.poids = poids;
	}

	public Sommet getSommetDestination() 
	{
		return sommetDestination;
	}

	public void setSommetDestination(Sommet sommetDestination) 
	{
		this.sommetDestination = sommetDestination;
	}

	public Sommet getSommetOrigine() 
	{
		return sommetOrigine;
	}

	public void setSommetOrigine(Sommet sommetOrigine) 
	{
		this.sommetOrigine = sommetOrigine;
	}
}
