package permintaan;
import bridging.ApiCareStream;
import fungsi.BackgroundMusic;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariDokter;
import simrskhanza.DlgCariBangsal;
import simrskhanza.DlgCariPoli;
import simrskhanza.DlgPeriksaDarah;
import simrskhanza.DlgPeriksaRadiologi;

public class DlgCariPermintaanDarah extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabMode3;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private DlgCariPoli poli=new DlgCariPoli(null,false);
    private DlgCariBangsal ruang=new DlgCariBangsal(null,false);
    private int i,nilai_detik,permintaanbaru=0;
    private PreparedStatement ps,ps2;
    private final Properties prop = new Properties();
    private BackgroundMusic music;
    private ResultSet rs,rs2;
    private Date now;
    private boolean aktif=false,semua;
    private String alarm="",formalarm="",nol_detik,detik,tglsampel="",tglhasil="",norm="",kamar="",namakamar="",
            NoPermintaan="",NoRawat="",Pasien="",Permintaan="",JamPermintaan="",Sampel="",JamSampel="",Hasil="",JamHasil="",KodeDokter="",DokterPerujuk="",Ruang="",
            InformasiTambahan="",Klinis="",finger="",statuspulang="";
    
    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public DlgCariPermintaanDarah(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        WindowAmbilSampel.setSize(400,200);
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Permintaan","No.Rawat","Pasien","Permintaan","Jam","Diperlukan","Jam","Kode Dokter","Dokter Perujuk","Poli Registrasi","Diagnosis Klinis","Alasan Transfusi","Kode Bayar","Jenis Bayar","Tgl.Sampel","Jam.Sampel"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbRadiologiRalan.setModel(tabMode);

        tbRadiologiRalan.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbRadiologiRalan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbRadiologiRalan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(90);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==2){
                column.setPreferredWidth(260);
            }else if(i==3){
                column.setPreferredWidth(100);
            }else if(i==4){
                column.setPreferredWidth(50);
            }else if(i==5){
                column.setPreferredWidth(100);
            }else if(i==6){
                column.setPreferredWidth(50);
            }else if(i==7){
                column.setPreferredWidth(50);
            }else if(i==8){
                column.setPreferredWidth(200);
            }else if(i==9){
                column.setPreferredWidth(200);
            }else if(i==10){
                column.setPreferredWidth(150);
            }else if(i==11){
                column.setPreferredWidth(150);
            }else if(i==12){
                column.setPreferredWidth(100);
            }else if(i==13){
                column.setPreferredWidth(150);
            }else if(i==14){
                column.setPreferredWidth(150);
            }else if(i==15){
                column.setPreferredWidth(150);
            }
        }
        tbRadiologiRalan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3=new DefaultTableModel(null,new Object[]{
            "No.Permintaan","No.Rawat","Pasien","Permintaan","Jam","Diperlukan","Jam","Kode Dokter","Dokter Perujuk","Poli Registrasi","Diagnosis Klinis","Alasan Transfusi","Kode Bayar","Jenis Bayar","Tgl.Sampel","Jam.Sampel"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbRadiologiRanap.setModel(tabMode3);

        tbRadiologiRanap.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbRadiologiRanap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbRadiologiRanap.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(90);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==2){
                column.setPreferredWidth(260);
            }else if(i==3){
                column.setPreferredWidth(100);
            }else if(i==4){
                column.setPreferredWidth(50);
            }else if(i==5){
                column.setPreferredWidth(100);
            }else if(i==6){
                column.setPreferredWidth(50);
            }else if(i==7){
                column.setPreferredWidth(50);
            }else if(i==8){
                column.setPreferredWidth(200);
            }else if(i==9){
                column.setPreferredWidth(200);
            }else if(i==10){
                column.setPreferredWidth(150);
            }else if(i==11){
                column.setPreferredWidth(150);
            }else if(i==12){
                column.setPreferredWidth(100);
            }else if(i==13){
                column.setPreferredWidth(150);
            }else if(i==14){
                column.setPreferredWidth(150);
            }else if(i==15){
                column.setPreferredWidth(150);
            }
        }
        tbRadiologiRanap.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
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
            public void windowOpened(WindowEvent e) {;}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){ 
                    if(TabPilihRawat.getSelectedIndex()==0){
                        CrDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        CrDokter.requestFocus();
                    }else if(TabPilihRawat.getSelectedIndex()==1){
                        CrDokter2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                        CrDokter2.requestFocus();
                    }                        
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
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli.getTable().getSelectedRow()!= -1){   
                    CrPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
                    CrPoli.requestFocus();
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
        
        ruang.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(ruang.getTable().getSelectedRow()!= -1){   
                    Kamar.setText(ruang.getTable().getValueAt(ruang.getTable().getSelectedRow(),1).toString());  
                    Kamar.requestFocus();
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
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            alarm=prop.getProperty("ALARMRADIOLOGI");
            formalarm=prop.getProperty("FORMALARMRADIOLOGI");
        } catch (Exception ex) {
            alarm="no";
            formalarm="ralan + ranap";
        }
        
        if(alarm.equals("yes")){
            jam();
        }
        
        ChkAccor.setSelected(false);
        isMenu();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WindowAmbilSampel = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        BtnSimpan4 = new widget.Button();
        jLabel26 = new widget.Label();
        TanggalPulang = new widget.Tanggal();
        TxJenisTabung = new widget.TextBox();
        jLabel27 = new widget.Label();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        ppSampelDarah = new javax.swing.JMenuItem();
        ppPemeriksaanDarah = new javax.swing.JMenuItem();
        ppBerikanDarah = new javax.swing.JMenuItem();
        MnHapusData = new javax.swing.JMenu();
        ppHapusPermintaan = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        jPanel2 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        label10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        panelisi1 = new widget.panelisi();
        BtnAll = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        TabPilihRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        panelGlass9 = new widget.panelisi();
        jLabel14 = new widget.Label();
        CrDokter = new widget.TextBox();
        BtnSeek3 = new widget.Button();
        jLabel16 = new widget.Label();
        CrPoli = new widget.TextBox();
        BtnSeek4 = new widget.Button();
        TabRawatJalan = new javax.swing.JTabbedPane();
        scrollPane1 = new widget.ScrollPane();
        tbRadiologiRalan = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        panelGlass10 = new widget.panelisi();
        jLabel15 = new widget.Label();
        CrDokter2 = new widget.TextBox();
        BtnSeek5 = new widget.Button();
        jLabel17 = new widget.Label();
        Kamar = new widget.TextBox();
        BtnSeek6 = new widget.Button();
        jLabel18 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        TabRawatInap = new javax.swing.JTabbedPane();
        scrollPane3 = new widget.ScrollPane();
        tbRadiologiRanap = new widget.Table();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        ScrollMenu = new widget.ScrollPane();
        FormMenu = new widget.PanelBiasa();
        BtnCetakHasilDarah = new widget.Button();
        BtnBarcodePermintaan = new widget.Button();

        WindowAmbilSampel.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowAmbilSampel.setName("WindowAmbilSampel"); // NOI18N
        WindowAmbilSampel.setUndecorated(true);
        WindowAmbilSampel.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Update Waktu Pengambilan Sampel ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(null);

        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(200, 110, 100, 30);

        BtnSimpan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan4.setMnemonic('S');
        BtnSimpan4.setText("Simpan");
        BtnSimpan4.setToolTipText("Alt+S");
        BtnSimpan4.setName("BtnSimpan4"); // NOI18N
        BtnSimpan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnSimpan4);
        BtnSimpan4.setBounds(10, 110, 100, 30);

        jLabel26.setText("Jenis Tabung :");
        jLabel26.setName("jLabel26"); // NOI18N
        internalFrame5.add(jLabel26);
        jLabel26.setBounds(0, 70, 100, 23);

        TanggalPulang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "16-07-2024 09:04:19" }));
        TanggalPulang.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TanggalPulang.setName("TanggalPulang"); // NOI18N
        TanggalPulang.setOpaque(false);
        TanggalPulang.setPreferredSize(new java.awt.Dimension(95, 23));
        internalFrame5.add(TanggalPulang);
        TanggalPulang.setBounds(120, 30, 150, 23);

        TxJenisTabung.setHighlighter(null);
        TxJenisTabung.setName("TxJenisTabung"); // NOI18N
        TxJenisTabung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxJenisTabungActionPerformed(evt);
            }
        });
        TxJenisTabung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxJenisTabungKeyPressed(evt);
            }
        });
        internalFrame5.add(TxJenisTabung);
        TxJenisTabung.setBounds(100, 70, 190, 23);

        jLabel27.setText("Tanggal & Jam :");
        jLabel27.setName("jLabel27"); // NOI18N
        internalFrame5.add(jLabel27);
        jLabel27.setBounds(6, 32, 100, 23);

        WindowAmbilSampel.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        jPopupMenu1.setForeground(new java.awt.Color(50, 50, 50));
        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        ppSampelDarah.setBackground(new java.awt.Color(255, 255, 254));
        ppSampelDarah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppSampelDarah.setForeground(new java.awt.Color(50, 50, 50));
        ppSampelDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppSampelDarah.setText("Sampel Darah");
        ppSampelDarah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSampelDarah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSampelDarah.setName("ppSampelDarah"); // NOI18N
        ppSampelDarah.setPreferredSize(new java.awt.Dimension(200, 26));
        ppSampelDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSampelDarahBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppSampelDarah);

        ppPemeriksaanDarah.setBackground(new java.awt.Color(255, 255, 254));
        ppPemeriksaanDarah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPemeriksaanDarah.setForeground(new java.awt.Color(50, 50, 50));
        ppPemeriksaanDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPemeriksaanDarah.setText("Pemeriksaan Darah");
        ppPemeriksaanDarah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPemeriksaanDarah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPemeriksaanDarah.setName("ppPemeriksaanDarah"); // NOI18N
        ppPemeriksaanDarah.setPreferredSize(new java.awt.Dimension(200, 26));
        ppPemeriksaanDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPemeriksaanDarahActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppPemeriksaanDarah);

        ppBerikanDarah.setBackground(new java.awt.Color(255, 255, 254));
        ppBerikanDarah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBerikanDarah.setForeground(new java.awt.Color(50, 50, 50));
        ppBerikanDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBerikanDarah.setText("Berikan Darah");
        ppBerikanDarah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBerikanDarah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBerikanDarah.setName("ppBerikanDarah"); // NOI18N
        ppBerikanDarah.setPreferredSize(new java.awt.Dimension(200, 26));
        ppBerikanDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBerikanDarahActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppBerikanDarah);

        MnHapusData.setBackground(new java.awt.Color(255, 255, 254));
        MnHapusData.setForeground(new java.awt.Color(50, 50, 50));
        MnHapusData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusData.setText("Hapus Data");
        MnHapusData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusData.setName("MnHapusData"); // NOI18N
        MnHapusData.setPreferredSize(new java.awt.Dimension(200, 26));

        ppHapusPermintaan.setBackground(new java.awt.Color(255, 255, 254));
        ppHapusPermintaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppHapusPermintaan.setForeground(new java.awt.Color(50, 50, 50));
        ppHapusPermintaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppHapusPermintaan.setText("Hapus Permintaan Darah");
        ppHapusPermintaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppHapusPermintaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppHapusPermintaan.setName("ppHapusPermintaan"); // NOI18N
        ppHapusPermintaan.setPreferredSize(new java.awt.Dimension(200, 26));
        ppHapusPermintaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppHapusPermintaanActionPerformed(evt);
            }
        });
        MnHapusData.add(ppHapusPermintaan);

        jPopupMenu1.add(MnHapusData);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Permintaan Darah ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass8.add(label11);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        Tgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl1KeyPressed(evt);
            }
        });
        panelGlass8.add(Tgl1);

        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass8.add(label18);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        Tgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl2KeyPressed(evt);
            }
        });
        panelGlass8.add(Tgl2);

        label10.setText("Key Word :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(112, 23));
        panelGlass8.add(label10);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(318, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass8.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('5');
        BtnCari.setToolTipText("Alt+5");
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
        panelGlass8.add(BtnCari);

        jPanel2.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelisi1.add(BtnAll);

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
        panelisi1.add(BtnPrint);

        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi1.add(jLabel10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(53, 23));
        panelisi1.add(LCount);

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
        panelisi1.add(BtnKeluar);

        jPanel2.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        TabPilihRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabPilihRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabPilihRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabPilihRawat.setName("TabPilihRawat"); // NOI18N
        TabPilihRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPilihRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        panelGlass9.setBorder(null);
        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 41));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel14.setText("Dokter :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass9.add(jLabel14);

        CrDokter.setEditable(false);
        CrDokter.setName("CrDokter"); // NOI18N
        CrDokter.setPreferredSize(new java.awt.Dimension(245, 23));
        panelGlass9.add(CrDokter);

        BtnSeek3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek3.setMnemonic('6');
        BtnSeek3.setToolTipText("ALt+6");
        BtnSeek3.setName("BtnSeek3"); // NOI18N
        BtnSeek3.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek3ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnSeek3);

        jLabel16.setText("Unit/Poli :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel16);

        CrPoli.setEditable(false);
        CrPoli.setName("CrPoli"); // NOI18N
        CrPoli.setPreferredSize(new java.awt.Dimension(245, 23));
        panelGlass9.add(CrPoli);

        BtnSeek4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek4.setMnemonic('5');
        BtnSeek4.setToolTipText("ALt+5");
        BtnSeek4.setName("BtnSeek4"); // NOI18N
        BtnSeek4.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek4ActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnSeek4);

        internalFrame2.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawatJalan.setBackground(new java.awt.Color(255, 255, 254));
        TabRawatJalan.setForeground(new java.awt.Color(50, 50, 50));
        TabRawatJalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawatJalan.setName("TabRawatJalan"); // NOI18N
        TabRawatJalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatJalanMouseClicked(evt);
            }
        });

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbRadiologiRalan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbRadiologiRalan.setComponentPopupMenu(jPopupMenu1);
        tbRadiologiRalan.setName("tbRadiologiRalan"); // NOI18N
        tbRadiologiRalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRadiologiRalanMouseClicked(evt);
            }
        });
        tbRadiologiRalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRadiologiRalanKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbRadiologiRalan);

        TabRawatJalan.addTab("Data Permintaan", scrollPane1);

        internalFrame2.add(TabRawatJalan, java.awt.BorderLayout.CENTER);

        TabPilihRawat.addTab("Rawat Jalan", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelGlass10.setBorder(null);
        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 41));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel15.setText("Dokter :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass10.add(jLabel15);

        CrDokter2.setEditable(false);
        CrDokter2.setName("CrDokter2"); // NOI18N
        CrDokter2.setPreferredSize(new java.awt.Dimension(170, 23));
        panelGlass10.add(CrDokter2);

        BtnSeek5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek5.setMnemonic('6');
        BtnSeek5.setToolTipText("ALt+6");
        BtnSeek5.setName("BtnSeek5"); // NOI18N
        BtnSeek5.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek5ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnSeek5);

        jLabel17.setText("Ruang :");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(54, 23));
        panelGlass10.add(jLabel17);

        Kamar.setEditable(false);
        Kamar.setName("Kamar"); // NOI18N
        Kamar.setPreferredSize(new java.awt.Dimension(170, 23));
        panelGlass10.add(Kamar);

        BtnSeek6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek6.setMnemonic('5');
        BtnSeek6.setToolTipText("ALt+5");
        BtnSeek6.setName("BtnSeek6"); // NOI18N
        BtnSeek6.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek6ActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnSeek6);

        jLabel18.setText("Status :");
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass10.add(jLabel18);

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Belum Pulang" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass10.add(cmbStatus);

        internalFrame3.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        TabRawatInap.setBackground(new java.awt.Color(255, 255, 254));
        TabRawatInap.setForeground(new java.awt.Color(50, 50, 50));
        TabRawatInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawatInap.setName("TabRawatInap"); // NOI18N
        TabRawatInap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatInapMouseClicked(evt);
            }
        });

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        scrollPane3.setName("scrollPane3"); // NOI18N
        scrollPane3.setOpaque(true);

        tbRadiologiRanap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbRadiologiRanap.setComponentPopupMenu(jPopupMenu1);
        tbRadiologiRanap.setName("tbRadiologiRanap"); // NOI18N
        tbRadiologiRanap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRadiologiRanapMouseClicked(evt);
            }
        });
        tbRadiologiRanap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRadiologiRanapKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(tbRadiologiRanap);

        TabRawatInap.addTab("Data Permintaan", scrollPane3);

        internalFrame3.add(TabRawatInap, java.awt.BorderLayout.CENTER);

        TabPilihRawat.addTab("Rawat Inap", internalFrame3);

        internalFrame1.add(TabPilihRawat, java.awt.BorderLayout.CENTER);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(230, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout());

        ChkAccor.setBackground(new java.awt.Color(255, 250, 250));
        ChkAccor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 255, 248)));
        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(15, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.EAST);

        ScrollMenu.setBorder(null);
        ScrollMenu.setName("ScrollMenu"); // NOI18N
        ScrollMenu.setOpaque(true);

        FormMenu.setBackground(new java.awt.Color(255, 255, 255));
        FormMenu.setBorder(null);
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(115, 43));
        FormMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 1));

        BtnCetakHasilDarah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnCetakHasilDarah.setText("Cetak Permintaan Darah");
        BtnCetakHasilDarah.setFocusPainted(false);
        BtnCetakHasilDarah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnCetakHasilDarah.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnCetakHasilDarah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnCetakHasilDarah.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnCetakHasilDarah.setName("BtnCetakHasilDarah"); // NOI18N
        BtnCetakHasilDarah.setPreferredSize(new java.awt.Dimension(215, 23));
        BtnCetakHasilDarah.setRoundRect(false);
        BtnCetakHasilDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakHasilDarahActionPerformed(evt);
            }
        });
        FormMenu.add(BtnCetakHasilDarah);

        BtnBarcodePermintaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item.png"))); // NOI18N
        BtnBarcodePermintaan.setText("Barcode No.Permintaan");
        BtnBarcodePermintaan.setFocusPainted(false);
        BtnBarcodePermintaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        BtnBarcodePermintaan.setGlassColor(new java.awt.Color(255, 255, 255));
        BtnBarcodePermintaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnBarcodePermintaan.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnBarcodePermintaan.setName("BtnBarcodePermintaan"); // NOI18N
        BtnBarcodePermintaan.setPreferredSize(new java.awt.Dimension(215, 23));
        BtnBarcodePermintaan.setRoundRect(false);
        BtnBarcodePermintaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBarcodePermintaanActionPerformed(evt);
            }
        });
        FormMenu.add(BtnBarcodePermintaan);

        ScrollMenu.setViewportView(FormMenu);

        PanelAccor.add(ScrollMenu, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelAccor, java.awt.BorderLayout.WEST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void Tgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl1KeyPressed
        Valid.pindah(evt,BtnKeluar,Tgl2);
    }//GEN-LAST:event_Tgl1KeyPressed

    private void Tgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl2KeyPressed
        Valid.pindah(evt, Tgl1,TCari);
    }//GEN-LAST:event_Tgl2KeyPressed

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
        pilihTab();
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
        if(TabPilihRawat.getSelectedIndex()==0){
            CrDokter.setText("");
            CrPoli.setText("");
            pilihRalan();
        }else if(TabPilihRawat.getSelectedIndex()==1){
            CrDokter2.setText("");
            Kamar.setText("");
            pilihRanap();
            statuspulang="";
            cmbStatus.setSelectedIndex(0);
        }
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        BtnAllActionPerformed(null);
    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        if(TabPilihRawat.getSelectedIndex()==0){
            if(TabRawatJalan.getSelectedIndex()==0){
                if(tabMode.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    TCari.requestFocus();
                }else if(tabMode.getRowCount()!=0){
                    
                    Sequel.queryu("delete from temporary_permintaan_radiologi");
                    int row=tabMode.getRowCount();
                    for(i=0;i<row;i++){  
                        tglsampel="";
                        try {
                            tglsampel=tabMode.getValueAt(i,5).toString();
                        } catch (Exception e) {
                            tglsampel="";
                        }
                        tglhasil="";
                        try {
                            tglhasil=tabMode.getValueAt(i,7).toString();
                        } catch (Exception e) {
                            tglhasil="";
                        }
                        Sequel.menyimpan("temporary_permintaan_radiologi","'0','"+
                            tabMode.getValueAt(i,0).toString()+"','"+
                            tabMode.getValueAt(i,1).toString()+"','"+
                            tabMode.getValueAt(i,2).toString()+"','"+
                            tabMode.getValueAt(i,3).toString()+"','"+
                            tabMode.getValueAt(i,4).toString()+"','"+
                            tglsampel+"','"+
                            tabMode.getValueAt(i,6).toString()+"','"+
                            tglhasil+"','"+
                            tabMode.getValueAt(i,8).toString()+"','"+
                            tabMode.getValueAt(i,9).toString()+"','"+
                            tabMode.getValueAt(i,10).toString()+"','"+
                            tabMode.getValueAt(i,11).toString()+"','','','','','','','','','','','','','','','','','','','','','','','','',''","Periksa Lab"); 
                    }
                    
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars",akses.getnamars());
                    param.put("alamatrs",akses.getalamatrs());
                    param.put("kotars",akses.getkabupatenrs());
                    param.put("propinsirs",akses.getpropinsirs());
                    param.put("kontakrs",akses.getkontakrs());
                    param.put("emailrs",akses.getemailrs());
                    param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                    Valid.MyReport("rptLapPermintaanRadiologi.jasper","report","::[ Data Permintaan Radiologi ]::",param);
                }
            }            
        }else if(TabPilihRawat.getSelectedIndex()==1){
            if(TabRawatInap.getSelectedIndex()==0){
                if(tabMode3.getRowCount()==0){
                    JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    TCari.requestFocus();
                }else if(tabMode3.getRowCount()!=0){
                    
                    Sequel.queryu("delete from temporary_permintaan_radiologi");
                    int row=tabMode3.getRowCount();
                    for(i=0;i<row;i++){  
                        tglsampel="";
                        try {
                            tglsampel=tabMode.getValueAt(i,5).toString();
                        } catch (Exception e) {
                            tglsampel="";
                        }
                        tglhasil="";
                        try {
                            tglhasil=tabMode.getValueAt(i,7).toString();
                        } catch (Exception e) {
                            tglhasil="";
                        }
                        Sequel.menyimpan("temporary_permintaan_radiologi","'0','"+
                            tabMode3.getValueAt(i,0).toString()+"','"+
                            tabMode3.getValueAt(i,1).toString()+"','"+
                            tabMode3.getValueAt(i,2).toString()+"','"+
                            tabMode3.getValueAt(i,3).toString()+"','"+
                            tabMode3.getValueAt(i,4).toString()+"','"+
                            tglsampel+"','"+
                            tabMode3.getValueAt(i,6).toString()+"','"+
                            tglhasil+"','"+
                            tabMode3.getValueAt(i,8).toString()+"','"+
                            tabMode3.getValueAt(i,9).toString()+"','"+
                            tabMode3.getValueAt(i,10).toString()+"','"+
                            tabMode3.getValueAt(i,11).toString()+"','','','','','','','','','','','','','','','','','','','','','','','','',''","Periksa Lab"); 
                    }
                    
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars",akses.getnamars());
                    param.put("alamatrs",akses.getalamatrs());
                    param.put("kotars",akses.getkabupatenrs());
                    param.put("propinsirs",akses.getpropinsirs());
                    param.put("kontakrs",akses.getkontakrs());
                    param.put("emailrs",akses.getemailrs());
                    param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                    Valid.MyReport("rptLapPermintaanRadiologi3.jasper","report","::[ Data Permintaan Radiologi ]::",param);
                }
            }           
        }            
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt,BtnAll,BtnAll);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        WindowAmbilSampel.dispose();
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            WindowAmbilSampel.dispose();
            dispose();
        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

