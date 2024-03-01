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
public class M_Utilisateur {
    private Db_mysql db;
    
    private int idUtilisateur;
    private String nom;
    private String prenom;
    private String telephone;
    private String login;
    private String motPasse;
    private int idRole;

    public M_Utilisateur(Db_mysql db, int idUtilisateur, String nom, String prenom, String telephone, String login, String motPasse, int idRole) {
        this.db = db;
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.login = login;
        this.motPasse = motPasse;
        this.idRole = idRole;
    }

    public M_Utilisateur(Db_mysql db, String nom, String prenom, String telephone, String login, String motPasse, int idRole) throws SQLException {
        this.db = db;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.login = login;
        motPasse=BCrypt.hashpw(motPasse, BCrypt.gensalt());
        this.motPasse = motPasse;
        this.idRole = idRole;
        
        String sql;
        sql = "INSERT INTO DMI_UTILISATEUR (nom, prenom, telephone, login, motPasse, idRole) "
           +"VALUES ('"+nom+"', '"+prenom+"', '"+telephone+"', '"+login+"', '"+this.motPasse+"', "+idRole+");" ;
        db.sqlExec(sql);

        ResultSet res;
        res = db.sqlLastId();
        res.first();
        this.idUtilisateur = res.getInt("id");     
    }

    public M_Utilisateur(Db_mysql db, int idUtilisateur) throws SQLException {
        this.db = db;
        this.idUtilisateur = idUtilisateur;
        
        String sql;
        sql = "SELECT * FROM DMI_UTILISATEUR WHERE idUtilisateur = "+idUtilisateur;
        ResultSet res;
        res = db.sqlSelect(sql);
        res.first();
        this.nom = res.getString("nom");
        this.prenom = res.getString("prenom");
        this.telephone = res.getString("telephone");
        this.login = res.getString("login");
        this.motPasse = res.getString("motPasse");
        this.idRole = res.getInt("idRole");
    }

   
    public Db_mysql getDb() {
        return db;
    }

    public int getIdUtulisateur() {
        return idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getLogin() {
        return login;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setDb(Db_mysql db) {
        this.db = db;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean setMotPasse(String motDePasseActuel, String nouveauMotDePasse) {
    boolean modif=false;
        System.out.println(motDePasseActuel);
    if (BCrypt.checkpw(motDePasseActuel, this.motPasse)) {
        this.motPasse = BCrypt.hashpw(nouveauMotDePasse, BCrypt.gensalt());
        modif=true;
        System.out.println("ok");
//    } else {
//        return false; // Le mot de passe actuel est incorrect, la modification n'a pas été effectuée
    }
    return modif;
}


    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
    
    public void update() throws SQLException{
        String sql;
        sql = "UPDATE DMI_UTILISATEUR SET nom = '"+nom+"', prenom = '"+prenom+"', telephone = '"+telephone+"',"
                + " login = '"+login+"', motPasse = '"+motPasse+"', idRole = "+idRole+" WHERE idUtilisateur = "+idUtilisateur+";";
        db.sqlExec(sql);
    }
    
   public void delete() throws SQLException {
    
    String countAdminSql = "SELECT COUNT(*) AS admincount FROM DMI_UTILISATEUR WHERE idRole = 1";
    ResultSet countResult = db.sqlSelect(countAdminSql);

    int adminCount;
  
        adminCount = countResult.getInt("adminCount");
    

    
    if (adminCount > 1) {
      
        String deleteSql = "DELETE FROM DMI_UTILISATEUR WHERE idUtilisateur = " + idUtilisateur;
        db.sqlExec(deleteSql);
    } else {
        System.out.println("Suppression impossible. ");
    }
}

    
        
    public static LinkedHashMap <Integer, M_Utilisateur> getRecords (Db_mysql db) throws SQLException {
        return getRecords(db, "1 = 1");
    } 
     
    public static LinkedHashMap <Integer, M_Utilisateur> getRecords (Db_mysql db, String clauseWhere) throws SQLException {
        LinkedHashMap <Integer, M_Utilisateur> lesUtilisateurs;
        lesUtilisateurs = new LinkedHashMap();
        M_Utilisateur unUtilisateur;
        
        int cle, idRole;
        String nom, prenom, telephone, login, motPasse;
          
        String sql;
        sql = "SELECT * FROM DMI_UTILISATEUR WHERE "+clauseWhere +" ORDER BY nom, prenom";
        ResultSet res;
        res = db.sqlSelect(sql);
        
        while (res.next()){
            cle = res.getInt("idUtilisateur");
            nom = res.getString("nom");
            prenom = res.getString("prenom");
            telephone = res.getString("telephone");
            login = res.getString("login");
            motPasse = res.getString("motPasse");
            idRole = res.getInt("idRole");
            
            unUtilisateur = new M_Utilisateur(db, cle, nom, prenom, telephone, login, motPasse, idRole);
            lesUtilisateurs.put(cle, unUtilisateur);
        }
       // System.out.println("fin get records");
        return lesUtilisateurs;
    } 
   
     public static M_Utilisateur connexion_log(Db_mysql db, String login, String motDePasse) throws SQLException {
        M_Utilisateur unUtilisateur=null;
        String sql;
        String nom, prenom, telephone, motPasse;
        int idUtilisateur,idRole;
        
        sql = "SELECT * FROM DMI_UTILISATEUR  WHERE login = '" + login + "'";
        ResultSet res;
        res = db.sqlSelect(sql);

        if (res.first()) { 
            motPasse = res.getString("motPasse");
            if (BCrypt.checkpw(motDePasse,motPasse )) { 

                idUtilisateur = res.getInt("idUtilisateur");
                login = res.getString("login");
                motDePasse = res.getString("motPasse");
                nom = res.getString("nom");
                prenom = res.getString("prenom");
                telephone = res.getString("telephone");
                motPasse = res.getString("motPasse");
                idRole = res.getInt("idRole");
                unUtilisateur = new M_Utilisateur(db, idUtilisateur,nom, prenom, telephone, login, motPasse, idRole);
           
           }
            
        }
         return  unUtilisateur;
     }
     
    public void affiche (){
        System.out.println(idUtilisateur+ " "+nom+ " "+prenom+ " "+telephone+ " "+login+ " "+motPasse+ " "+idRole);
    }
    
    
     public static void main(String[] args) throws Exception {
        
        
        
        Db_mysql maBase;
        maBase = new Db_mysql(Cl_Connection.url, Cl_Connection.login, Cl_Connection.password);

        M_Utilisateur unUtilisateur;
        
        LinkedHashMap<Integer, M_Utilisateur> lesUtilisateurs = M_Utilisateur.getRecords(maBase);
        for (Integer uneCle : lesUtilisateurs.keySet()) {
            unUtilisateur = lesUtilisateurs.get(uneCle);
            unUtilisateur.affiche();
         }    
    
     }
}
