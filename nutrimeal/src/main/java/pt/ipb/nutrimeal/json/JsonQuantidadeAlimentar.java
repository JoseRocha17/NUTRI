package pt.ipb.nutrimeal.json;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JsonQuantidadeAlimentar {
	
	long id, refeicao, alimento, quantidade;
	String atributo;

	public JsonQuantidadeAlimentar() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(long refeicao) {
		this.refeicao = refeicao;
	}

	public long getAlimento() {
		return alimento;
	}

	public void setAlimento(long alimento) {
		this.alimento = alimento;
	}

	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	
	




	



	

	
	
}