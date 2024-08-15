/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPerawatan.java
 *
 * Created on May 23, 2010, 6:36:30 PM
 */

package simrskhanza;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.Jurnal;

/**
 *
 * @author dosen
 */
public final class DlgPeriksaDarah extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private PreparedStatement pstindakan,ps;
    private ResultSet rstindakan,rs;
    private int i=0,jmlparsial=0;
    private String aktifkanparsial="no",status="";
       

    /** Creates new form DlgPerawatan
     * @param parent
     * @param modal */
    public DlgPeriksaDarah(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(885,674);

        Object[] row={
            "No Order","No Rawat","RM","Nama Pasien","Hasil","Golongan Darah","Rhesus","Kode Petugas","Nama Petugas","Tangal","Jam"};
        tabMode=new DefaultTableModel(null,row){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                    boolean a = false;
                    if ((colIndex==0)||(colIndex==2)||(colIndex==4)||(colIndex==5)) {
                        a=true;
                    }
                    return a;
             }
             Class[] types = new Class[] {
                 java.lang.Object.class,
                 java.lang.Object.class,
                 java.lang.Object.class,
                 java.lang.Object.class,
                 java.lang.Object.class,
                 java.lang.Object.class,
                 java.lang.Object.class,
                 java.lang.Object.class,
                 java.lang.Object.class,
                 java.lang.Object.class,
                 java.lang.Object.class,
                 java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }              
        };
        
        tbHasilPemeriksaan.setModel(tabMode);
        tbHasilPemeriksaan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbHasilPemeriksaan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbHasilPemeriksaan.getColumnModel().getColumn(i);
            switch (i) {
                case 0:
                    column.setPreferredWidth(100);
                    break;
                case 1:
                    column.setPreferredWidth(100);
                    break;
                case 3:
                    column.setPreferredWidth(150);
                    break;
                default:
//                    column.setMinWidth(0);
//                    column.setMaxWidth(0);
                    column.setPreferredWidth(80);
                    break;
            }
        }
        
        tbHasilPemeriksaan.setDefaultRenderer(Object.class, new WarnaTable());
                        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        KdPtg.setDocument(new batasInput((byte)20).getKata(KdPtg));
        
        ChkJln.setSelected(true);
        jam();        
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(akses.getform().equals("DlgPeriksaLaboratorium")){
                    if(petugas.getTable().getSelectedRow()!= -1){                   
                        KdPtg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        NmPtg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                    } 
                    KdPtg.requestFocus();
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        Alamat = new widget.TextBox();
        Popup = new javax.swing.JPopupMenu();
        ppHapus = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel13 = new widget.Label();
        LCount = new widget.Label();
        jLabel10 = new widget.Label();
        BtnKeluar = new widget.Button();
        FormInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        PanelInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TxNoorder = new widget.TextBox();
        jLabel11 = new widget.Label();
        jLabel12 = new widget.Label();
        KdPtg = new widget.TextBox();
        btnPetugas = new widget.Button();
        NmPtg = new widget.TextBox();
        Tanggal = new widget.Tanggal();
        CmbJam = new widget.ComboBox();
        CmbMenit = new widget.ComboBox();
        CmbDetik = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        jLabel16 = new widget.Label();
        jLabel15 = new widget.Label();
        TUmur = new widget.TextBox();
        TxHasil = new widget.ComboBox();
        TPasien = new widget.TextBox();
        jLabel4 = new widget.Label();
        jLabel5 = new widget.Label();
        TxGD = new widget.TextBox();
        jLabel6 = new widget.Label();
        TxRhesus = new widget.TextBox();
        PanelCariUtama = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbHasilPemeriksaan = new widget.Table();

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

        Popup.setName("Popup"); // NOI18N

        ppHapus.setBackground(new java.awt.Color(255, 255, 254));
        ppHapus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppHapus.setForeground(new java.awt.Color(50, 50, 50));
        ppHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppHapus.setText("Hapus");
        ppHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppHapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppHapus.setName("ppHapus"); // NOI18N
        ppHapus.setPreferredSize(new java.awt.Dimension(200, 25));
        ppHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppHapusActionPerformed(evt);
            }
        });
        Popup.add(ppHapus);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Input Data Hasil Periksa Darah ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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

        jLabel13.setText("Record :");
        jLabel13.setName("jLabel13"); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass8.add(jLabel13);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(53, 23));
        panelGlass8.add(LCount);

        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(85, 30));
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
        FormInput.setPreferredSize(new java.awt.Dimension(560, 200));
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
        PanelInput.setPreferredSize(new java.awt.Dimension(560, 168));
        PanelInput.setLayout(null);

        jLabel3.setText("No.Order :");
        jLabel3.setName("jLabel3"); // NOI18N
        PanelInput.add(jLabel3);
        jLabel3.setBounds(0, 50, 92, 23);

        TNoRw.setEditable(false);
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        PanelInput.add(TNoRw);
        TNoRw.setBounds(95, 12, 128, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        PanelInput.add(TNoRM);
        TNoRM.setBounds(225, 12, 108, 23);

        TxNoorder.setEditable(false);
        TxNoorder.setHighlighter(null);
        TxNoorder.setName("TxNoorder"); // NOI18N
        PanelInput.add(TxNoorder);
        TxNoorder.setBounds(100, 50, 379, 23);

        jLabel11.setText("Hasil :");
        jLabel11.setName("jLabel11"); // NOI18N
        PanelInput.add(jLabel11);
        jLabel11.setBounds(610, 110, 50, 23);

        jLabel12.setText("Petugas :");
        jLabel12.setName("jLabel12"); // NOI18N
        PanelInput.add(jLabel12);
        jLabel12.setBounds(30, 80, 60, 23);

        KdPtg.setEditable(false);
        KdPtg.setName("KdPtg"); // NOI18N
        KdPtg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPtgKeyPressed(evt);
            }
        });
        PanelInput.add(KdPtg);
        KdPtg.setBounds(100, 80, 80, 23);

        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('2');
        btnPetugas.setToolTipText("Alt+2");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        PanelInput.add(btnPetugas);
        btnPetugas.setBounds(430, 80, 28, 23);

        NmPtg.setEditable(false);
        NmPtg.setName("NmPtg"); // NOI18N
        PanelInput.add(NmPtg);
        NmPtg.setBounds(180, 80, 249, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-07-2024" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        PanelInput.add(Tanggal);
        Tanggal.setBounds(100, 110, 90, 23);

        CmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam.setName("CmbJam"); // NOI18N
        PanelInput.add(CmbJam);
        CmbJam.setBounds(230, 110, 70, 23);

        CmbMenit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit.setName("CmbMenit"); // NOI18N
        PanelInput.add(CmbMenit);
        CmbMenit.setBounds(300, 110, 60, 23);

        CmbDetik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik.setName("CmbDetik"); // NOI18N
        PanelInput.add(CmbDetik);
        CmbDetik.setBounds(360, 110, 62, 23);

        ChkJln.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkJln.setSelected(true);
        ChkJln.setBorderPainted(true);
        ChkJln.setBorderPaintedFlat(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        PanelInput.add(ChkJln);
        ChkJln.setBounds(430, 110, 23, 23);

        jLabel16.setText("Jam :");
        jLabel16.setName("jLabel16"); // NOI18N
        PanelInput.add(jLabel16);
        jLabel16.setBounds(200, 110, 30, 23);

        jLabel15.setText("Tanggal :");
        jLabel15.setName("jLabel15"); // NOI18N
        PanelInput.add(jLabel15);
        jLabel15.setBounds(30, 110, 60, 23);

        TUmur.setEditable(false);
        TUmur.setHighlighter(null);
        TUmur.setName("TUmur"); // NOI18N
        PanelInput.add(TUmur);
        TUmur.setBounds(716, 12, 110, 23);

        TxHasil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "COCOK", "TIDAK COCOK" }));
        TxHasil.setName("TxHasil"); // NOI18N
        TxHasil.setPreferredSize(new java.awt.Dimension(100, 23));
        TxHasil.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TxHasilItemStateChanged(evt);
            }
        });
        PanelInput.add(TxHasil);
        TxHasil.setBounds(670, 110, 160, 20);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        PanelInput.add(TPasien);
        TPasien.setBounds(335, 12, 379, 23);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        PanelInput.add(jLabel4);
        jLabel4.setBounds(0, 12, 92, 23);

        jLabel5.setText("Golongn Darah :");
        jLabel5.setName("jLabel5"); // NOI18N
        PanelInput.add(jLabel5);
        jLabel5.setBounds(580, 50, 78, 23);

        TxGD.setHighlighter(null);
        TxGD.setName("TxGD"); // NOI18N
        PanelInput.add(TxGD);
        TxGD.setBounds(670, 50, 160, 23);

        jLabel6.setText("Rhesus :");
        jLabel6.setName("jLabel6"); // NOI18N
        PanelInput.add(jLabel6);
        jLabel6.setBounds(610, 80, 50, 23);

        TxRhesus.setHighlighter(null);
        TxRhesus.setName("TxRhesus"); // NOI18N
        PanelInput.add(TxRhesus);
        TxRhesus.setBounds(670, 80, 160, 23);

        FormInput.add(PanelInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        PanelCariUtama.setName("PanelCariUtama"); // NOI18N
        PanelCariUtama.setOpaque(false);
        PanelCariUtama.setPreferredSize(new java.awt.Dimension(100, 143));
        PanelCariUtama.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(Popup);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbHasilPemeriksaan.setComponentPopupMenu(Popup);
        tbHasilPemeriksaan.setName("tbHasilPemeriksaan"); // NOI18N
        Scroll.setViewportView(tbHasilPemeriksaan);

        PanelCariUtama.add(Scroll, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelCariUtama, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().equals("")||TNoRM.getText().equals("")||TxNoorder.getText().equals("")){
            Valid.textKosong(TNoRw,"Pasien");
        }else if(KdPtg.getText().equals("")||NmPtg.getText().equals("")){
            Valid.textKosong(KdPtg,"Petugas");
        }else{
            if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi.\nSilahkan hubungi bagian kasir/keuangan ..!!");
            }else{
                simpan();              
            }                 
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
       
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dokter.dispose();
        petugas.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

private void KdPtgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPtgKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            NmPtg.setText(petugas.tampil3(KdPtg.getText()));
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPetugasActionPerformed(null);
        }
}//GEN-LAST:event_KdPtgKeyPressed

