import java.util.concurrent.Callable;

/**
 * Explorador especializado em rastrear caminhos e encontrar pistas.
 *
 * Callable permite executar a missão em outra thread e retornar pontos.
 */
public class Rastreador extends Explorador implements Callable<Double> {

    /**
     * Inicializa um explorador com a especialidade Rastreador.
     */
    public Rastreador(
            String nome,
            int nivel,
            int energia,
            Missao tarefa
    ) {
        super(nome, "Rastreador", nivel, energia, tarefa);
    }

    /**
     * Executa a missão usando a fórmula específica dos rastreadores.
     */
    @Override
    public Double executarMissao() {
        exibirStatus();

        System.out.println(
                "Status: Rastreando caminhos e procurando pistas!"
        );

        /*
         * A pontuação considera o nível, a dificuldade da missão
         * e parte da energia disponível.
         */
        double pontos = (getNivel() * 20.0)
                + (getTarefa().getDificuldade() * 10.0)
                + (getEnergia() * 0.5);

        System.out.println("Pontos obtidos: " + pontos);
        System.out.println();

        return pontos;
    }

    /**
     * Método executado pelo ExecutorService.
     */
    @Override
    public Double call() throws Exception {
        System.out.println(
                getNome() + " iniciou o rastreamento em "
                        + Thread.currentThread().getName()
        );

        // Simula o tempo necessário para executar a missão.
        Thread.sleep(1000);

        return executarMissao();
    }
}