import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Classe principal responsável por cadastrar os exploradores e executar
 * suas tarefas paralelamente.
 * Fluxo da aplicação como um todo :
 * O main cria um semáforo com duas permissões;
 * Os quatro exploradores começam praticamente juntos;
 * Somente dois conseguem passar pelo acquire();
 * Os demais ficam aguardando;
 * Quando um explorador chama release(), outro pode começar;
 * O main usa join() para aguardar todos;
 * As threads terminam no estado TERMINATED.
 */
public class CacaAoTesouroParalela 
{

    public static void main(String[] args) 
    {
        System.out.println("=== CAÇA AO TESOURO PARALELA ===");
        System.out.println(" Nivel Aventureiro \n");

        /**
         * O mesmo semafor  sera compartilhado por todos os exploradores.
         * As 2 permissoes limitam a execução a dois exploradores por vez.
        */
       Semaphore semaforo = new Semaphore(2);

       //Criação das quatro tarefas imutaveis.
       Tarefa tarefaCaverna = new Tarefa("Mapear caverna","Caverna do Eco",7);

       Tarefa tarefaRuinas = new Tarefa("Explorar ruinas","Templo Antigo",6);

       Tarefa tarefaFloresta = new Tarefa("Procurar pistas","Floresta Sombria",5);

       Tarefa tarefaMontanha = new Tarefa("Recuperar Artefato","Montanha Perdida",9);

       /*
         * Todos os exploradores recebem exatamente a mesma instância
         * do semáforo.
         */
        ExploradorRapido alice = new ExploradorRapido
        ("Alice", 5, Thread.MAX_PRIORITY, tarefaCaverna, semaforo);

         ExploradorRapido carlos = new ExploradorRapido
        ("Carlos", 4, 8, tarefaRuinas, semaforo);

        ExploradorCuidadoso bob = new ExploradorCuidadoso
        ("Bob", 4, 5, tarefaFloresta, semaforo);

        ExploradorCuidadoso diana = new ExploradorCuidadoso
        ("Diana", 6, Thread.MAX_PRIORITY, tarefaMontanha, semaforo);

        // Cada explorador Runnable é encapsulado em uma Thread.
        Thread threadAlice = new Thread(alice, "Thread-Alice");
        Thread threadCarlos = new Thread(carlos, "Thread-Carlos");
        Thread threadBob = new Thread(bob, "Thread-Bob");
        Thread threadDiana = new Thread(diana, "Thread-Diana");

        /*
         * A prioridade da Thread deve coincidir com a prioridade
         * armazenada no explorador.
         */
        threadAlice.setPriority(alice.getPrioridade());
        threadCarlos.setPriority(carlos.getPrioridade());
        threadBob.setPriority(bob.getPrioridade());
        threadDiana.setPriority(diana.getPrioridade());

        //Diana vai ser uma tarefa secundaria
        threadDiana.setDaemon(true);

        //Lista usada para gerenciar todas as threads.
        ArrayList<Thread> threads = new ArrayList<>();

        threads.add(threadAlice);
        threads.add(threadCarlos);
        threads.add(threadBob);
        threads.add(threadDiana);

        System.out.println("=== THREADS ANTES DO START ===");

        //Inicia a execução paralela das tarefas
        for(Thread thread : threads)
        {
            thread.start();
        }
        /*
         * Aguarda a conclusão das threads.
         * Mesmo sendo daemon, Diana também será aguardada para que sua
         * execução possa ser analisada completamente neste exercício.
         */
        for (Thread thread : threads) 
        {
            try 
            {
                thread.join();
            } 
            catch (InterruptedException e) 
            {
                System.err.println("A thread principal foi interrompida.");
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("=== ESTADO FINAL DAS THREADS ===");

        for(Thread thread : threads)
        {
            exibirInformacoes(thread);
        }

        System.out.println("\n=== EXPLORAÇÃO FINALIZADA ===");
        System.out.println("Todos os exploradores concluíram suas tarefas.");
    }
    /**
     * Exibe as principais informações de uma thread.
     */
    private static void exibirInformacoes(Thread thread) 
    {
        System.out.println(
                thread.getName()
                        + " | Prioridade: "
                        + thread.getPriority()
                        + " | Daemon: "
                        + thread.isDaemon()
                        + " | Estado: "
                        + thread.getState()
        );
    }
}
