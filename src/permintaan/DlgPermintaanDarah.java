/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPerawatan.java
 *
 * Created on May 23, 2010, 6:36:30 PM
 */

package permintaan;

import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPetugas;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public final class DlgPermintaanDarah extends javax.swing.JDialog {
    private DefaultTableModel tabMode;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private PreparedStatement ps,pspemeriksaan;
    private ResultSet rs;
    private int jml=0,i=0,jmlparsial=0;
    private String kamar,namakamar,status="",kelas="",
            norawatibu="",aktifkanparsial="no",finger="";

    /** Creates new form DlgPerawatan
     * @param parent
     * @param modal */
    public DlgPermintaanDarah(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        Object[] row={"P","No.Kantung","Komponen","G.D.","Rhesus"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbDarah.setModel(tabMode);        
        
        tbDarah.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDarah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for(i = 0; i < 5; i++) {
            TableColumn column = tbDarah.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(60);
            }else if(i==1){
                column.setPreferredWidth(200);
            }else if(i==2){
                column.setPreferredWidth(200);
            }else if(i==3){
                column.setPreferredWidth(50);
            }else if(i==4){
                column.setPreferredWidth(50);
            }else{
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDarah.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        KodePerujuk.setDocument(new batasInput((byte)20).getKata(KodePerujuk));
        TNoPermintaan.setDocument(new batasInput((byte)15).getKata(TNoPermintaan));
        DiagnosisKlinis.setDocument(new batasInput((int)100).getKata(DiagnosisKlinis));
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCariPeriksa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCariPeriksa.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCariPeriksa.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCariPeriksa.getText().length()>2){
                        tampil();
                    }
                }
            });
        }  
        
        ChkJln.setSelected(true);
        jam();
        
        ChkJln1.setSelected(true);
        jam1();
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    KodePerujuk.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    NmPerujuk.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                    KodePerujuk.requestFocus();
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        try {
            aktifkanparsial=koneksiDB.AKTIFKANBILLINGPARSIAL();
        } catch (Exception ex) {            
            aktifkanparsial="no";
        }
        autoNomor();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Penjab = new widget.TextBox();
        Jk = new widget.TextBox();
        Umur = new widget.TextBox();
        Alamat = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel10 = new widget.Label();
        BtnKeluar = new widget.Button();
        FormInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        PanelInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel9 = new widget.Label();
        CmbJam = new widget.ComboBox();
        CmbMenit = new widget.ComboBox();
        CmbDetik = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        KodePerujuk = new widget.TextBox();
        NmPerujuk = new widget.TextBox();
        btnDokter = new widget.Button();
        jLabel15 = new widget.Label();
        jLabel4 = new widget.Label();
        TNoPermintaan = new widget.TextBox();
        DiagnosisKlinis = new widget.TextBox();
        jLabel6 = new widget.Label();
        TxAlasanTransfusi = new widget.TextBox();
        TxRTS = new widget.ComboBox();
        label12 = new widget.Label();
        label13 = new widget.Label();
        TxPMT = new widget.ComboBox();
        label14 = new widget.Label();
        TxPPUC = new widget.ComboBox();
        label15 = new widget.Label();
        TxPCGD = new widget.ComboBox();
        TxKetPCGD = new widget.TextBox();
        TxKetPMT = new widget.TextBox();
        TxKetPPUC = new widget.TextBox();
        jLabel7 = new widget.Label();
        jLabel11 = new widget.Label();
        TxKadarHb = new widget.TextBox();
        jLabel8 = new widget.Label();
        TxJmlhKehamilan = new widget.TextBox();
        TxAbortus = new widget.ComboBox();
        label16 = new widget.Label();
        label17 = new widget.Label();
        TxHDN = new widget.ComboBox();
        Tanggal = new widget.Tanggal();
        jLabel16 = new widget.Label();
        ChkJln1 = new widget.CekBox();
        CmbDetik1 = new widget.ComboBox();
        CmbMenit1 = new widget.ComboBox();
        CmbJam1 = new widget.ComboBox();
        Tanggal1 = new widget.Tanggal();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        scrollPane1 = new widget.ScrollPane();
        tbDarah = new widget.Table();
        panelisi5 = new widget.panelisi();
        label10 = new widget.Label();
        TCariPeriksa = new widget.TextBox();
        btnCariPeriksa = new widget.Button();
        BtnAllPeriksa = new widget.Button();

        Penjab.setEditable(false);
        Penjab.setFocusTraversalPolicyProvider(true);
        Penjab.setName("Penjab"); // NOI18N
        Penjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenjabKeyPressed(evt);
            }
        });

        Jk.setEditable(false);
        Jk.setFocusTraversalPolicyProvider(true);
        Jk.setName("Jk"); // NOI18N

        Umur.setEditable(false);
        Umur.setHighlighter(null);
        Umur.setName("Umur"); // NOI18N

        Alamat.setEditable(false);
        Alamat.setHighlighter(null);
        Alamat.setName("Alamat"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Input Data Permintaan Darah ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(103, 30));
        panelGlass8.add(jLabel10);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setOpaque(false);
        FormInput.setPreferredSize(new java.awt.Dimension(560, 450));
        FormInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setSelected(true);
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        FormInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setPreferredSize(new java.awt.Dimension(600, 300));
        PanelInput.setLayout(null);

        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        PanelInput.add(jLabel3);
        jLabel3.setBounds(0, 12, 92, 23);

        TNoRw.setEditable(false);
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        PanelInput.add(TNoRw);
        TNoRw.setBounds(95, 12, 128, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        PanelInput.add(TNoRM);
        TNoRM.setBounds(225, 12, 105, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        PanelInput.add(TPasien);
        TPasien.setBounds(332, 12, 300, 23);

        jLabel9.setText("Dokter Perujuk :");
        jLabel9.setName("jLabel9"); // NOI18N
        PanelInput.add(jLabel9);
        jLabel9.setBounds(0, 42, 92, 23);

        CmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam.setName("CmbJam"); // NOI18N
        PanelInput.add(CmbJam);
        CmbJam.setBounds(190, 70, 50, 23);

        CmbMenit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit.setName("CmbMenit"); // NOI18N
        PanelInput.add(CmbMenit);
        CmbMenit.setBounds(240, 70, 50, 23);

        CmbDetik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik.setName("CmbDetik"); // NOI18N
        PanelInput.add(CmbDetik);
        CmbDetik.setBounds(290, 70, 50, 23);

        ChkJln.setBorder(null);
        ChkJln.setSelected(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        ChkJln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJlnActionPerformed(evt);
            }
        });
        PanelInput.add(ChkJln);
        ChkJln.setBounds(340, 70, 17, 20);

        KodePerujuk.setName("KodePerujuk"); // NOI18N
        KodePerujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodePerujukKeyPressed(evt);
            }
        });
        PanelInput.add(KodePerujuk);
        KodePerujuk.setBounds(95, 42, 128, 23);

        NmPerujuk.setEditable(false);
        NmPerujuk.setHighlighter(null);
        NmPerujuk.setName("NmPerujuk"); // NOI18N
        PanelInput.add(NmPerujuk);
        NmPerujuk.setBounds(225, 42, 377, 23);

        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter.setMnemonic('4');
        btnDokter.setToolTipText("ALt+4");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        PanelInput.add(btnDokter);
        btnDokter.setBounds(604, 42, 28, 23);

        jLabel15.setText("Tgl.Diperlukan :");
        jLabel15.setName("jLabel15"); // NOI18N
        PanelInput.add(jLabel15);
        jLabel15.setBounds(0, 100, 92, 23);

        jLabel4.setText("No.Permintaan :");
        jLabel4.setName("jLabel4"); // NOI18N
        PanelInput.add(jLabel4);
        jLabel4.setBounds(409, 72, 90, 23);

        TNoPermintaan.setHighlighter(null);
        TNoPermintaan.setName("TNoPermintaan"); // NOI18N
        TNoPermintaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoPermintaanKeyPressed(evt);
            }
        });
        PanelInput.add(TNoPermintaan);
        TNoPermintaan.setBounds(502, 72, 130, 23);

        DiagnosisKlinis.setHighlighter(null);
        DiagnosisKlinis.setName("DiagnosisKlinis"); // NOI18N
        DiagnosisKlinis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosisKlinisKeyPressed(evt);
            }
        });
        PanelInput.add(DiagnosisKlinis);
        DiagnosisKlinis.setBounds(200, 130, 420, 23);

        jLabel6.setText("Alasan Transfusi Sebelumnya :");
        jLabel6.setName("jLabel6"); // NOI18N
        PanelInput.add(jLabel6);
        jLabel6.setBounds(-10, 160, 209, 23);

        TxAlasanTransfusi.setHighlighter(null);
        TxAlasanTransfusi.setName("TxAlasanTransfusi"); // NOI18N
        TxAlasanTransfusi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxAlasanTransfusiActionPerformed(evt);
            }
        });
        TxAlasanTransfusi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxAlasanTransfusiKeyPressed(evt);
            }
        });
        PanelInput.add(TxAlasanTransfusi);
        TxAlasanTransfusi.setBounds(200, 160, 210, 23);

        TxRTS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        TxRTS.setName("TxRTS"); // NOI18N
        TxRTS.setPreferredSize(new java.awt.Dimension(100, 23));
        TxRTS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TxRTSItemStateChanged(evt);
            }
        });
        PanelInput.add(TxRTS);
        TxRTS.setBounds(200, 220, 90, 20);

        label12.setText("Riwayat Transfusi Sebelumnya :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(50, 23));
        PanelInput.add(label12);
        label12.setBounds(30, 220, 170, 20);

        label13.setText("Pernah Mengalami Reaksi Transfusi :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(50, 23));
        PanelInput.add(label13);
        label13.setBounds(20, 250, 180, 20);

        TxPMT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        TxPMT.setName("TxPMT"); // NOI18N
        TxPMT.setPreferredSize(new java.awt.Dimension(100, 23));
        TxPMT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TxPMTItemStateChanged(evt);
            }
        });
        PanelInput.add(TxPMT);
        TxPMT.setBounds(200, 250, 90, 20);

        label14.setText("Pernah Periksa Uji Coombs :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(50, 23));
        PanelInput.add(label14);
        label14.setBounds(30, 280, 170, 20);

        TxPPUC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        TxPPUC.setName("TxPPUC"); // NOI18N
        TxPPUC.setPreferredSize(new java.awt.Dimension(100, 23));
        TxPPUC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TxPPUCItemStateChanged(evt);
            }
        });
        PanelInput.add(TxPPUC);
        TxPPUC.setBounds(200, 280, 90, 20);

        label15.setText("Pernah Cek Golongan Darah :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(50, 23));
        PanelInput.add(label15);
        label15.setBounds(30, 310, 170, 20);

        TxPCGD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        TxPCGD.setName("TxPCGD"); // NOI18N
        TxPCGD.setPreferredSize(new java.awt.Dimension(100, 23));
        TxPCGD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TxPCGDItemStateChanged(evt);
            }
        });
        PanelInput.add(TxPCGD);
        TxPCGD.setBounds(200, 310, 90, 20);

        TxKetPCGD.setHighlighter(null);
        TxKetPCGD.setName("TxKetPCGD"); // NOI18N
        TxKetPCGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxKetPCGDActionPerformed(evt);
            }
        });
        TxKetPCGD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxKetPCGDKeyPressed(evt);
            }
        });
        PanelInput.add(TxKetPCGD);
        TxKetPCGD.setBounds(300, 310, 320, 23);

        TxKetPMT.setHighlighter(null);
        TxKetPMT.setName("TxKetPMT"); // NOI18N
        TxKetPMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxKetPMTActionPerformed(evt);
            }
        });
        TxKetPMT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxKetPMTKeyPressed(evt);
            }
        });
        PanelInput.add(TxKetPMT);
        TxKetPMT.setBounds(300, 250, 320, 23);

        TxKetPPUC.setHighlighter(null);
        TxKetPPUC.setName("TxKetPPUC"); // NOI18N
        TxKetPPUC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxKetPPUCActionPerformed(evt);
            }
        });
        TxKetPPUC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxKetPPUCKeyPressed(evt);
            }
        });
        PanelInput.add(TxKetPPUC);
        TxKetPPUC.setBounds(300, 280, 320, 23);

        jLabel7.setText("Indikasi Pemeriksaan / Diagnosis Klinis :");
        jLabel7.setName("jLabel7"); // NOI18N
        PanelInput.add(jLabel7);
        jLabel7.setBounds(-10, 130, 209, 23);

        jLabel11.setText("Kadar Hb :");
        jLabel11.setName("jLabel11"); // NOI18N
        PanelInput.add(jLabel11);
        jLabel11.setBounds(-10, 190, 209, 23);

        TxKadarHb.setHighlighter(null);
        TxKadarHb.setName("TxKadarHb"); // NOI18N
        TxKadarHb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxKadarHbActionPerformed(evt);
            }
        });
        TxKadarHb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxKadarHbKeyPressed(evt);
            }
        });
        PanelInput.add(TxKadarHb);
        TxKadarHb.setBounds(200, 190, 210, 23);

        jLabel8.setText("Jumlah kehamilan Sebelumnya :");
        jLabel8.setName("jLabel8"); // NOI18N
        PanelInput.add(jLabel8);
        jLabel8.setBounds(390, 160, 209, 23);

        TxJmlhKehamilan.setHighlighter(null);
        TxJmlhKehamilan.setName("TxJmlhKehamilan"); // NOI18N
        TxJmlhKehamilan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxJmlhKehamilanActionPerformed(evt);
            }
        });
        TxJmlhKehamilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxJmlhKehamilanKeyPressed(evt);
            }
        });
        PanelInput.add(TxJmlhKehamilan);
        TxJmlhKehamilan.setBounds(600, 160, 210, 23);

        TxAbortus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        TxAbortus.setName("TxAbortus"); // NOI18N
        TxAbortus.setPreferredSize(new java.awt.Dimension(100, 23));
        TxAbortus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TxAbortusItemStateChanged(evt);
            }
        });
        PanelInput.add(TxAbortus);
        TxAbortus.setBounds(600, 190, 90, 20);

        label16.setText("Pernah Abortus :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(50, 23));
        PanelInput.add(label16);
        label16.setBounds(430, 190, 170, 20);

        label17.setText("Riwayat Penyakit Hemolitik pada bayi (HDN) :");
        label17.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(50, 23));
        PanelInput.add(label17);
        label17.setBounds(340, 220, 260, 20);

        TxHDN.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));
        TxHDN.setName("TxHDN"); // NOI18N
        TxHDN.setPreferredSize(new java.awt.Dimension(100, 23));
        TxHDN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TxHDNItemStateChanged(evt);
            }
        });
        PanelInput.add(TxHDN);
        TxHDN.setBounds(600, 220, 90, 20);

        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-06-2024" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TanggalItemStateChanged(evt);
            }
        });
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        PanelInput.add(Tanggal);
        Tanggal.setBounds(100, 70, 90, 23);

        jLabel16.setText("Tgl.Periksa :");
        jLabel16.setName("jLabel16"); // NOI18N
        PanelInput.add(jLabel16);
        jLabel16.setBounds(0, 72, 92, 23);

        ChkJln1.setBorder(null);
        ChkJln1.setSelected(true);
        ChkJln1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln1.setName("ChkJln1"); // NOI18N
        ChkJln1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJln1ActionPerformed(evt);
            }
        });
        PanelInput.add(ChkJln1);
        ChkJln1.setBounds(340, 100, 17, 20);

        CmbDetik1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik1.setName("CmbDetik1"); // NOI18N
        PanelInput.add(CmbDetik1);
        CmbDetik1.setBounds(290, 100, 50, 23);

        CmbMenit1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit1.setName("CmbMenit1"); // NOI18N
        PanelInput.add(CmbMenit1);
        CmbMenit1.setBounds(240, 100, 50, 23);

        CmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam1.setName("CmbJam1"); // NOI18N
        PanelInput.add(CmbJam1);
        CmbJam1.setBounds(190, 100, 50, 23);

        Tanggal1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-06-2024" }));
        Tanggal1.setDisplayFormat("dd-MM-yyyy");
        Tanggal1.setName("Tanggal1"); // NOI18N
        Tanggal1.setOpaque(false);
        Tanggal1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Tanggal1ItemStateChanged(evt);
            }
        });
        Tanggal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tanggal1KeyPressed(evt);
            }
        });
        PanelInput.add(Tanggal1);
        Tanggal1.setBounds(100, 100, 90, 23);

        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("<html>\n    <ul><b>PERHATIAN</b>\n        <li>Setiap permintaan darah harap disertakan contoh darah eku minimal 2 cc.</li>\n        <li>Apabila ditemukan ketidakcocokan daah, segera laporka ke UTDRS dan darah dikembalikan beserta laporan reaksi transfusi.</li>\n    </ul>\n</html>");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setName("jLabel1"); // NOI18N
        PanelInput.add(jLabel1);
        jLabel1.setBounds(0, 340, 490, 80);

        FormInput.add(PanelInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 102));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbDarah.setAutoCreateRowSorter(true);
        tbDarah.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbDarah.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDarah.setName("tbDarah"); // NOI18N
        tbDarah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDarahMouseClicked(evt);
            }
        });
        tbDarah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDarahKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbDarah);

        jPanel3.add(scrollPane1, java.awt.BorderLayout.CENTER);

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label10.setText("Key Word :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi5.add(label10);

        TCariPeriksa.setToolTipText("Alt+C");
        TCariPeriksa.setName("TCariPeriksa"); // NOI18N
        TCariPeriksa.setPreferredSize(new java.awt.Dimension(490, 23));
        TCariPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCariPeriksaActionPerformed(evt);
            }
        });
        TCariPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariPeriksaKeyPressed(evt);
            }
        });
        panelisi5.add(TCariPeriksa);

        btnCariPeriksa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        btnCariPeriksa.setMnemonic('1');
        btnCariPeriksa.setToolTipText("Alt+1");
        btnCariPeriksa.setName("btnCariPeriksa"); // NOI18N
        btnCariPeriksa.setPreferredSize(new java.awt.Dimension(28, 23));
        btnCariPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPeriksaActionPerformed(evt);
            }
        });
        btnCariPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCariPeriksaKeyPressed(evt);
            }
        });
        panelisi5.add(btnCariPeriksa);

        BtnAllPeriksa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllPeriksa.setMnemonic('2');
        BtnAllPeriksa.setToolTipText("Alt+2");
        BtnAllPeriksa.setName("BtnAllPeriksa"); // NOI18N
        BtnAllPeriksa.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllPeriksaActionPerformed(evt);
            }
        });
        BtnAllPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllPeriksaKeyPressed(evt);
            }
        });
        panelisi5.add(BtnAllPeriksa);

        jPanel3.add(panelisi5, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dokter.dispose();
        petugas.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{}
}//GEN-LAST:event_BtnKeluarKeyPressed

