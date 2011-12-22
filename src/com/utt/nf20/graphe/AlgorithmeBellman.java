package com.utt.nf20.graphe;

import com.utt.nf20.accesfichier.AccesDonnees;
import java.util.Iterator;

/**
 *
 * @author dod
 */

public class AlgorithmeBellman 
{
	private Graphe grapheConstruit, grapheEnConstruction;
	private AccesDonnees accesDonnees = new AccesDonnees();
	
	private final int coutInfini = 99999;
	
	public AlgorithmeBellman()
	{
		grapheConstruit = new Graphe();
		grapheEnConstruction = new Graphe();
		accesDonnees.getInformationFromFile();
		constructionGrapheComplet();
		constructionGrapheIncomplet();
	}
	
	private void constructionGrapheComplet()
	{
		int nCout = 0, passagePremierSommet = 0;
		Sommet sommetRechercher;
		Arc arc;

		for (Integer numeroSommet : accesDonnees.getListeSommets())
		{
			grapheConstruit.getListeSommets().add(new Sommet(numeroSommet));

			if (passagePremierSommet == 0)
			{
				grapheEnConstruction.getListeSommets().add(new Sommet(numeroSommet, null, 0));
			}
			else
			{
				grapheEnConstruction.getListeSommets().add(new Sommet(numeroSommet, null, coutInfini));
			}

			passagePremierSommet++;
		}

		for (Sommet sommet : grapheConstruit.getListeSommets())
		{
			Iterator<Integer> iter = accesDonnees.getListePredecesseur().iterator(sommet.getNumeroSommet());
			while (iter.hasNext())
			{
				Integer o = iter.next();
				sommetRechercher = grapheConstruit.recherche(o);
				sommet.getListeSommetsVoisins().add(sommetRechercher);

				arc = new Arc(sommet, sommetRechercher, accesDonnees.getCout().get(nCout));
				sommet.getListeArcConnecte().add(arc);
				nCout++;
			}
		}

		System.out.println("Graphe complet construit.");
	}
	
	private void constructionGrapheIncomplet()
	{
		Sommet sommetRechercher = null;
		Arc arc = null;
		int nCout = 0;
		
		for (Sommet sommet : grapheEnConstruction.getListeSommets())
		{
			Iterator<Integer> iter = accesDonnees.getListePredecesseur().iterator(sommet.getNumeroSommet());
			while (iter.hasNext())
			{
				Integer o = iter.next();
				sommetRechercher = grapheEnConstruction.recherche(o);
				sommet.getListeSommetsVoisins().add(sommetRechercher);

				arc = new Arc(sommet, sommetRechercher, accesDonnees.getCout().get(nCout));
				sommet.getListeArcConnecte().add(arc);
				nCout++;
			}
		}

		System.out.println("Graphe incomplet construit.");
	}
	
	public void algorithmeBellman()
	{
		if (this.verficationCycleAbsorbant())
		{
			for (int i = 0; i < 2; i++)
			{
				for (Sommet sommet : grapheEnConstruction.getListeSommets())
				{
					for (Arc arc : grapheConstruit.getListeArcsParSommet(sommet))
					{
						if (arc.getSommetDestination().getPoids() > (arc.getSommetOrigine().getPoids() + arc.getPoids()))
						{
							arc.getSommetDestination().setPoids(arc.getSommetOrigine().getPoids() + arc.getPoids());
							arc.getSommetDestination().setPredecesseur(arc.getSommetOrigine());
						}
					}
				}
			}

			System.out.println("Fin pcc");
			this.presentation();
		}
	}

	private boolean verficationCycleAbsorbant()
	{
		for (Sommet sommet : grapheConstruit.getListeSommets())
		{
			for (Arc arc : grapheConstruit.getListeArcsParSommet(sommet))
			{
				if (arc.getSommetDestination().getPoids() > (arc.getSommetOrigine().getPoids() + arc.getPoids()))
				{
					return false;
				}
			}
		}

		return true;
	}

	private void presentation()
	{
		System.out.println(String.format("Nb sommets : %d", grapheEnConstruction.getListeSommets().size()));

		for (Sommet sommet : grapheEnConstruction.getListeSommets())
		{
			System.out.println(String.format("Numéro sommet : %d, poids sommet : %d",
					sommet.getNumeroSommet(), sommet.getPoids()));
		}
	}
}
