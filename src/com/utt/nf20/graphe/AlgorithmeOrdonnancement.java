package com.utt.nf20.graphe;

import com.utt.nf20.accesfichier.OrdonnancementAccesDonnees;
import java.util.LinkedList;

/**
 *
 * @author dod
 */

public class AlgorithmeOrdonnancement
{
	/* Représente la référence vers le fichier permettant la lecture du fichier, on pourra ainsi avoir accès au graphe
	 * construit suite à la lecture du fichier */
	private OrdonnancementAccesDonnees oad;
	private int daterSommet;
	/* Cette liste permet de contenir les sommets triés dans l'ordre topologique */
	private LinkedList<Sommet> listeTriTopologique;

	public AlgorithmeOrdonnancement(String nomFichier)
	{
		listeTriTopologique = new LinkedList<Sommet>();
		oad = new OrdonnancementAccesDonnees(nomFichier);
	}

	/* On va initialiser l'ensemble des sommets avant d'effectuer un tri topologique,
	 * chaque sommet se voit attribuer la couleur blanche */
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

	/* On effectue un parcours en profondeur afin de déterminer quelle tâche doit être effectuée
	 en premier, cela correcpond au tri topologique, le premier sommet à devenir noir sera la dernière
	 tâche que l'on peu effectuer. Cette tâche sera ajouée en premier dans la liste (listeTriTopologique)
	 on va donc l'ajouter en premier puis les sommets qui suivront seront ajoutés avant ce sommet */
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

	/* On va parcourir le graphe et effectuer l'algorithme de Bellman-Ford mais en changeant le signe > par <
	 * et en initialisant lors de la lecture du fichier les sommets avec un poids de -99999 sauf pour le premier
	 * sommet */
	public int rechercheDureeMaximale()
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

		return dureeMax;
	}
}
