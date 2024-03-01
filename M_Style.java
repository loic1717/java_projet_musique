/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pj_gestion_musique;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

/**
 *
 * @author COMPAORÉ Loïc
 */
public class M_Style {
     private Db_mysql db;
    private int idStyle;
    private String libelle;

    public M_Style(Db_mysql db, int idStyle, String libelle) {
        this.db = db;
        this.idStyle = idStyle;
        this.libelle = libelle;
    }
    
    public M_Style(Db_mysql db, String libelle) throws SQLException {
        this.db = db;
        this.libelle = libelle;
        String sql;
        sql = "INSERT INTO DMI_STYLE (libelle) "
           +"VALUES ('"+libelle+"');" ;
        db.sqlExec(sql);

        ResultSet res;
        res = db.sqlLastId();
        res.first();
        this.idStyle = res.getInt("id");     
    }
    
    public M_Style(Db_mysql db, int idStyle) throws SQLException {
        this.db = db;
        this.idStyle = idStyle;
        String sql;
        sql = "SELECT * FROM DMI_STYLE WHERE idStyle = "+idStyle;
        ResultSet res;
        res = db.sqlSelect(sql);
        res.first();
        this.libelle = res.getString("libelle");
    }

    public Db_mysql getDb() {
        return db;
    }

    public int getIdStyle() {
        return idStyle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setDb(Db_mysql db) {
        this.db = db;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    public void update() throws SQLException{
        String sql;
        sql = "UPDATE DMI_STYLE SET libelle = '"+libelle+"' WHERE idStyle = "+idStyle+";";
        db.sqlExec(sql);
    }
    
    public void delete () throws SQLException{
        String sql ;
        sql = "DELETE FROM DMI_STYLE WHERE idStyle = "+idStyle;
        db.sqlExec(sql);
    }
    public static int getCount(Db_mysql db, String clauseWhere) throws SQLException {
    int nbStyle;
    String sql = "SELECT COUNT(S.idStyle) as NB FROM DMI_ALBUM A " +
                 "JOIN DMI_STYLE S on A.idStyle = S.idStyle " +
                 "WHERE " + clauseWhere + ";";
    ResultSet res = db.sqlSelect(sql);
    res.first();
    nbStyle = res.getInt("NB");
    return nbStyle;
    }
    public static LinkedHashMap <Integer, M_Style> getRecords (Db_mysql db) throws SQLException {
        return getRecords(db, "1 = 1");
    } 
     
    public static LinkedHashMap <Integer, M_Style> getRecords (Db_mysql db, String clauseWhere) throws SQLException {
        LinkedHashMap <Integer, M_Style> lesStyles;
        lesStyles = new LinkedHashMap();
        M_Style unStyle;
        
        int cle;
        String libelle;
          
        String sql;
        sql = "SELECT * FROM DMI_STYLE WHERE "+clauseWhere +" ORDER BY libelle";
        ResultSet res;
        res = db.sqlSelect(sql);
        
        while (res.next()){
            cle = res.getInt("idStyle");
            libelle = res.getString("libelle");
            
            unStyle = new M_Style(db, cle, libelle);
            lesStyles.put(cle, unStyle);
        }
       // System.out.println("fin get records");
        return lesStyles;
    } 
   
    
    public void affiche (){
        System.out.println(idStyle+ " "+libelle);
    }
    
    
     public static void main(String[] args) throws Exception {
        
        
        
        Db_mysql maBase;
        maBase = new Db_mysql(Cl_Connection.url, Cl_Connection.login, Cl_Connection.password);

        M_Style unStyle;
        
        //Test du premier constructeur
//        unStyle = new M_Style(maBase, 123, "Test");  
//        unStyle.affiche();
        
        //Test du 2ème constructeur
//          unStyle = new M_Style(maBase, "trucs/bidules divers");
//          unStyle.affiche();
        
        //Test du 3ème constructeur
//          unStyle = new M_Style(maBase, 4);
//          unStyle.affiche();
        
        //Test méthode update
//        unStyle = new M_Style(maBase, 4);
//        unStyle.affiche();
//        unStyle.setLibelle("machins");
//        unStyle.update();
//        unStyle.affiche();
        
        
        //Test méthode delete
//        unStyle.delete();
        
        
        //Appel de la méthode getRecords
        LinkedHashMap <Integer, M_Style> lesStyles;
        lesStyles = M_Style.getRecords(maBase);  //, "libelle LIKE 'V%'");
        
        //Parcourir le dictionnaire pour afficher son contenu
//        Integer uneCle;
//        ArrayList<Integer> lesCles = new ArrayList<Integer>(lesStyles.keySet());
//        for (int i = 0; i < lesCles.size(); i++){
//            uneCle = lesCles.get(i);
//            unStyle = lesMarques.get(uneCle);
//            unStyle.affiche();
//        }

//         for (Integer uneCle : lesStyles.keySet()){
//            unStyle = lesStyles.get(uneCle);
//            unStyle.affiche();
//         }
         int nbStyle ;
        nbStyle = M_Style.getCount(maBase, "A.idStyle = 4");
        System.out.println(nbStyle);
        
    }    
}
