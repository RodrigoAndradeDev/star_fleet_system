package modelos;

import java.util.concurrent.TimeUnit;

import fleet.enums.StatusVeiculos;

public class NaveDeCombate extends VeiculoEspacial {

	private int quantidadeMisseis;

	public NaveDeCombate(String id, String nome, int quantidadeMisseis) {
		super(id, nome);
		this.quantidadeMisseis = quantidadeMisseis;
	}

	public int getQuantidadeMisseis() {
		return quantidadeMisseis;
	}

	public void setQuantidadeMisseis(int quantidadeMisseis) {
		this.quantidadeMisseis = quantidadeMisseis;
	}

	public void recarregarMissel(int valor) {
		setQuantidadeMisseis(valor + this.quantidadeMisseis);
	}

	@Override
	public void executarMissao() {
		if (getNivelEnergia() < 30) {
			throw new IllegalArgumentException("Não temos energia o suficiente para executar essa missão!");
		} else if (this.quantidadeMisseis < 1) {
			throw new IllegalArgumentException("Não temos munição o suficiente para executar essa missão!");
		}

		setStatus(StatusVeiculos.EM_MISSAO);

		System.out.println(
				"Sistema: Nave de combate [" + getNome() + "] iniciou a defesa do setor. Status: " + getStatus());

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			System.out.println("Operação interrompida!");
			Thread.currentThread().interrupt();
		}
		setNivelEnergia(getNivelEnergia() - 30);
		setStatus(StatusVeiculos.EM_DISPOSICAO);
		this.quantidadeMisseis--;

		System.out.println("Sistema: Nave de combate [" + getNome()
				+ "] teve uma missão cumprida com excelência e obteve grande desempenho ao derrotar ameaças!");
		System.out.println("Quantidade de misseis restante: " + getQuantidadeMisseis());
		System.out.println();
		System.out.println("Sistema: Defesa Concluida. Status: " + getStatus());

	}

}
