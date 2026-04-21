package application;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import modelos.NaveDeCombate;
import modelos.NaveExploradora;
import modelos.SondaDeReconhecimento;
import modelos.VeiculoEspacial;

public class Program {

	public static void main(String[] args) {
		List<VeiculoEspacial> frota = new LinkedList<>();
		criarNaves(frota);

	}

	public static void criarNaves(List<VeiculoEspacial> frota) {
		Scanner sc = new Scanner(System.in);

		System.out.println(
				"Tudo bem, CAPITÃO?! para iniciarmos com nossas frotas precisamos que 'crie' suas naves essenciais!");

		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------");
		System.out.println("Primeiro criaremos uma sonda!");
		System.out.println();
		System.out.println("Qual id da sonda a ser registrada?");
		String idSonda = sc.next();

		System.out.println("Qual o nome da sonda?");
		sc.nextLine();
		String nomeSonda = sc.nextLine();

		System.out.println("Qual a distancia que você deseja que ela alcance em suas missões?");
		double distanciaSonda = sc.nextDouble();
		SondaDeReconhecimento sdr = new SondaDeReconhecimento(idSonda, nomeSonda, distanciaSonda);
		frota.add(sdr);
		VeiculoEspacial naveEscolhida = frota.get(0);
		naveEscolhida.receberMensagem();
		System.out.println();

		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------");
		System.out.println("Agora iremos com a nossa nave de exploração");
		System.out.println();
		System.out.println("Qual id da nave de exploração a ser registrada?");
		String idNE = sc.next();

		System.out.println("Qual o nome da desta nave?");
		sc.nextLine();
		String nomeNE = sc.nextLine();

		System.out.println("Qual o planeta que você deseja que ela colete amostras em suas missões?");
		String Distino = sc.nextLine();
		NaveExploradora NE = new NaveExploradora(idNE, nomeNE, Distino);
		frota.add(NE);
		
		VeiculoEspacial naveEscolhida2 = frota.get(1);
		naveEscolhida2.receberMensagem();
		System.out.println();
		
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------");

		System.out.println("E por ultimo a nossa nave de combate");
		System.out.println();
		System.out.println("Qual id da nave de combate a ser registrada?");
		String idNC = sc.next();

		System.out.println("Qual o nome da desta nave?");
		sc.nextLine();
		String nomeNC = sc.nextLine();

		System.out.println("Qual a quantidade de misseis inical voce deseja para que comece as suas missões?");
		int quantidadeDeMisseis = sc.nextInt();
		NaveDeCombate NC = new NaveDeCombate(idNC, nomeNC, quantidadeDeMisseis);
		frota.add(NC);
		VeiculoEspacial naveEscolhida3 = frota.get(2);
		naveEscolhida3.receberMensagem();
		System.out.println();

		execucao(frota, sc);

	}

	public static void execucao(List<VeiculoEspacial> frota, Scanner sc) {

		int opcaoMenu = -1;

		while (opcaoMenu != 0) {
			System.out.println("\n=== SISTEMA DE GESTÃO DA FROTA ===");
			System.out.println("[1] Relatório da Frota");
			System.out.println("[2] Despachar Frota para Missão");
			System.out.println("[3] Recarregar/modificar frota");
			System.out.println("[0] Desligar Sistema");
			System.out.println("Qual seria sua ordem, Capitão? ");

			opcaoMenu = sc.nextInt();

			if (opcaoMenu == 1) {
				primeiraOpcao(frota, sc);
			} else if (opcaoMenu == 2) {
				segundaOpcao(frota, sc);
			} else if (opcaoMenu == 3) {
				terceiraOpcao(frota, sc);
			}
		}

		System.out.println("Encerrando nossa Trajetória por aqui!");
	}

