import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * Classe principal do nível Mestre da Caça ao Tesouro Paralela.
 */
public class CacaAoTesouroParalela {

    public static void main(String[] args) {
        System.out.println("=== CAÇA AO TESOURO PARALELA ===");
        System.out.println("Nível Mestre\n");

        // Criação das missões imutáveis.
        Missao missaoCaverna = new Missao(
                "Mapear cavernas",
                "Caverna do Eco",
                7
        );

        Missao missaoArtefato = new Missao(
                "Recuperar artefatos",
                "Templo Antigo",
                8
        );

        Missao missaoTrilhas = new Missao(
                "Encontrar trilhas secretas",
                "Floresta Sombria",
                5
        );

        Missao missaoTesouro = new Missao(
                "Resgatar o tesouro perdido",
                "Ruínas Submersas",
                9
        );

        // O polimorfismo permite armazenar subclasses como Explorador.
        ArrayList<Explorador> exploradores = new ArrayList<>();

        exploradores.add(
                new Rastreador(
                        "Lina",
                        5,
                        80,
                        missaoCaverna
                )
        );

        exploradores.add(
                new Saqueador(
                        "Drogan",
                        6,
                        90,
                        missaoArtefato
                )
        );

        exploradores.add(
                new Rastreador(
                        "Aria",
                        4,
                        75,
                        missaoTrilhas
                )
        );

        exploradores.add(
                new Saqueador(
                        "Kael",
                        7,
                        85,
                        missaoTesouro
                )
        );

        /*
         * O pool possui duas threads, portanto somente duas missões
         * poderão ser processadas simultaneamente.
         */
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Cada Future armazenará os pontos retornados por um explorador.
        ArrayList<Future<Double>> resultados = new ArrayList<>();

        System.out.println("=== ENVIANDO MISSÕES ===");

        for (Explorador explorador : exploradores) {
            Future<Double> resultado = executor.submit(explorador);
            resultados.add(resultado);
        }

        // O array será posteriormente processado pelo Fork/Join.
        double[] pontos = new double[exploradores.size()];

        try {
            System.out.println("\n=== COLETANDO RESULTADOS ===");

            for (int indice = 0; indice < resultados.size(); indice++) {
                /*
                 * get() aguarda a conclusão da missão correspondente
                 * e recupera o Double retornado pelo Callable.
                 */
                pontos[indice] = resultados.get(indice).get();

                Explorador explorador = exploradores.get(indice);

                System.out.println(
                        "Resultado de "
                                + explorador.getNome()
                                + ": "
                                + pontos[indice]
                                + " pontos."
                );
            }
        } catch (InterruptedException e) {
            System.err.println(
                    "A thread principal foi interrompida."
            );

            Thread.currentThread().interrupt();
            return;
        } catch (ExecutionException e) {
            System.err.println(
                    "Falha durante uma missão: "
                            + e.getCause().getMessage()
            );

            return;
        } finally {
            // Impede o recebimento de novas tarefas.
            executor.shutdown();
        }

        System.out.println("\n=== SOMANDO PONTOS COM FORK/JOIN ===");

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        try {
            SomaPontos tarefaSoma = new SomaPontos(pontos);

            // invoke() inicia a tarefa e aguarda o resultado.
            double total = forkJoinPool.invoke(tarefaSoma);

            System.out.println("Soma total dos pontos: " + total);
        } finally {
            forkJoinPool.shutdown();
        }

        System.out.println("\n=== MISSÕES FINALIZADAS ===");
    }
}