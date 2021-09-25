package memo2;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;


public class Employe {
		private String nom, prenom, mail, adresseEmploye, adresseLieuDetravail, poste, superieurImmediat;
		private int numeroBadge;
		
		public Employe(int Badge, String nom, String prenom, String mail, String adresseEmploye, String adresseLieuDeTravail, String poste, String superieur) {
			this.nom = nom;
			this.prenom = prenom;
			this.mail = mail;
			this.adresseLieuDetravail = adresseLieuDeTravail;
			this.adresseEmploye = adresseEmploye;
			this.poste = poste;
			this.superieurImmediat = superieur;
			this.numeroBadge = Badge;
		}
		
		public String getNomEmploye() {
			return nom;
					}
		public void setNomEmploye(String nom) {
			this.nom = nom;
		//	this.updateEmploye("nom");
		}
		
		public String getPrenomEmploye() {
			return prenom;
		}
		public void setPrenomEmploye(String prenom) {
			this.prenom = prenom;
		}
		public String getMail() {
			return mail;
		}
		public void setMail(String mail) {
			this.mail = mail;
			
		}
		public String getAdresseEmploye() {
			return adresseEmploye;
		}
	
		
		public void setAdresseEmploye(String adresseEmploye) {
			this.adresseEmploye = adresseEmploye;
		}
		
		public String getPoste(){
			return poste;
		}
		
		public void setPoste(String poste) {
			this.poste = poste;
		}
		
		public String getAdresseLieuDetravail() {
			return adresseLieuDetravail;
		}
		
		public void getAdresseLieuDetravail(String adresseLieuDetravail) {
			this.adresseLieuDetravail = adresseLieuDetravail;
			
		}
		public String getSuperieur() {
			return superieurImmediat;
		}
		
		public void setSuperieur(String superieurImmediat) {
			this.superieurImmediat = superieurImmediat;
		}
		
		public int getNumeroBadge() {
			return numeroBadge;
		}
		
		public void setNumeroBadge(int numeroBadge) {
			this.numeroBadge = numeroBadge;
		
		}
		
	public void  ajouterEmploye() {
            Bd connexion1 = new Bd();
		
	try {
		Connection con = connexion1.getConnection();
				
	String reqEmp = "insert into Employe(numeroBadge , nom, prenom, mail, adresseEmploye, adresseLieuDetravail, poste, superieurImmediat) values(?, ?, ?, ?, ?, ?, ?, ?)";
	        
	        PreparedStatement prepare = con.prepareStatement(reqEmp);
	        prepare.setInt(1, this.numeroBadge);
	        prepare.setString(2, this.nom);
	        prepare.setString(3, this.prenom);
	        prepare.setString(4, this.mail);
	        prepare.setString(5, this.adresseEmploye);
	        prepare.setString(6, this.adresseLieuDetravail);
	        prepare.setString(7,  this.poste);
	        prepare.setString(8,  this.superieurImmediat);
	        
	        prepare.executeUpdate(); 
			          
	        System.out.println("insertion faite");
	      //  connexion1.fermerconnexion(); 
	      
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
	/*	
	public void  updateEmploye(String attr){
			   Bd connexion1 = new Bd();
				
				try {
					Connection con = connexion1.getConnection();
					
				String reqEmp = "update employe  set "+attr+" = ?  where numeroBadge =  ? ";
			 
		        PreparedStatement prepare = con.prepareStatement(reqEmp);
		        
		        switch (attr) {
		        	case("numeroBadge"):
		        	prepare.setInt(1, this.numeroBadge);
		        	break;
		        	case ("nom"):
		        		prepare.setString(1, this.nom);
		        		break;
		        	case ("prenom"):
		        		prepare.setString(1, this.prenom);
		        		break;
		        	case ("mail"):
		        		prepare.setString(1, this.mail);
		        		break;
		        	case ("adresseEmploye"):
		        		prepare.setString(1, this.adresseEmploye);
		        		break;
		        	case ("adresseLieuDetravail"):
		        		prepare.setString(1, this.adresseLieuDetravail);
		        		break;
		        	case("poste"):
		        		prepare.setString(1, this.poste);
		        		break;
		        	case("superieurImmediat"):
		        		prepare.setString(1, this.superieurImmediat);
		        	
		        	
		        		
		        }  
		        	
		        prepare.setInt(2, this.numeroBadge);
		        
		        prepare.executeUpdate(); 
				          
		        System.out.println("mise à jour faite");
				
				} catch(Exception e) {
					e.printStackTrace();
				}
			
		  } 
		
		public void supprimerEmploye() {
		
		Bd connexion1 = new Bd();
		
		try {
			
			Connection con = connexion1.getConnection();
			
			String reqEmp= "delete from employe where numeroBadge =  ? ";
			
			   
	        PreparedStatement prepare = con.prepareStatement(reqEmp);
	        prepare.setInt(1, this.numeroBadge);

	        prepare.executeUpdate();
	        
	        System.out.println("ligne supprimée");
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		
	}
*/
}
 