	public static void primeiraOpcao(List<VeiculoEspacial> frota, Scanner sc) {
		System.out.println("\nQual das naves você deseja receber o relatorio!");
		System.out.println("[1] Sonda de reconhecimento");
		System.out.println("[2] Nave de exploração");
		System.out.println("[3] Nave de combate");
		int opcao = sc.nextInt();

		if (opcao >= 1 && opcao <= 3) {
			VeiculoEspacial naveEscolhida = frota.get(opcao - 1);

			System.out.println("\n--- RELATÓRIO ---");
			System.out.println("ID: " + naveEscolhida.getId());
			System.out.println("Nome: " + naveEscolhida.getNome());
			System.out.println("Energia: " + naveEscolhida.getNivelEnergia() + "%");
			System.out.println("Status: " + naveEscolhida.getStatus());
			System.out.println();

		} else {
			System.out.println("Opção invalida!");
		}

	}

	public static void segundaOpcao(List<VeiculoEspacial> frota, Scanner sc) {
		System.out.println("\nQual das naves você deseja despachar para a missão?!");
		System.out.println("[1] Sonda de reconhecimento");
		System.out.println("[2] Nave de exploração");
		System.out.println("[3] Nave de combate");
		int opcao = sc.nextInt();

		if (opcao >= 1 && opcao <= 3) {
			VeiculoEspacial naveEscolhida = frota.get(opcao - 1);

			System.out.println("\n--- EXECUTANDO MISSÃO ---");

			try {
				naveEscolhida.executarMissao();
			} catch (IllegalArgumentException e) {
				if (opcao == 3) {
					NaveDeCombate nDC = (NaveDeCombate) naveEscolhida;
					if (nDC.getQuantidadeMisseis() <= 0) {
						System.out.println("Recarregue seus misseis para continuar!");
					} else {
						System.out.println("Recarregue a ernegia para continuar!");
					}
				}else {
					System.out.println("Recarregue a ernegia para continuar!");
				}
				
			}

			System.out.println();

		} else {
			System.out.println("Opção invalida!");
		}

	}

	public static void terceiraOpcao(List<VeiculoEspacial> frota, Scanner sc) {
		System.out.println("\nDeseja carregar ernegia ou modificar algo das naves?!");
		System.out.println("[1] Ernegia");
		System.out.println("[2] Modificar");
		int opcaoEO = sc.nextInt();

		if (opcaoEO == 1) {
			System.out.println("\nQual das naves você deseja recarregar sua energia?!");
			System.out.println("[1] Sonda de reconhecimento");
			System.out.println("[2] Nave de exploração");
			System.out.println("[3] Nave de combate");
			int opcao = sc.nextInt();
			VeiculoEspacial naveEscolhida = frota.get(opcao - 1);

			naveEscolhida.recarregarErnegia();
		} else if (opcaoEO == 2) {
			System.out.println("\nO que deseja modificar?");
			System.out.println("[1] Distancia da sonda de reconhecimento");
			System.out.println("[2] Planeta de destino da nave de exploração");
			System.out.println("[3] Quantidade de missel da nave de combate");
			int opcao = sc.nextInt();
			VeiculoEspacial naveEscolhida = frota.get(opcao - 1);

			if (opcao == 1) {
				System.out.println("Qual a distancia que voce deseja que a sonda alcance?");
				SondaDeReconhecimento sonda = (SondaDeReconhecimento) naveEscolhida;
				sonda.setDistanciaAlcancada(sc.nextDouble());
			} else if (opcao == 2) {
				System.out.println("Qual o planeta que voce deseja que a nave vá em sua proxima exploração?");
				NaveExploradora exploradora = (NaveExploradora) naveEscolhida;
				sc.nextLine();
				exploradora.setPlanetaDestino(sc.nextLine());

			} else if (opcao == 3) {
				System.out.println("Quantos misseis deseja adicionar ao seu arsenal?");
				NaveDeCombate caca = (NaveDeCombate) naveEscolhida;
				caca.recarregarMissel(sc.nextInt());
			}

		}

	}
}