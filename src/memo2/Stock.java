package memo2;

import java.sql.Connection;

import java.sql.PreparedStatement;

public class Stock {
	private String   nomMagasin, libelleProduit, description, fournisseur, origine, typeProduit;
	private double prix, tauxTva, quantite;
        private int codeProduit;

	public Stock(int codeProduit, String nomMg, String libelle, String typepx, double quantit, String description, double prix,
                String origine, String fournisseur, double tauxTva ) {
		this.libelleProduit = libelle;
		this.codeProduit = codeProduit;
		this.description = description;
		this.fournisseur = fournisseur;
		this.origine = origine;
		this.prix = prix;             
		this.tauxTva = tauxTva;
                this.quantite = quantit;
                this.nomMagasin = nomMg;
                this.typeProduit = typepx;
	}
	
	public String getLibelleproduit() {
		return libelleProduit;
	}
	public void setLibelleproduit(String libelleProduit) {
            this.libelleProduit = libelleProduit;
		
	}
	public int getCodeproduit() {
		return codeProduit;
	}
	
	public void setCodeproduit(int codeProduit) {
		this.codeProduit = codeProduit;
	}
        public String getNomMagasin(){
            return nomMagasin;
        }
        public void setNomMagasin(String nomMagasin){
            this.nomMagasin = nomMagasin;
        }
        
        public String getTypeProduit() {
	return typeProduit;
        }
        public void setTypeproduit(String typeproduit) {
	this.typeProduit = typeProduit;
        }
        
	public String getDescription() {
		
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}
	public String getOrigine() {
		return origine;
	}
	public void setOrigine(String origine) {
		this.origine = origine;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
        public double getQuantite(){
            return quantite;
        }
        public void setQuantite(double quantite){
            this.quantite = quantite;
        }
	
	public double getTauxtva() {
		return tauxTva;
	}
	public void setTauxtva(double tauxTva) {
		this.tauxTva = tauxTva;
	}
	
 
	 public void ajouterProduit(Stock stock) {
Bd connexion1 = new Bd();
    	
    	try {
    		
		Connection con1 = connexion1.getConnection();
			
    	String reqX = "insert into Stock(codeProduit, nomMagasin, libelleproduit, typeProduit, quantite, description, prix,\n" +
"               origine, fournisseur,  tauxTva) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	
        PreparedStatement pstmt= con1.prepareStatement(reqX) ;
            pstmt.setInt(1,  stock.getCodeproduit());
            pstmt.setString(2,  stock.getNomMagasin());
            pstmt.setString(3, stock.getLibelleproduit());
            pstmt.setString(4, stock.getTypeProduit());
            pstmt.setDouble(5, stock.getQuantite());
            pstmt.setString(6, stock.getDescription());
            pstmt.setDouble(7,  stock.getPrix());
            pstmt.setString(8,  stock.getOrigine());
            pstmt.setString(9,  stock.getFournisseur());
            pstmt.setDouble(10,  stock.getTauxtva());
            
            
            pstmt.executeUpdate();
            
            System.out.println("insertion faite");
            
        } catch (Exception e) {
           
            e.printStackTrace();
        }
    }
		
	/*
	public void updateProduit(Stock produit) {
		
		Bd connexion1 = new Bd();
		try {
			Connection con1 = connexion1.getConnection();
			
	    	String reqX = "update produit (libelleProduit , codeProduit, prix, description, origine, fournisseur, tauxTva) set (?, ?, ?, ?, ?)";
	    	
	        PreparedStatement pstmt= con1.prepareStatement(reqX) ;
	            pstmt.setString(1,  produit.libelleProduit);
	            pstmt.setString(2, produit.getDescription());
	            pstmt.setString(3, produit.getFournisseur());
	            pstmt.setString(4, produit.getOrigine());
	            pstmt.setDouble(5, produit.getPrix());
	            
		} catch (Exception e) {
           
            e.printStackTrace();
        }
		
	}
	
	
	public void  updateX(String attr){
		   Bd connexion1 = new Bd();
			
			try {
				Connection con1 = connexion1.getConnection();
				
			String reqX = "update produit set "+attr+" = ?  where libelleProduit =  ? ";
		 
	        PreparedStatement prepare = con1.prepareStatement(reqX);
	        
	        switch (attr) {
	        	case("libelleProduit"):
	        	prepare.setString(1, this.libelleProduit);
	        	break;
	        	case ("codeProduit"):
	        		prepare.setString(1, this.codeProduit);
	        		break;
	        	case ("description"):
	        		prepare.setDouble(1, this.prix);
	        		break;
	        	case ("origine"):
	        		prepare.setString(1, this.origine);
	        		break;
	        	case ("fournisseur"):
	        		prepare.setString(1, this.fournisseur);
	        		break;
	        	case ("tauxTva"):
	        		prepare.setDouble(1, this.tauxTva);
	        		
	        }  
	        	
	        prepare.setString(2, this.libelleProduit);
	        
	        prepare.executeUpdate(); 
			          
	        System.out.println("mise à jour faite");
			
			} catch(Exception e) {
				e.printStackTrace();
			}
		
	  } 
	
	public void supprimerProduit(Stock produit) {
		
		Bd connexion1 = new Bd();
		
		try {
			
			Connection con1 = connexion1.getConnection();
			
			String reqX = "delete produit from produit where libelleProduit = ?";
			 			   
			        PreparedStatement prepare = con1.prepareStatement(reqX);
			        prepare.setString(1, this.libelleProduit);

			        prepare.executeUpdate();
			       
			
			System.out.println("produit supprimé !");

		} catch(Exception e) {
			e.printStackTrace();
		}
	
}*/

}
