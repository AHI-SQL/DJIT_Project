package com.utt.nf20.accesfichier;

import com.utt.nf20.graphe.Arc;
import com.utt.nf20.graphe.Graphe;
import com.utt.nf20.graphe.Sommet;
import com.utt.nf20.util.Utilitaire;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dod
 */

public class OrdonnancementAccesDonnees
{
	/* Permet de lire un fichier */
	BufferedReader entreeFichier;
	/* Stocke la ligne lue */
	String ligneLue = "";
	/* Récupère le sommet stocké dans le fichier */
	int sommet = 0;
	/* Stocke l'ensemble des sommets récupérés dans le fichier */
	private ArrayList<Sommet> listeSommets = new ArrayList<Sommet>();
	/* Stocke les arcs formés après la lecture du fichier */
	private ArrayList<Arc> listeArcs = new ArrayList<Arc>();
	/* Représente les sommets formés lors de la lecture des contraintes, ces sommets permettront de générer les premiers arcs */
	private Sommet sommetDepart, sommetArrivee;
	/* Représente le sommet fictif S de départ, et F d'arrivée */
	private Sommet sommetFictifDepart, sommetFictifArrivee;
	/* L'ensemble des sommets et des arcs seront stockés au sein du graphe */
	private Graphe grapheOrdonnacement = new Graphe();

	public OrdonnancementAccesDonnees()
	{
		this.lectureArcsFichier();
		this.constructionPredecesseurSuccesseur();
	}

	/* A changer l'adresse vers le fichier pour la lecture */
	public void lectureArcsFichier()
	{
		try
		{
			sommetFictifDepart = new Sommet(11111);
			/* On ajoute le sommet fictif de départ à la liste des sommets */
			listeSommets.add(sommetFictifDepart);
			entreeFichier = new BufferedReader(new FileReader("C:\\Users\\dod\\Desktop\\NF20\\Jeu_Essai\\instance_projet.dat"));

			/* On va lire les premières lignes */
			for (int i = 0; i < 4; i++)
			{
				entreeFichier.readLine();
			}

			/* On lit les arcs */
			while (ligneLue != null)
			{
				ligneLue = entreeFichier.readLine();
				if (ligneLue.equals("CONSTRAINTS"))
				{
					/* Une fois arrivée à la ligne contrainte qui représente la deuxième partie du fichier,
					 * on va lire cette seconde partie, on donc faire appel à la méthode lectureContrainteFichier
					 * avec la référence sur le fichier */
					this.lectureContraintesFichier(entreeFichier);
					/* On a l'ensemble de nos sommets à la fin de la lecture du fichier et il nous reste
					 * plus qu'à ajouter le sommet fictif final F */
					sommetFictifArrivee = new Sommet(99999);
					listeSommets.add(sommetFictifArrivee);
					entreeFichier.close();
					break;
				}
				else
				{
					StringTokenizer tokenElement = new StringTokenizer(ligneLue, " ");
					sommet = Integer.parseInt(tokenElement.nextToken());
					sommetDepart = new Sommet(sommet);
					listeSommets.add(sommetDepart);
				
					int nbEspaces = tokenElement.countTokens();
					for (int i=0 ; i < nbEspaces ; i++)
					{
						sommetDepart.setPoids(Integer.parseInt(tokenElement.nextElement().toString()));
					}
				}
			}
		}
		catch (Exception ex)
		{
			Logger.getLogger(OrdonnancementAccesDonnees.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void lectureContraintesFichier(BufferedReader suiteFichier)
	{
		BufferedReader suiteLecture = suiteFichier;

		/* On lit les arcs */
		try
		{
			while (ligneLue != null)
			{
				ligneLue = entreeFichier.readLine();

				if (ligneLue.equals("END"))
				{
					break;
				}
				else
				{
					StringTokenizer tokenElement = new StringTokenizer(ligneLue, " ");
					sommet = Integer.parseInt(tokenElement.nextToken());
					sommetDepart = Utilitaire.recherche(listeSommets, sommet);

					int nbEspaces = tokenElement.countTokens();
					for (int i=0 ; i < nbEspaces ; i++)
					{
						sommetArrivee = Utilitaire.recherche(listeSommets, Integer.parseInt(tokenElement.nextElement().toString()));
						sommetArrivee.getListePredecesseur().add(sommetDepart);
						sommetDepart.getListeSommetsVoisins().add(sommetArrivee);
					}
				}

				listeArcs.add(new Arc(sommetDepart, sommetArrivee));
			}
		}
		catch (IOException ex)
		{
			Logger.getLogger(OrdonnancementAccesDonnees.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void constructionPredecesseurSuccesseur()
	{
		/* On va affecter suite à la lecture du fichier, les prédecesseurs pour les sommets n'ayant aucun prédecesseur */
		for (Sommet elementSommet : listeSommets)
		{
			if (elementSommet.getListePredecesseur().size() == 0)
			{
				if (elementSommet.getNumeroSommet() != 99999 && elementSommet.getNumeroSommet() != 11111)
				{
					elementSommet.getListePredecesseur().add(sommetFictifDepart);
				}
			}
			
			if (elementSommet.getListeSommetsVoisins().size() == 0)
			{
				if (elementSommet.getNumeroSommet() != 99999 && elementSommet.getNumeroSommet() != 11111)
				{
					elementSommet.getListeSommetsVoisins().add(sommetFictifArrivee);
				}
			}
		}

		for (Sommet element : listeSommets)
		{
			if (element.getListeSommetsVoisins().size() == 1)
			{
				if (element.getListeSommetsVoisins().get(0).getNumeroSommet() == 99999 && element.getNumeroSommet() != 99999)
				{
					sommetFictifArrivee.getListePredecesseur().add(element);
				}
			}

			if (element.getListePredecesseur().size() == 1)
			{
				if (element.getListePredecesseur().get(0).getNumeroSommet() == 11111)
				{
					sommetFictifDepart.getListeSommetsVoisins().add(element);
				}
			}
		}

		for (Sommet element : listeSommets)
		{
			for (Sommet sommetVoisin : element.getListeSommetsVoisins())
			{
				Arc arc = new Arc(element, sommetVoisin, element.getPoids());
				element.getListeArcConnecte().add(arc);
			}
			element.setPoids(-99999);
		}

		grapheOrdonnacement.setListeSommets(listeSommets);
		grapheOrdonnacement.setListeArcs(listeArcs);
	}

	public ArrayList<Sommet> getListeSommets()
	{
		return listeSommets;
	}

	public void setListeSommets(ArrayList<Sommet> listeSommets)
	{
		this.listeSommets = listeSommets;
	}

	public Graphe getGrapheOrdonnacement()
	{
		return grapheOrdonnacement;
	}

	public void setGrapheOrdonnacement(Graphe grapheOrdonnacement)
	{
		this.grapheOrdonnacement = grapheOrdonnacement;
	}
}
