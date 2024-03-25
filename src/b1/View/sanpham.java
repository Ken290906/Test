/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package b1.View;

import b1.View.chucnang.quetmaqr;
import b1.View.intefacee.interfacesp;
import b1.View.intefacee.iterface2;

import ViewModelSP.sanphamchitietviewmodel;
import ViewModelSP.sanphamviewmodel;
import b1.entity.Chatlieusp;
import b1.entity.Daysp;
import b1.entity.Dodaysp;
import b1.entity.DongSanPham;
import b1.entity.Matdesanpham;
import b1.entity.MauSanPham;
import b1.entity.SizeSanPham;
import b1.entity.ThuocTinh;
import b1.entity.chitietsanpham;

import b1.entity.hangsanxuat;
import b1.repository.chitietsanphamviewmodelRepo;
import b1.services.chatlieuservices;

import b1.services.chitietsanphamservices;
import b1.services.dayservices;
import b1.services.dodayservices;
import b1.services.dspservices;
import b1.services.hangsxservices;
import b1.services.matdeservices;
import b1.services.sizeservices;
import b1.services.tenmauservices;
import b1.services.thuoctinhservices;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.glxn.qrgen.image.ImageType;
import net.glxn.qrgen.QRCode;

/**
 *
 * @author DELL
 */
public class sanpham extends javax.swing.JInternalFrame {
//Table

    private DefaultTableModel bang3 = new DefaultTableModel();
    private List<sanphamviewmodel> listsanpham = new ArrayList<>();
    private DefaultTableModel bang = new DefaultTableModel();
    private chitietsanphamservices sps = new chitietsanphamservices();

    private DefaultTableModel bang2 = new DefaultTableModel();
    private chitietsanphamviewmodelRepo spr2 = new chitietsanphamviewmodelRepo();
    private List<chitietsanpham> listctsp = new ArrayList<>();
    interfacesp itf = new iterface2() {
    };
    private List<sanphamchitietviewmodel> listviewmodel = new ArrayList<>();
    private List<hangsanxuat> listhsx = new ArrayList<>();
    private hangsxservices hsxs = new hangsxservices();
    private List<DongSanPham> listdsp = new ArrayList<>();
    private dspservices dsps = new dspservices();
    private List<MauSanPham> listmsp = new ArrayList<>();
    private tenmauservices ms = new tenmauservices();
    private List<Dodaysp> listddsp = new ArrayList<>();
    private dodayservices dds = new dodayservices();
    private List<Chatlieusp> clsp = new ArrayList<>();
    private chatlieuservices cls = new chatlieuservices();
    private List<Matdesanpham> listmdsp = new ArrayList<>();
    private matdeservices mds = new matdeservices();
    private List<Daysp> listdaysp = new ArrayList<>();
    private dayservices ds = new dayservices();
    private List<SizeSanPham> listsize = new ArrayList<>();
    private sizeservices ss = new sizeservices();
    //Combobox
    private DefaultComboBoxModel combo1 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combo2 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combo3 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combo4 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combo5 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combo6 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combo7 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combo8 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combo9 = new DefaultComboBoxModel();
    private sanphamthem spt;
    //thuoctinhsp
    private List<ThuocTinh> listtt = new ArrayList<>();
    private thuoctinhservices tts = new thuoctinhservices();

    /**
     * Creates new form phu
     */
    public sanpham() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        bang = (DefaultTableModel) tblhienthisanpham.getModel();
        listsanpham = sps.getall();
        showdata(listsanpham);
//      
        listviewmodel = itf.getall();
        bang2 = (DefaultTableModel) tblhienthichitietsanpham.getModel();
        showdata2(listviewmodel);
//thuoctinhsp
        listtt = tts.getall();
        bang3 = (DefaultTableModel) tblthuoctinhsanpham.getModel();
        showdata3(listtt);
//
        txtsearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchSP();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchSP();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchSP();
            }

        });

        cbbhang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timkiem = cbbhang.getSelectedItem().toString();
                if (!timkiem.isEmpty()) {
                    List<sanphamchitietviewmodel> searchedList = searchHang(timkiem);
                    showdata2(searchedList);
                } else {
                    // If no manufacturer is selected, reload all products
                    listviewmodel = itf.getall();
                    showdata2(listviewmodel);
                }
            }
        });
        cbbphoimau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timkiemmau = cbbphoimau.getSelectedItem().toString();
                if (!timkiemmau.isEmpty()) {
                    List<sanphamchitietviewmodel> searchedList = searchMau(timkiemmau);
                    showdata2(searchedList);
                } else {
                    // If no manufacturer is selected, reload all products
                    listviewmodel = itf.getall();
                    showdata2(listviewmodel);
                }
            }
        });
        cbbsize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timkiemsize = cbbsize.getSelectedItem().toString();
                if (!timkiemsize.isEmpty()) {
                    List<sanphamchitietviewmodel> searchedList = searchSize(timkiemsize);
                    showdata2(searchedList);
                } else {
                    // If no manufacturer is selected, reload all products
                    listviewmodel = itf.getall();
                    showdata2(listviewmodel);
                }
            }
        });
//        cbbchatlieu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String timkiem = cbbchatlieu.getSelectedItem().toString();
//                if (!timkiem.isEmpty()) {
//                    List<sanphamchitietviewmodel> searchedList = searchChatlieu(timkiem);
//                    showdata2(searchedList);
//                } else {
//                    // If no manufacturer is selected, reload all products
//                    listviewmodel = itf.getall();
//                    showdata2(listviewmodel);
//                }
//            }
//        });
        cbbday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timkiem = cbbday.getSelectedItem().toString();
                if (!timkiem.isEmpty()) {
                    List<sanphamchitietviewmodel> searchedList = searchday(timkiem);
                    showdata2(searchedList);
                } else {
                    // If no manufacturer is selected, reload all products
                    listviewmodel = itf.getall();
                    showdata2(listviewmodel);
                }
            }
        });