private void ChkJlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJlnActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_ChkJlnActionPerformed

    private void PenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenjabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenjabKeyPressed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt, DiagnosisKlinis,BtnBatal);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        jml=0;
        for(i=0;i<tbDarah.getRowCount();i++){
            if(tbDarah.getValueAt(i,0).toString().equals("true")){
                jml++;
            }
        }
        if(TNoRw.getText().equals("")||TNoRM.getText().equals("")||TPasien.getText().equals("")){
            Valid.textKosong(TNoRw,"Pasien");
        }else if(KodePerujuk.getText().equals("")||NmPerujuk.getText().equals("")){
            Valid.textKosong(KodePerujuk,"Dokter Perujuk");
        }else if(DiagnosisKlinis.getText().equals("")){
            Valid.textKosong(DiagnosisKlinis,"Diagnosis Klinis");
        }else if(tabMode.getRowCount()==0){
            Valid.textKosong(TCariPeriksa,"Data Permintaan");
        }else if(jml==0){
            Valid.textKosong(TCariPeriksa,"Data Permintaan");
        }else{
            jmlparsial=0;
            if(aktifkanparsial.equals("yes")){
                jmlparsial=Sequel.cariInteger("select count(set_input_parsial.kd_pj) from set_input_parsial where set_input_parsial.kd_pj=?",Penjab.getText());
            }
            if(jmlparsial>0){    
                simpan(); 
            }else{
                if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                    JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi, data tidak boleh dihapus.\nSilahkan hubungi bagian kasir/keuangan ..!!");
                    TCariPeriksa.requestFocus();
                }else{
                    simpan();              
                }
            }   
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnBatalActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnSimpan,BtnPrint);
        }
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void KodePerujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodePerujukKeyPressed
        
    }//GEN-LAST:event_KodePerujukKeyPressed

    private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokterActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        jml=0;
        for(i=0;i<tbDarah.getRowCount();i++){
            if(tbDarah.getValueAt(i,0).toString().equals("true")){
                jml++;
            }
        }
        if(TNoRw.getText().equals("")||TNoRM.getText().equals("")||TPasien.getText().equals("")){
            Valid.textKosong(TNoRw,"Pasien");
        }else if(KodePerujuk.getText().equals("")||NmPerujuk.getText().equals("")){
            Valid.textKosong(KodePerujuk,"Dokter Pengirim");
        }else if(tabMode.getRowCount()==0){
            Valid.textKosong(TCariPeriksa,"Data Permintaan");
        }else if(jml==0){
            Valid.textKosong(TCariPeriksa,"Data Permintaan");
        }else{
            
            Sequel.queryu("delete from temporary_permintaan_radiologi");
            for(i=0;i<tbDarah.getRowCount();i++){ 
                if(tbDarah.getValueAt(i,0).toString().equals("true")){
                    Sequel.menyimpan("temporary_permintaan_radiologi","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?",38,new String[]{
                        "0",tbDarah.getValueAt(i,1).toString(),
                        tbDarah.getValueAt(i,2).toString(),"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""
                    });
                }                
            }
            
            Map<String, Object> param = new HashMap<>();
            param.put("noperiksa",TNoPermintaan.getText());
            param.put("norm",TNoRM.getText());
            param.put("pekerjaan",Sequel.cariIsi("select pasien.pekerjaan from pasien where pasien.no_rkm_medis=?",TNoRM.getText()));
            param.put("noktp",Sequel.cariIsi("select pasien.no_ktp from pasien where pasien.no_rkm_medis=?",TNoRM.getText()));
            param.put("namapasien",TPasien.getText());
            param.put("jkel",Jk.getText());
            param.put("umur",Umur.getText());
            param.put("lahir",Sequel.cariIsi("select DATE_FORMAT(pasien.tgl_lahir,'%d-%m-%Y') from pasien where pasien.no_rkm_medis=? ",TNoRM.getText()));
            param.put("pengirim",NmPerujuk.getText());
            param.put("tanggal",Tanggal.getSelectedItem());
            param.put("alamat",Alamat.getText());
            param.put("kamar",kamar);
            param.put("namakamar",namakamar);
            param.put("diagnosa",DiagnosisKlinis.getText());
            param.put("jam",CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem());
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",KodePerujuk.getText());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+NmPerujuk.getText()+"\nID "+(finger.equals("")?KodePerujuk.getText():finger)+"\n"+Tanggal.getSelectedItem()); 
            
            Valid.MyReport("rptPermintaanRadiologi.jasper","report","::[ Permintaan Radiologi ]::",param);            
            ChkJln.setSelected(false);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void TNoPermintaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoPermintaanKeyPressed
        
    }//GEN-LAST:event_TNoPermintaanKeyPressed

    private void DiagnosisKlinisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosisKlinisKeyPressed
        
    }//GEN-LAST:event_DiagnosisKlinisKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void BtnAllPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllPeriksaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllPeriksaActionPerformed(null);
        }else{
            Valid.pindah(evt, btnCariPeriksa, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllPeriksaKeyPressed

    private void BtnAllPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllPeriksaActionPerformed
        TCariPeriksa.setText("");
        tampil();
    }//GEN-LAST:event_BtnAllPeriksaActionPerformed

    private void btnCariPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCariPeriksaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
        }else{
            Valid.pindah(evt, TCariPeriksa, BtnAllPeriksa);
        }
    }//GEN-LAST:event_btnCariPeriksaKeyPressed

    private void btnCariPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPeriksaActionPerformed
        tampil();
    }//GEN-LAST:event_btnCariPeriksaActionPerformed

    private void TCariPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariPeriksaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            btnCariPeriksaActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            btnCariPeriksa.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            TNoPermintaan.requestFocus();
        }
    }//GEN-LAST:event_TCariPeriksaKeyPressed

    private void TCariPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCariPeriksaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCariPeriksaActionPerformed

    private void TxAlasanTransfusiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxAlasanTransfusiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxAlasanTransfusiKeyPressed

    private void TxRTSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TxRTSItemStateChanged
        
    }//GEN-LAST:event_TxRTSItemStateChanged

    private void TxPMTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TxPMTItemStateChanged
        if(TxPMT.getSelectedItem().equals("Ya")){
            TxKetPMT.setVisible(true);
        }else{
            TxKetPMT.setVisible(false);
        }
    }//GEN-LAST:event_TxPMTItemStateChanged

    private void TxPPUCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TxPPUCItemStateChanged
        if(TxPPUC.getSelectedItem().equals("Ya")){
            TxKetPPUC.setVisible(true);
        }else{
            TxKetPPUC.setVisible(false);
        }
    }//GEN-LAST:event_TxPPUCItemStateChanged

    private void TxPCGDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TxPCGDItemStateChanged
        if(TxPCGD.getSelectedItem().equals("Ya")){
            TxKetPCGD.setVisible(true);
        }else{
            TxKetPCGD.setVisible(false);
        }
    }//GEN-LAST:event_TxPCGDItemStateChanged

    private void TxAlasanTransfusiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxAlasanTransfusiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxAlasanTransfusiActionPerformed

    private void TxKetPCGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxKetPCGDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxKetPCGDActionPerformed

    private void TxKetPCGDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxKetPCGDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxKetPCGDKeyPressed

    private void TxKetPMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxKetPMTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxKetPMTActionPerformed

    private void TxKetPMTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxKetPMTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxKetPMTKeyPressed

    private void TxKetPPUCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxKetPPUCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxKetPPUCActionPerformed

    private void TxKetPPUCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxKetPPUCKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxKetPPUCKeyPressed

    private void TxKadarHbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxKadarHbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxKadarHbActionPerformed

    private void TxKadarHbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxKadarHbKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxKadarHbKeyPressed

    private void TxJmlhKehamilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxJmlhKehamilanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxJmlhKehamilanActionPerformed

    private void TxJmlhKehamilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxJmlhKehamilanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxJmlhKehamilanKeyPressed

    private void TxAbortusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TxAbortusItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TxAbortusItemStateChanged

    private void TxHDNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TxHDNItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TxHDNItemStateChanged

    private void TanggalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TanggalItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalItemStateChanged

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalKeyPressed

    private void tbDarahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDarahMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
