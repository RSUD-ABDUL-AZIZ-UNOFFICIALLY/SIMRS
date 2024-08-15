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
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import restore.DlgRestoreObat;
import simrskhanza.DlgCariBangsal;

public class DlgBarangByResep extends javax.swing.JDialog {

    private DefaultTableModel tabMode;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    public DlgCariJenis jenis = new DlgCariJenis(null, false);
    public DlgCariKategori kategori = new DlgCariKategori(null, false);
    public DlgCariGolongan golongan = new DlgCariGolongan(null, false);
    public DlgCariSatuan satuan = new DlgCariSatuan(null, false);
    public DlgCariIndustriFarmasi industri=new DlgCariIndustriFarmasi(null,false);
    private DlgCariBangsal bangsal = new DlgCariBangsal(null, false);
    private PreparedStatement ps4;
    private ResultSet rs;
    private int i = 0;
    public String noRetur="",status="Ranap",carivalue="";
    public String aktifkanbatch="no",pengaturanharga=Sequel.cariIsi("select set_harga_obat.setharga from set_harga_obat");
    
    public DlgBarangByResep(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null,new Object[]{
            "P", "Kode Barang", "Nama Barang", "Kd.Sat Besar", "Nm.Satuan Besar",
            "Isi", "Kd.Sat Kecil", "Nm.Satuan Kecil","Kandungan","Jumlah",
            "Hrg.Beli(Rp)","Biaya Obat(Rp)","Total","Kadaluwarsa","No Batch",
            "No Faktur","Tgl.Validasi","Jam Validasi","Status"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Double.class,
                java.lang.Double.class,java.lang.Double.class,java.lang.Double.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbObat.setModel(tabMode);

        tbObat.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(85);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(68);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(37);
            } else if (i == 6) {
                column.setPreferredWidth(68);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(37);
            } else if (i == 9) {
                column.setPreferredWidth(120);
            } else if (i == 10) {
                column.setPreferredWidth(85);
            } else if (i == 11) {
                column.setPreferredWidth(85);
            } else if (i == 12) {
                column.setPreferredWidth(85);
            } else if (i == 13) {
                column.setPreferredWidth(85);
            } else if (i == 14) {
                column.setPreferredWidth(85);
            } else if (i == 15) {
                column.setPreferredWidth(85);
            } else if (i == 16) {
                column.setPreferredWidth(85);
            } else if (i == 17) {
                column.setPreferredWidth(85);
            } else if (i == 18) {
                column.setPreferredWidth(85);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());   
        
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
        ppBarcode = new javax.swing.JMenuItem();
        ppBarcodeItem = new javax.swing.JMenuItem();
        ppStok = new javax.swing.JMenuItem();
        ppStok2 = new javax.swing.JMenuItem();
        MnRestore = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        jPanel2 = new javax.swing.JPanel();
        panelisi2 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        panelisi1 = new widget.panelisi();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        scrollPane1 = new widget.ScrollPane();
        tbObat = new widget.Table();

        Popup.setName("Popup"); // NOI18N

        ppBarcode.setBackground(new java.awt.Color(255, 255, 254));
        ppBarcode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBarcode.setForeground(new java.awt.Color(50, 50, 50));
        ppBarcode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBarcode.setText("Barcode");
        ppBarcode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBarcode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBarcode.setName("ppBarcode"); // NOI18N
        ppBarcode.setPreferredSize(new java.awt.Dimension(200, 26));
        ppBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBarcodeBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppBarcode);

        ppBarcodeItem.setBackground(new java.awt.Color(255, 255, 254));
        ppBarcodeItem.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBarcodeItem.setForeground(new java.awt.Color(50, 50, 50));
        ppBarcodeItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBarcodeItem.setText("Barcode Perbarang");
        ppBarcodeItem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBarcodeItem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBarcodeItem.setName("ppBarcodeItem"); // NOI18N
        ppBarcodeItem.setPreferredSize(new java.awt.Dimension(200, 26));
        ppBarcodeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBarcodeItemBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppBarcodeItem);

        ppStok.setBackground(new java.awt.Color(255, 255, 254));
        ppStok.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppStok.setForeground(new java.awt.Color(50, 50, 50));
        ppStok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppStok.setText("Tampilkan Semua Stok");
        ppStok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppStok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppStok.setName("ppStok"); // NOI18N
        ppStok.setPreferredSize(new java.awt.Dimension(200, 26));
        ppStok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppStokBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppStok);

        ppStok2.setBackground(new java.awt.Color(255, 255, 254));
        ppStok2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppStok2.setForeground(new java.awt.Color(50, 50, 50));
        ppStok2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppStok2.setText("Tampilkan Stok Per Lokasi");
        ppStok2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppStok2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppStok2.setName("ppStok2"); // NOI18N
        ppStok2.setPreferredSize(new java.awt.Dimension(200, 26));
        ppStok2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppStok2BtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppStok2);

        MnRestore.setBackground(new java.awt.Color(255, 255, 254));
        MnRestore.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRestore.setForeground(new java.awt.Color(50, 50, 50));
        MnRestore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRestore.setText("Data Sampah");
        MnRestore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRestore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRestore.setName("MnRestore"); // NOI18N
        MnRestore.setPreferredSize(new java.awt.Dimension(200, 26));
        MnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRestoreActionPerformed(evt);
            }
        });
        Popup.add(MnRestore);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Obat, Alkes & BHP Medis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

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
        TCari.setText("");
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

    private void ppBarcodeBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBarcodeBtnPrintActionPerformed
        
    }//GEN-LAST:event_ppBarcodeBtnPrintActionPerformed

    private void ppBarcodeItemBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBarcodeItemBtnPrintActionPerformed
        

    }//GEN-LAST:event_ppBarcodeItemBtnPrintActionPerformed

