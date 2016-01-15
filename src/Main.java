import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		Scanner entrada = new Scanner(System.in);
		int distancia_maxima = 1;
		System.out.print("Número de cidades: ");
		int numero_cidades = entrada.nextInt();
		System.out.print("Distância máxima entre cidades: ");
		distancia_maxima = entrada.nextInt();
		entrada.close();

		System.out.println();

		int matrix[][] = new int[numero_cidades][numero_cidades];

		/* Gera distancias */
		AvaliaSolucao.geraDistancias(distancia_maxima, numero_cidades);
		List<Integer> solucao_chute = AvaliaSolucao
				.geraListaSolucao(numero_cidades);
		System.out.println(solucao_chute + "\n");

		matrix = AvaliaSolucao.geraDistancias(distancia_maxima, numero_cidades);

		AvaliaSolucao.imprimeMatrix(matrix);
		System.out.println("\nDistância original: "
				+ AvaliaSolucao.valorDistancia(matrix, solucao_chute) + "km\n");
		List<Integer> melhor_solucao_hill = new ArrayList<Integer>();
		List<Integer> melhor_solucao_annealing = new ArrayList<Integer>();
		List<List<Integer>> lista_solucoes_hill = new ArrayList<List<Integer>>();
		List<List<Integer>> lista_solucoes_annealing = new ArrayList<List<Integer>>();
		int melhor_distancia_hill[] = new int[1000], melhor_distancia_annealing[] = new int[1000], menor_distancia_hill = 0, menor_distancia_annealing = 0;
		
		/* CRIAÇÃO DO ARQUIVO DE SAÍDA COM AS INFORMAÇÕES */
		
		String saida = "saida.txt";
		FileWriter cria_saida = new FileWriter(saida);
		PrintWriter escreve_saida = new PrintWriter(cria_saida);
		
		escreve_saida.printf("Solução Original: " + solucao_chute + "\n");
		escreve_saida.printf("Distância Original: " + AvaliaSolucao.valorDistancia(matrix, solucao_chute) + "km\n\n");
		
		double temperatura_inicial = 100;
		double temperatura_final = 20;
		double alpha = 0.95;

		for (int i = 0; i < 1000; i++) {
			
			/* CHAMADA HILL CLIMBING 1000x */
			
			melhor_solucao_hill = new ArrayList<Integer>(HillClimbing.escalador(
					solucao_chute, matrix, numero_cidades));
			melhor_distancia_hill[i] = AvaliaSolucao.valorDistancia(matrix,
					melhor_solucao_hill);

			lista_solucoes_hill.add(i, melhor_solucao_hill);
			
			/* CHAMADA SIMULATED ANNEALING 1000x */

			melhor_solucao_annealing = new ArrayList<Integer>(
					SimulatedAnnealing.minimizadorDeEnergia(solucao_chute,
							matrix, numero_cidades, temperatura_inicial,
							temperatura_final, alpha));
			melhor_distancia_annealing[i] = AvaliaSolucao.valorDistancia(matrix,
					melhor_solucao_annealing);

			lista_solucoes_annealing.add(i, melhor_solucao_annealing);

		}

		menor_distancia_hill = melhor_distancia_hill[0];
		menor_distancia_annealing = melhor_distancia_annealing[0];
		
		escreve_saida.printf("Hill Climbing\tSimulated Annealing\n\n");

		for (int i = 0; i < 1000; i++) {
			
			escreve_saida.printf(melhor_distancia_hill[i] + "\t\t\t\t" + melhor_distancia_annealing[i] + "\n");
			
			if (melhor_distancia_hill[i] < menor_distancia_hill) {

				menor_distancia_hill = melhor_distancia_hill[i];
				melhor_solucao_hill = new ArrayList<Integer>(lista_solucoes_hill.get(i));

			}

			if (melhor_distancia_annealing[i] < menor_distancia_annealing) {

				menor_distancia_annealing = melhor_distancia_annealing[i];
				melhor_solucao_annealing = new ArrayList<Integer>(lista_solucoes_annealing.get(i));

			}

		}
		
		escreve_saida.print("\nMelhor solução Hill Climbing: " + melhor_solucao_hill + "\n");
		escreve_saida.printf("Menor distância Hill Climbing: " + menor_distancia_hill);
		
		escreve_saida.print("\n\nMelhor solução Simulated Annealing: " + melhor_solucao_annealing + "\n");
		escreve_saida.printf("Menor distância Simulated Annealing: " + menor_distancia_annealing);
		
		escreve_saida.close();
		cria_saida.close();

		System.out.println("==========HillClimbing==========\n\nMelhor solucao: "
				+ melhor_solucao_hill);
		System.out.println("Menor distância: " + menor_distancia_hill + "km");

		System.out
				.println("\n=======SimulatedAnnealing=======\n\nMelhor solucao: "
						+ melhor_solucao_annealing);
		System.out.println("Menor distância: " + menor_distancia_annealing + "km");
	}

}
