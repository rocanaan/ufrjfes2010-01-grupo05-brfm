package test.br.ufrj.dcc.fes.gerenciador;

import static org.junit.Assert.*;

import java.util.Calendar;

import model.br.ufrj.dcc.fes.gerenciador.Projeto;
import model.br.ufrj.dcc.fes.gerenciador.ProjetoUsuario;
import model.br.ufrj.dcc.fes.gerenciador.User;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import control.br.ufrj.dcc.fes.gerenciador.ProjetoJoinAction;

public class ProjetoJoinTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testSelectProjeto() {
		fail("Not yet implemented");
	}

	@Test
	public void testParticiparProjeto() {
		fail("Not yet implemented");
		ProjetoJoinAction participacao = new ProjetoJoinAction();
		Projeto projeto = new Projeto();
		User user = new User();
		participacao.participarProjeto();
		assertEquals("Result",  participacao.confirm());


	}

	@Test
	public void testSetParticipacaoDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testConfirm() {
		fail("Not yet implemented");
	}

	@Test
	public void testCancel() {
		fail("Not yet implemented");
	}

}
