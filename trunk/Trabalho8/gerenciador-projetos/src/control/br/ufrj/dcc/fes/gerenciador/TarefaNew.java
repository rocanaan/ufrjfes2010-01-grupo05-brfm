package control.br.ufrj.dcc.fes.gerenciador;

import javax.ejb.Local;

import model.br.ufrj.dcc.fes.gerenciador.Projeto;

@Local
public interface TarefaNew {

	   
	   public void novaTarefa();

	   
	   public void confirm();
	   
	   public void cancel();

}
