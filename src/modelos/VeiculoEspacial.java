package modelos;

import interfaces.Comunicavel;
import fleet.enums.StatusVeiculos;

public abstract class VeiculoEspacial implements Comunicavel {
	private String id;
	private String nome;
	private double nivelEnergia;
	private StatusVeiculos status;

	public VeiculoEspacial(String id, String nome) {
		this.id = id;
		this.nome = nome;
		setNivelEnergia(100);
		this.status = StatusVeiculos.EM_DISPOSICAO;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getNivelEnergia() {
		return nivelEnergia;
	}

	public void setNivelEnergia(double nivelEnergia) {
		this.nivelEnergia = nivelEnergia;
	}

	public StatusVeiculos getStatus() {
		return status;
	}

	public void setStatus(StatusVeiculos status) {
		this.status = status;
	}

	public abstract void executarMissao();

	public void recarregarErnegia() {
		this.nivelEnergia = 100;
	}

	@Override
	public void receberMensagem() {
		System.out.println("O veículo [" + nome + "] está com os canais abertos aguardando ordens...");
	}


}
