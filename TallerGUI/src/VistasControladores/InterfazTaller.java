/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistasControladores;

import Modelos.Carro;
import Modelos.Cliente;
import Modelos.Moto;
import Modelos.Taller;
import Modelos.Tecnico;
import Modelos.Vehiculo;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pipet
 */
public class InterfazTaller extends javax.swing.JFrame {

    Taller miTaller;
    LinkedList<Vehiculo> temporalVehiculos;

    /**
     * Creates new form InterfazTaller
     */
    public InterfazTaller() {
        initComponents();
        System.out.println("Hola desde el inicio");
        //this.miTaller = new Taller("El Cacharro", "Av 23");
        cargarSesion();

        this.lblNombreTaller.setText(this.miTaller.getNombre());
        this.lblDirecionTaller.setText(this.miTaller.getDireccion());
        this.rdCarro.setSelected(true);
        this.panelInfoMotos.setVisible(false);
        this.comboVehiculos.removeAllItems();
        this.temporalVehiculos = new LinkedList<>();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                eventoCierre();
            }
        });
    }

    public void eventoCierre() {
        System.out.println("Cerrando");
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("taller.obj"));
            salida.writeObject(this.miTaller);
            salida.close();
        } catch (Exception ex) {
            System.out.println("" + ex);
        }

    }

    public void cargarSesion() {
        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("taller.obj"));
            this.miTaller = (Taller) entrada.readObject();
            this.actualizarTablaClientes();
            this.actualizarTablaTecnicos();
            this.actualizarComboVehiculos();
            this.actualizarTablaVehiculos();
            
        } catch (Exception ex) {
            System.out.println("" + ex);
        }

    }

    public void limpiarDatosTecnico() {
        this.txtCedulaTecnico.setText("");
        this.txtNombreTecnico.setText("");
        this.txtApellidoTecnico.setText("");
        this.txtEspecialidadTecnico.setText("");
        this.txtTelefonoTecnico.setText("");
        this.spAñosExperienciaTecnico.setValue(0);

    }

    public void actualizarTablaTecnicos() {
        String nombresColumnas[] = {"Cedula", "Nombre", "Apellido", "Teléfono", "Especialidad", "Años Experiencia"};
        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);
        this.tblTecnicos.setModel(miModelo);
        for (Tecnico tecnicoActual : this.miTaller.getMisTecnicos()) {
            String fila[] = new String[nombresColumnas.length];
            fila[0] = tecnicoActual.getCedula();
            fila[1] = tecnicoActual.getNombre();
            fila[2] = tecnicoActual.getApellido();
            fila[3] = tecnicoActual.getTelefono();
            fila[4] = tecnicoActual.getEspecializacion();
            fila[5] = Integer.toString(tecnicoActual.getAñosExperiencia());
            miModelo.addRow(fila);
        }
    }

    public void actualizarTablaClientes() {
        String nombresColumnas[] = {"Cedula", "Nombre", "Apellido", "Teléfono", "Años Fidelidad", "Placas de sus vehiculos"};
        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);
        this.tblClientes.setModel(miModelo);
        for (Cliente clienteActual : this.miTaller.getMisClientes()) {
            String fila[] = new String[nombresColumnas.length];
            fila[0] = clienteActual.getCedula();
            fila[1] = clienteActual.getNombre();
            fila[2] = clienteActual.getApellido();
            fila[3] = clienteActual.getTelefono();
            fila[4] = Integer.toString(clienteActual.getAñosFidelidad());
            fila[5] = clienteActual.listarPlacas();
            miModelo.addRow(fila);
        }
    }

    public void actualizarTablaVehiculos() {
        String nombresColumnas[] = {"Placa", "Marca", "Año", "Color", "Tipo", "Tipo Transmisión", "Número Patas"};
        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);
        this.tblVehiculos.setModel(miModelo);

        for (Vehiculo vehiculoActual : this.miTaller.getMisVehiculos()) {
            String fila[] = new String[nombresColumnas.length];
            fila[0] = vehiculoActual.getPlaca();
            fila[1] = vehiculoActual.getMarca();
            fila[2] = Integer.toString(vehiculoActual.getAño());
            fila[3] = vehiculoActual.getColor();
            if (vehiculoActual instanceof Moto) {
                fila[4] = "Moto";
                fila[6] = Integer.toString(((Moto) vehiculoActual).getNumeroPatas());
            } else if (vehiculoActual instanceof Carro) {
                fila[4] = "Carro";
                fila[5] = ((Carro) vehiculoActual).getTipoTransmision();
            }

            miModelo.addRow(fila);
        }
    }

    public void actualizarTablaVehiculosSeleccionados(LinkedList<Vehiculo> miListaVehiculos) {
        String nombresColumnas[] = {"Placa", "Marca", "Año", "Color", "Tipo", "Tipo Transmisión", "Número Patas"};
        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);
        this.tblVehiculosCliente.setModel(miModelo);

        for (Vehiculo vehiculoActual : miListaVehiculos) {
            String fila[] = new String[nombresColumnas.length];
            fila[0] = vehiculoActual.getPlaca();
            fila[1] = vehiculoActual.getMarca();
            fila[2] = Integer.toString(vehiculoActual.getAño());
            fila[3] = vehiculoActual.getColor();
            if (vehiculoActual instanceof Moto) {
                fila[4] = "Moto";
                fila[6] = Integer.toString(((Moto) vehiculoActual).getNumeroPatas());
            } else if (vehiculoActual instanceof Carro) {
                fila[4] = "Carro";
                fila[5] = ((Carro) vehiculoActual).getTipoTransmision();
            }

            miModelo.addRow(fila);
        }
    }

    public void actualizarComboVehiculos() {
        this.comboVehiculos.removeAllItems();
        for (Vehiculo vehiculoActual : this.miTaller.getMisVehiculos()) {
            String palabra = vehiculoActual.getPlaca() + "," + vehiculoActual.getMarca();
            this.comboVehiculos.addItem(palabra);
        }
    }

    public void escribirArchivoPlano(String contenido, String extension) {
        try {
            JFileChooser file = new JFileChooser();
            file.showSaveDialog(this);
            //String ruta = "" + file.getCurrentDirectory();
            try (BufferedWriter save = new BufferedWriter(new FileWriter(file.getSelectedFile().toString() + "." + extension))) {
                save.write(contenido);
            }
            JOptionPane.showMessageDialog(null, "El archivo se a guardado Exitosamente",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Su archivo no se ha guardado",
                    "Advertencia",
                    JOptionPane.ERROR_MESSAGE);
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

        lblNombreTaller = new javax.swing.JLabel();
        lblDirecionTaller = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtCedulaCliente = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtTelefonoCliente = new javax.swing.JTextField();
        txtApellidoCliente = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        spAñosFidelidadCliente = new javax.swing.JSpinner();
        jLabel25 = new javax.swing.JLabel();
        comboVehiculos = new javax.swing.JComboBox<>();
        jButton13 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVehiculosCliente = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jButton14 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPlacaVehiculo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtMarcaVehiculo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtColorVehiculo = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        spAñoVehiculo = new javax.swing.JSpinner();
        rdCarro = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        rdMoto = new javax.swing.JRadioButton();
        scrollVehiculos = new javax.swing.JScrollPane();
        tblVehiculos = new javax.swing.JTable();
        panelInfoCarros = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtTipoTransmisionVehiculo = new javax.swing.JTextField();
        panelInfoMotos = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        spNumeroPatasVehiculo = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCedulaTecnico = new javax.swing.JTextField();
        txtNombreTecnico = new javax.swing.JTextField();
        txtEspecialidadTecnico = new javax.swing.JTextField();
        txtTelefonoTecnico = new javax.swing.JTextField();
        txtApellidoTecnico = new javax.swing.JTextField();
        spAñosExperienciaTecnico = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTecnicos = new javax.swing.JTable();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNombreTaller.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblNombreTaller.setText("Taller el Cacharro");

        lblDirecionTaller.setText("Calle 23 , Av Santander");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Clientes");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Cédula");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Teléfono");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Apellido");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Nombre");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Años Experiencia");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Añadir vehiculos");

        comboVehiculos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton13.setText("Agregar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton9.setText("Agregar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Buscar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Editar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Eliminar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        tblVehiculosCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblVehiculosCliente);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblClientes);

        jButton14.setText("Eliminar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(297, 297, 297))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addGap(82, 82, 82)
                                        .addComponent(txtCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel25))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(spAñosFidelidadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comboVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(7, 7, 7)
                                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel23))
                                        .addGap(69, 69, 69)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton14))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(63, 63, 63))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(spAñosFidelidadCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(comboVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13)
                    .addComponent(jButton14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton10)
                    .addComponent(jButton11)
                    .addComponent(jButton12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Clientes", jPanel2);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Vehículos");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Placa");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Marca");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Año");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Color");

        jButton5.setText("Agregar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Buscar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Editar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Eliminar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        rdCarro.setText("Carro");
        rdCarro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdCarroActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Tipo");

        rdMoto.setText("Moto");
        rdMoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdMotoActionPerformed(evt);
            }
        });

        tblVehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        scrollVehiculos.setViewportView(tblVehiculos);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Tipo Transmisión");

        javax.swing.GroupLayout panelInfoCarrosLayout = new javax.swing.GroupLayout(panelInfoCarros);
        panelInfoCarros.setLayout(panelInfoCarrosLayout);
        panelInfoCarrosLayout.setHorizontalGroup(
            panelInfoCarrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoCarrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTipoTransmisionVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInfoCarrosLayout.setVerticalGroup(
            panelInfoCarrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoCarrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoCarrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtTipoTransmisionVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Número Patas");

        javax.swing.GroupLayout panelInfoMotosLayout = new javax.swing.GroupLayout(panelInfoMotos);
        panelInfoMotos.setLayout(panelInfoMotosLayout);
        panelInfoMotosLayout.setHorizontalGroup(
            panelInfoMotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoMotosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(26, 26, 26)
                .addComponent(spNumeroPatasVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInfoMotosLayout.setVerticalGroup(
            panelInfoMotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoMotosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelInfoMotosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(spNumeroPatasVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(297, 297, 297))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(panelInfoCarros, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelInfoMotos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrollVehiculos, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel17))
                                .addGap(82, 82, 82)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtPlacaVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                        .addComponent(spAñoVehiculo))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(rdCarro)
                                        .addGap(36, 36, 36)
                                        .addComponent(rdMoto)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel16))
                                .addGap(69, 69, 69)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtColorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMarcaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(43, 43, 43))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtPlacaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(spAñoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtMarcaVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtColorVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(rdCarro)
                    .addComponent(rdMoto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInfoCarros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelInfoMotos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(18, 18, 18)
                .addComponent(scrollVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Vehículos", jPanel3);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Cubículos");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(414, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(297, 297, 297))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(441, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cubículos", jPanel4);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Técnicos");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Cédula");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Nombre");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Especialidad");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Teléfono");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Apellido");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Años Experiencia");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Editar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tblTecnicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblTecnicos);

        jButton15.setText("Certificado");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Exportar");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(39, 39, 39))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtEspecialidadTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombreTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(82, 82, 82)
                                .addComponent(txtCedulaTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(320, 320, 320))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(104, 104, 104)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel10)
                                                    .addComponent(jLabel11))
                                                .addGap(69, 69, 69)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtApellidoTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtTelefonoTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addGap(18, 18, 18)
                                                .addComponent(spAñosExperienciaTecnico))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton15)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(39, 39, 39))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtCedulaTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtNombreTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(txtEspecialidadTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtTelefonoTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtApellidoTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(spAñosExperienciaTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton15)
                    .addComponent(jButton16))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Técnicos", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblNombreTaller)
                                .addGap(206, 206, 206))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblDirecionTaller)
                                .addGap(286, 286, 286))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTabbedPane1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblNombreTaller)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDirecionTaller)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String cedula = this.txtCedulaTecnico.getText();
        Tecnico tecnicoBuscado = this.miTaller.buscarTecnico(cedula);
        if (tecnicoBuscado != null) {
            JOptionPane.showMessageDialog(this, "Ya existe un técnico registrado con esta cédula");
        } else {
            String nombre = this.txtNombreTecnico.getText();
            String apellido = this.txtApellidoTecnico.getText();
            String telefono = this.txtTelefonoTecnico.getText();
            int añosExperiencia = (Integer) this.spAñosExperienciaTecnico.getValue();
            String especializacion = this.txtEspecialidadTecnico.getText();
            if (!cedula.equals("") && !nombre.equals("") && !telefono.equals("") && !especializacion.equals("")) {
                Tecnico nuevoTecnico = new Tecnico(cedula, nombre, apellido, telefono, añosExperiencia, especializacion);
                this.miTaller.registrarTecnico(nuevoTecnico);
                JOptionPane.showMessageDialog(this, "Técnico creado exitosamente");
                this.limpiarDatosTecnico();
                this.actualizarTablaTecnicos();
            } else {

                JOptionPane.showMessageDialog(this, "Falta por ingresar información");
            }

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String cedulaBuscada = this.txtCedulaTecnico.getText();
        Tecnico tecnicoBuscado = this.miTaller.buscarTecnico(cedulaBuscada);
        if (tecnicoBuscado == null) {
            JOptionPane.showMessageDialog(this, "El técnico con cédula " + cedulaBuscada + " no está registrado");
        } else {
            this.txtTelefonoTecnico.setText(tecnicoBuscado.getTelefono());
            this.txtNombreTecnico.setText(tecnicoBuscado.getNombre());
            this.txtApellidoTecnico.setText(tecnicoBuscado.getApellido());
            this.txtEspecialidadTecnico.setText(tecnicoBuscado.getEspecializacion());
            this.spAñosExperienciaTecnico.setValue(tecnicoBuscado.getAñosExperiencia());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String cedula = this.txtCedulaTecnico.getText();
        Tecnico tecnicoBuscado = this.miTaller.buscarTecnico(cedula);
        if (tecnicoBuscado == null) {
            JOptionPane.showMessageDialog(this, "El técnico buscado no existe");
        } else {
            String nombre = this.txtNombreTecnico.getText();
            String apellido = this.txtApellidoTecnico.getText();
            String telefono = this.txtTelefonoTecnico.getText();
            int añosExperiencia = (Integer) this.spAñosExperienciaTecnico.getValue();
            String especializacion = this.txtEspecialidadTecnico.getText();

            tecnicoBuscado.setNombre(nombre);
            tecnicoBuscado.setApellido(apellido);
            tecnicoBuscado.setTelefono(telefono);
            tecnicoBuscado.setAñosExperiencia(añosExperiencia);
            tecnicoBuscado.setEspecializacion(especializacion);
            JOptionPane.showMessageDialog(this, "Técnico modificado exitosamente");
            this.actualizarTablaTecnicos();
            this.limpiarDatosTecnico();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String cedula = this.txtCedulaTecnico.getText();
        boolean exito = this.miTaller.eliminarTecnico(cedula);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Tecnico eliminado correctamente");
            this.limpiarDatosTecnico();
            this.actualizarTablaTecnicos();
        } else {
            JOptionPane.showMessageDialog(this, "No se eliminó el técnico");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void rdMotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdMotoActionPerformed
        // TODO add your handling code here:
        System.out.println("Es una moto");
        if (this.rdMoto.isSelected()) {
            this.rdCarro.setSelected(false);
            this.panelInfoMotos.setVisible(true);
            this.panelInfoCarros.setVisible(false);
        }
    }//GEN-LAST:event_rdMotoActionPerformed

    private void rdCarroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdCarroActionPerformed
        // TODO add your handling code here:
        System.out.println("Es un carro");
        if (this.rdCarro.isSelected()) {
            this.rdMoto.setSelected(false);
            this.panelInfoMotos.setVisible(false);
            this.panelInfoCarros.setVisible(true);
        }
    }//GEN-LAST:event_rdCarroActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        this.actualizarComboVehiculos();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        this.actualizarComboVehiculos();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String placa = this.txtPlacaVehiculo.getText();
        String marca = this.txtMarcaVehiculo.getText();
        int año = (Integer) this.spAñoVehiculo.getValue();
        String color = this.txtColorVehiculo.getText();
        if (this.rdCarro.isSelected()) {
            String tipoTransmision = this.txtTipoTransmisionVehiculo.getText();
            Carro nuevoCarro = new Carro(placa, marca, año, color, tipoTransmision);
            this.miTaller.getMisVehiculos().add(nuevoCarro);
            JOptionPane.showMessageDialog(this, "Carro creado");
        } else if (this.rdMoto.isSelected()) {
            int numeroPatas = (Integer) this.spNumeroPatasVehiculo.getValue();
            Moto nuevaMoto = new Moto(placa, marca, año, color, numeroPatas);
            this.miTaller.getMisVehiculos().add(nuevaMoto);
            JOptionPane.showMessageDialog(this, "Moto creada");
        }
        this.actualizarTablaVehiculos();
        this.actualizarComboVehiculos();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        String cedula = this.txtCedulaCliente.getText();

        Cliente buscado = this.miTaller.buscarCliente(cedula);
        if (buscado != null) {
            String telefono = this.txtTelefonoCliente.getText();
            String nombre = this.txtNombreCliente.getText();
            String apellido = this.txtApellidoCliente.getText();
            int añosFidelidad = (Integer) this.spAñosFidelidadCliente.getValue();
            buscado.setNombre(nombre);
            buscado.setApellido(apellido);
            buscado.setTelefono(telefono);
            buscado.setAñosFidelidad(añosFidelidad);
            buscado.setMisVehiculos(new LinkedList<>());
            for (Vehiculo aux : this.temporalVehiculos) {
                buscado.getMisVehiculos().add(aux);
            }
        }
        this.actualizarTablaClientes();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        String cedula = this.txtCedulaCliente.getText();

        Cliente buscado = this.miTaller.buscarCliente(cedula);
        if (buscado != null) {
            this.txtTelefonoCliente.setText(buscado.getTelefono());
            this.txtNombreCliente.setText(buscado.getNombre());
            this.txtApellidoCliente.setText(buscado.getApellido());
            this.spAñosFidelidadCliente.setValue(buscado.getAñosFidelidad());
            this.temporalVehiculos = buscado.getMisVehiculos();
            this.actualizarTablaVehiculosSeleccionados(buscado.getMisVehiculos());
        }
        this.actualizarTablaClientes();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        String cedula = this.txtCedulaCliente.getText();
        String telefono = this.txtTelefonoCliente.getText();
        String nombre = this.txtNombreCliente.getText();
        String apellido = this.txtApellidoCliente.getText();
        int añosFidelidad = (Integer) this.spAñosFidelidadCliente.getValue();
        Cliente nuevoCliente = new Cliente(cedula, nombre, apellido, telefono, añosFidelidad);
        for (Vehiculo aux : this.temporalVehiculos) {
            nuevoCliente.getMisVehiculos().add(aux);
        }
        this.miTaller.getMisClientes().add(nuevoCliente);
        System.out.println("***" + this.miTaller.getMisClientes().size());
        this.actualizarTablaClientes();
        this.temporalVehiculos = new LinkedList<>();
        this.actualizarTablaVehiculosSeleccionados(this.temporalVehiculos);
        this.txtCedulaCliente.setText("");
        this.txtTelefonoCliente.setText("");
        this.txtNombreCliente.setText("");
        this.txtApellidoCliente.setText("");
        this.spAñosFidelidadCliente.setValue(0);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        int indiceSeleccionado = this.comboVehiculos.getSelectedIndex();
        Vehiculo vehiculoSeleccionado = this.miTaller.getMisVehiculos().get(indiceSeleccionado);
        this.temporalVehiculos.add(vehiculoSeleccionado);
        this.actualizarTablaVehiculosSeleccionados(this.temporalVehiculos);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        int indiceSeleccionado = this.comboVehiculos.getSelectedIndex();
        Vehiculo vehiculoSeleccionado = this.miTaller.getMisVehiculos().get(indiceSeleccionado);
        this.temporalVehiculos.remove(vehiculoSeleccionado);
        this.actualizarTablaVehiculosSeleccionados(this.temporalVehiculos);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        String cedula = this.txtCedulaTecnico.getText();
        Tecnico buscado = this.miTaller.buscarTecnico(cedula);
        if (buscado == null) {
            JOptionPane.showMessageDialog(this, "El técnico buscado no existe");
        } else {
            System.out.println("Generando certificado");
            String contenido = "Manizales, 20 de mayo \n "
                    + "Certificado \n "
                    + "El taller el cacharro certifica que " + buscado.getNombre() + " " + buscado.getApellido() + " identificado con documento n°" + buscado.getCedula() + "\n"
                    + "trabaja en el taller desde " + buscado.getAñosExperiencia() + " con la especialidad de " + buscado.getEspecializacion();
            this.escribirArchivoPlano(contenido, "doc");
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        String contenido = "Cedula;Nombre;Apellido;Especialidad;Experiencia\n";
        for (Tecnico actual : this.miTaller.getMisTecnicos()) {
            contenido += actual.getCedula() + ";" + actual.getNombre() + ";" + actual.getApellido() + ";" + actual.getEspecializacion() + ";" + actual.getAñosExperiencia() + ";\n";
        }
        this.escribirArchivoPlano(contenido, "csv");
    }//GEN-LAST:event_jButton16ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazTaller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazTaller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazTaller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazTaller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazTaller().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboVehiculos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDirecionTaller;
    private javax.swing.JLabel lblNombreTaller;
    private javax.swing.JPanel panelInfoCarros;
    private javax.swing.JPanel panelInfoMotos;
    private javax.swing.JRadioButton rdCarro;
    private javax.swing.JRadioButton rdMoto;
    private javax.swing.JScrollPane scrollVehiculos;
    private javax.swing.JSpinner spAñoVehiculo;
    private javax.swing.JSpinner spAñosExperienciaTecnico;
    private javax.swing.JSpinner spAñosFidelidadCliente;
    private javax.swing.JSpinner spNumeroPatasVehiculo;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblTecnicos;
    private javax.swing.JTable tblVehiculos;
    private javax.swing.JTable tblVehiculosCliente;
    private javax.swing.JTextField txtApellidoCliente;
    private javax.swing.JTextField txtApellidoTecnico;
    private javax.swing.JTextField txtCedulaCliente;
    private javax.swing.JTextField txtCedulaTecnico;
    private javax.swing.JTextField txtColorVehiculo;
    private javax.swing.JTextField txtEspecialidadTecnico;
    private javax.swing.JTextField txtMarcaVehiculo;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreTecnico;
    private javax.swing.JTextField txtPlacaVehiculo;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoTecnico;
    private javax.swing.JTextField txtTipoTransmisionVehiculo;
    // End of variables declaration//GEN-END:variables
}
