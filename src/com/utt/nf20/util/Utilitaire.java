package com.utt.nf20.util;

import com.utt.nf20.graphe.Sommet;
import java.util.ArrayList;

/**
 *
 * @author dod
 */

public class Utilitaire
{
	public static Sommet recherche(ArrayList<Sommet> listeSommets, int sommetRechercher)
	{
		Sommet sommetTrouver = null;

		for (Sommet sommetParcouru : listeSommets)
		{
			if (sommetParcouru.getNumeroSommet() == sommetRechercher)
			{
				sommetTrouver = sommetParcouru;
			}
		}

		return sommetTrouver;
	}
}
