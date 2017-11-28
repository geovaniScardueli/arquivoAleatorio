/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executavel;

import Utilitarios.Aleatorio;
import Utilitarios.Diretorio;
import Utilitarios.Principal;
import java.io.File;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

/**
 *
 * @author isold
 */
public class Executavel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Principal().ativar();
        try {
//            RandomAccessFile ran = new RandomAccessFile(new File(Diretorio.caminho), "rw");
//            ran.write("10".getBytes());
//            ran.seek(10);
//            ran.write("Geovani Scardueli".getBytes());
//            ran.seek(110);
//            ran.write("1231321321".getBytes());
//            ran.seek(210);
//            ran.write("11".getBytes());
//            ran.seek(220);
//            ran.write("Geovani Scardueli2".getBytes());
//            ran.seek(320);
//            ran.write("11111111111111111".getBytes());
//            ran.seek(420);
//            ran.write("11".getBytes());
//            ran.seek(430);
//            ran.write("Geovani Scardueli2".getBytes());
//            ran.seek(530);
//            ran.write("11111111111111111".getBytes());
//            Aleatorio ale = new Aleatorio();
//            ale.buscaPosicaoLivre();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
