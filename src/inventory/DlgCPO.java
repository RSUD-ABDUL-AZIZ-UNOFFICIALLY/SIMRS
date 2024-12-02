/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */

package inventory;

import fungsi.WarnaTable;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class DlgCPO extends javax.swing.JDialog {

    private DefaultTableModel tabMode,tabMode2;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    public DlgCariJenis jenis = new DlgCariJenis(null, false);
    public DlgCariKategori kategori = new DlgCariKategori(null, false);
    public DlgCariGolongan golongan = new DlgCariGolongan(null, false);
    public DlgCariSatuan satuan = new DlgCariSatuan(null, false);
    public DlgCariIndustriFarmasi industri=new DlgCariIndustriFarmasi(null,false);
    private PreparedStatement ps,ps2,psracikan;
    private ResultSet rs,rs2,rsracikan;
    private int i = 0;
    public String noRetur="",status="",carivalue="",carivalue2="",caristts="",caritglResep="";
    public String aktifkanbatch="no",pengaturanharga=Sequel.cariIsi("select set_harga_obat.setharga from set_harga_obat");
    
    public DlgCPO(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        WindowPemberian.setSize(480,123);
        WindowHentikan.setSize(326,123);
        WindowCatatan.setSize(530,123);
        Object[] headers = new Object[]{
            "","","","","", "Kd Obat/Racikan","Nama Obat/Racikan", "Satuan","Jumlah",
            "Aturan Pakai","sisa"
        };

        tabMode = new DefaultTableModel(null,headers) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbObat.setModel(tabMode);

        tbObat.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < headers.length; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i <= 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else {
                column.setPreferredWidth(100);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());   
        
        Object[] headers2 = new Object[]{
             "","","Ket","Status","Tgl","Jam","Jumlah","nip","pegawai"
        };
        tabMode2 = new DefaultTableModel(null,headers2) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbRiwayatObat.setModel(tabMode2);

        tbRiwayatObat.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbRiwayatObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < headers2.length; i++) {
            TableColumn column = tbRiwayatObat.getColumnModel().getColumn(i);
            if (i <= 2) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setPreferredWidth(100);
            } else {
                column.setPreferredWidth(100);
            }
        }
        tbRiwayatObat.setDefaultRenderer(Object.class, new WarnaTable());   
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup = new javax.swing.JPopupMenu();
        ppPemberian = new javax.swing.JMenuItem();
        ppPenghentian = new javax.swing.JMenuItem();
        ppBatalHentikan = new javax.swing.JMenuItem();
        ppCatatan = new javax.swing.JMenuItem();
        Popup1 = new javax.swing.JPopupMenu();
        ppBatalBerikan = new javax.swing.JMenuItem();
        WindowPemberian = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        BtnSimpan4 = new widget.Button();
        jLabel26 = new widget.Label();
        TxTglBeri = new widget.Tanggal();
        TxJmlhObat = new widget.TextBox();
        jLabel27 = new widget.Label();
        WindowHentikan = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        BtnCloseIn5 = new widget.Button();
        BtnSimpan5 = new widget.Button();
        TxTglBeri1 = new widget.Tanggal();
        jLabel29 = new widget.Label();
        WindowCatatan = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        BtnCloseIn6 = new widget.Button();
        BtnSimpan6 = new widget.Button();
        TxCatatan = new widget.TextBox();
        jLabel30 = new widget.Label();
        BtnGanti6 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        scrollPane1 = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel4 = new javax.swing.JPanel();
        panelisi4 = new widget.panelisi();
        label12 = new widget.Label();
        lbName = new widget.Label();
        lbKod = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        label13 = new widget.Label();
        lbCatatan = new widget.Label();
        Scroll = new widget.ScrollPane();
        tbRiwayatObat = new widget.Table();
        jPanel2 = new javax.swing.JPanel();
        panelisi2 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        panelisi1 = new widget.panelisi();
        RtglResep = new widget.RadioButton();
        DTPCari1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();

        Popup.setName("Popup"); // NOI18N

        ppPemberian.setBackground(new java.awt.Color(255, 255, 254));
        ppPemberian.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPemberian.setForeground(new java.awt.Color(50, 50, 50));
        ppPemberian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPemberian.setText("Beri Obat");
        ppPemberian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPemberian.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPemberian.setName("ppPemberian"); // NOI18N
        ppPemberian.setPreferredSize(new java.awt.Dimension(200, 26));
        ppPemberian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPemberianBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppPemberian);

        ppPenghentian.setBackground(new java.awt.Color(255, 255, 254));
        ppPenghentian.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPenghentian.setForeground(new java.awt.Color(50, 50, 50));
        ppPenghentian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPenghentian.setText("Hentikan Obat");
        ppPenghentian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPenghentian.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPenghentian.setName("ppPenghentian"); // NOI18N
        ppPenghentian.setPreferredSize(new java.awt.Dimension(200, 26));
        ppPenghentian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPenghentianBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppPenghentian);

        ppBatalHentikan.setBackground(new java.awt.Color(255, 255, 254));
        ppBatalHentikan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBatalHentikan.setForeground(new java.awt.Color(50, 50, 50));
        ppBatalHentikan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBatalHentikan.setText("Batal Hentikan");
        ppBatalHentikan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBatalHentikan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBatalHentikan.setName("ppBatalHentikan"); // NOI18N
        ppBatalHentikan.setPreferredSize(new java.awt.Dimension(200, 26));
        ppBatalHentikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBatalHentikanBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppBatalHentikan);

        ppCatatan.setBackground(new java.awt.Color(255, 255, 254));
        ppCatatan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCatatan.setForeground(new java.awt.Color(50, 50, 50));
        ppCatatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCatatan.setText("Catatan");
        ppCatatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCatatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCatatan.setName("ppCatatan"); // NOI18N
        ppCatatan.setPreferredSize(new java.awt.Dimension(200, 26));
        ppCatatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCatatanBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppCatatan);

        Popup1.setName("Popup1"); // NOI18N

        ppBatalBerikan.setBackground(new java.awt.Color(255, 255, 254));
        ppBatalBerikan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBatalBerikan.setForeground(new java.awt.Color(50, 50, 50));
        ppBatalBerikan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBatalBerikan.setText("Batal Berikan");
        ppBatalBerikan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBatalBerikan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBatalBerikan.setName("ppBatalBerikan"); // NOI18N
        ppBatalBerikan.setPreferredSize(new java.awt.Dimension(200, 26));
        ppBatalBerikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBatalBerikanBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(ppBatalBerikan);

        WindowPemberian.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowPemberian.setName("WindowPemberian"); // NOI18N
        WindowPemberian.setUndecorated(true);
        WindowPemberian.setResizable(false);
        WindowPemberian.setSize(new java.awt.Dimension(100, 100));

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Update Waktu Pemberian Obat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setPreferredSize(new java.awt.Dimension(530, 180));
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
        BtnCloseIn4.setBounds(320, 70, 100, 30);

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
        BtnSimpan4.setBounds(220, 70, 100, 30);

        jLabel26.setText("Jumlah :");
        jLabel26.setName("jLabel26"); // NOI18N
        internalFrame5.add(jLabel26);
        jLabel26.setBounds(270, 30, 60, 23);

        TxTglBeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-11-2024 08:19:42" }));
        TxTglBeri.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TxTglBeri.setName("TxTglBeri"); // NOI18N
        TxTglBeri.setOpaque(false);
        TxTglBeri.setPreferredSize(new java.awt.Dimension(95, 23));
        internalFrame5.add(TxTglBeri);
        TxTglBeri.setBounds(110, 32, 150, 23);

        TxJmlhObat.setName("TxJmlhObat"); // NOI18N
        TxJmlhObat.setPreferredSize(new java.awt.Dimension(400, 23));
        TxJmlhObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxJmlhObatKeyPressed(evt);
            }
        });
        internalFrame5.add(TxJmlhObat);
        TxJmlhObat.setBounds(330, 30, 90, 23);

        jLabel27.setText("Tanggal & Jam :");
        jLabel27.setName("jLabel27"); // NOI18N
        internalFrame5.add(jLabel27);
        jLabel27.setBounds(6, 32, 100, 23);

        WindowPemberian.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        WindowPemberian.getAccessibleContext().setAccessibleParent(internalFrame1);

        WindowHentikan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowHentikan.setName("WindowHentikan"); // NOI18N
        WindowHentikan.setUndecorated(true);
        WindowHentikan.setResizable(false);
        WindowHentikan.setSize(new java.awt.Dimension(100, 100));

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Update Waktu Penghentian Obat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setPreferredSize(new java.awt.Dimension(326, 123));
        internalFrame6.setLayout(null);

        BtnCloseIn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn5.setMnemonic('U');
        BtnCloseIn5.setText("Tutup");
        BtnCloseIn5.setToolTipText("Alt+U");
        BtnCloseIn5.setName("BtnCloseIn5"); // NOI18N
        BtnCloseIn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn5ActionPerformed(evt);
            }
        });
        internalFrame6.add(BtnCloseIn5);
        BtnCloseIn5.setBounds(210, 70, 100, 30);

        BtnSimpan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan5.setMnemonic('S');
        BtnSimpan5.setText("Simpan");
        BtnSimpan5.setToolTipText("Alt+S");
        BtnSimpan5.setName("BtnSimpan5"); // NOI18N
        BtnSimpan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan5ActionPerformed(evt);
            }
        });
        internalFrame6.add(BtnSimpan5);
        BtnSimpan5.setBounds(30, 70, 100, 30);

        TxTglBeri1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-11-2024 08:19:42" }));
        TxTglBeri1.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TxTglBeri1.setName("TxTglBeri1"); // NOI18N
        TxTglBeri1.setOpaque(false);
        TxTglBeri1.setPreferredSize(new java.awt.Dimension(95, 23));
        internalFrame6.add(TxTglBeri1);
        TxTglBeri1.setBounds(110, 32, 150, 23);

        jLabel29.setText("Tanggal & Jam :");
        jLabel29.setName("jLabel29"); // NOI18N
        internalFrame6.add(jLabel29);
        jLabel29.setBounds(6, 32, 100, 23);

        WindowHentikan.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        WindowCatatan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCatatan.setName("WindowCatatan"); // NOI18N
        WindowCatatan.setUndecorated(true);
        WindowCatatan.setResizable(false);
        WindowCatatan.setSize(new java.awt.Dimension(100, 100));

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Catatan Waktu Pemberian Obat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setPreferredSize(new java.awt.Dimension(530, 180));
        internalFrame7.setLayout(null);

        BtnCloseIn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn6.setMnemonic('U');
        BtnCloseIn6.setText("Tutup");
        BtnCloseIn6.setToolTipText("Alt+U");
        BtnCloseIn6.setName("BtnCloseIn6"); // NOI18N
        BtnCloseIn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn6ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnCloseIn6);
        BtnCloseIn6.setBounds(320, 70, 100, 30);

        BtnSimpan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan6.setMnemonic('S');
        BtnSimpan6.setText("Simpan");
        BtnSimpan6.setToolTipText("Alt+S");
        BtnSimpan6.setName("BtnSimpan6"); // NOI18N
        BtnSimpan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan6ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnSimpan6);
        BtnSimpan6.setBounds(120, 70, 100, 30);

        TxCatatan.setName("TxCatatan"); // NOI18N
        TxCatatan.setPreferredSize(new java.awt.Dimension(400, 23));
        TxCatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxCatatanKeyPressed(evt);
            }
        });
        internalFrame7.add(TxCatatan);
        TxCatatan.setBounds(110, 30, 310, 23);

        jLabel30.setText("Catatan :");
        jLabel30.setName("jLabel30"); // NOI18N
        internalFrame7.add(jLabel30);
        jLabel30.setBounds(6, 32, 100, 23);

        BtnGanti6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti6.setMnemonic('S');
        BtnGanti6.setText("Ganti");
        BtnGanti6.setToolTipText("Alt+S");
        BtnGanti6.setName("BtnGanti6"); // NOI18N
        BtnGanti6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGanti6ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnGanti6);
        BtnGanti6.setBounds(220, 70, 100, 30);

        WindowCatatan.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Catatan Pemberian Obat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)), "::[ Daftar Obat&BHP ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 102));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setComponentPopupMenu(Popup);
        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(Popup);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbObatKeyReleased(evt);
            }
        });
        scrollPane1.setViewportView(tbObat);

        jPanel3.add(scrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)), "::[ Riwayat Pemberian ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(350, 102));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi4.setBorder(null);
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 80));

        label12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label12.setText("Obat, BHP :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(68, 23));

        lbName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbName.setText("Example. nama obat");
        lbName.setName("lbName"); // NOI18N
        lbName.setPreferredSize(new java.awt.Dimension(68, 23));

        lbKod.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbKod.setText("Example. Kode Obat");
        lbKod.setName("lbKod"); // NOI18N
        lbKod.setPreferredSize(new java.awt.Dimension(68, 23));

        jSeparator1.setName("jSeparator1"); // NOI18N

        label13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label13.setText("Catatan :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(68, 23));

        lbCatatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCatatan.setText("Example. 08.00, 16.00, 24.00");
        lbCatatan.setName("lbCatatan"); // NOI18N
        lbCatatan.setPreferredSize(new java.awt.Dimension(68, 23));

        javax.swing.GroupLayout panelisi4Layout = new javax.swing.GroupLayout(panelisi4);
        panelisi4.setLayout(panelisi4Layout);
        panelisi4Layout.setHorizontalGroup(
            panelisi4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(panelisi4Layout.createSequentialGroup()
                .addGroup(panelisi4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelisi4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbKod, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
                    .addGroup(panelisi4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbCatatan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelisi4Layout.setVerticalGroup(
            panelisi4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelisi4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelisi4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbKod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelisi4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCatatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.add(panelisi4, java.awt.BorderLayout.PAGE_END);

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbRiwayatObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRiwayatObat.setComponentPopupMenu(Popup1);
        tbRiwayatObat.setName("tbRiwayatObat"); // NOI18N
        tbRiwayatObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatObatMouseClicked(evt);
            }
        });
        tbRiwayatObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwayatObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbRiwayatObat);

        jPanel4.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(816, 100));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi2.setBackground(new java.awt.Color(255, 150, 255));
        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(label9);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(400, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi2.add(TCari);

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
        panelisi2.add(BtnCari);

        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(label10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi2.add(LCount);

        jPanel2.add(panelisi2, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        RtglResep.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        RtglResep.setText("Tanggal Resep :");
        RtglResep.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        RtglResep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        RtglResep.setName("RtglResep"); // NOI18N
        RtglResep.setPreferredSize(new java.awt.Dimension(120, 23));
        panelisi1.add(RtglResep);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-11-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPCari1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari1ItemStateChanged(evt);
            }
        });
        DTPCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari1KeyPressed(evt);
            }
        });
        panelisi1.add(DTPCari1);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(25, 23));
        panelisi1.add(jLabel22);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-11-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPCari2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari2ItemStateChanged(evt);
            }
        });
        DTPCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari2KeyPressed(evt);
            }
        });
        panelisi1.add(DTPCari2);

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

        jPanel2.add(panelisi1, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbObat.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil4(noRetur,status);   
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        String val="";
        if(tabMode.getRowCount()!=0){
            try {
                lbKod.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
                lbName.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
                
                val=Sequel.cariIsi("select IFNULL(catatan,'') from catatan_cpo where "
                                + "no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()+"' and "
                                + "stts_regis='"+status+"' and "
                                + "stts_racik='"+tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()+"' and "
                                + "kode='"+tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()+"' and "
                                + "nama='"+tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()+"' "
                                + "");
                
                lbCatatan.setText(val);                
                tampil5(
                    tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(),1).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(),2).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(),3).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(),5).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(),6).toString(),
                    status
                );
            } catch (java.lang.NullPointerException e) {
            }
        } 
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tabMode.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.setText("");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        RtglResep.setSelected(false);
        TCari.setText("");
        lbKod.setText("Example.Kode Obat");
        lbName.setText("Example.Nama Obat");
        lbCatatan.setText("Example.08.00, 16.00, 24.00");
        tampil4(noRetur,status);    
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnAllActionPerformed(null);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnAll, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void ppPenghentianBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPenghentianBtnPrintActionPerformed
        if(tabMode.getRowCount()!=0){
            try {
                
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
                WindowHentikan.setLocationRelativeTo(internalFrame1);
                WindowHentikan.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_ppPenghentianBtnPrintActionPerformed

    private void ppPemberianBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPemberianBtnPrintActionPerformed
        if(tabMode.getRowCount()!=0){
            try {
                
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
                WindowPemberian.setLocationRelativeTo(internalFrame1);
                WindowPemberian.setVisible(true);
                TxJmlhObat.requestFocus();
                this.setCursor(Cursor.getDefaultCursor());
            } catch (java.lang.NullPointerException e) {
            }
        }    

    }//GEN-LAST:event_ppPemberianBtnPrintActionPerformed

private void ppBatalHentikanBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBatalHentikanBtnPrintActionPerformed
   
}//GEN-LAST:event_ppBatalHentikanBtnPrintActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(!akses.getform().equals("DlgReturJual")){
            tampil4(noRetur,status);  
        }
    }//GEN-LAST:event_formWindowOpened

    private void tbObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyReleased
        
    }//GEN-LAST:event_tbObatKeyReleased

    private void DTPCari1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari1ItemStateChanged
        RtglResep.setSelected(true);
    }//GEN-LAST:event_DTPCari1ItemStateChanged

    private void DTPCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari1KeyPressed
        RtglResep.setSelected(true);
    }//GEN-LAST:event_DTPCari1KeyPressed

    private void DTPCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari2KeyPressed
        RtglResep.setSelected(true);
    }//GEN-LAST:event_DTPCari2KeyPressed

    private void DTPCari2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari2ItemStateChanged
        RtglResep.setSelected(true);
    }//GEN-LAST:event_DTPCari2ItemStateChanged

    private void tbRiwayatObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatObatMouseClicked
        
    }//GEN-LAST:event_tbRiwayatObatMouseClicked

    private void tbRiwayatObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatObatKeyPressed
        
    }//GEN-LAST:event_tbRiwayatObatKeyPressed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowPemberian.dispose();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnSimpan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan4ActionPerformed
        String kode="",nama="",cariObat="";
        float jml1,jml2=0;
        boolean result;
            if(tbObat.getSelectedRow()!= -1){
                if(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString().trim().equals("")){
                    Valid.textKosong(TxTglBeri,"No.Permintaan");
                }else{
                    kode=tbObat.getValueAt(tbObat.getSelectedRow(),5).toString();
                    nama=tbObat.getValueAt(tbObat.getSelectedRow(),6).toString();
                    cariObat=tbObat.getValueAt(tbObat.getSelectedRow(),10).toString();
                    jml2 = Float.parseFloat(cariObat);
                    jml1 = Float.parseFloat(TxJmlhObat.getText());
                    if(jml1<=jml2){
                        result=simpan(kode,nama,cariObat,"pemberian",TxJmlhObat.getText());
                        if (result==true) {
                            WindowPemberian.dispose();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Maaf, stok tidak mencukupi...!!!!");
                    }
                    
                }
            }else{
                JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data permintaan...!!!!");
                TCari.requestFocus();
            }
    }//GEN-LAST:event_BtnSimpan4ActionPerformed

    private void TxJmlhObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxJmlhObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxJmlhObatKeyPressed

    private void ppBatalBerikanBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBatalBerikanBtnPrintActionPerformed
        if (!akses.getkode().equals(tbRiwayatObat.getValueAt(tbRiwayatObat.getSelectedRow(),7).toString())) {
        } else {
            if(Sequel.meghapusQ("DELETE FROM catatan_pemberian_obat WHERE \n"
                    + "nip='"+akses.getkode()+"'\n"
                    + "AND kode='"+tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()+"'\n"
                    + "AND nama='"+tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()+"'\n"
                    + "AND no_rawat='"+tbRiwayatObat.getValueAt(tbRiwayatObat.getSelectedRow(),0).toString()+"'\n"
                    + "AND status='"+tbRiwayatObat.getValueAt(tbRiwayatObat.getSelectedRow(),3).toString()+"'\n"
                    + "AND tgl_perintah='"+tbRiwayatObat.getValueAt(tbRiwayatObat.getSelectedRow(),4).toString()+"'\n"
                    + "AND jam1='"+tbRiwayatObat.getValueAt(tbRiwayatObat.getSelectedRow(),5).toString()+"'\n"
                    + "AND jml='"+tbRiwayatObat.getValueAt(tbRiwayatObat.getSelectedRow(),6).toString()+"'\n"
                            + " ")==true){
                    if(tbRiwayatObat.getSelectedRow()!= -1){ 
                        tabMode2.removeRow(tbRiwayatObat.getSelectedRow());
                    }
            }else{
                JOptionPane.showMessageDialog(null,"Gagal, tidak dapat diproses...!!!!");
            }
        }
    }//GEN-LAST:event_ppBatalBerikanBtnPrintActionPerformed

    private void BtnCloseIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn5ActionPerformed
        WindowHentikan.dispose();
    }//GEN-LAST:event_BtnCloseIn5ActionPerformed

    private void BtnSimpan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan5ActionPerformed
        String kode="",nama="",cariObat="";
        boolean result;
        float jml1,jml2=0;
            if(tbObat.getSelectedRow()!= -1){
                if(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString().trim().equals("")){
                    Valid.textKosong(TxTglBeri,"No.Permintaan");
                }else{
                    kode=tbObat.getValueAt(tbObat.getSelectedRow(),5).toString();
                    nama=tbObat.getValueAt(tbObat.getSelectedRow(),6).toString();
                    cariObat=tbObat.getValueAt(tbObat.getSelectedRow(),10).toString();

                    result=simpan(kode,nama,cariObat,"penghentian","0");
                    if (result==true) {
                        WindowHentikan.dispose();
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data permintaan...!!!!");
                TCari.requestFocus();
            }
    }//GEN-LAST:event_BtnSimpan5ActionPerformed

    private void ppCatatanBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCatatanBtnPrintActionPerformed
        if(tabMode.getRowCount()!=0){
            try {
                
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
                WindowCatatan.setLocationRelativeTo(internalFrame1);
                WindowCatatan.setVisible(true);
                TxCatatan.requestFocus();
                TxCatatan.setText(lbCatatan.getText());
                this.setCursor(Cursor.getDefaultCursor());
            } catch (java.lang.NullPointerException e) {
            }
        } 
    }//GEN-LAST:event_ppCatatanBtnPrintActionPerformed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowCatatan.dispose();
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void BtnSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan6ActionPerformed
        String kode="",nama="";
        boolean result;
            if(tbObat.getSelectedRow()!= -1){
                if(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString().trim().equals("")){
                    Valid.textKosong(TxTglBeri,"No.Permintaan");
                }else{
                    kode=tbObat.getValueAt(tbObat.getSelectedRow(),5).toString();
                    nama=tbObat.getValueAt(tbObat.getSelectedRow(),6).toString();
                    
                    result=simpanCatatatn(kode,nama);
                    if (result==true) {
                        WindowCatatan.dispose();
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data permintaan...!!!!");
                TCari.requestFocus();
            }
    }//GEN-LAST:event_BtnSimpan6ActionPerformed

    private void TxCatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxCatatanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxCatatanKeyPressed

    private void BtnGanti6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGanti6ActionPerformed
        String kode="",nama="";
        boolean result;
            if(tbObat.getSelectedRow()!= -1){
                if(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString().trim().equals("")){
                    Valid.textKosong(TxTglBeri,"No.Permintaan");
                }else{
                    kode=tbObat.getValueAt(tbObat.getSelectedRow(),5).toString();
                    nama=tbObat.getValueAt(tbObat.getSelectedRow(),6).toString();
                    result=gantiCatatatn(kode,nama);
                    if (result==true) {
                        WindowCatatan.dispose();
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data permintaan...!!!!");
                TCari.requestFocus();
            }
    }//GEN-LAST:event_BtnGanti6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCPO dialog = new DlgCPO(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCloseIn5;
    private widget.Button BtnCloseIn6;
    private widget.Button BtnGanti6;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan4;
    private widget.Button BtnSimpan5;
    private widget.Button BtnSimpan6;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private javax.swing.JPopupMenu Popup;
    private javax.swing.JPopupMenu Popup1;
    private widget.RadioButton RtglResep;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TxCatatan;
    private widget.TextBox TxJmlhObat;
    private widget.Tanggal TxTglBeri;
    private widget.Tanggal TxTglBeri1;
    private javax.swing.JDialog WindowCatatan;
    private javax.swing.JDialog WindowHentikan;
    private javax.swing.JDialog WindowPemberian;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.Label jLabel22;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private widget.Label label10;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label9;
    private widget.Label lbCatatan;
    private widget.Label lbKod;
    private widget.Label lbName;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private widget.panelisi panelisi4;
    private javax.swing.JMenuItem ppBatalBerikan;
    private javax.swing.JMenuItem ppBatalHentikan;
    private javax.swing.JMenuItem ppCatatan;
    private javax.swing.JMenuItem ppPemberian;
    private javax.swing.JMenuItem ppPenghentian;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbObat;
    private widget.Table tbRiwayatObat;
    // End of variables declaration//GEN-END:variables

    public void tampil(){
        JOptionPane.showMessageDialog(null,"Resep Tidak Ada/Ulangi Proses Awal..!!");
                    
    }
    
    public boolean simpan(String kode, String nama, String sisa, String stts, String jmlh) {
        float jml2=0,jml1=0;
        boolean result=false;
        if(Sequel.saveTRy("catatan_pemberian_obat","?,?,?,?,?,?,?,?,?,?,?","Data CPO",11,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),2).toString(),
            tbObat.getValueAt(tbObat.getSelectedRow(),3).toString(),
            kode,
            nama,
            tbObat.getValueAt(tbObat.getSelectedRow(),9).toString(),
            jmlh,
            Valid.SetTgl(TxTglBeri.getSelectedItem()+""),
            TxTglBeri.getSelectedItem().toString().substring(11,19),
            stts,
            status,
            akses.getkode()
        })==true){
            tbObatMouseClicked(null);
            jml2 = Float.parseFloat(sisa);
                        jml1 = Float.parseFloat(jmlh);
            tbObat.setValueAt((jml2-jml1),tbObat.getSelectedRow(), 10);
            result=true;
        }else{
            result=false;
        }
        return result;
    }
    
    public boolean simpanCatatatn(String kode, String nama) {
        boolean result=false;
        if(Sequel.saveTRy("catatan_cpo","?,?,?,?,?,?","Data CPO",6,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),2).toString(),
            tbObat.getValueAt(tbObat.getSelectedRow(),3).toString(),
            kode,
            nama,
            TxCatatan.getText(),
            status
        })==true){
            lbCatatan.setText(TxCatatan.getText());
            result=true;
        }else{
            result=false;
        }
        return result;
    }
    
    public boolean gantiCatatatn(String kode, String nama) {
        boolean result=false;
        if(Sequel.mengedittf("catatan_cpo","no_rawat=? \n" +
                    "AND stts_racik=? \n" +
                    "AND kode=? \n" +
                    "AND nama=? \n" +
                    "AND stts_regis=? ","catatan=? ",6,
                new String[]{
                    TxCatatan.getText(),
                    tbObat.getValueAt(tbObat.getSelectedRow(),2).toString(),
                    tbObat.getValueAt(tbObat.getSelectedRow(),3).toString(),
                    kode,
                    nama,
                    status
            })==true){
            lbCatatan.setText(TxCatatan.getText());
            result=true;
        }else{
            result=false;
        }
        return result;
    }
    
    public void tampil4(String NoRetur, String Status) {
        Valid.tabelKosong(tabMode);
        Valid.tabelKosong(tabMode2);//kosongkan list view
        this.noRetur=NoRetur;
        this.status=Status;
        
        if (!status.equals("")) {
            caristts=" AND detail_pemberian_obat.status='"+status+"' ";
        }else{
            caristts="";
        }
        
        if (!TCari.equals("")) {
            carivalue=" AND databarang.nama_brng LIKE '%"+TCari.getText().trim()+"%' ";
            carivalue2=" AND obat_racikan.nama_racik LIKE '%"+TCari.getText().trim()+"%' ";
        }else{
            carivalue="";
            carivalue2="";
        }
        
        float rtt=0,rtt1=0;
        String rtt2="0";
        try{  
            ps=koneksi.prepareStatement("select reg_periksa.no_rawat FROM reg_periksa where reg_periksa.no_rawat=? "
                    
            );
            try{
                ps.setString(1,noRetur);
                    
                rs=ps.executeQuery();
                while(rs.next()){
                                  
                    ps2=koneksi.prepareStatement("SELECT\n" +
                            "	databarang.kode_brng, \n" +
                            "	databarang.nama_brng, \n" +
                            "	detail_pemberian_obat.no_rawat, \n" +
                            "	detail_pemberian_obat.tgl_perawatan, \n" +
                            "	detail_pemberian_obat.jam, \n" +
                            "	SUM(detail_pemberian_obat.jml) AS jml, \n" +
                            "	kodesatuan.satuan\n" +
                            "FROM\n" +
                            "	detail_pemberian_obat\n" +
                            "	INNER JOIN\n" +
                            "	databarang\n" +
                            "	ON \n" +
                            "		detail_pemberian_obat.kode_brng = databarang.kode_brng\n" +
                            "	INNER JOIN\n" +
                            "	kodesatuan\n" +
                            "	ON \n" +
                            "		databarang.kode_sat = kodesatuan.kode_sat\n" +
                            "WHERE\n" +
                            "	detail_pemberian_obat.no_rawat = ? \n " +
                            caristts+
                            carivalue+
                            " GROUP BY\n" +
                            "	databarang.kode_brng ASC");
                    try {
                        ps2.setString(1,rs.getString("no_rawat"));
//                        ps2.setString(2,status);
//                        ps2.setString(3,"%"+TCari.getText().trim()+"%");

                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            rtt2=Sequel.cariIsi("select IFNULL(sum(jml),0) from catatan_pemberian_obat where "
                                + "no_rawat='"+rs2.getString("no_rawat")+"' "
                                + "AND stts_regis='"+status+"' "
                                + "AND kode='"+rs2.getString("kode_brng")+"' "
                                + "AND nama='"+rs2.getString("nama_brng")+"' "
                                + "");
                            rtt1=Float.parseFloat(rtt2);
                            rtt=Float.parseFloat(rs2.getString("jml"));                            
                            tabMode.addRow(new String[]{
                                rs2.getString("tgl_perawatan"),rs2.getString("jam"),rs2.getString("no_rawat"),
                                "tidak","",rs2.getString("kode_brng"),rs2.getString("nama_brng"),
                                rs2.getString("satuan"),rs2.getString("jml"),
                                Sequel.cariIsi("select aturan from aturan_pakai where tgl_perawatan='"+rs2.getString("tgl_perawatan")+"' and jam='"+rs2.getString("jam")+"' and no_rawat='"+rs.getString("no_rawat")+"' and kode_brng='"+rs2.getString("kode_brng")+"'"),
                                Float.toString(rtt-rtt1)
                            });
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    psracikan=koneksi.prepareStatement(
                            "select obat_racikan.no_racik,obat_racikan.nama_racik,"+
                            "obat_racikan.tgl_perawatan,obat_racikan.jam,obat_racikan.no_rawat,"+
                            "obat_racikan.kd_racik,metode_racik.nm_racik as metode,"+
                            "SUM(obat_racikan.jml_dr) AS jml_dr,obat_racikan.aturan_pakai,"+
                            "obat_racikan.keterangan from obat_racikan inner join metode_racik "+
                            "on obat_racikan.kd_racik=metode_racik.kd_racik where "+
                            "obat_racikan.no_rawat=? "+
//                            caristts+
                            carivalue2+
                            "GROUP BY nama_racik");
                    
                    try {
                        psracikan.setString(1,rs.getString("no_rawat"));
                        rsracikan=psracikan.executeQuery();
                        while(rsracikan.next()){
                            rtt2=Sequel.cariIsi("select IFNULL(sum(jml),0) from catatan_pemberian_obat where "
                                + "no_rawat='"+rsracikan.getString("no_rawat")+"' "
                                + "AND stts_regis='"+status+"' "
                                + "AND kode='"+rsracikan.getString("no_racik")+"' "
                                + "AND nama='"+rsracikan.getString("nama_racik")+"' "
                                + "");
                            rtt1=Float.parseFloat(rtt2);
                            rtt=Float.parseFloat(rsracikan.getString("jml_dr"));
                            tabMode.addRow(new String[]{
                                rsracikan.getString("tgl_perawatan"),rsracikan.getString("jam"),rsracikan.getString("no_rawat"),"ya","",rsracikan.getString("no_racik"),rsracikan.getString("nama_racik"),
                                rsracikan.getString("metode")+", Ket: "+rsracikan.getString("keterangan"),rsracikan.getString("jml_dr"),
                                rsracikan.getString("aturan_pakai"),
                                Float.toString(rtt-rtt1)
                            });
                            
                            
                        }
                    } catch (Exception e) {
                        System.out.println("Notif Racikan : "+e);
                    } finally{
                        if(rsracikan!=null){
                            rsracikan.close();
                        }
                        if(psracikan!=null){
                            psracikan.close();
                        }
                    }
                }                
                rs.last();
                LCount.setText(""+rs.getRow());
            } catch(Exception ex){
                System.out.println("Notifikasi : "+ex);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }                
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        } 
    }

    
    public void emptTeks() {
        TCari.setText("");
    }
    
    public void tampil5(String tgl, String jam,
            String norawat, String ssracik,
            String kdbarang, String nmbarang, String stts){
        Valid.tabelKosong(tabMode2);
        try{  
            ps=koneksi.prepareStatement("SELECT\n" +
                "	catatan_pemberian_obat.no_rawat, \n" +
                "	catatan_pemberian_obat.stts_racik, \n" +
                "	catatan_pemberian_obat.kode, \n" +
                "	catatan_pemberian_obat.nama, \n" +
                "	catatan_pemberian_obat.aturan_pakai, \n" +
                "	catatan_pemberian_obat.jml, \n" +
                "	catatan_pemberian_obat.tgl_perintah, \n" +
                "	catatan_pemberian_obat.jam1, \n" +
                "	catatan_pemberian_obat.`status`, \n" +
                "	catatan_pemberian_obat.stts_regis, \n" +
                "	catatan_pemberian_obat.nip, \n" +
                "	pegawai.nama AS nm_pegawai\n" +
                "FROM\n" +
                "	catatan_pemberian_obat\n" +
                "	INNER JOIN pegawai on catatan_pemberian_obat.nip=pegawai.nik\n" +
                "WHERE\n" +
                "	catatan_pemberian_obat.no_rawat = ? AND\n" +
                "	catatan_pemberian_obat.stts_racik = ? AND\n" +
                "	catatan_pemberian_obat.kode = ? AND\n" +
                "	catatan_pemberian_obat.nama = ? AND\n" +
                "	catatan_pemberian_obat.stts_regis = ?\n" +
                "ORDER BY\n" +
                "	catatan_pemberian_obat.tgl_perintah DESC, \n" +
                "	catatan_pemberian_obat.jam1 DESC"
                    
            );
            try{
                ps.setString(1,norawat);
                ps.setString(2,ssracik);
                ps.setString(3,kdbarang);
                ps.setString(4,nmbarang);
                ps.setString(5,stts);
                
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode2.addRow(new Object[]{
                        rs.getString("no_rawat"),
                        rs.getString("kode"),
                        rs.getString("stts_regis"),
                        rs.getString("status"),
                        rs.getString("tgl_perintah"),
                        rs.getString("jam1"),
                        rs.getString("jml"),
                        rs.getString("nip"),
                        rs.getString("nm_pegawai"),
                    });
                }                
                rs.last();
            } catch(Exception ex){
                System.out.println("Notifikasi : "+ex);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }                
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    public JTable getTable() {
        return tbObat;
    }
    
    public void isCek() {
        TCari.requestFocus(); 
    }

}
