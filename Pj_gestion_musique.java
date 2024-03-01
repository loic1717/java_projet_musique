/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pj_gestion_musique;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedHashMap;

/**
 *
 * @author COMPAORÉ Loïc
 */
public class Pj_gestion_musique {
    
    private Db_mysql baseLC;
    
    private V_Main fm_Main;

    private V_Album fm_Album;
    
    private V_Style fm_Style;

    private V_Artiste fm_Artiste;
    
    private V_Utilisateur_Admin fm_Utilisateur_Admin;
    
     private V_MonCompte fm_MonCompte;
    
    private LinkedHashMap<Integer, M_Album >lesAlbums;

    private M_Album unAlbum;
    
    private LinkedHashMap <Integer, M_Style> lesStyles;
    private M_Style unStyle;

    private LinkedHashMap <Integer, M_Artiste> lesArtistes;
    
    private M_Artiste unArtiste;
   
    private   LinkedHashMap<Integer, M_Album> albumsArtiste ;
    
    private M_Interpreter unInterpreter;
    
    private LinkedHashMap<Integer, M_Utilisateur> lesUtilisateurs;
    
    private M_Utilisateur unMembre,compteConnecte;
   
    
     private LinkedHashMap <Integer, M_Role> lesRoles;
     
    public Pj_gestion_musique() throws Exception{

        this.connection();

        fm_Main= new V_Main(this);

        fm_Style =new V_Style(fm_Main,true,this);
        fm_Album= new V_Album(fm_Main, true, this);
        fm_Artiste=new V_Artiste(fm_Main,true,this);
        fm_Utilisateur_Admin = new V_Utilisateur_Admin(fm_Main,true,this);
        fm_MonCompte= new V_MonCompte(fm_Main, true,this);
        fm_Main.afficher();
    }

    public void aff_V_Style() throws SQLException{
        lesStyles = M_Style.getRecords(baseLC);
        fm_Style.afficher(lesStyles);
    }

    public void ajout_Style(String libelle) throws SQLException {
        unStyle = new M_Style(baseLC, libelle);
        this.aff_V_Style();
        }

    public void modif_Style (int idStyle, String libelle) throws SQLException {
        unStyle = lesStyles.get(idStyle);
        unStyle.setLibelle(libelle);
        unStyle.update();
        this.aff_V_Style();
        }
    
    public void sup_Style (int idStyle) throws SQLException {        
        unStyle = lesStyles.get(idStyle);
        unStyle.delete();
        lesStyles.remove(idStyle);
        this.aff_V_Style();
    }

   public Boolean estLiee (int idStyle) throws SQLException {
        Boolean rep;
        int nbStyl;
        nbStyl= M_Style.getCount(baseLC, "A.idStyle = "+idStyle);
        if (nbStyl> 0){
            rep = true;
        }
            else {rep = false;}
        
        return rep;

        }  
    public void aff_V_Albums() throws SQLException{
        lesAlbums= M_Album.getRecords(baseLC);
        lesStyles = M_Style.getRecords(baseLC);
        fm_Album.afficher(lesAlbums,lesStyles);
        }
    
    public void afficherAlbumsAvecClauseWhere(String clauseWhere) throws SQLException {
        lesAlbums= M_Album.getRecords(baseLC,clauseWhere);
        lesStyles = M_Style.getRecords(baseLC);
        fm_Album.afficher(lesAlbums,lesStyles);
        }
        public void afficherroleAvecClauseWhere(String clauseWhere) throws SQLException {
        lesUtilisateurs= M_Utilisateur.getRecords(baseLC,clauseWhere);
        lesRoles = M_Role.getRecords(baseLC);
        fm_Utilisateur_Admin.afficher(lesUtilisateurs,lesRoles);
        }
    public void aff_V_AlbumsTous() throws SQLException{
        lesAlbums= M_Album.getRecords(baseLC);
        lesStyles = M_Style.getRecords(baseLC);
        fm_Album.afficher(lesAlbums,lesStyles);
        }
     public void ajout_Albums(String titre, boolean live, int nbCD, int nbMorceaux, String dateSortie,int idStyle) throws SQLException {
        unAlbum= new M_Album(baseLC,titre, live, nbCD, nbMorceaux,dateSortie,idStyle);
        this.aff_V_Albums();
        }
     
    public void sup_Albums (int idAlbum) throws SQLException{        
        unAlbum = lesAlbums.get(idAlbum);
        unAlbum.delete();
        lesAlbums.remove(idAlbum);
        this.aff_V_Albums();
        }
    
