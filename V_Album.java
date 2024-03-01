/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.pj_gestion_musique;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author COMPAORÉ Loïc
 */
public class V_Album extends javax.swing.JDialog {
    private Pj_gestion_musique leControl;

    private LinkedHashMap <Integer, M_Style>lesStyles;
    private M_Style unStyle;
    private LinkedHashMap<Integer, M_Album>lesAlbums;
    private M_Album unAlbum;
    private Object[] lesClesStyles,tableauDesIDsDeStyle;
    private DefaultTableModel dm_tb_Albums;
     private int ligne, nbAlbums,ligneSelect;
    private Integer uneCle;
    private int mode,iAlbum;

    public void afficher(LinkedHashMap<Integer,M_Album>lesAlbums, LinkedHashMap <Integer, M_Style> lesStyles){

        this.lesAlbums= lesAlbums;
        this.lesStyles=lesStyles;
        lesClesStyles= lesStyles.keySet().toArray();
        tableauDesIDsDeStyle= lesStyles.keySet().toArray();
        dm_tb_Albums = (DefaultTableModel) tb_album.getModel();

            ligne = 0;
            nbAlbums= lesAlbums.size();
            dm_tb_Albums.setRowCount(nbAlbums);


            for (Integer uneCle : lesAlbums.keySet()){
                unAlbum= lesAlbums.get(uneCle);
                tb_album.setValueAt(unAlbum.getIdAlbum(), ligne, 0);
                tb_album.setValueAt(unAlbum.getTitre(), ligne, 1);
                ligne++;
            }

             //Remplir la liste déroulante des Styles
          cb_Style.removeAllItems(); // Efface les éléments existants

            if (lesStyles != null) { // Vérifie si lesStyles ne sont pas nuls
                for (Integer uneCle : lesStyles.keySet()) {
                    unStyle = lesStyles.get(uneCle);
                    cb_Style.addItem(unStyle.getLibelle());
                }
                } else {
                    // Gérer le cas où lesStyles est nul (par exemple, ajouter un élément par défaut ou afficher un message d'erreur)
                    cb_Style.addItem("Aucun style disponible"); // Exemple : ajoute un élément par défaut
                    // Ou affiche un message d'erreur
                    // JOptionPane.showMessageDialog(this, "Les styles sont nuls.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

                cb_choix_style.removeAllItems(); // Efface les éléments existants

                cb_choix_style.addItem("Tout les Styles");
                for (Integer uneCle : lesStyles.keySet()) {
                    unStyle = lesStyles.get(uneCle);
                    cb_choix_style.addItem(unStyle.getLibelle());
                }

               tb_album.clearSelection();



            pn_cms_Albums.setVisible(true);
            pn_Details.setVisible(false);
            pn_Boutons.setVisible(true);
            pn_bouton_enregitrer.setVisible(false);
            pn_Details.setVisible(false);
            this.modeEdit(false);


            this.setSize(800, 720);
            this.setLocationRelativeTo(null);
            this.setVisible(true);



    }

    private void modeEdit (Boolean active){
        ed_Id.setEditable(active);
        ed_commentaire.setEditable(active);
        ed_nombrecd.setEditable(active);
        ed_titre.setEditable(active);
        ed_nombreMorceaux.setEditable(active);
        ed_date_sortie.setEditable(active);
        ed_commentaire.setEditable(active);
      
        pn_Details.setVisible(active);
        cb_Style.setEnabled(active);
        rb_false.setEnabled(active);
        rb_true.setEnabled(active);
      
        
      
    }
    private void sePlacer (){
        iAlbum = tb_album.getSelectedRow();
        uneCle = (Integer) tb_album.getValueAt(iAlbum, 0);
        unAlbum = lesAlbums.get(uneCle);

        // Afficher les informations de l'album dans les champs de texte
        ed_Id.setText(Integer.toString(unAlbum.getIdAlbum()));
        ed_titre.setText(unAlbum.getTitre());
//        ed_nom_artiste.setText(unAlbum.getNomArtiste());

          if (unAlbum.isLive()) {
            rb_true.setSelected(true);
            rb_false.setSelected(false);
            } else {
            rb_true.setSelected(false);
            rb_false.setSelected(true);
            }

        
        ed_nombrecd.setText(Integer.toString(unAlbum.getNbCD()));
        ed_nombreMorceaux.setText(Integer.toString( unAlbum.getNbMorceaux()));
        ed_date_sortie.setText(unAlbum.getDateSortie());
//        ed_commentaire.setText(unAlbum.getCommentaire());
//        ed_style.setText(Integer.toString(unAlbum.getIdStyle()));
        //Sélectionne le bon élément de la liste de rôles
    

    int idStyle = unAlbum.getIdStyle();

    unStyle = lesStyles.get(idStyle);
    String lib = unStyle.getLibelle();
    // Sélectionner l'élément approprié dans la combobox en fonction du libellé du style
    cb_Style.setSelectedItem(lib);

    }
    
    private void seVider() {
        ed_Id.setVisible(false);
        ed_titre.setText(""); // Vide le champ de titre
       
        rb_true.setSelected(true); // Décoche le bouton radio "OUI" pour le statut "live" (assumant que "OUI" est la valeur par défaut)
        rb_false.setSelected(false); // Coche le bouton radio "NON" pour le statut "live"
        ed_nombrecd.setText(""); // Vide le champ du nombre de CD
        ed_nombreMorceaux.setText("");
        ed_date_sortie.setText(""); // Vide le champ de la date de sortie
        ed_commentaire.setText(""); // Vide le champ de commentaire
        cb_Style.setSelectedIndex(-1);
    }
   private boolean isDateValid(String date) {
    // Utilisez une expression régulière pour valider le format de date (YYYY-MM-DD par exemple)
    String regex = "\\d{4}-\\d{2}-\\d{2}";
    return date.matches(regex);
    } 
    /**
     * Creates new form V_Album
     */
    public V_Album(java.awt.Frame parent, boolean modal,Pj_gestion_musique leControleur) {
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

        jMenuItem1 = new javax.swing.JMenuItem();
        pn_cms_Albums = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_album = new javax.swing.JTable();
        pn_Details = new javax.swing.JPanel();
        lb_id = new javax.swing.JLabel();
        lb_titre = new javax.swing.JLabel();
        lb_live = new javax.swing.JLabel();
        ed_Id = new javax.swing.JTextField();
        ed_nombrecd = new javax.swing.JTextField();
        ed_titre = new javax.swing.JTextField();
        lb_nombre_cd = new javax.swing.JLabel();
        lb_date = new javax.swing.JLabel();
        lb_commentaire = new javax.swing.JLabel();
        rb_true = new javax.swing.JRadioButton();
        rb_false = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ed_commentaire = new javax.swing.JTextPane();
        ed_date_sortie = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cb_Style = new javax.swing.JComboBox<>();
        lb_nbMorceaux = new javax.swing.JLabel();
        ed_nombreMorceaux = new javax.swing.JTextField();
        pn_Boutons = new javax.swing.JPanel();
        bt_ajout = new javax.swing.JButton();
        bt_modif = new javax.swing.JButton();
        bt_supp = new javax.swing.JButton();
        cb_choix_style = new javax.swing.JComboBox<>();
        pn_bouton_enregitrer = new javax.swing.JPanel();
        bt_enregist = new javax.swing.JButton();
        bt_annul = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mi_Quitter = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tb_album.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Titre"
            }
        ));
        tb_album.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_album.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_album.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_albumMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_album);

        javax.swing.GroupLayout pn_cms_AlbumsLayout = new javax.swing.GroupLayout(pn_cms_Albums);
        pn_cms_Albums.setLayout(pn_cms_AlbumsLayout);
        pn_cms_AlbumsLayout.setHorizontalGroup(
            pn_cms_AlbumsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_cms_AlbumsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        pn_cms_AlbumsLayout.setVerticalGroup(
            pn_cms_AlbumsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_cms_AlbumsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pn_Details.setEnabled(false);

        lb_id.setText("Id :");

        lb_titre.setText("Titre :");

        lb_live.setText("Live :");

        ed_Id.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ed_Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ed_IdActionPerformed(evt);
            }
        });

        lb_nombre_cd.setText("Nombre de CD :");

        lb_date.setText("Dade de Sortie :");

        lb_commentaire.setText("Commentaire :");

        rb_true.setText("OUI");
        rb_true.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_trueActionPerformed(evt);
            }
        });

        rb_false.setText("NON");
        rb_false.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_falseActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(ed_commentaire);

        jLabel1.setText("Style de Musique :");

        cb_Style.setForeground(new java.awt.Color(102, 204, 0));
        cb_Style.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lb_nbMorceaux.setText("Nombre de Morceaux :");

        javax.swing.GroupLayout pn_DetailsLayout = new javax.swing.GroupLayout(pn_Details);
        pn_Details.setLayout(pn_DetailsLayout);
        pn_DetailsLayout.setHorizontalGroup(
            pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_DetailsLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_DetailsLayout.createSequentialGroup()
                        .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_DetailsLayout.createSequentialGroup()
                                .addComponent(lb_titre, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pn_DetailsLayout.createSequentialGroup()
                                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_nombre_cd, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_date, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_nbMorceaux, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_live, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(ed_date_sortie, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ed_nombrecd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                            .addComponent(ed_nombreMorceaux, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_Style, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pn_DetailsLayout.createSequentialGroup()
                                .addComponent(rb_true, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(rb_false, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ed_titre, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_DetailsLayout.createSequentialGroup()
                        .addComponent(lb_id, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ed_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(pn_DetailsLayout.createSequentialGroup()
                        .addComponent(lb_commentaire, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        pn_DetailsLayout.setVerticalGroup(
            pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_DetailsLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_id, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ed_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_titre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ed_titre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_false)
                    .addComponent(rb_true)
                    .addComponent(lb_live, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_nombre_cd)
                    .addComponent(ed_nombrecd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_nbMorceaux)
                    .addComponent(ed_nombreMorceaux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_date)
                    .addComponent(ed_date_sortie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb_Style, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pn_DetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_DetailsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(lb_commentaire)
                        .addGap(148, 148, 148))
                    .addGroup(pn_DetailsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        bt_ajout.setText("Ajouter");
        bt_ajout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ajoutActionPerformed(evt);
            }
        });

        bt_modif.setText("Modifier");
        bt_modif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_modifActionPerformed(evt);
            }
        });

        bt_supp.setText("Supprimer");
        bt_supp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_suppActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_BoutonsLayout = new javax.swing.GroupLayout(pn_Boutons);
        pn_Boutons.setLayout(pn_BoutonsLayout);
        pn_BoutonsLayout.setHorizontalGroup(
            pn_BoutonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_BoutonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_BoutonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_ajout, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_modif, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_supp, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        pn_BoutonsLayout.setVerticalGroup(
            pn_BoutonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_BoutonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_ajout, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_modif, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_supp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        cb_choix_style.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_choix_style.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_choix_styleActionPerformed(evt);
            }
        });

        bt_enregist.setText("Enregistrer");
        bt_enregist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_enregistActionPerformed(evt);
            }
        });

        bt_annul.setText("Annuler");
        bt_annul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_annulActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_bouton_enregitrerLayout = new javax.swing.GroupLayout(pn_bouton_enregitrer);
        pn_bouton_enregitrer.setLayout(pn_bouton_enregitrerLayout);
        pn_bouton_enregitrerLayout.setHorizontalGroup(
            pn_bouton_enregitrerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_bouton_enregitrerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_enregist)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_annul)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        pn_bouton_enregitrerLayout.setVerticalGroup(
            pn_bouton_enregitrerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_bouton_enregitrerLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(pn_bouton_enregitrerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_enregist)
                    .addComponent(bt_annul))
                .addGap(35, 35, 35))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "idArtiste", "nom"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jMenu1.setText("Fichier");

        mi_Quitter.setText("Quitter");
        mi_Quitter.setActionCommand("");
        mi_Quitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_QuitterActionPerformed(evt);
            }
        });
        jMenu1.add(mi_Quitter);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pn_cms_Albums, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pn_Details, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(pn_bouton_enregitrer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pn_Boutons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cb_choix_style, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(202, 202, 202))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pn_cms_Albums, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pn_Details, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(cb_choix_style, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(pn_Boutons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pn_bouton_enregitrer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)))
                .addContainerGap(152, Short.MAX_VALUE))
        );

        pn_Details.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ed_IdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ed_IdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ed_IdActionPerformed

    private void rb_falseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_falseActionPerformed
        // TODO add your handling code here:
         if (rb_false.isSelected()) {
            rb_true.setSelected(false);
//          rb_false.setSelected(false);
            } else {
            rb_true.setSelected(true);
//              rb_false.setSelected(true);
            }

    }//GEN-LAST:event_rb_falseActionPerformed

    private void bt_modifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_modifActionPerformed
        // TODO add your handling code here:
        if (tb_album.getSelectedRow()!=-1){
            this.modeEdit(true);
            tb_album.setEnabled(false);
            tb_album.setVisible(false);
            pn_Boutons.setVisible(false);
            pn_bouton_enregitrer.setVisible(true);
            cb_choix_style.setVisible(false);
            lb_id.setVisible(false);
            ed_Id.setVisible(false);
            mode= 0;
            this.modeEdit(true);
      
        }
    }//GEN-LAST:event_bt_modifActionPerformed

    private void bt_suppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_suppActionPerformed
        // TODO add your handling code here:
        ligneSelect = tb_album.getSelectedRow();
        uneCle = (Integer) tb_album.getValueAt(ligneSelect,0);
        int dialogResult = JOptionPane.showConfirmDialog (this, "Etes-vous sûre de vouloir supprimer l'album "+ed_titre.getText()+"  de ?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
       
            try {
                leControl.sup_Albums(uneCle);
            } catch (SQLException ex) {
                Logger.getLogger(V_Album.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_bt_suppActionPerformed
    
    private void bt_ajoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ajoutActionPerformed
        // TODO add your handling code here:
        this.modeEdit(true);
        tb_album.setVisible(false);
        pn_Boutons.setVisible(false);
        pn_Details.setVisible(true);
        pn_bouton_enregitrer.setVisible(true);
        ed_Id.setVisible(false);
        lb_id.setVisible(false);
        cb_choix_style.setVisible(false);
        mode= 1;
       
   
        
        this.seVider();
    }//GEN-LAST:event_bt_ajoutActionPerformed

    private void bt_enregistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_enregistActionPerformed
        // TODO add your handling code here:
        this.modeEdit(false);
        tb_album.setEnabled(true);
        pn_Boutons.setVisible(true);
        pn_bouton_enregitrer.setVisible(false);
        ed_Id.setVisible(true);
        lb_id.setVisible(true);
        tb_album.setVisible(true);
        cb_choix_style.setVisible(true);
        int idAlbum;
        String titre;
        boolean live;
        int nbCD;
        int nbMorceaux;
        String dateSortie;
        String nomArtiste;
        String commentaire;
        int idStyle;
        
         
        titre= ed_titre.getText();
        nbCD = Integer.valueOf(ed_nombrecd.getText());
        nbMorceaux= Integer.valueOf(ed_nombreMorceaux.getText());
        
        commentaire = ed_commentaire.getText();
        dateSortie=ed_date_sortie.getText();
        if (!isDateValid(dateSortie)) {
        // La date n'a pas le format attendu, affichez un message d'erreur
        JOptionPane.showMessageDialog(this, "Le format de la date n'est pas valide. Utilisez YYYY-MM-DD.", "Erreur", JOptionPane.ERROR_MESSAGE);
        return; // Sort de la méthode pour éviter de continuer avec une date invalide
        }
    
        if (rb_true.isSelected()) {
            live = true;
            } else {
            live = false;
            }
        
        idStyle= (int) lesClesStyles[cb_Style.getSelectedIndex()];
        
        System.out.println("le nombre id styles est"+idStyle);
        if(mode==1){
            try {
                  //ajout
               leControl.ajout_Albums(titre, live,nbCD, nbMorceaux, dateSortie,idStyle);
                 
            } catch (SQLException ex) {
                Logger.getLogger(V_Album.class.getName()).log(Level.SEVERE, null, ex);
            }
            ed_Id.setVisible(true);
            lb_id.setVisible(true);
            tb_album.setVisible(true);
        }
        else{
            idAlbum = Integer.valueOf(ed_Id.getText());
            try {
                 //modification
                leControl.modif_Albums(idAlbum, titre, live, nbCD, nbMorceaux ,dateSortie, idStyle);
            } catch (SQLException ex) {
                Logger.getLogger(V_Album.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        
    }//GEN-LAST:event_bt_enregistActionPerformed

    private void tb_albumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_albumMouseClicked
        // TODO add your handling code here:
           if (pn_Boutons.isVisible()){
            this.sePlacer();
            pn_Details.setVisible(true);
           }
    }//GEN-LAST:event_tb_albumMouseClicked
    
    private void bt_annulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_annulActionPerformed
        // TODO add your handling code here:
        this.modeEdit(false);
        tb_album.setEnabled(true);
        tb_album.setVisible(true);
        this.modeEdit(false);
        pn_Boutons.setVisible(true);
        pn_bouton_enregitrer.setVisible(false);
        cb_choix_style.setVisible(true);
        this.sePlacer();
        
    }//GEN-LAST:event_bt_annulActionPerformed

    private void cb_choix_styleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_choix_styleActionPerformed
        // TODO add your handling code here:
        
        int selectedIndex = cb_choix_style.getSelectedIndex();
        
        if (selectedIndex >= 1) {
      
        
            int selectedStyleID = (int) tableauDesIDsDeStyle[selectedIndex-1] ;
            
            unStyle = lesStyles.get(selectedStyleID);
            
            String selectedLib = unStyle.getLibelle();// Récupére le libellé du style
            
            cb_choix_style.setSelectedItem(selectedLib); // Affiche l'élément sélectionné;
        try {
                    leControl.afficherAlbumsAvecClauseWhere("idStyle = " + selectedStyleID);
                } catch (SQLException ex) {
                    Logger.getLogger(V_Album.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("le nombre id styles est"+selectedIndex);
                }
//                else if((selectedIndex-1)==-1){
//        //        else{
//                                try {
//                                    leControl.aff_V_AlbumsTous();
//                                } catch (SQLException ex) {
//                                    Logger.getLogger(V_Album.class.getName()).log(Level.SEVERE, null, ex);
//
//                        }
//
//                    System.out.println("le nombre id styles est"+selectedIndex);
//                        this.afficher(lesAlbums, lesStyles);
//                }

    }//GEN-LAST:event_cb_choix_styleActionPerformed

    private void mi_QuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_QuitterActionPerformed
        // TODO add your handling code here:
         this.setVisible(false);
    }//GEN-LAST:event_mi_QuitterActionPerformed

    private void rb_trueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_trueActionPerformed
        // TODO add your handling code here:
        if (rb_true.isSelected()) {
//            rb_true.setSelected(true);
            rb_false.setSelected(false);
            } else {
//            rb_true.setSelected(false);
            rb_false.setSelected(true);
            }
    }//GEN-LAST:event_rb_trueActionPerformed
    
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
            java.util.logging.Logger.getLogger(V_Album.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(V_Album.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(V_Album.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(V_Album.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                V_Album dialog = new V_Album(new javax.swing.JFrame(), true,null);
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
    private javax.swing.JButton bt_ajout;
    private javax.swing.JButton bt_annul;
    private javax.swing.JButton bt_enregist;
    private javax.swing.JButton bt_modif;
    private javax.swing.JButton bt_supp;
    private javax.swing.JComboBox<String> cb_Style;
    private javax.swing.JComboBox<String> cb_choix_style;
    private javax.swing.JTextField ed_Id;
    private javax.swing.JTextPane ed_commentaire;
    private javax.swing.JTextField ed_date_sortie;
    private javax.swing.JTextField ed_nombreMorceaux;
    private javax.swing.JTextField ed_nombrecd;
    private javax.swing.JTextField ed_titre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lb_commentaire;
    private javax.swing.JLabel lb_date;
    private javax.swing.JLabel lb_id;
    private javax.swing.JLabel lb_live;
    private javax.swing.JLabel lb_nbMorceaux;
    private javax.swing.JLabel lb_nombre_cd;
    private javax.swing.JLabel lb_titre;
    private javax.swing.JMenuItem mi_Quitter;
    private javax.swing.JPanel pn_Boutons;
    private javax.swing.JPanel pn_Details;
    private javax.swing.JPanel pn_bouton_enregitrer;
    private javax.swing.JPanel pn_cms_Albums;
    private javax.swing.JRadioButton rb_false;
    private javax.swing.JRadioButton rb_true;
    private javax.swing.JTable tb_album;
    // End of variables declaration//GEN-END:variables
}