private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        akses.setform("DlgPeriksaLaboratorium");
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
}//GEN-LAST:event_btnPetugasActionPerformed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void ppHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppHapusActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!");
            TNoRw.requestFocus();                    
        }else {
            if(akses.getutd_pemisahan_darah()==false){
                JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh yang bersangkutan..!!");
            }else if(!akses.getkode().equals(tbHasilPemeriksaan.getValueAt(tbHasilPemeriksaan.getSelectedRow(),7))){
                JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh yang bersangkutan..!!");
            }else{
               Sequel.queryu("delete from hasil_pemeriksaan_darah where noorder='"+tbHasilPemeriksaan.getValueAt(tbHasilPemeriksaan.getSelectedRow(),0).toString()+
                "' and tgl_periksa='"+tbHasilPemeriksaan.getValueAt(tbHasilPemeriksaan.getSelectedRow(),9)+
                "' and jam_periksa='"+tbHasilPemeriksaan.getValueAt(tbHasilPemeriksaan.getSelectedRow(),10)+
                "' and petugas_periksa='"+tbHasilPemeriksaan.getValueAt(tbHasilPemeriksaan.getSelectedRow(),7)+"' ");
               tampil();

            }
        }
    }//GEN-LAST:event_ppHapusActionPerformed

    private void TxHasilItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TxHasilItemStateChanged
     
    }//GEN-LAST:event_TxHasilItemStateChanged

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void PenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenjabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenjabKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPeriksaDarah dialog = new DlgPeriksaDarah(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJln;
    private widget.ComboBox CmbDetik;
    private widget.ComboBox CmbJam;
    private widget.ComboBox CmbMenit;
    private javax.swing.JPanel FormInput;
    private widget.TextBox Jk;
    private widget.TextBox KdPtg;
    private widget.Label LCount;
    private widget.TextBox NmPtg;
    private javax.swing.JPanel PanelCariUtama;
    private widget.PanelBiasa PanelInput;
    private widget.TextBox Penjab;
    private javax.swing.JPopupMenu Popup;
    private widget.ScrollPane Scroll;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TUmur;
    private widget.Tanggal Tanggal;
    private widget.TextBox TxGD;
    private widget.ComboBox TxHasil;
    private widget.TextBox TxNoorder;
    private widget.TextBox TxRhesus;
    private widget.TextBox Umur;
    private widget.Button btnPetugas;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.panelisi panelGlass8;
    private javax.swing.JMenuItem ppHapus;
    private widget.Table tbHasilPemeriksaan;
    // End of variables declaration//GEN-END:variables

    private void tampil() { 
        Valid.tabelKosong(tabMode);   
        try{  
            ps=koneksi.prepareStatement("SELECT\n" +
            "	hasil_pemeriksaan_darah.noorder, \n" +
            "	reg_periksa.no_rawat, \n" +
            "	pasien.no_rkm_medis, \n" +
            "	pasien.nm_pasien, \n" +
            "	hasil_pemeriksaan_darah.hasil, \n" +
            "	hasil_pemeriksaan_darah.golongan_darah, \n" +
            "	hasil_pemeriksaan_darah.rhesus, \n" +
            "	hasil_pemeriksaan_darah.tgl_periksa, \n" +
            "	hasil_pemeriksaan_darah.petugas_periksa, \n" +
            "	pegawai.nama, \n" +
            "	hasil_pemeriksaan_darah.jam_periksa \n" +
            "FROM\n" +
            "	hasil_pemeriksaan_darah\n" +
            "	INNER JOIN\n" +
            "	permintaan_darah\n" +
            "	ON \n" +
            "		hasil_pemeriksaan_darah.noorder = permintaan_darah.noorder\n" +
            "	INNER JOIN\n" +
            "	reg_periksa\n" +
            "	ON \n" +
            "		permintaan_darah.no_rawat = reg_periksa.no_rawat\n" +
            "	INNER JOIN\n" +
            "	pasien\n" +
            "	ON \n" +
            "		reg_periksa.no_rkm_medis = pasien.no_rkm_medis\n" +
            "	INNER JOIN\n" +
            "	pegawai\n" +
            "	ON \n" +
            "		hasil_pemeriksaan_darah.petugas_periksa = pegawai.nik \n" +
            "WHERE\n" +
            "	permintaan_darah.noorder like ? order by hasil_pemeriksaan_darah.tgl_periksa,hasil_pemeriksaan_darah.jam_periksa "); 
            try {
                ps.setString(1,TxNoorder.getText());
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[] {
                        rs.getString("noorder"),
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("hasil"),
                        rs.getString("golongan_darah"),
                        rs.getString("rhesus"),
                        rs.getString("petugas_periksa"),
                        rs.getString("nama"),
                        rs.getString("tgl_periksa"),
                        rs.getString("jam_periksa")
                    });
                } 
            } catch (Exception e) {
                System.out.println("Notif : "+e);
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
        LCount.setText(""+tabMode.getRowCount());
    }
      
    public void emptTeks() {
        KdPtg.setText("");
        NmPtg.setText("");
        TxHasil.setSelectedIndex(0);
    }
   
    private void isPsien(){
        Sequel.mengedit("pasien","no_rkm_medis=?","umur=CONCAT(CONCAT(CONCAT(TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()), ' Th '),CONCAT(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12), ' Bl ')),CONCAT(TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()), ' Hr'))",1,new String[]{TNoRM.getText()});
        try {
            pstindakan=koneksi.prepareStatement(
                "select reg_periksa.no_rkm_medis,reg_periksa.kd_pj,reg_periksa.kd_dokter,dokter.nm_dokter,pasien.nm_pasien,pasien.jk,pasien.umur,"+
                "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat "+
                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis inner join kelurahan on pasien.kd_kel=kelurahan.kd_kel "+
                "inner join kecamatan on pasien.kd_kec=kecamatan.kd_kec inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab "+
                "inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter where no_rawat=?");
            try {
                pstindakan.setString(1,TNoRw.getText());
                rstindakan=pstindakan.executeQuery();
                while(rstindakan.next()){
                    TNoRM.setText(rstindakan.getString("no_rkm_medis"));
                    Penjab.setText(rstindakan.getString("kd_pj"));
                    TPasien.setText(rstindakan.getString("nm_pasien"));
                    Jk.setText(rstindakan.getString("jk"));
                    Umur.setText(rstindakan.getString("umur"));
                    Alamat.setText(rstindakan.getString("alamat"));
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rstindakan!=null){
                    rstindakan.close();
                }
                if(pstindakan!=null){
                    pstindakan.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
        TUmur.setText(Umur.getText());
    }
    
    public void isReset(){
        Valid.tabelKosong(tabMode);
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

    public void setNoRm(String norwt,String posisi, String noorder) {
        TxNoorder.setText(noorder);
        TNoRw.setText(norwt);
        this.status=posisi;
        
        isPsien();
        isReset();
        tampil();
    }
    
    public void isCek(){
        if(akses.getjml2()>=1){
            KdPtg.setText(akses.getkode());
            NmPtg.setText(petugas.tampil3(KdPtg.getText()));
        }else{
            KdPtg.setText("");
            NmPtg.setText("");
        }
        btnPetugas.setEnabled(akses.getutd_pemisahan_darah());
        BtnSimpan.setEnabled(akses.getutd_pemisahan_darah());
        BtnPrint.setEnabled(akses.getutd_pemisahan_darah());
        ppHapus.setEnabled(akses.getutd_pemisahan_darah());
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            FormInput.setPreferredSize(new Dimension(WIDTH,269));
            PanelInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            FormInput.setPreferredSize(new Dimension(WIDTH,20));
            PanelInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    private void simpan() {
        if(Sequel.menyimpantf("hasil_pemeriksaan_darah","?,?,?,?,?,?,?","Data Hasil Pemeriksaan Darah",7,new String[]{
                 TxNoorder.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+""),CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem(),
                 TxGD.getText().toUpperCase(),TxRhesus.getText(),TxHasil.getSelectedItem().toString(),KdPtg.getText()
             })==true){
                 JOptionPane.showMessageDialog(null,"Proses simpan selesai...!");
                 tabMode.addRow(new Object[] {
                    TxNoorder.getText(),
                    TNoRw.getText(),
                    TNoRM.getText(),
                    TPasien.getText(),
                    TxHasil.getSelectedItem().toString(),
                    TxGD.getText().toUpperCase(),
                    TxRhesus.getText(),
                    KdPtg.getText(),
                    NmPtg.getText(),
                    Valid.SetTgl(Tanggal.getSelectedItem()+""),
                    CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()
                });
                 emptTeks();
        }
    }

}
