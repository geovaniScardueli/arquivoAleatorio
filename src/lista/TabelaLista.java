/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista;

import Utilitarios.Agenda;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author isold
 */
public class TabelaLista extends AbstractTableModel {

    private ArrayList<Agenda> agendamentos = new ArrayList();
    private String[] columns = {"Pessoa", "telefone", "ID"};

    @Override
    public int getRowCount() {
        return agendamentos.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return agendamentos.get(rowIndex).getNomePessoa();
            case 1:
                return agendamentos.get(rowIndex).getTelefone();
            case 2:
                return agendamentos.get(rowIndex).getId();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    public void addAgendas(ArrayList<Agenda> agendas) {
        agendamentos = agendas;
        fireTableDataChanged();
    }

}
