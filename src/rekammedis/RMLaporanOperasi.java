/*
 * By Mas Elkhanza
 */


package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPetugas;


/**
 *
 * @author perpustakaan
 */
public final class RMLaporanOperasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private DlgCariPetugas perawat1=new DlgCariPetugas(null,false);
    private DlgCariPetugas perawat2=new DlgCariPetugas(null,false);
    private DlgCariPetugas perawat3=new DlgCariPetugas(null,false);
    private MasterCariTemplateLaporanOperasi template=new MasterCariTemplateLaporanOperasi(null,false);
    private String finger="";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMLaporanOperasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        isCek2();
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","J.K.","Tgl.Lahir",
            "Tanggal Mulai","Jam Mulai","Jam Selesai","Lama Operasi","Kategori","Kode Dokter","Nama Dokter",
            "Perawat1","Nama Perawat1","Perawat2","nama Perawat2","Perawat3",
            "nama Perawat3","Diagnosa Pasca Operasi","Tindakan Operasi",
            "Laporan Operasi","Komplikasi","Ket Komplikasi","Jml. Pendarahan","Jml. Transfusi",
            "Jml. Jaringan","Ukuran","Fiksasi","Pabrik","Type",
            "Nomor P.","Status","Ruangan"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 33; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i>=1 && i<=10){
                column.setPreferredWidth(70);
            }else if(i==16 | i==17 | i==18 | i==26 ){
                column.setPreferredWidth(300);
            }else{
                column.setPreferredWidth(100);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                    KdDokter.requestFocus();
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
        
        perawat1.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(perawat1.getTable().getSelectedRow()!= -1){
                    kdperawat1.setText(perawat1.getTable().getValueAt(perawat1.getTable().getSelectedRow(),0).toString());
                    nmperawat1.setText(perawat1.getTable().getValueAt(perawat1.getTable().getSelectedRow(),1).toString());
                    kdperawat1.requestFocus();
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
        
        perawat2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(perawat2.getTable().getSelectedRow()!= -1){
                    kdperawat2.setText(perawat2.getTable().getValueAt(perawat2.getTable().getSelectedRow(),0).toString());
                    nmperawat2.setText(perawat2.getTable().getValueAt(perawat2.getTable().getSelectedRow(),1).toString());
                    kdperawat2.requestFocus();
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
        
        perawat3.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(perawat3.getTable().getSelectedRow()!= -1){
                    kdperawat3.setText(perawat3.getTable().getValueAt(perawat3.getTable().getSelectedRow(),0).toString());
                    nmperawat3.setText(perawat3.getTable().getValueAt(perawat3.getTable().getSelectedRow(),1).toString());
                    kdperawat3.requestFocus();
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
        
        template.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(template.getTable().getSelectedRow()!= -1){  
                    TxLaporanOperasi.setText(template.getTable().getValueAt(template.getTable().getSelectedRow(),6).toString());
                    TxLaporanOperasi.requestFocus();
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
        
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoadHTML = new widget.editorpane();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnCetakLaporan = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TxStatus = new widget.ComboBox();
        TNoRM = new widget.TextBox();
        label14 = new widget.Label();
        KdDokter = new widget.TextBox();
        NmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        label11 = new widget.Label();
        jLabel109 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        TxLaporanOperasi = new widget.TextArea();
        label27 = new widget.Label();
        btnPerawat1 = new widget.Button();
        nmperawat1 = new widget.TextBox();
        kdperawat1 = new widget.TextBox();
        label29 = new widget.Label();
        kdperawat2 = new widget.TextBox();
        nmperawat2 = new widget.TextBox();
        btnPerawat2 = new widget.Button();
        label30 = new widget.Label();
        kdperawat3 = new widget.TextBox();
        nmperawat3 = new widget.TextBox();
        btnPerawat3 = new widget.Button();
        jLabel5 = new widget.Label();
        Kategori = new widget.ComboBox();
        scrollPane3 = new widget.ScrollPane();
        TxTindakanOperasi = new widget.TextArea();
        jLabel31 = new widget.Label();
        label17 = new widget.Label();
        TxKomplikasi = new widget.ComboBox();
        TxKetKomplikasi = new widget.TextBox();
        jLabel15 = new widget.Label();
        TxJmlhPendarahan = new widget.TextBox();
        jLabel32 = new widget.Label();
        jLabel33 = new widget.Label();
        jLabel17 = new widget.Label();
        TxDiagnosaPascaBedah = new widget.TextBox();
        label13 = new widget.Label();
        TglOpMulai = new widget.Tanggal();
        jLabel18 = new widget.Label();
        TxJenisJaringan = new widget.TextBox();
        TxJmlhTransfusi = new widget.TextBox();
        TxUkuran = new widget.TextBox();
        jLabel34 = new widget.Label();
        TxFiksasi = new widget.TextBox();
        jLabel35 = new widget.Label();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel36 = new widget.Label();
        jLabel37 = new widget.Label();
        TxPabrikan = new widget.TextBox();
        jLabel38 = new widget.Label();
        TxType = new widget.TextBox();
        jLabel39 = new widget.Label();
        TxNoPendaftaran = new widget.TextBox();
        label12 = new widget.Label();
        CmbJam = new widget.ComboBox();
        CmbMenit = new widget.ComboBox();
        CmbDetik = new widget.ComboBox();
        CmbJam1 = new widget.ComboBox();
        CmbMenit1 = new widget.ComboBox();
        CmbDetik1 = new widget.ComboBox();
        label15 = new widget.Label();
        CmbJam2 = new widget.ComboBox();
        CmbMenit2 = new widget.ComboBox();
        CmbDetik2 = new widget.ComboBox();
        jLabel40 = new widget.Label();
        TxRuang = new widget.TextBox();
        jLabel41 = new widget.Label();
        btnTemplate = new widget.Button();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCetakLaporan.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakLaporan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakLaporan.setForeground(new java.awt.Color(50, 50, 50));
        MnCetakLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakLaporan.setText("Laporan Operasi");
        MnCetakLaporan.setName("MnCetakLaporan"); // NOI18N
        MnCetakLaporan.setPreferredSize(new java.awt.Dimension(220, 26));
        MnCetakLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakLaporanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakLaporan);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.BorderLayout(1, 1));

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Laporan Operasi dengan Lokal ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(467, 480));
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.setPreferredSize(new java.awt.Dimension(457, 480));
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(102, 480));
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1200));
        FormInput.setLayout(null);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 70, 750, 1);

        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 310, 770, 3);

        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(420, 480, 330, 3);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(10, 30, 131, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(240, 30, 300, 23);

        TxStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ralan", "Ranap" }));
        TxStatus.setName("TxStatus"); // NOI18N
        TxStatus.setPreferredSize(new java.awt.Dimension(100, 23));
        TxStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TxStatusItemStateChanged(evt);
            }
        });
        FormInput.add(TxStatus);
        TxStatus.setBounds(510, 770, 230, 20);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(140, 30, 100, 23);

        label14.setText("Dokter :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(360, 80, 50, 23);

        KdDokter.setEditable(false);
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdDokter);
        KdDokter.setBounds(410, 80, 100, 23);

        NmDokter.setEditable(false);
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(520, 80, 190, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        BtnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterKeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(710, 80, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(550, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(610, 10, 130, 23);

        Jk.setEditable(false);
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);
        Jk.setBounds(610, 40, 130, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(540, 40, 70, 23);

        label11.setText("Mulai :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(32, 110, 80, 23);

        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel109.setText("PROSEDUR OPERASI DAN RINCIAN TEMUAN");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(10, 320, 230, 23);

        scrollPane14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane14.setName("scrollPane14"); // NOI18N

        TxLaporanOperasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TxLaporanOperasi.setColumns(20);
        TxLaporanOperasi.setRows(5);
        TxLaporanOperasi.setName("TxLaporanOperasi"); // NOI18N
        TxLaporanOperasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxLaporanOperasiKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(TxLaporanOperasi);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(20, 340, 380, 500);

        label27.setText("Perawat 1 :");
        label27.setName("label27"); // NOI18N
        label27.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label27);
        label27.setBounds(351, 110, 60, 23);

        btnPerawat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPerawat1.setMnemonic('2');
        btnPerawat1.setToolTipText("Alt+2");
        btnPerawat1.setName("btnPerawat1"); // NOI18N
        btnPerawat1.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPerawat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerawat1ActionPerformed(evt);
            }
        });
        FormInput.add(btnPerawat1);
        btnPerawat1.setBounds(710, 110, 28, 23);

        nmperawat1.setEditable(false);
        nmperawat1.setName("nmperawat1"); // NOI18N
        nmperawat1.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(nmperawat1);
        nmperawat1.setBounds(520, 110, 190, 23);

        kdperawat1.setEditable(false);
        kdperawat1.setName("kdperawat1"); // NOI18N
        kdperawat1.setPreferredSize(new java.awt.Dimension(80, 23));
        kdperawat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdperawat1KeyPressed(evt);
            }
        });
        FormInput.add(kdperawat1);
        kdperawat1.setBounds(410, 110, 100, 23);

        label29.setText("Perawat 2 :");
        label29.setName("label29"); // NOI18N
        label29.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label29);
        label29.setBounds(351, 140, 60, 23);

        kdperawat2.setEditable(false);
        kdperawat2.setName("kdperawat2"); // NOI18N
        kdperawat2.setPreferredSize(new java.awt.Dimension(80, 23));
        kdperawat2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdperawat2KeyPressed(evt);
            }
        });
        FormInput.add(kdperawat2);
        kdperawat2.setBounds(410, 140, 100, 23);

        nmperawat2.setEditable(false);
        nmperawat2.setName("nmperawat2"); // NOI18N
        nmperawat2.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(nmperawat2);
        nmperawat2.setBounds(520, 140, 190, 23);

        btnPerawat2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPerawat2.setMnemonic('2');
        btnPerawat2.setToolTipText("Alt+2");
        btnPerawat2.setName("btnPerawat2"); // NOI18N
        btnPerawat2.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPerawat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerawat2ActionPerformed(evt);
            }
        });
        FormInput.add(btnPerawat2);
        btnPerawat2.setBounds(710, 140, 28, 23);

        label30.setText("Perawat 3 :");
        label30.setName("label30"); // NOI18N
        label30.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label30);
        label30.setBounds(351, 170, 60, 23);

        kdperawat3.setEditable(false);
        kdperawat3.setName("kdperawat3"); // NOI18N
        kdperawat3.setPreferredSize(new java.awt.Dimension(80, 23));
        kdperawat3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdperawat3KeyPressed(evt);
            }
        });
        FormInput.add(kdperawat3);
        kdperawat3.setBounds(410, 170, 100, 23);

        nmperawat3.setEditable(false);
        nmperawat3.setName("nmperawat3"); // NOI18N
        nmperawat3.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(nmperawat3);
        nmperawat3.setBounds(520, 170, 190, 23);

        btnPerawat3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPerawat3.setMnemonic('2');
        btnPerawat3.setToolTipText("Alt+2");
        btnPerawat3.setName("btnPerawat3"); // NOI18N
        btnPerawat3.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPerawat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerawat3ActionPerformed(evt);
            }
        });
        FormInput.add(btnPerawat3);
        btnPerawat3.setBounds(710, 170, 28, 23);

        jLabel5.setText("Kategori :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(450, 360, 60, 23);

        Kategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Khusus", "Besar", "Sedang", "Kecil", "Elektive", "Emergency", "Besar Emergency", "Khusus Emergency", "Besar Elektive", "Khusus Elektive" }));
        Kategori.setName("Kategori"); // NOI18N
        Kategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KategoriKeyPressed(evt);
            }
        });
        FormInput.add(Kategori);
        Kategori.setBounds(520, 360, 220, 23);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        TxTindakanOperasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TxTindakanOperasi.setColumns(20);
        TxTindakanOperasi.setRows(5);
        TxTindakanOperasi.setName("TxTindakanOperasi"); // NOI18N
        TxTindakanOperasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxTindakanOperasiKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(TxTindakanOperasi);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(150, 230, 560, 60);

        jLabel31.setText("Tindakan Operasi :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(50, 230, 90, 23);

        label17.setText("Komplikasi :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(50, 23));
        FormInput.add(label17);
        label17.setBounds(410, 390, 100, 20);

        TxKomplikasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        TxKomplikasi.setName("TxKomplikasi"); // NOI18N
        TxKomplikasi.setPreferredSize(new java.awt.Dimension(100, 23));
        TxKomplikasi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TxKomplikasiItemStateChanged(evt);
            }
        });
        FormInput.add(TxKomplikasi);
        TxKomplikasi.setBounds(510, 390, 90, 20);

        TxKetKomplikasi.setHighlighter(null);
        TxKetKomplikasi.setName("TxKetKomplikasi"); // NOI18N
        TxKetKomplikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxKetKomplikasiActionPerformed(evt);
            }
        });
        TxKetKomplikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxKetKomplikasiKeyPressed(evt);
            }
        });
        FormInput.add(TxKetKomplikasi);
        TxKetKomplikasi.setBounds(610, 390, 140, 23);

        jLabel15.setText("Jumlah Perdarahan :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(410, 420, 100, 23);

        TxJmlhPendarahan.setHighlighter(null);
        TxJmlhPendarahan.setName("TxJmlhPendarahan"); // NOI18N
        TxJmlhPendarahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxJmlhPendarahanActionPerformed(evt);
            }
        });
        TxJmlhPendarahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxJmlhPendarahanKeyPressed(evt);
            }
        });
        FormInput.add(TxJmlhPendarahan);
        TxJmlhPendarahan.setBounds(510, 420, 230, 23);

        jLabel32.setText("Spesimen Operasi");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(420, 490, 90, 23);

        jLabel33.setText("Jenis Jaringan :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(430, 520, 80, 23);

        jLabel17.setText("Diagnosa Pasca Operasi :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(10, 200, 130, 23);

        TxDiagnosaPascaBedah.setHighlighter(null);
        TxDiagnosaPascaBedah.setName("TxDiagnosaPascaBedah"); // NOI18N
        TxDiagnosaPascaBedah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxDiagnosaPascaBedahActionPerformed(evt);
            }
        });
        TxDiagnosaPascaBedah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxDiagnosaPascaBedahKeyPressed(evt);
            }
        });
        FormInput.add(TxDiagnosaPascaBedah);
        TxDiagnosaPascaBedah.setBounds(150, 200, 560, 23);

        label13.setText("Tgl. Operasi :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label13);
        label13.setBounds(32, 80, 80, 23);

        TglOpMulai.setForeground(new java.awt.Color(50, 70, 50));
        TglOpMulai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-09-2024" }));
        TglOpMulai.setDisplayFormat("dd-MM-yyyy");
        TglOpMulai.setName("TglOpMulai"); // NOI18N
        TglOpMulai.setOpaque(false);
        TglOpMulai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglOpMulaiItemStateChanged(evt);
            }
        });
        FormInput.add(TglOpMulai);
        TglOpMulai.setBounds(120, 80, 170, 23);

        jLabel18.setText("Jumlah Transfusi :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(410, 450, 100, 23);

        TxJenisJaringan.setHighlighter(null);
        TxJenisJaringan.setName("TxJenisJaringan"); // NOI18N
        TxJenisJaringan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxJenisJaringanActionPerformed(evt);
            }
        });
        TxJenisJaringan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxJenisJaringanKeyPressed(evt);
            }
        });
        FormInput.add(TxJenisJaringan);
        TxJenisJaringan.setBounds(510, 520, 230, 23);

        TxJmlhTransfusi.setHighlighter(null);
        TxJmlhTransfusi.setName("TxJmlhTransfusi"); // NOI18N
        TxJmlhTransfusi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxJmlhTransfusiActionPerformed(evt);
            }
        });
        TxJmlhTransfusi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxJmlhTransfusiKeyPressed(evt);
            }
        });
        FormInput.add(TxJmlhTransfusi);
        TxJmlhTransfusi.setBounds(510, 450, 230, 23);

        TxUkuran.setHighlighter(null);
        TxUkuran.setName("TxUkuran"); // NOI18N
        TxUkuran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxUkuranActionPerformed(evt);
            }
        });
        TxUkuran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxUkuranKeyPressed(evt);
            }
        });
        FormInput.add(TxUkuran);
        TxUkuran.setBounds(510, 550, 230, 23);

        jLabel34.setText("Ukuran :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(430, 550, 80, 23);

        TxFiksasi.setHighlighter(null);
        TxFiksasi.setName("TxFiksasi"); // NOI18N
        TxFiksasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxFiksasiActionPerformed(evt);
            }
        });
        TxFiksasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxFiksasiKeyPressed(evt);
            }
        });
        FormInput.add(TxFiksasi);
        TxFiksasi.setBounds(510, 580, 230, 23);

        jLabel35.setText("Fiksasi :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(430, 580, 80, 23);

        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(420, 630, 330, 3);

        jLabel36.setText("Pemakaian Implan");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(420, 640, 90, 23);

        jLabel37.setText("Pabrik :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(430, 670, 80, 23);

        TxPabrikan.setHighlighter(null);
        TxPabrikan.setName("TxPabrikan"); // NOI18N
        TxPabrikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxPabrikanActionPerformed(evt);
            }
        });
        TxPabrikan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxPabrikanKeyPressed(evt);
            }
        });
        FormInput.add(TxPabrikan);
        TxPabrikan.setBounds(510, 670, 230, 23);

        jLabel38.setText("Type :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(430, 700, 80, 23);

        TxType.setHighlighter(null);
        TxType.setName("TxType"); // NOI18N
        TxType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxTypeActionPerformed(evt);
            }
        });
        TxType.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxTypeKeyPressed(evt);
            }
        });
        FormInput.add(TxType);
        TxType.setBounds(510, 700, 230, 23);

        jLabel39.setText("<html>Nomor Pendaftaran :</html>");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(440, 730, 70, 23);

        TxNoPendaftaran.setHighlighter(null);
        TxNoPendaftaran.setName("TxNoPendaftaran"); // NOI18N
        TxNoPendaftaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxNoPendaftaranActionPerformed(evt);
            }
        });
        TxNoPendaftaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxNoPendaftaranKeyPressed(evt);
            }
        });
        FormInput.add(TxNoPendaftaran);
        TxNoPendaftaran.setBounds(510, 730, 230, 23);

        label12.setText("Selesaii :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label12);
        label12.setBounds(30, 140, 80, 23);

        CmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam.setName("CmbJam"); // NOI18N
        CmbJam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbJamItemStateChanged(evt);
            }
        });
        FormInput.add(CmbJam);
        CmbJam.setBounds(120, 110, 62, 23);

        CmbMenit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit.setName("CmbMenit"); // NOI18N
        CmbMenit.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbMenitItemStateChanged(evt);
            }
        });
        FormInput.add(CmbMenit);
        CmbMenit.setBounds(180, 110, 62, 23);

        CmbDetik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik.setName("CmbDetik"); // NOI18N
        CmbDetik.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbDetikItemStateChanged(evt);
            }
        });
        FormInput.add(CmbDetik);
        CmbDetik.setBounds(240, 110, 62, 23);

        CmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam1.setName("CmbJam1"); // NOI18N
        CmbJam1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbJam1ItemStateChanged(evt);
            }
        });
        FormInput.add(CmbJam1);
        CmbJam1.setBounds(120, 140, 62, 23);

        CmbMenit1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit1.setName("CmbMenit1"); // NOI18N
        CmbMenit1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbMenit1ItemStateChanged(evt);
            }
        });
        FormInput.add(CmbMenit1);
        CmbMenit1.setBounds(180, 140, 62, 23);

        CmbDetik1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik1.setName("CmbDetik1"); // NOI18N
        CmbDetik1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbDetik1ItemStateChanged(evt);
            }
        });
        FormInput.add(CmbDetik1);
        CmbDetik1.setBounds(240, 140, 62, 23);

        label15.setText("Lama Operasi :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(30, 170, 80, 23);

        CmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam2.setName("CmbJam2"); // NOI18N
        FormInput.add(CmbJam2);
        CmbJam2.setBounds(120, 170, 62, 23);

        CmbMenit2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit2.setName("CmbMenit2"); // NOI18N
        FormInput.add(CmbMenit2);
        CmbMenit2.setBounds(180, 170, 62, 23);

        CmbDetik2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik2.setName("CmbDetik2"); // NOI18N
        FormInput.add(CmbDetik2);
        CmbDetik2.setBounds(240, 170, 62, 23);

        jLabel40.setText("Nama Ruang :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(440, 800, 70, 23);

        TxRuang.setHighlighter(null);
        TxRuang.setName("TxRuang"); // NOI18N
        TxRuang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxRuangActionPerformed(evt);
            }
        });
        TxRuang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxRuangKeyPressed(evt);
            }
        });
        FormInput.add(TxRuang);
        TxRuang.setBounds(510, 800, 230, 23);

        jLabel41.setText("Status :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(440, 770, 70, 23);

        btnTemplate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnTemplate.setMnemonic('2');
        btnTemplate.setToolTipText("Alt+2");
        btnTemplate.setName("btnTemplate"); // NOI18N
        btnTemplate.setPreferredSize(new java.awt.Dimension(28, 23));
        btnTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTemplateActionPerformed(evt);
            }
        });
        FormInput.add(btnTemplate);
        btnTemplate.setBounds(240, 320, 28, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Laporan", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl.Asuhan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-09-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-09-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setToolTipText("Alt+3");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Laporan", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
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

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

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

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnAll);

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

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
        }else{            
            Valid.pindah(evt,TCari,BtnDokter);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else if(nmperawat1.getText().trim().equals("")){
            kdperawat1.setText("-");
            nmperawat1.setText("-");
        }else if(nmperawat2.getText().trim().equals("")){
            kdperawat2.setText("-");
            nmperawat2.setText("-");
        }else if(nmperawat3.getText().trim().equals("")){
            kdperawat3.setText("-");
            nmperawat3.setText("-");
        }else if(TxDiagnosaPascaBedah.getText().trim().equals("")){
            Valid.textKosong(TxDiagnosaPascaBedah,"Diagnosa Pasca Bedah");
        }else if(TxLaporanOperasi.getText().trim().equals("")){
            Valid.textKosong(TxLaporanOperasi,"laporan Operasi");
        }else{
            try {
                if(Sequel.menyimpantf("laporan_operasi_lokal","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data Laporan Operasi Lokal",25,new String[]{
                  TNoRw.getText(),
                  Valid.SetTgl(TglOpMulai.getSelectedItem()+""),
                  CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem(),
                  CmbJam1.getSelectedItem()+":"+CmbMenit1.getSelectedItem()+":"+CmbDetik1.getSelectedItem(),
                  CmbJam2.getSelectedItem()+":"+CmbMenit2.getSelectedItem()+":"+CmbDetik2.getSelectedItem(),
                  Kategori.getSelectedItem().toString(),
                  KdDokter.getText(),
                  kdperawat1.getText(),
                  kdperawat2.getText(),
                  kdperawat3.getText(),
                  TxDiagnosaPascaBedah.getText(),
                  TxTindakanOperasi.getText(),
                  TxLaporanOperasi.getText(),
                  TxKomplikasi.getSelectedItem().toString(),
                  TxKetKomplikasi.getText(),
                  TxJmlhPendarahan.getText(),
                  TxJmlhTransfusi.getText(),
                  TxJenisJaringan.getText(),
                  TxUkuran.getText(),
                  TxFiksasi.getText(),
                  TxPabrikan.getText(),
                  TxType.getText(),
                  TxNoPendaftaran.getText(),
                  TxStatus.getSelectedItem().toString(),
                  TxRuang.getText()
                })==true){
                      JOptionPane.showMessageDialog(null,"Proses simpan selesai...!");
                      emptTeks();
               }  
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Proses gagal...!E: "+e);
            }
        }
    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TabRawat.getSelectedIndex()!=0) {
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    hapus();
                }else{
                    if(KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString())){
                        hapus();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh dokter yang bersangkutan..!!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
            }  
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data laporan terlebih dahulu..!!");
        }
    
                        
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(NmDokter.getText().trim().equals("")){
            Valid.textKosong(BtnDokter,"Dokter");
        }else if(TxLaporanOperasi.getText().trim().equals("")){
            Valid.textKosong(TxLaporanOperasi,"Ringkasan Klinik");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh dokter yang bersangkutan..!!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            TCari.setText("");
            tampil();
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if((evt.getClickCount()==2)&&(tbObat.getSelectedColumn()==0)){
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        //Valid.pindah(evt,Edukasi,Hubungan);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void MnCetakLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakLaporanActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());          
            param.put("norawat",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());          
            param.put("dokter",tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());          
            param.put("tglmulai",tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());          
            param.put("jammulai",tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());          
            param.put("status",tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());          
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),11).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),10).toString():finger)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())); 
            
            Valid.MyReport("rptLaporanOperasiLokal.jasper","report","::[ Laporan Operasi dengan Lokal ]::",param);
        }
    }//GEN-LAST:event_MnCetakLaporanActionPerformed

    private void TxLaporanOperasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxLaporanOperasiKeyPressed
        
    }//GEN-LAST:event_TxLaporanOperasiKeyPressed

    private void btnPerawat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerawat1ActionPerformed
        perawat1.isCek();
        perawat1.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        perawat1.setLocationRelativeTo(internalFrame1);
        perawat1.setAlwaysOnTop(false);
        perawat1.setVisible(true);
    }//GEN-LAST:event_btnPerawat1ActionPerformed

    private void kdperawat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdperawat1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPerawat1ActionPerformed(null);
        }
    }//GEN-LAST:event_kdperawat1KeyPressed

    private void kdperawat2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdperawat2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPerawat2ActionPerformed(null);
        }else{
            Valid.pindah(evt,kdperawat1,kdperawat3);
        }
    }//GEN-LAST:event_kdperawat2KeyPressed

    private void btnPerawat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerawat2ActionPerformed
        perawat2.isCek();
        perawat2.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        perawat2.setLocationRelativeTo(internalFrame1);
        perawat2.setAlwaysOnTop(false);
        perawat2.setVisible(true);
    }//GEN-LAST:event_btnPerawat2ActionPerformed

    private void kdperawat3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdperawat3KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPerawat3ActionPerformed(null);
        }
    }//GEN-LAST:event_kdperawat3KeyPressed

    private void btnPerawat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerawat3ActionPerformed
        perawat3.isCek();
        perawat3.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        perawat3.setLocationRelativeTo(internalFrame1);
        perawat3.setAlwaysOnTop(false);
        perawat3.setVisible(true);
    }//GEN-LAST:event_btnPerawat3ActionPerformed

    private void KategoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KategoriKeyPressed
        
    }//GEN-LAST:event_KategoriKeyPressed

    private void TxTindakanOperasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxTindakanOperasiKeyPressed
        
    }//GEN-LAST:event_TxTindakanOperasiKeyPressed

    private void TxKomplikasiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TxKomplikasiItemStateChanged
        isCek2();
    }//GEN-LAST:event_TxKomplikasiItemStateChanged

    private void TxJmlhPendarahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxJmlhPendarahanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxJmlhPendarahanActionPerformed

    private void TxJmlhPendarahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxJmlhPendarahanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxJmlhPendarahanKeyPressed

    private void TxDiagnosaPascaBedahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxDiagnosaPascaBedahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxDiagnosaPascaBedahActionPerformed

    private void TxDiagnosaPascaBedahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxDiagnosaPascaBedahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxDiagnosaPascaBedahKeyPressed

    private void TxJmlhTransfusiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxJmlhTransfusiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxJmlhTransfusiActionPerformed

    private void TxJmlhTransfusiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxJmlhTransfusiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxJmlhTransfusiKeyPressed

    private void TxKetKomplikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxKetKomplikasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxKetKomplikasiActionPerformed

    private void TxKetKomplikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxKetKomplikasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxKetKomplikasiKeyPressed

    private void TxJenisJaringanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxJenisJaringanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxJenisJaringanActionPerformed

    private void TxJenisJaringanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxJenisJaringanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxJenisJaringanKeyPressed

    private void TxUkuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxUkuranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxUkuranActionPerformed

    private void TxUkuranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxUkuranKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxUkuranKeyPressed

    private void TxFiksasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxFiksasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxFiksasiActionPerformed

    private void TxFiksasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxFiksasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxFiksasiKeyPressed

    private void TxPabrikanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxPabrikanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxPabrikanActionPerformed

    private void TxPabrikanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxPabrikanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxPabrikanKeyPressed

    private void TxTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxTypeActionPerformed

    private void TxTypeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxTypeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxTypeKeyPressed

    private void TxNoPendaftaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxNoPendaftaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxNoPendaftaranActionPerformed

    private void TxNoPendaftaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxNoPendaftaranKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxNoPendaftaranKeyPressed

    private void TglOpMulaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglOpMulaiItemStateChanged
        
    }//GEN-LAST:event_TglOpMulaiItemStateChanged

    private void CmbJamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbJamItemStateChanged
        getLamaOp();
    }//GEN-LAST:event_CmbJamItemStateChanged

    private void CmbJam1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbJam1ItemStateChanged
        getLamaOp();
    }//GEN-LAST:event_CmbJam1ItemStateChanged

    private void CmbMenitItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbMenitItemStateChanged
        getLamaOp();
    }//GEN-LAST:event_CmbMenitItemStateChanged

    private void CmbDetikItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbDetikItemStateChanged
        getLamaOp();
    }//GEN-LAST:event_CmbDetikItemStateChanged

    private void CmbMenit1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbMenit1ItemStateChanged
        getLamaOp();
    }//GEN-LAST:event_CmbMenit1ItemStateChanged

    private void CmbDetik1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbDetik1ItemStateChanged
        getLamaOp();
    }//GEN-LAST:event_CmbDetik1ItemStateChanged

    private void TxRuangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxRuangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxRuangActionPerformed

    private void TxRuangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxRuangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxRuangKeyPressed

    private void TxStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TxStatusItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TxStatusItemStateChanged

    private void btnTemplateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTemplateActionPerformed
        template.emptTeks();
        template.isCek();
        template.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        template.setLocationRelativeTo(internalFrame1);
        template.setVisible(true);
    }//GEN-LAST:event_btnTemplateActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMLaporanOperasi dialog = new RMLaporanOperasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.ComboBox CmbDetik;
    private widget.ComboBox CmbDetik1;
    private widget.ComboBox CmbDetik2;
    private widget.ComboBox CmbJam;
    private widget.ComboBox CmbJam1;
    private widget.ComboBox CmbJam2;
    private widget.ComboBox CmbMenit;
    private widget.ComboBox CmbMenit1;
    private widget.ComboBox CmbMenit2;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Jk;
    private widget.ComboBox Kategori;
    private widget.TextBox KdDokter;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private javax.swing.JMenuItem MnCetakLaporan;
    private widget.TextBox NmDokter;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox TglLahir;
    private widget.Tanggal TglOpMulai;
    private widget.TextBox TxDiagnosaPascaBedah;
    private widget.TextBox TxFiksasi;
    private widget.TextBox TxJenisJaringan;
    private widget.TextBox TxJmlhPendarahan;
    private widget.TextBox TxJmlhTransfusi;
    private widget.TextBox TxKetKomplikasi;
    private widget.ComboBox TxKomplikasi;
    private widget.TextArea TxLaporanOperasi;
    private widget.TextBox TxNoPendaftaran;
    private widget.TextBox TxPabrikan;
    private widget.TextBox TxRuang;
    private widget.ComboBox TxStatus;
    private widget.TextArea TxTindakanOperasi;
    private widget.TextBox TxType;
    private widget.TextBox TxUkuran;
    private widget.Button btnPerawat1;
    private widget.Button btnPerawat2;
    private widget.Button btnPerawat3;
    private widget.Button btnTemplate;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel15;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private widget.TextBox kdperawat1;
    private widget.TextBox kdperawat2;
    private widget.TextBox kdperawat3;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label17;
    private widget.Label label27;
    private widget.Label label29;
    private widget.Label label30;
    private widget.TextBox nmperawat1;
    private widget.TextBox nmperawat2;
    private widget.TextBox nmperawat3;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane3;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            
            ps=koneksi.prepareStatement(
                "SELECT\n" +
                "	laporan_operasi_lokal.no_rawat, \n" +
                "	pasien.no_rkm_medis, \n" +
                "	pasien.nm_pasien, \n" +
                "	pasien.jk, \n" +
                "	pasien.tgl_lahir, \n" +
                "	laporan_operasi_lokal.tanggal_mulai, \n" +
                "	laporan_operasi_lokal.jam_mulai, \n" +
                "	laporan_operasi_lokal.jam_selesai, \n" +
                "	laporan_operasi_lokal.lama_op, \n" +
                "	laporan_operasi_lokal.kategori, \n" +
                "	laporan_operasi_lokal.dokter, \n" +
                "	dokter.nm_dokter, \n" +
                "	laporan_operasi_lokal.perawat1, \n" +
                "	perawat1.nama AS nm_perawat1, \n" +
                "	laporan_operasi_lokal.perawat2, \n" +
                "	perawat2.nama AS nm_perawat2, \n" +
                "	laporan_operasi_lokal.perawat3, \n" +
                "	perawat3.nama AS nm_perawat3, \n" +
                "	laporan_operasi_lokal.diagnosa_pasca_bedah, \n" +
                "	laporan_operasi_lokal.tindakan_operasi, \n" +
                "	laporan_operasi_lokal.laporan_operasi, \n" +
                "	laporan_operasi_lokal.komplikasi, \n" +
                "	laporan_operasi_lokal.ket_komplikasi, \n" +
                "	laporan_operasi_lokal.jml_pendarahan, \n" +
                "	laporan_operasi_lokal.jml_transfusi, \n" +
                "	laporan_operasi_lokal.jns_jaringan, \n" +
                "	laporan_operasi_lokal.ukuran, \n" +
                "	laporan_operasi_lokal.fiksasi, \n" +
                "	laporan_operasi_lokal.pabrik, \n" +
                "	laporan_operasi_lokal.type, \n" +
                "	laporan_operasi_lokal.nomor_pendaftaran, \n" +
                "	laporan_operasi_lokal.`status`, \n" +
                "	laporan_operasi_lokal.nama_ruang\n" +
                "FROM\n" +
                "	laporan_operasi_lokal\n" +
                "	INNER JOIN\n" +
                "	dokter\n" +
                "	ON \n" +
                "		laporan_operasi_lokal.dokter = dokter.kd_dokter\n" +
                "	INNER JOIN\n" +
                "	petugas AS perawat1\n" +
                "	ON \n" +
                "		laporan_operasi_lokal.perawat1 = perawat1.nip\n" +
                "	INNER JOIN\n" +
                "	reg_periksa\n" +
                "	ON \n" +
                "		laporan_operasi_lokal.no_rawat = reg_periksa.no_rawat\n" +
                "	INNER JOIN\n" +
                "	pasien\n" +
                "	ON \n" +
                "		reg_periksa.no_rkm_medis = pasien.no_rkm_medis\n" +
                "	INNER JOIN\n" +
                "	petugas AS perawat2\n" +
                "	ON \n" +
                "		laporan_operasi_lokal.perawat2 = perawat2.nip\n" +
                "	INNER JOIN\n" +
                "	petugas AS perawat3\n" +
                "	ON \n" +
                "		laporan_operasi_lokal.perawat3 = perawat3.nip\n" +
                "WHERE\n" +
                "	laporan_operasi_lokal.tanggal_mulai BETWEEN ? AND ? AND\n" +
                "	(\n" +
                "		reg_periksa.no_rawat LIKE ? OR\n" +
                "		pasien.no_rkm_medis LIKE ? OR\n" +
                "		pasien.nm_pasien LIKE ? OR\n" +
                "		laporan_operasi_lokal.dokter LIKE ? OR\n" +
                "		dokter.nm_dokter LIKE ?\n" +
                "	)\n" +
                "ORDER BY\n" +
                "	laporan_operasi_lokal.tanggal_mulai ASC"
            );
            
            try {
               
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(3,"%"+TCari.getText()+"%");
                ps.setString(4,"%"+TCari.getText()+"%");
                ps.setString(5,"%"+TCari.getText()+"%");
                ps.setString(6,"%"+TCari.getText()+"%");
                ps.setString(7,"%"+TCari.getText()+"%");
                    
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jk"),
                        rs.getString("tgl_lahir"),
                        rs.getString("tanggal_mulai"),
                        rs.getString("jam_mulai"),
                        rs.getString("jam_selesai"),
                        rs.getString("lama_op"),
                        rs.getString("kategori"),
                        rs.getString("dokter"),
                        rs.getString("nm_dokter"),
                        rs.getString("perawat1"),
                        rs.getString("nm_perawat1"),
                        rs.getString("perawat2"),
                        rs.getString("nm_perawat2"),
                        rs.getString("perawat3"),
                        rs.getString("nm_perawat3"),
                        rs.getString("diagnosa_pasca_bedah"),
                        rs.getString("tindakan_operasi"),
                        rs.getString("laporan_operasi"),
                        rs.getString("komplikasi"),
                        rs.getString("ket_komplikasi"),
                        rs.getString("jml_pendarahan"),
                        rs.getString("jml_transfusi"),
                        rs.getString("jns_jaringan"),
                        rs.getString("ukuran"),
                        rs.getString("fiksasi"),
                        rs.getString("pabrik"),
                        rs.getString("type"),
                        rs.getString("nomor_pendaftaran"),
                        rs.getString("status"),
                        rs.getString("nama_ruang"),
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
        TglOpMulai.setDate(new Date());
        TabRawat.setSelectedIndex(0);
//        KdDokter.setText("");
//        NmDokter.setText("");
        kdperawat1.setText("-");
        nmperawat1.setText("-");
        kdperawat2.setText("-");
        nmperawat2.setText("-");
        kdperawat3.setText("-");
        nmperawat3.setText("-");
        TxDiagnosaPascaBedah.setText("");
        TxTindakanOperasi.setText("");
        TxKomplikasi.setSelectedIndex(0);
        TxKetKomplikasi.setText("");
        TxJenisJaringan.setText("");
        TxFiksasi.setText("");
        TxUkuran.setText("");
        TxPabrikan.setText("");
        TxType.setText("");
        TxNoPendaftaran.setText("");
        TxJmlhPendarahan.setText("");
        TxJmlhTransfusi.setText("");
        TxLaporanOperasi.setText("");
        TxLaporanOperasi.requestFocus();
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()); 
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            Valid.SetTgl2(TglOpMulai,tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            CmbJam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString().substring(0,2));
            CmbMenit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString().substring(3,5));
            CmbDetik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString().substring(6,8));
            CmbJam1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString().substring(0,2));
            CmbMenit1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString().substring(3,5));
            CmbDetik1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString().substring(6,8));
            CmbJam2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().substring(0,2));
            CmbMenit2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().substring(3,5));
            CmbDetik2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString().substring(6,8));
            Kategori.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            KdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            NmDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            kdperawat1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            nmperawat1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            kdperawat2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            nmperawat2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            kdperawat3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            nmperawat3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            TxDiagnosaPascaBedah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            TxTindakanOperasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            TxLaporanOperasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            TxKomplikasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            TxKetKomplikasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            TxJmlhPendarahan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            TxJmlhTransfusi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            TxJenisJaringan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            TxUkuran.setText(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            TxFiksasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            TxPabrikan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            TxType.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            TxNoPendaftaran.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            TxStatus.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            TxRuang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
        }
    }

    private void isRawat() {
        try {
            ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rkm_medis,pasien.nm_pasien, if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,reg_periksa.tgl_registrasi "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "where reg_periksa.no_rawat=?");
            try {
                ps.setString(1,TNoRw.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
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
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }
 
    public void setNoRm(String norwt,Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);    
        isRawat(); 
    }
 
    public void setStatus(String status,String ruang) {
        TxStatus.setSelectedItem(status);
        TxRuang.setText(ruang);
    }
    
    public void setNoRm(String norwt,Date tgl2,String KodeDokter,String NamaDokter,String Operasi) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);    
        isRawat(); 
        KdDokter.setText(KodeDokter);
        NmDokter.setText(NamaDokter);
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpenilaian_pre_operasi());
        BtnHapus.setEnabled(akses.getpenilaian_pre_operasi());
        BtnEdit.setEnabled(akses.getpenilaian_pre_operasi());
        BtnEdit.setEnabled(akses.getpenilaian_pre_operasi());
        if(akses.getjml2()>=1){
            KdDokter.setEditable(false);
//            BtnDokter.setEnabled(false);
            KdDokter.setText(akses.getkode());
            NmDokter.setText(dokter.tampil3(KdDokter.getText()));
            if(NmDokter.getText().equals("")){
                KdDokter.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan Dokter...!!");
            }
        }            
    }
    
    public void setTampil(){
        TabRawat.setSelectedIndex(1);
    }

    private void hapus() {
        int reply = JOptionPane.showConfirmDialog(rootPane,"Yakin Ingin Menghapus??","::[Konfirmasi Hapus Data]::",JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            if(Sequel.queryu2tf("delete from laporan_operasi_lokal where no_rawat=? and tanggal_mulai=? and jam_mulai=? and jam_selesai=? and dokter=? and status=? and kategori=? and tindakan_operasi=? ",8,new String[]{
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),5).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),6).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),7).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),10).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),31).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),9).toString(),
                tbObat.getValueAt(tbObat.getSelectedRow(),19).toString()
            })==true){
                JOptionPane.showMessageDialog(null,"Berhail menghapus..!!");
                tabMode.removeRow(tbObat.getSelectedRow());
                LCount.setText(""+tabMode.getRowCount());
                TabRawat.setSelectedIndex(1);
            }else{
                JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
            }
        }
    }

    private void ganti() {
        int reply = JOptionPane.showConfirmDialog(rootPane,"Yakin ingin Mengubah??","::[Konfirmasi Ubah Data]::",JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                if(Sequel.queryu2tf("UPDATE laporan_operasi_lokal SET tanggal_mulai=?,jam_mulai=?,"
                        + "jam_selesai=?,lama_op=?,kategori=?,dokter=?,perawat1=?,"
                        + "perawat2=?,perawat3=?,diagnosa_pasca_bedah=?,tindakan_operasi=?,"
                        + "laporan_operasi=?,komplikasi=?,ket_komplikasi=?,jml_pendarahan=?,"
                        + "jml_transfusi=?,jns_jaringan=?,ukuran=?,fiksasi=?,pabrik=?,"
                        + "type=?,nomor_pendaftaran=?,status=?,nama_ruang=? WHERE no_rawat=? and tanggal_mulai=? and jam_mulai=? and jam_selesai=? and dokter=? and status=? and kategori=? and tindakan_operasi=? ",32,new String[]{
                        Valid.SetTgl(TglOpMulai.getSelectedItem()+""),
                        CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem(),
                        CmbJam1.getSelectedItem()+":"+CmbMenit1.getSelectedItem()+":"+CmbDetik1.getSelectedItem(),
                        CmbJam2.getSelectedItem()+":"+CmbMenit2.getSelectedItem()+":"+CmbDetik2.getSelectedItem(),
                        Kategori.getSelectedItem().toString(),
                        KdDokter.getText(),
                        kdperawat1.getText(),
                        kdperawat2.getText(),
                        kdperawat3.getText(),
                        TxDiagnosaPascaBedah.getText(),
                        TxTindakanOperasi.getText(),
                        TxLaporanOperasi.getText(),
                        TxKomplikasi.getSelectedItem().toString(),
                        TxKetKomplikasi.getText(),
                        TxJmlhPendarahan.getText(),
                        TxJmlhTransfusi.getText(),
                        TxJenisJaringan.getText(),
                        TxUkuran.getText(),
                        TxFiksasi.getText(),
                        TxPabrikan.getText(),
                        TxType.getText(),
                        TxNoPendaftaran.getText(),
                        TxStatus.getSelectedItem().toString(),
                        TxRuang.getText(),
                            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),
                            tbObat.getValueAt(tbObat.getSelectedRow(),5).toString(),
                            tbObat.getValueAt(tbObat.getSelectedRow(),6).toString(),
                            tbObat.getValueAt(tbObat.getSelectedRow(),7).toString(),
                            tbObat.getValueAt(tbObat.getSelectedRow(),10).toString(),
                            tbObat.getValueAt(tbObat.getSelectedRow(),31).toString(),
                            tbObat.getValueAt(tbObat.getSelectedRow(),9).toString(),
                            tbObat.getValueAt(tbObat.getSelectedRow(),19).toString(),
                    })==true){
                       tampil();
                       emptTeks();
                       TabRawat.setSelectedIndex(1);
                }else{
                    JOptionPane.showMessageDialog(null,"Proses simpan gagal...!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Proses simpan gagal...!E:"+e);
            }
        }
    }

    private void isCek2(){
        
        if(TxKomplikasi.getSelectedItem().equals("Ya")){
            TxKetKomplikasi.setVisible(true);
        }else{
            TxKetKomplikasi.setVisible(false);
            TxKetKomplikasi.setText("");
        }
          
    }
    
    private void getLamaOp(){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            // Get selected time strings
            String startTimeStr = CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem();
            String endTimeStr = CmbJam1.getSelectedItem()+":"+CmbMenit1.getSelectedItem()+":"+CmbDetik1.getSelectedItem();
            String nol_jam = "";
            String nol_menit = "";
            String nol_detik = "";
            
            // Parse the strings to LocalTime
            LocalTime startTime = LocalTime.parse(startTimeStr, formatter);
            LocalTime endTime = LocalTime.parse(endTimeStr, formatter);

            // Calculate the duration
            Duration duration;
            if (endTime.isBefore(startTime)) {
                // If end time is before start time, assume it is the next day
                duration = Duration.between(startTime, endTime.plusHours(24));
            } else {
                duration = Duration.between(startTime, endTime);
            }
            // Extract hours, minutes, and seconds
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            long seconds = duration.getSeconds() % 60;
                if (hours <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (minutes <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (seconds <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
            // Display the result
            CmbJam2.setSelectedItem(nol_jam+Long.toString(hours));
            CmbMenit2.setSelectedItem(nol_menit+Long.toString(minutes));
            CmbDetik2.setSelectedItem(nol_detik+Long.toString(seconds));
        } catch (Exception ex) {
            CmbJam2.setSelectedItem("00");
            CmbMenit2.setSelectedItem("00");
            CmbDetik2.setSelectedItem("00");
        }
    }

}
