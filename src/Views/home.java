/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.*;
import java.awt.CardLayout;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import Controllers.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.objects.NativeRegExp.test;

/**
 *
 * @author juanba
 */
public class home extends javax.swing.JFrame {

    private boolean loginAttempt = false;
    ProductoController prodControl = new ProductoController();
    ClientesController clienControl = new ClientesController();
    ProveedorController provControl = new ProveedorController();
    UsuarioController userControl = new UsuarioController();
    SearchCombo search = new SearchCombo();
    Usuario tmpUser = new Usuario();
    Usuario currentUser = new Usuario();
    int tmpUserId;

    Postgres postgres = new Postgres();
    Connection connection = postgres.connect();
    ResultSet resultSet = null;
    Statement statement = null;

    DefaultTableModel modelVentas = new DefaultTableModel(new String[]{"", "", "", "", ""}, 0);
    double total;

    //Create new venta model object
    Venta venta = new Venta();

    //Create new venta and corte controller object
    VentaController ventaControl = new VentaController();
    CorteController corteControl = new CorteController();

    //Date and Time objects
    DateTimeFormatter dtfyear = DateTimeFormatter.ofPattern("yyyy");
    DateTimeFormatter dtfmonth = DateTimeFormatter.ofPattern("MM");
    DateTimeFormatter dtfday = DateTimeFormatter.ofPattern("dd");
    LocalDate localDate = LocalDate.now();

    DateTimeFormatter dtftime = DateTimeFormatter.ofPattern("HH:mm");
    LocalDateTime now = LocalDateTime.now();