//        cbbmatde.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String timkiem = cbbmatde.getSelectedItem().toString();
//                if (!timkiem.isEmpty()) {
//                    List<sanphamchitietviewmodel> searchedList = searchmatde(timkiem);
//                    showdata2(searchedList);
//                } else {
//                    // If no manufacturer is selected, reload all products
//                    listviewmodel = itf.getall();
//                    showdata2(listviewmodel);
//                }
//            }
//        });
//        cbbdoday.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String timkiem = cbbdoday.getSelectedItem().toString();
//                if (!timkiem.isEmpty()) {
//                    List<sanphamchitietviewmodel> searchedList = searchdoday(timkiem);
//                    showdata2(searchedList);
//                } else {
//                    // If no manufacturer is selected, reload all products
//                    listviewmodel = itf.getall();
//                    showdata2(listviewmodel);
//                }
//            }
//        });

        setOtherFrame(spt);

    }

    public void setOtherFrame(sanphamthem spt) {
        this.spt = spt;
    }

    public JPanel getPanelSPCT() {
        return giaodiensanpham;
    }

    private void performSearch(String criteria) {
        // Get the value from the corresponding combo box or text field
        Boolean searchValue;
        if (criteria.equals("Hãng")) {
            searchValue = rdohang.isSelected();
        }

        // Perform the actual search using the searchValue
        // Implement your search logic here...
    }

    private List<sanphamchitietviewmodel> searchHang(String manufacturerName) {
        List<sanphamchitietviewmodel> filteredList = new ArrayList<>();

        for (sanphamchitietviewmodel product : listviewmodel) {
            if (product.getIdhangsx().equalsIgnoreCase(manufacturerName)) {
                filteredList.add(product);
            }
        }

        return filteredList;
    }
    //search mau  

    private List<sanphamchitietviewmodel> searchMau(String manufacturerName) {
        List<sanphamchitietviewmodel> filteredList = new ArrayList<>();

        for (sanphamchitietviewmodel product : listviewmodel) {
            if (product.getIdphoimau().equalsIgnoreCase(manufacturerName)) {
                filteredList.add(product);
            }

        }
        return filteredList;
    }

    //search size
    private List<sanphamchitietviewmodel> searchSize(String manufacturerName) {
        List<sanphamchitietviewmodel> filteredList = new ArrayList<>();

        for (sanphamchitietviewmodel product : listviewmodel) {
            if (product.getIdsize().equalsIgnoreCase(manufacturerName)) {
                filteredList.add(product);
            }

        }

        return filteredList;
    }

    //search chat lieu
    private List<sanphamchitietviewmodel> searchChatlieu(String manufacturerName) {
        List<sanphamchitietviewmodel> filteredList = new ArrayList<>();

        for (sanphamchitietviewmodel product : listviewmodel) {
            if (product.getIdchatlieu().equalsIgnoreCase(manufacturerName)) {
                filteredList.add(product);
            }

        }

        return filteredList;
    }

    //search day
    private List<sanphamchitietviewmodel> searchday(String manufacturerName) {
        List<sanphamchitietviewmodel> filteredList = new ArrayList<>();

        for (sanphamchitietviewmodel product : listviewmodel) {
            if (product.getIdday().equalsIgnoreCase(manufacturerName)) {
                filteredList.add(product);
            }

        }

        return filteredList;
    }

    //search matde
    private List<sanphamchitietviewmodel> searchmatde(String manufacturerName) {
        List<sanphamchitietviewmodel> filteredList = new ArrayList<>();

        for (sanphamchitietviewmodel product : listviewmodel) {
            if (product.getIdmatde().equalsIgnoreCase(manufacturerName)) {
                filteredList.add(product);
            }

        }

        return filteredList;
    }

    //search do day
    private List<sanphamchitietviewmodel> searchdoday(String manufacturerName) {
        List<sanphamchitietviewmodel> filteredList = new ArrayList<>();

        for (sanphamchitietviewmodel product : listviewmodel) {
            if (product.getIddoday().equalsIgnoreCase(manufacturerName)) {
                filteredList.add(product);
            }

        }

        return filteredList;
    }

    public void oneclicksp(int show) {
        sanphamviewmodel spvm = listsanpham.get(show);
        txtmasanpham.setText(spvm.getMasp());
        txttensanpham.setText(spvm.getTensp());
        if (String.valueOf(spvm.getTrangthai()).equalsIgnoreCase("Còn hàng")) {
            rdoconhang.setSelected(true);
        } else {
            rdohethang.setSelected(true);
        }
        txtghichu.setText(spvm.getMota());
    }

    private void searchSP() {
//        listviewmodel = itf.Search(txtsearch.getText());
//        showdata2(listviewmodel);
        listsanpham = sps.Search(txtsearch.getText());
        showdata(listsanpham);

    }
//

    public void showdata3(List<ThuocTinh> list) {
        bang3.setRowCount(0);
        int stt = 0;
        for (ThuocTinh thuocTinh : list) {

            stt++;
            bang3.addRow(new Object[]{stt, thuocTinh.getMasp(), thuocTinh.getTensp()});
        }
    }
