import java.util.*;

public class HillClimbing {

	public static List<Integer> escalador(List<Integer> solucao_chute,
			int[][] matrix_distancias, int numero_cidades) {

		List<Integer> solucao = new ArrayList<Integer>(solucao_chute);
		List<Integer> vizinho_melhor = new ArrayList<Integer>(solucao);
		List<Integer> vizinho = new ArrayList<Integer>(solucao);

		int valor_solucao = AvaliaSolucao.valorDistancia(matrix_distancias,
				solucao);
		int auxilia_troca = 0, temp = 0, distancia_vizinho = 0, vizinho_distancia_melhor = 0;

		while (true) {

			vizinho_melhor = new ArrayList<Integer>(solucao);
			vizinho_distancia_melhor = AvaliaSolucao.valorDistancia(
					matrix_distancias, solucao);

			for (int i = 1; i < solucao.size() - 2; i++) {

				vizinho = new ArrayList<Integer>(solucao);
				auxilia_troca = 0;

				do {

					auxilia_troca = vizinho.get(i)
							+ (int) (Math.random() * (vizinho.size() - i));

				} while (auxilia_troca >= numero_cidades
						|| auxilia_troca == 0);

				/* PERTUBA SOLUÇÃO */

				temp = vizinho.get(i);
				vizinho.set(i, vizinho.get(auxilia_troca));
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

			if (vizinho_distancia_melhor < valor_solucao) {

				solucao = new ArrayList<Integer>(vizinho_melhor);
				valor_solucao = vizinho_distancia_melhor;

			}

			else {

				return solucao;

			}

		}

	}

}
