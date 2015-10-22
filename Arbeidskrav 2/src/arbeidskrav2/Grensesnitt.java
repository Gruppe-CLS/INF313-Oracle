/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arbeidskrav2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_CANCEL_OPTION;
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
        jPanelOppmelding = new javax.swing.JPanel();
        lblStud = new javax.swing.JLabel();
        cbOppVelgStud = new javax.swing.JComboBox();
        lblVelgFag = new javax.swing.JLabel();
        cbOppVelgFag = new javax.swing.JComboBox();
        btnOppmelding = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lblOppHarKarakter = new javax.swing.JLabel();

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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Oversikt", jPanelOverview);

        jPanelAdd.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelAddComponentShown(evt);
            }
        });

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

        txt3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt3KeyTyped(evt);
            }
        });

        btnReg.setText("Registrer ny");
        btnReg.setEnabled(false);
        btnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegActionPerformed(evt);
            }
        });

        lbl4.setText("Karakter:");

        cbKarakter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "E", "F" }));
        cbKarakter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKarakterActionPerformed(evt);
            }
        });

        cbStudentnr.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alle studenter" }));
        cbStudentnr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStudentnrActionPerformed(evt);
            }
        });

        cbFagnr.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Velg student" }));
        cbFagnr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbFagnrMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelAddLayout = new javax.swing.GroupLayout(jPanelAdd);
        jPanelAdd.setLayout(jPanelAddLayout);
        jPanelAddLayout.setHorizontalGroup(
            jPanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddLayout.createSequentialGroup()
                .addContainerGap()
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
                            .addGroup(jPanelAddLayout.createSequentialGroup()
                                .addGroup(jPanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbStudentnr, 0, 237, Short.MAX_VALUE)
                                    .addComponent(cbFagnr, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(btnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(lblStudentInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Søk", jPanelSearch);

        jPanelOppmelding.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelOppmeldingComponentShown(evt);
            }
        });

        lblStud.setText("Velg student:");

        cbOppVelgStud.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Velg student" }));
        cbOppVelgStud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOppVelgStudActionPerformed(evt);
            }
        });

        lblVelgFag.setText("Velg fag:");

        cbOppVelgFag.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Velg fag" }));
        cbOppVelgFag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOppVelgFagActionPerformed(evt);
            }
        });

        btnOppmelding.setText("Meld opp");
        btnOppmelding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOppmeldingActionPerformed(evt);
            }
        });

        jLabel7.setText("Oppmelding til fag:");

        javax.swing.GroupLayout jPanelOppmeldingLayout = new javax.swing.GroupLayout(jPanelOppmelding);
        jPanelOppmelding.setLayout(jPanelOppmeldingLayout);
        jPanelOppmeldingLayout.setHorizontalGroup(
            jPanelOppmeldingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOppmeldingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOppmeldingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOppmelding)
                    .addComponent(jLabel7)
                    .addGroup(jPanelOppmeldingLayout.createSequentialGroup()
                        .addGroup(jPanelOppmeldingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStud)
                            .addComponent(lblVelgFag))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelOppmeldingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbOppVelgStud, 0, 215, Short.MAX_VALUE)
                            .addComponent(cbOppVelgFag, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblOppHarKarakter, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jPanelOppmeldingLayout.setVerticalGroup(
            jPanelOppmeldingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOppmeldingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelOppmeldingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStud)
                    .addComponent(cbOppVelgStud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelOppmeldingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVelgFag)
                    .addComponent(cbOppVelgFag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOppHarKarakter, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOppmelding)
                .addContainerGap(326, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Oppmelding", jPanelOppmelding);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
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
        txt3.setEnabled(true);
    }//GEN-LAST:event_rbFagActionPerformed

    
    
    /*
    == RADIOBUTTON - Karakter
    =====================================================================================
    == Setter opp riktig inputfelter ved trykk på karakter på "Legg til"-siden
    =====================================================================================
    */
    private void rbKarakterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbKarakterActionPerformed
        nullStillNyKarakter();
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
        txt3.setEnabled(true);
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
                int studiepoeng = Integer.parseInt(txt3.getText());
                String tilbakemelding = Kontroll.kontroll.nyttFag(fagnr, fagnavn, studiepoeng);
                JOptionPane.showMessageDialog(null, tilbakemelding);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
        
        if(karakter) {
            try {
                int snr, fagkode, aar, bedskjed;
                String fagnavn, studnr, dato, valgtKarakter, melding;
                
                // Henter ut studentnr og konverterer til int
                studnr = cbStudentnr.getSelectedItem().toString().substring(0, 6);
                snr = Integer.parseInt(studnr);
                
                // Henter ut fagnavn og henter fagkoden med getFagKode()
                fagnavn = cbFagnr.getSelectedItem().toString().trim();
                fagkode = Kontroll.kontroll.getFagKode(fagnavn);
                
                
                // Henter ut dato
                dato = txt3.getText();
                aar = Integer.parseInt(dato.substring(6, 10));
                if (Kontroll.kontroll.gyldigDato(dato) && dato.length() == 10 && aar > 2014) {

                    // Henter valgt karakter
                    valgtKarakter = cbKarakter.getSelectedItem().toString().trim();

                    // Oppretter melding, og spør bruker om alt ser ok ut før vi legger inn ny karakter
                    melding = "Du er i ferd med å legge inn følgende: "
                            + "\n" + "\n"
                            + "Dato: " + dato + "\n"
                            + "Fag: " + fagnavn + "\n"
                            + "StudenID: " + Integer.toString(snr) + "\n"
                            + "Karakter: " + valgtKarakter + "\n" + "\n"
                            + "Sikker på at du vil legge inn dette?";

                    Object[] options = {"Ja", "Nei"};
                    bedskjed = JOptionPane.showOptionDialog(null, melding, "Legg til karakter", YES_NO_CANCEL_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);

                    // Om OK, så insert
                    if (bedskjed == JOptionPane.YES_OPTION) {
                        String svar = Kontroll.kontroll.nyKarakter(dato, fagkode, snr, valgtKarakter);
                        JOptionPane.showMessageDialog(null, svar);
                        nullStillNyKarakter();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, dato + " er ikke en gyldig dato.");
                    txt3.requestFocus();
                    txt3.selectAll();
                }
                
                
                
            } catch (Exception ex) {
            }
            
            
        }
    }//GEN-LAST:event_btnRegActionPerformed

    public void nullStillNyKarakter() {
        lbl1.setText("Studentnr:");
        lbl2.setText("Fag:");
        lbl3.setText("Dato: (dd.mm.åååå)");
        lbl4.setText("Karakter:");
        txt1.setVisible(false);
        txt2.setVisible(false);
        cbKarakter.setVisible(true);
        cbFagnr.setVisible(true);
        cbStudentnr.setVisible(true);
        
        // disable av utfyllinger
        cbFagnr.setEnabled(false);
        txt3.setEnabled(false);
        cbKarakter.setEnabled(false);
    }
    
    
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
        // Setter etternavn som satt
        rbEnavn.setSelected(true);
        txtSearch.requestFocus();
        
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
        String fagliste = "";
        String svar = "<html><h3>Resultat:</h3>" + "<br>";
        input = txtSearch.getText();

        if (rbEnavn.isSelected() && input.length() >= 3) { // Søker på etternavn
            try {
                ResultSet rs = Kontroll.kontroll.searchEtternavn(input);
                while (rs.next()) {
                    nr = rs.getString(1);
                    passord = rs.getString(2);
                    fnavn = rs.getString(3);
                    enavn = rs.getString(4);
                    epost = rs.getString(5);
                    svar += "<h4>Navn: " + fnavn + " " + enavn + "</h4>" 
                            + "<b>Studentnr:</b> " + nr + "<br>" 
                            + "<b>Epost:</b> " + epost + "<br><"
                            + "<b>Passord:</b> " + passord + "<br>"
                            + "<b>Fag:</b><br>";
                    
                    // Finner alle fag til hver enkelt student
                    int studnr = Integer.parseInt(nr);
                    ResultSet res = Kontroll.kontroll.getAlleFullforte(studnr);
                    while (res.next()) {
                        fagliste += res.getString(2);
                        String fag = res.getString(2);
                        int fagkode = Kontroll.kontroll.getFagKode(fag);
                        String karakter = Kontroll.kontroll.getKarakterFag(studnr, fagkode);
                        fagliste+= " med karakteren: " + karakter + "<br>";
                    }
                    res.close();
                    svar+=fagliste+"<br>";
                }
                lblStudentInfo.setText(svar + "</htm>");
                rs.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if(rbEnavn.isSelected() && input.length() <= 3) {
            // Ved få søkebokstaver nullstiller vi resultatet
            lblStudentInfo.setText("");
        }
    }//GEN-LAST:event_txtSearchKeyTyped

    public void klarKarakter() {
        txt3.setText("");
        cbFagnr.setEnabled(false);
        txt3.setEnabled(false);
        cbKarakter.setEnabled(false);
        btnReg.setEnabled(false);
    }
    /*
    == INNLASTNING av "Legg til"-siden
    =======================================================================================
    == Vi fyller her komboboksen med alle studenter
    =======================================================================================
    */
    @SuppressWarnings("unchecked")
    private void jPanelAddComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAddComponentShown
        // Lister ut alle studenter i dropdown-boksen
        if(rbKarakter.isSelected()) {
            klarKarakter();
        }
        try {
            ResultSet rs = Kontroll.kontroll.hentAlleStudenter();
            String space = " - ";
            while (rs.next()) {
                cbStudentnr.addItem(rs.getString(1) + " - " + rs.getString(3) + " " + rs.getString(4));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jPanelAddComponentShown
    
    
    
    @SuppressWarnings("unchecked")
    private void cbStudentnrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStudentnrActionPerformed
        // TODO add your handling code here:
        String antS;
        int snr;
        int ant = 0;
        String studnr = cbStudentnr.getSelectedItem().toString().substring(0, 6);
        snr = Integer.parseInt(studnr);
        try {
            cbFagnr.setEnabled(true);
            cbFagnr.removeAllItems();
            cbFagnr.addItem("Velg fag");
            ResultSet rs = Kontroll.kontroll.getAlleFagTilStudent(snr);
            while (rs.next()) {
                cbFagnr.addItem(rs.getString(2)+"\n");
                ant += 1;
            }
            rs.close();
            if (cbFagnr.getItemCount() < 2) { 
                cbFagnr.removeAllItems();
                cbFagnr.addItem("Studenten har ingen fag"); 
            } else {
                antS = Integer.toString(ant);
                String tekst = "Studenten har " + antS + " aktive fag:";
                cbFagnr.removeItemAt(0); // Tømmer linje 0
                cbFagnr.insertItemAt(tekst, 0); // Setter inn ny info om valgt student
                cbFagnr.setSelectedIndex(0); // Setter fokus til ny linje
            }
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_cbStudentnrActionPerformed

    private void txt3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt3KeyTyped
        if(cbFagnr.getSelectedIndex() != 0 && txt3.getText().length() == 9 && rbKarakter.isSelected()) {
            // Om vi har valgt et fag, lengden er 10 i dato OG karakter er det som skal legges inn, så:
            cbKarakter.setEnabled(true);
        } else {
            cbKarakter.setEnabled(false);
        }
        
    }//GEN-LAST:event_txt3KeyTyped

    private void cbFagnrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbFagnrMouseClicked
        // TODO add your handling code here:
        txt3.setEnabled(true);
    }//GEN-LAST:event_cbFagnrMouseClicked

    private void cbKarakterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKarakterActionPerformed
        // TODO add your handling code here:
        btnReg.setEnabled(true);
    }//GEN-LAST:event_cbKarakterActionPerformed

    
    /*
    == INNLASTNING av "OPPMELDING"
    =======================================================================================
    == Viser kun frem studenter
    =======================================================================================
    */
    private void jPanelOppmeldingComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelOppmeldingComponentShown
       nullstill();
       
       // Lister ut alle studenter i Combobox
       try {
            ResultSet rs = Kontroll.kontroll.hentAlleStudenter();
            while (rs.next()) {
                cbOppVelgStud.addItem(rs.getString(1) + " - " + rs.getString(3) + " " + rs.getString(4));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jPanelOppmeldingComponentShown

    private void cbOppVelgStudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOppVelgStudActionPerformed
        // TODO add your handling code here:
        nullstill();
        String studnr;
        int snr;
        // Henter ut valgt student
        if (cbOppVelgStud.getSelectedIndex() > 0) {
        studnr = cbOppVelgStud.getSelectedItem().toString().substring(0, 6);
        snr = Integer.parseInt(studnr);

        // Vis Combobox med alle fag
        cbOppVelgFag.setVisible(true);
        lblVelgFag.setVisible(true);
        
            try {
                ResultSet rs = Kontroll.kontroll.getAlleFag(snr);
                while (rs.next()) {
                    cbOppVelgFag.addItem(rs.getString(1) + " - " + rs.getString(2));
                }
                rs.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_cbOppVelgStudActionPerformed

    private void cbOppVelgFagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOppVelgFagActionPerformed
        // Viser knappen
        btnOppmelding.setVisible(true);
        
        String studnr, karakter, fag, fagnr;
        int snr, fagkode;
        
        try {
            studnr = cbOppVelgStud.getSelectedItem().toString().substring(0, 6);
            snr = Integer.parseInt(studnr);
            fag = cbOppVelgFag.getSelectedItem().toString();
            fagnr = fag.substring(0, fag.indexOf(" "));
            fagkode = Integer.parseInt(fagnr);
            karakter = Kontroll.kontroll.sjekkKarakter(snr, fagkode);
            if(karakter != null) {
                lblOppHarKarakter.setVisible(true);
                lblOppHarKarakter.setText("Studenten har følgende karakter i dette faget: " + karakter);
            } else {
                lblOppHarKarakter.setVisible(true);
                lblOppHarKarakter.setText("Ingen karakter.");
            }
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cbOppVelgFagActionPerformed

    private void btnOppmeldingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOppmeldingActionPerformed
        String studnr, karakter, fag, fagnr, melding, dato, studenten;
        int snr, fagkode, varsling;
        
        // Sjekk om student har karakter
        try {
            studenten = cbOppVelgStud.getSelectedItem().toString();
            studnr = cbOppVelgStud.getSelectedItem().toString().substring(0, 6);
            snr = Integer.parseInt(studnr.trim());
            fag = cbOppVelgFag.getSelectedItem().toString();
            fagnr = fag.substring(0, fag.indexOf(" "));
            fagkode = Integer.parseInt(fagnr.trim());
            karakter = Kontroll.kontroll.sjekkKarakter(snr, fagkode);
            if(karakter != null) {
                // Om studenten har en karakter fra før varsler vi om det før insert
                melding = "Studenten er allerede oppført med en karakteren \"" + karakter + "\" i faget \"" + fag + "\"."+"\n"
                        + "Ved ny oppmelding mister studenten den nåværende karakteren!"
                        + "\n" + "\n"
                        + "Ny oppmelding:" + "\n"
                        + "Fag: " + fag + "\n"
                        + "Student: " + studenten + "\n"
                        + "\n"
                        + "Nåværende karakter blir overskrevet, er du sikker?";

                Object[] options = {"Ja", "Nei"};
                varsling = JOptionPane.showOptionDialog(null, melding, "Oppmelding av student", YES_NO_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
  
                // Om du svarer ja, insert. Om nei, ingenting
                if(varsling == JOptionPane.YES_OPTION) {
                    String svar = Kontroll.kontroll.oppdaterKarakter(fagkode, snr);
                    JOptionPane.showMessageDialog(null, svar);
                    nullstill();
                }
                
            } else { // Om det ikke finnes noen karakter fra før, blir man meldt opp her
                // Bedskjed om hva som meldes opp
                melding = "Ny oppmelding:" + "\n"
                        + "Fag: " + fag + "\n"
                        + "Student: " + studenten + "\n"
                        + "\n"
                        + "Vil du melde opp denne studenten?";
                
                Object[] options = {"Ja", "Nei"};
                varsling = JOptionPane.showOptionDialog(null, melding, "Oppmelding av student", YES_NO_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
                
                // Om du bekrefter
                if(varsling == JOptionPane.YES_OPTION) {
                    String svar = Kontroll.kontroll.nyOppmelding(fagkode, snr);
                    JOptionPane.showMessageDialog(null, svar);
                    nullstill();
                }
            }
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnOppmeldingActionPerformed

    public void nullstill() {
        cbOppVelgFag.removeAllItems();
        cbOppVelgFag.setVisible(false);
        lblVelgFag.setVisible(false);
        btnOppmelding.setVisible(false);
        lblOppHarKarakter.setVisible(false);
    }
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
    private javax.swing.JButton btnOppmelding;
    private javax.swing.JToggleButton btnReg;
    private javax.swing.JComboBox cbFagnr;
    private javax.swing.JComboBox cbKarakter;
    private javax.swing.JComboBox cbOppVelgFag;
    private javax.swing.JComboBox cbOppVelgStud;
    private javax.swing.JComboBox cbStudentListe;
    private javax.swing.JComboBox cbStudentnr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanelAdd;
    private javax.swing.JPanel jPanelOppmelding;
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
    private javax.swing.JLabel lblOppHarKarakter;
    private javax.swing.JLabel lblStrykprosent;
    private javax.swing.JLabel lblStud;
    private javax.swing.JLabel lblStudentInfo;
    private javax.swing.JLabel lblVelgFag;
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
