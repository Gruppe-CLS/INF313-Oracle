/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arbeidskrav2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Christian & Ståle
 */
public class Grensesnitt extends javax.swing.JFrame {

   /*
    == GRENSESNITT - Ved opprettelse
    =====================================================================================
    == Setter opp de felter vi ønsker å vise ved oppstart, og skjuler annen info. 
    == Legger også radioknappene inn i en ButtonGroup.
    == Vi har valgt å vise studentoversikten ved oppstart.
    =====================================================================================
    */
    public Grensesnitt() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Setter fullskjerm på programmet
        initComponents();
        rbStudent.setSelected(true);
        cbKarakter.setVisible(false);
        cbFagnr.setVisible(false);
        cbStudentnr.setVisible(false);
        lbl4.setText("");
        
        // Radioknapp-Gruppe for Legg til-side
        ButtonGroup gruppe = new ButtonGroup();
        gruppe.add(rbFag);
        gruppe.add(rbKarakter);
        gruppe.add(rbStudent);
        
        // Radioknapp-gruppe for SØK-siden
        ButtonGroup searchGruppe = new ButtonGroup();
        searchGruppe.add(rbEnavn);
        searchGruppe.add(rbEpost);
       
        /*
        == OVERSIKT ALLE STUDENTER - Lastes inne i Grensesnitt()
        ============================================================================
        == Vi laster inn oversikten over alle studenter ved oppstart av programmet.
        == Ved innlasting utfører vi flere operasjoner. Vi begynner med en SwingWorker 
        == doInBackground()-funksjon som henter ut infomasjon fra DB mens vi venter. 
        == Vi fyller ut antall studenter, strykprosent, og vi lister ut en oversikt over 
        == alle studentene til slutt.
        ============================================================================
        */
        JOptionPane messagePane = new JOptionPane(
        "Finner database... Vennligst vent.",
        JOptionPane.INFORMATION_MESSAGE);
        final JDialog dialog = messagePane.createDialog(this, "Søker.");
         try {
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    // Henter ut alle studenter:
                    String uttekst = ""; // Ved tom liste viser vi denne
                    String nr, passord, fnavn, enavn, epost;
                    try {
                        ResultSet rs = Kontroll.kontroll.hentAlleStudenter();
                        DefaultTableModel tabell = (DefaultTableModel) TableOversikt.getModel();
                        while (rs.next()) {
                            nr = rs.getString(1);
                            passord = rs.getString(2);
                            fnavn = rs.getString(3);
                            enavn = rs.getString(4);
                            epost = rs.getString(5);
                            uttekst = uttekst + nr + ", "+passord+", "+fnavn + ", " + enavn + ", " + epost ;
                            // Vi legger informasjonen inn i tabellen
                            Object[] nyRad = {nr, passord, fnavn, enavn, epost};
                            tabell.addRow(nyRad);
                        }
                        rs.close();
                        if (uttekst.equals("")) {   
                            uttekst = "Ingen studenter";
                            lblFeilmelding.setText(uttekst);
                        } 
                        
                        // Henter ut Antall Studenter
                        int antStud = Kontroll.kontroll.getAntallStudenter();
                        String ut = Integer.toString(antStud);
                        lblAntStud.setText(ut);
                        
                        // Hent ut strykprosent
                        int strpro = Kontroll.kontroll.getStrykprosent();
                        String strykprosen = Integer.toString(strpro);
                        lblStrykprosent.setText(strykprosen + "%");
                        
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    return null;
                }
                // Når søket er ferdig kjører denne:
                protected void done() {
                    dialog.dispose();
                }
            ;
            }.execute();
            dialog.setVisible(true);
        } catch (Exception e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelOverview = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblAntStud = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblFeilmelding = new javax.swing.JLabel();
        lblStrykprosent = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableOversikt = new javax.swing.JTable();
        jPanelAdd = new javax.swing.JPanel();
        rbStudent = new javax.swing.JRadioButton();
        rbFag = new javax.swing.JRadioButton();
        rbKarakter = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        lbl3 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        txt3 = new javax.swing.JTextField();
        btnReg = new javax.swing.JToggleButton();
        lbl4 = new javax.swing.JLabel();
        cbKarakter = new javax.swing.JComboBox();
        cbStudentnr = new javax.swing.JComboBox();
        cbFagnr = new javax.swing.JComboBox();
        jPanelSearch = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbStudentListe = new javax.swing.JComboBox();
        lblStudentInfo = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rbEnavn = new javax.swing.JRadioButton();
        rbEpost = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
        });

        jPanelOverview.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanelOverviewFocusGained(evt);
            }
        });
        jPanelOverview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelOverviewMouseClicked(evt);
            }
        });
        jPanelOverview.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelOverviewComponentShown(evt);
            }
        });

        jLabel2.setText("Antall studenter:");

        lblAntStud.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        jLabel3.setText("Total Strykprosent:");

        lblFeilmelding.setBackground(new java.awt.Color(0, 0, 0));
        lblFeilmelding.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblFeilmelding.setForeground(new java.awt.Color(0, 0, 0));

        lblStrykprosent.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        jLabel4.setText("Liste over alle studenter:");

        TableOversikt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Studentnr", "Passord", "Fornavn", "Etternavn", "Epost"
            }
        ));
        jScrollPane1.setViewportView(TableOversikt);

        javax.swing.GroupLayout jPanelOverviewLayout = new javax.swing.GroupLayout(jPanelOverview);
        jPanelOverview.setLayout(jPanelOverviewLayout);
        jPanelOverviewLayout.setHorizontalGroup(
            jPanelOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOverviewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelOverviewLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(1044, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelOverviewLayout.createSequentialGroup()
                        .addGroup(jPanelOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelOverviewLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblAntStud, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelOverviewLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblStrykprosent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFeilmelding, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122))))
        );
        jPanelOverviewLayout.setVerticalGroup(
            jPanelOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOverviewLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanelOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelOverviewLayout.createSequentialGroup()
                        .addGroup(jPanelOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblAntStud, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblStrykprosent, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblFeilmelding, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Oversikt", jPanelOverview);

        rbStudent.setText("Student");
        rbStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbStudentActionPerformed(evt);
            }
        });

        rbFag.setText("Fag");
        rbFag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbFagActionPerformed(evt);
            }
        });

        rbKarakter.setText("Karakter");
        rbKarakter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbKarakterActionPerformed(evt);
            }
        });

        jLabel1.setText("Legg til ny:");

        lbl1.setText("Fornavn:");

        lbl2.setText("Etternavn:");

        lbl3.setText("Epost:");

        btnReg.setText("Registrer ny");
        btnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegActionPerformed(evt);
            }
        });

        lbl4.setText("Karakter:");

        cbKarakter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "E", "F" }));

        cbStudentnr.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Finner studenter" }));

        cbFagnr.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Finner ingen fag" }));

        javax.swing.GroupLayout jPanelAddLayout = new javax.swing.GroupLayout(jPanelAdd);
        jPanelAdd.setLayout(jPanelAddLayout);
        jPanelAddLayout.setHorizontalGroup(
            jPanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAddLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAddLayout.createSequentialGroup()
                        .addGroup(jPanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAddLayout.createSequentialGroup()
                                .addComponent(lbl4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbKarakter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelAddLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbStudent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbFag)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbKarakter))
                            .addGroup(jPanelAddLayout.createSequentialGroup()
                                .addGroup(jPanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl1)
                                    .addComponent(lbl2)
                                    .addComponent(lbl3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanelAddLayout.createSequentialGroup()
                                            .addComponent(cbStudentnr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txt1))
                                        .addGroup(jPanelAddLayout.createSequentialGroup()
                                            .addComponent(cbFagnr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 817, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelAddLayout.setVerticalGroup(
            jPanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(rbStudent)
                    .addComponent(rbFag)
                    .addComponent(rbKarakter))
                .addGap(18, 18, 18)
                .addGroup(jPanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl1)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStudentnr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl2)
                    .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFagnr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl3)
                    .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl4)
                    .addComponent(cbKarakter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 316, Short.MAX_VALUE)
                .addComponent(btnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Legg til", jPanelAdd);

        jPanelSearch.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelSearchComponentShown(evt);
            }
        });

        jLabel5.setText("Søk etter student:");

        cbStudentListe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Velg student" }));
        cbStudentListe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStudentListeActionPerformed(evt);
            }
        });

        lblStudentInfo.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });

        jLabel6.setText("Søk etter student på:");

        rbEnavn.setText("Etternavn");

        rbEpost.setText("Epost");

        javax.swing.GroupLayout jPanelSearchLayout = new javax.swing.GroupLayout(jPanelSearch);
        jPanelSearch.setLayout(jPanelSearchLayout);
        jPanelSearchLayout.setHorizontalGroup(
            jPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSearchLayout.createSequentialGroup()
                        .addComponent(lblStudentInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanelSearchLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbEnavn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbEpost)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 363, Short.MAX_VALUE)
                        .addComponent(cbStudentListe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(468, 468, 468))
                    .addGroup(jPanelSearchLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanelSearchLayout.setVerticalGroup(
            jPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbStudentListe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(rbEnavn)
                        .addComponent(rbEpost)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStudentInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Søk", jPanelSearch);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    /*
    == RADIOBUTTON - Fag
    =====================================================================================
    == Setter opp riktig inputfelter ved trykk på fag på "Legg til"-siden
    =====================================================================================
    */
    private void rbFagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbFagActionPerformed
        lbl4.setText("");
        txt1.setVisible(true);
        txt2.setVisible(true);
        cbFagnr.setVisible(false);
        cbStudentnr.setVisible(false);
        cbKarakter.setVisible(false);
        lbl1.setText("Fagnr:");
        lbl2.setText("Fagnavn:");
        lbl3.setText("Studiepoeng:");
    }//GEN-LAST:event_rbFagActionPerformed

    
    
    /*
    == RADIOBUTTON - Karakter
    =====================================================================================
    == Setter opp riktig inputfelter ved trykk på karakter på "Legg til"-siden
    =====================================================================================
    */
    private void rbKarakterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbKarakterActionPerformed
        lbl1.setText("Studentnr:");
        lbl2.setText("Fagnr:");
        lbl3.setText("Dato:");
        lbl4.setText("Karakter:");
        txt1.setVisible(false);
        txt2.setVisible(false);
        cbKarakter.setVisible(true);
        cbFagnr.setVisible(true);
        cbStudentnr.setVisible(true);
    }//GEN-LAST:event_rbKarakterActionPerformed

    
    /*
    == RADIOBUTTON - Student
    =====================================================================================
    == Setter opp riktig inputfelter ved trykk på student på "Legg til"-siden
    =====================================================================================
    */
    private void rbStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbStudentActionPerformed
        cbKarakter.setVisible(false);
        txt1.setVisible(true);
        txt2.setVisible(true);
        cbFagnr.setVisible(false);
        cbStudentnr.setVisible(false);
        lbl4.setText("");
        lbl1.setText("Fornavn:");
        lbl2.setText("Etternavn:");
        lbl3.setText("Epost:");
    }//GEN-LAST:event_rbStudentActionPerformed

    
    
    /*
    == REGISTRER-knappen (btnReg)
    =====================================================================================
    == Vi finner først ut hvilken radio-knapp som er huket av, også legger vi inn 
    == informasjonen i riktig tabell deretter. Det er altså 3 tilnærmet like if-tester.
    =====================================================================================
    */
    private void btnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegActionPerformed
        // Legger knappene i en boolean, slik at vi kan finne ut hvilken som er valgt
        boolean student = rbStudent.isSelected();
        boolean fag = rbFag.isSelected();
        boolean karakter = rbKarakter.isSelected();
        
        // Sjekker om student er huket av ved trykk på reg
        if (student) {
            try {
                String fornavn = txt1.getText();
                String etternavn = txt2.getText();
                String epost = txt3.getText();
                String tilbakemelding = Kontroll.kontroll.nyStudent(fornavn, etternavn, epost);
                JOptionPane.showMessageDialog(null, tilbakemelding);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        
        // Legger inn FAG om rb er huket av
        if(fag) {
            try {
                int fagnr = Integer.parseInt(txt1.getText());
                String fagnavn = txt2.getText();
                String studiepoeng = txt3.getText();
                String tilbakemelding = Kontroll.kontroll.nyttFag(fagnr, fagnavn, studiepoeng);
                JOptionPane.showMessageDialog(null, tilbakemelding);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        
        if(karakter) {
            JOptionPane.showMessageDialog(null, "Du har valgt KARAKTER!");
        }
    }//GEN-LAST:event_btnRegActionPerformed

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1FocusGained

    private void jPanelOverviewFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanelOverviewFocusGained
    }//GEN-LAST:event_jPanelOverviewFocusGained

    private void jPanelOverviewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelOverviewMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelOverviewMouseClicked

    
    
    /*
    == OVERSIKT-siden
    ========================================================================================
    == Oversikt er en statisk side som ikke utfører handling, men kun viser frem 
    == informasjon. Derfor har vi lagt alt som skjer her ved instansiering av grensesnitt()
    ========================================================================================
    */
    private void jPanelOverviewComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelOverviewComponentShown
        // Se grensesnitt() for kode
    }//GEN-LAST:event_jPanelOverviewComponentShown
    
    
    /*
    == Innlasting av SØK-siden
    ==
    == Ved innlasting av søke-siden fyller vi inn comboboxen med studennr
    =======================================================================
    */
    private void jPanelSearchComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelSearchComponentShown
        // Fyller komboboksen med studenter
        
        // Henter ut alle studenter:
        try {
            ResultSet rs = Kontroll.kontroll.hentAlleStudenter();
            while (rs.next()) {
                cbStudentListe.addItem(rs.getString(1));
            }
            rs.close();
            // Her kan vi evt fjerne teksten i dropdownboksen
            //cbStudentListe.removeItemAt(0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jPanelSearchComponentShown

    
   
    /*
    == SØK-siden, KLIKK på Studentlisten (komboboks)
    =======================================================================================
    == Ved klikk på denne komboboksen ønsker vi å printe ut informasjon om denne studenten
    =======================================================================================
    */
    private void cbStudentListeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStudentListeActionPerformed
        try {
            String valgtStud, fagliste, snitt, ut;
            int studnr;
            fagliste = "";

            // Finn valgt STUDENT
            valgtStud = cbStudentListe.getSelectedItem().toString();
            studnr = Integer.parseInt(valgtStud);
            JOptionPane.showMessageDialog(null, valgtStud);

            // SNITTKARAKTER til student
            snitt = Kontroll.kontroll.getKaraktersnitt(studnr);

            // Finn FAG til student
            ResultSet rs = Kontroll.kontroll.getAlleFagTilStudent(studnr);
            while (rs.next()) {
                fagliste = rs.getString(2) + "\n";
            }
            rs.close();

            // Henter ut NAVN til studenten
            String navn;
            navn = Kontroll.kontroll.getNavn(studnr);

            // Nå har vi all info, og kan legge det ut på GUI
            ut = "<html>"+navn +"<br>"+"Karaktersnitt: "+snitt+"<br>"+"Oppmeldte fag:"+"<br>"+fagliste+"</html>";
            lblStudentInfo.setText(ut);

        } catch (SQLException ex) {
            System.out.println("Klarte ikke å hente ut karaktersnittet.");
            Logger.getLogger(Grensesnitt.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_cbStudentListeActionPerformed

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        // Søk etter student
        String input, nr, fnavn, enavn, epost, passord;
        String svar = "Resultat:"+"\n";
        input = txtSearch.getText();
        
        if(rbEnavn.isSelected()) { // Søker på etternavn
            try {
                ResultSet rs = Kontroll.kontroll.searchEtternavn(input);
            while (rs.next()) {
                nr = rs.getString(1);
                passord = rs.getString(1);
                fnavn = rs.getString(2);
                enavn = rs.getString(3);
                epost = rs.getString(4);
                svar += nr+","+passord+", "+fnavn+", "+enavn+", "+epost+"\n";
            }
            lblStudentInfo.setText(svar);
            rs.close();
            } catch (Exception e) {
            }
        } else { // Søker på epost
        }
    }//GEN-LAST:event_txtSearchKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Grensesnitt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Grensesnitt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Grensesnitt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Grensesnitt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
 
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Grensesnitt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableOversikt;
    private javax.swing.JToggleButton btnReg;
    private javax.swing.JComboBox cbFagnr;
    private javax.swing.JComboBox cbKarakter;
    private javax.swing.JComboBox cbStudentListe;
    private javax.swing.JComboBox cbStudentnr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanelAdd;
    private javax.swing.JPanel jPanelOverview;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lblAntStud;
    private javax.swing.JLabel lblFeilmelding;
    private javax.swing.JLabel lblStrykprosent;
    private javax.swing.JLabel lblStudentInfo;
    private javax.swing.JRadioButton rbEnavn;
    private javax.swing.JRadioButton rbEpost;
    private javax.swing.JRadioButton rbFag;
    private javax.swing.JRadioButton rbKarakter;
    private javax.swing.JRadioButton rbStudent;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