    public void modif_Albums (int idAlbum, String titre, boolean live, int nbCD, int nbMorceaux, String dateSortie, int idStyle) throws SQLException{
        unAlbum = lesAlbums.get(idAlbum);
        unAlbum.setTitre(titre);
        unAlbum.setLive(live);
        unAlbum.setNbCD(nbCD);
        unAlbum.setNbMorceaux(nbMorceaux);
        unAlbum.setDateSortie(dateSortie);
        unAlbum.setIdStyle(idStyle);
        unAlbum.update();
        this.aff_V_Albums();
        }
     public void aff_V_Artiste() throws SQLException{
         lesAlbums= M_Album.getRecords(baseLC);
        lesArtistes = M_Artiste.getRecords(baseLC);
        fm_Artiste.afficher(lesArtistes,lesAlbums);
        }
    public void modif_Artiste (int idArtiste, String nom, String prenom, String date_naissance, String date_deces) throws SQLException {
        unArtiste = lesArtistes.get(idArtiste);
        unArtiste.setNom(nom);
        unArtiste.setPrenom(prenom);
        unArtiste.setDateNaissance(date_naissance);
        unArtiste.setDateDeces(date_deces);
        unArtiste.update();
        this.aff_V_Artiste();
      
    }
     public void sup_Artiste (int idArtiste) throws SQLException{        
        unArtiste = lesArtistes.get(idArtiste);
        unArtiste.delete();
        lesArtistes.remove(idArtiste);
        this.aff_V_Artiste();
        }
    public void ajout_Artiste(String nom, String prenom, String dateNaissance, String dateDeces) throws SQLException{
        unArtiste = new M_Artiste(baseLC, nom, prenom, dateNaissance, dateDeces);
        this.aff_V_Artiste();
        }
  public LinkedHashMap<Integer, M_Album> aff_album_artiste(int idArtiste) throws SQLException {
      
     albumsArtiste = M_Artiste.getRecords(baseLC, idArtiste);
     return albumsArtiste;
}

  public void sup_album_artiste(int idArtiste, int idAlbum) throws SQLException{
      
    unInterpreter = new M_Interpreter(baseLC, idArtiste, idAlbum);
    unInterpreter.delete();
    this.aff_V_Artiste();
  }
    public void aff_V_Utilisateurs_Admin() throws SQLException{
        lesUtilisateurs = M_Utilisateur.getRecords(baseLC);
        lesRoles = M_Role.getRecords(baseLC);
        fm_Utilisateur_Admin.afficher(lesUtilisateurs, lesRoles);    
    }
       public void modif_Utilisateur(Integer idUtilisateur, String nom, String prenom, String telephone, String login, int idRole) throws SQLException{
        unMembre = lesUtilisateurs.get(idUtilisateur);
        unMembre.setNom(nom);
        unMembre.setPrenom(prenom);
        unMembre.setTelephone(telephone);
        unMembre.setLogin(login);
        unMembre.setIdRole(idRole);
        unMembre.update();
        this.aff_V_Utilisateurs_Admin();
    }
        public void ajout_Utilisateur(String nom, String prenom, String telephone, String login, int idRole, String motPasse) throws SQLException{
        unMembre = new M_Utilisateur(baseLC, nom, prenom, telephone, login, motPasse, idRole);
        //Voir pour la saisie du mot de passe
        //Peut-être en générer un et faire un envoi par mail
        this.aff_V_Utilisateurs_Admin();
    }
        public void sup_Utilisateur (Integer idUtilisateur) throws SQLException{        
        unMembre = lesUtilisateurs.get(idUtilisateur);
        unMembre.delete();
        lesUtilisateurs.remove(idUtilisateur);
        this.aff_V_Utilisateurs_Admin();
    }
  public void aj_album_artiste(int idArtiste, int idAlbum) throws SQLException{
   unInterpreter = new M_Interpreter(baseLC, idArtiste, idAlbum,true);
   this.aff_V_Artiste();
   
  }
     public void aff_Moncompte(){
       // Récupérer le membre connecté depuis la base de données
     
        fm_MonCompte.afficher(compteConnecte);
    }
    public boolean modif_MP_Utilisateurs(String ancien,String nouveau) throws SQLException{
    boolean modifok;
    modifok=compteConnecte.setMotPasse(ancien, nouveau);
    if (modifok)
            {
        compteConnecte.update();
         this.aff_Moncompte();
    }
    return modifok;

    }
    public void modifierInformationsCompte(String nouveauNom, String nouveauPrenom, String nouveauTelephone) throws SQLException {
   
        // Modification des informations de l'utilisateur
        compteConnecte.setNom(nouveauNom);
        compteConnecte.setPrenom(nouveauPrenom);
        compteConnecte.setTelephone(nouveauTelephone);
        
        // Mettre à jour les informations dans la base de données
        compteConnecte.update();
       this.aff_Moncompte();
    
}

    
    public M_Utilisateur connexion_log(String login, String motDePasse) throws SQLException{
        compteConnecte = M_Utilisateur.connexion_log(baseLC,login,motDePasse);
        return compteConnecte;
    }
    public void deconnexion(){
        compteConnecte=null;
    }
    private void connection () throws Exception{
        baseLC = new Db_mysql(Cl_Connection.url, Cl_Connection.login, Cl_Connection.password);
        }
      
    public static void main(String[] args) throws Exception {
        Pj_gestion_musique leControleur =new Pj_gestion_musique();
        }

        LinkedHashMap<Integer, M_Album> getLesAlbums() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

    }
