package com.utt.nf20.graphe;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author dod
 */

public class Graphe 
{
	private ArrayList<Sommet> listeSommets;
	private ArrayList<Arc> listeArcs;

	public Graphe()
	{
		this.listeSommets = new ArrayList<Sommet>();
		this.listeArcs = new ArrayList<Arc>();
	}
	
	public Graphe(ArrayList<Sommet> listeSommets, ArrayList<Arc> listeArcs) 
	{
		this.listeSommets = listeSommets;
		this.listeArcs = listeArcs;
	}

	public ArrayList<Sommet> getListeSommets() 
	{
		return listeSommets;
	}

	public void setListeSommets(ArrayList<Sommet> listeSommets) 
	{
		this.listeSommets = listeSommets;
	}
	
	public ArrayList<Arc> getListeArcs() 
	{
		return listeArcs;
	}

	public void setListeArcs(ArrayList<Arc> listeArcs) 
	{
		this.listeArcs = listeArcs;
	}
	
	public ArrayList<Arc> getListeArcsParSommet (Sommet sommet)
	{
		LinkedList<Arc> listeArcsParSommetsTemporaire = new LinkedList<Arc>();

		for (Arc arc : sommet.getListeArcConnecte())
		{
			listeArcsParSommetsTemporaire.add(arc);
		}
		
		ArrayList<Arc> listeArcsParSommets = new ArrayList<Arc>(listeArcsParSommetsTemporaire);
		
		return listeArcsParSommets;
	}

	public Sommet recherche (int numeroSommetRechercher)
	{
		for (Sommet element : this.getListeSommets())
		{
			if (numeroSommetRechercher == element.getNumeroSommet())
			{
				return element;
			}
		}

		return null;
	}
}
