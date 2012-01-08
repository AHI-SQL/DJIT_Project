package com.utt.nf20.view;

import com.utt.nf20.graphe.AlgorithmeOrdonnancement;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import com.utt.nf20.model.Graph;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * La fenêtre principale de l'application
 * @author HALOUI Amine
 * @author JALOUZET Jérémie
 * @author XU Jiahuan
 */
public class MainForm extends javax.swing.JFrame {

    private Graph instanceFile = null;
    private AlgorithmeOrdonnancement ordonnancement;

    public MainForm() {
        initComponents();
        panel_PCC.setVisible(false);
        selecteurFichier.setFileFilter(new FileNameExtensionFilter("Instance", "dat"));
        selecteurFichier.setCurrentDirectory(new File("." + File.separator));
    }

    private void chooseFile() {
        if (selecteurFichier.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                instanceFile = new Graph(selecteurFichier.getSelectedFile());
                panel_PCC.setVisible(true);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }

            nodes_Label.setText(String.valueOf(instanceFile.getNumberOfNodes()));
            arcs_Label.setText(String.valueOf(instanceFile.getNumberOfArcs()));

            // On vide le modèle par défaut de la JTable
            clearDefaultTableModel((DefaultTableModel) minimalCosts_Table.getModel());
            // On récupère le modèle par défaut de la JTable Couts minimum
            DefaultTableModel defaultTableModel = (DefaultTableModel) listOfArcs_Table.getModel();
            // On vide le modèle par défaut de la JTable
            clearDefaultTableModel(defaultTableModel);
            // On vide la ComboBox Liste des noeuds
            nodes_ComboBox.removeAllItems();

            for (int i = 0; i < instanceFile.getListOfArcs().length; i++) {
                for (int j = 0; j < instanceFile.getListOfArcs()[i].length; j++) {
                    if (instanceFile.getListOfArcs()[i][j] != Graph.INFINITE_COST) {
                        Object[] o = {i, j, instanceFile.getListOfArcs()[i][j]};
                        defaultTableModel.addRow(o);
                    }
                }
            }
            for (int i = 0; i < instanceFile.getNumberOfNodes(); i++) {
                nodes_ComboBox.addItem(String.valueOf(i));
            }
        }
    }

    private void clearDefaultTableModel(DefaultTableModel t) {
        for (int i = t.getRowCount(); i > 0; --i) {
            t.removeRow(i - 1);
        }
    }

    private void displayMinimalCosts(int initialNode, int[] costs) {
        // On récupère le modèle par défaut de la JTable
        DefaultTableModel defaultTableModel = (DefaultTableModel) minimalCosts_Table.getModel();
        // On vide le modèle par défaut de la JTable
        clearDefaultTableModel(defaultTableModel);

        for (int i = 0; i < costs.length; i++) {
            Object[] o = {initialNode, i, costs[i]};
            defaultTableModel = (DefaultTableModel) minimalCosts_Table.getModel();
            defaultTableModel.addRow(o);
        }
    }

    private void algorithm_Bellman() {
        if (instanceFile != null) {
            int initialNode = Integer.parseInt(nodes_ComboBox.getSelectedItem().toString());
            int[] couts = instanceFile.doAlgorithm_Bellman(initialNode);
            displayMinimalCosts(initialNode, couts);
        }
    }

    private void algorithm_Dijkstra() {
        if (instanceFile != null) {
            int initialNode = Integer.parseInt(nodes_ComboBox.getSelectedItem().toString());
            int[] couts = instanceFile.doAlgorithm_Dijkstra(initialNode);
            displayMinimalCosts(initialNode, couts);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selecteurFichier = new javax.swing.JFileChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        panel_PCC = new javax.swing.JPanel();
        nodes_Panel = new javax.swing.JPanel();
        nodes_Label = new javax.swing.JLabel();
        arcs_Panel = new javax.swing.JPanel();
        arcs_Label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listOfArcs_Table = new javax.swing.JTable();
        listOfArcs_Label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        nodes_ComboBox = new javax.swing.JComboBox();
        algorithme_Bellman = new javax.swing.JButton();
        algorithme_Dijkstra = new javax.swing.JButton();
        listOfArcs_Label1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        minimalCosts_Table = new javax.swing.JTable();
        fileChoice_Button = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        projetChoixFichier_Bouton = new javax.swing.JButton();
        dureeMin_JLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NF20 : Plus court chemin");
        setResizable(false);

        panel_PCC.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        nodes_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre de noeuds", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        nodes_Label.setFont(new java.awt.Font("Tahoma", 1, 12));
        nodes_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nodes_Label.setText("0");

        javax.swing.GroupLayout nodes_PanelLayout = new javax.swing.GroupLayout(nodes_Panel);
        nodes_Panel.setLayout(nodes_PanelLayout);
        nodes_PanelLayout.setHorizontalGroup(
            nodes_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nodes_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nodes_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addContainerGap())
        );
        nodes_PanelLayout.setVerticalGroup(
            nodes_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nodes_PanelLayout.createSequentialGroup()
                .addComponent(nodes_Label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        arcs_Panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre d'arcs", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        arcs_Panel.setPreferredSize(new java.awt.Dimension(138, 53));

        arcs_Label.setFont(new java.awt.Font("Tahoma", 1, 12));
        arcs_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        arcs_Label.setText("0");

        javax.swing.GroupLayout arcs_PanelLayout = new javax.swing.GroupLayout(arcs_Panel);
        arcs_Panel.setLayout(arcs_PanelLayout);
        arcs_PanelLayout.setHorizontalGroup(
            arcs_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, arcs_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(arcs_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addContainerGap())
        );
        arcs_PanelLayout.setVerticalGroup(
            arcs_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(arcs_PanelLayout.createSequentialGroup()
                .addComponent(arcs_Label)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        listOfArcs_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Origine", "Destination", "Coût"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        listOfArcs_Table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(listOfArcs_Table);

        listOfArcs_Label.setFont(new java.awt.Font("Tahoma", 1, 11));
        listOfArcs_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        listOfArcs_Label.setText("Liste d'arcs");

        jLabel1.setText("Sommet d'origine : ");

        algorithme_Bellman.setText("Effectuer l'algorithme de Bellman-Ford");
        algorithme_Bellman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algorithme_BellmanActionPerformed(evt);
            }
        });

        algorithme_Dijkstra.setText("Effectuer l'algorithme de Dijkstra");
        algorithme_Dijkstra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algorithme_DijkstraActionPerformed(evt);
            }
        });

        listOfArcs_Label1.setFont(new java.awt.Font("Tahoma", 1, 11));
        listOfArcs_Label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        listOfArcs_Label1.setText("Chemins les plus courts");

        minimalCosts_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Origine", "Destination", "Coût minimum"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        minimalCosts_Table.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(minimalCosts_Table);

        javax.swing.GroupLayout panel_PCCLayout = new javax.swing.GroupLayout(panel_PCC);
        panel_PCC.setLayout(panel_PCCLayout);
        panel_PCCLayout.setHorizontalGroup(
            panel_PCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_PCCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_PCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listOfArcs_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_PCCLayout.createSequentialGroup()
                        .addGroup(panel_PCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_PCCLayout.createSequentialGroup()
                                .addComponent(nodes_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(arcs_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_PCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(listOfArcs_Label1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                            .addComponent(algorithme_Bellman, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                            .addGroup(panel_PCCLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nodes_ComboBox, 0, 202, Short.MAX_VALUE))
                            .addComponent(algorithme_Dijkstra, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panel_PCCLayout.setVerticalGroup(
            panel_PCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_PCCLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_PCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nodes_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arcs_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_PCCLayout.createSequentialGroup()
                        .addGroup(panel_PCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(nodes_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(algorithme_Bellman)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_PCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listOfArcs_Label)
                    .addComponent(algorithme_Dijkstra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_PCCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_PCCLayout.createSequentialGroup()
                        .addComponent(listOfArcs_Label1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                .addGap(51, 51, 51))
        );

        fileChoice_Button.setText("Choisir un fichier d'instance");
        fileChoice_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChoice_ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_PCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fileChoice_Button))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fileChoice_Button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_PCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Plus Court Chemin", jPanel1);

        projetChoixFichier_Bouton.setText("Choisir un fichier d'instance");
        projetChoixFichier_Bouton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projetChoixFichier_BoutonActionPerformed(evt);
            }
        });

        dureeMin_JLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        dureeMin_JLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dureeMin_JLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
                    .addComponent(projetChoixFichier_Bouton))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(projetChoixFichier_Bouton)
                .addGap(11, 11, 11)
                .addComponent(dureeMin_JLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(279, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ordonnancement de Projet", jPanel2);

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 24));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Projet NF20");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        jLabel3.setText("<html><ul><li>HALOUI Amine</li><li>JALOUZET Jérémie</li><li>XU Jiahuan</li></ul></html>");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-762)/2, (screenSize.height-585)/2, 762, 585);
    }// </editor-fold>//GEN-END:initComponents

    private void fileChoice_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChoice_ButtonActionPerformed
        chooseFile();
    }//GEN-LAST:event_fileChoice_ButtonActionPerformed

    private void algorithme_BellmanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algorithme_BellmanActionPerformed
        algorithm_Bellman();
    }//GEN-LAST:event_algorithme_BellmanActionPerformed

    private void algorithme_DijkstraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algorithme_DijkstraActionPerformed
        algorithm_Dijkstra();
    }//GEN-LAST:event_algorithme_DijkstraActionPerformed

    private void projetChoixFichier_BoutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projetChoixFichier_BoutonActionPerformed
        if (selecteurFichier.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            ordonnancement = new AlgorithmeOrdonnancement(selecteurFichier.getSelectedFile().toString());
            dureeMin_JLabel.setText(String.format("La durée minimale pour ce projet est : %d ", ordonnancement.rechercheDureeMaximale()));
        }
    }//GEN-LAST:event_projetChoixFichier_BoutonActionPerformed

    public static void main(String args[]) throws ParseException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton algorithme_Bellman;
    private javax.swing.JButton algorithme_Dijkstra;
    private javax.swing.JLabel arcs_Label;
    private javax.swing.JPanel arcs_Panel;
    private javax.swing.JLabel dureeMin_JLabel;
    private javax.swing.JButton fileChoice_Button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel listOfArcs_Label;
    private javax.swing.JLabel listOfArcs_Label1;
    private javax.swing.JTable listOfArcs_Table;
    private javax.swing.JTable minimalCosts_Table;
    private javax.swing.JComboBox nodes_ComboBox;
    private javax.swing.JLabel nodes_Label;
    private javax.swing.JPanel nodes_Panel;
    private javax.swing.JPanel panel_PCC;
    private javax.swing.JButton projetChoixFichier_Bouton;
    private javax.swing.JFileChooser selecteurFichier;
    // End of variables declaration//GEN-END:variables
}