//

    public void oneclicktt(int show) {
        ThuocTinh tt = listtt.get(show);
        txtmathuoctinh.setText(tt.getMasp());
        txttenthuoctinh.setText(tt.getTensp());
    }

    public void deleteSelectedRows() {
        DefaultTableModel model = (DefaultTableModel) tblhienthichitietsanpham.getModel();
        int[] selectedRows = tblhienthichitietsanpham.getSelectedRows();

        for (int i = selectedRows.length - 1; i >= 0; i--) {
            model.removeRow(selectedRows[i]);
        }
    }

    public void showdata(List<sanphamviewmodel> list) {
        bang.setRowCount(0);
        int stt = 0;

        for (sanphamviewmodel object : list) {
            stt++;
            String tt = (object.getSoluong() == 0) ? "Hết hàng" : object.getTrangthai();
            bang.addRow(new Object[]{stt, object.getMasp(), object.getTensp(), object.getMota(), object.getSoluong(), tt});
        }
    }

    public void showdata2(List<sanphamchitietviewmodel> list) {
        bang2.setRowCount(0);
        int stt = 0;
        for (sanphamchitietviewmodel object : list) {
            stt++;
            bang2.addRow(new Object[]{stt, object.getMctsp(),
                object.getIdhangsx(),
                object.getIddongsp(),
                object.getIdphoimau(),
                object.getIdsize(),
                object.getIddoday(),
                object.getIdchatlieu(),
                object.getIdmatde(),
                object.getIdday(),
                object.getGiaban()});
        }

    }

    public void showcombobox1(List<hangsanxuat> listhsx) {
        combo1.removeAllElements();
        for (hangsanxuat object : listhsx) {
            combo1.addElement(object.getTenhang());
        }

    }

    public void showcombo2(List<DongSanPham> listdsp) {
        combo2.removeAllElements();
        for (DongSanPham dongSanPham : listdsp) {
            combo2.addElement(dongSanPham.getTendsp());
        }

    }

    public void showcombo3(List<MauSanPham> listmau) {
        combo3.removeAllElements();
        for (MauSanPham mauSanPham : listmau) {
            combo3.addElement(mauSanPham.getTenmau());
        }

    }

    public void showcombo4(List<SizeSanPham> listsize) {
        combo4.removeAllElements();
        for (SizeSanPham sizeSanPham : listsize) {
            combo4.addElement(sizeSanPham.getMasize());
        }

    }

    public void showcombo5(List<Dodaysp> listdoday) {
        combo5.removeAllElements();
        for (Dodaysp dodaysp : listdoday) {
            combo5.addElement(dodaysp.getDoday());
        }
    }

    public void showcombo6(List<Chatlieusp> listchatlieu) {
        combo6.removeAllElements();
        for (Chatlieusp chatlieusp : listchatlieu) {
            combo6.addElement(chatlieusp.getChatlieusp());
        }

    }

    public void showcombo7(List<Matdesanpham> listmd) {
        combo7.removeAllElements();
        for (Matdesanpham matdesanpham : listmd) {
            combo7.addElement(matdesanpham.getMatde());
        }

    }

    public void showcombo8(List<Daysp> listday) {
        combo8.removeAllElements();
        for (Daysp daysp : listday) {
            combo8.addElement(daysp.getDaysp());
        }

    }

    public void showcombo9(List<SizeSanPham> listSIZE) {
        combo9.removeAllElements();
        for (SizeSanPham sizeSanPham : listSIZE) {
            combo9.addElement(sizeSanPham.getMasize());
        }

    }

    public chitietsanpham getformdatasp() {
        String MSP = txtmasanpham.getText();
        String TSP = txttensanpham.getText();
        String TT = "";
        if (rdoconhang.isSelected()) {
            TT = rdoconhang.getText();
        } else if (rdohethang.isSelected()) {
            TT = rdohethang.getText();
        }
        String GC = txtghichu.getText();
        chitietsanpham ctsp = new chitietsanpham(MSP, GC, 0, TT, 0);
        return ctsp;
    }

    //
    public ThuocTinh getformdata() {
        String M = txtmathuoctinh.getText();
        String T = txttenthuoctinh.getText();
        ThuocTinh tt = new ThuocTinh(M, T, 0);
        return tt;
    }

    public hangsanxuat getformdatahsx() {
        String M = txtmathuoctinh.getText();
        String T = txttenthuoctinh.getText();

        hangsanxuat hsx = new hangsanxuat(M, T, 0, "Phúc", "Phúc");
        return hsx;
    }

    public Chatlieusp getformdatachatlieu() {
        String M = txtmathuoctinh.getText();
        String T = txttenthuoctinh.getText();

        Chatlieusp cl = new Chatlieusp(M, T, 0, "Phúc", "Phúc");
        return cl;
    }

    public Daysp getformdataday() {
        String M = txtmathuoctinh.getText();
        String T = txttenthuoctinh.getText();

        Daysp d = new Daysp(M, T, 0, "Phúc", "Phúc");
        return d;
    }

    public MauSanPham getformdatamau() {
        String M = txtmathuoctinh.getText();
        String T = txttenthuoctinh.getText();

        MauSanPham msp = new MauSanPham(M, T, 0, "Phúc", "Phúc");
        return msp;
    }

    public Dodaysp getformddatadoday() {
        String M = txtmathuoctinh.getText();
        String T = txttenthuoctinh.getText();

        Dodaysp dd = new Dodaysp(M, T, 0, "Phúc", "Phúc");
        return dd;
    }

    public Matdesanpham getformdatamatde() {
        String M = txtmathuoctinh.getText();
        String T = txttenthuoctinh.getText();

        Matdesanpham md = new Matdesanpham(M, T, 0, "Phúc", "Phúc");
        return md;
    }

    //
