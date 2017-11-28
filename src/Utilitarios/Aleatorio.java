/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author isold
 */
public class Aleatorio {
    
    private RandomAccessFile ran;
    private long ponteiro = 0l;
    private String id = "1";
    
    public Aleatorio() {
        try {
            ran = new RandomAccessFile(new File(Diretorio.caminho), "rw");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void inserir(Agenda agenda) {
        try {
            if (Diretorio.priemiraAtivacao) {
                buscaPosicaoLivre();
                Diretorio.priemiraAtivacao = false;
            } else {
                int sequencia = Integer.parseInt(id.trim());
                sequencia++;
                id = sequencia + "";
            }
            System.out.println(ran.getFilePointer());
            ran.seek(ponteiro);
            ran.write(id.getBytes());
            ponteiro += 10;
            ran.seek(ponteiro);
            ran.write(agenda.getNomePessoa().getBytes());
            ponteiro += 100;
            ran.seek(ponteiro);
            ran.write(agenda.getTelefone().getBytes());
            ponteiro += 100;
            ran.seek(ponteiro);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public ArrayList agendas() throws Exception {
        ArrayList<Agenda> agendas = new ArrayList();
        byte [] id = new byte[10];
        byte [] nome = new byte[100];
        byte [] telefone = new byte[100];
        ran.seek(0);
        while (true) {
            if (ran.read(id) < 0) {
                break;
            }
            ran.read(nome);
            ran.read(telefone);
            Agenda age = new Agenda();
            age.setId(new String(id).trim());
            age.setNomePessoa(new String(nome).trim());
            age.setTelefone(new String(telefone).trim());
            agendas.add(age);
        }
        return agendas;
    }
    
    
    private void buscaPosicaoLivre() throws Exception{
        int conseguiLer;
        byte [] arr = new byte[10];
        ran.seek(0);
        while(true){
            conseguiLer = ran.read(arr);
            if (conseguiLer < 0) {
                String temp = new String(arr,"UTF-8");
                if (!"".equalsIgnoreCase(temp.trim())) {
                    int sequencia = Integer.parseInt(temp.trim());
                    sequencia++;
                    id = sequencia + "";
                    ponteiro = ran.getFilePointer();
                }
                break;
            }
            ran.seek(ran.getFilePointer() + 200);
        }
    }
}
