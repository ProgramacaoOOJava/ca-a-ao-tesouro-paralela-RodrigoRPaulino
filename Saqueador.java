import java.util.concurrent.Callable;

/**
 * Explorador especializado em recuperar tesouros e artefatos.
 *
 * Callable permite executar a missão em outra thread e retornar pontos.
 */
public class Saqueador extends Explorador implements Callable<Double> {

    /**
     * Inicializa um explorador com a especialidade Saqueador.
     */
    public Saqueador(
            String nome,
            int nivel,
            int energia,
            Missao tarefa
    ) {
        super(nome, "Saqueador", nivel, energia, tarefa);
    }

    /**
     * Executa a missão usando a fórmula específica dos saqueadores.
     */
    @Override
    public Double executarMissao() {
        exibirStatus();

        System.out.println(
                "Status: Recuperando tesouros e artefatos!"
        );

        /*
         * O saqueador recebe mais pontos pela dificuldade da missão,
         * pois sua especialidade envolve missões de maior risco.
         */
        double pontos = (getNivel() * 25.0)
                + (getTarefa().getDificuldade() * 15.0)
                + (getEnergia() * 0.4);

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
                getNome() + " iniciou o saque em "
                        + Thread.currentThread().getName()
        );

        // Simula uma missão mais demorada.
        Thread.sleep(1500);

        return executarMissao();
    }
}