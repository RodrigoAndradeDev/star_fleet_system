package modelos;

import java.util.concurrent.TimeUnit;

import fleet.enums.StatusVeiculos;

public class NaveExploradora extends VeiculoEspacial {

	private String planetaDestino;

	public NaveExploradora(String id, String nome, String planetaDestino) {
		super(id, nome);
		this.planetaDestino = planetaDestino;
	}

	public String getPlanetaDestino() {
		return planetaDestino;
	}

	public void setPlanetaDestino(String planetaDestino) {
		this.planetaDestino = planetaDestino;
	}

	@Override
	public void executarMissao() {

		if (getNivelEnergia() < 20) {
			throw new IllegalArgumentException("Não temos energia o suficiente para executar essa missao!");
		}

		setStatus(StatusVeiculos.EM_MISSAO);

		System.out.println("Sistema: Nave Exploradora [" + getNome() + "] iniciou a busca de amostra do planeta "
				+ getPlanetaDestino() + ". Status: " + getStatus());

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			System.out.println("Operação interrompida!");
			Thread.currentThread().interrupt();
		}
		setNivelEnergia(getNivelEnergia() - 20);

		setStatus(StatusVeiculos.EM_DISPOSICAO);
		System.out.println(
				"Sistema: Nave Exploradora [" + getNome() + "] teve uma missão cumprida com excelência e obteve "
						+ "diversas amostras do planeta " + getPlanetaDestino());

		System.out.println("Sistema: Captura das amostras Concluido. Status: " + getStatus());

	}

}
