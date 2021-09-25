
package memo2;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static memo2.Bd.con;
  

public class Crud_Emp extends javax.swing.JFrame {

    public Crud_Emp() {
        initComponents();
        connecter();
        LoadNumBadge();
        updateTable();
    }
    Connection con;
    ResultSet rs;
    PreparedStatement pst;
    PreparedStatement insert;
    
    public void connecter(){
        try {
           Class.forName("com.mysql.cj.jdbc.Driver" );
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/commerce?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "root");
           System.out.println("connection faite");
        } catch (Exception e) {
            e.printStackTrace();      
        } 
    }
    public void LoadNumBadge(){
        try{
            pst = con.prepareStatement("select numeroBadge from employe");
            rs = pst.executeQuery();
            combo1.removeAllItems();
            while(rs.next()){
                
                combo1.addItem(rs.getString(1));
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }      
    }
    public void updateTable(){
        int j , numeroBadge;
      
    try {
          Class.forName("com.mysql.cj.jdbc.Driver" );
          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/commerce?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "root");
          insert = con.prepareStatement("select * from employe");
          ResultSet rs = insert.executeQuery();
          
          ResultSetMetaData Rss = rs.getMetaData();
          
          j = Rss.getColumnCount();
          
          DefaultTableModel Df = (DefaultTableModel)tab1.getModel();
            
            Df.setRowCount(0);
            
            while(rs.next()){
                Vector ob= new Vector();
                
                for(int i = 1; i<=j; i++){
                    ob.add(rs.getString("numeroBadge"));
                    ob.add(rs.getString("nom"));
                    ob.add(rs.getString("prenom"));
                    ob.add(rs.getString("mail"));
                    ob.add(rs.getString("adresseEmploye"));
                    ob.add(rs.getString("adresseLieuDetravail"));
                    ob.add(rs.getString("poste"));
                    ob.add(rs.getString("superieurImmediat"));
                }
                Df.addRow(ob);
                
            }
                 
      }catch(Exception e){
          e.printStackTrace();
      }    
    }
    public void supprimerEmploye(){
            String id = combo1.getSelectedItem().toString();
    
	try {
            
	String reqEmp = "delete from employe where numeroBadge =? ";
        
        PreparedStatement pst = con.prepareStatement(reqEmp);
                pst.setString(1, id);
	        pst.executeUpdate(); 	          
	        JOptionPane.showMessageDialog(this, "suppression faite"); 
                LoadNumBadge();
                
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void rechercherEmploye(){
        String id;
        id = combo1.getSelectedItem().toString();
       try{
       pst = con.prepareStatement("select * from employe where numeroBadge = ?");
       pst.setString(1, id);
       rs = pst.executeQuery();
       if(rs.next() == true){
           tf2.setText(rs.getString(2));
           tf3.setText(rs.getString(3));
           tf4.setText(rs.getString(4));
           tf5.setText(rs.getString(5));
           tf6.setText(rs.getString(6));
           tf7.setText(rs.getString(7));
           tf8.setText(rs.getString(8));
       }
       } catch(Exception e){
           e.printStackTrace();
       }
    }
    
    public void creerEmploye(){
            String numbadge = tf1.getText();
        String nom = tf2.getText();
        String prenom = tf3.getText();
        String mail = tf4.getText();
        String adP = tf5.getText();
        String adT = tf6.getText();
        String post = tf7.getText();
        String sup = tf8.getText();
      	
	try {
				
	String reqEmp = "insert into Employe(numeroBadge , nom, prenom, mail, adresseEmploye, adresseLieuDetravail, poste, superieurImmediat) values(?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement pst = con.prepareStatement(reqEmp);
	        pst.setString(1, numbadge);
	        pst.setString(2, nom);
	        pst.setString(3, prenom);
	        pst.setString(4, mail);
	        pst.setString(5, adP);
	        pst.setString(6, adT);
	        pst.setString(7, post);
	        pst.setString(8,  sup);
                int t = pst.executeUpdate(); 
                LoadNumBadge();
                updateTable();
                if (t == 1){
                   JOptionPane.showMessageDialog(this, "insertion faite");
                   tf1.setText("");
                   tf2.setText("");
                   tf3.setText("");
                   tf4.setText("");
                   tf5.setText("");
                   tf6.setText("");
                   tf7.setText("");
                   tf8.setText("");
                }
                else{
                    JOptionPane.showMessageDialog(this, "echec !");
                }
	        
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "echec !");
        }
    }
    public void updateEmploye(){
        String id = combo1.getSelectedItem().toString();
        String nom = tf2.getText();
        String prenom = tf3.getText();
        String mail = tf4.getText();
        String adP = tf5.getText();
        String adT = tf6.getText();
        String post = tf7.getText();
        String sup = tf8.getText();
      	
	try {
            
	String reqEmp = "update employe set nom =?, prenom =?, mail =?, adresseEmploye=?, adresseLieuDetravail=?, poste=?, superieurImmediat=? where numeroBadge =? ";
        
        PreparedStatement pst = con.prepareStatement(reqEmp);
	        
	        pst.setString(1, nom);
	        pst.setString(2, prenom);
	        pst.setString(3, mail);
	        pst.setString(4, adP);
	        pst.setString(5, adT);
	        pst.setString(6, post);
	        pst.setString(7,  sup);
                pst.setString(8, id);
                int t= pst.executeUpdate(); 
                LoadNumBadge();
                if (t == 1){
                   JOptionPane.showMessageDialog(this, "mise a jour faite");
                   tf1.setText("");
                   tf2.setText("");
                   tf3.setText("");
                   tf4.setText("");
                   tf5.setText("");
                   tf6.setText("");
                   tf7.setText("");
                   tf8.setText("");
                }
                else{
                    JOptionPane.showMessageDialog(this, "echec !");
                }
                
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void viderFormulaire(){
         tf1.setText("");
         tf2.setText("");
         tf3.setText("");
         tf4.setText("");
         tf5.setText("");
         tf6.setText("");
         tf7.setText("");
         tf8.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label5 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        label6 = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        label7 = new javax.swing.JLabel();
        label8 = new javax.swing.JLabel();
        tf6 = new javax.swing.JTextField();
        tf2 = new javax.swing.JTextField();
        tf1 = new javax.swing.JTextField();
        tf4 = new javax.swing.JTextField();
        tf3 = new javax.swing.JTextField();
        tf5 = new javax.swing.JTextField();
        tf7 = new javax.swing.JTextField();
        tf8 = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        search = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab1 = new javax.swing.JTable();
        combo1 = new javax.swing.JComboBox<>();
        label9 = new javax.swing.JLabel();
        edition = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        label5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label5.setText("adresse personnel");

        label3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label3.setText("prenom");

        label4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label4.setText("mail");

        label6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label6.setText("adresse lieu de travail");

        label1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label1.setText("numero de badge");

        label2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label2.setText("nom");

        label7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label7.setText("poste");

        label8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label8.setText("Superieur immediat");

        tf6.setText(" ");

        tf2.setText(" ");

        tf1.setText(" ");
        tf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf1ActionPerformed(evt);
            }
        });

        tf4.setText(" ");
        tf4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf4ActionPerformed(evt);
            }
        });

        tf3.setText(" ");

        tf5.setText(" ");

        tf7.setText(" ");

        tf8.setText(" ");

        add.setText("Create");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tf7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(tf6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf8)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 410, 420));

        jLabel9.setBackground(new java.awt.Color(51, 255, 0));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setText("Gestion du personnel");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 0, 465, 37));

        search.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        getContentPane().add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 510, -1, 32));

        tab1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tab1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero Badge", "Nom", "Prenom", "Mail", "Adresse perso", "Adresse Travail", "Poste", "Superieur"
            }
        ));
        tab1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tab1PropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tab1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 700, 120));

        combo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo1ActionPerformed(evt);
            }
        });
        getContentPane().add(combo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 510, 90, 30));

        label9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label9.setText("Numero Badge :");
        getContentPane().add(label9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 120, 30));

        edition.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        edition.setText("Reinitialize");
        edition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editionActionPerformed(evt);
            }
        });
        getContentPane().add(edition, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 510, 100, 30));

        jButton1.setBackground(new java.awt.Color(0, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Accueil");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 540, -1, -1));

        jButton2.setBackground(new java.awt.Color(0, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("Ventes");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 540, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf1ActionPerformed

    private void tf4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf4ActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
     
    supprimerEmploye();
    }//GEN-LAST:event_deleteActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
       rechercherEmploye();
    }//GEN-LAST:event_searchActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
         creerEmploye();
    }//GEN-LAST:event_addActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        updateEmploye();
    }//GEN-LAST:event_updateActionPerformed

    private void editionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editionActionPerformed
       
        viderFormulaire();
    }//GEN-LAST:event_editionActionPerformed

    private void tab1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tab1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tab1PropertyChange

    private void combo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo1ActionPerformed
        
        
        
    }//GEN-LAST:event_combo1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Accueil_commerce a = new Accueil_commerce();
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Vente v = new Vente();
        v.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Crud_Emp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JComboBox<String> combo1;
    private javax.swing.JButton delete;
    private javax.swing.JButton edition;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label8;
    private javax.swing.JLabel label9;
    private javax.swing.JButton search;
    private javax.swing.JTable tab1;
    private javax.swing.JTextField tf1;
    private javax.swing.JTextField tf2;
    private javax.swing.JTextField tf3;
    private javax.swing.JTextField tf4;
    private javax.swing.JTextField tf5;
    private javax.swing.JTextField tf6;
    private javax.swing.JTextField tf7;
    private javax.swing.JTextField tf8;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
