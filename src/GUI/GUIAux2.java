/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAOs.DAODisciplinas;
import Entidades.Disciplinas;
import GUI.GUIAluno;
import GUI.GUIDisciplinas;
import GUI.GUIEstado;
import GUI.GUIDisciplinas;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author lucastoshitaka
 */
public class GUIAux2 extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel(new GridLayout(1, 2));
    JLabel placa = new JLabel("Escolha a modalidade");
    JComboBox cb = new JComboBox();
//    private JButton btDisciplinas= new JButton("Disciplinas");
//    private JButton btEstado= new JButton("Estado");
//    private JButton btDisciplinas= new JButton("Disciplinas");
    private String[] listaComboBox;
    private String item;

    JButton btEnviar = new JButton("Selecionar");

    public GUIAux2() {
            
            setSize(800, 200);
            setResizable(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            cp = getContentPane();
            cp.setLayout(new BorderLayout());
            setTitle("Escolha");
            setModal(true);
            cp.add(pnNorte);
            pnNorte.add(placa);
            DAODisciplinas daoDisciplinas = new DAODisciplinas();
            List<Disciplinas> listaDisciplinas = daoDisciplinas.list();
            // String aux3 = "";
            //String[] aa2 = new String[listaDisciplinas.size()];
            for (Disciplinas aux : listaDisciplinas) {

                cb.addItem(aux.getNomeDisciplinas());

            }

            pnNorte.add(cb);
            pnNorte.add(btEnviar);
            
            

         // int aa=null;
          
            btEnviar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("btasdasd");
                     item = String.valueOf(cb.getSelectedItem());
                     dispose();
                   // System.out.println(item);
                }
            });
            
            setVisible(true);
            
        

    }

//    public void setLista(String[] listaComboBox){
//        this.listaComboBox=listaComboBox;
//
//    }
    public String getItem() {
        return item;
    }

    public static void main(String[] args) {
        GUIAux2 guiAux1 = new GUIAux2();
    }

//    
//    public String GUIPegarDado(String titulo, String[] listaComboBox){
//    setSize(800, 200);
//        setResizable(true);
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        cp = getContentPane();
//        cp.setLayout(new BorderLayout());
//        setTitle("Escolha");
//        cp.add(pnNorte);
//        pnNorte.add(placa);
//        
//        cb.addItem(listaComboBox);
//        pnNorte.add(cb);
//        cb.setSelectedItem(listaComboBox[0]);
//        cb.requestFocus();
//        
//        return (String) cb.getSelectedItem();
//        
//        
//}
}
