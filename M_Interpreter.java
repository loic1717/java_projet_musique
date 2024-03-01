/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pj_gestion_musique;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author COMPAORÉ Loïc
 */


public class M_Interpreter {
    private Db_mysql db;

    private int idArtiste;
    private int idAlbum;

    

    public M_Interpreter(Db_mysql db, int idArtiste, int idAlbum, boolean insert) throws SQLException {
        this.db = db;
        this.idArtiste = idArtiste;
        this.idAlbum = idAlbum;

        if (insert) {
            String sql = "INSERT INTO DMI_INTERPRETER (idArtiste, idAlbum) VALUES (" + idArtiste + ", " + idAlbum + ")";
            db.sqlExec(sql);
        }
    }
 public M_Interpreter(Db_mysql db, int idArtiste, int idAlbum, boolean insert,boolean insertion) throws SQLException {
        this.db = db;
        this.idArtiste = idArtiste;
        this.idAlbum = idAlbum;

        if (insert) {
            String sql = "INSERT INTO DMI_INTERPRETER (idAlbum, idArtiste) VALUES (" + idArtiste + ", " + idAlbum + ")";
            db.sqlExec(sql);
        }
    }
    public M_Interpreter(Db_mysql db, int idArtiste, int idAlbum) throws SQLException {
        this.db = db;
        this.idArtiste = idArtiste;
        this.idAlbum = idAlbum;
        String sql = "SELECT * FROM DMI_INTERPRETER WHERE idArtiste = " + idArtiste + " AND idAlbum = " + idAlbum + ";";
          ResultSet res;
        res = db.sqlSelect(sql);
        res.first();
                
        }
    

    public Db_mysql getDb() {
        return db;
    }

    public int getIdArtiste() {
        return idArtiste;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setDb(Db_mysql db) {
        this.db = db;
    }

    public void update() throws SQLException {
        String sql = "UPDATE DMI_INTERPRETER SET ... WHERE idArtiste = " + idArtiste + " AND idAlbum = " + idAlbum + ";";
        db.sqlExec(sql);
    }

    public void delete() throws SQLException {
        String sql = "DELETE FROM DMI_INTERPRETER WHERE idArtiste = " + idArtiste + " AND idAlbum = " + idAlbum + ";";
        db.sqlExec(sql);
    }

    public static ArrayList<M_Interpreter> getRecords(Db_mysql db) throws SQLException {
        return getRecords(db, "1 = 1");
    }

    public static ArrayList<M_Interpreter> getRecords(Db_mysql db, String clauseWhere) throws SQLException {
        ArrayList<M_Interpreter> lesInterpreters = new ArrayList<>();
        M_Interpreter unInterpreter;

        int idArtiste, idAlbum;

        String sql = "SELECT * FROM DMI_INTERPRETER WHERE " + clauseWhere + ";";
        ResultSet res = db.sqlSelect(sql);

        while (res.next()) {
            idArtiste = res.getInt("idArtiste");
            idAlbum = res.getInt("idAlbum");
            unInterpreter = new M_Interpreter(db, idArtiste, idAlbum);
            lesInterpreters.add(unInterpreter);
        }
        return lesInterpreters;
    }

    public static int getCount(Db_mysql db, String clauseWhere) throws SQLException {
        int nbInterpreters;

        String sql = "SELECT COUNT(*) AS nb FROM DMI_INTERPRETER WHERE " + clauseWhere + ";";
        ResultSet res = db.sqlSelect(sql);
        res.first();
        nbInterpreters = res.getInt("nb");

        return nbInterpreters;
    }
    public boolean estAlbumAssocie(int idArtiste, int idAlbum) throws SQLException {
    String sql = "SELECT * FROM DMI_INTERPRETER WHERE idArtiste = " + idArtiste + " AND idAlbum = " + idAlbum + ";";
    ResultSet res = db.sqlSelect(sql);

    // Vérifie si l'album est déjà associé à l'artiste
    return res.next(); // true si une association existe, sinon false
}


    public void affiche() {
        System.out.println("idArtiste : " + idArtiste + " idAlbum : " + idAlbum);
    }

    public static void main(String[] args) throws Exception {
        Db_mysql maBase = new Db_mysql(Cl_Connection.url, Cl_Connection.login, Cl_Connection.password);
////         System.out.println(unInterpreter.getIdMarque()+"-"+unInterpreter.getLibelle());
//        ArrayList<M_Interpreter> lesInterpreters = M_Interpreter.getRecords(maBase, "idArtiste = 1");
//
//        for (M_Interpreter unInterpreter : lesInterpreters) {
//            unInterpreter.affiche();
//        }
//
//        int nbInterpreters = M_Interpreter.getCount(maBase, "idArtiste = 1");
//        System.out.println(nbInterpreters);



        //test suppression album pour un artiste
        
        
//         int idArtiste = 2; // ID de l'artiste pour lequel vous souhaitez supprimer l'album
//         int idAlbum = 2; // ID de l'album à supprimer de l'artiste

    // Création d'une instance de M_Interpreter pour l'association artiste-album
//    M_Interpreter unInterpreter = new M_Interpreter(maBase, idArtiste, idAlbum);
//
//    // Suppression de l'album de l'artiste
//    try {
//        unInterpreter.delete();
//        System.out.println("L'album a été supprimé de l'artiste avec succès.");
//    } catch (SQLException e) {
//        System.out.println("Une erreur est survenue lors de la suppression de l'album de l'artiste : ");
//    }
//test ajout d'un album pour un artiste

  int idArtiste = 1; // ID de l'artiste avec lequel vous voulez associer un album
  int idAlbum = 4; // ID de l'album à associer à l'artiste
    boolean insert = true; // Flag indiquant l'insertion

    try {
        // Création d'une instance de M_Interpreter pour l'association artiste-album
      M_Interpreter  unInterpreter = new M_Interpreter(maBase, idArtiste, idAlbum, insert);
        System.out.println("Association artiste-album ajoutée avec succès.");
    } catch (SQLException e) {
        System.out.println("Erreur lors de l'ajout de l'association artiste-album : ");
    }

    }
}
