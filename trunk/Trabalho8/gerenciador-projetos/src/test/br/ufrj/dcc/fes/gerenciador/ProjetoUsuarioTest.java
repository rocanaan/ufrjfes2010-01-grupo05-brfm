//$Id: ProjetoUsuarioTest.java 5810 2010-07-16 06:46:47Z fes $
package test.br.ufrj.dcc.fes.gerenciador;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import model.br.ufrj.dcc.fes.gerenciador.Projeto;
import model.br.ufrj.dcc.fes.gerenciador.ProjetoUsuario;
import model.br.ufrj.dcc.fes.gerenciador.User;

import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.core.Manager;
import org.jboss.seam.mock.SeamTest;
import org.testng.annotations.Test;

import control.br.ufrj.dcc.fes.gerenciador.ProjetoJoin;

public class ProjetoUsuarioTest extends SeamTest
{
   
   @Test
   public void testGerenciadorProjetos() throws Exception
   {
      
      new FacesRequest() {
         
         @Override
         protected void invokeApplication() throws Exception
         {
            Contexts.getSessionContext().set("user", new User("Usuario Demo", "demo", "demo"));
            setValue("#{identity.username}", "demo");
            setValue("#{identity.password}", "demo");            
            invokeAction("#{identity.login}");
         }
         
      }.run();
      
      new FacesRequest("/main.xhtml") {

         @Override
         protected void updateModelValues() throws Exception
         {
            setValue("#{projetoSearch.searchString}", "nada");
         }

         @Override
         protected void invokeApplication()
         {
            assert invokeAction("#{projetoSearch.find}")==null;
         }

         @Override
         protected void renderResponse()
         {
            DataModel projetos = (DataModel) Contexts.getSessionContext().get("projetos");
            assert projetos.getRowCount()==1;
            assert ( (Projeto) projetos.getRowData() ).getDescricaoBreve().equals("xxxxx");
            assert getValue("#{projetoSearch.searchString}").equals("Projeto X");
            assert !Manager.instance().isLongRunningConversation();
         }
         
      }.run();
      
      String id = new FacesRequest("/main.xhtml") {
         
         @Override
         protected void invokeApplication() throws Exception {
            ProjetoJoin participacao = (ProjetoJoin) getInstance("participarProjeto");
            DataModel projetos = (DataModel) Contexts.getSessionContext().get("projetos");
            assert projetos.getRowCount()==1;
            participacao.selectProjeto( (Projeto) projetos.getRowData() );
         }

         @Override
         protected void renderResponse()
         {
            Projeto projeto = (Projeto) Contexts.getConversationContext().get("projeto");
            assert projeto.getLocalizacao().equals("Jardim Botanico, Rio de Janeiro");
            assert projeto.getDescricaoBreve().equals("xxxxx");
            assert Manager.instance().isLongRunningConversation();
         }
         
      }.run();
      
      id = new FacesRequest("/projeto.xhtml", id) {

         @Override
         protected void invokeApplication()
         {
            invokeAction("#{participarProjeto.participarProjeto}");
         }

         @Override
         protected void renderResponse()
         {
            assert getValue("#{projetoUsuario.user}")!=null;
            assert getValue("#{projetoUsuario.projeto}")!=null;
            ProjetoUsuario participacao = (ProjetoUsuario) Contexts.getConversationContext().get("projetoUsuario");
            assert participacao.getProjeto()==Contexts.getConversationContext().get("projeto");
            assert participacao.getUser()==Contexts.getSessionContext().get("user");
            assert Manager.instance().isLongRunningConversation();
         }
         
      }.run();
      
      new FacesRequest("/join.xhtml", id) {

         @Override
         protected void processValidations() throws Exception
         {
            validateValue("#{projetoUsuario.checkinDate}", "123");
            assert isValidationFailure();
         }

         /*
         @Override
         protected void renderResponse()
         {
            Iterator messages = FacesContext.getCurrentInstance().getMessages();
            assert messages.hasNext();
            assert ( (FacesMessage) messages.next() ).getSummary().equals("Campo temporal");
            assert !messages.hasNext();
            assert Manager.instance().isLongRunningConversation();
         }
         */
         @Override
         protected void afterRequest()
         {
            assert !isInvokeApplicationBegun();
         }
         
      }.run();
      
      new FacesRequest("/join.xhtml", id) {

         @Override
         protected void processValidations() throws Exception
         {
            validateValue("#{projetoUsuario.checkinDate}", "");
            assert isValidationFailure();
         }

         @Override
         protected void renderResponse()
         {
            Iterator messages = FacesContext.getCurrentInstance().getMessages();
            assert messages.hasNext();
            assert ( (FacesMessage) messages.next() ).getSummary().equals("Campo faltando");
            assert !messages.hasNext();
            assert Manager.instance().isLongRunningConversation();
         }
         
         @Override
         protected void afterRequest()
         {
            assert !isInvokeApplicationBegun();
         }
         
      }.run();
      
      new FacesRequest("/join.xhtml", id) {
         
         @Override @SuppressWarnings("deprecation")
         protected void updateModelValues() throws Exception
         { 
            setValue("#{projetoUsuario.responsavel}", true);
            setValue("#{projetoUsuario.observacoes}", "USUARIO DEMO");
            setValue("#{projetoUsuario.equipe}", '1');
            Date now = new Date();
            setValue("#{projetoUsuario.checkinDate}", now);
            setValue("#{projetoUsuario.checkoutDate}", now);
         }

         @Override
         protected void invokeApplication()
         {
            assert invokeAction("#{participarProjeto.setParticipacaoDetails}")==null;
         }

         
         //TODO mensagem
         @Override
         protected void renderResponse()
         {
            Iterator messages = FacesContext.getCurrentInstance().getMessages();
            assert messages.hasNext();
            FacesMessage message = (FacesMessage) messages.next();
            assert message.getSummary().equals("Check out date must be later than check in date");
            assert !messages.hasNext();
            assert Manager.instance().isLongRunningConversation();
         }
         
         @Override
         protected void afterRequest()
         {
            assert isInvokeApplicationComplete();
         }
         
      }.run();
      
      new FacesRequest("/join.xhtml", id) {
         
         @Override @SuppressWarnings("deprecation")
         protected void updateModelValues() throws Exception
         {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, 2);
            setValue("#{projetoUsuario.checkoutDate}", cal.getTime() );
         }

         @Override
         protected void invokeApplication()
         {
            invokeAction("#{participarProjeto.setParticipacaoDetails}");
         }

         @Override
         protected void renderResponse()
         {
            assert Manager.instance().isLongRunningConversation();
         }
         
         @Override
         protected void afterRequest()
         {
            assert isInvokeApplicationComplete();
         }
         
      }.run();
      
