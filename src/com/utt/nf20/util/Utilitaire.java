package com.utt.nf20.util;

import com.utt.nf20.graphe.Sommet;
import java.util.ArrayList;

/**
 *
 * @author dod
 */

/* Classe utilitaire qui permet d'obtenir la référence à un sommet à partir de son numéro, la référence
 * va nous permettre ainsi de manipuler le sommet là où le numéro ne nous permet pas d'accéder aux propriété du sommet */
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
