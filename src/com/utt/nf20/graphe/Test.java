package com.utt.nf20.graphe;

import com.utt.nf20.accesfichier.AccesDonnees;

/**
 *
 * @author dod
 */

public class Test
{
	private Graphe grapheConstruit, grapheEnConstruction;
	private Sommet sommetUn, sommetDeux, sommetTrois, sommetQuatre, sommetCinq,
			sommetUnBis, sommetDeuxBis, sommetTroisBis, sommetQuatreBis, sommetCinqBis;

	private Arc arcUnDeux, arcUnQuatre, arcDeuxTrois, arcDeuxQuatre, arcTroisCinq, arcQuatreDeux,
			arcQuatreTrois, arcQuatreCinq, arcCinqUn, arcCinqTrois,
			arcUnDeuxBis, arcUnQuatreBis, arcDeuxTroisBis, arcDeuxQuatreBis, arcTroisCinqBis,
			arcQuatreDeuxBis, arcQuatreTroisBis, arcQuatreCinqBis, arcCinqUnBis, arcCinqTroisBis;

	private AccesDonnees accesDonnees = new AccesDonnees();

	private final int coutInfini = 99999;

	public Test()
	{
		grapheConstruit = new Graphe();
		grapheEnConstruction = new Graphe();
		constructionGrapheComplet();
		constructionGrapheIncomplet();
	}

	private void constructionGrapheComplet()
	{
		/* Création des sommets 0, 3, 5, 9, 11 */

		sommetUn = new Sommet(0);
		sommetDeux = new Sommet(1);
		sommetTrois = new Sommet(2);
		sommetQuatre = new Sommet(3);
		sommetCinq = new Sommet(4);

		/* Ajout des voisins pour chacun des sommets */

		sommetUn.getListeSommetsVoisins().add(sommetDeux);
		sommetUn.getListeSommetsVoisins().add(sommetQuatre);

		sommetDeux.getListeSommetsVoisins().add(sommetTrois);
		sommetDeux.getListeSommetsVoisins().add(sommetQuatre);

		sommetTrois.getListeSommetsVoisins().add(sommetCinq);

		sommetQuatre.getListeSommetsVoisins().add(sommetDeux);
		sommetQuatre.getListeSommetsVoisins().add(sommetTrois);
		sommetQuatre.getListeSommetsVoisins().add(sommetCinq);

		/* Ajout des sommets au graphe */

		grapheConstruit.getListeSommets().add(sommetUn);
		grapheConstruit.getListeSommets().add(sommetDeux);
		grapheConstruit.getListeSommets().add(sommetTrois);
		grapheConstruit.getListeSommets().add(sommetQuatre);
		grapheConstruit.getListeSommets().add(sommetCinq);

		/* Création des arcs avec leurs poids */

		/* Arcs entre 0 et d'autres sommets*/

		arcUnDeux = new Arc(sommetUn, sommetDeux, 10);
		arcUnQuatre = new Arc(sommetUn, sommetQuatre, 5);

		arcDeuxTrois = new Arc(sommetDeux, sommetTrois, 1);
		arcDeuxQuatre = new Arc(sommetDeux, sommetQuatre, 2);

		arcTroisCinq = new Arc(sommetTrois, sommetCinq, 4);

		arcQuatreDeux = new Arc(sommetQuatre, sommetDeux, 3);
		arcQuatreTrois = new Arc(sommetQuatre, sommetTrois, 9);
		arcQuatreCinq = new Arc(sommetQuatre, sommetCinq, 2);

		arcCinqUn = new Arc(sommetCinq, sommetUn, 7);
		arcCinqTrois = new Arc(sommetCinq, sommetTrois, 6);

		/* Ajout des arcs au graphe */

		grapheConstruit.getListeArcs().add(arcUnDeux);
		grapheConstruit.getListeArcs().add(arcUnQuatre);
		grapheConstruit.getListeArcs().add(arcDeuxTrois);
		grapheConstruit.getListeArcs().add(arcDeuxQuatre);
		grapheConstruit.getListeArcs().add(arcTroisCinq);
		grapheConstruit.getListeArcs().add(arcQuatreDeux);
		grapheConstruit.getListeArcs().add(arcQuatreTrois);
		grapheConstruit.getListeArcs().add(arcQuatreCinq);
		grapheConstruit.getListeArcs().add(arcCinqUn);
		grapheConstruit.getListeArcs().add(arcCinqTrois);
	}