      new FacesRequest("/confirm.xhtml", id) {

         @Override
         protected void invokeApplication()
         {
            invokeAction("#{participarProjeto.confirm}");
         }
         
         @Override
         protected void afterRequest()
         {
            assert isInvokeApplicationComplete();
         }
         
      }.run();
      
      new NonFacesRequest("/main.xhtml") {

         @Override
         protected void renderResponse()
         {
            ListDataModel projetosUsuarios = (ListDataModel) getInstance("projetosUsuarios");
            assert projetosUsuarios.getRowCount()==1;
            projetosUsuarios.setRowIndex(0);
            ProjetoUsuario participacao = (ProjetoUsuario) projetosUsuarios.getRowData();
            assert participacao.getProjeto().getLocalizacao().equals("Jardim Botanico, Rio de Janeiro");
            assert participacao.getUser().getUsername().equals("demo");
            assert !Manager.instance().isLongRunningConversation();
         }
         
      }.run();
      
      new FacesRequest("/main.xhtml") {
         
         @Override
         protected void invokeApplication()
         {
            ListDataModel projetosUsuarios = (ListDataModel) Contexts.getSessionContext().get("projetosUsuarios");
            projetosUsuarios.setRowIndex(0);
            invokeAction("#{projetoUsuarioList.cancel}");
         }

         @Override
         protected void renderResponse()
         {
            ListDataModel projetosUsuarios = (ListDataModel) Contexts.getSessionContext().get("projetosUsuarios");
            assert projetosUsuarios.getRowCount()==0;
            assert !Manager.instance().isLongRunningConversation();
         }
         
      }.run();
      
   }
   
}
