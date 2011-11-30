package com.utt.nf20.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import com.utt.nf20.model.Graph;
import org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel;

/**
 * La fenêtre principale de l'application
 * @author HALOUI Amine
 * @author JALOUZET Jérémie
 * @author XU Jiahuan
 */
public class MainForm extends javax.swing.JFrame {

    private Graph instanceFile = null;

    public MainForm() {
        initComponents();
    }

    private void chooseFile() {
        if (fileChoice_FileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                instanceFile = new Graph(fileChoice_FileChooser.getSelectedFile());
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

    private void algorithme_Bellman() {
        if (instanceFile != null) {

            // On récupère le modèle par défaut de la JTable
            DefaultTableModel defaultTableModel = (DefaultTableModel) minimalCosts_Table.getModel();
            // On vide le modèle par défaut de la JTable
            clearDefaultTableModel(defaultTableModel);

            int initialNode = Integer.parseInt(nodes_ComboBox.getSelectedItem().toString());
            int[] d = instanceFile.doAlgorithm_Bellman(initialNode);
            for (int i = 0; i < d.length; i++) {
                Object[] o = {initialNode, i, d[i]};
                defaultTableModel = (DefaultTableModel) minimalCosts_Table.getModel();
                defaultTableModel.addRow(o);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChoice_FileChooser = new javax.swing.JFileChooser();
        nodes_Panel = new javax.swing.JPanel();
        nodes_Label = new javax.swing.JLabel();
        arcs_Panel = new javax.swing.JPanel();
        arcs_Label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listOfArcs_Table = new javax.swing.JTable();
        listOfArcs_Label = new javax.swing.JLabel();
        fileChoice_Button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        nodes_ComboBox = new javax.swing.JComboBox();
        algorithme_Bellman = new javax.swing.JButton();
        algorithme_Dijkstra = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        minimalCosts_Table = new javax.swing.JTable();
        listOfArcs_Label1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NF20 : Plus court chemin");
        setResizable(false);

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

        arcs_Label.setFont(new java.awt.Font("Tahoma", 1, 12));
        arcs_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        arcs_Label.setText("0");

        javax.swing.GroupLayout arcs_PanelLayout = new javax.swing.GroupLayout(arcs_Panel);
        arcs_Panel.setLayout(arcs_PanelLayout);
        arcs_PanelLayout.setHorizontalGroup(
            arcs_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, arcs_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(arcs_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
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

        fileChoice_Button.setText("Choisir un fichier d'instance");
        fileChoice_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChoice_ButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Sommet d'origine : ");

        algorithme_Bellman.setText("Effectuer l'algorithme de Bellman-Ford");
        algorithme_Bellman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algorithme_BellmanActionPerformed(evt);
            }
        });

        algorithme_Dijkstra.setText("Effectuer l'algorithme de Dijkstra");

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

        listOfArcs_Label1.setFont(new java.awt.Font("Tahoma", 1, 11));
        listOfArcs_Label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        listOfArcs_Label1.setText("Chemins les plus courts");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(listOfArcs_Label, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nodes_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(arcs_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(fileChoice_Button, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nodes_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(algorithme_Bellman, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(algorithme_Dijkstra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(listOfArcs_Label1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileChoice_Button)
                    .addComponent(jLabel1)
                    .addComponent(nodes_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(algorithme_Bellman)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(algorithme_Dijkstra))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(nodes_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(arcs_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listOfArcs_Label)
                    .addComponent(listOfArcs_Label1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-542)/2, (screenSize.height-433)/2, 542, 433);
    }// </editor-fold>//GEN-END:initComponents

    private void fileChoice_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChoice_ButtonActionPerformed
        chooseFile();
    }//GEN-LAST:event_fileChoice_ButtonActionPerformed

    private void algorithme_BellmanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algorithme_BellmanActionPerformed
        algorithme_Bellman();
    }//GEN-LAST:event_algorithme_BellmanActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new SubstanceOfficeSilver2007LookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
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
    private javax.swing.JButton fileChoice_Button;
    private javax.swing.JFileChooser fileChoice_FileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel listOfArcs_Label;
    private javax.swing.JLabel listOfArcs_Label1;
    private javax.swing.JTable listOfArcs_Table;
    private javax.swing.JTable minimalCosts_Table;
    private javax.swing.JComboBox nodes_ComboBox;
    private javax.swing.JLabel nodes_Label;
    private javax.swing.JPanel nodes_Panel;
    // End of variables declaration//GEN-END:variables
}
