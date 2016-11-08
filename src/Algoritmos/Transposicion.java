/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Josh <github:Josh2604>
 */
public class Transposicion extends javax.swing.JFrame {

    private static int TAM = 0;
    private static char ELEMENTOS_CIFRADO[] = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '=', '+', '/', '.', ',', '?', '|','\'','"','?',';',
        ':','<','>','{','}','[',']','_'};

    /**
     * Creates new form Transposicion
     */
    public Transposicion() {
        initComponents();
    }

    /*------------------------------------------------*/
    /**
     * Metodo que pide el ingreso del numero de columnas para el cifrado del
     * mensaje,y su validacion correspondiente donde solo debe de recibir
     * numeros enteros y un numero de columnas mayor o igual a 1.
     */
    public void num_Columnas() {
        String tam_matriz = "";
        boolean valida = false;
//        do {
        try {
            tam_matriz = txtLlave_cifrado.getText();
            if (!tam_matriz.equals("")) {
                TAM = tam_matriz.length();
                if (TAM <= 0) {
                    valida = false;
                    JOptionPane.showMessageDialog(null, "Opcion no valida, numero de filas debe de ser >=1", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    valida = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "El campo de la llave para cifrar el menaje se encuentra vacio.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Asegurece de solo ingresar numeros:", "Error al leer el tamaño de la matriz",
                    JOptionPane.ERROR_MESSAGE);
        }
//        } while (valida != true);

        if (valida == true) {
            creacion_Matriz_Transposicion(TAM);
        }
    }// fin del metodo que se encarga de filtrar el tamano de la matriz

    /**
     * Metodo que se encarga de recibir el mensaje a cifrar por parte del
     * usuario, retorna un error si el mensaje es mayor a la cantidad de
     * espacios de la matriz y si el mensaje es mas corto llena los espacios
     * restantes con u a 'x'.
     */
    private void creacion_Matriz_Transposicion(int columnas) {
        if (columnas < 100) {
            char matrix[][] = new char[columnas][columnas];
            String mensaje;
            int contador = 0;
            mensaje = txtMensaje_cifrar.getText();
            if (!mensaje.equals("")) {
                char mnsj[] = new char[mensaje.length()];

                for (int i = 0; i < mensaje.length(); i++) {
                    mnsj[i] = mensaje.charAt(i);
                }

                if (mnsj.length > columnas * columnas) {
                    JOptionPane.showMessageDialog(null, "El tamaño del mensaje es mayor que la capacidad de la matriz", "Error al ingresar los datos",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    for (int i = 0; i < columnas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            if (contador < mnsj.length) {
                                matrix[i][j] = mnsj[contador];
                                contador++;
                            } else {
                                matrix[i][j] = '%';
                            }

                        }

                    }

                }

                for (int i = 0; i < columnas; i++) {
                    textAreaCifrado.append("\n");
                    for (int j = 0; j < columnas; j++) {
                        textAreaCifrado.append("\t" + matrix[i][j]);
                    }
                }
                textAreaCifrado.append("\n\n");
                ingresa_Clave(matrix);
            } else {
                JOptionPane.showMessageDialog(null, "El mensaje debe de contener un caracter como minimo.", "ERROR!!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Numero excedido", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Metodo que pide el ingreso de la clave con la cual se cifrara el mensaje,
     * se debe de ingresar la llave con un espacio entre cada numero, retorna
     * error al no dejar el espacio y al ingresar un numero de columna no
     * existente al igual de la omision de alguna.
     */
    private void ingresa_Clave(char matrix[][]) {
        String llaveCifrado = txtLlave_cifrado.getText();
        int llave[] = findKey(ELEMENTOS_CIFRADO, generCaracter(llaveCifrado));

        if (!llaveCifrado.equals("")) {
            try {
                valida_Llave(llave, matrix);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Asegurece de solo ingresar numeros y con un espacio entre ellos.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No ha ingresado una clave, favor de verificar.", "ERROR!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * metodo que se encarga de validar la llave con la que será cifrado el
     * mensaje
     */
    private void valida_Llave(int llave[], char matrix[][]) {

        for (int i = 0; i < llave.length; i++) {
            if (llave[i] > TAM) {
                JOptionPane.showMessageDialog(null, "El numero de columna: " + llave[i] + " --> no existe", "Favor de ingresar una columna valida",
                        JOptionPane.ERROR_MESSAGE);
                System.out.println("" + llave[i]);
                break;
            } else if (llave.length > TAM) {
                JOptionPane.showMessageDialog(null, "Numero de columnas excedido", "Favor de ingresar una llave valida:",
                        JOptionPane.ERROR_MESSAGE);
                break;
            } else if (llave.length < TAM) {
                JOptionPane.showMessageDialog(null, "Falta Alguna columna", "Favor de ingresar una llave valida:",
                        JOptionPane.ERROR_MESSAGE);
                break;
            } else if (llave.length == TAM) {
                valida_Columnas_Llave(llave, matrix);
                break;

            }
        }

    }

    /**
     * Metodo que valida las columnas con que son ingresadas como llave.
     */
    private void valida_Columnas_Llave(int llave[], char matrix[][]) {
        int aux[] = llave;
        int cont = 0;
        for (int j = 0; j < llave.length; j++) {
            for (int k = 0; k < llave.length; k++) {
                if (aux[j] == llave[k]) {
                    cont++;
                }
            }
        }
        if (cont > llave.length) {
            JOptionPane.showMessageDialog(null, "El numero de una columna se encuentra repetido:",
                    "Error de columna repetida", JOptionPane.ERROR_MESSAGE);
            ingresa_Clave(matrix);
        } else {
            cifrado(matrix, llave);
        }
    }

    /**
     * Metodo donde se cifra el texto introducido por el usuario.
     */
    private void cifrado(char matrix[][], int llave[]) {
        String messagge="";
        int temporal;
        int cont = 0, cont2 = 0;
        char matrix2[][] = new char[matrix.length][matrix.length];
        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                temporal = llave[cont];
                matrix2[i][j] = matrix[i][temporal];
                cont++;
            }
            cont = 0;
        }

        for (int k = 0; k < matrix2.length; k++) {
            textAreaCifrado.append("\n");
            for (int l = 0; l < matrix2.length; l++) {
                textAreaCifrado.append("\t" + matrix2[k][l]);
            }
        }
        textAreaCifrado.append("\n\n");
        cont = 0;
        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                temporal = llave[cont];
                textAreaCifrado.append("" + matrix[cont2][temporal]);
                messagge=messagge+""+matrix[cont2][temporal];
                cont2++;
            }
            cont2 = 0;
            cont++;
        }
        textAreaCifrado.append("\n\n");
        txtMensjae_a_decifrar.setText(messagge);
    }

    /**
     * Metodo que decifra el mensaje que es introducido por el usuario, despues
     * de haber pedido la llave y compararla con la ingresada anteriormente.
     */
    private void decifrar(String Mensaje, String Llave) {
        int llave3[] = findKey(ELEMENTOS_CIFRADO, generCaracter(Llave));
        char matrix[][] = new char[Llave.length()][Llave.length()];
        boolean bandera = true;
        if (bandera == true) {

            char mnsj[] = new char[Mensaje.length()];
            if (Mensaje.length() > Llave.length() * Llave.length()) {
                JOptionPane.showMessageDialog(null, "Mensaje mayor a la capacidad de la matriz..");
            } else {
                for (int i = 0; i < Mensaje.length(); i++) {
                    mnsj[i] = Mensaje.charAt(i);
                    System.out.print("" + mnsj[i]);
                }
                System.out.println("Mensaje_total\t" + mnsj.length);
                /*======Regeneracion de la matriz para el decifrado====*/
                int aux = 0, sumador = 0, almacen = 0;
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix.length; j++) {
                        if (almacen < mnsj.length) {
                            matrix[sumador][aux] = mnsj[almacen];
                            sumador++;
                            almacen++;
                        } else {
                            matrix[aux][sumador] = '%';
                        }
                    }
                    aux++;
                    sumador = 0;
                }

                ///only debug
//                for (int i = 0; i < Llave.length(); i++) {
//                    System.out.println("");
//                    for (int j = 0; j < Llave.length(); j++) {
//                        System.out.print("\t" + matrix[i][j]);
//                    }
//                }

                //-----------impresion del mensaje decifrado-------------------------//
                textAreaDecifrado.append("\n");
                int conta = 0;
                for (int i = 0; i < matrix.length;) {
                    for (int j = 0; j < matrix.length; j++) {
                        if (llave3[j] == conta && i < llave3.length) {
                            if (matrix[i][j] != '%') {
                                textAreaDecifrado.append("" + matrix[i][j]);
                            }
                            conta++;
                        }
                        if (conta == llave3.length) {
                            i++;
                            conta = 0;
                        }
                    }

                }
                textAreaDecifrado.append("\n");                
                /*------------------------------------------*/
                boolean flag = false;
                int temporal;
                int cont = 0, cont2 = 0;
                char matrix2[][] = new char[matrix.length][matrix.length];

                for (int i = 0; i < llave3.length; i++) {
                    if (llave3[i] >= llave3.length || llave3[i] < 0) {
                        flag = true;
                    }
                }

                if (!flag == true) {
                    for (int i = 0; i < matrix2.length; i++) {
                        for (int j = 0; j < matrix2.length; j++) {
                            temporal = llave3[cont];
                            matrix2[i][j] = matrix[i][temporal];
                            cont++;
                        }
                        cont = 0;
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Un numero de columna ingresada no corresponde con la matriz original.");
                }
                /*-----------------*/
            }
        }

    }

    //only develop
    private char random(char array[]) {
        int random = (int) Math.random() * 84;
        return array[random];
    }

    /*===========================================
    |   Metodo que genera la llave con la que  
    |   el mensaje sera cifrado.
     */
    public int[] findKey(char elementos[], char mensaje[]) {
        int llave[] = new int[mensaje.length];
        int aux = 0;
        for (int i = 0; i < elementos.length; i++) {
            for (int j = 0; j < mensaje.length; j++) {
                if (elementos[i] == mensaje[j]) {
                    llave[aux] = j;
                    aux++;
                }
            }
        }

//        only debug
//        printArreglo(llave);
//        System.out.println("\n\n");
        return llave;
    }

    /*===========================================
    | Metodo sobreescrito que imprime un arreglo 
    |
     */
    private void printArreglo(char expanClave[]) {
        for (int i = 0; i < expanClave.length; i++) {
            System.out.print("" + expanClave[i]);
        }
        System.out.println("\n");
    }

    private void printArreglo(int expanClave[]) {
        for (int i = 0; i < expanClave.length; i++) {
            System.out.print("" + expanClave[i]);
        }
        System.out.println("\n");
    }

    /*===========================================
    |   Método para generar caracteres apartir
    |   de una cadena (String).
     */
    private char[] generCaracter(String cadena) {
        char mcadena[] = new char[cadena.length()];
        for (int i = 0; i < cadena.length(); i++) {
            mcadena[i] = cadena.charAt(i);
        }
        return mcadena;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtMensaje_cifrar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtLlave_cifrado = new javax.swing.JTextField();
        btnCifrar = new javax.swing.JButton();
        btnDecifrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaCifrado = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaDecifrado = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        txtMensjae_a_decifrar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtllave_decifrar = new javax.swing.JTextField();
        txtTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ingrese el mensaje que decea cifrar:");

        jLabel2.setText("Ingrese la llave para cifrar el mensaje:");

        btnCifrar.setText("Cifrar");
        btnCifrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCifrarActionPerformed(evt);
            }
        });

        btnDecifrar.setText("Decifrar");
        btnDecifrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecifrarActionPerformed(evt);
            }
        });

        textAreaCifrado.setColumns(20);
        textAreaCifrado.setRows(5);
        jScrollPane1.setViewportView(textAreaCifrado);

        textAreaDecifrado.setColumns(20);
        textAreaDecifrado.setRows(5);
        jScrollPane2.setViewportView(textAreaDecifrado);

        jLabel3.setText("Ingrese el mensaje a decifar:");

        jLabel4.setText("Ingrese la llave con la que decea decifrar el mensaje:");

        txtTitle.setBackground(new java.awt.Color(255, 0, 102));
        txtTitle.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txtTitle.setForeground(new java.awt.Color(255, 204, 0));
        txtTitle.setText("Cifrado Por Transposicion Columnar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtMensjae_a_decifrar, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtllave_decifrar, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(261, 261, 261)
                                .addComponent(btnDecifrar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(txtTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLlave_cifrado, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMensaje_cifrar, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(btnCifrar)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTitle)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMensaje_cifrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtLlave_cifrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMensjae_a_decifrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtllave_decifrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btnDecifrar)
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btnCifrar)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCifrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCifrarActionPerformed
        // TODO add your handling code here:
        num_Columnas();
    }//GEN-LAST:event_btnCifrarActionPerformed

    private void btnDecifrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecifrarActionPerformed
        // TODO add your handling code here:
        String Mensaje = txtMensjae_a_decifrar.getText();
        String Llave = txtllave_decifrar.getText();
        decifrar(Mensaje, Llave);
    }//GEN-LAST:event_btnDecifrarActionPerformed

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
            java.util.logging.Logger.getLogger(Transposicion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transposicion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transposicion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transposicion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transposicion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCifrar;
    private javax.swing.JButton btnDecifrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea textAreaCifrado;
    private javax.swing.JTextArea textAreaDecifrado;
    private javax.swing.JTextField txtLlave_cifrado;
    private javax.swing.JTextField txtMensaje_cifrar;
    private javax.swing.JTextField txtMensjae_a_decifrar;
    private javax.swing.JLabel txtTitle;
    private javax.swing.JTextField txtllave_decifrar;
    // End of variables declaration//GEN-END:variables
}
