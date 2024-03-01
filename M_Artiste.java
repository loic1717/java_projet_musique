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
import com.mycompany.pj_gestion_musique.M_Album;


public class M_Artiste {

    static int getCount(Db_mysql baseLC, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private Db_mysql db;
    private int idArtiste;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String dateDeces;

    public M_Artiste(Db_mysql db, int idArtiste, String nom, String prenom, String dateNaissance, String dateDeces) {
    this.db = db;
    this.idArtiste = idArtiste;
    this.nom = nom;
    this.prenom = prenom;
    this.dateNaissance = dateNaissance;
    this.dateDeces = dateDeces;
    }

        public M_Artiste(Db_mysql db, String nom, String prenom, String dateNaissance, String dateDeces) throws SQLException {
        this.db = db;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.dateDeces = dateDeces;

        String sql = "INSERT INTO DMI_ARTISTE (nom, prenom, date_naissance, date_deces) VALUES ('" +
                nom + "', '" + prenom + "', '" + dateNaissance + "', '" + dateDeces + "');";

        db.sqlExec(sql);

        ResultSet res = db.sqlLastId();
        res.first();
        this.idArtiste = res.getInt("id");
    }

        public M_Artiste(Db_mysql db, int idArtiste) throws SQLException {
        this.db = db;
        this.idArtiste = idArtiste;
        String sql = "SELECT * FROM DMI_ARTISTE WHERE idArtiste = " + idArtiste;
        ResultSet res = db.sqlSelect(sql);
        res.first();
        this.nom = res.getString("nom");
        this.prenom = res.getString("prenom");
        this.dateNaissance = res.getString("date_naissance");
        this.dateDeces = res.getString("date_deces");
    }

    public Db_mysql getDb() {
        return db;
    }

      public int getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(int idArtiste) {
        this.idArtiste = idArtiste;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getDateDeces() {
        return dateDeces;
    }

    public void setDateDeces(String dateDeces) {
        this.dateDeces = dateDeces;
    }


    public void update() throws SQLException {
        String sql = "UPDATE DMI_ARTISTE SET " +
                "nom = '" + nom + "', " +
                "prenom = '" + prenom + "', " +
                "date_naissance = '" + dateNaissance + "', " +
                "date_deces = '" + dateDeces + "' " +
                "WHERE idArtiste = " + idArtiste + ";";

        db.sqlExec(sql);
    }


     public void delete() throws SQLException {
        String sql = "DELETE FROM DMI_ARTISTE WHERE idArtiste = " + idArtiste;
        db.sqlExec(sql);
    }
    

    public static LinkedHashMap<Integer, M_Artiste> getRecords(Db_mysql db) throws SQLException {
        return getRecords(db, "1 = 1");
    }

    public static LinkedHashMap<Integer, M_Artiste> getRecords(Db_mysql db, String clauseWhere) throws SQLException {
        LinkedHashMap<Integer, M_Artiste> lesArtistes = new LinkedHashMap<>();
        M_Artiste unArtiste;

        int idArtiste;
        String nom;
        String prenom;
        String dateNaissance;
        String dateDeces;

        String sql = "SELECT * FROM DMI_ARTISTE WHERE " + clauseWhere + " ORDER BY nom";
        ResultSet res = db.sqlSelect(sql);

        while (res.next()) {
            idArtiste = res.getInt("idArtiste");
            nom = res.getString("nom");
            prenom = res.getString("prenom");
            dateNaissance = res.getString("date_naissance");
            dateDeces = res.getString("date_deces");

            unArtiste = new M_Artiste(db, idArtiste, nom, prenom, dateNaissance, dateDeces);

            lesArtistes.put(idArtiste, unArtiste);
        }

        return lesArtistes;
    }
    
public static LinkedHashMap<Integer, M_Album> getRecords(Db_mysql db, int idArtiste) throws SQLException {
    LinkedHashMap<Integer, M_Album> lesAlbums = new LinkedHashMap<>();

    String sql = "SELECT DMI_ALBUM.titre, DMI_ALBUM.idAlbum\n" +
                 "FROM DMI_ARTISTE\n" +
                 "JOIN DMI_INTERPRETER ON DMI_ARTISTE.idArtiste = DMI_INTERPRETER.idArtiste\n" +
                 "JOIN DMI_ALBUM ON DMI_INTERPRETER.idAlbum = DMI_ALBUM.idAlbum\n" +
                 "WHERE DMI_ARTISTE.idArtiste = " + idArtiste;

    ResultSet res = db.sqlSelect(sql);

    while (res.next()) {
        int idAlbum = res.getInt("idAlbum");
        String titre = res.getString("titre");

        M_Album unAlbum = new M_Album(idAlbum, titre);
        lesAlbums.put(idAlbum, unAlbum);
    }

    return lesAlbums;
}



    public void affiche() {
        System.out.println(idArtiste + " " + nom + " " + prenom + " " + dateNaissance + " " + dateDeces);
    }

    public static void main(String[] args) throws Exception {
        Db_mysql maBase = new Db_mysql(Cl_Connection.url, Cl_Connection.login, Cl_Connection.password);
        M_Artiste unArtiste;
  LinkedHashMap<Integer, M_Artiste> lesArtistes = M_Artiste.getRecords(maBase);
//         //Test de création d'un nouvel artiste
//        M_Artiste nouvelArtiste = new M_Artiste(maBase, "michel", "Jkiiohn", "1990-01-01", "2000-01-05");
//        nouvelArtiste.affiche();

//        // Test de mise à jour d'un artiste
//           unArtiste = new M_Artiste(maBase, 28);
//       
//       unArtiste.setPrenom("steeve");
//        unArtiste.update();
//        unArtiste.affiche();
//        // Test de suppression de l'artiste créé précédemment
//        nouvelArtiste.delete();
//
//        // Test de récupération de tous les artistes
//        LinkedHashMap<Integer, M_Artiste> lesArtistes = M_Artiste.getRecords(maBase);
//        for (Integer uneCle : lesArtistes.keySet()) {
//            unArtiste = lesArtistes.get(uneCle);
//            unArtiste.affiche();

//            int idArtiste = 1; 
//            unArtiste = new M_Artiste(maBase, idArtiste);
//            unArtiste.selectAlbum();
  
            // Obtenez tous les artistes
       
            
        // Parcourez les artistes et affichez leurs informations ainsi que leurs albums
//         for (Integer uneCle : lesArtistes.keySet()) {
//            unArtiste = lesArtistes.get(uneCle);
//            unArtiste.affiche();
//                
//                // Obtenez les albums de chaque artiste
//                LinkedHashMap<Integer, M_Album> lesAlbums = M_Artiste.getRecords(maBase, unArtiste.getIdArtiste());
//                
//                // Affichez les albums de l'artiste
//                for (M_Album unAlbum : lesAlbums.values()) {
//                    System.out.println("Album de l'artiste : " + unAlbum.getTitre());
//                }
//            }
int idArtiste = 1; 

    LinkedHashMap<Integer, M_Album> albumsArtiste = M_Artiste.getRecords(maBase, idArtiste);

    if (!albumsArtiste.isEmpty()) {
        System.out.println("Albums de l'artiste avec l'ID " + idArtiste + ":");
        for (M_Album unAlbum : albumsArtiste.values()) {
            System.out.println("ID Album: " + unAlbum.getIdAlbum() + ", Titre: " + unAlbum.getTitre());
        }
    } else {
        System.out.println("Aucun album trouvé pour l'artiste avec l'ID " + idArtiste);
    }
       
    }
}

   
