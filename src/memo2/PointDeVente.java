package memo2;
import java.sql.*;

public class PointDeVente {
	
	private String nomPointdevente;
	private int numeroBadge, codeProduit, numeroCartefidelite, nb_article;
        private float poids, tva, sousTotal, total;
	 
	public PointDeVente(String nom, int numeroBadge, int codepx, int numCarte, int nbArt, float poids, float tva, float st, float ttal ) {
		this.nomPointdevente = nom;
		this.numeroBadge = numeroBadge;
                this.codeProduit = codepx;
                this.numeroCartefidelite = numCarte;
                this.nb_article = nbArt;
                this.poids = poids;
                this.sousTotal = st;
                this.total = ttal;
		 
	}
	public String getNomPointDevente() {
		return nomPointdevente;
	}
	public void setNomPointDevente(String nomPointdevente) {
		this.nomPointdevente = nomPointdevente;
	}
	
	public int getNumeroBadge() {
		return numeroBadge;
	}
	
	public void setNumeroBadge(int numeroBadge) {
		this.numeroBadge = numeroBadge;
	}
	
        public String getCodeproduit(String codeProduit){
            return codeProduit;
        }
        public void setCodeproduit(){
            this.codeProduit = codeProduit;
        }
        public int getNumeroCartefidelite(int numeroCartefidelite){
            return numeroCartefidelite;
        }
        public void setNumeroCarte(){
            this.numeroCartefidelite = numeroCartefidelite;
            
        }
        public int getNb_article(int nbArt){
            return nb_article;
        }
        public void setNb_article(){
            this.nb_article = nb_article;
        }
        public float getPoids(float poids){
            return poids;
        }
        public void setPois(){
            this.poids = poids;
        }    
        public float getTva(float tva){
                return tva;
        }
        public void setTva(){
            this.tva = tva;
        }
        public float getTotal(float total){
            return total;
        }
        public void setTotal(){
            this.total = total;
        }
            
        
}/*public static void ajouterPointDevente(PointDeVente PointDevente) {
		Bd connexion1 = new Bd();
		
		try {
			Connection con = connexion1.getConnection();
		
			String reqPv = "insert into point_devente (nomPointdevente, numeroBadge) values(?, ?)";
	    	
	        PreparedStatement pstmt= con.prepareStatement(reqPv) ;
	        	pstmt.setString(1, PointDevente.getNomPointDevente());
	        	pstmt.setInt(2,  PointDevente.getNumeroBadge());
	                  
	            pstmt.executeUpdate();
	            
	            System.out.println("insertin faite");
	            
	        } catch (Exception e) {
	           
	            e.printStackTrace();
	        }		
		}
	
	
	public void  updatePv(String attr){
		   Bd connexion1 = new Bd();
			
			try {
				Connection con = connexion1.getConnection();
				
			String reqPv = "update point_devente set "+attr+" = ?  where nomPointdevente =  ?";
		 
	        PreparedStatement prepare = con.prepareStatement(reqPv);
	        
	        switch (attr) {
	        	case("nomPointdevente"):
	        	prepare.setString(1, this.nomPointdevente);
	        	break;
	        	case ("numeroBadge"):
	        		prepare.setInt(1, this.numeroBadge);     		
	        }  
	        	
	        prepare.setString(2, this.nomPointdevente);
	        
	        prepare.executeUpdate(); 
			          
	        System.out.println("mise à jour faite");
			
			} catch(Exception e) {
				e.printStackTrace();
			}	
	  } 
	
	
	public void supprimerPointdevente(PointDeVente pointdevente) {
		
		Bd connexion1 = new Bd();
		
		try {
			
			Connection con = connexion1.getConnection();
			
			String reqPv= "delete from employe where nomPointdevente =  ? ";
			
			   
	        PreparedStatement prepare = con.prepareStatement(reqPv);
	        prepare.setString(1, this.nomPointdevente);

	        prepare.executeUpdate();
	        
	        System.out.println("ligne supprimée");
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	
	}
}

*/