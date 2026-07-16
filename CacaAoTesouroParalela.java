import java.util.ArrayList;

/**
 * Classe principal responsável por cadastrar os exploradores e executar
 * suas tarefas paralelamente.
 */
public class CacaAoTesouroParalela {

    public static void main(String[] args) {
        System.out.println("=== CAÇA AO TESOURO PARALELA ===");

        // A lista permite gerenciar todas as threads em um único lugar.
        ArrayList<Thread> threads = new ArrayList<>();

        // São criados pelo menos dois exploradores de cada tipo.
        ExploradorRapido alice = new ExploradorRapido("Alice", "Vasculhar a caverna");
        ExploradorRapido davi = new ExploradorRapido("Davi", "Investigar as ruínas");
        ExploradorCuidadoso bob = new ExploradorCuidadoso("Bob", "Mapear a floresta");

        // A tarefa vazia demonstra o tratamento da exceção personalizada.
        ExploradorCuidadoso clara = new ExploradorCuidadoso("Clara", "   ");

        // Cada objeto Runnable é encapsulado em uma Thread.
        Thread threadAlice = new Thread(alice, "Explorador-Alice");
        Thread threadDavi = new Thread(davi, "Explorador-Davi");
        Thread threadBob = new Thread(bob, "Explorador-Bob");
        Thread threadClara = new Thread(clara, "Explorador-Clara");

        // A prioridade deve ser configurada antes de iniciar a thread.
        threadAlice.setPriority(Thread.MAX_PRIORITY);
        threadDavi.setPriority(Thread.MAX_PRIORITY);
        threadBob.setPriority(Thread.MIN_PRIORITY);
        threadClara.setPriority(Thread.MIN_PRIORITY);

        // A thread daemon representa uma tarefa secundária da aplicação.
        // Clara permanece não daemon para garantir a exibição do erro da tarefa.
        threadBob.setDaemon(true);

        threads.add(threadAlice);
        threads.add(threadDavi);
        threads.add(threadBob);
        threads.add(threadClara);

        System.out.println("\n=== THREADS ANTES DA EXECUÇÃO ===");
        for (Thread thread : threads) {
            exibirInformacoesDaThread(thread);
        }

        System.out.println("\n=== INICIANDO EXPLORAÇÃO ===");
        for (Thread thread : threads) {
            // start() cria a execução paralela; run() direto seria sequencial.
            thread.start();
        }

        // Aguarda somente as threads principais (não daemon) terminarem.
        for (Thread thread : threads) {
            if (!thread.isDaemon()) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    // Restaura o sinal de interrupção para não ocultar o evento.
                    Thread.currentThread().interrupt();
                    System.err.println("A thread principal foi interrompida.");
                    return;
                }
            }
        }

        System.out.println("\n=== ESTADO FINAL DAS THREADS ===");
        for (Thread thread : threads) {
            exibirInformacoesDaThread(thread);
        }

        System.out.println("\n=== CAÇA AO TESOURO FINALIZADA ===");
        System.out.println("Todos os exploradores principais concluíram suas missões.");
    }

    /**
     * Mostra prioridade, tipo e estado do ciclo de vida da thread.
     */
    private static void exibirInformacoesDaThread(Thread thread) {
        System.out.printf(
                "%s | prioridade: %d | daemon: %s | estado: %s%n",
                thread.getName(),
                thread.getPriority(),
                thread.isDaemon(),
                thread.getState()
        );
    }
}
