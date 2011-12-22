package com.utt.nf20.graphe;

import com.utt.nf20.accesfichier.OrdonnancementAccesDonnees;
import java.util.LinkedList;

/**
 *
 * @author dod
 */

public class AlgorithmeOrdonnancement
{
	private OrdonnancementAccesDonnees oad;
	private int daterSommet;
	private LinkedList<Sommet> listeTriTopologique = new LinkedList<Sommet>();
	private Graphe grapheOrdonnancementConstruction = new Graphe();

	public AlgorithmeOrdonnancement()
	{
		oad = new OrdonnancementAccesDonnees();
	}

	private void parcoursProfondeur()
	{
		for (Sommet sommet : oad.getListeSommets())
		{
			sommet.setCouleur("Blanc");
			sommet.setPredecesseur(null);
		}

		for (Sommet sommet : oad.getListeSommets())
		{
			if (sommet.getCouleur().equals("Blanc"))
			{
				this.visiterParcoursProfondeur(sommet);
			}
		}
	}

	private void visiterParcoursProfondeur(Sommet sommet)
	{
		daterSommet++;
		sommet.setDateDecouverte(daterSommet);
		sommet.setCouleur("Gris");

		for (Arc arc : sommet.getListeArcConnecte())
		{
			if (arc.getSommetDestination().getCouleur().equals("Blanc"))
			{
				arc.getSommetDestination().setPredecesseur(sommet);
				visiterParcoursProfondeur(arc.getSommetDestination());
			}
		}

		sommet.setCouleur("Noir");
		daterSommet++;
		sommet.setDateFinExamen(daterSommet);
		listeTriTopologique.addFirst(sommet);
	}

	public void triTopologique()
	{
		parcoursProfondeur();
		oad.getGrapheOrdonnacement().getListeSommets().get(0).setPoids(0);
		int dureeMax = 0, dureeMaxTemp = 0;

		for (Sommet sommetPrisOrdreTopologique : listeTriTopologique)
		{
			for (Arc arc : oad.getGrapheOrdonnacement().getListeArcsParSommet(sommetPrisOrdreTopologique))
			{
				if (arc.getSommetDestination().getPoids() < sommetPrisOrdreTopologique.getPoids() + arc.getPoids())
				{
					dureeMaxTemp = sommetPrisOrdreTopologique.getPoids() + arc.getPoids();
					arc.getSommetDestination().setPoids(dureeMaxTemp);
					arc.getSommetDestination().setPredecesseur(sommetPrisOrdreTopologique);

					if (dureeMaxTemp > dureeMax)
					{
						dureeMax = dureeMaxTemp;
					}
				}
			}
		}

		System.out.println("Durée maximale : " + dureeMaxTemp);
	}
}
