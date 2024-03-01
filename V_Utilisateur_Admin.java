/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.pj_gestion_musique;

import java.awt.Color;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author COMPAORÉ Loïc
 */
public class V_Utilisateur_Admin extends javax.swing.JDialog {
  private LinkedHashMap<Integer, M_Utilisateur> lesUtilisateurs;
    private M_Utilisateur unUtilisateur;
    private LinkedHashMap <Integer, M_Role> lesRoles;
    private M_Role unRole;
    
    private Object[] lesClesRoles, tableauDesIDRoles;
    
    private DefaultTableModel dm_tb_Utilisateurs;
    
    private int nbUtilisateurs, iUtilisateur, ligne, ligneSelect;    
    private Integer uneCle;
    private Boolean modif;
    
    private Pj_gestion_musique leControl;
    
    public void afficher (LinkedHashMap<Integer, M_Utilisateur> lesUtilisateurs, LinkedHashMap <Integer, M_Role> lesRoles){
        this.lesUtilisateurs = lesUtilisateurs;
        this.lesRoles = lesRoles;
           tableauDesIDRoles= lesRoles.keySet().toArray();
        lesClesRoles = lesRoles.keySet().toArray();
        
        dm_tb_Utilisateurs = new DefaultTableModel();
        dm_tb_Utilisateurs = (DefaultTableModel) tb_Utilisateurs.getModel();
        
        nbUtilisateurs = lesUtilisateurs.size();
        dm_tb_Utilisateurs.setRowCount(nbUtilisateurs);
        ligne = 0;
        for (Integer uneCle : lesUtilisateurs.keySet()){
            unUtilisateur = lesUtilisateurs.get(uneCle);
            tb_Utilisateurs.setValueAt(unUtilisateur.getIdUtulisateur(), ligne, 0);
            tb_Utilisateurs.setValueAt(unUtilisateur.getNom(), ligne, 1);
            tb_Utilisateurs.setValueAt(unUtilisateur.getPrenom(), ligne, 2);        
            ligne++;
        }
        
        //Remplir la liste déroulante des marques
        cb_Role.removeAllItems();
        for (Integer uneCle : lesRoles.keySet()){
            unRole = lesRoles.get(uneCle);
            cb_Role.addItem(unRole.getLibelle());
        }
        
        //iUtilisateur = 0;
        //tb_Utilisateurs.setRowSelectionInterval(iUtilisateur, iUtilisateur);
        tb_Utilisateurs.clearSelection();
        ed_Id.setText("");
        ed_Login.setText("");
        ed_Nom.setText("");
        ed_Prenom.setText("");
        ed_Telephone.setText("");
        this.modeEdit(false);
        
        lb_MotPasse.setVisible(false);
        ed_MotPasse.setVisible(false);
        bt_Generer.setVisible(false);
        
        pn_Boutons.setVisible(true);
        pn_Bt_Enreg.setVisible(false);
        pn_Details.setVisible(false);
        
        
         cb_role_filter.removeAllItems(); // Efface les éléments existants

                cb_role_filter.addItem("Tout les utilisateurs");
                for (Integer uneCle : lesRoles.keySet()) {
                    unRole= lesRoles.get(uneCle);
                    cb_role_filter.addItem(unRole.getLibelle());
                }
        this.setSize(540, 640);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }   
     private void modeEdit (Boolean active){
        ed_Id.setEditable(active);
        ed_Login.setEditable(active);
        ed_Nom.setEditable(active);
        ed_Prenom.setEditable(active);
        ed_Telephone.setEditable(active);
        pn_Bt_Enreg.setVisible(active);
        cb_Role.setEnabled(active);
        cb_Role.setForeground(Color.BLACK);
    }
    
    
    private void sePlacer (){
        iUtilisateur = tb_Utilisateurs.getSelectedRow();
        uneCle = (Integer) tb_Utilisateurs.getValueAt(iUtilisateur, 0);
        unUtilisateur = lesUtilisateurs.get(uneCle);
        ed_Id.setText(Integer.toString(unUtilisateur.getIdUtulisateur()));
        ed_Nom.setText(unUtilisateur.getNom());
        ed_Prenom.setText(unUtilisateur.getPrenom());
        ed_Telephone.setText(unUtilisateur.getTelephone());
        ed_Login.setText(unUtilisateur.getLogin());
        
        //Sélectionne le bon élément de la liste de rôles
        //Récupérer l'idRole du membre
        int idRole = unUtilisateur.getIdRole();
        //Récupérer le libellé de cet idRole
        M_Role unRole = lesRoles.get(idRole);
        String lib = unRole.getLibelle();
        //String lib = lesRoles.get(idRole).getLibelle();
        //Sélectionner dans la combobox le libelle récupéré
        cb_Role.setSelectedItem(lib);
    }
            
