package modelos;

import java.util.concurrent.TimeUnit;
import fleet.enums.StatusVeiculos;

public class SondaDeReconhecimento extends VeiculoEspacial {
	private double distanciaAlcacada;

	public SondaDeReconhecimento(String id, String nome, double distanciaAlcacada) {
		super(id, nome);
		this.distanciaAlcacada = distanciaAlcacada;
	}

	public double getDistanciaAlcancada() {
		return distanciaAlcacada;
	}

	public void setDistanciaAlcancada(double distanciaAlcacada) {
		this.distanciaAlcacada = distanciaAlcacada;
	}

	@Override
	public void executarMissao() {

		if (getNivelEnergia() < 10) {
			throw new IllegalArgumentException("Não temos energia o suficiente para executar essa missao!");
		}

		setStatus(StatusVeiculos.EM_MISSAO);

		System.out.println("Sistema: Sonda [" + getNome() + "] iniciou a captura de imagens. Status: " + getStatus());

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			System.out.println("Operação interrompida!");
			Thread.currentThread().interrupt();
		}
		setNivelEnergia(getNivelEnergia() - 10);
		setStatus(StatusVeiculos.EM_DISPOSICAO);
		System.out.println("Sistema: Sonda [" + getNome() + "] teve uma missão cumprida com excelência e obteve "
				+ "diversas fotos do espaço profundo, a mais de " + distanciaAlcacada + "KM de distancia da Terra.");
		System.out.println("Sistema: Captura concluída. Status: " + getStatus());

	}
}
