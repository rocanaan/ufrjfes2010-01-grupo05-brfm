package control.br.ufrj.dcc.fes.gerenciador;

import javax.ejb.Local;

import model.br.ufrj.dcc.fes.gerenciador.Tarefa;


@Local
public interface TarefaEdit {
	
	 public void selectTarefa(Tarefa selectedTarefa);
	   
	 public void updateTarefa();
	   
	 public void confirmTarefa();
	   
	 public void cancel();
	   
	 public void destroy();

}
