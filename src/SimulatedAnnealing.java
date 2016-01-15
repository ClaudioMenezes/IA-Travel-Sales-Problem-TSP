import java.util.*;

public class SimulatedAnnealing {

	public static List<Integer> minimizadorDeEnergia(
			List<Integer> solucao_chute, int[][] matrix_distancias,
			int numero_cidades, double temperatura_inicial,
			double temperatura_final, double alpha) {

		final int numero_vizinhos = numero_cidades * 10;

		List<Integer> solucao = new ArrayList<Integer>(solucao_chute);
		List<Integer> vizinho_melhor = new ArrayList<Integer>(solucao);
		List<Integer> vizinho = new ArrayList<Integer>(solucao);

		double temperatura_atual = temperatura_inicial, boltzmann = 0.0, sorteado = 0.0;
		int valor_solucao = AvaliaSolucao.valorDistancia(matrix_distancias,
				solucao);
		int auxilia_troca = 0, temp = 0, distancia_vizinho = 0, vizinho_distancia_melhor = 0, numero_gerado = 0;

		Random gerador = new Random();

		while (true) {

			if (temperatura_atual < temperatura_final) {

				return solucao;

			}

			temperatura_atual *= alpha;

			vizinho_melhor = new ArrayList<Integer>(solucao);
			vizinho_distancia_melhor = AvaliaSolucao.valorDistancia(
					matrix_distancias, solucao);

			for (int i = 1; i < numero_vizinhos; i++) {

				vizinho = new ArrayList<Integer>(solucao);
				auxilia_troca = 0;

				do {

					/* Sorteia aleatóriamente a posição que será trocada */

					numero_gerado = gerador.nextInt(numero_cidades);
					auxilia_troca = vizinho.get(numero_gerado)
							+ (int) (Math.random() * (vizinho.size() - numero_gerado));

				} while (auxilia_troca >= numero_cidades
						|| auxilia_troca == 0
						|| numero_gerado == 0);

				/* PERTUBA SOLUÇÃO */

				temp = vizinho.get(numero_gerado);
				vizinho.set(numero_gerado, vizinho.get(auxilia_troca));
				vizinho.set(auxilia_troca, temp);

				/* Calcula novo valor de distância para a solução pertubada */

				distancia_vizinho = AvaliaSolucao.valorDistancia(
						matrix_distancias, vizinho);

				/* Verifica se o vizinho atual é o melhor dos vizinhos */

				if (distancia_vizinho < vizinho_distancia_melhor) {

					vizinho_melhor = new ArrayList<Integer>(vizinho);
					vizinho_distancia_melhor = distancia_vizinho;

				}

			}

			/* Verifica se encontramos um vizinho melhor para a solução atual */

			if ((vizinho_distancia_melhor - valor_solucao) < 0) {

				solucao = new ArrayList<Integer>(vizinho_melhor);
				valor_solucao = vizinho_distancia_melhor;

			}

			else {

				/* Critério de aceitação de Boltzmann para o Simulated Annealing */

				boltzmann = Math
						.exp(-(vizinho_distancia_melhor - valor_solucao)
								/ temperatura_atual);
				sorteado = gerador.nextDouble();
				sorteado = (Math.round(sorteado * 1000) / 1000d);

				if (sorteado < boltzmann) {

					solucao = new ArrayList<Integer>(vizinho_melhor);
					valor_solucao = vizinho_distancia_melhor;

				}

			}

		}

	}

}
