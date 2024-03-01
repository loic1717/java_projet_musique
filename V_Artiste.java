/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.pj_gestion_musique;

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
public class V_Artiste extends javax.swing.JDialog {

    /**
     * Creates new form V_Artiste
     */
    private Pj_gestion_musique leControl;
    private LinkedHashMap<Integer, M_Artiste> lesArtistes;
    private M_Artiste unArtiste;
    private DefaultTableModel dm_tb_Artistes;
    private int ligne, nbArtistes, ligneSelect,iArtiste,nbAlbums;
    private Integer uneCle,cle;
    private int  mode;
    private   LinkedHashMap<Integer, M_Album> albumsArtiste ;
    private LinkedHashMap<Integer,M_Album> lesAlbums ;
    private M_Album album;
    private DefaultTableModel dm_tb_Albumsartist;
    private M_Album unAlbum;
    private M_Interpreter unInterpreter;
    private Object[] lesClesAlbums;
    
    public void afficher(LinkedHashMap<Integer, M_Artiste> lesArtistes,LinkedHashMap<Integer,M_Album> lesAlbums){
    this.lesArtistes = lesArtistes;
    this.lesAlbums = lesAlbums;
    dm_tb_Artistes = (DefaultTableModel) tb_artiste.getModel();
        
        ligne = 0;
        nbArtistes = lesArtistes.size();
        dm_tb_Artistes.setRowCount(nbArtistes);
        
        
        for (Integer uneCle : lesArtistes.keySet()){
            unArtiste = this.lesArtistes.get(uneCle);
            tb_artiste.setValueAt(unArtiste.getIdArtiste(), ligne, 0);
            tb_artiste.setValueAt(unArtiste.getNom(), ligne, 1);
            tb_artiste.setValueAt(unArtiste.getPrenom(), ligne, 2);
            ligne++;
        }
       tb_artiste.clearSelection();
        
    pn_details.setVisible(true);  
    this.modeEdit(false);
    tb_album.setVisible(true);
    pn_boutons.setVisible(true);
    pn_btenregistrer.setVisible(false);
    pn_ajout_album_artiste.setVisible(false);
    pn_bouton_album.setVisible(false);
//    this.setSize(900, 1500);
//    this.setLocationRelativeTo(null);
    this.setVisible(true);
    
    }
        private void modeEdit (Boolean active){
        ed_id.setEditable(active);
        ed_nom.setEditable(active);
        ed_prenom.setEditable(active);
        ed_datenaiss.setEditable(active);
        ed_deces.setEditable(active);
        pn_details.setVisible(active);
      
      
        
      
    }
        private void seVider() {
        ed_id.setVisible(false);
        ed_nom.setText(""); // Vide le champ de titre
        ed_prenom.setText(""); // Vide le champ du nombre de CD
        ed_datenaiss.setText("");
        ed_deces.setText(""); // Vide le champ de la date de sortie
        
    }
       private void sePlacer (){ 
        iArtiste = tb_artiste.getSelectedRow();
        uneCle = (Integer) tb_artiste.getValueAt(iArtiste, 0);
        unArtiste = lesArtistes.get(uneCle);

        // Afficher les informations de l'album dans les champs de texte
        ed_id.setText(Integer.toString(unArtiste.getIdArtiste()));
        ed_nom.setText(unArtiste.getNom());
//        ed_nom_artiste.setText(unAlbum.getNomArtiste());

    
        ed_prenom.setText((unArtiste.getPrenom()));
        ed_datenaiss.setText(unArtiste.getDateNaissance());
        
        //a revoir
        if(unArtiste.getDateDeces()==null){
//        ed_deces.setText("Il est en vie");
        ed_deces.setVisible(false);
        lb_datedeces.setVisible(false);
        
        }
        else{
        ed_deces.setVisible(true);
        lb_datedeces.setVisible(true);
        ed_deces.setText(unArtiste.getDateDeces());
//        ed_style.setText(Integer.toString(unAlbum.getIdStyle()));
        //Sélectionne le bon élément de la liste de rôles
        }

       

    }
       
//       private Map<Integer, Boolean> getAlbumsDansTableau() {
//    Map<Integer, Boolean> albumsDansTableau = new HashMap<>();
//    int rowCount = tb_album.getRowCount();
//
//    for (int i = 0; i < rowCount; i++) {
//        int idAlbum = (int) tb_album.getValueAt(i, 0); 
//        albumsDansTableau.put(idAlbum, true);
//    }
//
//    return albumsDansTableau;
//}
//
//private void chargerComboBoxAlbums(LinkedHashMap<Integer, M_Album> lesAlbums) {
//    this.lesAlbums = lesAlbums;
//    if (lesAlbums != null) {
//        cb_album.removeAllItems();
//
//        Map<Integer, Boolean> albumsDansTableau = getAlbumsDansTableau();
//
//        for (M_Album album : lesAlbums.values()) {
//            if (!albumsDansTableau.containsKey(album.getIdAlbum())) {
//                cb_album.addItem(album.getTitre());
//            }
//        }
//    } else {
//        System.out.println("La liste des albums est vide.");
//    }
//}
  private void chargerComboBoxAlbums(LinkedHashMap<Integer, M_Album> lesAlbums) {
      this.lesAlbums=lesAlbums;
    if (lesAlbums != null ) {
        cb_album.removeAllItems(); 

        for (M_Album album : lesAlbums.values()) {
            cb_album.addItem(album.getTitre()); 
        }
    } else {
        System.out.println("La liste des albums est vide.");
    }
}
   
