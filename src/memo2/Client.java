package memo2;
import java.sql.*;

public class Client {
	private String nom, prenom, mail;
	private int numeroCartefidelite, codePostal, pointDefidelite;
    
    
public Client(int numFidelite, String nom, String prenom, String mail,  int cp, int pointFidelite) {
		
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.numeroCartefidelite = numFidelite;
		this.codePostal = cp;
		this.pointDefidelite = pointFidelite;
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
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public int getNumerofidelite() {
		return numeroCartefidelite;
	}
	public void setNumerofidelite(int numeroCartefidelite) {
		this.numeroCartefidelite = numeroCartefidelite;
	}
	
	public int getCodepostal() {
		return codePostal;
	}
	
	public void setCodepostal(int codePosatl) {
		this.codePostal = codePostal;
	}
	
	public int getNbPointfidelite() {
		return pointDefidelite;
	}
	
	public void setNbPointfidelite(int nbPointfidelite) {
		this.pointDefidelite = nbPointfidelite;
	}
   // Test des methodes de la classe
   /* public static void ajouterClient(Client client) {
    	
    	Bd connexion1 = new Bd();
    	
    	try {
    		
		Connection con = connexion1.getConnection();
			
    	String reqclient = "insert into Client (numeroCartefidelite ,  nom, prenom, mail, codePostal, nbPointfidelite) values(?, ?, ?, ?, ?, ?, ?)";
    	
        PreparedStatement pstmt= con.prepareStatement(reqclient) ;
            pstmt.setInt(1,  client.numeroCartefidelite);
            pstmt.setString(2, client.getNom());
            pstmt.setString(3, client.getPrenom());
            pstmt.setString(4, client.getMail());
            pstmt.setInt(5, client.getCodepostal());
            pstmt.setInt(6, client.getNbPointfidelite());
            
            pstmt.executeUpdate();
            
            System.out.println("insertin faite");
            
        } catch (Exception e) {
           
            e.printStackTrace();
        }
    }
    
  public void  updateClient(String attr){
		   Bd connexion1 = new Bd();
			
			try {
				Connection con = connexion1.getConnection();
				
			String reqclient = "update client  set "+attr+" = ?  where numeroCartefidelite =  ? ";
		 
	        PreparedStatement prepare = con.prepareStatement(reqclient);
	        
	        switch (attr) {
	        	case("numeroCartefidelite"):
	        	prepare.setInt(1, this.numeroCartefidelite);
	        		break;
	        	case("nomPointdevente"):
	        		prepare.setString(1,  this.nomPointdevente);
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
	        	case ("codePostal"):
	        		prepare.setInt(1, this.codePostal);
	        		break;
	        	 
	        	case("nbPointfidelite"):
	        		prepare.setInt(1, this.nbPointfidelite);
	        		break; 	
	        		
	        }  
	        	
	        prepare.setInt(2, this.numeroCartefidelite);
	        
	        prepare.executeUpdate(); 
			          
	        System.out.println("mise à jour faite");
			
			} catch(Exception e) {
				e.printStackTrace();
			}
    }
    
public void supprimerClient(Client client) {
		
		Bd connexion1 = new Bd();
		
		try {
			
			Connection con = connexion1.getConnection();
			
			String reqclient= "delete from client where numeroCartefidelite =  ? ";
			
			   
	        PreparedStatement prepare = con.prepareStatement(reqclient);
	        prepare.setInt(1, this.numeroCartefidelite);

	        prepare.executeUpdate();
	        
	        System.out.println("client supprimé");
	        
		} catch(Exception e) {
			e.printStackTrace();
	        	}
	
  } 
*/
}
