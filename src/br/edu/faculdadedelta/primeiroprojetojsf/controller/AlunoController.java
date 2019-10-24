package br.edu.faculdadedelta.primeiroprojetojsf.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.primeiroprojetojsf.dao.AlunoDAO;
import br.edu.faculdadedelta.primeiroprojetojsf.modelo.Aluno;

@ManagedBean
@SessionScoped
public class AlunoController {

	private Aluno aluno = new Aluno();
	private AlunoDAO dao = new AlunoDAO();

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public String salvar() {
		
		try {
			
			if (aluno.getId() == null) {
				// incluir
				dao.incluir(aluno);
				limparCampos();
				
				exibirMensagem("Inclusão realizada com sucesso!");
			} else {
				// alterar
				dao.alterar(aluno);
				limparCampos();
				
				exibirMensagem("Alteração realizada com sucesso!");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação. "
						+ "Tente novamente mais tarde. " + e.getMessage());
		}
		
		return "cadastroAluno.xhtml"; 
	}
	
	public void limparCampos() {
		aluno = new Aluno();
	}
	
	public List<Aluno> getLista() {
		List<Aluno> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listaRetorno;
	}
	
	public String excluir() {
		try {
			dao.excluir(aluno);
			exibirMensagem("Exclusão realizada com sucesso!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação, "
					+ "tente novamente mais tarde. " + e.getMessage());
		}
		return "listaAluno.xhtml";
	}
	
	public String editar() {
		return "cadastroAluno.xhtml";
	}
	
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}