private void tbRadiologiRalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRadiologiRalanMouseClicked
    if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbRadiologiRalanMouseClicked

private void tbRadiologiRalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRadiologiRalanKeyPressed
   if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbRadiologiRalanKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        pilihTab();
    }//GEN-LAST:event_formWindowOpened

    private void TabRawatJalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatJalanMouseClicked
        TeksKosong();
        pilihRalan();
    }//GEN-LAST:event_TabRawatJalanMouseClicked

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowAmbilSampel.dispose();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnSimpan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan4ActionPerformed
        String cariakses = Sequel.cariIsi("select IFNULL(nik,'') from pegawai where nik=?",akses.getkode());
        if(cariakses.equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Akun anda tidak terdaftar...!!!!");
        }else{
            if(TabPilihRawat.getSelectedIndex()==0){
                if(tbRadiologiRalan.getSelectedRow()!= -1){
                    if(tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),0).toString().trim().equals("")){
                        Valid.textKosong(TanggalPulang,"No.Permintaan");
                    }else{
                        try {                    
                            koneksi.setAutoCommit(false);
                            if(Sequel.menyimpantf2("sampel_darah","?,?,?,?,?","Pemeriksaan Darah",5,new String[]{
                                    tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),0).toString(),
                                    akses.getkode(),TxJenisTabung.getText(),
                                    Valid.SetTgl(TanggalPulang.getSelectedItem()+""),
                                    TanggalPulang.getSelectedItem().toString().substring(11,19)                        
                                })==true){   
                                tbRadiologiRalan.setValueAt(Valid.SetTgl(TanggalPulang.getSelectedItem()+""),tbRadiologiRalan.getSelectedRow(),14);
                                tbRadiologiRalan.setValueAt(TanggalPulang.getSelectedItem().toString().substring(11,19),tbRadiologiRalan.getSelectedRow(),15);
                                WindowAmbilSampel.dispose();
                            }   

                            koneksi.setAutoCommit(true);                    
                        } catch (Exception e) {
                            System.out.println("Periksa darah ralan, error: "+e);
                        }   
                    }
                }else{            
                    JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data permintaan...!!!!");
                    TCari.requestFocus();
                } 
            }else if(TabPilihRawat.getSelectedIndex()==1){
                if(tbRadiologiRanap.getSelectedRow()!= -1){
                    if(tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),0).toString().trim().equals("")){
                        Valid.textKosong(TanggalPulang,"No.Permintaan");
                    }else{
//                        System.out.println("akses.getkode():"+akses.getkode());
                        try {                    
                            koneksi.setAutoCommit(false);
                            if(Sequel.menyimpantf2("sampel_darah","?,?,?,?,?","Pemeriksaan Darah",5,new String[]{
                                    tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),0).toString(),
                                    akses.getkode(),TxJenisTabung.getText(),
                                    Valid.SetTgl(TanggalPulang.getSelectedItem()+""),
                                    TanggalPulang.getSelectedItem().toString().substring(11,19)                        
                                })==true){   
                                tbRadiologiRanap.setValueAt(Valid.SetTgl(TanggalPulang.getSelectedItem()+""),tbRadiologiRanap.getSelectedRow(),14);
                                tbRadiologiRanap.setValueAt(TanggalPulang.getSelectedItem().toString().substring(11,19),tbRadiologiRanap.getSelectedRow(),15);
                                WindowAmbilSampel.dispose();
                            }   

                            koneksi.setAutoCommit(true);                    
                        } catch (Exception e) {
                            System.out.println("Periksa darah ranap, error: "+e);
                        } 
                    }
                }else{            
                    JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data permintaan...!!!!");
                    TCari.requestFocus();
                } 
            }  
        }
    }//GEN-LAST:event_BtnSimpan4ActionPerformed

    private void BtnSeek3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek3ActionPerformed
        dokter.isCek();
        dokter.TCari.requestFocus();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnSeek3ActionPerformed

    private void BtnSeek4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek4ActionPerformed
        poli.isCek();
        poli.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnSeek4ActionPerformed

    private void BtnSeek5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek5ActionPerformed
        dokter.isCek();
        dokter.TCari.requestFocus();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnSeek5ActionPerformed

    private void BtnSeek6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek6ActionPerformed
        ruang.isCek();
        ruang.emptTeks();
        ruang.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        ruang.setLocationRelativeTo(internalFrame1);
        ruang.setVisible(true);
    }//GEN-LAST:event_BtnSeek6ActionPerformed

    private void TabPilihRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPilihRawatMouseClicked
        TeksKosong();
        pilihTab();
    }//GEN-LAST:event_TabPilihRawatMouseClicked

    private void tbRadiologiRanapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRadiologiRanapMouseClicked
        if(tabMode3.getRowCount()!=0){
            try {
                getData2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRadiologiRanapMouseClicked

    private void tbRadiologiRanapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRadiologiRanapKeyPressed
        if(tabMode3.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData2();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRadiologiRanapKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        aktif=true;
    }//GEN-LAST:event_formWindowActivated

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        aktif=false;
    }//GEN-LAST:event_formWindowDeactivated

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        isMenu();
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void BtnCetakHasilDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakHasilDarahActionPerformed
        String lokasi;
        if(TabPilihRawat.getSelectedIndex()==0){
            if(tbRadiologiRalan.getSelectedRow()!= -1){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                if(NoPermintaan.trim().equals("")){
                    Valid.textKosong(TCari,"No.Permintaan");
                }else{   
                    Map<String, Object> param = new HashMap<>();
                    param.put("noorder",tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),0).toString());
                    param.put("norawat",tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),1).toString());
                    param.put("tglpermintaan",tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),3).toString());
                    param.put("jampermintaan",tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),4).toString());
                    param.put("dokter",tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),7).toString());
                    param.put("namars",akses.getnamars());
                    param.put("alamatrs",akses.getalamatrs());
                    param.put("kotars",akses.getkabupatenrs());
                    param.put("propinsirs",akses.getpropinsirs());
                    param.put("kontakrs",akses.getkontakrs());
                    param.put("emailrs",akses.getemailrs());   
                    param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                    try {
                        final InputStream is = new URL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/penggajian/ttd/"+tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),7).toString()+".png").openStream();
                        param.put("ttddokter",is); 
                    } catch (MalformedURLException ex) {
                        param.put("ttddokter","");
//                             System.out.println("r:");
//                        Logger.getLogger(DlgCariPermintaanDarah.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        param.put("ttddokter","");
//                        System.out.println("r2:");
//                        Logger.getLogger(DlgCariPermintaanDarah.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",KodeDokter);
                    param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+DokterPerujuk+"\nID "+(finger.equals("")?KodeDokter:finger)+"\n"+Valid.SetTgl3(Permintaan)); 
