package tutoriasapp;

// author @liu
public class GUIRegistroDePeriodoEscolar extends javax.swing.JFrame {

    public GUIRegistroDePeriodoEscolar() {
        initComponents();
        this.setTitle("Registro de periodo escolar");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRegistroDePeriodoEscolar = new javax.swing.JPanel();
        dcFechaInicio = new com.toedter.calendar.JDateChooser();
        dcFechaTermino = new com.toedter.calendar.JDateChooser();
        lblFechaInicio = new javax.swing.JLabel();
        lblFechaTermino = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblFechaInicio.setText("Fecha de inicio");

        lblFechaTermino.setText("Fecha de termino");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlRegistroDePeriodoEscolarLayout = new javax.swing.GroupLayout(pnlRegistroDePeriodoEscolar);
        pnlRegistroDePeriodoEscolar.setLayout(pnlRegistroDePeriodoEscolarLayout);
        pnlRegistroDePeriodoEscolarLayout.setHorizontalGroup(
            pnlRegistroDePeriodoEscolarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistroDePeriodoEscolarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRegistroDePeriodoEscolarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegistroDePeriodoEscolarLayout.createSequentialGroup()
                        .addGap(0, 98, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar))
                    .addComponent(dcFechaTermino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlRegistroDePeriodoEscolarLayout.createSequentialGroup()
                        .addGroup(pnlRegistroDePeriodoEscolarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaTermino)
                            .addComponent(lblFechaInicio))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(dcFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlRegistroDePeriodoEscolarLayout.setVerticalGroup(
            pnlRegistroDePeriodoEscolarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistroDePeriodoEscolarLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblFechaInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblFechaTermino)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcFechaTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(pnlRegistroDePeriodoEscolarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRegistroDePeriodoEscolar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlRegistroDePeriodoEscolar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private com.toedter.calendar.JDateChooser dcFechaInicio;
    private com.toedter.calendar.JDateChooser dcFechaTermino;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblFechaTermino;
    private javax.swing.JPanel pnlRegistroDePeriodoEscolar;
    // End of variables declaration//GEN-END:variables
}