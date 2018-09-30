/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAOs.DAOAlunoHasModalidades;
import DAOs.DAOModalidades;
import Entidades.AlunoHasModalidades;
import Entidades.AlunoHasModalidadesPK;
import Entidades.Modalidades;
import java.util.List;

/**
 *
 * @author lucastoshitaka
 */
public class RemoverEntidadeTable {

    public RemoverEntidadeTable(String pegarCpf, String modalidadeEscolhida) {
        DAOAlunoHasModalidades daoAlunoHasModalidades = new DAOAlunoHasModalidades();

        List<AlunoHasModalidades> lista = daoAlunoHasModalidades.list();

        Modalidades modalidades = new Modalidades();
        DAOModalidades daoModalidades = new DAOModalidades();
        int modalidadeEscolhida1 = 0;
        List<Modalidades> ii = daoModalidades.list();
        for (Modalidades modalidades1 : ii) {
            if (modalidades1.getNomeModalidades().equals(modalidadeEscolhida)) {
                modalidadeEscolhida1 = modalidades1.getIdModalidades();
            }
        }

        for (AlunoHasModalidades alunoHasModalidades : lista) {
            AlunoHasModalidadesPK a = alunoHasModalidades.getAlunoHasModalidadesPK();

            if (a.getAlunoCpf().equals(pegarCpf) && a.getModalidadesIdModalidades() == modalidadeEscolhida1) {
                daoAlunoHasModalidades.remover(alunoHasModalidades);
            }

        }

    }

    RemoverEntidadeTable() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
