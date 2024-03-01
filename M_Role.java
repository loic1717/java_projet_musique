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
 * @author clocharda
 */
public class M_Role {
     private Db_mysql db;
    private int idRole;
    private String libelle;

    public M_Role(Db_mysql db, int idRole, String libelle) {
        this.db = db;
        this.idRole = idRole;
        this.libelle = libelle;
    }
    
    public M_Role(Db_mysql db, String libelle) throws SQLException {
        this.db = db;
        this.libelle = libelle;
        String sql;
        sql = "INSERT INTO DMI_ROLE (libelle) "
           +"VALUES ('"+libelle+"');" ;
        db.sqlExec(sql);

        ResultSet res;
        res = db.sqlLastId();
        res.first();
        this.idRole = res.getInt("id");     
    }
    
    public M_Role(Db_mysql db, int idRole) throws SQLException {
        this.db = db;
        this.idRole = idRole;
        String sql;
        sql = "SELECT * FROM DMI_ROLE WHERE idRole = "+idRole;
        ResultSet res;
        res = db.sqlSelect(sql);
        res.first();
        this.libelle = res.getString("libelle");
    }

    public Db_mysql getDb() {
        return db;
    }

    public int getIdRole() {
        return idRole;
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
        sql = "UPDATE DMI_ROLE SET libelle = '"+libelle+"' WHERE idRole = "+idRole+";";
        db.sqlExec(sql);
    }
    
    public void delete () throws SQLException{
        String sql ;
        sql = "DELETE FROM DMI_ROLE WHERE idRole = "+idRole;
        db.sqlExec(sql);
    }
    
    
    public static LinkedHashMap <Integer, M_Role> getRecords (Db_mysql db) throws SQLException {
        return getRecords(db, "1 = 1");
    } 
     
    public static LinkedHashMap <Integer, M_Role> getRecords (Db_mysql db, String clauseWhere) throws SQLException {
        LinkedHashMap <Integer, M_Role> lesRoles;
        lesRoles = new LinkedHashMap();
        M_Role unRole;
        
        int cle;
        String libelle;
          
        String sql;
        sql = "SELECT * FROM DMI_ROLE WHERE "+clauseWhere +" ORDER BY libelle";
        ResultSet res;
        res = db.sqlSelect(sql);
        
        while (res.next()){
            cle = res.getInt("idRole");
            libelle = res.getString("libelle");
            
            unRole = new M_Role(db, cle, libelle);
            lesRoles.put(cle, unRole);
        }
       // System.out.println("fin get records");
        return lesRoles;
    } 
   
    
    public void affiche (){
        System.out.println(idRole+ " "+libelle);
    }
    
    
     public static void main(String[] args) throws Exception {
        
        
        
        Db_mysql maBase;
        maBase = new Db_mysql(Cl_Connection.url, Cl_Connection.login, Cl_Connection.password);

        M_Role unRole;
        
        //Test du premier constructeur
//        unRole = new M_Role(maBase, 123, "Test");  
//        unRole.affiche();
        
        //Test du 2ème constructeur
//          unRole = new M_Role(maBase, "trucs/bidules divers");
//          unRole.affiche();
        
        //Test du 3ème constructeur
//          unRole = new M_Role(maBase, 4);
//          unRole.affiche();
        
        //Test méthode update
//        unRole = new M_Role(maBase, 4);
//        unRole.affiche();
//        unRole.setLibelle("machins");
//        unRole.update();
//        unRole.affiche();
        
        
        //Test méthode delete
//        unRole.delete();
        
        
        //Appel de la méthode getRecords
        LinkedHashMap <Integer, M_Role> lesRoles;
        lesRoles = M_Role.getRecords(maBase);  //, "libelle LIKE 'V%'");
        
        //Parcourir le dictionnaire pour afficher son contenu
//        Integer uneCle;
//        ArrayList<Integer> lesCles = new ArrayList<Integer>(lesRoles.keySet());
//        for (int i = 0; i < lesCles.size(); i++){
//            uneCle = lesCles.get(i);
//            unRole = lesMarques.get(uneCle);
//            unRole.affiche();
//        }

         for (Integer uneCle : lesRoles.keySet()){
            unRole = lesRoles.get(uneCle);
            unRole.affiche();
         }
        
    }    
}
