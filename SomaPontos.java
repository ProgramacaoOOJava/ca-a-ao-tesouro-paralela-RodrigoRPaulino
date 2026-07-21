import java.util.concurrent.RecursiveTask;

/**
 * Soma paralelamente os pontos obtidos pelos exploradores.
 */
public class SomaPontos extends RecursiveTask<Double> {

    // Limite máximo de elementos somados diretamente.
    private static final int LIMITE_DIRETO = 2;

    private final double[] pontos;
    private final int inicio;
    private final int fim;

    /**
     * Cria a tarefa principal usando todo o array.
     */
    public SomaPontos(double[] pontos) {
        this(pontos, 0, pontos.length);
    }

    /**
     * Cria uma subtarefa responsável por uma parte do array.
     *
     * O índice inicial é incluído e o índice final é excluído.
     */
    private SomaPontos(
            double[] pontos,
            int inicio,
            int fim
    ) {
        this.pontos = pontos;
        this.inicio = inicio;
        this.fim = fim;
    }

    /**
     * Divide o trabalho ou realiza a soma diretamente.
     */
    @Override
    protected Double compute() {
        int quantidade = fim - inicio;

        // Intervalos com até dois elementos são somados diretamente.
        if (quantidade <= LIMITE_DIRETO) {
            return somarDiretamente();
        }

        // Divide o intervalo aproximadamente ao meio.
        int meio = inicio + quantidade / 2;

        SomaPontos tarefaEsquerda = new SomaPontos(
                pontos,
                inicio,
                meio
        );

        SomaPontos tarefaDireita = new SomaPontos(
                pontos,
                meio,
                fim
        );

        /*
         * A parte esquerda é enviada para execução paralela.
         * A parte direita é calculada pela thread atual.
         */
        tarefaEsquerda.fork();
        double resultadoDireita = tarefaDireita.compute();

        // join() aguarda e recupera o resultado da tarefa esquerda.
        double resultadoEsquerda = tarefaEsquerda.join();

        return resultadoEsquerda + resultadoDireita;
    }

    /**
     * Soma diretamente os elementos do intervalo atual.
     */
    private double somarDiretamente() {
        double soma = 0.0;

        for (int indice = inicio; indice < fim; indice++) {
            soma += pontos[indice];
        }

        return soma;
    }
}