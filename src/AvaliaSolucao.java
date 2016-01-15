import java.util.*;

public class AvaliaSolucao {
	
	public static int[][] geraDistancias(int distancia_maxima, int numero_cidades) {
		
		Random gerador = new Random();
		
		int matrix[][] = new int[numero_cidades][numero_cidades];
		
		for (int linha = 0; linha < numero_cidades; linha++) {
			
			for (int coluna = 0; coluna < numero_cidades; coluna++) {
				
				matrix[linha][coluna] = (gerador.nextInt(distancia_maxima) + 1);
				
			}
				
		}
		
		return matrix;
				
	}
	
	public static void imprimeMatrix(int[][] matrix) {
		
		for (int linha = 0; linha < matrix.length; linha++) {
			
			for (int coluna = 0; coluna < matrix[0].length; coluna++) {
				
				System.out.print(matrix[linha][coluna] + "\t");
				
			}
			
			System.out.println();
				
		}
				
	}
	
	public static List<Integer> geraListaSolucao(int numero_cidades) {
		
		List<Integer> lista = new ArrayList<Integer>();
		
		for (int i = 0; i < numero_cidades; i++) {
			
			lista.add(i);
			
		}
		
		Collections.shuffle(lista);
				
		lista.add(lista.get(0));
		
		return lista;	
		
	}
	
	/* Função que mede o retorno de uma solucao em km */
	
	public static int valorDistancia(int[][] matrix, List<Integer> solucao_chute) {
		
		int valor = 0;
		
		for (int i = 0; i < solucao_chute.size() - 1; i++) {
			
			valor += matrix[solucao_chute.get(i)][solucao_chute.get(i + 1)];
			
		}
		
		return valor;
	}

}
