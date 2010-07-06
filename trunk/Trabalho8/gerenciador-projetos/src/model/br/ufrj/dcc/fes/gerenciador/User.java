//$Id: User.java 5579 2010-06-27 00:06:49Z fes $
package model.br.ufrj.dcc.fes.gerenciador;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Pattern;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Entity
@Name("user")
@Scope(SESSION)
@Table(name="Usuario")
public class User implements Serializable
{
   private String username;
   private String password;
   private String nome;
   private String perfil;
   
   public String getPerfil() {
	return perfil;
}

public void setPerfil(String perfil) {
	this.perfil = perfil;
}

public User(String name, String password, String username)
   {
      this.nome = name;
      this.password = password;
      this.username = username;
   }
   
   public User() {}

   @NotNull
   @Length(max=100)
   public String getNome()
   {
      return nome;
   }
   public void setNome(String nome)
   {
      this.nome = nome;
   }
   
   @NotNull
   @Length(min=5, max=15)
   public String getPassword()
   {
      return password;
   }
   public void setPassword(String password)
   {
      this.password = password;
   }
   
   @Id
   @Length(min=4, max=15)
   @Pattern(regex="^\\w*$", message="not a valid username")
   public String getUsername()
   {
      return username;
   }
   public void setUsername(String username)
   {
      this.username = username;
   }
   
   @Override
   public String toString() 
   {
      return "User(" + username + ")";
   }
}
