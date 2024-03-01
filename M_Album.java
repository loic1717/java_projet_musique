/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pj_gestion_musique;

/**
 *
 * @author COMPAORÉ Loïc
 */



import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;

public class M_Album {
      

    public M_Album(int idAlbum, String titre) {
        this.idAlbum = idAlbum;
        this.titre = titre;
    }

    static int getCount(Db_mysql baseLC, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private Db_mysql db;
    private int idAlbum;
    private String titre;
    private boolean live;
    private int nbCD;
    private int nbMorceaux;
    private String dateSortie;
    private int idStyle;

    public M_Album(Db_mysql db, int idAlbum, String titre, boolean live, int nbCD, int nbMorceaux, String dateSortie, int idStyle) {
        this.db = db;
        this.idAlbum = idAlbum;
        this.titre = titre;
        this.live = live;
        this.nbCD = nbCD;
        this.nbMorceaux = nbMorceaux;
        this.dateSortie = dateSortie;
        this.idStyle = idStyle;
    }

    public M_Album(Db_mysql db, String titre, boolean live, int nbCD, int nbMorceaux, String dateSortie, int idStyle) throws SQLException {
        this.db = db;
        this.titre = titre;
        this.live = live;
        this.nbCD = nbCD;
        this.nbMorceaux = nbMorceaux;
        this.dateSortie = dateSortie;
        this.idStyle = idStyle;

        String sql = "INSERT INTO DMI_ALBUM (titre, live, nbCD, nbMorceaux, dateSortie, idStyle) " +
                     "VALUES ('" + titre + "', " + (live ? 1 : 0) + ", " + nbCD + ", " + nbMorceaux + ", '" +
                     dateSortie + "', " + idStyle + ");";

        db.sqlExec(sql);

        ResultSet res = db.sqlLastId();
        res.first();
        this.idAlbum = res.getInt("id");
    }

    public M_Album(Db_mysql db, int idAlbum) throws SQLException {
        this.db = db;
        this.idAlbum = idAlbum;
        String sql = "SELECT * FROM DMI_ALBUM WHERE idAlbum = " + idAlbum;
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.titre = res.getString("titre");
        this.live = res.getBoolean("live");
        this.nbCD = res.getInt("nbCD");
        this.nbMorceaux = res.getInt("nbMorceaux");
        this.dateSortie = res.getString("dateSortie");
        this.idStyle = res.getInt("idStyle");
    }

    public Db_mysql getDb() {
        return db;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public String getTitre() {
        return titre;
    }

    public boolean isLive() {
        return live;
    }

    public int getNbCD() {
        return nbCD;
    }

    public int getNbMorceaux() {
        return nbMorceaux;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public int getIdStyle() {
        return idStyle;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void setNbCD(int nbCD) {
        this.nbCD = nbCD;
    }

    public void setNbMorceaux(int nbMorceaux) {
        this.nbMorceaux = nbMorceaux;
    }

    public void setDateSortie(String dateSortie) {
        
        this.dateSortie = dateSortie;
    }

    public void setIdStyle(int idStyle) {
        this.idStyle = idStyle;
    }

    public void update() throws SQLException {
       
        String sql = "UPDATE DMI_ALBUM SET " +
                     "titre = '" + titre + "', " +
                     "live = " + (live ? 1 : 0) + ", " +
                     "nbCD = " + nbCD + ", " +
                     "nbMorceaux = " + nbMorceaux + ", " +
                     "dateSortie = '" + dateSortie + "', " +
                     "idStyle = " + idStyle + " " +
                     "WHERE idAlbum = " + idAlbum + ";";

        db.sqlExec(sql);
    }

    public void delete() throws SQLException {
        String sql = "DELETE FROM DMI_ALBUM WHERE idAlbum = " + idAlbum;
        db.sqlExec(sql);
    }

    public static LinkedHashMap<Integer, M_Album> getRecords(Db_mysql db) throws SQLException {
        return getRecords(db, "1 = 1");
    }

    public static LinkedHashMap<Integer, M_Album> getRecords(Db_mysql db, String clauseWhere) throws SQLException {
        LinkedHashMap<Integer, M_Album> lesAlbums = new LinkedHashMap<>();
        M_Album unAlbum;

        int idAlbum;
        String titre;
        boolean live;
        int nbCD;
        int nbMorceaux;
        String dateSortie;
        int idStyle;

        String sql = "SELECT * FROM DMI_ALBUM WHERE " + clauseWhere + " ORDER BY titre";
        ResultSet res = db.sqlSelect(sql);

        while (res.next()) {
            idAlbum = res.getInt("idAlbum");
            titre = res.getString("titre");
            live = res.getBoolean("live");
            nbCD = res.getInt("nbCD");
            nbMorceaux = res.getInt("nbMorceaux");
            dateSortie = res.getString("dateSortie");
            idStyle = res.getInt("idStyle");

            unAlbum = new M_Album(db, idAlbum, titre, live, nbCD, nbMorceaux, dateSortie, idStyle);

            lesAlbums.put(idAlbum, unAlbum);
        }

        return lesAlbums;
    }



    public void affiche() {
        System.out.println(idAlbum + " " + titre + " " + live + " " + nbCD + " " + nbMorceaux + " " + dateSortie + " " + idStyle);
    }

    public static void main(String[] args) throws Exception {
        Db_mysql maBase;
        maBase = new Db_mysql(Cl_Connection.url, Cl_Connection.login, Cl_Connection.password);
        M_Album unAlbum;

//        // Test de création d'un nouvel album
//        M_Album nouvelAlbum = new M_Album(maBase, "Nouvel Album", true, 1, 10, "2015-11-20", "Nouvel Artiste", "Commentaire test", 1);
//        nouvelAlbum.affiche();

//         Test de récupération de tous les albums   
//        LinkedHashMap<Integer, M_Album> lesAlbums = M_Album.getRecords(maBase,clauseWhere);
//        for (Integer uneCle : lesAlbums.keySet()) {
//            unAlbum = lesAlbums.get(uneCle);
//            unAlbum.affiche();
//        }

//        // Test de mise à jour d'un album
//        nouvelAlbum.setTitre("Nouveau Titre");
//        nouvelAlbum.setNbMorceaux(12);
//        nouvelAlbum.update();
//
// Test de suppression de l'album créé précédemment
//        nouvelAlbum.delete();
// Test avec la clause where
// String clauseWhere = "idStyle = 2";    
//        LinkedHashMap<Integer, M_Album> lesAlbums = M_Album.getRecords(maBase,clauseWhere);
//        for (Integer uneCle : lesAlbums.keySet()) {
//            unAlbum = lesAlbums.get(uneCle);
//            unAlbum.affiche();
//        }
    }
    
}
