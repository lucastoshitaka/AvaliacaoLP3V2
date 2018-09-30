/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAOs.DAODisciplinas;
import DAOs.DAODisciplinasHasModalidades;

import Entidades.Disciplinas;
import Entidades.DisciplinasHasModalidades;
import Entidades.DisciplinasHasModalidadesPK;
import java.util.List;

/**
 *
 * @author lucastoshitaka
 */
public class RemoverEntidadeTable1 {

    public RemoverEntidadeTable1(int pegarCpf, String disciplinaEscolhida) {
        DAODisciplinasHasModalidades daoDisciplinasHasModalidades = new DAODisciplinasHasModalidades();
        List<DisciplinasHasModalidades> lista = daoDisciplinasHasModalidades.list();

        Disciplinas disciplinas = new Disciplinas();
        DAODisciplinas daoDisciplinas = new DAODisciplinas();
        int disciplinaEscolhida1 = 0;
        List<Disciplinas> ii = daoDisciplinas.list();
        for (Disciplinas disciplinas1 : ii) {
            if (disciplinas1.getNomeDisciplinas().equals(disciplinaEscolhida)) {
                disciplinaEscolhida1 = disciplinas1.getIdDisciplinas();
            }
        }

        for (DisciplinasHasModalidades disciplinasHasModalidades : lista) {
            DisciplinasHasModalidadesPK a = disciplinasHasModalidades.getDisciplinasHasModalidadesPK();

            if (a.getModalidadesIdModalidades()==pegarCpf && a.getDisciplinasIdDisciplinas() == disciplinaEscolhida1) {
                daoDisciplinasHasModalidades.remover(disciplinasHasModalidades);
            }

        }

    }

    RemoverEntidadeTable1() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