    private void vider (){
        ed_Id.setVisible(false);
        lb_Id.setVisible(false);
        ed_Login.setText("");
        ed_Nom.setText("");
        ed_Prenom.setText("");
        ed_Telephone.setText("");
        cb_Role.setSelectedIndex(-1);
    }   

    /**
     * Creates new form V_Utilisateur_Admin
     */
    public V_Utilisateur_Admin(java.awt.Frame parent, boolean modal,Pj_gestion_musique leControleur) {
        super(parent, modal);
        leControl = leControleur;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Utilisateurs = new javax.swing.JTable();
        pn_Boutons = new javax.swing.JPanel();
        bt_Modifier = new javax.swing.JButton();
        bt_Supprimer = new javax.swing.JButton();
        bt_Ajouter = new javax.swing.JButton();
        pn_Details = new javax.swing.JPanel();
        ed_Id = new javax.swing.JTextField();
        ed_Nom = new javax.swing.JTextField();
        ed_Prenom = new javax.swing.JTextField();
        ed_Telephone = new javax.swing.JTextField();
        ed_Login = new javax.swing.JTextField();
        ed_MotPasse = new javax.swing.JTextField();
        lb_Id = new javax.swing.JLabel();
        lb_Nom = new javax.swing.JLabel();
        lb_Prenom = new javax.swing.JLabel();
        lb_Telephone = new javax.swing.JLabel();
        lb_Login = new javax.swing.JLabel();
        lb_MotPasse = new javax.swing.JLabel();
        cb_Role = new javax.swing.JComboBox<>();
        bt_Generer = new javax.swing.JButton();
        lb_Role = new javax.swing.JLabel();
        pn_Bt_Enreg = new javax.swing.JPanel();
        bt_Enregistrer = new javax.swing.JButton();
        bt_Annuler = new javax.swing.JButton();
        cb_role_filter = new javax.swing.JComboBox<>();
        jMenuBar2 = new javax.swing.JMenuBar();
        mn_fichier = new javax.swing.JMenu();
        mi_quitter = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tb_Utilisateurs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nom", "Prenom"
            }
        ));
        tb_Utilisateurs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_UtilisateursMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Utilisateurs);

        bt_Modifier.setText("Modifier");
        bt_Modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ModifierActionPerformed(evt);
            }
        });

        bt_Supprimer.setText("Supprimer");
        bt_Supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_SupprimerActionPerformed(evt);
            }
        });

        bt_Ajouter.setText("Ajouter");
        bt_Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_AjouterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_BoutonsLayout = new javax.swing.GroupLayout(pn_Boutons);
        pn_Boutons.setLayout(pn_BoutonsLayout);
        pn_BoutonsLayout.setHorizontalGroup(
            pn_BoutonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_BoutonsLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(pn_BoutonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bt_Supprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_Ajouter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_Modifier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        pn_BoutonsLayout.setVerticalGroup(
            pn_BoutonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_BoutonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_Modifier)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_Supprimer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_Ajouter)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lb_Id.setText("Id:");

        lb_Nom.setText("Nom:");

        lb_Prenom.setText("Prenom:");

        lb_Telephone.setText("Telephone:");

        lb_Login.setText("Login:");

        lb_MotPasse.setText("Mots Passe:");

        cb_Role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        bt_Generer.setText("Generer");
        bt_Generer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_GenererActionPerformed(evt);
            }
        });

        lb_Role.setText("Role:");

        javax.swing.GroupLayout pn_DetailsLayout = new javax.swing.GroupLayout(pn_Details);
        pn_Details.setLayout(pn_DetailsLayout);
        pn_DetailsLayout.setHorizontalGroup(
            pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_DetailsLayout.createSequentialGroup()
                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_DetailsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lb_Prenom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lb_Telephone, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                            .addComponent(lb_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_Role, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_MotPasse, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lb_Nom, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_Role, 0, 150, Short.MAX_VALUE)
                    .addComponent(ed_Id)
                    .addComponent(ed_Nom)
                    .addComponent(ed_Prenom)
                    .addComponent(ed_Telephone)
                    .addComponent(ed_Login)
                    .addComponent(ed_MotPasse))
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_DetailsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bt_Generer)
                .addGap(63, 63, 63))
        );
        pn_DetailsLayout.setVerticalGroup(
            pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_DetailsLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pn_DetailsLayout.createSequentialGroup()
                        .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ed_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_Id))
                        .addGap(18, 18, 18)
                        .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ed_Nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_Nom))
                        .addGap(18, 18, 18)
                        .addComponent(ed_Prenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_Prenom))
                .addGap(18, 18, 18)
                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ed_Telephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Telephone))
                .addGap(28, 28, 28)
                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ed_Login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Login))
                .addGap(18, 18, 18)
                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_Role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Role, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ed_MotPasse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_MotPasse))
                .addGap(18, 18, 18)
                .addComponent(bt_Generer)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        bt_Enregistrer.setText("Enregistrer");
        bt_Enregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_EnregistrerActionPerformed(evt);
            }
        });

        bt_Annuler.setText("Annuler");
        bt_Annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_AnnulerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_Bt_EnregLayout = new javax.swing.GroupLayout(pn_Bt_Enreg);
        pn_Bt_Enreg.setLayout(pn_Bt_EnregLayout);
        pn_Bt_EnregLayout.setHorizontalGroup(
            pn_Bt_EnregLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Bt_EnregLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pn_Bt_EnregLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_Enregistrer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_Annuler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        pn_Bt_EnregLayout.setVerticalGroup(
            pn_Bt_EnregLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Bt_EnregLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(bt_Enregistrer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_Annuler)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        cb_role_filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_role_filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_role_filterActionPerformed(evt);
            }
        });

        mn_fichier.setText("Fichier");

        mi_quitter.setText("Quitter");
        mi_quitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_quitterActionPerformed(evt);
            }
        });
        mn_fichier.add(mi_quitter);

        jMenuBar2.add(mn_fichier);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pn_Boutons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cb_role_filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(pn_Details, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(pn_Bt_Enreg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_Boutons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_role_filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addComponent(pn_Bt_Enreg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(233, 233, 233))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(pn_Details, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_GenererActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_GenererActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_GenererActionPerformed

    private void bt_EnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_EnregistrerActionPerformed
        // TODO add your handling code here:
              String nom, prenom, telephone, login, motPasse;
        int idRole, idUtilisateur;
        
        this.modeEdit(false);
        tb_Utilisateurs.setEnabled(true);
        pn_Boutons.setVisible(true);
        
        nom = ed_Nom.getText();
        prenom = ed_Prenom.getText();
        telephone = ed_Telephone.getText();
        login = ed_Login.getText();
        lb_Id.setVisible(false);
        ed_Id.setVisible(false);
        
        idRole = (int) lesClesRoles[cb_Role.getSelectedIndex()];
        System.out.println(idRole);
        
        if (modif){
            //modification
            idUtilisateur = Integer.valueOf(ed_Id.getText());
            
            
                  try {
                      leControl.modif_Utilisateur(idUtilisateur, nom, prenom, telephone, login, idRole);
                  } catch (SQLException ex) {
                      Logger.getLogger(V_Utilisateur_Admin.class.getName()).log(Level.SEVERE, null, ex);
                  }
           
            
        }
        else {
            //ajout
            motPasse = ed_MotPasse.getText();
            
            
           
                  try {
                      leControl.ajout_Utilisateur(nom, prenom, telephone, login, idRole, motPasse);
                  } catch (SQLException ex) {
                      Logger.getLogger(V_Utilisateur_Admin.class.getName()).log(Level.SEVERE, null, ex);
                  }
           
            ed_Id.setVisible(true);
            lb_Id.setVisible(true);
            ed_Id.setEnabled(false);
            tb_Utilisateurs.setVisible(true);
            //leControl.aff_V_Membres_Admin
        }
    }//GEN-LAST:event_bt_EnregistrerActionPerformed

    private void bt_AnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_AnnulerActionPerformed
        // TODO add your handling code here:
         this.modeEdit(false);
        tb_Utilisateurs.setEnabled(true);
        pn_Boutons.setVisible(true);
        pn_Details.setVisible(false);
        tb_Utilisateurs.setVisible(true);
        lb_MotPasse.setVisible(false);
        ed_MotPasse.setVisible(false);
        bt_Generer.setVisible(false);
        this.sePlacer();
        
    }//GEN-LAST:event_bt_AnnulerActionPerformed

    private void bt_SupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_SupprimerActionPerformed
        // TODO add your handling code here:
         ligneSelect = tb_Utilisateurs.getSelectedRow();
        uneCle = (Integer) tb_Utilisateurs.getValueAt(ligneSelect,0);
        int dialogResult = JOptionPane.showConfirmDialog (this, "Etes-vous sûre de vouloir supprimer le membre "+ed_Prenom.getText()+" "+ed_Nom.getText()+" ?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
             try {
                 leControl.sup_Utilisateur(uneCle);
             } catch (SQLException ex) {
                 Logger.getLogger(V_Utilisateur_Admin.class.getName()).log(Level.SEVERE, null, ex);
             }
        
        } 
    }//GEN-LAST:event_bt_SupprimerActionPerformed

    private void tb_UtilisateursMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_UtilisateursMouseClicked
        // TODO add your handling code here:
           if (pn_Boutons.isVisible()){
        this.sePlacer();
        pn_Details.setVisible(true);
        //modeEdit(false);
       }
    }//GEN-LAST:event_tb_UtilisateursMouseClicked

    private void bt_ModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ModifierActionPerformed
        // TODO add your handling code here:
        lb_Id.setVisible(false);
        ed_Id.setVisible(false);
        if (tb_Utilisateurs.getSelectedRow()!=-1){
            this.modeEdit(true);
            tb_Utilisateurs.setEnabled(false);
            pn_Boutons.setVisible(false);
                        
            //this.sePlacer();
            modif = true;
        }
    }//GEN-LAST:event_bt_ModifierActionPerformed

    private void bt_AjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_AjouterActionPerformed
        // TODO add your handling code here:
        this.modeEdit(true);
        tb_Utilisateurs.setVisible(false);
        pn_Boutons.setVisible(false);
        pn_Details.setVisible(true);
        
        lb_MotPasse.setVisible(true);
        ed_MotPasse.setVisible(true);
        ed_MotPasse.setEditable(true);
        bt_Generer.setVisible(true);
        
        this.vider();
        modif = false;  
    }//GEN-LAST:event_bt_AjouterActionPerformed

    private void mi_quitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_quitterActionPerformed
        // TODO add your handling code here:
          this.setVisible(false);
    }//GEN-LAST:event_mi_quitterActionPerformed

    private void cb_role_filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_role_filterActionPerformed
        // TODO add your handling code here:
        int selectedIndex = cb_role_filter.getSelectedIndex();
        
        if (selectedIndex >= 1) {
      
        
            int selectedroleID = (int) tableauDesIDRoles[selectedIndex-1] ;
            
            unRole = lesRoles.get(selectedroleID);
            
            String selectedLib = unRole.getLibelle();// Récupére le libellé du role
            
            cb_role_filter.setSelectedItem(selectedLib); // Affiche l'élément sélectionné;
            try {
                leControl.afficherroleAvecClauseWhere("idRole = " + selectedroleID);
            } catch (SQLException ex) {
                Logger.getLogger(V_Utilisateur_Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
             
                System.out.println("le nombre id styles est"+selectedIndex);
                }
    }//GEN-LAST:event_cb_role_filterActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(V_Utilisateur_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(V_Utilisateur_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(V_Utilisateur_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(V_Utilisateur_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                V_Utilisateur_Admin dialog = new V_Utilisateur_Admin(new javax.swing.JFrame(), true,null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Ajouter;
    private javax.swing.JButton bt_Annuler;
    private javax.swing.JButton bt_Enregistrer;
    private javax.swing.JButton bt_Generer;
    private javax.swing.JButton bt_Modifier;
    private javax.swing.JButton bt_Supprimer;
    private javax.swing.JComboBox<String> cb_Role;
    private javax.swing.JComboBox<String> cb_role_filter;
    private javax.swing.JTextField ed_Id;
    private javax.swing.JTextField ed_Login;
    private javax.swing.JTextField ed_MotPasse;
    private javax.swing.JTextField ed_Nom;
    private javax.swing.JTextField ed_Prenom;
    private javax.swing.JTextField ed_Telephone;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_Id;
    private javax.swing.JLabel lb_Login;
    private javax.swing.JLabel lb_MotPasse;
    private javax.swing.JLabel lb_Nom;
    private javax.swing.JLabel lb_Prenom;
    private javax.swing.JLabel lb_Role;
    private javax.swing.JLabel lb_Telephone;
    private javax.swing.JMenuItem mi_quitter;
    private javax.swing.JMenu mn_fichier;
    private javax.swing.JPanel pn_Boutons;
    private javax.swing.JPanel pn_Bt_Enreg;
    private javax.swing.JPanel pn_Details;
    private javax.swing.JTable tb_Utilisateurs;
    // End of variables declaration//GEN-END:variables
}
