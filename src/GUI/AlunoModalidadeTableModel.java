package GUI;

import Entidades.AlunoHasModalidades;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AlunoModalidadeTableModel extends AbstractTableModel {

    private final Class classes[] = new Class[]{Integer.class, String.class};
    private final String colunas[] = new String[]{"CPF aluno", "Modalidade"};
    private List<String> dados;
    private String pegarCpf;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //private final SimpleDateFormat timeformat = new SimpleDateFormat("h:mm a");

    public AlunoModalidadeTableModel(List<String> dados) {
        this.dados = dados;
    }

    public void setDados(List<String> dados,String pegarCpf) {
        
this.pegarCpf=pegarCpf;
        this.dados = dados;

    }

    public List<String> getDados() {
        return this.dados;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        String s = dados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pegarCpf;
            case 1:
                return s;
            default:
                throw new IndexOutOfBoundsException("Coluna Inválida!");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return false;
        }
        return true;
    }

//    @Override
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//
//      //  mudou = true;
//        AlunoHasModalidades s = dados.get(rowIndex);
//        switch (columnIndex) {
//            case 0:              
//                    s.set(String) aValue);                
//                break;
//            case 1:
//                s.setNomeAlunoHasModalidades((Integer) aValue);
//                break;          
//            default:
//                throw new IndexOutOfBoundsException("Coluna Inválida!!!");
//        }
//        fireTableDataChanged();
//    }
    public String getValue(int rowIndex) {
        return pegarCpf;
    }

    public int indexOf(AlunoHasModalidades tc) {
        return dados.indexOf(tc);
    }

//    public void onAdd(AlunoHasModalidades tipoConta) {
//        dados.add(tipoConta);
//        fireTableRowsInserted(indexOf(tipoConta), indexOf(tipoConta));
//    }

    public void onRemove(int[] rowIndex) {
        int x;
        for (x = rowIndex.length - 1; x >= 0; x--) {
            dados.remove(rowIndex[x]);
            fireTableRowsDeleted(rowIndex[x], rowIndex[x]);
        }
    }
}
