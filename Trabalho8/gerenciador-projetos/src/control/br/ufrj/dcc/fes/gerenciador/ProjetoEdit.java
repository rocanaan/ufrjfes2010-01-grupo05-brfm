package control.br.ufrj.dcc.fes.gerenciador;

import javax.ejb.Local;

import model.br.ufrj.dcc.fes.gerenciador.Projeto;

@Local
public interface ProjetoEdit {
	 public void selectProjeto(); //(Projeto selectedProjeto);
	   
	 public void updateProjeto();
	   
	 public void confirmProjeto();
	   
	 public void cancel();
	   
	 public void destroy();
	   
}
