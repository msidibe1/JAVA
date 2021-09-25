
package memo2;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Vente extends javax.swing.JFrame {
    
    DefaultTableModel d = new DefaultTableModel();
    private int selectedrow;

    public Vente() {
        initComponents();
        connecter();
        LoadNumfidelite();
        LoadNumBadge();
        LoadNomPointDevente();
        choisirProduit();
        d = (DefaultTableModel)panier.getModel();
    }
    Connection con;
    ResultSet rs, rs1;
    PreparedStatement pst;
    
     public void LoadNumfidelite(){
        try{
            pst = con.prepareStatement("select numeroCartefidelite from client");
            rs = pst.executeQuery();

            while(rs.next()){

                combo1.addItem(rs.getString(1));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
     }
    public void LoadNumBadge(){
        
        try{
            pst = con.prepareStatement("select numeroBadge from employe");
            rs = pst.executeQuery();
            combo3.removeAllItems();

            while(rs.next()){

                combo3.addItem(rs.getString(1));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
     }
    public void LoadNomPointDevente(){
        try{
            pst = con.prepareStatement("select distinct nomMagasin from stock");
            rs = pst.executeQuery();
            combo2.removeAllItems();

            while(rs.next()){

                combo2.addItem(rs.getString(1));

            }
        } catch(Exception e){
            e.printStackTrace();
        }
     }
    public void connecter(){
        try {
           Class.forName("com.mysql.cj.jdbc.Driver" );
           con = DriverManager.getConnection("jdbc:mysql://localhost:3306/commerce?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "root");
           System.out.println("connection faite");
        } catch (Exception e) {
            e.printStackTrace();      
        } 
    }
    
    public void choisirProduit(){
        
        try{
            String codeproduit = txt_code.getText();
            pst = con.prepareStatement("select * from stock where codeProduit = ?");
            pst.setString(1, codeproduit);
            rs = pst.executeQuery();
       
        if(rs.next() == false){                //si le code barre ne figure pas dans le stock
            
           JOptionPane.showMessageDialog(this, "code barre introuvable");
            txt_code.setText(null);
            
        } else{  
            
            String libelle = rs.getString("libelleProduit");
            String prix = rs.getString("prix");
            String type = rs.getString("typeproduit");
            
            txtf_libelle.setText(libelle.trim());  // affiche le prix et le libelle
            txt_prix.setText(prix.trim());
        }
   
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ajouterAuPanier(){
     
        String codeproduit, libelle, poids; 
               codeproduit= txt_code.getText();
               libelle = txtf_libelle.getText();
               Double quantite = Double.parseDouble(txt_quantite.getText().trim());
               poids = txt_poids.getText();
               Double prix = Double.parseDouble(txt_prix.getText());
               Double prixTotal = prix * quantite;
               //txt_total.setText(prixTotal.toString());
               Double tva = null;
               Double sousTotal = null;
               
            Object []ligne = new Object[]{codeproduit, libelle, quantite, poids, prixTotal};  
            d.addRow(ligne);
            for(int i = 0; i < d.getRowCount(); i++){
                prixTotal = prixTotal + Double.parseDouble(panier.getValueAt(i, 4).toString());
                txt_total.setText(prixTotal.toString());
            }
            
            try{ 
            pst = con.prepareStatement("select typeproduit from stock where codeProduit = ?");
            pst.setString(1, codeproduit);
            rs = pst.executeQuery();
            rs.next();
            String type = rs.getString("typeproduit");
           switch(type){
               case "electronique":
                   tva = prixTotal * 0.05;
                   break;
               case"medecine":
               case"alimentaire":
                   tva = prixTotal * 0.02;
                   break;  
           }  
           txt_tva.setText(tva.toString());
             }catch(Exception e){
                 e.printStackTrace();
             } 
            
            sousTotal = prixTotal - tva;
            txt_sousTotal.setText(sousTotal.toString());
        
        /*  double prix = Double.parseDouble(txt_prix.getText());
        double quantite = Double.parseDouble(txt_quantite.getText());
        
        double total = prix * quantite;
        d = (DefaultTableModel)panier.getModel();
        d.addRow(new Object[]
        {
            
        });
       */
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_sousTotal = new javax.swing.JTextField();
        txt_tva = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_PointFidelite = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_code = new javax.swing.JTextField();
        txtf_libelle = new javax.swing.JTextField();
        txt_poids = new javax.swing.JTextField();
        txt_prix = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panier = new javax.swing.JTable();
        ajout = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btn_supprimerProduit = new javax.swing.JButton();
        combo1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        combo2 = new javax.swing.JComboBox<>();
        combo3 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txt_quantite = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Sous Total");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("TVA");

        txt_sousTotal.setText(" ");
        txt_sousTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sousTotalActionPerformed(evt);
            }
        });

        txt_tva.setText(" ");

        txt_total.setText(" ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Total");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Point de fidelite");

        txt_PointFidelite.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_sousTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_total, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(txt_tva)
                            .addComponent(txt_PointFidelite))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sousTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tva, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_PointFidelite, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 240, -1, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Espace vente");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 0, 1116, 35));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Libellé");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 100, -1, 36));

        txt_code.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_code.setToolTipText("Entrer le codebar et tapez sur Entrer");
        txt_code.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_codeMouseEntered(evt);
            }
        });
        txt_code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_codeActionPerformed(evt);
            }
        });
        txt_code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_codeKeyPressed(evt);
            }
        });
        getContentPane().add(txt_code, new org.netbeans.lib.awtextra.AbsoluteConstraints(86, 101, 108, 36));

        txtf_libelle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtf_libelle.setText(" ");
        txtf_libelle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_libelleActionPerformed(evt);
            }
        });
        getContentPane().add(txtf_libelle, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 100, 135, 37));

        txt_poids.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_poids.setText(" ");
        txt_poids.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_poidsActionPerformed(evt);
            }
        });
        getContentPane().add(txt_poids, new org.netbeans.lib.awtextra.AbsoluteConstraints(598, 100, 89, 36));

        txt_prix.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_prix.setText(" ");
        txt_prix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_prixActionPerformed(evt);
            }
        });
        getContentPane().add(txt_prix, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 100, 100, 36));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Poids");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(561, 100, -1, 34));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Quantite");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(393, 101, -1, 34));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Prix");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(702, 100, 30, 34));

        panier.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        panier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codeproduit", "Libelle", "quantite", "poids", "prixTotal"
            }
        ));
        panier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(panier);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 710, 137));

        ajout.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ajout.setText("Ajouter au panier");
        ajout.setToolTipText("N'oubliez pas d'indiquer la quantite ou le poids");
        ajout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutActionPerformed(evt);
            }
        });
        getContentPane().add(ajout, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 180, -1, 32));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Valider achat");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, -1, 32));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Annuler");
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, -1, 32));

        btn_supprimerProduit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_supprimerProduit.setText("Supprimer");
        btn_supprimerProduit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_supprimerProduitActionPerformed(evt);
            }
        });
        getContentPane().add(btn_supprimerProduit, new org.netbeans.lib.awtextra.AbsoluteConstraints(592, 180, -1, 32));

        combo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0" }));
        combo1.setToolTipText("0 si le client n'a pas de carte de fidelite");
        getContentPane().add(combo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(954, 100, 70, 38));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Numero fidelite");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 101, 100, 36));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1068, 20, -1, -1));

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Nom Point de vente");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Badge employe");

        combo2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item1" }));
        combo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo2ActionPerformed(evt);
            }
        });

        combo3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combo3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(combo3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(combo2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 39, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Code produit");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 100, -1, 36));

        txt_quantite.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_quantite.setText(" ");
        txt_quantite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_quantiteActionPerformed(evt);
            }
        });
        getContentPane().add(txt_quantite, new org.netbeans.lib.awtextra.AbsoluteConstraints(451, 100, 100, 37));

        jButton1.setBackground(new java.awt.Color(0, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Accueil");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 9, 123, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtf_libelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_libelleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_libelleActionPerformed

    private void txt_sousTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sousTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sousTotalActionPerformed

    private void txt_prixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_prixActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_prixActionPerformed

    private void txt_poidsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_poidsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_poidsActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_supprimerProduitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_supprimerProduitActionPerformed
       
        String codeproduit = null, quantite = null, poids = null, prix = null;
        String[] ligne = new String[]{codeproduit, quantite, poids, prix};
        d.removeRow(selectedrow);
        
    }//GEN-LAST:event_btn_supprimerProduitActionPerformed

    private void ajoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutActionPerformed
        
        ajouterAuPanier();
        
    }//GEN-LAST:event_ajoutActionPerformed

    private void panierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panierMouseClicked
       
        selectedrow = panier.getSelectedRow();
    }//GEN-LAST:event_panierMouseClicked

    private void txt_codeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_codeActionPerformed
    
    }//GEN-LAST:event_txt_codeActionPerformed

    private void txt_quantiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_quantiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quantiteActionPerformed

    private void txt_codeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codeKeyPressed
        
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER){
           choisirProduit(); 
        }
    }//GEN-LAST:event_txt_codeKeyPressed

    private void txt_codeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_codeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codeMouseEntered

    private void combo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Accueil_commerce a = new Accueil_commerce();
        a.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> {
            new Vente().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajout;
    private javax.swing.JButton btn_supprimerProduit;
    private javax.swing.JComboBox<String> combo1;
    private javax.swing.JComboBox<String> combo2;
    private javax.swing.JComboBox<String> combo3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable panier;
    private javax.swing.JTextField txt_PointFidelite;
    private javax.swing.JTextField txt_code;
    private javax.swing.JTextField txt_poids;
    private javax.swing.JTextField txt_prix;
    private javax.swing.JTextField txt_quantite;
    private javax.swing.JTextField txt_sousTotal;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_tva;
    private javax.swing.JTextField txtf_libelle;
    // End of variables declaration//GEN-END:variables
}