	private void constructionGrapheIncomplet()
	{
		/* Création du sommet de départ, de démarrage */

		sommetUnBis = new Sommet(0, null, 0);
		sommetDeuxBis = new Sommet(1, null, coutInfini);
		sommetTroisBis = new Sommet(2, null, coutInfini);
		sommetQuatreBis = new Sommet(3, null, coutInfini);
		sommetCinqBis = new Sommet(4, null, coutInfini);

		/* Ajout des voisins pour chacun des sommets */

		sommetUnBis.getListeSommetsVoisins().add(sommetDeuxBis);
		sommetUnBis.getListeSommetsVoisins().add(sommetQuatreBis);

		sommetDeuxBis.getListeSommetsVoisins().add(sommetTroisBis);
		sommetDeuxBis.getListeSommetsVoisins().add(sommetQuatreBis);

		sommetTroisBis.getListeSommetsVoisins().add(sommetCinqBis);

		sommetQuatreBis.getListeSommetsVoisins().add(sommetDeuxBis);
		sommetQuatreBis.getListeSommetsVoisins().add(sommetTroisBis);
		sommetQuatreBis.getListeSommetsVoisins().add(sommetCinqBis);

		/* Ajout des sommets au graphe */

		grapheEnConstruction.getListeSommets().add(sommetUnBis);
		grapheEnConstruction.getListeSommets().add(sommetDeuxBis);
		grapheEnConstruction.getListeSommets().add(sommetTroisBis);
		grapheEnConstruction.getListeSommets().add(sommetQuatreBis);
		grapheEnConstruction.getListeSommets().add(sommetCinqBis);

		/* Création des arcs avec leurs poids */

		/* Arcs entre 0 et d'autres sommets*/

		arcUnDeuxBis = new Arc(sommetUnBis, sommetDeuxBis, 10);
		arcUnQuatreBis = new Arc(sommetUnBis, sommetQuatreBis, 5);

		arcDeuxTroisBis = new Arc(sommetDeuxBis, sommetTroisBis, 1);
		arcDeuxQuatreBis = new Arc(sommetDeuxBis, sommetQuatreBis, 2);

		arcTroisCinqBis = new Arc(sommetTrois, sommetCinq, 4);

		arcQuatreDeuxBis = new Arc(sommetQuatreBis, sommetDeuxBis, 3);
		arcQuatreTroisBis = new Arc(sommetQuatreBis, sommetTroisBis, 9);
		arcQuatreCinqBis = new Arc(sommetQuatreBis, sommetCinqBis, 2);

		arcCinqUnBis = new Arc(sommetCinqBis, sommetUnBis, 7);
		arcCinqTroisBis = new Arc(sommetCinqBis, sommetTroisBis, 6);

		/* Ajout des arcs au graphe */

		grapheEnConstruction.getListeArcs().add(arcUnDeuxBis);
		grapheEnConstruction.getListeArcs().add(arcUnQuatreBis);
		grapheEnConstruction.getListeArcs().add(arcDeuxTroisBis);
		grapheEnConstruction.getListeArcs().add(arcDeuxQuatreBis);
		grapheEnConstruction.getListeArcs().add(arcTroisCinqBis);
		grapheEnConstruction.getListeArcs().add(arcQuatreDeuxBis);
		grapheEnConstruction.getListeArcs().add(arcQuatreTroisBis);
		grapheEnConstruction.getListeArcs().add(arcQuatreCinqBis);
		grapheEnConstruction.getListeArcs().add(arcCinqUnBis);
		grapheEnConstruction.getListeArcs().add(arcCinqTroisBis);

	}

	public void algorithmeBellman()
	{
		if (this.verficationCycleAbsorbant())
		{
			for (int i = 0; i < 2; i++)
			{
				for (Sommet sommet : grapheConstruit.getListeSommets())
				{
					for (Arc arc : grapheEnConstruction.getListeArcsParSommet(sommet))
					{
						if (arc.getSommetDestination().getPoids() > (arc.getSommetOrigine().getPoids() + arc.getPoids()))
						{
							System.out.println(arc.getSommetDestination().getPoids());
							arc.getSommetDestination().setPoids(arc.getSommetOrigine().getPoids() + arc.getPoids());
							arc.getSommetDestination().setPredecesseur(arc.getSommetOrigine());
						}
					}
				}
			}

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