private void ppStokBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppStokBtnPrintActionPerformed
   
}//GEN-LAST:event_ppStokBtnPrintActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if(!akses.getform().equals("DlgReturJual")){
            tampil4(noRetur,status);  
        }
    }//GEN-LAST:event_formWindowOpened

    private void ppStok2BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppStok2BtnPrintActionPerformed
        bangsal.isCek();
        bangsal.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        bangsal.setLocationRelativeTo(internalFrame1);
        bangsal.setVisible(true);
    }//GEN-LAST:event_ppStok2BtnPrintActionPerformed

    private void MnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRestoreActionPerformed
        DlgRestoreObat restore=new DlgRestoreObat(null,true);
        restore.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        restore.setLocationRelativeTo(internalFrame1);
        restore.setVisible(true);
    }//GEN-LAST:event_MnRestoreActionPerformed

    private void tbObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyReleased
        
    }//GEN-LAST:event_tbObatKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgBarangByResep dialog = new DlgBarangByResep(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnRestore;
    private javax.swing.JPopupMenu Popup;
    private widget.TextBox TCari;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JPanel jPanel2;
    private widget.Label label10;
    private widget.Label label9;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private javax.swing.JMenuItem ppBarcode;
    private javax.swing.JMenuItem ppBarcodeItem;
    private javax.swing.JMenuItem ppStok;
    private javax.swing.JMenuItem ppStok2;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil(){
        JOptionPane.showMessageDialog(null,"Resep Tidak Ada/Ulangi Proses Awal..!!");
                    
    }
    
    public void emptTeks() {
        TCari.setText("");
    }
    
    public void tampil4(String NoRetur, String Status) {
        this.noRetur=NoRetur;
        this.status=Status;
        if(!TCari.getText().equals("")){
            carivalue=" AND (databarang.nama_brng like '%" + TCari.getText().trim() + "%' or databarang.kode_brng like '%" + TCari.getText().trim() + "%' ) ";
        }else{
            carivalue="";
        }
        if(aktifkanbatch.equals("yes")){
            Valid.tabelKosong(tabMode);
            try {
                ps4 = koneksi.prepareStatement(
                        "select databarang.kode_brng, databarang.nama_brng,databarang.kode_satbesar,satuanbesar.satuan as satuanbesar, "
                        + " databarang.isi,databarang.kode_sat,kodesatuan.satuan,databarang.letak_barang,data_batch.dasar,data_batch.h_beli,"
                        + " data_batch.ralan,data_batch.kelas1,data_batch.kelas2,data_batch.kelas3,detail_pemberian_obat.no_batch,"
                        + " data_batch.utama,databarang.vip,data_batch.vvip,data_batch.beliluar,data_batch.jualbebas,"
                        + " data_batch.karyawan,databarang.stokminimal, databarang.kdjns,detail_pemberian_obat.no_faktur,"
                        + " jenis.nama,kapasitas,databarang.expire,databarang.kode_industri,industrifarmasi.nama_industri, "
                        + " databarang.kode_kategori,kategori_barang.nama as kategori,databarang.kode_golongan,golongan_barang.nama as golongan,"
                        + " detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.jam,detail_pemberian_obat.jml,"
                        + " detail_pemberian_obat.h_beli,detail_pemberian_obat.biaya_obat,detail_pemberian_obat.total,detail_pemberian_obat.status "
                        + " from data_batch inner join databarang on data_batch.kode_brng=databarang.kode_brng "
                        + " inner join kodesatuan on databarang.kode_sat=kodesatuan.kode_sat "
                        + " inner join kodesatuan as satuanbesar on databarang.kode_satbesar=satuanbesar.kode_sat "
                        + " inner join jenis on databarang.kdjns=jenis.kdjns "
                        + " inner join industrifarmasi on databarang.kode_industri=industrifarmasi.kode_industri "
                        + " inner join golongan_barang on databarang.kode_golongan=golongan_barang.kode "
                        + " inner join kategori_barang on databarang.kode_kategori=kategori_barang.kode "
                        + " inner join detail_pemberian_obat on detail_pemberian_obat.kode_brng=data_batch.kode_brng and detail_pemberian_obat.no_batch=data_batch.no_batch and detail_pemberian_obat.no_faktur=data_batch.no_faktur "
                        + " where detail_pemberian_obat.no_rawat=? AND detail_pemberian_obat.status=? AND databarang.status='1' "
                        +carivalue
                        + "group by databarang.kode_brng,detail_pemberian_obat.no_batch,detail_pemberian_obat.no_faktur order by detail_pemberian_obat.tgl_perawatan DESC,detail_pemberian_obat.jam DESC");
                try {
                    ps4.setString(1,NoRetur);
                    ps4.setString(2,Status);
                    rs = ps4.executeQuery();
                    while (rs.next()) {
                        tabMode.addRow(new Object[]{
                            false, rs.getString("kode_brng"),
                            rs.getString("nama_brng"),
                            rs.getString("kode_satbesar"),
                            rs.getString("satuanbesar"),
                            rs.getDouble("isi"),
                            rs.getString("kode_sat"),
                            rs.getString("satuan"),
                            rs.getDouble("kapasitas"),
                            rs.getDouble("jml"),
                            rs.getDouble("h_beli"),
                            rs.getDouble("biaya_obat"),
                            rs.getDouble("total"),
                            rs.getString("expire"),
                            rs.getString("no_batch"),
                            rs.getString("no_faktur"),
                            rs.getString("tgl_perawatan"),
                            rs.getString("jam"),
                            rs.getString("status")
                        });
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }finally{
                    if(rs != null){
                        rs.close();
                    }
                    if(ps4 != null){
                        ps4.close();
                    }
                }
                LCount.setText("" + tabMode.getRowCount());
                if(tabMode.getRowCount()==0){
                   tampil();
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }else{
            Valid.tabelKosong(tabMode);
            try {
                ps4 = koneksi.prepareStatement(
                        "select databarang.kode_brng, databarang.nama_brng,databarang.kode_satbesar,satuanbesar.satuan as satuanbesar, "
                        + " databarang.isi,databarang.kode_sat,kodesatuan.satuan,databarang.letak_barang,databarang.dasar,databarang.h_beli,"
                        + " databarang.ralan,databarang.kelas1,databarang.kelas2,databarang.kelas3,"
                        + " databarang.utama,databarang.vip,databarang.vvip,databarang.beliluar,databarang.jualbebas,"
                        + " databarang.karyawan,databarang.stokminimal, databarang.kdjns,"
                        + " jenis.nama,kapasitas,databarang.expire,databarang.kode_industri,industrifarmasi.nama_industri, "
                        + " databarang.kode_kategori,kategori_barang.nama as kategori,databarang.kode_golongan,golongan_barang.nama as golongan,"
                        + " detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.jam,detail_pemberian_obat.jml,"
                        + " detail_pemberian_obat.h_beli,detail_pemberian_obat.biaya_obat,detail_pemberian_obat.total,detail_pemberian_obat.status "
                        + " from databarang inner join kodesatuan on databarang.kode_sat=kodesatuan.kode_sat "
                        + " inner join kodesatuan as satuanbesar on databarang.kode_satbesar=satuanbesar.kode_sat "
                        + " inner join jenis on databarang.kdjns=jenis.kdjns "
                        + " inner join industrifarmasi on databarang.kode_industri=industrifarmasi.kode_industri "
                        + " inner join golongan_barang on databarang.kode_golongan=golongan_barang.kode "
                        + " inner join kategori_barang on databarang.kode_kategori=kategori_barang.kode "
                        + " inner join detail_pemberian_obat on detail_pemberian_obat.kode_brng=databarang.kode_brng "
                        + " where detail_pemberian_obat.no_rawat=? AND detail_pemberian_obat.status=? group by databarang.kode_brng order by detail_pemberian_obat.tgl_perawatan DESC,detail_pemberian_obat.jam DESC");
                try {
                    ps4.setString(1,NoRetur);
                    ps4.setString(2,Status);
                    rs = ps4.executeQuery();
                    while (rs.next()) {
                        tabMode.addRow(new Object[]{
                            false, rs.getString("kode_brng"),
                            rs.getString("nama_brng"),
                            rs.getString("kode_satbesar"),
                            rs.getString("satuanbesar"),
                            rs.getDouble("isi"),
                            rs.getString("kode_sat"),
                            rs.getString("satuan"),
                            rs.getDouble("kapasitas"),
                            rs.getDouble("jml"),
                            rs.getDouble("h_beli"),
                            rs.getDouble("biaya_obat"),
                            rs.getDouble("total"),
                            rs.getString("expire"),
                            rs.getString("no_batch"),
                            rs.getString("no_faktur"),
                            rs.getString("tgl_perawatan"),
                            rs.getString("jam"),
                            rs.getString("status")
                        });
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }finally{
                    if(rs != null){
                        rs.close();
                    }
                    if(ps4 != null){
                        ps4.close();
                    }
                }
                LCount.setText("" + tabMode.getRowCount());
                if(tabMode.getRowCount()==0){
                   tampil();
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }               
    }

    public JTable getTable() {
        return tbObat;
    }
    
    public void isCek() {
        TCari.requestFocus();
        if(akses.getkode().equals("Admin Utama")){
            MnRestore.setEnabled(true);
        }else{
            MnRestore.setEnabled(false);
        } 
    }

}
