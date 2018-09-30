package GUI;

import DAOs.DAOAlunoHasModalidades;
import DAOs.DAOModalidades;
import Entidades.AlunoHasModalidades;
import Entidades.AlunoHasModalidadesPK;
import Entidades.Modalidades;
import tools.CentroDoMonitorMaior;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import tools.JanelaPesquisar;
import tools.StringTools;

public class AlunoModalidadeJTable extends JDialog {

    private final Container cp;
    private final JPanel painelAvisos = new JPanel();
    private final JButton btnAdd = new JButton("Adicionar");
    private final JButton btnRem = new JButton("Remover");
    private final JButton btnCarregar = new JButton("Carregar dados");

    private JTable table = new JTable();
    // private AlunoModalidadeTableModel tableModel;
    private AlunoModalidadeTableModel tableModel;

    private DAOAlunoHasModalidades daoAlunoHasModalidades = new DAOAlunoHasModalidades();

    public AlunoModalidadeJTable(String pegarCpf) {

        setTitle("CRUD Tipo Conta");
        setLayout(new FlowLayout());
        setSize(600, 500);

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(BorderLayout.NORTH, painelAvisos);
        setModal(true);

//        List< AlunoHasModalidades> lista = new ArrayList<>();
//        tableModel = new AlunoModalidadeTableModel(lista);
        List< String> lista = new ArrayList<>();
        tableModel = new AlunoModalidadeTableModel(lista);

        table.setModel(tableModel);
        // setModal(true);

        JScrollPane scrollPane = new JScrollPane(table);
        cp.add(scrollPane);

//        painelBotoes.add(btnAdd);
//        painelBotoes.add(btnRem);
        painelAvisos.add(new JLabel("Pressione 1 = Insere novo registro"));
        painelAvisos.add(new JLabel("     --     "));
        painelAvisos.add(new JLabel("Pressione 2 = Exclui registro selecionado"));
        painelAvisos.setBackground(Color.cyan);

        table.setDefaultEditor(Date.class, new DateEditor());
        table.setDefaultRenderer(Date.class, new DateRenderer());

        // É necessário clicar antes na tabela para o código funcionar
        InputMap im = table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap actionMap = table.getActionMap();

        KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_1, 0);
        im.put(enterKey, "Action.insert");

