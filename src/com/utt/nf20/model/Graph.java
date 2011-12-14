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
            if (Character.isDigit(line.charAt(cursor)) || line.charAt(cursor) == '-') {
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
        int[] minimalCosts = new int[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            minimalCosts[i] = INFINITE_COST;
        }
        minimalCosts[initialNode] = 0;
        boolean found = false;
        int k = 0;
        do {
            k++;
            found = false;
            for (int y = 0; y < minimalCosts.length; y++) {
                int[] predecessor = findPredecessors(y);
                for (int i = 0; i < predecessor.length; i++) {
                    int x = predecessor[i];
                    if (minimalCosts[x] + listOfArcs[x][y] < minimalCosts[y]) {
                        minimalCosts[y] = minimalCosts[x] + listOfArcs[x][y];
                        found = true;
                    }
                }
            }
        } while (found);
        return minimalCosts;
    }

    /**
     * Effectue l'algorithme de Dijkstra sur le graphe
     * @param initialNode Le noeud de départ, à partir duquel on va chercher un plus court chemin vers tous les autres noeuds du graphe
     * @return Les coûts minimums entre un noeud initial et tous les autres noeuds du graphe
     */
    public int[] doAlgorithm_Dijkstra(int initialNode) {
        int[] minimalCosts = new int[numberOfNodes];
        boolean[] visitedNodes = new boolean[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            minimalCosts[i] = INFINITE_COST;
            visitedNodes[i] = false;
        }
        minimalCosts[initialNode] = 0;
        int min = INFINITE_COST;
        int x = 0;
        int y = 0;

        for (int k = 0; k < numberOfNodes; k++) {
            min = INFINITE_COST;
            for (y = 0; y < numberOfNodes; y++) {
                if ((!visitedNodes[y]) && (minimalCosts[y] < min)) {
                    x = y;
                    min = minimalCosts[y];
                }
            }
            visitedNodes[x] = true;
            int[] successors = findSuccessors(x);
            for (int i = 0; i < successors.length; i++) {
                y = successors[i];
                if (min + listOfArcs[x][y] < minimalCosts[y]) {
                    minimalCosts[y] = min + listOfArcs[x][y];
                }
            }
        }
        return minimalCosts;
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
        return copyListIntoArray(arrayList);
    }

    /**
     * Cherche les successeurs d'un noeud
     * @param node Le noeud sur lequel on va chercher les successeurs
     * @return Les successeurs du noeud
     */
    private int[] findSuccessors(int node) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < listOfArcs.length; i++) {
            if (listOfArcs[node][i] != INFINITE_COST) {
                arrayList.add(i);
            }
        }
        return copyListIntoArray(arrayList);
    }

    /**
     * Recopie une liste (ArrayList) d'entiers (à taille dynamique) dans un tableau d'entiers (à taille statique)
     * @param arrayList La liste d'entiers (taille dynamique)
     * @return Le tableau d'entiers (taille statique)
     */
    private int[] copyListIntoArray(ArrayList<Integer> arrayList) {
        int[] array = new int[arrayList.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = arrayList.get(i);
        }
        return array;
    }
}
