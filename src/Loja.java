import java.util.Scanner;

public class Loja {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Produtos[] catalogo = { new Televisao(), new Radio(), new Videogame(), new Tablet(), new Celular() };

		Produtos[] carrinho = new Produtos[15];
		int[] qntd = new int[50];
		int total = 0;

		System.out.println("Bem Vindo(a)\n");

		while (true) {
			System.out.println("Produtos disponíveis");
			for (int i = 0; i < catalogo.length; i++)
				System.out.printf("[%d] %s - R$ %.2f%n", i + 1, catalogo[i].nome, catalogo[i].preco);
			System.out.println("[0] Finalizar compra");
			System.out.print("\nEscolha um produto: ");

			if (!sc.hasNextInt()) {
				System.out.println("Entrada inválida! Digite um número.");
				sc.next();
				continue;
			}

			int opcao = sc.nextInt();

			if (opcao == 0)
				break;

			if (opcao < 1 || opcao > catalogo.length) {
				System.out.println("Produto não existe! Tente novamente.");
				continue;
			}

			System.out.print("Quantidade: ");

			if (!sc.hasNextInt()) {
				System.out.println("Quantidade inválida!");
				sc.next();
				continue;
			}

			int quantidade = sc.nextInt();

			if (quantidade <= 0) {
				System.out.println("Quantidade deve ser maior que zero!");
				continue;
			}

			Produtos produtoEscolhido = catalogo[opcao - 1];
			boolean jaNoCarrinho = false;

			for (int i = 0; i < total; i++) {
				if (carrinho[i].nome.equals(produtoEscolhido.nome)) {
					qntd[i] += quantidade;
					jaNoCarrinho = true;
					break;
				}
			}

			if (!jaNoCarrinho) {
				carrinho[total] = produtoEscolhido;
				qntd[total] = quantidade;
				total++;
			}

			System.out.printf("%dx %s adicionado(s) ao carrinho!%n", quantidade, produtoEscolhido.nome);
		}

		if (total == 0)
			System.out.println("Nenhum produto foi comprado.");
		else {
			double totalCarrinho = 0;
			for (int i = 0; i < total; i++) {
				double subtotal = carrinho[i].preco * qntd[i];
				totalCarrinho += subtotal;
			}

			System.out.printf("Total: R$ %.2f%n", totalCarrinho);
		}
		
		sc.close();
	}
}