    public V_Artiste(java.awt.Frame parent, boolean modal,Pj_gestion_musique leControleur) {
        super(parent, modal);
        leControl=leControleur;
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

        pn_boutons = new javax.swing.JPanel();
        bt_modifier = new javax.swing.JButton();
        bt_ajouter = new javax.swing.JButton();
        bt_supprimer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_artiste = new javax.swing.JTable();
        pn_details = new javax.swing.JPanel();
        ed_id = new javax.swing.JTextField();
        ed_nom = new javax.swing.JTextField();
        ed_prenom = new javax.swing.JTextField();
        ed_datenaiss = new javax.swing.JTextField();
        ed_deces = new javax.swing.JTextField();
        lb_id = new javax.swing.JLabel();
        lb_nom = new javax.swing.JLabel();
        lb_prenom = new javax.swing.JLabel();
        lb_datenaiss = new javax.swing.JLabel();
        lb_datedeces = new javax.swing.JLabel();
        pn_btenregistrer = new javax.swing.JPanel();
        bt_annuler = new javax.swing.JButton();
        bt_enregister = new javax.swing.JButton();
        pn_bouton_album = new javax.swing.JPanel();
        bt_ajouter_album = new javax.swing.JButton();
        bt_supprimer_alb = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_album = new javax.swing.JTable();
        pn_ajout_album_artiste = new javax.swing.JPanel();
        cb_album = new javax.swing.JComboBox<>();
        bt_enregistrer_album = new javax.swing.JButton();
        bt_annuler_album = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mn_fichier = new javax.swing.JMenu();
        mi_quitter = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bt_modifier.setText("Modifier");
        bt_modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_modifierActionPerformed(evt);
            }
        });

        bt_ajouter.setText("Ajouter");
        bt_ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ajouterActionPerformed(evt);
            }
        });

        bt_supprimer.setText("Supprimer");
        bt_supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_supprimerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_boutonsLayout = new javax.swing.GroupLayout(pn_boutons);
        pn_boutons.setLayout(pn_boutonsLayout);
        pn_boutonsLayout.setHorizontalGroup(
            pn_boutonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_boutonsLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pn_boutonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bt_supprimer)
                    .addComponent(bt_ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_modifier, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        pn_boutonsLayout.setVerticalGroup(
            pn_boutonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_boutonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_modifier)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_ajouter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_supprimer)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tb_artiste.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "idArtiste", "nom", "prenom"
            }
        ));
        tb_artiste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_artisteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_artiste);

        lb_id.setText("id");

        lb_nom.setText("Nom");

        lb_prenom.setText("Prenom");

        lb_datenaiss.setText("Date_naissance");

        lb_datedeces.setText("Date_deces");

        javax.swing.GroupLayout pn_detailsLayout = new javax.swing.GroupLayout(pn_details);
        pn_details.setLayout(pn_detailsLayout);
        pn_detailsLayout.setHorizontalGroup(
            pn_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_detailsLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(pn_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_datedeces, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pn_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lb_prenom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                        .addComponent(lb_id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lb_datenaiss, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ed_id)
                    .addComponent(ed_nom)
                    .addComponent(ed_prenom)
                    .addComponent(ed_datenaiss)
                    .addComponent(ed_deces, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        pn_detailsLayout.setVerticalGroup(
            pn_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_detailsLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(pn_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ed_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_id))
                .addGap(18, 18, 18)
                .addGroup(pn_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ed_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_nom))
                .addGap(18, 18, 18)
                .addGroup(pn_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ed_prenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_prenom))
                .addGap(18, 18, 18)
                .addGroup(pn_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ed_datenaiss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_datenaiss))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ed_deces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_datedeces))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        bt_annuler.setText("Annuler");
        bt_annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_annulerActionPerformed(evt);
            }
        });

        bt_enregister.setText("Enregistrer");
        bt_enregister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_enregisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_btenregistrerLayout = new javax.swing.GroupLayout(pn_btenregistrer);
        pn_btenregistrer.setLayout(pn_btenregistrerLayout);
        pn_btenregistrerLayout.setHorizontalGroup(
            pn_btenregistrerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_btenregistrerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_enregister)
                .addGap(18, 18, 18)
                .addComponent(bt_annuler)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        pn_btenregistrerLayout.setVerticalGroup(
            pn_btenregistrerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_btenregistrerLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(pn_btenregistrerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_enregister)
                    .addComponent(bt_annuler))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        bt_ajouter_album.setText("Ajouter");
        bt_ajouter_album.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ajouter_albumActionPerformed(evt);
            }
        });

        bt_supprimer_alb.setText("Supprimer");
        bt_supprimer_alb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_supprimer_albActionPerformed(evt);
            }
        });

        tb_album.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "idAlbum", "titreAlbum"
            }
        ));
        jScrollPane2.setViewportView(tb_album);
        if (tb_album.getColumnModel().getColumnCount() > 0) {
            tb_album.getColumnModel().getColumn(0).setResizable(false);
            tb_album.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout pn_bouton_albumLayout = new javax.swing.GroupLayout(pn_bouton_album);
        pn_bouton_album.setLayout(pn_bouton_albumLayout);
        pn_bouton_albumLayout.setHorizontalGroup(
            pn_bouton_albumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_bouton_albumLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_bouton_albumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_supprimer_alb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_ajouter_album, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        pn_bouton_albumLayout.setVerticalGroup(
            pn_bouton_albumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_bouton_albumLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pn_bouton_albumLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pn_bouton_albumLayout.createSequentialGroup()
                        .addComponent(bt_ajouter_album)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_supprimer_alb)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cb_album.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_album.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_albumActionPerformed(evt);
            }
        });

        bt_enregistrer_album.setText("Enregistrer");
        bt_enregistrer_album.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_enregistrer_albumActionPerformed(evt);
            }
        });

        bt_annuler_album.setText("Annuler");
        bt_annuler_album.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_annuler_albumActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_ajout_album_artisteLayout = new javax.swing.GroupLayout(pn_ajout_album_artiste);
        pn_ajout_album_artiste.setLayout(pn_ajout_album_artisteLayout);
        pn_ajout_album_artisteLayout.setHorizontalGroup(
            pn_ajout_album_artisteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_ajout_album_artisteLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pn_ajout_album_artisteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pn_ajout_album_artisteLayout.createSequentialGroup()
                        .addComponent(bt_annuler_album)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_enregistrer_album))
                    .addComponent(cb_album, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        pn_ajout_album_artisteLayout.setVerticalGroup(
            pn_ajout_album_artisteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_ajout_album_artisteLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(cb_album, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(pn_ajout_album_artisteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_enregistrer_album)
                    .addComponent(bt_annuler_album))
                .addContainerGap())
        );

        mn_fichier.setText("Fichier");

        mi_quitter.setText("Quitter");
        mi_quitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_quitterActionPerformed(evt);
            }
        });
        mn_fichier.add(mi_quitter);

        jMenuBar1.add(mn_fichier);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(pn_boutons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(pn_btenregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pn_details, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 30, Short.MAX_VALUE)
                        .addComponent(pn_bouton_album, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(pn_ajout_album_artiste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(pn_boutons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pn_bouton_album, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(pn_details, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(pn_ajout_album_artiste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(80, 80, 80)
                .addComponent(pn_btenregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_annulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_annulerActionPerformed
        // TODO add your handling code here:
        this.modeEdit(false);
        tb_artiste.setEnabled(true);
        tb_artiste.setVisible(true);
        tb_album.setVisible(true);
        tb_album.setEnabled(true);
        this.modeEdit(false);
        pn_boutons.setVisible(true);
        pn_btenregistrer.setVisible(false);
        this.sePlacer();
    }//GEN-LAST:event_bt_annulerActionPerformed

    private void bt_enregisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_enregisterActionPerformed
        // TODO add your handling code here:
         String nom, prenom,date_naissance,date_deces;
         int idArtiste;
         this.modeEdit(false);
        tb_artiste.setEnabled(true);
        pn_boutons.setVisible(true);
        tb_artiste.setVisible(true);
        tb_album.setVisible(true);
        nom = ed_nom.getText();
        prenom = ed_prenom.getText();
        date_naissance = ed_datenaiss.getText();
        date_deces = ed_deces.getText();
       
         if(mode==1){
            idArtiste = Integer.valueOf(ed_id.getText());
            try {

                leControl.modif_Artiste(idArtiste, nom, prenom, date_naissance, date_deces);
            } catch (SQLException ex) {
                Logger.getLogger(V_Artiste.class.getName()).log(Level.SEVERE, null, ex);
            }
             }
             else{
                 try {
                    leControl.ajout_Artiste(nom, prenom, date_naissance, date_deces);
                 } catch (SQLException ex) {
                     Logger.getLogger(V_Artiste.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
    }//GEN-LAST:event_bt_enregisterActionPerformed

    private void bt_modifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modifierActionPerformed
        // TODO add your handling code here:
        if (tb_artiste.getSelectedRow()!=-1){
            tb_artiste.setEnabled(false);
             tb_artiste.setVisible(false);
             tb_album.setVisible(false);
            pn_boutons.setVisible(false);
            pn_btenregistrer.setVisible(true);
            lb_id.setVisible(false);
            ed_id.setVisible(false);
            this.modeEdit(true);
            mode=1;
        
        
         }
       
           
            
      
        
        
    }//GEN-LAST:event_bt_modifierActionPerformed

    private void bt_ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ajouterActionPerformed
        // TODO add your handling code here:
        this.modeEdit(true);
        pn_details.setVisible(true);
        tb_artiste.setVisible(false);
        mode=0;
        pn_boutons.setVisible(false);
        pn_btenregistrer.setVisible(true);
        ed_id.setVisible(false);
        lb_id.setVisible(false);
        tb_album.setVisible(false);
        mode= 1;
       
   
        
        this.seVider();
    }//GEN-LAST:event_bt_ajouterActionPerformed

    private void bt_supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_supprimerActionPerformed
        // TODO add your handling code here:
               // TODO add your handling code here:
        ligneSelect = tb_artiste.getSelectedRow();
        uneCle = (Integer) tb_artiste.getValueAt(ligneSelect,0);
        int dialogResult = JOptionPane.showConfirmDialog (this, "Etes-vous sûre de vouloir supprimer artiste "+ed_nom.getText()+"  de ?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
        try {
                leControl.sup_Artiste(uneCle);
            } catch (SQLException ex) {
                Logger.getLogger(V_Album.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_bt_supprimerActionPerformed

    private void tb_artisteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_artisteMouseClicked
        // TODO add your handling code here:
     if (pn_boutons.isVisible()) {
        this.sePlacer();
//code pour la recuperation d'album pour chaque artiste
         int selectedRow = tb_artiste.getSelectedRow();
        int idArtiste = (int) tb_artiste.getValueAt(selectedRow, 0);  //la première colonne contient l'ID de l'artiste;

        
        
        try {
            albumsArtiste = leControl.aff_album_artiste(idArtiste);
            
            DefaultTableModel dm_tb_Albumsartist = (DefaultTableModel) tb_album.getModel();
            dm_tb_Albumsartist.setRowCount(albumsArtiste.size());
            
            int ligne = 0;
            for (M_Album unAlbum : albumsArtiste.values()) {
                tb_album.setValueAt(unAlbum.getIdAlbum(), ligne, 0);
                tb_album.setValueAt(unAlbum.getTitre(), ligne, 1);
                ligne++;
//                System.out.println(unAlbum.getIdAlbum());
//                System.out.println(unArtiste.getIdArtiste());
            }
             chargerComboBoxAlbums(lesAlbums);
            pn_details.setVisible(true);
            pn_bouton_album.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(V_Artiste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    }//GEN-LAST:event_tb_artisteMouseClicked

    private void bt_ajouter_albumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ajouter_albumActionPerformed
        // TODO add your handling code here:
          int selectedRow = tb_artiste.getSelectedRow();
          int idArtiste = (int) tb_artiste.getValueAt(selectedRow, 0);  // La première colonne contient l'ID de l'artiste
          pn_ajout_album_artiste.setVisible(true);
        //Remplir la liste déroulante des albums

    }//GEN-LAST:event_bt_ajouter_albumActionPerformed

    private void bt_supprimer_albActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_supprimer_albActionPerformed
        // TODO add your handling code here:
         int selectedRow = tb_artiste.getSelectedRow();
        int idArtiste = (int) tb_artiste.getValueAt(selectedRow, 0);  //la première colonne contient l'ID de l'artiste;
        ligneSelect = tb_album.getSelectedRow();
        uneCle = (Integer) tb_album.getValueAt(ligneSelect,0);
        int dialogResult = JOptionPane.showConfirmDialog (this, "Etes-vous sûre de vouloir supprimer l'album ","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
       
            try {
              leControl.sup_album_artiste(idArtiste, uneCle);
            } catch (SQLException ex) {
                Logger.getLogger(V_Album.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_bt_supprimer_albActionPerformed

    private void mi_quitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_quitterActionPerformed
        // TODO add your handling code here:
          this.setVisible(false);
    }//GEN-LAST:event_mi_quitterActionPerformed

    private void bt_enregistrer_albumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_enregistrer_albumActionPerformed
        // TODO add your handling code here:
        // int selectedAlbumIndex = cb_album.getSelectedIndex();
         lesClesAlbums= lesAlbums.keySet().toArray();
    // Vérifie si un album est sélectionné
   // if (selectedAlbumIndex != -1) {
        // Récupérer l'album sélectionné
      int  idAlbum = (int) lesClesAlbums[cb_album.getSelectedIndex()];
        
        // Enregistrer cet album pour l'artiste sélectionné
        int selectedArtistRow = tb_artiste.getSelectedRow();
        int idArtiste = (int) tb_artiste.getValueAt(selectedArtistRow, 0);
        
             try {
                 

                 leControl.aj_album_artiste(idArtiste, idAlbum);
             } catch (SQLException ex) {
                 Logger.getLogger(V_Artiste.class.getName()).log(Level.SEVERE, null, ex);
             }
               pn_ajout_album_artiste.setVisible(false);
        
//    } else {
//        
//    }
        
    }//GEN-LAST:event_bt_enregistrer_albumActionPerformed

    private void bt_annuler_albumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_annuler_albumActionPerformed
        // TODO add your handling code here:
        pn_bouton_album.setVisible(false);
        pn_ajout_album_artiste.setVisible(false);
    }//GEN-LAST:event_bt_annuler_albumActionPerformed

    private void cb_albumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_albumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_albumActionPerformed

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
            java.util.logging.Logger.getLogger(V_Artiste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(V_Artiste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(V_Artiste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(V_Artiste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                V_Artiste dialog = new V_Artiste(new javax.swing.JFrame(), true,null);
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
    private javax.swing.JButton bt_ajouter;
    private javax.swing.JButton bt_ajouter_album;
    private javax.swing.JButton bt_annuler;
    private javax.swing.JButton bt_annuler_album;
    private javax.swing.JButton bt_enregister;
    private javax.swing.JButton bt_enregistrer_album;
    private javax.swing.JButton bt_modifier;
    private javax.swing.JButton bt_supprimer;
    private javax.swing.JButton bt_supprimer_alb;
    private javax.swing.JComboBox<String> cb_album;
    private javax.swing.JTextField ed_datenaiss;
    private javax.swing.JTextField ed_deces;
    private javax.swing.JTextField ed_id;
    private javax.swing.JTextField ed_nom;
    private javax.swing.JTextField ed_prenom;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_datedeces;
    private javax.swing.JLabel lb_datenaiss;
    private javax.swing.JLabel lb_id;
    private javax.swing.JLabel lb_nom;
    private javax.swing.JLabel lb_prenom;
    private javax.swing.JMenuItem mi_quitter;
    private javax.swing.JMenu mn_fichier;
    private javax.swing.JPanel pn_ajout_album_artiste;
    private javax.swing.JPanel pn_bouton_album;
    private javax.swing.JPanel pn_boutons;
    private javax.swing.JPanel pn_btenregistrer;
    private javax.swing.JPanel pn_details;
    private javax.swing.JTable tb_album;
    private javax.swing.JTable tb_artiste;
    // End of variables declaration//GEN-END:variables
}
