package com.utt.nf20.graphe;

import java.util.ArrayList;

/**
 *
 * @author dod
 */

public class Sommet 
{
	private int numeroSommet;
	private static int nombreTotalSommet;
	private int poids;
	private Sommet predecesseur;
	private ArrayList<Sommet> listeSommetsVoisins = new ArrayList<Sommet>();
	private static ArrayList<Sommet> listeCompleteSommet = new ArrayList<Sommet>();
	private ArrayList<Arc> listeArcConnecte = new ArrayList<Arc>();

	/* Partie pour l'ordonnacement */
	private ArrayList<Sommet> listePredecesseur = new ArrayList<Sommet>();
	private String couleur;
	private int dateDecouverte, dateFinExamen;

	public Sommet()
	{
		this.numeroSommet = 0;
		Sommet.listeCompleteSommet.add(this);
	}
	
	public Sommet(int numeroSommet) 
	{
		this.numeroSommet = numeroSommet;
		nombreTotalSommet++;
		Sommet.listeCompleteSommet.add(this);
	}
	
	public Sommet(int numeroSommet, Sommet predecesseur) 
	{
		this.numeroSommet = numeroSommet;
		this.predecesseur = predecesseur;
		
		nombreTotalSommet++;
		Sommet.listeCompleteSommet.add(this);
	}
	
	public Sommet(int numeroSommet, Sommet predecesseur, int poids) 
	{
		this.numeroSommet = numeroSommet;
		this.predecesseur = predecesseur;
		this.poids = poids;

		nombreTotalSommet++;
		Sommet.listeCompleteSommet.add(this);
	}

	public int getNumeroSommet() 
	{
		return numeroSommet;
	}

	public void setNumeroSommet(int numeroSommet) 
	{
		this.numeroSommet = numeroSommet;
	}
	
	public static int getNombreTotalSommet() 
	{
		return nombreTotalSommet;
	}
	
	public int getPoids() 
	{
		return poids;
	}

	public void setPoids(int poids) 
	{
		this.poids = poids;
	}
	
	public Sommet getPredecesseur() 
	{
		return predecesseur;
	}

	public void setPredecesseur(Sommet predecesseur) 
	{
		this.predecesseur = predecesseur;
	}
	
	public ArrayList<Sommet> getListeSommetsVoisins() 
	{
		return listeSommetsVoisins;
	}

	public void setListeSommetsVoisins(ArrayList<Sommet> listeSommetsVoisins) 
	{
		this.listeSommetsVoisins = listeSommetsVoisins;
	}

	public ArrayList<Arc> getListeArcConnecte()
	{
		return listeArcConnecte;
	}

	public void setListeArcConnecte(ArrayList<Arc> listeArcConnecte)
	{
		this.listeArcConnecte = listeArcConnecte;
	}

	public ArrayList<Sommet> getListePredecesseur()
	{
		return listePredecesseur;
	}

	public void setListePredecesseur(ArrayList<Sommet> listePredecesseur)
	{
		this.listePredecesseur = listePredecesseur;
	}

	public String getCouleur()
	{
		return couleur;
	}

	public void setCouleur(String couleur)
	{
		this.couleur = couleur;
	}

	public int getDateDecouverte()
	{
		return dateDecouverte;
	}

	public void setDateDecouverte(int dateDecouverte)
	{
		this.dateDecouverte = dateDecouverte;
	}

	public int getDateFinExamen()
	{
		return dateFinExamen;
	}

	public void setDateFinExamen(int dateFinExamen)
	{
		this.dateFinExamen = dateFinExamen;
	}
}