    /**
     * Creates new form home
     */
    public home() {
        initComponents();

        final JTextField textfield = (JTextField) buscarProductoField.getEditor().getEditorComponent();

        final JTextField textfield2 = (JTextField) buscarClienteVentaField.getEditor().getEditorComponent();

        textfield.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        comboFilterProductos(textfield.getText());
                    }
                });
            }
        });

        textfield2.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        comboFilterClientes(textfield2.getText());
                    }
                });
            }
        });
    }

    public void comboFilterProductos(String enteredText) {
        List<String> filterArray = new ArrayList<String>();

        String str1 = "";

        try {
            String str = "SELECT nombre FROM productos WHERE nombre  LIKE '" + enteredText + "%'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str);
            while (resultSet.next()) {
                str1 = resultSet.getString("nombre");
                filterArray.add(str1);
            }

        } catch (Exception ex) {
            System.out.println("error" + ex);
        }

        if (filterArray.size() > 0) {
            buscarProductoField.setModel(new DefaultComboBoxModel(filterArray.toArray()));
            buscarProductoField.setSelectedItem(enteredText);
            buscarProductoField.showPopup();
        } else {
            buscarProductoField.hidePopup();
        }
    }

    public void comboFilterClientes(String enteredText) {
        List<String> filterArray = new ArrayList<String>();

        String str1 = "";

        try {
            String str = "SELECT rfc FROM clientes WHERE rfc LIKE '" + enteredText + "%'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(str);
            while (resultSet.next()) {
                str1 = resultSet.getString("rfc");
                filterArray.add(str1);
            }

        } catch (Exception ex) {
            System.out.println("error" + ex);
        }

        if (filterArray.size() > 0) {
            buscarClienteVentaField.setModel(new DefaultComboBoxModel(filterArray.toArray()));
            buscarClienteVentaField.setSelectedItem(enteredText);
            buscarClienteVentaField.showPopup();
        } else {
            buscarClienteVentaField.hidePopup();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTextField3 = new javax.swing.JTextField();
        finalPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("finalPU").createEntityManager();
        productosQuery = java.beans.Beans.isDesignTime() ? null : finalPUEntityManager.createQuery("SELECT p FROM Productos p");
        productosList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : productosQuery.getResultList();
        mainMasterPanel = new javax.swing.JPanel();
        loginPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        passwordField = new javax.swing.JPasswordField();
        usernameField = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        iniciarButton = new javax.swing.JLabel();
        errorLogin = new javax.swing.JLabel();
        backgroundPanel = new javax.swing.JPanel();
        headerMainPanel = new javax.swing.JPanel();
        headerSecondPanel = new javax.swing.JPanel();
        ventasLabel = new javax.swing.JLabel();
        headerBackgroundImage = new javax.swing.JLabel();
        inventarioLabel = new javax.swing.JLabel();
        clientesLabel = new javax.swing.JLabel();
        proveedoresLabel = new javax.swing.JLabel();
        comprasLabel = new javax.swing.JLabel();
        configuracionLabel = new javax.swing.JLabel();
        corteLabel = new javax.swing.JLabel();
        salirLabel = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        ventasPanel = new javax.swing.JPanel();
        nombreLabelSearch = new javax.swing.JLabel();
        venderButton = new javax.swing.JButton();
        brownBackground = new javax.swing.JPanel();
        tableHeaders = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ventasBottomPanel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        buscarClienteVentaField = new javax.swing.JComboBox<>();
        errorVentas = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        todosProductosVentasTable = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        buscarProductoField = new javax.swing.JComboBox<>();
        jLabel137 = new javax.swing.JLabel();
        cantidadVenderField = new javax.swing.JTextField();
        inventarioPanel = new javax.swing.JPanel();
        inventarioSubPanel = new javax.swing.JPanel();
        invTodosPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        todosProductosTable = new javax.swing.JTable();
        invBajosPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        todosBajosTable = new javax.swing.JTable();
        invAgregarPanel = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        inventarioAgregarNombre = new javax.swing.JTextField();
        inventarioAgregarPC = new javax.swing.JTextField();
        inventarioAgregarPV = new javax.swing.JTextField();
        inventarioAgregarExistencias = new javax.swing.JTextField();
        inventarioAgregarStock = new javax.swing.JTextField();
        agregarCancelarProductoButton = new javax.swing.JButton();
        agregarGuardarProductoButton = new javax.swing.JButton();
        errorYaExiste = new javax.swing.JLabel();
        successMessage = new javax.swing.JLabel();
        invModificarPanel = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        guardarCambioButton = new javax.swing.JButton();
        modBuscarField = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();
        successLabel = new javax.swing.JLabel();
        invBorrarPanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        borrarButton = new javax.swing.JButton();
        productoBorrarField = new javax.swing.JTextField();
        borrarBuscarButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        borrarNombreField = new javax.swing.JTextField();
        borrarPCField = new javax.swing.JTextField();
        borrarPVField = new javax.swing.JTextField();
        borrarExistenciasField = new javax.swing.JTextField();
        borrarStockField = new javax.swing.JTextField();
        errorBorrar = new javax.swing.JLabel();
        successBorrado = new javax.swing.JLabel();
        invTodosButton = new javax.swing.JButton();
        invBajosButton = new javax.swing.JButton();
        invAgregarButton = new javax.swing.JButton();
        invModificarButton = new javax.swing.JButton();
        invBorrarButton = new javax.swing.JButton();
        clientesPanel = new javax.swing.JPanel();
        clientesSubPanel = new javax.swing.JPanel();
        clientesTodosPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        clientesTodos = new javax.swing.JTable();
        clientesAgregarPanel = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        clienteNombre = new javax.swing.JTextField();
        clienteApellidoPaterno = new javax.swing.JTextField();
        clienteApellidoMaterno = new javax.swing.JTextField();
        clienteRfc = new javax.swing.JTextField();
        clienteTelefono = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        clienteGuardar = new javax.swing.JButton();
        agClienteSuccess = new javax.swing.JLabel();
        agClienteError = new javax.swing.JLabel();
        clientesModificarPanel = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        mClienteBuscarField = new javax.swing.JTextField();
        mClienteBuscarButton = new javax.swing.JButton();
        mClienteError = new javax.swing.JLabel();
        mClienteSuccess = new javax.swing.JLabel();
        clientesBorrarPanel = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        bClienteBuscarField = new javax.swing.JTextField();
        bClienteBuscarButton = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        bClienteError = new javax.swing.JLabel();
        bClienteSuccess = new javax.swing.JLabel();
        clientesTodosButton = new javax.swing.JButton();
        clientesAgregarButton = new javax.swing.JButton();
        clientesModificarButton = new javax.swing.JButton();
        clientesBorrarButton = new javax.swing.JButton();
        proveedoresPanel = new javax.swing.JPanel();
        proveedoresSubPanel = new javax.swing.JPanel();
        provTodosPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaProveedores = new javax.swing.JTable();
        provAgregarPanel = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        nombreProveedor = new javax.swing.JTextField();
        apellidoPaternoProveedor = new javax.swing.JTextField();
        apellidoMaternoProveedor = new javax.swing.JTextField();
        rfcProveedor = new javax.swing.JTextField();
        telefonoProveedor = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        guardarProveedor = new javax.swing.JButton();
        aPError = new javax.swing.JLabel();
        aPSuccess = new javax.swing.JLabel();
        provModificarPanel = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jTextField30 = new javax.swing.JTextField();
        jTextField31 = new javax.swing.JTextField();
        jTextField32 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        modProveedorBuscarField = new javax.swing.JTextField();
        modProveedorBuscarButton = new javax.swing.JButton();
        mProveedorError = new javax.swing.JLabel();
        mProveedorSuccess = new javax.swing.JLabel();
        provBorrarPanel = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        boProveedorBuscarField = new javax.swing.JTextField();
        boProveedorBuscarButton = new javax.swing.JButton();
        boProveedorError = new javax.swing.JLabel();
        boProveedorSuccess = new javax.swing.JLabel();
        provTodosButton = new javax.swing.JButton();
        provBorrarButton = new javax.swing.JButton();
        provModificarButton = new javax.swing.JButton();
        provAgregarButton = new javax.swing.JButton();
        comprasPanel = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jComboBox9 = new javax.swing.JComboBox<>();
        jComboBox10 = new javax.swing.JComboBox<>();
        jTextField36 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        configuracionPanel = new javax.swing.JPanel();
        configuracionSubPanel = new javax.swing.JPanel();
        configuracionUsuariosPanel = new javax.swing.JPanel();
        todosUsuariosPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableUsuarios = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        mainUsuariosError = new javax.swing.JLabel();
        aUsuarioPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        aUsuarioError = new javax.swing.JLabel();
        aUsuarioSuccess = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        mUsuarioPanel = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jTextField35 = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jTextField43 = new javax.swing.JTextField();
        jTextField44 = new javax.swing.JTextField();
        jLabel130 = new javax.swing.JLabel();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        aUsuarioError1 = new javax.swing.JLabel();
        aUsuarioSuccess1 = new javax.swing.JLabel();
        mUsuarioID = new javax.swing.JTextField();
        bUsuarioPanel = new javax.swing.JPanel();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jTextField45 = new javax.swing.JTextField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jTextField46 = new javax.swing.JTextField();
        jTextField47 = new javax.swing.JTextField();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jTextField48 = new javax.swing.JTextField();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        mUsuarioID1 = new javax.swing.JTextField();
        configuracionTicketPanel = new javax.swing.JPanel();
        jLabel107 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jTextField38 = new javax.swing.JTextField();
        jTextField39 = new javax.swing.JTextField();
        jTextField40 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jTextField41 = new javax.swing.JTextField();
        jTextField42 = new javax.swing.JTextField();
        jLabel121 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jLabel122 = new javax.swing.JLabel();
        confUsuariosPanel = new javax.swing.JButton();
        confTicketPanel = new javax.swing.JButton();
        cortePanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();

        jTextField3.setText("jTextField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mi Tiendita App");
        setBackground(new java.awt.Color(0, 0, 0));
        setBounds(new java.awt.Rectangle(0, 0, 1025, 640));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        setSize(new java.awt.Dimension(1025, 640));

        mainMasterPanel.setLayout(new java.awt.CardLayout());

        loginPanel.setBackground(new java.awt.Color(36, 47, 65));

        jPanel5.setBackground(new java.awt.Color(97, 212, 195));

        jLabel29.setFont(new java.awt.Font("Hobo Std", 0, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Mi Tiendita App");

        jLabel89.setToolTipText("www.juanbarajas.com");
        jLabel89.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel89.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel89MouseClicked(evt);
            }
        });

        jLabel92.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setToolTipText("");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mitiendita2.png"))); // NOI18N
        jLabel3.setToolTipText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel89)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 385, Short.MAX_VALUE)
                                .addComponent(jLabel92))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 17, Short.MAX_VALUE)))
                        .addContainerGap(83, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel92)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel89)
                        .addContainerGap(422, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLabel88.setFont(new java.awt.Font("Damascus", 0, 14)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setText("Iniciar Sesion");

        jLabel90.setFont(new java.awt.Font("Damascus", 0, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("USERNAME");

        passwordField.setBackground(new java.awt.Color(36, 47, 65));
        passwordField.setForeground(new java.awt.Color(255, 255, 255));
        passwordField.setText("passpass");
        passwordField.setToolTipText("Ingresar contraseña");
        passwordField.setBorder(null);
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFieldFocusLost(evt);
            }
        });
        passwordField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordFieldMouseClicked(evt);
            }
        });

        usernameField.setBackground(new java.awt.Color(36, 47, 65));
        usernameField.setFont(new java.awt.Font("DialogInput", 0, 12)); // NOI18N
        usernameField.setForeground(new java.awt.Color(255, 255, 255));
        usernameField.setText("Ingresar Usuario");
        usernameField.setToolTipText("Ingresar Usuario");
        usernameField.setBorder(null);
        usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameFieldFocusLost(evt);
            }
        });
        usernameField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameFieldMouseClicked(evt);
            }
        });
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        jLabel93.setFont(new java.awt.Font("Damascus", 0, 14)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("PASSWORD:");

        jLabel91.setFont(new java.awt.Font("Damascus", 1, 12)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Cerrar");
        jLabel91.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel91.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel91.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel91MouseClicked(evt);
            }
        });

        iniciarButton.setForeground(new java.awt.Color(255, 255, 255));
        iniciarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Iniciar_Default.png"))); // NOI18N
        iniciarButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iniciarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                iniciarButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                iniciarButtonMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iniciarButtonMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                iniciarButtonMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                iniciarButtonMouseEntered(evt);
            }
        });

        errorLogin.setForeground(new java.awt.Color(255, 51, 51));
        errorLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator2)
                                .addComponent(jSeparator4)
                                .addComponent(passwordField)
                                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(iniciarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel91)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(161, Short.MAX_VALUE))
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(452, 452, 452)
                .addComponent(errorLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(iniciarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addComponent(errorLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 50, Short.MAX_VALUE))
        );

        mainMasterPanel.add(loginPanel, "loginPanel");

        backgroundPanel.setBackground(new java.awt.Color(204, 255, 255));

        headerMainPanel.setLayout(new java.awt.GridBagLayout());

        headerSecondPanel.setOpaque(false);

        ventasLabel.setForeground(new java.awt.Color(255, 255, 255));
        ventasLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ventasLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ventasLabelMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ventasLabelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ventasLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ventasLabelMouseEntered(evt);
            }
        });

        headerBackgroundImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Header_Default.png"))); // NOI18N
        headerBackgroundImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                headerBackgroundImageMouseExited(evt);
            }
        });

        inventarioLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                inventarioLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                inventarioLabelMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                inventarioLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                inventarioLabelMouseEntered(evt);
            }
        });

        clientesLabel.setSize(new java.awt.Dimension(55, 15));
        clientesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                clientesLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                clientesLabelMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clientesLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clientesLabelMouseEntered(evt);
            }
        });

        proveedoresLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                proveedoresLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                proveedoresLabelMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                proveedoresLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                proveedoresLabelMouseEntered(evt);
            }
        });

        comprasLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                comprasLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                comprasLabelMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                comprasLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                comprasLabelMouseEntered(evt);
            }
        });

        configuracionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                configuracionLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                configuracionLabelMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                configuracionLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                configuracionLabelMouseEntered(evt);
            }
        });

        corteLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                corteLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                corteLabelMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                corteLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                corteLabelMouseEntered(evt);
            }
        });

        salirLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                salirLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                salirLabelMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                salirLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                salirLabelMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout headerSecondPanelLayout = new javax.swing.GroupLayout(headerSecondPanel);
        headerSecondPanel.setLayout(headerSecondPanelLayout);
        headerSecondPanelLayout.setHorizontalGroup(
            headerSecondPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerSecondPanelLayout.createSequentialGroup()
                .addComponent(ventasLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventarioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(proveedoresLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comprasLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118)
                .addComponent(configuracionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(corteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salirLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
            .addGroup(headerSecondPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(headerSecondPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(headerBackgroundImage)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        headerSecondPanelLayout.setVerticalGroup(
            headerSecondPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ventasLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(inventarioLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(clientesLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(proveedoresLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(comprasLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(configuracionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(corteLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(salirLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
            .addGroup(headerSecondPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(headerSecondPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(headerBackgroundImage)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        headerMainPanel.add(headerSecondPanel, gridBagConstraints);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setLayout(new java.awt.CardLayout());

        ventasPanel.setBackground(new java.awt.Color(204, 255, 255));

        nombreLabelSearch.setText("Nombre de Producto:");

        venderButton.setText("Vender Producto");
        venderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venderButtonActionPerformed(evt);
            }
        });

        brownBackground.setBackground(new java.awt.Color(255, 255, 255));
        brownBackground.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tableHeaders.setBackground(new java.awt.Color(36, 47, 65));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Descripcion Del Producto");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Precio Venta");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cant.");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Importe");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Existencias");

        javax.swing.GroupLayout tableHeadersLayout = new javax.swing.GroupLayout(tableHeaders);
        tableHeaders.setLayout(tableHeadersLayout);
        tableHeadersLayout.setHorizontalGroup(
            tableHeadersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tableHeadersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(59, 59, 59)
                .addComponent(jLabel9)
                .addGap(130, 130, 130)
                .addComponent(jLabel12)
                .addGap(144, 144, 144)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(138, 138, 138))
        );
        tableHeadersLayout.setVerticalGroup(
            tableHeadersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tableHeadersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tableHeadersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ventasBottomPanel.setBackground(new java.awt.Color(204, 255, 255));
        ventasBottomPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton2.setText("Cobrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel15.setText("Total:");

        jLabel16.setText("00.00");

        jLabel1.setText("RFC:");

        jButton1.setText("Cancelar Venta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buscarClienteVentaField.setEditable(true);
        buscarClienteVentaField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarClienteVentaFieldActionPerformed(evt);
            }
        });

        errorVentas.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout ventasBottomPanelLayout = new javax.swing.GroupLayout(ventasBottomPanel);
        ventasBottomPanel.setLayout(ventasBottomPanelLayout);
        ventasBottomPanelLayout.setHorizontalGroup(
            ventasBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ventasBottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(106, 106, 106)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscarClienteVentaField, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(errorVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );
        ventasBottomPanelLayout.setVerticalGroup(
            ventasBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ventasBottomPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ventasBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(errorVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ventasBottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jLabel15)
                        .addComponent(jLabel16)
                        .addComponent(jLabel1)
                        .addComponent(jButton1)
                        .addComponent(buscarClienteVentaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        todosProductosVentasTable.setModel(modelVentas);
        jScrollPane3.setViewportView(todosProductosVentasTable);

        javax.swing.GroupLayout brownBackgroundLayout = new javax.swing.GroupLayout(brownBackground);
        brownBackground.setLayout(brownBackgroundLayout);
        brownBackgroundLayout.setHorizontalGroup(
            brownBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableHeaders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ventasBottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        brownBackgroundLayout.setVerticalGroup(
            brownBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(brownBackgroundLayout.createSequentialGroup()
                .addComponent(tableHeaders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(ventasBottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setText("Ticket:");

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setText("#######");

        buscarProductoField.setEditable(true);

        jLabel137.setText("Cantidad:");

        javax.swing.GroupLayout ventasPanelLayout = new javax.swing.GroupLayout(ventasPanel);
        ventasPanel.setLayout(ventasPanelLayout);
        ventasPanelLayout.setHorizontalGroup(
            ventasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombreLabelSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buscarProductoField, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cantidadVenderField, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(venderButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addContainerGap())
            .addComponent(brownBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ventasPanelLayout.setVerticalGroup(
            ventasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ventasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreLabelSearch)
                    .addComponent(venderButton)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(buscarProductoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel137)
                    .addComponent(cantidadVenderField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brownBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(ventasPanel, "ventasPanel");

        inventarioPanel.setBackground(new java.awt.Color(204, 255, 255));

        inventarioSubPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        inventarioSubPanel.setLayout(new java.awt.CardLayout());

        invTodosPanel.setBackground(new java.awt.Color(255, 255, 255));

        todosProductosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        todosProductosTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        todosProductosTable.setGridColor(new java.awt.Color(204, 204, 204));
        todosProductosTable.setSelectionBackground(new java.awt.Color(0, 51, 153));
        todosProductosTable.getTableHeader().setResizingAllowed(false);
        todosProductosTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(todosProductosTable);
        if (todosProductosTable.getColumnModel().getColumnCount() > 0) {
            todosProductosTable.getColumnModel().getColumn(0).setResizable(false);
            todosProductosTable.getColumnModel().getColumn(1).setResizable(false);
            todosProductosTable.getColumnModel().getColumn(2).setResizable(false);
            todosProductosTable.getColumnModel().getColumn(3).setResizable(false);
            todosProductosTable.getColumnModel().getColumn(4).setResizable(false);
            todosProductosTable.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout invTodosPanelLayout = new javax.swing.GroupLayout(invTodosPanel);
        invTodosPanel.setLayout(invTodosPanelLayout);
        invTodosPanelLayout.setHorizontalGroup(
            invTodosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
        );
        invTodosPanelLayout.setVerticalGroup(
            invTodosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
        );

        inventarioSubPanel.add(invTodosPanel, "invTodosPanel");

        invBajosPanel.setBackground(new java.awt.Color(255, 255, 255));

        todosBajosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(todosBajosTable);

        javax.swing.GroupLayout invBajosPanelLayout = new javax.swing.GroupLayout(invBajosPanel);
        invBajosPanel.setLayout(invBajosPanelLayout);
        invBajosPanelLayout.setHorizontalGroup(
            invBajosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
        );
        invBajosPanelLayout.setVerticalGroup(
            invBajosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
        );

        inventarioSubPanel.add(invBajosPanel, "invBajosPanel");

        invAgregarPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(43, 66, 150));
        jLabel22.setText("Agregar Producto");

        jLabel56.setForeground(new java.awt.Color(43, 66, 150));
        jLabel56.setText("Ingrese informacion de producto a agregar:");

        jLabel57.setText("Nombre:");

        jLabel58.setText("Precio Compra:");

        jLabel59.setText("Precio Venta:");

        jLabel60.setText("Existencias:");

        jLabel61.setText("Stock Minimo:");

        inventarioAgregarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventarioAgregarNombreActionPerformed(evt);
            }
        });

        agregarCancelarProductoButton.setText("Cancelar");
        agregarCancelarProductoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarCancelarProductoButtonActionPerformed(evt);
            }
        });

        agregarGuardarProductoButton.setText("Guardar");
        agregarGuardarProductoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarGuardarProductoButtonActionPerformed(evt);
            }
        });

        errorYaExiste.setForeground(new java.awt.Color(255, 0, 0));

        successMessage.setForeground(new java.awt.Color(0, 102, 0));

        javax.swing.GroupLayout invAgregarPanelLayout = new javax.swing.GroupLayout(invAgregarPanel);
        invAgregarPanel.setLayout(invAgregarPanelLayout);
        invAgregarPanelLayout.setHorizontalGroup(
            invAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invAgregarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(invAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22)
                    .addComponent(jLabel56)
                    .addGroup(invAgregarPanelLayout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inventarioAgregarNombre))
                    .addGroup(invAgregarPanelLayout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inventarioAgregarPC))
                    .addGroup(invAgregarPanelLayout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inventarioAgregarPV))
                    .addGroup(invAgregarPanelLayout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inventarioAgregarExistencias))
                    .addGroup(invAgregarPanelLayout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(invAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inventarioAgregarStock)
                            .addGroup(invAgregarPanelLayout.createSequentialGroup()
                                .addComponent(agregarCancelarProductoButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(agregarGuardarProductoButton)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGroup(invAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(invAgregarPanelLayout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(errorYaExiste, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(invAgregarPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(successMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        invAgregarPanelLayout.setVerticalGroup(
            invAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invAgregarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(invAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(errorYaExiste))
                .addGap(51, 51, 51)
                .addGroup(invAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(inventarioAgregarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(invAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(inventarioAgregarPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(invAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(inventarioAgregarPV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(invAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(inventarioAgregarExistencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(invAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(inventarioAgregarStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                .addGroup(invAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarCancelarProductoButton)
                    .addComponent(agregarGuardarProductoButton)
                    .addComponent(successMessage))
                .addGap(69, 69, 69))
        );

        inventarioSubPanel.add(invAgregarPanel, "invAgregarPanel");

        invModificarPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel62.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(43, 66, 150));
        jLabel62.setText("Modificar Producto");

        jLabel63.setForeground(new java.awt.Color(43, 66, 150));
        jLabel63.setText("Indicar producto a modificar:");

        jLabel64.setText("ID:");

        jLabel65.setText("Nombre:");

        jLabel66.setText("Precio Compra:");

        jLabel67.setText("Precio Venta:");

        jLabel68.setText("Existencias:");

        jLabel69.setText("Stock Minimo:");

        jTextField19.setEditable(false);
        jTextField19.setBackground(new java.awt.Color(204, 204, 204));

        jButton11.setText("Cancelar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        guardarCambioButton.setText("Guardar Cambio");
        guardarCambioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarCambioButtonActionPerformed(evt);
            }
        });

        jButton16.setText("Buscar");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));

        successLabel.setForeground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout invModificarPanelLayout = new javax.swing.GroupLayout(invModificarPanel);
        invModificarPanel.setLayout(invModificarPanelLayout);
        invModificarPanelLayout.setHorizontalGroup(
            invModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invModificarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(invModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(invModificarPanelLayout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guardarCambioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(successLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel62)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, invModificarPanelLayout.createSequentialGroup()
                        .addGroup(invModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, invModificarPanelLayout.createSequentialGroup()
                                .addComponent(jLabel64)
                                .addGap(107, 107, 107)
                                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, invModificarPanelLayout.createSequentialGroup()
                                .addComponent(jLabel63)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modBuscarField, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(invModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, invModificarPanelLayout.createSequentialGroup()
                            .addComponent(jLabel66)
                            .addGap(31, 31, 31)
                            .addComponent(jTextField21))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, invModificarPanelLayout.createSequentialGroup()
                            .addComponent(jLabel65)
                            .addGap(72, 72, 72)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel68, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, invModificarPanelLayout.createSequentialGroup()
                            .addComponent(jLabel67)
                            .addGap(45, 45, 45)
                            .addGroup(invModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField24)
                                .addComponent(jTextField22)
                                .addComponent(jTextField23)))))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        invModificarPanelLayout.setVerticalGroup(
            invModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invModificarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(invModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(invModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel63)
                        .addComponent(modBuscarField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(invModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(invModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(invModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(invModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(invModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(invModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(invModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(guardarCambioButton)
                    .addComponent(successLabel))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        inventarioSubPanel.add(invModificarPanel, "invModificarPanel");

        invBorrarPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(43, 66, 150));
        jLabel20.setText("Borrar Producto");

        jLabel70.setForeground(new java.awt.Color(43, 66, 150));
        jLabel70.setText("Seleccionar el producto que desea borrar:");

        jButton15.setText("Cancelar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        borrarButton.setText("Borrar");
        borrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarButtonActionPerformed(evt);
            }
        });

        borrarBuscarButton.setText("Buscar");
        borrarBuscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarBuscarButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre:");

        jLabel125.setText("Precio Compra:");

        jLabel126.setText("Precio Venta:");

        jLabel127.setText("Existencias:");

        jLabel128.setText("Stock Minimo:");

        borrarNombreField.setEditable(false);

        borrarPCField.setEditable(false);

        borrarPVField.setEditable(false);

        borrarExistenciasField.setEditable(false);

        borrarStockField.setEditable(false);

        errorBorrar.setForeground(new java.awt.Color(255, 0, 0));

        successBorrado.setForeground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout invBorrarPanelLayout = new javax.swing.GroupLayout(invBorrarPanel);
        invBorrarPanel.setLayout(invBorrarPanelLayout);
        invBorrarPanelLayout.setHorizontalGroup(
            invBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invBorrarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(invBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addGroup(invBorrarPanelLayout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productoBorrarField, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(borrarBuscarButton)
                        .addGap(28, 28, 28)
                        .addComponent(errorBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel127)
                    .addComponent(jLabel128)
                    .addGroup(invBorrarPanelLayout.createSequentialGroup()
                        .addGroup(invBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(invBorrarPanelLayout.createSequentialGroup()
                                .addComponent(jButton15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(borrarButton))
                            .addGroup(invBorrarPanelLayout.createSequentialGroup()
                                .addGroup(invBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel125)
                                    .addComponent(jLabel126)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(invBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(borrarNombreField)
                                    .addComponent(borrarPCField)
                                    .addComponent(borrarPVField)
                                    .addComponent(borrarExistenciasField)
                                    .addComponent(borrarStockField, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(successBorrado, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        invBorrarPanelLayout.setVerticalGroup(
            invBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(invBorrarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(invBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(productoBorrarField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(borrarBuscarButton)
                    .addComponent(errorBorrar))
                .addGap(18, 18, 18)
                .addGroup(invBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(borrarNombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(invBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel125)
                    .addComponent(borrarPCField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(invBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel126)
                    .addComponent(borrarPVField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(invBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel127)
                    .addComponent(borrarExistenciasField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(invBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel128)
                    .addComponent(borrarStockField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addGroup(invBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(borrarButton)
                    .addComponent(successBorrado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
        );

        inventarioSubPanel.add(invBorrarPanel, "invBorrarPanel");

        invTodosButton.setText("Todos");
        invTodosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invTodosButtonActionPerformed(evt);
            }
        });

        invBajosButton.setText("Bajos En Inventario");
        invBajosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invBajosButtonActionPerformed(evt);
            }
        });

        invAgregarButton.setText("Agregar");
        invAgregarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invAgregarButtonActionPerformed(evt);
            }
        });

        invModificarButton.setText("Modificar");
        invModificarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invModificarButtonActionPerformed(evt);
            }
        });

        invBorrarButton.setText("Borrar");
        invBorrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invBorrarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inventarioPanelLayout = new javax.swing.GroupLayout(inventarioPanel);
        inventarioPanel.setLayout(inventarioPanelLayout);
        inventarioPanelLayout.setHorizontalGroup(
            inventarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventarioPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(invTodosButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invBajosButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(invAgregarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invModificarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invBorrarButton)
                .addContainerGap())
            .addComponent(inventarioSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        inventarioPanelLayout.setVerticalGroup(
            inventarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventarioPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(inventarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(invTodosButton)
                    .addComponent(invBajosButton)
                    .addComponent(invAgregarButton)
                    .addComponent(invModificarButton)
                    .addComponent(invBorrarButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inventarioSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(inventarioPanel, "inventarioPanel");

        clientesPanel.setBackground(new java.awt.Color(204, 255, 255));

        clientesSubPanel.setBackground(new java.awt.Color(255, 255, 255));
        clientesSubPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        clientesSubPanel.setLayout(new java.awt.CardLayout());

        clientesTodosPanel.setBackground(new java.awt.Color(255, 255, 255));

        clientesTodos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(clientesTodos);

        javax.swing.GroupLayout clientesTodosPanelLayout = new javax.swing.GroupLayout(clientesTodosPanel);
        clientesTodosPanel.setLayout(clientesTodosPanelLayout);
        clientesTodosPanelLayout.setHorizontalGroup(
            clientesTodosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
        );
        clientesTodosPanelLayout.setVerticalGroup(
            clientesTodosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
        );

        clientesSubPanel.add(clientesTodosPanel, "clientesTodosPanel");

        clientesAgregarPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(43, 66, 150));
        jLabel24.setText("Nuevo Cliente");

        jLabel37.setForeground(new java.awt.Color(43, 66, 150));
        jLabel37.setText("Llene la siguiente informacion acerca del nuevo cliente.");

        jLabel38.setText("Nombre:");

        jLabel39.setText("Apellido Paterno:");

        jLabel40.setText("Apellido Materno:");

        jLabel41.setText("RFC:");

        jLabel42.setText("Telefono:");

        clienteNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteNombreActionPerformed(evt);
            }
        });

        clienteApellidoPaterno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteApellidoPaternoActionPerformed(evt);
            }
        });

        clienteTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteTelefonoActionPerformed(evt);
            }
        });

        jButton5.setText("Cancelar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        clienteGuardar.setText("Guardar Cliente");
        clienteGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteGuardarActionPerformed(evt);
            }
        });

        agClienteSuccess.setForeground(new java.awt.Color(0, 153, 0));

        agClienteError.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout clientesAgregarPanelLayout = new javax.swing.GroupLayout(clientesAgregarPanel);
        clientesAgregarPanel.setLayout(clientesAgregarPanelLayout);
        clientesAgregarPanelLayout.setHorizontalGroup(
            clientesAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesAgregarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientesAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clientesAgregarPanelLayout.createSequentialGroup()
                        .addGroup(clientesAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addGroup(clientesAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel38)
                                .addComponent(jLabel39))
                            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(clientesAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(clienteApellidoPaterno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(clienteApellidoMaterno, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clienteRfc, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, clientesAgregarPanelLayout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clienteGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(agClienteSuccess))
                            .addComponent(clienteTelefono, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clienteNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(agClienteError))
                    .addComponent(jLabel24)
                    .addComponent(jLabel37))
                .addContainerGap(569, Short.MAX_VALUE))
        );
        clientesAgregarPanelLayout.setVerticalGroup(
            clientesAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesAgregarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addGap(18, 18, 18)
                .addGroup(clientesAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(clienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agClienteError))
                .addGap(18, 18, 18)
                .addGroup(clientesAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(clienteApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(clientesAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(clienteApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(clientesAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(clienteRfc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(clientesAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(clienteTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(clientesAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(clienteGuardar)
                    .addComponent(agClienteSuccess))
                .addContainerGap(167, Short.MAX_VALUE))
        );

        clientesSubPanel.add(clientesAgregarPanel, "clientesAgregarPanel");

        clientesModificarPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel43.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(43, 66, 150));
        jLabel43.setText("Modificar Cliente");

        jLabel44.setForeground(new java.awt.Color(43, 66, 150));
        jLabel44.setText("Indique cliente a modificar:");

        jButton7.setText("Cancelar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Guardar Cambios");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel25.setText("Nombre:");

        jLabel45.setText("Apellido Paterno:");

        jLabel46.setText("Apellido Materno:");

        jLabel47.setText("RFC:");

        jLabel48.setText("Telefono:");

        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        mClienteBuscarField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mClienteBuscarFieldActionPerformed(evt);
            }
        });

        mClienteBuscarButton.setText("Buscar");
        mClienteBuscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mClienteBuscarButtonActionPerformed(evt);
            }
        });

        mClienteError.setForeground(new java.awt.Color(255, 0, 0));

        mClienteSuccess.setForeground(new java.awt.Color(0, 153, 51));

        javax.swing.GroupLayout clientesModificarPanelLayout = new javax.swing.GroupLayout(clientesModificarPanel);
        clientesModificarPanel.setLayout(clientesModificarPanelLayout);
        clientesModificarPanelLayout.setHorizontalGroup(
            clientesModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesModificarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientesModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, clientesModificarPanelLayout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mClienteBuscarField, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mClienteBuscarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mClienteError))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, clientesModificarPanelLayout.createSequentialGroup()
                        .addGroup(clientesModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, clientesModificarPanelLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(clientesModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(clientesModificarPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(clientesModificarPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel45)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(clientesModificarPanelLayout.createSequentialGroup()
                                        .addGroup(clientesModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel47)
                                            .addComponent(jLabel46))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(clientesModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(clientesModificarPanelLayout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(clientesModificarPanelLayout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mClienteSuccess)))
                .addContainerGap(498, Short.MAX_VALUE))
        );
        clientesModificarPanelLayout.setVerticalGroup(
            clientesModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesModificarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(clientesModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(mClienteBuscarField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mClienteBuscarButton)
                    .addComponent(mClienteError))
                .addGap(29, 29, 29)
                .addGroup(clientesModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(clientesModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(clientesModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(clientesModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(clientesModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(clientesModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton7)
                    .addComponent(mClienteSuccess))
                .addContainerGap(196, Short.MAX_VALUE))
        );

        clientesSubPanel.add(clientesModificarPanel, "clientesModificarPanel");

        clientesBorrarPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel49.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(43, 66, 150));
        jLabel49.setText("Borrar Cliente");

        jLabel50.setForeground(new java.awt.Color(43, 66, 150));
        jLabel50.setText("Seleccione cliente a borrar:");

        jButton9.setText("Cancelar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Borrar Cliente");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        bClienteBuscarButton.setText("Buscar");
        bClienteBuscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClienteBuscarButtonActionPerformed(evt);
            }
        });

        jLabel34.setText("Nombre:");

        jLabel35.setText("Apellido Paterno:");

        jLabel36.setText("Apellido Materno:");

        jLabel51.setText("RFC:");

        jLabel52.setText("Telefono:");

        jTextField15.setEditable(false);
        jTextField15.setBackground(new java.awt.Color(230, 230, 230));

        jTextField16.setEditable(false);
        jTextField16.setBackground(new java.awt.Color(230, 230, 230));

        jTextField17.setEditable(false);
        jTextField17.setBackground(new java.awt.Color(230, 230, 230));

        jTextField18.setEditable(false);
        jTextField18.setBackground(new java.awt.Color(230, 230, 230));

        jTextField25.setEditable(false);
        jTextField25.setBackground(new java.awt.Color(230, 230, 230));

        bClienteError.setForeground(new java.awt.Color(204, 0, 0));

        bClienteSuccess.setForeground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout clientesBorrarPanelLayout = new javax.swing.GroupLayout(clientesBorrarPanel);
        clientesBorrarPanel.setLayout(clientesBorrarPanelLayout);
        clientesBorrarPanelLayout.setHorizontalGroup(
            clientesBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesBorrarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientesBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49)
                    .addGroup(clientesBorrarPanelLayout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bClienteBuscarField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(bClienteBuscarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bClienteError, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(clientesBorrarPanelLayout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bClienteSuccess))
                    .addGroup(clientesBorrarPanelLayout.createSequentialGroup()
                        .addGroup(clientesBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36)
                            .addComponent(jLabel51)
                            .addComponent(jLabel52))
                        .addGap(51, 51, 51)
                        .addGroup(clientesBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField15)
                            .addComponent(jTextField16)
                            .addComponent(jTextField17)
                            .addComponent(jTextField18)
                            .addComponent(jTextField25, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        clientesBorrarPanelLayout.setVerticalGroup(
            clientesBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesBorrarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(clientesBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clientesBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel50)
                        .addComponent(bClienteBuscarField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bClienteBuscarButton))
                    .addComponent(bClienteError, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(clientesBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(clientesBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(clientesBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(clientesBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(clientesBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                .addGroup(clientesBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton10)
                    .addComponent(bClienteSuccess))
                .addGap(77, 77, 77))
        );

        clientesSubPanel.add(clientesBorrarPanel, "clientesBorrarPanel");

        clientesTodosButton.setText("Todos");
        clientesTodosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesTodosButtonActionPerformed(evt);
            }
        });

        clientesAgregarButton.setText("Agregar");
        clientesAgregarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesAgregarButtonActionPerformed(evt);
            }
        });

        clientesModificarButton.setText("Modificar");
        clientesModificarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesModificarButtonActionPerformed(evt);
            }
        });

        clientesBorrarButton.setText("Borrar");
        clientesBorrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesBorrarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout clientesPanelLayout = new javax.swing.GroupLayout(clientesPanel);
        clientesPanel.setLayout(clientesPanelLayout);
        clientesPanelLayout.setHorizontalGroup(
            clientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(clientesTodosButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clientesAgregarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientesModificarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientesBorrarButton)
                .addContainerGap())
            .addComponent(clientesSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        clientesPanelLayout.setVerticalGroup(
            clientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientesTodosButton)
                    .addComponent(clientesAgregarButton)
                    .addComponent(clientesModificarButton)
                    .addComponent(clientesBorrarButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientesSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(clientesPanel, "clientesPanel");

        proveedoresPanel.setBackground(new java.awt.Color(204, 255, 255));

        proveedoresSubPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        proveedoresSubPanel.setLayout(new java.awt.CardLayout());

        provTodosPanel.setBackground(new java.awt.Color(255, 255, 255));

        tablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tablaProveedores);

        javax.swing.GroupLayout provTodosPanelLayout = new javax.swing.GroupLayout(provTodosPanel);
        provTodosPanel.setLayout(provTodosPanelLayout);
        provTodosPanelLayout.setHorizontalGroup(
            provTodosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
        );
        provTodosPanelLayout.setVerticalGroup(
            provTodosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
        );

        proveedoresSubPanel.add(provTodosPanel, "provTodosPanel");

        provAgregarPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(43, 66, 150));
        jLabel27.setText("Agregar Proveedor");

        jLabel74.setForeground(new java.awt.Color(43, 66, 150));
        jLabel74.setText("Ingrese la informacion del proveedor a agregar:");

        jLabel75.setText("Nombre:");

        jLabel76.setText("Apellido Paterno:");

        jLabel77.setText("Apellido Materno:");

        jLabel78.setText("RFC:");

        jLabel79.setText("Telefono:");

        nombreProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreProveedorActionPerformed(evt);
            }
        });

        apellidoPaternoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoPaternoProveedorActionPerformed(evt);
            }
        });

        jButton17.setText("Cancelar");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        guardarProveedor.setText("Guardar");
        guardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarProveedorActionPerformed(evt);
            }
        });

        aPError.setForeground(new java.awt.Color(255, 0, 0));

        aPSuccess.setForeground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout provAgregarPanelLayout = new javax.swing.GroupLayout(provAgregarPanel);
        provAgregarPanel.setLayout(provAgregarPanelLayout);
        provAgregarPanelLayout.setHorizontalGroup(
            provAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(provAgregarPanelLayout.createSequentialGroup()
                .addGroup(provAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(provAgregarPanelLayout.createSequentialGroup()
                        .addContainerGap(187, Short.MAX_VALUE)
                        .addGroup(provAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(provAgregarPanelLayout.createSequentialGroup()
                                .addGroup(provAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel76)
                                    .addComponent(jLabel77)
                                    .addComponent(jLabel79, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel78, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(provAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, provAgregarPanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(telefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(provAgregarPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(provAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(provAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(nombreProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                .addComponent(apellidoPaternoProveedor)
                                                .addComponent(apellidoMaternoProveedor))
                                            .addComponent(rfcProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(provAgregarPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jButton17)
                                .addGap(18, 18, 18)
                                .addComponent(guardarProveedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(aPSuccess))))
                    .addGroup(provAgregarPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(provAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel74))))
                .addGap(109, 109, 109)
                .addComponent(aPError)
                .addContainerGap(397, Short.MAX_VALUE))
        );
        provAgregarPanelLayout.setVerticalGroup(
            provAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(provAgregarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(provAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(aPError))
                .addGap(18, 18, 18)
                .addGroup(provAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(nombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(provAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(apellidoPaternoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(provAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel77)
                    .addComponent(apellidoMaternoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(provAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(rfcProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(provAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(telefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(provAgregarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17)
                    .addComponent(guardarProveedor)
                    .addComponent(aPSuccess))
                .addContainerGap(155, Short.MAX_VALUE))
        );

        proveedoresSubPanel.add(provAgregarPanel, "provAgregarPanel");

        provModificarPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel80.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(43, 66, 150));
        jLabel80.setText("Modificar Proveedor");

        jLabel81.setForeground(new java.awt.Color(43, 66, 150));
        jLabel81.setText("Ingrese el nombre del proveedor a modificar:");

        jLabel28.setText("Nombre:");

        jLabel82.setText("Apellido Paterno:");

        jLabel83.setText("Apellido Materno:");

        jLabel84.setText("RFC:");

        jLabel85.setText("Telefono:");

        jButton19.setText("Cancelar");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setText("Guardar Cambios");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jTextField31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31ActionPerformed(evt);
            }
        });

        modProveedorBuscarButton.setText("Buscar");
        modProveedorBuscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modProveedorBuscarButtonActionPerformed(evt);
            }
        });

        mProveedorError.setForeground(new java.awt.Color(255, 0, 0));

        mProveedorSuccess.setForeground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout provModificarPanelLayout = new javax.swing.GroupLayout(provModificarPanel);
        provModificarPanel.setLayout(provModificarPanelLayout);
        provModificarPanelLayout.setHorizontalGroup(
            provModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(provModificarPanelLayout.createSequentialGroup()
                .addGroup(provModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(provModificarPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(provModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(provModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel80)
                                .addGroup(provModificarPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel28)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField30))
                                .addGroup(provModificarPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel82)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField31))
                                .addGroup(provModificarPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel83)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField32, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                                .addGroup(provModificarPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel84)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField33))
                                .addGroup(provModificarPanelLayout.createSequentialGroup()
                                    .addComponent(jLabel85)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField34)))
                            .addGroup(provModificarPanelLayout.createSequentialGroup()
                                .addComponent(jLabel81)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modProveedorBuscarField, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(modProveedorBuscarButton)
                                .addGap(51, 51, 51)
                                .addComponent(mProveedorError))))
                    .addGroup(provModificarPanelLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jButton19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mProveedorSuccess)))
                .addContainerGap(395, Short.MAX_VALUE))
        );
        provModificarPanelLayout.setVerticalGroup(
            provModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(provModificarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(provModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(modProveedorBuscarField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modProveedorBuscarButton)
                    .addComponent(mProveedorError))
                .addGap(27, 27, 27)
                .addGroup(provModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(provModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(provModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83)
                    .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(provModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(provModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(provModificarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton19)
                    .addComponent(jButton20)
                    .addComponent(mProveedorSuccess))
                .addContainerGap(163, Short.MAX_VALUE))
        );

        proveedoresSubPanel.add(provModificarPanel, "provModificarPanel");

        provBorrarPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel86.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(43, 66, 150));
        jLabel86.setText("Borrar Proveedor");

        jLabel87.setForeground(new java.awt.Color(43, 66, 150));
        jLabel87.setText("Ingrese el nombre del proveedor a borrar:");

        jButton21.setText("Cancelar");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setText("Borrar Proveedor");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jLabel4.setText("Nombre:");

        jLabel21.setText("Apellido Paterno:");

        jLabel23.setText("Apellido Materno:");

        jLabel26.setText("RFC:");

        jLabel33.setText("Telefono:");

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        boProveedorBuscarButton.setText("Buscar");
        boProveedorBuscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boProveedorBuscarButtonActionPerformed(evt);
            }
        });

        boProveedorError.setForeground(new java.awt.Color(204, 0, 51));

        boProveedorSuccess.setForeground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout provBorrarPanelLayout = new javax.swing.GroupLayout(provBorrarPanel);
        provBorrarPanel.setLayout(provBorrarPanelLayout);
        provBorrarPanelLayout.setHorizontalGroup(
            provBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(provBorrarPanelLayout.createSequentialGroup()
                .addGroup(provBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(provBorrarPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(provBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(provBorrarPanelLayout.createSequentialGroup()
                                .addGroup(provBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(provBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(provBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(provBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                                    .addComponent(jTextField5)
                                    .addComponent(jTextField6)
                                    .addComponent(jTextField7)
                                    .addComponent(jTextField13)))
                            .addComponent(jLabel86)
                            .addGroup(provBorrarPanelLayout.createSequentialGroup()
                                .addComponent(jLabel87)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(boProveedorBuscarField, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(provBorrarPanelLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jButton21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boProveedorSuccess)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boProveedorBuscarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boProveedorError)
                .addGap(363, 363, 363))
        );
        provBorrarPanelLayout.setVerticalGroup(
            provBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(provBorrarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel86)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(provBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(boProveedorBuscarField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boProveedorBuscarButton)
                    .addComponent(boProveedorError))
                .addGap(18, 18, 18)
                .addGroup(provBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(provBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(provBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(provBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(provBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addGroup(provBorrarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton21)
                    .addComponent(jButton22)
                    .addComponent(boProveedorSuccess))
                .addGap(80, 80, 80))
        );

        proveedoresSubPanel.add(provBorrarPanel, "provBorrarPanel");

        provTodosButton.setText("Todos");
        provTodosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provTodosButtonActionPerformed(evt);
            }
        });

        provBorrarButton.setText("Borrar");
        provBorrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provBorrarButtonActionPerformed(evt);
            }
        });

        provModificarButton.setText("Modificar");
        provModificarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provModificarButtonActionPerformed(evt);
            }
        });

        provAgregarButton.setText("Agregar");
        provAgregarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provAgregarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout proveedoresPanelLayout = new javax.swing.GroupLayout(proveedoresPanel);
        proveedoresPanel.setLayout(proveedoresPanelLayout);
        proveedoresPanelLayout.setHorizontalGroup(
            proveedoresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proveedoresPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(provTodosButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(provAgregarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(provModificarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(provBorrarButton)
                .addContainerGap())
            .addComponent(proveedoresSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        proveedoresPanelLayout.setVerticalGroup(
            proveedoresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(proveedoresPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(proveedoresPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(provTodosButton)
                    .addComponent(provBorrarButton)
                    .addComponent(provModificarButton)
                    .addComponent(provAgregarButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(proveedoresSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(proveedoresPanel, "proveedoresPanel");

        comprasPanel.setBackground(new java.awt.Color(255, 255, 255));
        comprasPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel96.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(43, 66, 150));
        jLabel96.setText("Comprar Producto");

        jLabel97.setForeground(new java.awt.Color(43, 66, 150));
        jLabel97.setText("Ingrese informacion de producto a comprar:");

        jLabel98.setText("Proveedor:");

        jLabel99.setText("Producto:");

        jLabel100.setText("Cantidad:");

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton13.setText("Cancelar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton3.setText("Comprar");

        javax.swing.GroupLayout comprasPanelLayout = new javax.swing.GroupLayout(comprasPanel);
        comprasPanel.setLayout(comprasPanelLayout);
        comprasPanelLayout.setHorizontalGroup(
            comprasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comprasPanelLayout.createSequentialGroup()
                .addGroup(comprasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(comprasPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(comprasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel96)
                            .addComponent(jLabel97)
                            .addGroup(comprasPanelLayout.createSequentialGroup()
                                .addGroup(comprasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel98)
                                    .addComponent(jLabel99)
                                    .addComponent(jLabel100))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(comprasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox10, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox9, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField36)))))
                    .addGroup(comprasPanelLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jButton13)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap(736, Short.MAX_VALUE))
        );
        comprasPanelLayout.setVerticalGroup(
            comprasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(comprasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel96)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel97)
                .addGap(24, 24, 24)
                .addGroup(comprasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(comprasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel99)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(comprasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100)
                    .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(comprasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jButton3))
                .addContainerGap(283, Short.MAX_VALUE))
        );

        mainPanel.add(comprasPanel, "comprasPanel");

        configuracionPanel.setBackground(new java.awt.Color(204, 255, 255));

        configuracionSubPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        configuracionSubPanel.setLayout(new java.awt.CardLayout());

        configuracionUsuariosPanel.setBackground(new java.awt.Color(255, 255, 255));
        configuracionUsuariosPanel.setLayout(new java.awt.CardLayout());

        todosUsuariosPanel.setBackground(new java.awt.Color(255, 255, 255));
        todosUsuariosPanel.setForeground(new java.awt.Color(0, 0, 0));

        tableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableUsuarios.setFillsViewportHeight(true);
        tableUsuarios.setOpaque(false);
        tableUsuarios.setShowGrid(false);
        jScrollPane6.setViewportView(tableUsuarios);

        jButton6.setText("Borrar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton12.setText("Modificar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton18.setText("Agregar");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton24.setText("Todos");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        mainUsuariosError.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout todosUsuariosPanelLayout = new javax.swing.GroupLayout(todosUsuariosPanel);
        todosUsuariosPanel.setLayout(todosUsuariosPanelLayout);
        todosUsuariosPanelLayout.setHorizontalGroup(
            todosUsuariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, todosUsuariosPanelLayout.createSequentialGroup()
                .addComponent(jButton24)
                .addGap(295, 295, 295)
                .addComponent(mainUsuariosError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 394, Short.MAX_VALUE)
                .addComponent(jButton18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6))
            .addGroup(todosUsuariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE))
        );
        todosUsuariosPanelLayout.setVerticalGroup(
            todosUsuariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(todosUsuariosPanelLayout.createSequentialGroup()
                .addGroup(todosUsuariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(todosUsuariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton24)
                        .addComponent(mainUsuariosError))
                    .addGroup(todosUsuariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(467, Short.MAX_VALUE))
            .addGroup(todosUsuariosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, todosUsuariosPanelLayout.createSequentialGroup()
                    .addGap(0, 27, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        configuracionUsuariosPanel.add(todosUsuariosPanel, "todosUsuariosPanel");

        aUsuarioPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 153));
        jLabel6.setText("Ingresar datos de usuario que desea agregar:");

        jLabel7.setText("Username:");

        jLabel53.setText("Password:");

        jLabel54.setText("Nombre:");

        jLabel55.setText("Apellido Paterno:");

        jLabel71.setText("Apellido Materno:");

        jButton25.setText("Guardar");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setText("Cancelar");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        aUsuarioError.setForeground(new java.awt.Color(255, 0, 0));

        aUsuarioSuccess.setForeground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout aUsuarioPanelLayout = new javax.swing.GroupLayout(aUsuarioPanel);
        aUsuarioPanel.setLayout(aUsuarioPanelLayout);
        aUsuarioPanelLayout.setHorizontalGroup(
            aUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aUsuarioPanelLayout.createSequentialGroup()
                .addGroup(aUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aUsuarioPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(aUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(aUsuarioPanelLayout.createSequentialGroup()
                                .addGroup(aUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel53)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel54)
                                    .addComponent(jLabel55)
                                    .addComponent(jLabel71))
                                .addGap(33, 33, 33)
                                .addGroup(aUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField14)
                                    .addComponent(jTextField27)
                                    .addComponent(jTextField28)
                                    .addComponent(jTextField29, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                    .addComponent(jPasswordField1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(aUsuarioError))))
                    .addGroup(aUsuarioPanelLayout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jButton26)
                        .addGap(18, 18, 18)
                        .addComponent(jButton25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aUsuarioSuccess)))
                .addContainerGap(613, Short.MAX_VALUE))
        );
        aUsuarioPanelLayout.setVerticalGroup(
            aUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aUsuarioPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(aUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aUsuarioError))
                .addGap(18, 18, 18)
                .addGroup(aUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(aUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(aUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(aUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                .addGroup(aUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton25)
                    .addComponent(jButton26)
                    .addComponent(aUsuarioSuccess))
                .addGap(57, 57, 57))
        );

        configuracionUsuariosPanel.add(aUsuarioPanel, "aUsuarioPanel");

        mUsuarioPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel72.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(0, 0, 153));
        jLabel72.setText("Ingresar datos a modificar y guardar cambios:");

        jLabel73.setText("Username:");

        jLabel94.setText("Password:");

        jLabel95.setText("Nombre:");

        jLabel129.setText("Apellido Paterno:");

        jLabel130.setText("Apellido Materno:");

        jButton27.setText("Guardar");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setText("Cancelar");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        aUsuarioError1.setForeground(new java.awt.Color(255, 0, 0));

        aUsuarioSuccess1.setForeground(new java.awt.Color(0, 153, 0));

        mUsuarioID.setEditable(false);
        mUsuarioID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mUsuarioIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mUsuarioPanelLayout = new javax.swing.GroupLayout(mUsuarioPanel);
        mUsuarioPanel.setLayout(mUsuarioPanelLayout);
        mUsuarioPanelLayout.setHorizontalGroup(
            mUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mUsuarioPanelLayout.createSequentialGroup()
                .addGroup(mUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mUsuarioPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72)
                            .addGroup(mUsuarioPanelLayout.createSequentialGroup()
                                .addGroup(mUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel94)
                                    .addComponent(jLabel73)
                                    .addComponent(jLabel95)
                                    .addComponent(jLabel129)
                                    .addComponent(jLabel130))
                                .addGap(33, 33, 33)
                                .addGroup(mUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField26)
                                    .addComponent(jTextField35)
                                    .addComponent(jTextField43)
                                    .addComponent(jTextField44, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                    .addComponent(jPasswordField2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(aUsuarioError1)))
                        .addGap(325, 325, 325))
                    .addGroup(mUsuarioPanelLayout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jButton28)
                        .addGap(18, 18, 18)
                        .addComponent(jButton27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aUsuarioSuccess1)))
                .addContainerGap(341, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mUsuarioPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mUsuarioID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        mUsuarioPanelLayout.setVerticalGroup(
            mUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mUsuarioPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel72)
                .addGap(18, 18, 18)
                .addGroup(mUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aUsuarioError1))
                .addGap(18, 18, 18)
                .addGroup(mUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel129)
                    .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel130)
                    .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                .addGroup(mUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton27)
                    .addComponent(jButton28)
                    .addComponent(aUsuarioSuccess1))
                .addGap(38, 38, 38)
                .addComponent(mUsuarioID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        configuracionUsuariosPanel.add(mUsuarioPanel, "mUsuarioPanel");

        bUsuarioPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel131.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel131.setForeground(new java.awt.Color(0, 0, 153));
        jLabel131.setText("Confirmar que realmente quiere borrar el usuario:");

        jLabel132.setText("Username:");

        jTextField45.setEditable(false);
        jTextField45.setBackground(new java.awt.Color(225, 224, 224));

        jPasswordField3.setEditable(false);
        jPasswordField3.setBackground(new java.awt.Color(225, 224, 224));

        jLabel133.setText("Nombre:");

        jLabel134.setText("Password:");

        jTextField46.setEditable(false);
        jTextField46.setBackground(new java.awt.Color(225, 224, 224));

        jTextField47.setEditable(false);
        jTextField47.setBackground(new java.awt.Color(225, 224, 224));

        jLabel135.setText("Apellido Paterno:");

        jLabel136.setText("Apellido Materno:");

        jTextField48.setEditable(false);
        jTextField48.setBackground(new java.awt.Color(225, 224, 224));

        jButton29.setText("Cancelar");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setText("Borrar");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        mUsuarioID1.setEditable(false);
        mUsuarioID1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mUsuarioID1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bUsuarioPanelLayout = new javax.swing.GroupLayout(bUsuarioPanel);
        bUsuarioPanel.setLayout(bUsuarioPanelLayout);
        bUsuarioPanelLayout.setHorizontalGroup(
            bUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bUsuarioPanelLayout.createSequentialGroup()
                .addContainerGap(966, Short.MAX_VALUE)
                .addComponent(mUsuarioID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(bUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bUsuarioPanelLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addGroup(bUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel131)
                        .addGroup(bUsuarioPanelLayout.createSequentialGroup()
                            .addGroup(bUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel134)
                                .addComponent(jLabel132)
                                .addComponent(jLabel133)
                                .addComponent(jLabel135)
                                .addComponent(jLabel136))
                            .addGap(33, 33, 33)
                            .addGroup(bUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField45)
                                .addComponent(jTextField46)
                                .addComponent(jTextField47)
                                .addComponent(jTextField48, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                .addComponent(jPasswordField3)))
                        .addGroup(bUsuarioPanelLayout.createSequentialGroup()
                            .addGap(196, 196, 196)
                            .addComponent(jButton29)
                            .addGap(18, 18, 18)
                            .addComponent(jButton30)))
                    .addContainerGap(642, Short.MAX_VALUE)))
        );
        bUsuarioPanelLayout.setVerticalGroup(
            bUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bUsuarioPanelLayout.createSequentialGroup()
                .addContainerGap(451, Short.MAX_VALUE)
                .addComponent(mUsuarioID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(bUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(bUsuarioPanelLayout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(jLabel131)
                    .addGap(18, 18, 18)
                    .addGroup(bUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel132)
                        .addComponent(jTextField45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(bUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel134)
                        .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(bUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel133)
                        .addComponent(jTextField46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(bUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel135)
                        .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(bUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel136)
                        .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                    .addGroup(bUsuarioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton30)
                        .addComponent(jButton29))
                    .addGap(63, 63, 63)))
        );

        configuracionUsuariosPanel.add(bUsuarioPanel, "bUsuarioPanel");

        configuracionSubPanel.add(configuracionUsuariosPanel, "configuracionUsuariosPanel");

        configuracionTicketPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel107.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(43, 66, 150));
        jLabel107.setText("Personalizacion del Ticket de Venta");

        jPanel4.setBackground(new java.awt.Color(245, 243, 243));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTextField2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextField2.setText("Mi Tiendita Barajas");
        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField37.setText("Direccion 123 Col. Colonia");
        jTextField37.setBorder(null);

        jTextField38.setText("(333) 123 4567");
        jTextField38.setBorder(null);

        jTextField39.setText("RFC012345656969");
        jTextField39.setBorder(null);

        jTextField40.setBorder(null);

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel32.setText("25 de Julio de 2017");

        jLabel108.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel108.setText("3:09 PM");

        jLabel109.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel109.setText("Cant.");

        jLabel110.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel110.setText("Descripcion");

        jLabel111.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jLabel111.setText("Importe");

        jLabel112.setText("1");

        jLabel113.setText("Agua Ciel");

        jLabel114.setText("$7.00");

        jLabel115.setText("1");

        jLabel116.setText("Coca Cola");

        jLabel117.setText("$8.00");

        jLabel118.setText("No. de Articulos:");

        jLabel119.setText("2");

        jLabel120.setText("Total: $15.00");

        jTextField41.setText("Gracias por su compra");
        jTextField41.setBorder(null);
        jTextField41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField41ActionPerformed(evt);
            }
        });

        jTextField42.setText("www.juanbarajas.com");
        jTextField42.setBorder(null);
        jTextField42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField42ActionPerformed(evt);
            }
        });

        jLabel121.setText("Ticket #:  123456");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField41))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator6)
                            .addComponent(jTextField40, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField39, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField38, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField37, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addComponent(jLabel108)
                                .addGap(22, 22, 22))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel109)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel110)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel111))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel112)
                                    .addComponent(jLabel115))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel116)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel117))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel113)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel114))))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel120)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel118)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel119)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField42)))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel121)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel121)
                .addGap(23, 23, 23)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel108))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(jLabel110)
                    .addComponent(jLabel111))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel112)
                    .addComponent(jLabel113)
                    .addComponent(jLabel114))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel115)
                    .addComponent(jLabel116)
                    .addComponent(jLabel117))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel118)
                    .addComponent(jLabel119))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel120)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jButton14.setText("Cancelar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton23.setText("Guardar");

        jLabel122.setForeground(new java.awt.Color(43, 66, 150));
        jLabel122.setText("Puede personalizar los campos abiertos del siguiente formato:");

        javax.swing.GroupLayout configuracionTicketPanelLayout = new javax.swing.GroupLayout(configuracionTicketPanel);
        configuracionTicketPanel.setLayout(configuracionTicketPanelLayout);
        configuracionTicketPanelLayout.setHorizontalGroup(
            configuracionTicketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configuracionTicketPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configuracionTicketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel107)
                    .addComponent(jLabel122))
                .addGap(115, 115, 115)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(jButton14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton23)
                .addContainerGap())
        );
        configuracionTicketPanelLayout.setVerticalGroup(
            configuracionTicketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configuracionTicketPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configuracionTicketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(configuracionTicketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton14)
                        .addComponent(jButton23))
                    .addGroup(configuracionTicketPanelLayout.createSequentialGroup()
                        .addComponent(jLabel107)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel122))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        configuracionSubPanel.add(configuracionTicketPanel, "configuracionTicketPanel");

        confUsuariosPanel.setText("Usuarios");
        confUsuariosPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confUsuariosPanelActionPerformed(evt);
            }
        });

        confTicketPanel.setText("Ticket");
        confTicketPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confTicketPanelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout configuracionPanelLayout = new javax.swing.GroupLayout(configuracionPanel);
        configuracionPanel.setLayout(configuracionPanelLayout);
        configuracionPanelLayout.setHorizontalGroup(
            configuracionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configuracionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(confUsuariosPanel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confTicketPanel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(configuracionSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        configuracionPanelLayout.setVerticalGroup(
            configuracionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configuracionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configuracionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confUsuariosPanel)
                    .addComponent(confTicketPanel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(configuracionSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(configuracionPanel, "configuracionPanel");

        cortePanel.setBackground(new java.awt.Color(255, 255, 255));
        cortePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Corte Del ");

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 102));
        jLabel19.setText("21/07/2017");

        jSeparator5.setForeground(new java.awt.Color(204, 204, 204));

        jButton4.setText("Hacer Corte Del Dia");

        jLabel30.setText("Ventas Totales");

        jLabel31.setText("Cantidad de Tickets");

        jLabel101.setText("Numero de Productos Vendidos");

        jLabel102.setForeground(new java.awt.Color(0, 153, 0));
        jLabel102.setText("$100.00");

        jLabel103.setText("39");

        jLabel104.setText("229");

        jLabel105.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(102, 102, 102));
        jLabel105.setText("Usuario:");

        jLabel106.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(0, 0, 102));
        jLabel106.setText("Admin");

        jLabel123.setText("Saldo al Realizar Corte");

        jLabel124.setForeground(new java.awt.Color(0, 153, 0));
        jLabel124.setText("$200.00");

        javax.swing.GroupLayout cortePanelLayout = new javax.swing.GroupLayout(cortePanel);
        cortePanel.setLayout(cortePanelLayout);
        cortePanelLayout.setHorizontalGroup(
            cortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addGroup(cortePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cortePanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addGap(114, 114, 114)
                        .addComponent(jLabel105)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel106)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 365, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addGroup(cortePanelLayout.createSequentialGroup()
                        .addGroup(cortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cortePanelLayout.createSequentialGroup()
                                .addGroup(cortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel101)
                                    .addComponent(jLabel123))
                                .addGap(30, 30, 30)
                                .addGroup(cortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel104)
                                    .addComponent(jLabel124)))
                            .addGroup(cortePanelLayout.createSequentialGroup()
                                .addGroup(cortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel31))
                                .addGap(102, 102, 102)
                                .addGroup(cortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel103)
                                    .addComponent(jLabel102))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        cortePanelLayout.setVerticalGroup(
            cortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cortePanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(cortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel19)
                        .addComponent(jLabel105)
                        .addComponent(jLabel106))
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel102))
                .addGap(18, 18, 18)
                .addGroup(cortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel103))
                .addGap(18, 18, 18)
                .addGroup(cortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101)
                    .addComponent(jLabel104))
                .addGap(18, 18, 18)
                .addGroup(cortePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel123)
                    .addComponent(jLabel124))
                .addContainerGap(350, Short.MAX_VALUE))
        );

        mainPanel.add(cortePanel, "cortePanel");

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(headerMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                .addComponent(headerMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mainMasterPanel.add(backgroundPanel, "applicationPanel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainMasterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1025, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(mainMasterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clientesBorrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesBorrarButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) clientesSubPanel.getLayout();
        card.show(clientesSubPanel, "clientesBorrarPanel");
    }//GEN-LAST:event_clientesBorrarButtonActionPerformed

    private void clientesModificarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesModificarButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) clientesSubPanel.getLayout();
        card.show(clientesSubPanel, "clientesModificarPanel");
    }//GEN-LAST:event_clientesModificarButtonActionPerformed

    private void clientesAgregarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesAgregarButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) clientesSubPanel.getLayout();
        card.show(clientesSubPanel, "clientesAgregarPanel");
    }//GEN-LAST:event_clientesAgregarButtonActionPerformed

    private void clientesTodosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesTodosButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) clientesSubPanel.getLayout();
        card.show(clientesSubPanel, "clientesTodosPanel");
        DefaultTableModel model = null;
        try {
            model = clienControl.todosClientesDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        clientesTodos.setModel(model);


    }//GEN-LAST:event_clientesTodosButtonActionPerformed

    private void invBajosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invBajosButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) inventarioSubPanel.getLayout();
        card.show(inventarioSubPanel, "invBajosPanel");
        DefaultTableModel model = null;
        try {
            model = prodControl.todosProductosBajosDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        todosBajosTable.setModel(model);
    }//GEN-LAST:event_invBajosButtonActionPerformed

    private void invTodosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invTodosButtonActionPerformed
        // TODO add your handling code here:  
        CardLayout card = (CardLayout) inventarioSubPanel.getLayout();
        card.show(inventarioSubPanel, "invTodosPanel");
        DefaultTableModel model = null;
        try {
            model = prodControl.todosProductosDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        todosProductosTable.setModel(model);

    }//GEN-LAST:event_invTodosButtonActionPerformed

    private void provTodosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provTodosButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) proveedoresSubPanel.getLayout();
        card.show(proveedoresSubPanel, "provTodosPanel");
        DefaultTableModel model = null;
        try {
            model = provControl.todosProveedoresDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablaProveedores.setModel(model);
    }//GEN-LAST:event_provTodosButtonActionPerformed

    private void provAgregarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provAgregarButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) proveedoresSubPanel.getLayout();
        card.show(proveedoresSubPanel, "provAgregarPanel");
    }//GEN-LAST:event_provAgregarButtonActionPerformed

    private void provModificarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provModificarButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) proveedoresSubPanel.getLayout();
        card.show(proveedoresSubPanel, "provModificarPanel");
    }//GEN-LAST:event_provModificarButtonActionPerformed

    private void provBorrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provBorrarButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) proveedoresSubPanel.getLayout();
        card.show(proveedoresSubPanel, "provBorrarPanel");
    }//GEN-LAST:event_provBorrarButtonActionPerformed

    private void confUsuariosPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confUsuariosPanelActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) configuracionSubPanel.getLayout();
        card.show(configuracionSubPanel, "configuracionUsuariosPanel");
        CardLayout card2 = (CardLayout) configuracionUsuariosPanel.getLayout();
        card2.show(configuracionUsuariosPanel, "todosUsuariosPanel");

        UsuarioController userControl = new UsuarioController();
        DefaultTableModel model = null;
        try {
            model = userControl.todosUsuariosDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableUsuarios.setModel(model);
    }//GEN-LAST:event_confUsuariosPanelActionPerformed

    private void confTicketPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confTicketPanelActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) configuracionSubPanel.getLayout();
        card.show(configuracionSubPanel, "configuracionTicketPanel");
    }//GEN-LAST:event_confTicketPanelActionPerformed

    private void clienteApellidoPaternoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteApellidoPaternoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clienteApellidoPaternoActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) clientesSubPanel.getLayout();
        card.show(clientesSubPanel, "clientesTodosPanel");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) inventarioSubPanel.getLayout();
        card.show(inventarioSubPanel, "invTodosPanel");
        borrarNombreField.setText("");
        borrarPCField.setText("");
        borrarPVField.setText("");
        borrarExistenciasField.setText("");
        borrarStockField.setText("");
    }//GEN-LAST:event_jButton15ActionPerformed

    private void nombreProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreProveedorActionPerformed

    private void apellidoPaternoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoPaternoProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidoPaternoProveedorActionPerformed

    private void jTextField31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        ProveedorController borrarProv = new ProveedorController();
        try {
            if (borrarProv.buscarProveedor(boProveedorBuscarField.getText()) > 0) {
                try {
                    borrarProv.borrarProveedor(boProveedorBuscarField.getText());
                    boProveedorSuccess.setText("Borrado exitosamente!");
                    jTextField4.setText("");
                    jTextField5.setText("");
                    jTextField6.setText("");
                    jTextField7.setText("");
                    jTextField13.setText("");
                    boProveedorBuscarField.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                boProveedorError.setText("Favor de indicar proveedor a borrar.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jLabel91MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel91MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel91MouseClicked

    private void jLabel89MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel89MouseClicked
        // TODO add your handling code here:
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI("http://www.juanbarajas.com"));
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jLabel89MouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField41ActionPerformed

    private void jTextField42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField42ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void iniciarButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iniciarButtonMouseEntered
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Iniciar_Hover.png"));
        iniciarButton.setIcon(II);
    }//GEN-LAST:event_iniciarButtonMouseEntered

    private void iniciarButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iniciarButtonMouseExited
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Iniciar_Default.png"));
        iniciarButton.setIcon(II);
    }//GEN-LAST:event_iniciarButtonMouseExited

    private void iniciarButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iniciarButtonMousePressed
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Iniciar_Pressed.png"));
        iniciarButton.setIcon(II);
    }//GEN-LAST:event_iniciarButtonMousePressed

    private void iniciarButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iniciarButtonMouseReleased
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Iniciar_Hover.png"));
        iniciarButton.setIcon(II);

        Postgres postgres = new Postgres();
        loginAttempt = postgres.login(usernameField.getText(), passwordField.getText());
        if (loginAttempt) {
            try {
                //Set today's at cortes page
                jLabel19.setText(dtfday.format(localDate) + "/" + dtfmonth.format(localDate) + "/" + dtfyear.format(localDate));

                //Set currentUser object to the current user
                int userid;
                userid = userControl.buscarUsuario(usernameField.getText());
                currentUser.loadUserDetails(userid);

                //Calculate next ticket number
                ventaControl.siguienteTicket();
                corteControl.numeroCorte();

                //Set sales screen to display new sales ticket number              
                jLabel18.setText(String.valueOf(ventaControl.getTicketNumero()));

                //Set corte page to display username
                jLabel106.setText(currentUser.getNombre() + " " + currentUser.getApellidoPaterno() + " " + currentUser.getApellidoMaterno());

                CardLayout card = (CardLayout) mainMasterPanel.getLayout();
                card.show(mainMasterPanel, "applicationPanel");
            } catch (SQLException ex) {
                Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            errorLogin.setText("Usuario o Contraseña No Validos. Favor de Intentar de Nuevo.");
        }

    }//GEN-LAST:event_iniciarButtonMouseReleased

    private void usernameFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameFieldMouseClicked
        // TODO add your handling code here:
        if (usernameField.getText().equals("Ingresar Usuario")) {
            usernameField.setText("");
        }
    }//GEN-LAST:event_usernameFieldMouseClicked

    private void passwordFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordFieldMouseClicked
        // TODO add your handling code here:
        if (passwordField.getText().equals("passpass")) {
            passwordField.setText("");
        }
    }//GEN-LAST:event_passwordFieldMouseClicked

    private void usernameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldFocusGained

    private void passwordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldFocusGained

    private void usernameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFieldFocusLost
        // TODO add your handling code here:
        if (usernameField.getText().equals("")) {
            usernameField.setText("Ingresar Usuario");
        }

    }//GEN-LAST:event_usernameFieldFocusLost

    private void passwordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusLost
        // TODO add your handling code here:
        if (passwordField.getText().equals("")) {
            passwordField.setText("passpass");
        }
    }//GEN-LAST:event_passwordFieldFocusLost

    private void ventasLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasLabelMouseEntered
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Hover_Ventas.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_ventasLabelMouseEntered

    private void inventarioLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventarioLabelMouseEntered
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Hover_Inventario.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_inventarioLabelMouseEntered

    private void clientesLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesLabelMouseEntered
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Hover_Clientes.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_clientesLabelMouseEntered

    private void proveedoresLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proveedoresLabelMouseEntered
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Hover_Proveedores.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_proveedoresLabelMouseEntered

    private void comprasLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comprasLabelMouseEntered
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Hover_Compras.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_comprasLabelMouseEntered

    private void configuracionLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configuracionLabelMouseEntered
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Hover_Configuracion.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_configuracionLabelMouseEntered

    private void corteLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_corteLabelMouseEntered
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Hover_Corte.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_corteLabelMouseEntered

    private void salirLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirLabelMouseEntered
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Hover_Salir.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_salirLabelMouseEntered

    private void headerBackgroundImageMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerBackgroundImageMouseExited
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Default.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_headerBackgroundImageMouseExited

    private void ventasLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasLabelMouseExited
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Default.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_ventasLabelMouseExited

    private void inventarioLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventarioLabelMouseExited
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Default.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_inventarioLabelMouseExited

    private void clientesLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesLabelMouseExited
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Default.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_clientesLabelMouseExited

    private void proveedoresLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proveedoresLabelMouseExited
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Default.png"));
        headerBackgroundImage.setIcon(II);


    }//GEN-LAST:event_proveedoresLabelMouseExited

    private void comprasLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comprasLabelMouseExited
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Default.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_comprasLabelMouseExited

    private void configuracionLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configuracionLabelMouseExited
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Default.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_configuracionLabelMouseExited

    private void corteLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_corteLabelMouseExited
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Default.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_corteLabelMouseExited

    private void salirLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirLabelMouseExited
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Default.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_salirLabelMouseExited

    private void ventasLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasLabelMousePressed
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Pressed_Ventas.png"));
        headerBackgroundImage.setIcon(II);

        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "ventasPanel");
    }//GEN-LAST:event_ventasLabelMousePressed

    private void ventasLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasLabelMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_ventasLabelMouseClicked

    private void ventasLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasLabelMouseReleased
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Hover_Ventas.png"));
        headerBackgroundImage.setIcon(II);

    }//GEN-LAST:event_ventasLabelMouseReleased

    private void inventarioLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventarioLabelMousePressed
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Pressed_Inventario.png"));
        headerBackgroundImage.setIcon(II);
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "inventarioPanel");
        CardLayout card2 = (CardLayout) inventarioSubPanel.getLayout();
        card2.show(inventarioSubPanel, "invTodosPanel");
        DefaultTableModel model = null;
        try {
            model = prodControl.todosProductosDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        todosProductosTable.setModel(model);

    }//GEN-LAST:event_inventarioLabelMousePressed

    private void inventarioLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventarioLabelMouseReleased
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Hover_Inventario.png"));
        headerBackgroundImage.setIcon(II);

    }//GEN-LAST:event_inventarioLabelMouseReleased

    private void clientesLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesLabelMousePressed
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Pressed_Clientes.png"));
        headerBackgroundImage.setIcon(II);
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "clientesPanel");
        CardLayout card2 = (CardLayout) clientesSubPanel.getLayout();
        card2.show(clientesSubPanel, "clientesTodosPanel");
        DefaultTableModel model = null;
        try {
            model = clienControl.todosClientesDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        clientesTodos.setModel(model);
    }//GEN-LAST:event_clientesLabelMousePressed

    private void clientesLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesLabelMouseReleased
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Hover_Clientes.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_clientesLabelMouseReleased

    private void proveedoresLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proveedoresLabelMousePressed
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Pressed_Proveedores.png"));
        headerBackgroundImage.setIcon(II);
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "proveedoresPanel");
        CardLayout card2 = (CardLayout) proveedoresSubPanel.getLayout();
        card2.show(proveedoresSubPanel, "provTodosPanel");
        DefaultTableModel model = null;
        try {
            model = provControl.todosProveedoresDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablaProveedores.setModel(model);
    }//GEN-LAST:event_proveedoresLabelMousePressed

    private void proveedoresLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proveedoresLabelMouseReleased
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Hover_Proveedores.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_proveedoresLabelMouseReleased

    private void comprasLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comprasLabelMousePressed
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Pressed_Compras.png"));
        headerBackgroundImage.setIcon(II);
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "comprasPanel");
    }//GEN-LAST:event_comprasLabelMousePressed

    private void comprasLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comprasLabelMouseReleased
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Hover_Compras.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_comprasLabelMouseReleased

    private void configuracionLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configuracionLabelMousePressed
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Pressed_Configuracion.png"));
        headerBackgroundImage.setIcon(II);
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "configuracionPanel");
        CardLayout card2 = (CardLayout) configuracionSubPanel.getLayout();
        card2.show(configuracionSubPanel, "configuracionUsuariosPanel");
        UsuarioController userControl = new UsuarioController();
        DefaultTableModel model = null;
        try {
            model = userControl.todosUsuariosDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableUsuarios.setModel(model);
    }//GEN-LAST:event_configuracionLabelMousePressed

    private void configuracionLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configuracionLabelMouseReleased
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Hover_Configuracion.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_configuracionLabelMouseReleased

    private void corteLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_corteLabelMousePressed
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Pressed_Corte.png"));
        headerBackgroundImage.setIcon(II);
        CardLayout card = (CardLayout) mainPanel.getLayout();
        card.show(mainPanel, "cortePanel");
    }//GEN-LAST:event_corteLabelMousePressed

    private void corteLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_corteLabelMouseReleased
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Hover_Corte.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_corteLabelMouseReleased

    private void salirLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirLabelMousePressed
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Pressed_Salir.png"));
        headerBackgroundImage.setIcon(II);
    }//GEN-LAST:event_salirLabelMousePressed

    private void salirLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirLabelMouseReleased
        // TODO add your handling code here:
        ImageIcon II = new ImageIcon(getClass().getResource("/Images/Header_Hover_Salir.png"));
        headerBackgroundImage.setIcon(II);
        System.exit(0);
    }//GEN-LAST:event_salirLabelMouseReleased

    private void iniciarButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iniciarButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_iniciarButtonMouseClicked

    private void agregarGuardarProductoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarGuardarProductoButtonActionPerformed
        boolean error = false;
        ProductoController agProd = new ProductoController();
        try {
            error = agProd.agregarProducto(inventarioAgregarNombre.getText(), Double.parseDouble(inventarioAgregarPC.getText()), Double.parseDouble(inventarioAgregarPV.getText()), Integer.parseInt(inventarioAgregarExistencias.getText()), Integer.parseInt(inventarioAgregarStock.getText()));
            if (error) {
                errorYaExiste.setText("El articulo ya se encuentra en inventario");
            } else {
                successMessage.setText("Producto agregado exitosamente");
                inventarioAgregarNombre.setText("");
                inventarioAgregarPC.setText("");
                inventarioAgregarPV.setText("");
                inventarioAgregarExistencias.setText("");
                inventarioAgregarStock.setText("");
                errorYaExiste.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_agregarGuardarProductoButtonActionPerformed

    private void agregarCancelarProductoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarCancelarProductoButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) inventarioSubPanel.getLayout();
        card.show(inventarioSubPanel, "invTodosPanel");
    }//GEN-LAST:event_agregarCancelarProductoButtonActionPerformed

    private void borrarBuscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarBuscarButtonActionPerformed
        // TODO add your handling code here:
        ProductoController prod = new ProductoController();
        errorBorrar.setText("");
        try {
            if (prod.buscarProducto(productoBorrarField.getText()) > 0) {
                System.out.print(prod.getPk_ProductoId());
                borrarNombreField.setText(prod.getNombre());
                borrarPCField.setText(String.valueOf(prod.getPrecioCompra()));
                borrarPVField.setText(String.valueOf(prod.getPrecioVenta()));
                borrarExistenciasField.setText(String.valueOf(prod.getExistencias()));
                borrarStockField.setText(String.valueOf(prod.getStockMin()));
            } else {
                errorBorrar.setText("Producto No Encontrado");
                borrarNombreField.setText("");
                borrarPCField.setText("");
                borrarPVField.setText("");
                borrarExistenciasField.setText("");
                borrarStockField.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_borrarBuscarButtonActionPerformed

    private void borrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarButtonActionPerformed
        // TODO add your handling code here:
        ProductoController borrarProd = new ProductoController();
        try {
            if (borrarProd.buscarProducto(borrarNombreField.getText()) > 0) {
                System.out.print(borrarProd.getPk_ProductoId());
                try {
                    borrarProd.borrarProducto(borrarNombreField.getText());
                    successBorrado.setText("Borrado exitosamente!");
                    errorBorrar.setText("");
                    borrarNombreField.setText("");
                    borrarPCField.setText("");
                    borrarPVField.setText("");
                    borrarExistenciasField.setText("");
                    borrarStockField.setText("");
                    productoBorrarField.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                errorBorrar.setText("Favor de indicar producto a borrar.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_borrarButtonActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        ProductoController prod = new ProductoController();
        errorLabel.setText("");
        successLabel.setText("");
        try {
            if (prod.buscarProducto(modBuscarField.getText()) > 0) {
                jTextField19.setText(String.valueOf(prod.buscarProducto(modBuscarField.getText())));
                jTextField20.setText(prod.getNombre());
                jTextField21.setText(String.valueOf(prod.getPrecioCompra()));
                jTextField22.setText(String.valueOf(prod.getPrecioVenta()));
                jTextField23.setText(String.valueOf(prod.getExistencias()));
                jTextField24.setText(String.valueOf(prod.getStockMin()));
            } else {
                errorLabel.setText("Producto No Encontrado");
                jTextField19.setText("");
                jTextField20.setText("");
                jTextField21.setText("");
                jTextField22.setText("");
                jTextField23.setText("");
                jTextField24.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void invAgregarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invAgregarButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) inventarioSubPanel.getLayout();
        card.show(inventarioSubPanel, "invAgregarPanel");
        errorYaExiste.setText("");
        successMessage.setText("");
    }//GEN-LAST:event_invAgregarButtonActionPerformed

    private void invModificarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invModificarButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) inventarioSubPanel.getLayout();
        card.show(inventarioSubPanel, "invModificarPanel");
    }//GEN-LAST:event_invModificarButtonActionPerformed

    private void invBorrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invBorrarButtonActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) inventarioSubPanel.getLayout();
        card.show(inventarioSubPanel, "invBorrarPanel");
        errorBorrar.setText("");
        successBorrado.setText("");
    }//GEN-LAST:event_invBorrarButtonActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) inventarioSubPanel.getLayout();
        card.show(inventarioSubPanel, "invTodosPanel");
        modBuscarField.setText("");
        jTextField19.setText("");
        jTextField20.setText("");
        jTextField21.setText("");
        jTextField22.setText("");
        jTextField23.setText("");
        jTextField24.setText("");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void guardarCambioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarCambioButtonActionPerformed
        // TODO add your handling code here:

        ProductoController modProd = new ProductoController();
        try {
            if (jTextField19.getText().equals("")) {
                errorLabel.setText("Favor de indicar producto a modificar.");
            } else {
                modProd.modificarProducto(jTextField20.getText(), Double.parseDouble(jTextField21.getText()), Double.parseDouble(jTextField22.getText()), Integer.parseInt(jTextField23.getText()), Integer.parseInt(jTextField24.getText()));
                successLabel.setText("Producto modificado exitosamente");
                modBuscarField.setText("");
                jTextField19.setText("");
                jTextField20.setText("");
                jTextField21.setText("");
                jTextField22.setText("");
                jTextField23.setText("");
                jTextField24.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_guardarCambioButtonActionPerformed

    private void clienteNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clienteNombreActionPerformed

    private void inventarioAgregarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventarioAgregarNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inventarioAgregarNombreActionPerformed

    private void clienteGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteGuardarActionPerformed
        // TODO add your handling code here:
        boolean error = false;
        ClientesController clienControl = new ClientesController();
        try {
            error = clienControl.agregarCliente(clienteNombre.getText(), clienteApellidoPaterno.getText(), clienteApellidoMaterno.getText(), clienteRfc.getText(), clienteTelefono.getText());
            if (error) {
                agClienteError.setText("El cliente ya se encuentra en registrado en el sistema");
            } else {
                agClienteSuccess.setText("Cliente agregado a la lista de contacto");
                clienteNombre.setText("");
                clienteApellidoPaterno.setText("");
                clienteApellidoMaterno.setText("");
                clienteRfc.setText("");
                clienteTelefono.setText("");
                agClienteError.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_clienteGuardarActionPerformed

    private void clienteTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clienteTelefonoActionPerformed

    private void guardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarProveedorActionPerformed
        boolean error = false;
        ProveedorController provControl = new ProveedorController();
        try {
            error = provControl.agregarProveedor(nombreProveedor.getText(), apellidoPaternoProveedor.getText(), apellidoMaternoProveedor.getText(), rfcProveedor.getText(), telefonoProveedor.getText());
            if (error) {
                aPError.setText("El proveedor ya se encuentra en registrado en el sistema");
            } else {
                aPSuccess.setText("Proveedor agregado a la lista de contacto");
                nombreProveedor.setText("");
                apellidoPaternoProveedor.setText("");
                apellidoMaternoProveedor.setText("");
                rfcProveedor.setText("");
                telefonoProveedor.setText("");
                aPError.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_guardarProveedorActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void modProveedorBuscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modProveedorBuscarButtonActionPerformed
        // TODO add your handling code here:
        ProveedorController prov = new ProveedorController();
        mProveedorError.setText("");
        mProveedorSuccess.setText("");
        try {
            if (prov.buscarProveedor(modProveedorBuscarField.getText()) > 0) {
                jTextField30.setText(prov.getNombre());
                jTextField31.setText(prov.getApellidoPaterno());
                jTextField32.setText(prov.getApellidoMaterno());
                jTextField33.setText(prov.getRfc());
                jTextField34.setText(prov.getTelefono());
            } else {
                mProveedorError.setText("Proveedor No Encontrado");
                jTextField30.setText("");
                jTextField31.setText("");
                jTextField32.setText("");
                jTextField33.setText("");
                jTextField34.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_modProveedorBuscarButtonActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        ProveedorController modProv = new ProveedorController();
        try {
            if (jTextField30.getText().equals("")) {
                mProveedorError.setText("Favor de indicar proveedor a modificar.");
            } else {
                modProv.modificarProveedor(jTextField30.getText(), jTextField31.getText(), jTextField32.getText(), jTextField33.getText(), jTextField34.getText());
                mProveedorSuccess.setText("Producto modificado exitosamente");
                modProveedorBuscarField.setText("");
                jTextField30.setText("");
                jTextField31.setText("");
                jTextField32.setText("");
                jTextField33.setText("");
                jTextField34.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void boProveedorBuscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boProveedorBuscarButtonActionPerformed
        // TODO add your handling code here:
        ProveedorController prov = new ProveedorController();
        boProveedorError.setText("");
        boProveedorSuccess.setText("");
        try {
            if (prov.buscarProveedor(boProveedorBuscarField.getText()) > 0) {
                jTextField4.setText(prov.getNombre());
                jTextField5.setText(prov.getApellidoPaterno());
                jTextField6.setText(prov.getApellidoMaterno());
                jTextField7.setText(prov.getRfc());
                jTextField13.setText(prov.getTelefono());
            } else {
                boProveedorError.setText("Proveedor No Encontrado");
                jTextField4.setText("");
                jTextField5.setText("");
                jTextField6.setText("");
                jTextField7.setText("");
                jTextField13.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_boProveedorBuscarButtonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) clientesSubPanel.getLayout();
        card.show(clientesSubPanel, "clientesTodosPanel");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) clientesSubPanel.getLayout();
        card.show(clientesSubPanel, "clientesTodosPanel");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) proveedoresSubPanel.getLayout();
        card.show(proveedoresSubPanel, "provTodosPanel");
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) proveedoresSubPanel.getLayout();
        card.show(proveedoresSubPanel, "provTodosPanel");
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) proveedoresSubPanel.getLayout();
        card.show(proveedoresSubPanel, "provTodosPanel");
    }//GEN-LAST:event_jButton21ActionPerformed

    private void mClienteBuscarFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mClienteBuscarFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mClienteBuscarFieldActionPerformed

    private void mClienteBuscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mClienteBuscarButtonActionPerformed
        // TODO add your handling code here:
        ClientesController clien = new ClientesController();
        mClienteError.setText("");
        mClienteSuccess.setText("");
        try {
            if (clien.buscarCliente(mClienteBuscarField.getText()) > 0) {
                jTextField8.setText(clien.getNombre());
                jTextField9.setText(clien.getApellidoPaterno());
                jTextField10.setText(clien.getApellidoMaterno());
                jTextField11.setText(clien.getRfc());
                jTextField12.setText(clien.getTelefono());
            } else {
                mClienteError.setText("Cliente No Encontrado");
                jTextField8.setText("");
                jTextField9.setText("");
                jTextField10.setText("");
                jTextField11.setText("");
                jTextField12.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mClienteBuscarButtonActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        ClientesController clien = new ClientesController();
        try {
            if (jTextField8.getText().equals("")) {
                mClienteError.setText("Favor de indicar cliente a modificar.");
            } else {
                clien.modificarCliente(jTextField8.getText(), jTextField9.getText(), jTextField10.getText(), jTextField11.getText(), jTextField12.getText());
                mClienteSuccess.setText("Cliente modificado exitosamente");
                mClienteBuscarField.setText("");
                jTextField8.setText("");
                jTextField9.setText("");
                jTextField10.setText("");
                jTextField11.setText("");
                jTextField12.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void bClienteBuscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClienteBuscarButtonActionPerformed
        // TODO add your handling code here:
        ClientesController clien = new ClientesController();
        bClienteError.setText("");
        bClienteSuccess.setText("");
        try {
            if (clien.buscarCliente(bClienteBuscarField.getText()) > 0) {
                jTextField15.setText(clien.getNombre());
                jTextField16.setText(clien.getApellidoPaterno());
                jTextField17.setText(clien.getApellidoMaterno());
                jTextField18.setText(clien.getRfc());
                jTextField25.setText(clien.getTelefono());
            } else {
                bClienteError.setText("Cliente No Encontrado");
                jTextField15.setText("");
                jTextField16.setText("");
                jTextField17.setText("");
                jTextField18.setText("");
                jTextField19.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bClienteBuscarButtonActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        ClientesController clien = new ClientesController();
        try {
            if (clien.buscarCliente(bClienteBuscarField.getText()) > 0) {
                try {
                    clien.borrarCliente(bClienteBuscarField.getText());
                    bClienteSuccess.setText("Borrado exitosamente!");
                    jTextField15.setText("");
                    jTextField16.setText("");
                    jTextField17.setText("");
                    jTextField18.setText("");
                    jTextField25.setText("");
                    bClienteBuscarField.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                bClienteError.setText("Favor de indicar cliente a borrar.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) configuracionSubPanel.getLayout();
        card.show(configuracionSubPanel, "configuracionUsuariosPanel");
        CardLayout card2 = (CardLayout) configuracionUsuariosPanel.getLayout();
        card2.show(configuracionUsuariosPanel, "todosUsuariosPanel");
        DefaultTableModel model = null;
        try {
            model = userControl.todosUsuariosDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableUsuarios.setModel(model);
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) configuracionUsuariosPanel.getLayout();
        card.show(configuracionUsuariosPanel, "aUsuarioPanel");
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:

        if (!tableUsuarios.getSelectionModel().isSelectionEmpty()) {
            mainUsuariosError.setText("");
            int row = tableUsuarios.getSelectedRow();
            tmpUser.setUsername((tableUsuarios.getModel().getValueAt(row, 0).toString()));
            CardLayout card = (CardLayout) configuracionUsuariosPanel.getLayout();
            card.show(configuracionUsuariosPanel, "mUsuarioPanel");

            try {
                if (userControl.buscarUsuario(tmpUser.getUsername()) > 0) {
                    jTextField26.setText(userControl.getUsername());
                    jPasswordField2.setText(userControl.getPassword());
                    jTextField35.setText(userControl.getNombre());
                    jTextField43.setText(userControl.getApellidoPaterno());
                    jTextField44.setText(userControl.getApellidoMaterno());
                    mUsuarioID.setText(String.valueOf((userControl.getPk_usuarioID())));
                } else {
                    jTextField26.setText("");
                    jPasswordField2.setText("");
                    jTextField35.setText("");
                    jTextField43.setText("");
                    jTextField44.setText("");
                }
            } catch (SQLException ex) {
                Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            mainUsuariosError.setText("Favor de seleccionar usuario.");
        }


    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:

        if (!tableUsuarios.getSelectionModel().isSelectionEmpty()) {
            CardLayout card = (CardLayout) configuracionUsuariosPanel.getLayout();
            card.show(configuracionUsuariosPanel, "bUsuarioPanel");
            mainUsuariosError.setText("");
            int row = tableUsuarios.getSelectedRow();
            tmpUser.setUsername((tableUsuarios.getModel().getValueAt(row, 0).toString()));

            try {
                if (userControl.buscarUsuario(tmpUser.getUsername()) > 0) {
                    jTextField45.setText(userControl.getUsername());
                    jPasswordField3.setText(userControl.getPassword());
                    jTextField46.setText(userControl.getNombre());
                    jTextField47.setText(userControl.getApellidoPaterno());
                    jTextField48.setText(userControl.getApellidoMaterno());
                    mUsuarioID1.setText(String.valueOf((userControl.getPk_usuarioID())));
                } else {
                    jTextField45.setText("");
                    jPasswordField3.setText("");
                    jTextField46.setText("");
                    jTextField47.setText("");
                    jTextField48.setText("");
                    mUsuarioID1.setText("");
                }
            } catch (SQLException ex) {
                Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            mainUsuariosError.setText("Favor de seleccionar usuario.");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        boolean error = false;
        UsuarioController userControl = new UsuarioController();
        try {
            error = userControl.agregarUsuario(jTextField14.getText(), jPasswordField1.getText(), jTextField27.getText(), jTextField28.getText(), jTextField29.getText());
            if (error) {
                aUsuarioError.setText("El usuario ya se encuentra en registrado en el sistema");
            } else {
                aUsuarioSuccess.setText("Usuario agregado a la lista de usuarios");
                jTextField14.setText("");
                jPasswordField1.setText("");
                jTextField27.setText("");
                jTextField28.setText("");
                jTextField29.setText("");
                aUsuarioError.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        UsuarioController userControl = new UsuarioController();
        try {
            userControl.modificarUsuario(Integer.parseInt(mUsuarioID.getText()), jTextField26.getText(), jPasswordField2.getText(), jTextField35.getText(), jTextField43.getText(), jTextField44.getText());
            jTextField26.setText("");
            jPasswordField2.setText("");
            jTextField35.setText("");
            jTextField43.setText("");
            jTextField44.setText("");
            mUsuarioID.setText("");

        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        CardLayout card = (CardLayout) configuracionSubPanel.getLayout();
        card.show(configuracionSubPanel, "configuracionUsuariosPanel");
        CardLayout card2 = (CardLayout) configuracionUsuariosPanel.getLayout();
        card2.show(configuracionUsuariosPanel, "todosUsuariosPanel");
        DefaultTableModel model = null;
        try {
            model = userControl.todosUsuariosDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableUsuarios.setModel(model);
    }//GEN-LAST:event_jButton27ActionPerformed

    private void mUsuarioIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mUsuarioIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mUsuarioIDActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) configuracionSubPanel.getLayout();
        card.show(configuracionSubPanel, "configuracionUsuariosPanel");
        CardLayout card2 = (CardLayout) configuracionUsuariosPanel.getLayout();
        card2.show(configuracionUsuariosPanel, "todosUsuariosPanel");
        DefaultTableModel model = null;
        try {
            model = userControl.todosUsuariosDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableUsuarios.setModel(model);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) configuracionSubPanel.getLayout();
        card.show(configuracionSubPanel, "configuracionUsuariosPanel");
        CardLayout card2 = (CardLayout) configuracionUsuariosPanel.getLayout();
        card2.show(configuracionUsuariosPanel, "todosUsuariosPanel");
        DefaultTableModel model = null;
        try {
            model = userControl.todosUsuariosDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableUsuarios.setModel(model);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        UsuarioController userControl = new UsuarioController();
        try {
            System.out.print(mUsuarioID1.getText());
            userControl.borrarUsuario(mUsuarioID1.getText());
            jTextField26.setText("");
            jPasswordField2.setText("");
            jTextField35.setText("");
            jTextField43.setText("");
            jTextField44.setText("");
            mUsuarioID.setText("");

        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        CardLayout card = (CardLayout) configuracionSubPanel.getLayout();
        card.show(configuracionSubPanel, "configuracionUsuariosPanel");
        CardLayout card2 = (CardLayout) configuracionUsuariosPanel.getLayout();
        card2.show(configuracionUsuariosPanel, "todosUsuariosPanel");
        DefaultTableModel model = null;
        try {
            model = userControl.todosUsuariosDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableUsuarios.setModel(model);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void mUsuarioID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mUsuarioID1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mUsuarioID1ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout) configuracionSubPanel.getLayout();
        card.show(configuracionSubPanel, "configuracionUsuariosPanel");
        CardLayout card2 = (CardLayout) configuracionUsuariosPanel.getLayout();
        card2.show(configuracionUsuariosPanel, "todosUsuariosPanel");
        DefaultTableModel model = null;
        try {
            model = userControl.todosUsuariosDisplay();
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableUsuarios.setModel(model);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void venderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venderButtonActionPerformed
        // TODO add your handling code here:
        if (cantidadVenderField.getText().equals("") || buscarProductoField.getSelectedItem().toString().equals("")) {
            errorVentas.setText("Favor de seleccionar producto y cantidad");
        } else {
            //Set new sold product on the table
            try {
                
                String found = null;
                String tmpprod = buscarProductoField.getSelectedItem().toString();
                int cantidad = Integer.parseInt(cantidadVenderField.getText());

                String sql = "SELECT * FROM productos";

                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    //String id = resultSet.getString("pk_productoid");
                    String nombre = resultSet.getString("nombre");
                    String preciocompra = String.valueOf(resultSet.getDouble("preciocompra"));
                    Double precioventa = resultSet.getDouble("precioventa");
                    String existencias = String.valueOf(resultSet.getInt("existencias"));
                    String stockminimo = String.valueOf(resultSet.getInt("stockminimo"));
                    String activo = String.valueOf(resultSet.getInt("activo"));
                    if (nombre.equals(tmpprod)) {
                        modelVentas.addRow(new Object[]{nombre, precioventa, existencias, cantidad, cantidad * precioventa});
                        total = total + cantidad * precioventa;
                        jLabel16.setText(String.valueOf(total));

                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            }
            todosProductosVentasTable.setModel(modelVentas);
            cantidadVenderField.setText("");
            errorVentas.setText("");
            buscarProductoField.setSelectedIndex(-1);
            
        }


    }//GEN-LAST:event_venderButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (todosProductosVentasTable.getRowCount() > 0) {

            JDialog.setDefaultLookAndFeelDecorated(true);
            int response = JOptionPane.showConfirmDialog(null, "Desea realizar la venta?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.NO_OPTION) {

                System.out.println("No button clicked");

            } else if (response == JOptionPane.YES_OPTION) {
                try {
                    if (buscarClienteVentaField.getSelectedIndex() != -1) {
                        try {
                            String selectSQL = "SELECT * FROM clientes";
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery(selectSQL);
                            
                            while (resultSet.next()) {
                                if (resultSet.getString("rfc").equals(buscarClienteVentaField.getSelectedItem().toString())) {
                                    venta.setFk_clienteID(resultSet.getInt("pk_clienteid"));
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    venta.setNumeroTicket(ventaControl.getTicketNumero());
                    venta.setFk_usuarioID(currentUser.getPk_usuarioID());
                    venta.setFk_corteID(corteControl.getCorteNumero());
                    venta.setDia(Integer.parseInt(dtfday.format(localDate)));
                    venta.setMes(Integer.parseInt(dtfmonth.format(localDate)));
                    venta.setAno(Integer.parseInt(dtfyear.format(localDate)));
                    venta.setHora(dtftime.format(now));
                    venta.setCantidadArticulos(todosProductosVentasTable.getRowCount());
                    venta.setTotal(total);
                    venta.saveToDatabase();
                    
                    modelVentas.setRowCount(0);
                    buscarClienteVentaField.setSelectedIndex(-1);
                    ventaControl.siguienteTicket();
                    jLabel18.setText(String.valueOf(ventaControl.getTicketNumero()));
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (response == JOptionPane.CLOSED_OPTION) {
                System.out.println("JOptionPane closed");
            }

        } else {
            errorVentas.setText("No se han agregado productos");
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void buscarClienteVentaFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarClienteVentaFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarClienteVentaFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        modelVentas.setRowCount(0);
        buscarClienteVentaField.setSelectedIndex(-1);
        buscarProductoField.setSelectedIndex(-1);
        cantidadVenderField.setText("");
        jLabel16.setText("0.00");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new home().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aPError;
    private javax.swing.JLabel aPSuccess;
    private javax.swing.JLabel aUsuarioError;
    private javax.swing.JLabel aUsuarioError1;
    private javax.swing.JPanel aUsuarioPanel;
    private javax.swing.JLabel aUsuarioSuccess;
    private javax.swing.JLabel aUsuarioSuccess1;
    private javax.swing.JLabel agClienteError;
    private javax.swing.JLabel agClienteSuccess;
    private javax.swing.JButton agregarCancelarProductoButton;
    private javax.swing.JButton agregarGuardarProductoButton;
    private javax.swing.JTextField apellidoMaternoProveedor;
    private javax.swing.JTextField apellidoPaternoProveedor;
    private javax.swing.JButton bClienteBuscarButton;
    private javax.swing.JTextField bClienteBuscarField;
    private javax.swing.JLabel bClienteError;
    private javax.swing.JLabel bClienteSuccess;
    private javax.swing.JPanel bUsuarioPanel;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton boProveedorBuscarButton;
    private javax.swing.JTextField boProveedorBuscarField;
    private javax.swing.JLabel boProveedorError;
    private javax.swing.JLabel boProveedorSuccess;
    private javax.swing.JButton borrarBuscarButton;
    private javax.swing.JButton borrarButton;
    private javax.swing.JTextField borrarExistenciasField;
    private javax.swing.JTextField borrarNombreField;
    private javax.swing.JTextField borrarPCField;
    private javax.swing.JTextField borrarPVField;
    private javax.swing.JTextField borrarStockField;
    private javax.swing.JPanel brownBackground;
    private javax.swing.JComboBox<String> buscarClienteVentaField;
    private javax.swing.JComboBox<String> buscarProductoField;
    private javax.swing.JTextField cantidadVenderField;
    private javax.swing.JTextField clienteApellidoMaterno;
    private javax.swing.JTextField clienteApellidoPaterno;
    private javax.swing.JButton clienteGuardar;
    private javax.swing.JTextField clienteNombre;
    private javax.swing.JTextField clienteRfc;
    private javax.swing.JTextField clienteTelefono;
    private javax.swing.JButton clientesAgregarButton;
    private javax.swing.JPanel clientesAgregarPanel;
    private javax.swing.JButton clientesBorrarButton;
    private javax.swing.JPanel clientesBorrarPanel;
    private javax.swing.JLabel clientesLabel;
    private javax.swing.JButton clientesModificarButton;
    private javax.swing.JPanel clientesModificarPanel;
    private javax.swing.JPanel clientesPanel;
    private javax.swing.JPanel clientesSubPanel;
    private javax.swing.JTable clientesTodos;
    private javax.swing.JButton clientesTodosButton;
    private javax.swing.JPanel clientesTodosPanel;
    private javax.swing.JLabel comprasLabel;
    private javax.swing.JPanel comprasPanel;
    private javax.swing.JButton confTicketPanel;
    private javax.swing.JButton confUsuariosPanel;
    private javax.swing.JLabel configuracionLabel;
    private javax.swing.JPanel configuracionPanel;
    private javax.swing.JPanel configuracionSubPanel;
    private javax.swing.JPanel configuracionTicketPanel;
    private javax.swing.JPanel configuracionUsuariosPanel;
    private javax.swing.JLabel corteLabel;
    private javax.swing.JPanel cortePanel;
    private javax.swing.JLabel errorBorrar;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel errorLogin;
    private javax.swing.JLabel errorVentas;
    private javax.swing.JLabel errorYaExiste;
    private javax.persistence.EntityManager finalPUEntityManager;
    private javax.swing.JButton guardarCambioButton;
    private javax.swing.JButton guardarProveedor;
    private javax.swing.JLabel headerBackgroundImage;
    private javax.swing.JPanel headerMainPanel;
    private javax.swing.JPanel headerSecondPanel;
    private javax.swing.JLabel iniciarButton;
    private javax.swing.JButton invAgregarButton;
    private javax.swing.JPanel invAgregarPanel;
    private javax.swing.JButton invBajosButton;
    private javax.swing.JPanel invBajosPanel;
    private javax.swing.JButton invBorrarButton;
    private javax.swing.JPanel invBorrarPanel;
    private javax.swing.JButton invModificarButton;
    private javax.swing.JPanel invModificarPanel;
    private javax.swing.JButton invTodosButton;
    private javax.swing.JPanel invTodosPanel;
    private javax.swing.JTextField inventarioAgregarExistencias;
    private javax.swing.JTextField inventarioAgregarNombre;
    private javax.swing.JTextField inventarioAgregarPC;
    private javax.swing.JTextField inventarioAgregarPV;
    private javax.swing.JTextField inventarioAgregarStock;
    private javax.swing.JLabel inventarioLabel;
    private javax.swing.JPanel inventarioPanel;
    private javax.swing.JPanel inventarioSubPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JButton mClienteBuscarButton;
    private javax.swing.JTextField mClienteBuscarField;
    private javax.swing.JLabel mClienteError;
    private javax.swing.JLabel mClienteSuccess;
    private javax.swing.JLabel mProveedorError;
    private javax.swing.JLabel mProveedorSuccess;
    private javax.swing.JTextField mUsuarioID;
    private javax.swing.JTextField mUsuarioID1;
    private javax.swing.JPanel mUsuarioPanel;
    private javax.swing.JPanel mainMasterPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel mainUsuariosError;
    private javax.swing.JTextField modBuscarField;
    private javax.swing.JButton modProveedorBuscarButton;
    private javax.swing.JTextField modProveedorBuscarField;
    private javax.swing.JLabel nombreLabelSearch;
    private javax.swing.JTextField nombreProveedor;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField productoBorrarField;
    private java.util.List<Views.Productos> productosList;
    private javax.persistence.Query productosQuery;
    private javax.swing.JButton provAgregarButton;
    private javax.swing.JPanel provAgregarPanel;
    private javax.swing.JButton provBorrarButton;
    private javax.swing.JPanel provBorrarPanel;
    private javax.swing.JButton provModificarButton;
    private javax.swing.JPanel provModificarPanel;
    private javax.swing.JButton provTodosButton;
    private javax.swing.JPanel provTodosPanel;
    private javax.swing.JLabel proveedoresLabel;
    private javax.swing.JPanel proveedoresPanel;
    private javax.swing.JPanel proveedoresSubPanel;
    private javax.swing.JTextField rfcProveedor;
    private javax.swing.JLabel salirLabel;
    private javax.swing.JLabel successBorrado;
    private javax.swing.JLabel successLabel;
    private javax.swing.JLabel successMessage;
    private javax.swing.JTable tablaProveedores;
    private javax.swing.JPanel tableHeaders;
    private javax.swing.JTable tableUsuarios;
    private javax.swing.JTextField telefonoProveedor;
    private javax.swing.JTable todosBajosTable;
    private javax.swing.JTable todosProductosTable;
    private javax.swing.JTable todosProductosVentasTable;
    private javax.swing.JPanel todosUsuariosPanel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JButton venderButton;
    private javax.swing.JPanel ventasBottomPanel;
    private javax.swing.JLabel ventasLabel;
    private javax.swing.JPanel ventasPanel;
    // End of variables declaration//GEN-END:variables

}