//                    System.out.println("g:"+param);
                    Valid.MyReport("rptPermintaanDarahUTDRS.jasper","report","::[ Permintaan Darah UTDRS ]::",param);            
                }
                this.setCursor(Cursor.getDefaultCursor());
            }else{            
                JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data permintaan...!!!!");
                TCari.requestFocus();
            } 
        }else if(TabPilihRawat.getSelectedIndex()==1){
            if(tbRadiologiRanap.getSelectedRow()!= -1){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                if(NoPermintaan.trim().equals("")){
                    Valid.textKosong(TCari,"No.Permintaan");
                }else{
                    Map<String, Object> param = new HashMap<>();
                    param.put("noorder",tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),0).toString());
                    param.put("norawat",tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),1).toString());
                    param.put("tglpermintaan",tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),3).toString());
                    param.put("jampermintaan",tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),4).toString());
                    param.put("dokter",tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),7).toString());
                    param.put("namars",akses.getnamars());
                    param.put("alamatrs",akses.getalamatrs());
                    param.put("kotars",akses.getkabupatenrs());
                    param.put("propinsirs",akses.getpropinsirs());
                    param.put("kontakrs",akses.getkontakrs());
                    param.put("emailrs",akses.getemailrs());   
                    param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                    try {
                        final InputStream is = new URL("http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/penggajian/ttd/"+tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),7).toString()+".png").openStream();
                        param.put("ttddokter",is); 
                    } catch (MalformedURLException ex) {
                        param.put("ttddokter","");
//                             System.out.println("r:");
//                        Logger.getLogger(DlgCariPermintaanDarah.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        param.put("ttddokter","");
//                        System.out.println("r2:");
//                        Logger.getLogger(DlgCariPermintaanDarah.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",KodeDokter);
                    param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+DokterPerujuk+"\nID "+(finger.equals("")?KodeDokter:finger)+"\n"+Valid.SetTgl3(Permintaan)); 
                    Valid.MyReport("rptPermintaanDarahUTDRS.jasper","report","::[ Permintaan Darah UTDRS ]::",param);            
                }
                this.setCursor(Cursor.getDefaultCursor());
            }else{            
                JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data permintaan...!!!!");
                TCari.requestFocus();
            } 
        }
    }//GEN-LAST:event_BtnCetakHasilDarahActionPerformed

    private void BtnBarcodePermintaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBarcodePermintaanActionPerformed
        if(TabPilihRawat.getSelectedIndex()==0){
            if(tbRadiologiRalan.getSelectedRow()!= -1){
                if(tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),0).toString().trim().equals("")){
                    Valid.textKosong(TCari,"No.Permintaan");
                }else{ 
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    Map<String, Object> param = new HashMap<>();
                    norm=Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat=? ",tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),1).toString());
                    param.put("nama",Sequel.cariIsi("select pasien.nm_pasien from pasien where pasien.no_rkm_medis=? ",norm));
                    param.put("alamat",Sequel.cariIsi("select date_format(tgl_lahir,'%d/%m/%Y') from pasien where no_rkm_medis=?",norm));
                    param.put("norm",norm);
                    param.put("parameter","%"+TCari.getText().trim()+"%");     
                    param.put("namars",akses.getnamars());
                    param.put("alamatrs",akses.getalamatrs());
                    param.put("kotars",akses.getkabupatenrs());
                    param.put("propinsirs",akses.getpropinsirs());
                    param.put("kontakrs",akses.getkontakrs());
                    param.put("emailrs",akses.getemailrs());   
                    Valid.MyReportqry("rptBarcodePermintaanRadiologi.jasper","report","::[ Barcode No.Permintaan Radiologi ]::",
                            "select noorder from permintaan_radiologi where no_rawat='"+tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),1).toString()+"'",param); 
                    this.setCursor(Cursor.getDefaultCursor());
                } 
            }else{            
                JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data permintaan...!!!!");
                TCari.requestFocus();
            } 
        }else if(TabPilihRawat.getSelectedIndex()==1){
            if(tbRadiologiRanap.getSelectedRow()!= -1){
                if(tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),0).toString().trim().equals("")){
                    Valid.textKosong(TCari,"No.Permintaan");
                }else{ 
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    Map<String, Object> param = new HashMap<>();
                    norm=Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat=? ",tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),1).toString());
                    param.put("nama",Sequel.cariIsi("select pasien.nm_pasien from pasien where pasien.no_rkm_medis=? ",norm));
                    param.put("alamat",Sequel.cariIsi("select date_format(tgl_lahir,'%d/%m/%Y') from pasien where no_rkm_medis=?",norm));
                    param.put("norm",norm);
                    param.put("parameter","%"+TCari.getText().trim()+"%");     
                    param.put("namars",akses.getnamars());
                    param.put("alamatrs",akses.getalamatrs());
                    param.put("kotars",akses.getkabupatenrs());
                    param.put("propinsirs",akses.getpropinsirs());
                    param.put("kontakrs",akses.getkontakrs());
                    param.put("emailrs",akses.getemailrs());   
                    Valid.MyReportqry("rptBarcodePermintaanRadiologi.jasper","report","::[ Barcode No.Permintaan Radiologi ]::",
                            "select noorder from permintaan_radiologi where no_rawat='"+tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),1).toString()+"'",param); 
                    this.setCursor(Cursor.getDefaultCursor());
                } 
            }else{            
                JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data permintaan...!!!!");
                TCari.requestFocus();
            } 
        }
    }//GEN-LAST:event_BtnBarcodePermintaanActionPerformed

    private void TabRawatInapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatInapMouseClicked
        TeksKosong();
        pilihRanap();
    }//GEN-LAST:event_TabRawatInapMouseClicked

    private void ppSampelDarahBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSampelDarahBtnPrintActionPerformed
        
        if(TabPilihRawat.getSelectedIndex()==0){
            if(TabRawatJalan.getSelectedIndex()==0){
                if(tbRadiologiRalan.getSelectedRow()!= -1){
                    if(!Sequel.cariIsi("select tgl_terima from sampel_darah where noorder=? ",tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),0).toString()).equals("")){
                        JOptionPane.showMessageDialog(null,"Maaf, sudah diambil sampel...!!!!");
                    } else 
                    if(tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),0).toString().trim().equals("")){
                        Valid.textKosong(TCari,"No.Permintaan");
                    }else{ 
                        TanggalPulang.setDate(new Date());
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
                        WindowAmbilSampel.setLocationRelativeTo(internalFrame1);
                        WindowAmbilSampel.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                        TeksKosong();
                    }
                }else{            
                    JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data permintaan...!!!!");
                    TCari.requestFocus();
                }   
            }else if(TabRawatJalan.getSelectedIndex()==1){
                JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih Data Permintaan...!!!!");
                TabRawatJalan.setSelectedIndex(0);
                TCari.requestFocus();
            } 
        }else if(TabPilihRawat.getSelectedIndex()==1){
            if(TabRawatInap.getSelectedIndex()==0){
                if(tbRadiologiRanap.getSelectedRow()!= -1){
                    if(!Sequel.cariIsi("select tgl_terima from sampel_darah where noorder=? ",tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),0).toString()).equals("")){
                        JOptionPane.showMessageDialog(null,"Maaf, sudah diambil sampel...!!!!");
                    } else 
                    if(tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),0).toString().trim().equals("")){
                        Valid.textKosong(TCari,"No.Permintaan");
                    }else{ 
                        TanggalPulang.setDate(new Date());
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
                        WindowAmbilSampel.setLocationRelativeTo(internalFrame1);
                        WindowAmbilSampel.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                        TeksKosong();
                    }
                }else{            
                    JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data permintaan...!!!!");
                    TCari.requestFocus();
                }   
            }else if(TabRawatInap.getSelectedIndex()==1){
                JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih Data Permintaan...!!!!");
                TabRawatInap.setSelectedIndex(0);
                TCari.requestFocus();
            } 
        }     
    }//GEN-LAST:event_ppSampelDarahBtnPrintActionPerformed

    private void ppPemeriksaanDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPemeriksaanDarahActionPerformed
        if(TabPilihRawat.getSelectedIndex()==0){
            if(TabRawatJalan.getSelectedIndex()==0){
                if(tbRadiologiRalan.getSelectedRow()!= -1){
                    if(tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),0).toString().trim().equals("")){
                        Valid.textKosong(TCari,"No.Permintaan");
                    }else if(tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),14).toString().trim().equals("")){
                        JOptionPane.showMessageDialog(null,"Maaf, belum diambil sampel...!!!!");
                    }else{                     
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        DlgPeriksaDarah dlgro=new DlgPeriksaDarah(null,false);
                        dlgro.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                        dlgro.setLocationRelativeTo(internalFrame1);
                        dlgro.emptTeks();
                        dlgro.isCek();
                        dlgro.setNoRm(NoRawat,"Ralan",tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),0).toString());
                        dlgro.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                }else{            
                    JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data permintaan...!!!!");
                    TCari.requestFocus();
                } 
            }else if(TabRawatJalan.getSelectedIndex()==1){
                JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih Data Permintaan...!!!!");
                TabRawatJalan.setSelectedIndex(0);
                TCari.requestFocus();
            }
        }else if(TabPilihRawat.getSelectedIndex()==1){
            if(TabRawatInap.getSelectedIndex()==0){
                if(tbRadiologiRanap.getSelectedRow()!= -1){
                    if(tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),0).toString().trim().equals("")){
                        Valid.textKosong(TCari,"No.Permintaan");
                    }else if(tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),14).toString().trim().equals("")){
                        JOptionPane.showMessageDialog(null,"Maaf, belum diambil sampel...!!!!");
                    }else{                     
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        DlgPeriksaDarah dlgro=new DlgPeriksaDarah(null,false);
                        dlgro.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                        dlgro.setLocationRelativeTo(internalFrame1);
                        dlgro.emptTeks();
                        dlgro.isCek();
                        dlgro.setNoRm(NoRawat,"Ranap",tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),0).toString());
                        dlgro.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                }else{            
                    JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data permintaan...!!!!");
                    TCari.requestFocus();
                } 
            }else if(TabRawatInap.getSelectedIndex()==1){
                JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih Data Permintaan...!!!!");
                TabRawatInap.setSelectedIndex(0);
                TCari.requestFocus();
            }
        } 
    }//GEN-LAST:event_ppPemeriksaanDarahActionPerformed

    private void ppHapusPermintaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppHapusPermintaanActionPerformed
        if(TabPilihRawat.getSelectedIndex()==0){
            if(TabRawatJalan.getSelectedIndex()==0){
                if(tbRadiologiRalan.getSelectedRow()!= -1){
                    if(tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),0).toString().trim().equals("")){
                        Valid.textKosong(TCari,"No.Permintaan");
                    }else{
                        if(Sampel.equals("")||akses.getkode().equals("Admin Utama")){
                            if(Sequel.cariInteger("select count(noorder) from hasil_pemeriksaan_darah where noorder=?",tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),0).toString())>0){
                                JOptionPane.showMessageDialog(null,"Maaf, Tidak boleh dihapus karena sudah ada tindakan yang sudah dibayar.\nSilahkan hubungi kasir...!!!!");
                            }else{
                                Sequel.meghapus("permintaan_darah","noorder",tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),0).toString());
                                tampil();
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Maaf, Sudah dilakukan pengambilan sampel...!!!!");
                        }                     
                    }
                }else{            
                    JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data permintaan...!!!!");
                    TCari.requestFocus();
                }
            }else if(TabRawatJalan.getSelectedIndex()==1){
                JOptionPane.showMessageDialog(null,"Hanya bisa dilakukan hapus di Data Permintaan..!!!");
                TabRawatJalan.setSelectedIndex(0);
                TCari.requestFocus();
            } 
        }else if(TabPilihRawat.getSelectedIndex()==1){
            if(TabRawatInap.getSelectedIndex()==0){
                if(tbRadiologiRanap.getSelectedRow()!= -1){
                    if(tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),0).toString().trim().equals("")){
                        Valid.textKosong(TCari,"No.Permintaan");
                    }else{
                        if(Sampel.equals("")||akses.getkode().equals("Admin Utama")){
                            if(Sequel.cariInteger("select count(noorder) from hasil_pemeriksaan_darah where noorder=?",tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),0).toString())>0){
                                JOptionPane.showMessageDialog(null,"Maaf, Tidak boleh dihapus karena sudah ada tindakan yang sudah dibayar.\nSilahkan hubungi kasir...!!!!");
                            }else{
                                Sequel.meghapus("permintaan_darah","noorder",tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),0).toString());
                                tampil3();
                            } 
                        }else{
                            JOptionPane.showMessageDialog(null,"Maaf, Sudah dilakukan pengambilan sampel...!!!!");
                        }                    
                    }
                }else{            
                    JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data permintaan...!!!!");
                    TCari.requestFocus();
                }
            }else if(TabRawatInap.getSelectedIndex()==1){
                JOptionPane.showMessageDialog(null,"Hanya bisa dilakukan hapus di Data Permintaan..!!!");
                TabRawatInap.setSelectedIndex(0);
                TCari.requestFocus();
            } 
        }           
    }//GEN-LAST:event_ppHapusPermintaanActionPerformed

    private void TxJenisTabungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxJenisTabungActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxJenisTabungActionPerformed

    private void TxJenisTabungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxJenisTabungKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxJenisTabungKeyPressed

    private void ppBerikanDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBerikanDarahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ppBerikanDarahActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCariPermintaanDarah dialog = new DlgCariPermintaanDarah(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBarcodePermintaan;
    private widget.Button BtnCari;
    private widget.Button BtnCetakHasilDarah;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSeek3;
    private widget.Button BtnSeek4;
    private widget.Button BtnSeek5;
    private widget.Button BtnSeek6;
    private widget.Button BtnSimpan4;
    private widget.CekBox ChkAccor;
    private widget.TextBox CrDokter;
    private widget.TextBox CrDokter2;
    private widget.TextBox CrPoli;
    private widget.PanelBiasa FormMenu;
    private widget.TextBox Kamar;
    private widget.Label LCount;
    private javax.swing.JMenu MnHapusData;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane ScrollMenu;
    private widget.TextBox TCari;
    private javax.swing.JTabbedPane TabPilihRawat;
    private javax.swing.JTabbedPane TabRawatInap;
    private javax.swing.JTabbedPane TabRawatJalan;
    private widget.Tanggal TanggalPulang;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.TextBox TxJenisTabung;
    private javax.swing.JDialog WindowAmbilSampel;
    private widget.ComboBox cmbStatus;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label18;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi1;
    private javax.swing.JMenuItem ppBerikanDarah;
    private javax.swing.JMenuItem ppHapusPermintaan;
    private javax.swing.JMenuItem ppPemeriksaanDarah;
    private javax.swing.JMenuItem ppSampelDarah;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane3;
    private widget.Table tbRadiologiRalan;
    private widget.Table tbRadiologiRanap;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            semua=CrDokter.getText().trim().equals("")&&CrPoli.getText().trim().equals("")&&TCari.getText().trim().equals("");
            ps=koneksi.prepareStatement(
                    "SELECT\n" +
                    "	permintaan_darah.*, \n" +
                    "	reg_periksa.no_rkm_medis, \n" +
                    "	pasien.nm_pasien, \n" +
                    "	pasien.no_ktp, \n" +
                    "	pasien.jk, \n" +
                    "	pasien.tgl_lahir, \n" +
                    "	dokter.kd_dokter, \n" +
                    "	dokter.nm_dokter, \n" +
                    "	penjab.kd_pj, \n" +
                    "	penjab.png_jawab\n" +
                    "FROM\n" +
                    "	permintaan_darah\n" +
                    "	INNER JOIN\n" +
                    "	reg_periksa\n" +
                    "	ON \n" +
                    "		permintaan_darah.no_rawat = reg_periksa.no_rawat\n" +
                    "	INNER JOIN\n" +
                    "	dokter\n" +
                    "	ON \n" +
                    "		permintaan_darah.dokter_perujuk = dokter.kd_dokter \n" +
                    "	INNER JOIN\n" +
                    "	pasien\n" +
                    "	ON \n" +
                    "		reg_periksa.no_rkm_medis = pasien.no_rkm_medis\n" +
                    "	INNER JOIN\n" +
                    "	penjab\n" +
                    "	ON \n" +
                    "		pasien.kd_pj = penjab.kd_pj AND\n" +
                    "		reg_periksa.kd_pj = penjab.kd_pj "+
                    "where permintaan_darah.status='ralan' and permintaan_darah.tgl_permintaan between ? and ? "+
                    (semua?"":"and dokter.nm_dokter like ? and "+
                    "(permintaan_darah.noorder like ? or permintaan_darah.no_rawat like ? or reg_periksa.no_rkm_medis like ? "+
                    "or pasien.nm_pasien like ? or dokter.nm_dokter like ? or penjab.png_jawab like ?) ")+
                    "order by permintaan_darah.tgl_permintaan,permintaan_darah.jam_permintaan desc");
            try {
                ps.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                if(!semua){
                    ps.setString(3,"%"+CrDokter.getText().trim()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                    ps.setString(7,"%"+TCari.getText()+"%");
                    ps.setString(8,"%"+TCari.getText()+"%");
                    ps.setString(9,"%"+TCari.getText()+"%");
                } 
                    
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("noorder"),
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis")+" "+rs.getString("nm_pasien")+" ["+rs.getString("tgl_lahir")+"]",
                        rs.getString("tgl_permintaan"),
                        rs.getString("jam_permintaan"),
                        rs.getString("tgl_diperlukan"),
                        rs.getString("jam_diperlukan"),
                        rs.getString("dokter_perujuk"),
                        rs.getString("nm_dokter"),
                        rs.getString("kamar")+": "+rs.getString("nama_kamar"),
                        rs.getString("diagnosa_klinis"),
                        rs.getString("alasan_transfusi"),
                        rs.getString("kd_pj"),
                        rs.getString("png_jawab"),
                        Sequel.cariIsi("select tgl_terima from sampel_darah where noorder=? ",rs.getString("noorder")),
                        Sequel.cariIsi("select jam_terima from sampel_darah where noorder=? ",rs.getString("noorder"))
                    });
                    
                    tabMode.addRow(new Object[]{
                        "","","Darah Lengkap(Whole Blood)",rs.getString("darah_lengkap"),"","","","","","","","","","","",""
                    });
                    tabMode.addRow(new Object[]{
                        "","","Plasma (biasa)",rs.getString("plasma"),"","","","","","","","","","","",""
                    });
                    tabMode.addRow(new Object[]{
                        "","","Plasma (konvalesen Covid)",rs.getString("plasma_konvalensen_covid"),"","","","","","","","","","","",""
                    });
                    tabMode.addRow(new Object[]{
                        "","","Packed Red Cell (PRC)",rs.getString("packed_red_cell"),"","","","","","","","","","","",""
                    });
                    tabMode.addRow(new Object[]{
                        "","","Thrombocyte Concentrate (TC)",rs.getString("thrombocytr_concentrate"),"","","","","","","","","","","",""
                    });
                    tabMode.addRow(new Object[]{
                        "","","Lainnya",rs.getString("lainnya"),"","","","","","","","","","","",""
                    });
                        
                }
                rs.last();
                LCount.setText(""+rs.getRow());
            } catch (Exception e) {
                System.out.println("Notif Tbl Permintaan Ralan: "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif Tbl Permintaan Ralan: "+e);
        }        
    }
    
    private void getData() {
        if(tbRadiologiRalan.getSelectedRow()!= -1){
            NoPermintaan=tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),0).toString();
            NoRawat=tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),1).toString();
            Pasien=tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),2).toString();
            Permintaan=tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),3).toString();
            JamPermintaan=tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),4).toString();
            Sampel=tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),5).toString();
            JamSampel=tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),6).toString();
            Hasil=tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),7).toString();
            JamHasil=tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),8).toString();
            KodeDokter=tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),9).toString();
            DokterPerujuk=tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),10).toString();
            Ruang=tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),11).toString();
            InformasiTambahan=tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),12).toString();
            Klinis=tbRadiologiRalan.getValueAt(tbRadiologiRalan.getSelectedRow(),13).toString();
        }
    }
    
    private void getData2() {
        if(tbRadiologiRanap.getSelectedRow()!= -1){
            NoPermintaan=tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),0).toString();
            NoRawat=tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),1).toString();
            Pasien=tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),2).toString();
            Permintaan=tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),3).toString();
            JamPermintaan=tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),4).toString();
            Sampel=tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),5).toString();
            JamSampel=tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),6).toString();
            Hasil=tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),7).toString();
            JamHasil=tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),8).toString();
            KodeDokter=tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),9).toString();
            DokterPerujuk=tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),10).toString();
            Ruang=tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),11).toString();
            InformasiTambahan=tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),12).toString();
            Klinis=tbRadiologiRanap.getValueAt(tbRadiologiRanap.getSelectedRow(),13).toString();
        }
    }
    
    public void isCek(){
        BtnCetakHasilDarah.setEnabled(akses.getpermintaan_lab());
        ppSampelDarah.setEnabled(akses.getutd_penyerahan_darah());
        ppPemeriksaanDarah.setEnabled(akses.getutd_penyerahan_darah());
        ppBerikanDarah.setEnabled(akses.getutd_penyerahan_darah());
        ppHapusPermintaan.setEnabled(akses.getkode().equals("Admin Utama"));
        BtnPrint.setEnabled(akses.getpermintaan_lab());
    }
    
    public void setPasien(String pasien, String status){
        TCari.setText(pasien);
        switch (status) {
            case "Ranap":
                TabPilihRawat.setSelectedIndex(1);
                break;
            default:
                TabPilihRawat.setSelectedIndex(0);
                break;
        }
    }

    public void pilihTab(){
        if(TabPilihRawat.getSelectedIndex()==0){
            pilihRalan();
        }else if(TabPilihRawat.getSelectedIndex()==1){
            pilihRanap();
        }
    }
    
    public void pilihRalan(){
        if(TabRawatJalan.getSelectedIndex()==0){
            tampil();
        }
    }
    
    public void pilihRanap(){
        if(TabRawatInap.getSelectedIndex()==0){
            tampil3();
        }
    }
    
    private void tampil3() {
        Valid.tabelKosong(tabMode3);
        try {
            semua=CrDokter2.getText().trim().equals("")&&Kamar.getText().trim().equals("")&&TCari.getText().trim().equals("");
            if (cmbStatus.getSelectedIndex() == 1){
                statuspulang = " And kamar_inap.stts_pulang = '-' ";
            }
                ps=koneksi.prepareStatement(
                    "SELECT\n" +
                    "	permintaan_darah.*, \n" +
                    "	reg_periksa.no_rkm_medis, \n" +
                    "	pasien.nm_pasien, \n" +
                    "	pasien.no_ktp, \n" +
                    "	pasien.jk, \n" +
                    "	pasien.tgl_lahir, \n" +
                    "	dokter.kd_dokter, \n" +
                    "	dokter.nm_dokter, \n" +
                    "	penjab.kd_pj, \n" +
                    "	penjab.png_jawab,\n" +
                    "	kamar_inap.stts_pulang\n" +
                    "FROM\n" +
                    "	permintaan_darah\n" +
                    "	INNER JOIN\n" +
                    "	reg_periksa\n" +
                    "	ON \n" +
                    "		permintaan_darah.no_rawat = reg_periksa.no_rawat\n" +
                    "	INNER JOIN\n" +
                    "	dokter\n" +
                    "	ON \n" +
                    "		permintaan_darah.dokter_perujuk = dokter.kd_dokter \n" +
                    "	INNER JOIN\n" +
                    "	pasien\n" +
                    "	ON \n" +
                    "		reg_periksa.no_rkm_medis = pasien.no_rkm_medis\n" +
                    "	INNER JOIN\n" +
                    "	penjab\n" +
                    "	ON \n" +
                    "		pasien.kd_pj = penjab.kd_pj \n" +
                    "	INNER JOIN\n" +
                    "	kamar_inap\n" +
                    "	ON \n" +
                    "		reg_periksa.no_rawat = kamar_inap.no_rawat "+
                    "where permintaan_darah.status='ranap' and permintaan_darah.tgl_permintaan between ? and ? "+statuspulang+
                    (semua?"":"and dokter.nm_dokter like ? and "+
                    "(permintaan_darah.noorder like ? or permintaan_darah.no_rawat like ? or reg_periksa.no_rkm_medis like ? "+
                    "or pasien.nm_pasien like ? or dokter.nm_dokter like ? or penjab.png_jawab like ?) ")+
                    "order by permintaan_darah.tgl_permintaan,permintaan_darah.jam_permintaan,kamar_inap.tgl_masuk desc");
            
                
            try {
                ps.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                if(!semua){
                    ps.setString(3,"%"+CrDokter.getText().trim()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                    ps.setString(7,"%"+TCari.getText()+"%");
                    ps.setString(8,"%"+TCari.getText()+"%");
                    ps.setString(9,"%"+TCari.getText()+"%");
                } 
                    
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode3.addRow(new String[]{
                        rs.getString("noorder"),
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis")+" "+rs.getString("nm_pasien")+" ["+rs.getString("tgl_lahir")+"]",
                        rs.getString("tgl_permintaan"),
                        rs.getString("jam_permintaan"),
                        rs.getString("tgl_diperlukan"),
                        rs.getString("jam_diperlukan"),
                        rs.getString("dokter_perujuk"),
                        rs.getString("nm_dokter"),
                        rs.getString("kamar")+": "+rs.getString("nama_kamar"),
                        rs.getString("diagnosa_klinis"),
                        rs.getString("alasan_transfusi"),
                        rs.getString("kd_pj"),
                        rs.getString("png_jawab"),
                        Sequel.cariIsi("select tgl_terima from sampel_darah where noorder=? ",rs.getString("noorder")),
                        Sequel.cariIsi("select jam_terima from sampel_darah where noorder=? ",rs.getString("noorder"))
                    });
                    
                    tabMode3.addRow(new Object[]{
                        "","","Darah Lengkap(Whole Blood)",rs.getString("darah_lengkap"),"","","","","","","","","","","",""
                    });
                    tabMode3.addRow(new Object[]{
                        "","","Plasma (biasa)",rs.getString("plasma"),"","","","","","","","","","","",""
                    });
                    tabMode3.addRow(new Object[]{
                        "","","Plasma (konvalesen Covid)",rs.getString("plasma_konvalensen_covid"),"","","","","","","","","","","",""
                    });
                    tabMode3.addRow(new Object[]{
                        "","","Packed Red Cell (PRC)",rs.getString("packed_red_cell"),"","","","","","","","","","","",""
                    });
                    tabMode3.addRow(new Object[]{
                        "","","Thrombocyte Concentrate (TC)",rs.getString("thrombocytr_concentrate"),"","","","","","","","","","","",""
                    });
                    tabMode3.addRow(new Object[]{
                        "","","Lainnya",rs.getString("lainnya"),"","","","","","","","","","","",""
                    });
                }
                rs.last();
                LCount.setText(""+rs.getRow());
            } catch (Exception e) {
                System.out.println("Notif Tbl Permintaan Ranap: "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif Tbl Permintaan Ranap: "+e);
        }        
    }
   
    private void jam(){
        ActionListener taskPerformer = (ActionEvent e) -> {
            if(aktif==true){
                nol_detik = "";
                now = Calendar.getInstance().getTime();
                nilai_detik = now.getSeconds();
                if (nilai_detik <= 9) {
                    nol_detik = "0";
                }

                detik = nol_detik + Integer.toString(nilai_detik);
                if(detik.equals("05")){
                    permintaanbaru=0;
                    if(formalarm.contains("ralan")){
                        tampil();
                        for(i=0;i<tbRadiologiRalan.getRowCount();i++){
                            if((!tbRadiologiRalan.getValueAt(i,0).toString().equals(""))&&tbRadiologiRalan.getValueAt(i,5).toString().equals("")){
                                permintaanbaru++;
                            }
                        }
                    }

                    if(formalarm.contains("ranap")){
                        tampil3();
                        for(i=0;i<tbRadiologiRanap.getRowCount();i++){
                            if((!tbRadiologiRanap.getValueAt(i,0).toString().equals(""))&&tbRadiologiRanap.getValueAt(i,5).toString().equals("")){
                                permintaanbaru++;
                            }
                        }
                    }

                    if(permintaanbaru>0){
                        try {
                            music = new BackgroundMusic("./suara/alarm.mp3");
                            music.start();
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }
 
    private void TeksKosong() {
//        NoPermintaan="";
//        NoRawat="";
//        Pasien="";
//        Permintaan="";
//        JamPermintaan="";
//        Sampel="";
//        JamSampel="";
//        Hasil="";
//        JamHasil="";
//        KodeDokter="";
//        DokterPerujuk="";
//        Ruang="";
//        InformasiTambahan="";
//        Klinis="";
        TxJenisTabung.setText("");
    }
    
    private void isMenu(){
        if(ChkAccor.isSelected()==true){
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(240,HEIGHT));
            FormMenu.setVisible(true); 
            ChkAccor.setVisible(true);
        }else if(ChkAccor.isSelected()==false){  
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(15,HEIGHT));
            FormMenu.setVisible(false);    
            ChkAccor.setVisible(true);
        }
    }
}
