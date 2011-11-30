package com.utt.nf20.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Un graphe, créé à partir d'une instance de fichier
 * @author HALOUI Amine
 * @author JALOUZET Jérémie
 * @author XU Jiahuan
 */
public class Graph {

    /**
     * Le fichier utilisé pour créer le graphe
     */
    private File file = null;
    /**
     * Le nombre de noeuds du graphe
     */
    private int numberOfNodes = 0;
    /**
     * Le nombre d'arcs du graphe
     */
    private int numberOfArcs = 0;
    /**
     * La liste des arcs du graphe. Le premier nombre est le noeud d'origine, le second est le noeud de destination.
     */
    private int[][] listOfArcs = null;
    /**
     * Un curseur, utilisé dans les algorithmes pour parcourir une chaine de caractères
     */
    int cursor = 0;
    /**
     * La constante représentant l'infini (lorsqu'il n'y a pas d'arc entre deux noeuds)
     */
    public final static int INFINITE_COST = 999999999;

    /**
     * Constructeur du graphe. Récupère les informations à partir du fichier
     * @param file Le fichier d'instance servant à créer le graphe
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public Graph(File file) throws FileNotFoundException, IOException {
        this.file = file;
        getInformationFromFile();
    }

    /**
     * Convertit un morceau de ligne en nombre (s'arrête lorsqu'on rencontre un espace ou tout caractère qui n'est pas un chiffre)
     * @param line La ligne du fichier à traiter
     * @return Le nombre
     */
    private int convertStringIntoInteger(String line) {
        String stringOnlyWithNumbers = "";
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i)) || line.charAt(i) == '-') {
                stringOnlyWithNumbers += line.charAt(i);
            }
        }
        return Integer.parseInt(stringOnlyWithNumbers);
    }

    /**
     * Avance le curseur jusqu'au prochain caractère, autre qu'un espace
     * @param line La ligne du fichier à traiter
     */
    private void moveCursorToNextCharacter(String line) {
        boolean stop = false;
        while (!stop && cursor < line.length()) {
            if (line.charAt(cursor) != ' ') {
                stop = true;
            } else {
                cursor++;
            }
        }
    }

    /**
     * Ajout des commentaires de test.
     * Récupère un attribut à partir d'une ligne de fichier (nombre de noeuds ou nombre d'arcs)
     * @param line La ligne du fichier à traiter
     * @return L'attribut (nombre de noeuds ou nombre d'arcs)
     */
    private String getAttributeFromString(String line) {
        String s = "";
        boolean stop = false;
        while (!stop && cursor < line.length()) {
            if (Character.isDigit(line.charAt(cursor)) || line.charAt(cursor) == '-') {
                s += line.charAt(cursor);
            } else {
                stop = true;
            }
            cursor++;
        }
        return s;
    }

    /**
     * Ajoute un arc à partir d'une ligne du fichier
     * @param line La ligne du fichier à traiter
     */
    private void addArc(String line) {
        cursor = 0;

        // On cherche le sommet origine
        String origin = getAttributeFromString(line);

        // On passe au prochain nombre
        moveCursorToNextCharacter(line);

        // On cherche le sommet destination
        String destination = getAttributeFromString(line);

        // On passe au prochain nombre
        moveCursorToNextCharacter(line);

        // On cherche le cout
        String cost = getAttributeFromString(line);

        listOfArcs[Integer.parseInt(origin)][Integer.parseInt(destination)] = Integer.parseInt(cost);
    }

    /**
     * Récupère le nombre du noeuds du graphe
     * @return Le nombre du noeuds du graphe
     */
    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    /**
     * Récupère le nombre d'arcs du graphe
     * @return Le nombre d'arcs du graphe
     */
    public int getNumberOfArcs() {
        return numberOfArcs;
    }

    /**
     * Récupère la liste des arcs du graphe
     * @return La liste des arcs du graphe
     */
    public int[][] getListOfArcs() {
        return listOfArcs;
    }

    /**
     * Récupère toutes les informations du graphe à partir de l'instance de fichier
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void getInformationFromFile() throws FileNotFoundException, IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = fileReader.readLine()) != null) {
            if (line.startsWith("NB_NODES")) {
                String s = line.substring("NB_NODES".length());
                numberOfNodes = convertStringIntoInteger(s);
            } else if (line.startsWith("NB_ARCS")) {
                String s = line.substring("NB_ARCS".length());
                numberOfArcs = convertStringIntoInteger(s);
            } else if (line.startsWith("LIST_OF_ARCS")) {
                line = fileReader.readLine(); // On passe à la ligne suivante

                listOfArcs = new int[numberOfNodes][numberOfNodes];
                for (int i = 0; i < listOfArcs.length; i++) {
                    for (int j = 0; j < listOfArcs[i].length; j++) {
                        listOfArcs[i][j] = INFINITE_COST;
                    }
                }

                while (!line.startsWith("END")) {
                    addArc(line);
                    line = fileReader.readLine();
                }
            }
        }
        fileReader.close();
    }

    /**
     * Effectue l'algorithme de Bellman-Ford sur le graphe
     * @param initialNode Le noeud de départ, à partir duquel on va chercher un plus court chemin vers tous les autres noeuds du graphe
     * @return Les coûts minimums entre un noeud initial et tous les autres noeuds du graphe
     */
    public int[] doAlgorithm_Bellman(int initialNode) {
        int[] d = new int[numberOfNodes];
        int[] pere = new int[numberOfNodes];
        for (int i = 0; i < d.length; i++) {
            d[i] = INFINITE_COST;
        }
        d[initialNode] = 0;
        pere[initialNode] = 0;
        boolean trouver = false;
        int k = 0;
        do {
            k++;
            trouver = false;
            for (int y = 0; y < d.length; y++) {
                int[] predecesseur = findPredecessors(y);
                for (int i = 0; i < predecesseur.length; i++) {
                    int x = predecesseur[i];
                    if (d[x] + listOfArcs[x][y] < d[y]) {
                        d[y] = d[x] + listOfArcs[x][y];
                        pere[y] = x;
                        trouver = true;
                    }
                }
            }
        } while (trouver);
        return d;
    }

    /**
     * Cherche les prédécesseurs d'un noeud
     * @param node Le noeud sur lequel on va chercher les prédécesseurs
     * @return Les prédécesseurs du noeud
     */
    private int[] findPredecessors(int node) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < listOfArcs.length; i++) {
            if (listOfArcs[i][node] != INFINITE_COST) {
                arrayList.add(i);
            }
        }
        int[] predecesseurs = new int[arrayList.size()];
        for (int i = 0; i < predecesseurs.length; i++) {
            predecesseurs[i] = arrayList.get(i);
        }
        return predecesseurs;
    }
    
    /**
     * Effectue l'algorithme de Dijkstra sur le graphe
     * @param initialNode Le noeud de départ, à partir duquel on va chercher un plus court chemin vers tous les autres noeuds du graphe
     * @return Les coûts minimums entre un noeud initial et tous les autres noeuds du graphe
     */
    public int[] doAlgorithm_Dijkstra(int initialNode) {
        int min = INFINITE_COST;
        int successeur = initialNode;
        int[] dist = new int[numberOfNodes];
        boolean[] sommetsFixes = new boolean[numberOfNodes];

        for (int i = 0; i < sommetsFixes.length; i++) {
            sommetsFixes[i] = false;
        }

        for (int i = 0; i < numberOfNodes; i++) {
            dist[i] = listOfArcs[initialNode][i];
            sommetsFixes[initialNode] = true;
            for (i = 0; i < numberOfNodes - 1; i++) {
                min = INFINITE_COST;
                successeur = initialNode;
                for (int j = 0; j < numberOfNodes; j++) {
                    if (!sommetsFixes[j] && dist[j] < min) {
                        successeur = j;
                        min = dist[j];
                    }
                    sommetsFixes[successeur] = true;
                }               
            }
            for (int w = 0; w < numberOfNodes; w++) {
                if (!sommetsFixes[w] && dist[successeur] + listOfArcs[successeur][w] < dist[w]) {
                    dist[w] = dist[successeur] + listOfArcs[successeur][w];
                }
            }
        }
        return dist;
    }
    
}
