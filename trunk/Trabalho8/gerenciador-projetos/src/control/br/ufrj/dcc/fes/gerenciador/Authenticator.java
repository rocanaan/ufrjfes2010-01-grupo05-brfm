package control.br.ufrj.dcc.fes.gerenciador;

import javax.ejb.Local;

@Local
public interface Authenticator
{
   boolean authenticate();
}