        actionMap.put("Action.insert", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnAdd.doClick();
            }
        });

        KeyStroke delKey = KeyStroke.getKeyStroke(KeyEvent.VK_2, 0);
        im.put(delKey, "Action.delete");

        actionMap.put("Action.delete", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (table.getSelectedRow() >= 0) {
                    String ee = tableModel.getValue(table.getSelectedRow());
//                    AlunoHasModalidades eee = daoAlunoHasModalidades.obter(pegarCpf);
                    

                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(cp,
                            //                            "Confirma a exclusão desse tipo de conta [" + tableModel.getValue(table.getSelectedRow()).getAlunoHasModalidadesPK().getAlunoCpf() + " - "
                            //                            + tableModel.getValue(table.getSelectedRow()).getAlunoHasModalidadesPK().getModalidadesIdModalidades() + "]?", "Confirm",
                            //                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {

                            "Confirma a exclusão desse tipo de conta [" + pegarCpf + " - "
                            + String.valueOf(tableModel.getValueAt(table.getSelectedRow(), 1)) + "]?", "Confirm",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {

                        btnRem.doClick();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Escolha na tabela a conta a ser excluída");
                }
            }
        });

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                dispose();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DAOModalidades daoModalidades = new DAOModalidades();
                List<Modalidades> listaModalidades = daoModalidades.list();

                String d = pegarCpf;

                GUIAux1 oo = new GUIAux1();
                String f = oo.getItem();

                try {
                    int a = 0;

                    try {
                        AlunoHasModalidades c = daoAlunoHasModalidades.obter(d);
                    } catch (Exception aaasd) {

                        for (Modalidades aux : listaModalidades) {

                            String aaa = aux.getNomeModalidades();

                            if (aaa.equals(f)) {
                                a = aux.getIdModalidades();
                            }

                        }

                        GUIAlunoModalidades xxx = new GUIAlunoModalidades(d, a);

                        btnCarregar.doClick();

                    }

                } catch (Exception err) {
                  //  JOptionPane.showMessageDialog(cp, "Erro ao inserir a conta" /*+ d
                       //     + " com o erro=>" + err.getMessage()*/);
                }

            }
        });

        btnRem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                try {

                    if (table.getSelectedRow() != -1 && table.getSelectedRow() < tableModel.getRowCount()) {
                        String ee = tableModel.getValue(table.getSelectedRow());
                        System.out.println("foi1");
                        
                        //RemoverEntidadeTable removerEntidadeTable = new RemoverEntidadeTable();
                         new RemoverEntidadeTable(pegarCpf, (String) table.getValueAt(table.getSelectedRow(), 1));
                        
                        tableModel.onRemove(table.getSelectedRows());

                    } else {
                        JOptionPane.showMessageDialog(cp, "Escolha na tabela o tipo de conta a ser excluído");
                        table.requestFocus();
                    }
                    tableModel.fireTableDataChanged();
//                } catch (Exception isisis) {
                    //JOptionPane.showMessageDialog(cp, "Selecione um registo");
//                }
            }
        });

        btnCarregar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DAOAlunoHasModalidades dAOAlunoHasModalidades = new DAOAlunoHasModalidades();
                Modalidades modalidades = new Modalidades();
                DAOModalidades daoModalidades = new DAOModalidades();
                try {
                    List<AlunoHasModalidades> lc = daoAlunoHasModalidades.list();
                    String aux3 = "";
                    List<AlunoHasModalidades> lc1 = daoAlunoHasModalidades.list();
                    HashMap<Integer, String> map = new HashMap<>();
                    //int contador2 = 0;
                    for (AlunoHasModalidades aux : lc) {

                        AlunoHasModalidadesPK a = aux.getAlunoHasModalidadesPK();
                        System.out.println("3");
                        if (!a.getAlunoCpf().equals(pegarCpf)) {
                            lc1.remove(aux);
                            
                            
                          //  contador2++;
                        } else {
                            Modalidades io = daoModalidades.obter(a.getModalidadesIdModalidades());
                            
                            map.put(a.getModalidadesIdModalidades(), io.getNomeModalidades() );

                        }
                    }
                    
                    List<String> ListofValues = null;
                    ListofValues = map.values().stream().collect(Collectors. 
                                       toCollection(ArrayList::new));
                    

                    tableModel.setDados(ListofValues,pegarCpf);
                    tableModel.fireTableDataChanged();
                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(cp, "Erro ao carregar os dados..." + ex.getMessage());
                }
            }

        });

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // if (tableModel.mudou) {
                try{
                if (table.getSelectedRow() != -1 && table.getSelectedRow() < tableModel.getRowCount()) {
                    String ee = tableModel.getValue(table.getSelectedRow());
                    AlunoHasModalidades eee = daoAlunoHasModalidades.obter(ee);
                    AlunoHasModalidades c = eee;
                    daoAlunoHasModalidades.atualizar(c);
                }
                //}
                }catch(Exception disd){}
            }
        }
        );

        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();

        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        btnCarregar.doClick();//carrega os dados 

        setVisible(true);
        setModal(true);
    } //fim do construtor da GUI

    private static class DateRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!(value instanceof Date)) {
                return this;
            }
            DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
            setText(DATE_FORMAT.format((Date) value));
            return this;
        }
    }

    private static class DateEditor extends AbstractCellEditor implements TableCellEditor {

        private static final long serialVersionUID = 1L;
        private final JSpinner theSpinner;
        private Object value;

        DateEditor() {
            theSpinner = new JSpinner(new SpinnerDateModel());
            theSpinner.setOpaque(true);
            theSpinner.setEditor(new JSpinner.DateEditor(theSpinner, "dd/MM/yyyy"));
        }

        @Override
        public Object getCellEditorValue() {
            return theSpinner.getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            theSpinner.setValue(value);
            if (isSelected) {
                theSpinner.setBackground(table.getSelectionBackground());
            } else {
                theSpinner.setBackground(table.getBackground());
            }
            return theSpinner;
        }
    }

    public static void main(String[] args) {
        AlunoModalidadeJTable alunoModalidadeJTable = new AlunoModalidadeJTable("3");
    }
}