//getformthuoctinh
    public void clear() {
        txtmathuoctinh.setText("");
        txttenthuoctinh.setText("");
    }

    public void showdatahang(List<hangsanxuat> hsx) {
        bang3.setRowCount(0);
        int i = 0;
        for (hangsanxuat object : hsx) {
            i++;
            bang3.addRow(new Object[]{i, object.getIDhsx(), object.getTenhang()});
        }
    }

    public void showdatachatlieu(List<Chatlieusp> listcl) {
        bang3.setRowCount(0);
        int i = 0;
        for (Chatlieusp chatlieusp : listcl) {
            i++;
            bang3.addRow(new Object[]{i, chatlieusp.getIdchatlieu(), chatlieusp.getChatlieusp()});
        }
    }

    public void showdataphoimau(List<MauSanPham> list) {
        bang3.setRowCount(0);
        int i = 0;
        for (MauSanPham mauSanPham : list) {
            i++;
            bang3.addRow(new Object[]{i, mauSanPham.getIdmau(), mauSanPham.getTenmau()});
        }

    }

    public void showdataday(List<Daysp> list) {
        bang3.setRowCount(0);
        int i = 0;
        for (Daysp daysp : list) {
            i++;
            bang3.addRow(new Object[]{i, daysp.getIDday(), daysp.getDaysp()});
        }
    }

    public void showdatadoday(List<Dodaysp> list) {
        bang3.setRowCount(0);
        int i = 0;
        for (Dodaysp dodaysp : list) {
            i++;
            bang3.addRow(new Object[]{i, dodaysp.getIDdoday(), dodaysp.getDoday()});
        }

    }

    public void showmatde(List<Matdesanpham> list) {
        bang3.setRowCount(0);
        int i = 0;
        for (Matdesanpham matdesanpham : list) {
            i++;
            bang3.addRow(new Object[]{i, matdesanpham.getIDmatde(), matdesanpham.getMatde()});
        }
    }

    /**
     *
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rdotrangthai = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        rdothuoctinhsanpham = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        panel1 = new b1.View.chucnang.Panel();
        panelchitietsanpham = new javax.swing.JPanel();
        giaodiensanpham = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblhienthichitietsanpham = new javax.swing.JTable();
        btnquetmaqr = new b1.View.chucnang.ButtonGradient();
        btnthem1 = new b1.View.chucnang.ButtonGradient();
        btnexel = new b1.View.chucnang.ButtonGradient();
        rdoall = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        cbbphoimau = new b1.View.Combobox();
        cbbsize = new b1.View.Combobox();
        cbbhang = new b1.View.Combobox();
        cbbday = new b1.View.Combobox();
        btnreseat2 = new b1.View.chucnang.ButtonGradient();
        panelsanpham = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txttensanpham = new b1.View.chucnang.TextField();
        txtmasanpham = new b1.View.chucnang.TextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtghichu = new javax.swing.JTextArea();
        btnthem2 = new b1.View.chucnang.ButtonGradient();
        btnxoa1 = new b1.View.chucnang.ButtonGradient();
        btnupdate = new b1.View.chucnang.ButtonGradient();
        jLabel3 = new javax.swing.JLabel();
        rdohethang = new javax.swing.JRadioButton();
        rdoconhang = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblhienthisanpham = new javax.swing.JTable();
        btnreseat3 = new b1.View.chucnang.ButtonGradient();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txttenthuoctinh = new b1.View.chucnang.TextField();
        txtmathuoctinh = new b1.View.chucnang.TextField();
        rdohang = new javax.swing.JRadioButton();
        rdochatlieu = new javax.swing.JRadioButton();
        rdoday = new javax.swing.JRadioButton();
        rdododay = new javax.swing.JRadioButton();
        rdophoimau = new javax.swing.JRadioButton();
        rdomatde = new javax.swing.JRadioButton();
        btnthem3 = new b1.View.chucnang.ButtonGradient();
        btnxoa2 = new b1.View.chucnang.ButtonGradient();
        btnupdate1 = new b1.View.chucnang.ButtonGradient();
        btnreseat4 = new b1.View.chucnang.ButtonGradient();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblthuoctinhsanpham = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        giaodiensanpham.setBackground(new java.awt.Color(255, 255, 255));

        tblhienthichitietsanpham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblhienthichitietsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "MÃ", "HÃNG", "TÊN SP", "PHỐI MÀU", "SIZE", "ĐỘ DÀY", "CHẤT LIỆU", "MẶT ĐẾ", "DÂY", "GIÁ BÁN", "Chọn tất cả"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblhienthichitietsanpham.setGridColor(new java.awt.Color(255, 255, 255));
        tblhienthichitietsanpham.setRowHeight(40);
        tblhienthichitietsanpham.setSelectionBackground(new java.awt.Color(0, 204, 204));
        tblhienthichitietsanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhienthichitietsanphamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblhienthichitietsanpham);
        if (tblhienthichitietsanpham.getColumnModel().getColumnCount() > 0) {
            tblhienthichitietsanpham.getColumnModel().getColumn(0).setHeaderValue("STT");
            tblhienthichitietsanpham.getColumnModel().getColumn(1).setHeaderValue("MÃ");
            tblhienthichitietsanpham.getColumnModel().getColumn(2).setHeaderValue("HÃNG");
            tblhienthichitietsanpham.getColumnModel().getColumn(3).setHeaderValue("TÊN SP");
            tblhienthichitietsanpham.getColumnModel().getColumn(4).setHeaderValue("PHỐI MÀU");
            tblhienthichitietsanpham.getColumnModel().getColumn(5).setHeaderValue("SIZE");
            tblhienthichitietsanpham.getColumnModel().getColumn(6).setHeaderValue("ĐỘ DÀY");
            tblhienthichitietsanpham.getColumnModel().getColumn(7).setHeaderValue("CHẤT LIỆU");
            tblhienthichitietsanpham.getColumnModel().getColumn(8).setHeaderValue("MẶT ĐẾ");
            tblhienthichitietsanpham.getColumnModel().getColumn(9).setHeaderValue("DÂY");
            tblhienthichitietsanpham.getColumnModel().getColumn(10).setHeaderValue("GIÁ BÁN");
            tblhienthichitietsanpham.getColumnModel().getColumn(11).setHeaderValue("Chọn tất cả");
        }

        btnquetmaqr.setBackground(new java.awt.Color(153, 255, 255));
        btnquetmaqr.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnquetmaqr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/qr-code.png"))); // NOI18N
        btnquetmaqr.setColor1(new java.awt.Color(102, 255, 102));
        btnquetmaqr.setColor2(new java.awt.Color(102, 255, 102));
        btnquetmaqr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnquetmaqr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquetmaqrActionPerformed(evt);
            }
        });

        btnthem1.setBackground(new java.awt.Color(153, 255, 255));
        btnthem1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnthem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/add-user.png"))); // NOI18N
        btnthem1.setColor1(new java.awt.Color(204, 204, 255));
        btnthem1.setColor2(new java.awt.Color(255, 255, 255));
        btnthem1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnthem1MouseClicked(evt);
            }
        });
        btnthem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthem1ActionPerformed(evt);
            }
        });

        btnexel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/excel.png"))); // NOI18N
        btnexel.setColor1(new java.awt.Color(255, 255, 255));
        btnexel.setColor2(new java.awt.Color(255, 255, 255));
        btnexel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexelActionPerformed(evt);
            }
        });

        rdoall.setBackground(new java.awt.Color(255, 255, 255));
        rdoall.setText("All");
        rdoall.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdoallMouseClicked(evt);
            }
        });
        rdoall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoallActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cbbphoimau.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Xanh", "Đỏ", "Xanh nhạt", "Xanh Lá", "Nâu", "Hồng", "Cam", "Xám", "Đen", "Nau Do" }));
        cbbphoimau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbbphoimau.setLabeText("Phối màu");

        cbbsize.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50" }));
        cbbsize.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbbsize.setLabeText("Size");
        cbbsize.setPreferredSize(new java.awt.Dimension(200, 42));

        cbbhang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Nike", "Adidas", "Puma", "Rebook", "Convert", "Vans." }));
        cbbhang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbbhang.setLabeText("Hãng");
        cbbhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbhangActionPerformed(evt);
            }
        });

        cbbday.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Dây xanh", "Dây Trắng", "Dây Đen", "Dây Trăng lai Đen", "Dây Đỏ", "Dây 2light", "Dây Đỏ lai Xanh", "Dây Xám", "Dây Phát Sáng", "Dây Hồng" }));
        cbbday.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbbday.setLabeText("Dây");
        cbbday.setPreferredSize(new java.awt.Dimension(200, 42));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(cbbhang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                .addComponent(cbbphoimau, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117)
                .addComponent(cbbsize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(cbbday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbsize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbphoimau, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbhang, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        btnreseat2.setBackground(new java.awt.Color(153, 255, 255));
        btnreseat2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnreseat2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/undo.png"))); // NOI18N
        btnreseat2.setColor1(new java.awt.Color(204, 204, 255));
        btnreseat2.setColor2(new java.awt.Color(255, 255, 255));
        btnreseat2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnreseat2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnreseat2MouseClicked(evt);
            }
        });
        btnreseat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreseat2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout giaodiensanphamLayout = new javax.swing.GroupLayout(giaodiensanpham);
        giaodiensanpham.setLayout(giaodiensanphamLayout);
        giaodiensanphamLayout.setHorizontalGroup(
            giaodiensanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(giaodiensanphamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnexel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnthem1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnquetmaqr, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnreseat2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(rdoall, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(giaodiensanphamLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );
        giaodiensanphamLayout.setVerticalGroup(
            giaodiensanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(giaodiensanphamLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(giaodiensanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnthem1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnquetmaqr, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnexel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoall, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnreseat2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelchitietsanphamLayout = new javax.swing.GroupLayout(panelchitietsanpham);
        panelchitietsanpham.setLayout(panelchitietsanphamLayout);
        panelchitietsanphamLayout.setHorizontalGroup(
            panelchitietsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(giaodiensanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelchitietsanphamLayout.setVerticalGroup(
            panelchitietsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(giaodiensanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panel1.addTab("Chi tiết Sản Phẩm", panelchitietsanpham);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        txttensanpham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txttensanpham.setLabelText("Tên sản phẩm");

        txtmasanpham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtmasanpham.setLabelText("Mã sản phẩm");
        txtmasanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmasanphamActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Mô tả sản phẩm");

        txtghichu.setColumns(20);
        txtghichu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtghichu.setRows(5);
        jScrollPane1.setViewportView(txtghichu);

        btnthem2.setBackground(new java.awt.Color(153, 255, 255));
        btnthem2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnthem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/add-user.png"))); // NOI18N
        btnthem2.setColor1(new java.awt.Color(204, 204, 255));
        btnthem2.setColor2(new java.awt.Color(255, 255, 255));
        btnthem2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthem2.setMinimumSize(new java.awt.Dimension(150, 26));
        btnthem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnthem2MouseClicked(evt);
            }
        });
        btnthem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthem2ActionPerformed(evt);
            }
        });

        btnxoa1.setBackground(new java.awt.Color(153, 255, 255));
        btnxoa1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnxoa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/delete.png"))); // NOI18N
        btnxoa1.setColor1(new java.awt.Color(204, 204, 255));
        btnxoa1.setColor2(new java.awt.Color(255, 255, 255));
        btnxoa1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnxoa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoa1ActionPerformed(evt);
            }
        });

        btnupdate.setBackground(new java.awt.Color(153, 255, 255));
        btnupdate.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/level-up.png"))); // NOI18N
        btnupdate.setColor1(new java.awt.Color(204, 204, 255));
        btnupdate.setColor2(new java.awt.Color(255, 255, 255));
        btnupdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnupdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnupdateMouseClicked(evt);
            }
        });
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Trạng thái :");

        rdohethang.setBackground(new java.awt.Color(255, 255, 255));
        rdotrangthai.add(rdohethang);
        rdohethang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdohethang.setText("Hết hàng");

        rdoconhang.setBackground(new java.awt.Color(255, 255, 255));
        rdotrangthai.add(rdoconhang);
        rdoconhang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoconhang.setText("Còn hàng");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Tìm kiếm :");

        txtsearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtsearch.setBorder(null);
        txtsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        tblhienthisanpham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblhienthisanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "TÊN SP", "Mô tả", "Số lượng ", "trạng thái", "Hành dộng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblhienthisanpham.setGridColor(new java.awt.Color(255, 255, 255));
        tblhienthisanpham.setRowHeight(40);
        tblhienthisanpham.setSelectionBackground(new java.awt.Color(0, 204, 204));
        tblhienthisanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhienthisanphamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblhienthisanpham);
        if (tblhienthisanpham.getColumnModel().getColumnCount() > 0) {
            tblhienthisanpham.getColumnModel().getColumn(0).setHeaderValue("STT");
            tblhienthisanpham.getColumnModel().getColumn(1).setHeaderValue("Mã SP");
            tblhienthisanpham.getColumnModel().getColumn(2).setHeaderValue("TÊN SP");
            tblhienthisanpham.getColumnModel().getColumn(3).setHeaderValue("Mô tả");
            tblhienthisanpham.getColumnModel().getColumn(4).setHeaderValue("Số lượng ");
            tblhienthisanpham.getColumnModel().getColumn(5).setHeaderValue("trạng thái");
            tblhienthisanpham.getColumnModel().getColumn(6).setHeaderValue("Hành dộng");
        }

        btnreseat3.setBackground(new java.awt.Color(153, 255, 255));
        btnreseat3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnreseat3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/undo.png"))); // NOI18N
        btnreseat3.setColor1(new java.awt.Color(204, 204, 255));
        btnreseat3.setColor2(new java.awt.Color(255, 255, 255));
        btnreseat3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnreseat3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnreseat3MouseClicked(evt);
            }
        });
        btnreseat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreseat3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txttensanpham, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addComponent(txtmasanpham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(274, 274, 274)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnthem2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(btnxoa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnreseat3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnupdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(92, 92, 92))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(rdoconhang, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdohethang, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator2)
                            .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(102, 102, 102))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtmasanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txttensanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnreseat3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnthem2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnxoa1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnupdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(72, 72, 72)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(rdohethang)
                        .addComponent(rdoconhang))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelsanphamLayout = new javax.swing.GroupLayout(panelsanpham);
        panelsanpham.setLayout(panelsanphamLayout);
        panelsanphamLayout.setHorizontalGroup(
            panelsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelsanphamLayout.setVerticalGroup(
            panelsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelsanphamLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        panel1.addTab("Sản Phẩm", panelsanpham);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txttenthuoctinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txttenthuoctinh.setLabelText("Tên thuộc tính");

        txtmathuoctinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtmathuoctinh.setLabelText("Mã thuộc tính");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(txttenthuoctinh, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(txtmathuoctinh, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(129, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(146, Short.MAX_VALUE)
                .addComponent(txttenthuoctinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addComponent(txtmathuoctinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(168, Short.MAX_VALUE)))
        );

        rdohang.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdohang);
        rdohang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdohang.setText("Hãng");
        rdohang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdohangActionPerformed(evt);
            }
        });

        rdochatlieu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdochatlieu);
        rdochatlieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdochatlieu.setText("Chất liệu");
        rdochatlieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdochatlieuActionPerformed(evt);
            }
        });

        rdoday.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdoday);
        rdoday.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdoday.setText("Dây");
        rdoday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdodayActionPerformed(evt);
            }
        });

        rdododay.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdododay);
        rdododay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdododay.setText("Độ dày");
        rdododay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdododayActionPerformed(evt);
            }
        });

        rdophoimau.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdophoimau);
        rdophoimau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdophoimau.setText("Phối màu");
        rdophoimau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdophoimauActionPerformed(evt);
            }
        });

        rdomatde.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdomatde);
        rdomatde.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rdomatde.setText("Mặt đế");
        rdomatde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdomatdeActionPerformed(evt);
            }
        });

        btnthem3.setBackground(new java.awt.Color(153, 255, 255));
        btnthem3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnthem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/add-user.png"))); // NOI18N
        btnthem3.setColor1(new java.awt.Color(204, 204, 255));
        btnthem3.setColor2(new java.awt.Color(255, 255, 255));
        btnthem3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthem3.setMinimumSize(new java.awt.Dimension(150, 26));
        btnthem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnthem3MouseClicked(evt);
            }
        });
        btnthem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthem3ActionPerformed(evt);
            }
        });

        btnxoa2.setBackground(new java.awt.Color(153, 255, 255));
        btnxoa2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnxoa2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/delete.png"))); // NOI18N
        btnxoa2.setColor1(new java.awt.Color(204, 204, 255));
        btnxoa2.setColor2(new java.awt.Color(255, 255, 255));
        btnxoa2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnxoa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoa2ActionPerformed(evt);
            }
        });

        btnupdate1.setBackground(new java.awt.Color(153, 255, 255));
        btnupdate1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnupdate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/level-up.png"))); // NOI18N
        btnupdate1.setColor1(new java.awt.Color(204, 204, 255));
        btnupdate1.setColor2(new java.awt.Color(255, 255, 255));
        btnupdate1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnupdate1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnupdate1MouseClicked(evt);
            }
        });
        btnupdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdate1ActionPerformed(evt);
            }
        });

        btnreseat4.setBackground(new java.awt.Color(153, 255, 255));
        btnreseat4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnreseat4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/undo.png"))); // NOI18N
        btnreseat4.setColor1(new java.awt.Color(204, 204, 255));
        btnreseat4.setColor2(new java.awt.Color(255, 255, 255));
        btnreseat4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnreseat4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnreseat4MouseClicked(evt);
            }
        });
        btnreseat4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreseat4ActionPerformed(evt);
            }
        });

        tblthuoctinhsanpham.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblthuoctinhsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã TT", "Tên TT"
            }
        ));
        tblthuoctinhsanpham.setGridColor(new java.awt.Color(255, 255, 255));
        tblthuoctinhsanpham.setRowHeight(40);
        tblthuoctinhsanpham.setSelectionBackground(new java.awt.Color(0, 204, 204));
        tblthuoctinhsanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblthuoctinhsanphamMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblthuoctinhsanpham);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdochatlieu)
                    .addComponent(rdohang))
                .addGap(81, 81, 81)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoday)
                    .addComponent(rdododay))
                .addGap(50, 50, 50)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rdomatde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnxoa2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rdophoimau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addComponent(btnthem3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnupdate1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnreseat4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnthem3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdohang)
                                .addComponent(rdoday)
                                .addComponent(rdophoimau))
                            .addComponent(btnreseat4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdochatlieu)
                                    .addComponent(rdododay)
                                    .addComponent(rdomatde))
                                .addGap(128, 128, 128))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnxoa2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnupdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(138, 138, 138))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel1.addTab("Thuộc tính sản phẩm", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblhienthisanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhienthisanphamMouseClicked
        // TODO add your handling code here:
        int show = tblhienthisanpham.getSelectedRow();
        oneclicksp(show);
    }//GEN-LAST:event_tblhienthisanphamMouseClicked

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        int sua = tblhienthisanpham.getSelectedRow();
        sanphamviewmodel spvm = listsanpham.get(sua);
        sps.Sua(getformdatasp(), txttensanpham.getText(), spvm.getMasp());
        listsanpham = sps.getall();
        showdata(listsanpham);
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnupdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnupdateMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnupdateMouseClicked

    private void btnxoa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoa1ActionPerformed
        // TODO add your handling code here:
        int xoa = tblhienthisanpham.getSelectedRow();
        sanphamviewmodel spvm = listsanpham.get(xoa);
        sps.Xoa(spvm.getMasp());
        listsanpham = sps.getall();
        showdata(listsanpham);

    }//GEN-LAST:event_btnxoa1ActionPerformed

    private void btnthem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthem2ActionPerformed
        // TODO add your handling code here:
        sps.Add(getformdatasp(), txttensanpham.getText());
        listsanpham = sps.getall();
        showdata(listsanpham);
    }//GEN-LAST:event_btnthem2ActionPerformed

    private void btnthem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnthem2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnthem2MouseClicked

    private void txtmasanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmasanphamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmasanphamActionPerformed

    private void rdoallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoallActionPerformed

        int row = tblhienthichitietsanpham.getRowCount();
        for (int i = 0; i < row; i++) {
            tblhienthichitietsanpham.setValueAt(true, i, 11);
        }
    }//GEN-LAST:event_rdoallActionPerformed

    private void rdoallMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdoallMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoallMouseClicked

    private void btnexelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexelActionPerformed
        boolean excel = itf.Excel("C:\\Ecxel\\table.xlsx");
        if (excel) {
            JOptionPane.showMessageDialog(this, "Xuất các sản phẩm trong hệ thống thành công !");
        } else {
            JOptionPane.showMessageDialog(this, "Hệ thống bị lỗi? Không thể xuất sang excel được");
        }
    }//GEN-LAST:event_btnexelActionPerformed

    private void btnthem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthem1ActionPerformed
        // TODO add your handling code here:
        sanphamthem spt = new sanphamthem();
        spt.setVisible(true);
        spt.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //        itf.Add(getformdata());
        //        listviewmodel = itf.getall();
        //        showdata2(listviewmodel);
    }//GEN-LAST:event_btnthem1ActionPerformed

    private void btnthem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnthem1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnthem1MouseClicked

    private void btnquetmaqrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquetmaqrActionPerformed
        // TODO add your handling code here:
        quetmaqr mn = new quetmaqr();
        mn.setVisible(true);
        mn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnquetmaqrActionPerformed

    private void tblhienthichitietsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhienthichitietsanphamMouseClicked
        // TODO add your handling code here:
        // Import statement if SecondFrame is in a different package
// import your.package.path.SecondFrame;


    }//GEN-LAST:event_tblhienthichitietsanphamMouseClicked

    private void cbbhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbhangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbhangActionPerformed

    private void btnreseat2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnreseat2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnreseat2MouseClicked

    private void btnreseat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreseat2ActionPerformed
        // TODO add your handling code here:
        cbbhang.setSelectedIndex(0);
//        cbbchatlieu.setSelectedItem(0);
//        cbbdoday.setSelectedItem(0);
        cbbday.setSelectedItem(0);
        cbbphoimau.setSelectedItem(0);
        cbbsize.setSelectedItem(0);
//        cbbmatde.setSelectedItem(0);

    }//GEN-LAST:event_btnreseat2ActionPerformed

    private void btnreseat3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnreseat3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnreseat3MouseClicked

    private void btnreseat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreseat3ActionPerformed
        // TODO add your handling code here:
        int show = tblthuoctinhsanpham.getSelectedRow();
        int dk = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn Reseat?");
        if (dk == JOptionPane.YES_OPTION) {
            txtmasanpham.setText("");
            txttensanpham.setText("");
            txtghichu.setText("");
            rdotrangthai.clearSelection();
            JOptionPane.showMessageDialog(this, "Đã Reseat thành công");
        }
        if (dk == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã bỏ qua");
            return;
        }
        if (dk == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn Cancel");
            return;
        }

        txtmasanpham.setText("");
        txttensanpham.setText("");
        txtghichu.setText("");
        rdotrangthai.clearSelection();
    }//GEN-LAST:event_btnreseat3ActionPerformed

    private void btnthem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnthem3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnthem3MouseClicked

    private void btnthem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthem3ActionPerformed
        // TODO add your handling code here:
        int show = tblthuoctinhsanpham.getSelectedRow();
        int dk = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn Thêm?");
        if (dk == JOptionPane.YES_OPTION) {
            if (rdohang.isSelected()) {
                tts.add(getformdatahsx());
                showdatahang(tts.gethsx());
            } else if (rdochatlieu.isSelected()) {
                tts.addcl(getformdatachatlieu());
                showdatachatlieu(tts.getchatlieu());
            } else if (rdoday.isSelected()) {
                tts.addday(getformdataday());
                showdataday(tts.getday());
            } else if (rdododay.isSelected()) {
                tts.adddoday(getformddatadoday());
                showdatadoday(tts.getdoday());
            } else if (rdomatde.isSelected()) {
                tts.adddoday(getformddatadoday());
                showmatde(tts.getmatde());
            } else if (rdophoimau.isSelected()) {
                tts.addmau(getformdatamau());
                showdataphoimau(tts.getmau());
            } else {
                tts.add(getformdata());
                listtt = tts.getall();
                showdata3(listtt);
            }
            JOptionPane.showMessageDialog(this, "Đã thêm thành công");
        }
        if (dk == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã bỏ qua");
            return;
        }
        if (dk == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn Cancel");
            return;
        }

    }//GEN-LAST:event_btnthem3ActionPerformed

    private void btnxoa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoa2ActionPerformed
        // TODO add your handling code here:
//        int dk = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn?");
//        int xoa = tblthuoctinhsanpham.getSelectedRow();

        int show = tblthuoctinhsanpham.getSelectedRow();
        int dk = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?");
        if (dk == JOptionPane.YES_OPTION) {
            if (rdohang.isSelected()) {
                hangsanxuat hsx = tts.gethsx().get(show);
                tts.xoahsx(hsx.getIDhsx());
                showdatahang(tts.gethsx());
            } else if (rdochatlieu.isSelected()) {
                Chatlieusp cl = tts.getchatlieu().get(show);
                tts.xoachatlieu(cl.getIdchatlieu());
                showdatachatlieu(tts.getchatlieu());
            } else if (rdomatde.isSelected()) {
                Matdesanpham md = tts.getmatde().get(show);
                tts.xoamatde(md.getIDmatde());
                showmatde(tts.getmatde());
            } else if (rdophoimau.isSelected()) {
                MauSanPham msp = tts.getmau().get(show);
                tts.xoamau(msp.getIdmau());
                showdataphoimau(tts.getmau());
            } else if (rdoday.isSelected()) {
                Daysp day = tts.getday().get(show);
                tts.xoaday(day.getIDday());
                showdataday(tts.getday());
            } else if (rdododay.isSelected()) {
                Dodaysp dd = tts.getdoday().get(show);
                tts.xoadoday(dd.getIDdoday());
                showdatadoday(tts.getdoday());
            } else {
                ThuocTinh tt = listtt.get(show);
                tts.xoa(tt.getMasp());
                listtt = tts.getall();
                showdata3(listtt);
            }
            JOptionPane.showMessageDialog(this, "Đã xóa thành công");
        }
        if (dk == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã bỏ xóa");
            return;
        }
        if (dk == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn Cancel");
            return;
        }


    }//GEN-LAST:event_btnxoa2ActionPerformed

    private void btnupdate1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnupdate1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnupdate1MouseClicked

    private void btnupdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdate1ActionPerformed
        int index = tblthuoctinhsanpham.getSelectedRow();
        int dk = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn sửa?");

        if (dk == JOptionPane.YES_OPTION) {
            if (rdohang.isSelected()) {
                hangsanxuat hsx = tts.gethsx().get(index);
                tts.updatehsx(hsx.getIDhsx(), getformdatahsx());
                showdatahang(tts.gethsx());
            } else if (rdoday.isSelected()) {
                Daysp day = tts.getday().get(index);
                tts.updatehsx(day.getIDday(), getformdataday());
                showdataday(tts.getday());
            } else if (rdophoimau.isSelected()) {
                MauSanPham mau = tts.getmau().get(index);
                tts.updatephoimau(mau.getIdmau(), getformdatamau());
                showdataphoimau(tts.getmau());
            } else if (rdochatlieu.isSelected()) {
                Chatlieusp cl = tts.getchatlieu().get(index);
                tts.updatechatlieu(cl.getIdchatlieu(), getformdatachatlieu());
                showdatachatlieu(tts.getchatlieu());
            } else if (rdododay.isSelected()) {
                Dodaysp dd = tts.getdoday().get(index);
                tts.updatedoday(dd.getIDdoday(), getformddatadoday());
                showdatadoday(tts.getdoday());
            } else if (rdomatde.isSelected()) {
                Matdesanpham md = tts.getmatde().get(index);
                tts.updatematde(md.getIDmatde(), getformdatamatde());
                showmatde(tts.getmatde());
            } else {
                ThuocTinh tt = listtt.get(index);
                tts.update(getformdata(), tt.getMasp());
                listtt = tts.getall();
                showdata3(listtt);
            }
        }
        if (dk == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã hủy");
            return;
        }
        if (dk == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã Cancel");
            return;
        }


    }//GEN-LAST:event_btnupdate1ActionPerformed

    private void btnreseat4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnreseat4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnreseat4MouseClicked

    private void btnreseat4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreseat4ActionPerformed
        // TODO add your handling code here:
        int show = tblthuoctinhsanpham.getSelectedRow();
        int dk = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn reseat");
        if (dk == JOptionPane.YES_OPTION) {
            buttonGroup2.clearSelection();
            txtmasanpham.setText("");
            txttensanpham.setText("");
            showdata3(listtt);
            JOptionPane.showMessageDialog(this, "Đã  reseat thành công");
        }
        if (dk == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã bỏ qua");
            return;
        }
        if (dk == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn Cancel");
            return;
        }


    }//GEN-LAST:event_btnreseat4ActionPerformed

    private void tblthuoctinhsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblthuoctinhsanphamMouseClicked
        // TODO add your handling code here:
        int show = tblthuoctinhsanpham.getSelectedRow();
        oneclicktt(show);

        //
        if (rdochatlieu.isSelected()) {
            int index = tblthuoctinhsanpham.getSelectedRow();
            Chatlieusp clsp = cls.getall().get(index);
            txtmathuoctinh.setText(clsp.getIdchatlieu());
            txttenthuoctinh.setText(clsp.getChatlieusp());
        } else if (rdohang.isSelected()) {
            int index = tblthuoctinhsanpham.getSelectedRow();
            hangsanxuat hsx = hsxs.getall().get(index);
            txtmathuoctinh.setText(hsx.getIDhsx());
            txttenthuoctinh.setText(hsx.getTenhang());

        } else if (rdoday.isSelected()) {
            int index = tblthuoctinhsanpham.getSelectedRow();
            Daysp day = ds.getall().get(index);
            txtmathuoctinh.setText(day.getIDday());
            txttenthuoctinh.setText(day.getDaysp());

        } else if (rdomatde.isSelected()) {
            int index = tblthuoctinhsanpham.getSelectedRow();
            Matdesanpham md = mds.getall().get(index);
            txtmathuoctinh.setText(md.getIDmatde());
            txttenthuoctinh.setText(md.getMatde());
        } else if (rdododay.isSelected()) {
            int index = tblthuoctinhsanpham.getSelectedRow();
            Dodaysp dd = dds.getall().get(index);
            txtmathuoctinh.setText(dd.getIDdoday());
            txttenthuoctinh.setText(dd.getDoday());
        } else if (rdophoimau.isSelected()) {
            int index = tblthuoctinhsanpham.getSelectedRow();
            MauSanPham msp = ms.getall().get(index);
            txtmathuoctinh.setText(msp.getIdmau());
            txttenthuoctinh.setText(msp.getTenmau());
        }

    }//GEN-LAST:event_tblthuoctinhsanphamMouseClicked

    private void rdohangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdohangActionPerformed
        // TODO add your handling code here:
        showdatahang(tts.gethsx());
        clear();

    }//GEN-LAST:event_rdohangActionPerformed

    private void rdochatlieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdochatlieuActionPerformed
        // TODO add your handling code here:
        showdatachatlieu(tts.getchatlieu());
        clear();
        ;

    }//GEN-LAST:event_rdochatlieuActionPerformed

    private void rdodayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdodayActionPerformed
        // TODO add your handling code here:
        showdataday(tts.getday());
        clear();
    }//GEN-LAST:event_rdodayActionPerformed

    private void rdophoimauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdophoimauActionPerformed
        // TODO add your handling code here:
        showdataphoimau(tts.getmau());
        clear();
    }//GEN-LAST:event_rdophoimauActionPerformed

    private void rdomatdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdomatdeActionPerformed
        // TODO add your handling code here:
        showmatde(tts.getmatde());
        clear();
    }//GEN-LAST:event_rdomatdeActionPerformed

    private void rdododayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdododayActionPerformed
        // TODO add your handling code here:
        showdatadoday(tts.getdoday());
        clear();
    }//GEN-LAST:event_rdododayActionPerformed

    private void txtsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_txtsearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private b1.View.chucnang.ButtonGradient btnexel;
    private b1.View.chucnang.ButtonGradient btnquetmaqr;
    private b1.View.chucnang.ButtonGradient btnreseat2;
    private b1.View.chucnang.ButtonGradient btnreseat3;
    private b1.View.chucnang.ButtonGradient btnreseat4;
    private b1.View.chucnang.ButtonGradient btnthem1;
    private b1.View.chucnang.ButtonGradient btnthem2;
    private b1.View.chucnang.ButtonGradient btnthem3;
    private b1.View.chucnang.ButtonGradient btnupdate;
    private b1.View.chucnang.ButtonGradient btnupdate1;
    b1.View.chucnang.ButtonGradient btnxoa1;
    b1.View.chucnang.ButtonGradient btnxoa2;
    private javax.swing.ButtonGroup buttonGroup2;
    private b1.View.Combobox cbbday;
    private b1.View.Combobox cbbhang;
    private b1.View.Combobox cbbphoimau;
    private b1.View.Combobox cbbsize;
    private javax.swing.JPanel giaodiensanpham;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator2;
    private b1.View.chucnang.Panel panel1;
    private javax.swing.JPanel panelchitietsanpham;
    private javax.swing.JPanel panelsanpham;
    private javax.swing.JCheckBox rdoall;
    private javax.swing.JRadioButton rdochatlieu;
    private javax.swing.JRadioButton rdoconhang;
    private javax.swing.JRadioButton rdoday;
    private javax.swing.JRadioButton rdododay;
    private javax.swing.JRadioButton rdohang;
    private javax.swing.JRadioButton rdohethang;
    private javax.swing.JRadioButton rdomatde;
    private javax.swing.JRadioButton rdophoimau;
    private javax.swing.ButtonGroup rdothuoctinhsanpham;
    private javax.swing.ButtonGroup rdotrangthai;
    private javax.swing.JTable tblhienthichitietsanpham;
    private javax.swing.JTable tblhienthisanpham;
    private javax.swing.JTable tblthuoctinhsanpham;
    private javax.swing.JTextArea txtghichu;
    b1.View.chucnang.TextField txtmasanpham;
    private b1.View.chucnang.TextField txtmathuoctinh;
    private javax.swing.JTextField txtsearch;
    private b1.View.chucnang.TextField txttensanpham;
    private b1.View.chucnang.TextField txttenthuoctinh;
    // End of variables declaration//GEN-END:variables
}
