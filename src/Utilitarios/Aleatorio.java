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
    private static long ponteiro = 0l;
    private String id = "1";

    public Aleatorio() {
        try {
            ran = new RandomAccessFile(new File(Diretorio.caminho), "rw");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public void ponteiro() {
        try {
        System.out.println(ran.getFilePointer() + "");
            System.out.println(this.ponteiro);
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
        byte[] id = new byte[10];
        byte[] nome = new byte[100];
        byte[] telefone = new byte[100];
        ran.seek(0);
        while (true) {
            if (ran.read(id) < 0) {
                break;
            }
            if ("".equals(new String(id).trim())) {
                this.ponteiro = ran.getFilePointer() - 10;
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

    public Agenda buscaAgenda(String id) throws Exception {
        int conseguiLer;
        byte[] arr = new byte[10];
        byte[] nome = new byte[100];
        byte[] telefone = new byte[100];
        ran.seek(0);
        Agenda retorno = null;
        while (true) {
            conseguiLer = ran.read(arr);
            if (conseguiLer < 0) {
                break;
            }
            String temp = new String(arr);
            if (id.equals(temp.trim())) {
                retorno = new Agenda();
                retorno.setPonteiro(ran.getFilePointer());
                ran.read(nome);
                ran.read(telefone);
                retorno.setNomePessoa(new String(nome).trim());
                retorno.setTelefone(new String(telefone).trim());
                break;
            }
            ran.seek(ran.getFilePointer() + 200);
        }
        return retorno;
    }

    public void atualizarAgenda(Agenda agenda) throws Exception {
        byte[] limpar = new byte[100];
        ran.seek(agenda.getPonteiro());
        ran.write(limpar);
        ran.write(limpar);
        ran.seek(agenda.getPonteiro());
        ran.write(agenda.getNomePessoa().getBytes());
        ran.seek(agenda.getPonteiro() + 100);
        ran.write(agenda.getTelefone().getBytes());
    }

    public void excluirAgenda(long ponteiro) throws Exception {
        if (this.ponteiro == 0) {
            buscaPosicaoLivre();
            Diretorio.priemiraAtivacao = false;
        }
        byte[] limpar = new byte[210];
        byte[] id = new byte[10];
        byte[] infor = new byte[100];
        ran.seek(ponteiro);
        ran.write(limpar);
        ran.seek(this.ponteiro - 210);
        Agenda temp = new Agenda();
        ran.read(id);
        temp.setId(new String(id).trim());
        ran.read(infor);
        temp.setNomePessoa(new String(infor).trim());
        ran.read(infor);
        temp.setTelefone(new String(infor).trim());
        ran.seek(ponteiro);
        ran.write(temp.getId().getBytes());
        ponteiro += 10;
        ran.seek(ponteiro);
        ran.write(temp.getNomePessoa().getBytes());
        ponteiro += 100;
        ran.seek(ponteiro);
        ran.write(temp.getTelefone().getBytes());
        ran.seek(this.ponteiro - 210);
        ran.write(limpar);
        this.ponteiro = this.ponteiro - 210;
    }

    private void buscaPosicaoLivre() throws Exception {
        int conseguiLer;
        byte[] arr = new byte[10];
        ran.seek(0);
        while (true) {
            conseguiLer = ran.read(arr);
            if (conseguiLer < 0) {
                String temp = new String(arr, "UTF-8");
                if (!"".equalsIgnoreCase(temp.trim())) {
                    int sequencia = Integer.parseInt(id);
                    sequencia++;
                    id = sequencia + "";
                    ponteiro = ran.getFilePointer();
                }
                break;
            }
            String temp = new String(arr, "UTF-8");
            if ("".equals(temp.trim())) {
                int sequencia = Integer.parseInt(id);
                sequencia++;
                id = sequencia + "";
                ponteiro = ran.getFilePointer() - 10;
                break;
            }
            if (Integer.parseInt(temp.trim()) > Integer.parseInt(id)) {
                id = temp.trim();
            }
            ran.seek(ran.getFilePointer() + 200);
        }
    }
}