//                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDarahMouseClicked

    private void tbDarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDarahKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
//                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDarahKeyPressed

    private void Tanggal1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Tanggal1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Tanggal1ItemStateChanged

    private void Tanggal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tanggal1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tanggal1KeyPressed

    private void ChkJln1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJln1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkJln1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPermintaanDarah dialog = new DlgPermintaanDarah(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.TextBox Alamat;
    private widget.Button BtnAllPeriksa;
    private widget.Button BtnBatal;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJln;
    private widget.CekBox ChkJln1;
    private widget.ComboBox CmbDetik;
    private widget.ComboBox CmbDetik1;
    private widget.ComboBox CmbJam;
    private widget.ComboBox CmbJam1;
    private widget.ComboBox CmbMenit;
    private widget.ComboBox CmbMenit1;
    private widget.TextBox DiagnosisKlinis;
    private javax.swing.JPanel FormInput;
    private widget.TextBox Jk;
    private widget.TextBox KodePerujuk;
    private widget.TextBox NmPerujuk;
    private widget.PanelBiasa PanelInput;
    private widget.TextBox Penjab;
    private widget.TextBox TCariPeriksa;
    private widget.TextBox TNoPermintaan;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal Tanggal;
    private widget.Tanggal Tanggal1;
    private widget.ComboBox TxAbortus;
    private widget.TextBox TxAlasanTransfusi;
    private widget.ComboBox TxHDN;
    private widget.TextBox TxJmlhKehamilan;
    private widget.TextBox TxKadarHb;
    private widget.TextBox TxKetPCGD;
    private widget.TextBox TxKetPMT;
    private widget.TextBox TxKetPPUC;
    private widget.ComboBox TxPCGD;
    private widget.ComboBox TxPMT;
    private widget.ComboBox TxPPUC;
    private widget.ComboBox TxRTS;
    private widget.TextBox Umur;
    private widget.Button btnCariPeriksa;
    private widget.Button btnDokter;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JLabel jLabel1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private widget.Label label10;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi5;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbDarah;
    // End of variables declaration//GEN-END:variables
    
    
    private void tampil() {         
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement(
                "select utd_stok_darah.no_kantong,utd_komponen_darah.nama as darah,"+
                "utd_stok_darah.golongan_darah,utd_stok_darah.resus,"+
                "utd_stok_darah.tanggal_aftap,utd_stok_darah.tanggal_kadaluarsa,"+
                "utd_stok_darah.asal_darah,utd_stok_darah.status,"+
                "utd_komponen_darah.jasa_sarana,utd_komponen_darah.paket_bhp,"+
                "utd_komponen_darah.kso,utd_komponen_darah.manajemen,"+
                "utd_komponen_darah.total,utd_komponen_darah.pembatalan,utd_stok_darah.kode_komponen "+
                "from utd_komponen_darah inner join utd_stok_darah "+
                "on utd_stok_darah.kode_komponen=utd_komponen_darah.kode "+
                "where utd_stok_darah.status='Ada' and utd_stok_darah.no_kantong like ? or "+
                "utd_stok_darah.status='Ada' and utd_komponen_darah.nama like ? or "+
                "utd_stok_darah.status='Ada' and utd_stok_darah.resus like ? or "+
                "utd_stok_darah.status='Ada' and utd_stok_darah.asal_darah like ? or "+
                "utd_stok_darah.status='Ada' and utd_stok_darah.status like ? "+
                "order by utd_stok_darah.tanggal_kadaluarsa");
                        
            try {
                ps.setString(1,"%"+TCariPeriksa.getText().trim()+"%");
                ps.setString(2,"%"+TCariPeriksa.getText().trim()+"%");
                ps.setString(3,"%"+TCariPeriksa.getText().trim()+"%");
                ps.setString(4,"%"+TCariPeriksa.getText().trim()+"%");
                ps.setString(5,"%"+TCariPeriksa.getText().trim()+"%");
                
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        false,rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getString(4)
                    });
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
        
    public void isReset(){
        jml=tbDarah.getRowCount();
        for(i=0;i<jml;i++){ 
            tbDarah.setValueAt(false,i,0);
        }
        Valid.tabelKosong(tabMode);
        tampil();
    }
    
    public void emptTeks() {
        TCariPeriksa.setText("");
        TNoPermintaan.requestFocus();
        autoNomor();
        
        TxAlasanTransfusi.setText("");
        TxJmlhKehamilan.setText("");
        TxKadarHb.setText("");
        
        TxPCGD.setSelectedItem("Ya");
        TxHDN.setSelectedItem("Ya");
        TxPMT.setSelectedItem("Ya");
        TxPPUC.setSelectedItem("Ya");
        TxRTS.setSelectedItem("Ya");
        TxAbortus.setSelectedItem("Ya");
        
        TxKetPCGD.setVisible(true);
        TxKetPMT.setVisible(true);
        TxKetPPUC.setVisible(true);
    }
    
    private void isRawat(){
        if(status.equals("Ranap")){
            norawatibu=Sequel.cariIsi("select ranap_gabung.no_rawat from ranap_gabung where ranap_gabung.no_rawat2=?",TNoRw.getText());
            if(!norawatibu.equals("")){
                kamar=Sequel.cariIsi("select ifnull(kamar_inap.kd_kamar,'') from kamar_inap where kamar_inap.no_rawat=? order by kamar_inap.tgl_masuk desc limit 1",norawatibu);
                kelas=Sequel.cariIsi(
                    "select kamar.kelas from kamar inner join kamar_inap "+
                    "on kamar.kd_kamar=kamar_inap.kd_kamar where kamar_inap.no_rawat=? "+
                    "and kamar_inap.stts_pulang='-' order by STR_TO_DATE(concat(kamar_inap.tgl_masuk,' ',kamar_inap.jam_masuk),'%Y-%m-%d %H:%i:%s') desc limit 1",norawatibu);
            }else{
                kamar=Sequel.cariIsi("select ifnull(kamar_inap.kd_kamar,'') from kamar_inap where kamar_inap.no_rawat=? order by kamar_inap.tgl_masuk desc limit 1",TNoRw.getText());
                kelas=Sequel.cariIsi(
                    "select kamar.kelas from kamar inner join kamar_inap "+
                    "on kamar.kd_kamar=kamar_inap.kd_kamar where kamar_inap.no_rawat=? "+
                    "and kamar_inap.stts_pulang='-' order by STR_TO_DATE(concat(kamar_inap.tgl_masuk,' ',kamar_inap.jam_masuk),'%Y-%m-%d %H:%i:%s') desc limit 1",TNoRw.getText());
            } 
            namakamar=kamar+", "+Sequel.cariIsi("select bangsal.nm_bangsal from bangsal inner join kamar on bangsal.kd_bangsal=kamar.kd_bangsal "+
                    " where kamar.kd_kamar=? ",kamar);            
            kamar="Kamar";
        }else if(status.equals("Ralan")){
            kelas="Rawat Jalan";
            kamar="Poli";
            namakamar=Sequel.cariIsi("select poliklinik.nm_poli from poliklinik inner join reg_periksa on poliklinik.kd_poli=reg_periksa.kd_poli "+
                    "where reg_periksa.no_rawat=?",TNoRw.getText());
        }
    }

    private void isPsien(){
        try {
            pspemeriksaan=koneksi.prepareStatement(
                "select reg_periksa.no_rkm_medis,reg_periksa.kd_pj,reg_periksa.kd_dokter,dokter.nm_dokter,pasien.nm_pasien,pasien.jk,pasien.umur,"+
                "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat "+
                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel "+
                "inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab "+
                "inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter where no_rawat=?");
            try {
                pspemeriksaan.setString(1,TNoRw.getText());
                rs=pspemeriksaan.executeQuery();
                while(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    Penjab.setText(rs.getString("kd_pj"));
                    KodePerujuk.setText(rs.getString("kd_dokter"));
                    NmPerujuk.setText(rs.getString("nm_dokter"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    Jk.setText(rs.getString("jk"));
                    Umur.setText(rs.getString("umur"));
                    Alamat.setText(rs.getString("alamat"));
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(pspemeriksaan!=null){
                    pspemeriksaan.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }
    
    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            @Override
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                // Membuat Date
                //Date dt = new Date();
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkJln.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkJln.isSelected()==false){
                    nilai_jam =CmbJam.getSelectedIndex();
                    nilai_menit =CmbMenit.getSelectedIndex();
                    nilai_detik =CmbDetik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                CmbJam.setSelectedItem(jam);
                CmbMenit.setSelectedItem(menit);
                CmbDetik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }
    
    private void jam1(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            @Override
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                // Membuat Date
                //Date dt = new Date();
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkJln1.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkJln1.isSelected()==false){
                    nilai_jam =CmbJam1.getSelectedIndex();
                    nilai_menit =CmbMenit1.getSelectedIndex();
                    nilai_detik =CmbDetik1.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                CmbJam1.setSelectedItem(jam);
                CmbMenit1.setSelectedItem(menit);
                CmbDetik1.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    public void setNoRm(String norwt,String posisi){
        TNoRw.setText(norwt);
        this.status=posisi;        
        isRawat();
        isPsien();
        try {
            tampil();
        } catch (Exception e) {
        }
    }
    
    public void setNoRm(String norwt,String posisi,String kddokter,String nmdokter) {
        TNoRw.setText(norwt);
        this.status=posisi;
        isRawat();
        isPsien();
        try {
            tampil();
        } catch (Exception e) {
        }
        KodePerujuk.setText(kddokter);
        NmPerujuk.setText(nmdokter);
    }
    
    public void isCek(){        
        BtnSimpan.setEnabled(akses.getpermintaan_radiologi());
        BtnPrint.setEnabled(akses.getpermintaan_radiologi());
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            FormInput.setPreferredSize(new Dimension(WIDTH,189));
            PanelInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            FormInput.setPreferredSize(new Dimension(WIDTH,20));
            PanelInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void setDokterPerujuk(String kodeperujuk,String namaperujuk){
        KodePerujuk.setText(kodeperujuk);
        NmPerujuk.setText(namaperujuk);
    }

    private void autoNomor() {
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(permintaan_darah.noorder,4),signed)),0) from permintaan_darah where permintaan_darah.tgl_permintaan='"+Valid.SetTgl(Tanggal.getSelectedItem()+"")+"' ","PR"+Valid.SetTgl(Tanggal.getSelectedItem()+"").replaceAll("-",""),4,TNoPermintaan);           
    }

    private void simpan() {
        int reply = JOptionPane.showConfirmDialog(rootPane,"Eeiiiiiits, udah bener belum data yang mau disimpan..??","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            ChkJln.setSelected(false);
            try {                    
                koneksi.setAutoCommit(false);
                autoNomor();
                if(Sequel.menyimpantf2("permintaan_darah","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Permintaan",24,new String[]{
                        TNoPermintaan.getText(),TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+""),
                        CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem(), 
                        Valid.SetTgl(Tanggal1.getSelectedItem()+""),CmbJam1.getSelectedItem()+":"+CmbMenit1.getSelectedItem()+":"+CmbDetik1.getSelectedItem(),
                        KodePerujuk.getText(),status.replaceAll("R","r"),
                        DiagnosisKlinis.getText(),TxAlasanTransfusi.getText(),TxKadarHb.getText(),
                        TxRTS.getSelectedItem().toString(),TxJmlhKehamilan.getText(),TxAbortus.getSelectedItem().toString(),
                        TxHDN.getSelectedItem().toString(),TxPMT.getSelectedItem().toString(),TxKetPMT.getText(),
                        TxPPUC.getSelectedItem().toString(),TxKetPPUC.getText(),TxPCGD.getSelectedItem().toString(),TxKetPCGD.getText(),
                        kelas,kamar,namakamar
                    })==true){
                    for(i=0;i<tbDarah.getRowCount();i++){ 
                        if(tbDarah.getValueAt(i,0).toString().equals("true")){
                            Sequel.menyimpan2("permintaan_darah_detail","?,?,?","Pemeriksaan Darah",3,new String[]{
                                TNoPermintaan.getText(),tbDarah.getValueAt(i,1).toString(),"Belum"
                            });
                        }                        
                    } 
                    isReset();
                    emptTeks();
                }   

                koneksi.setAutoCommit(true);                    
                JOptionPane.showMessageDialog(null,"Proses simpan selesai...!");
            } catch (Exception e) {
                System.out.println(e);
            }    
            ChkJln.setSelected(true);            
        }
    }

}
