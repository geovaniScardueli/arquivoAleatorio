package Utilitarios;

import agendar.Agendar;
import cancelAtualizarExcluir.Atualizar;
import lista.Lista;


public class Principal extends javax.swing.JFrame {

    private Agendar agendar;
    private Lista lista;
    private Atualizar atualizar;
 
    public Principal() {
        initComponents();
    }

    public void ativar() {
        this.setVisible(true);
        
        agendar = new Agendar();
        lista = new Lista();
        atualizar = new Atualizar();
        
        container.add("Agendamento", agendar);
        container.add("Lista", lista);
        container.add("atualizar", atualizar);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 350));

        container.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                containerStateChanged(evt);
            }
        });
        getContentPane().add(container, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void containerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_containerStateChanged
        if (container.getSelectedComponent() == agendar) {
            agendar.ativar();
        } else if (container.getSelectedComponent() == lista) {
            lista.ativar();
        } else if (container.getSelectedComponent() == atualizar) {
            atualizar.ativar();
        }
    }//GEN-LAST:event_containerStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane container;
    // End of variables declaration//GEN-END:variables
}
