
import java.util.concurrent.Semaphore;
/**
 * Explorador que realiza sua tarefa de forma rápida.
 * Runnable permite que o objeto seja executado por uma Thread.
 * 
 * Arguardar uma permissao
 * Classe responsavel por adquirir uma das 2 permissoes 
 * Executar uma tarefa como um performer ou dispatcher
 * simula o tempo de execucao e libera a permissao no release.
 */
public class ExploradorRapido extends Explorador implements Runnable {

    /**
     * Exploradores rápidos usam a prioridade máxima definida por Thread.
     */
    public ExploradorRapido(String nome, int nivel,int prioridade,Tarefa tarefa,Semaphore semaforo) 
    {
        // a especialidade sera definida pela propria subclasse
        super(nome, "Rápido",nivel,prioridade,tarefa,semaforo);
    }

    //implementa o comportamento especifico do explorador do perfil rapido
    @Override
    public void executarTarefa()
    {
        exibirStatus();
        System.out.println(" Status: Executando tarefa rapidamente!");
        System.out.println
        (
            getNome()  + " esta explorando rapidamente o local" +
            getTarefa().getLocal() + "."
        );
    }

    /**
     * Controla a entrada do explorador na area limitada pelo semaforo.
     */
    @Override
    public void run() 
    {
        boolean permissao = false;

        try
        {
            System.out.println(getNome() + " esta aguardando a permissao para iniciar.");

            //pegamos lock da tarefa ate que uma das 2 permissoes esteja disponivel
            getSemaforo().acquire();
            permissao = true;

            System.out.println(" Permissao concedida!");

            // apos a permissao retornar true, inicamos a tarefa
            executarTarefa();

            //ultilizaremos o sleep para simular um bloco de fluxos 
            Thread.sleep(1000);
            System.out.println(" Tarefa concluida rapidamente.");
        }
        catch(InterruptedException e)
        {
            System.err.println(" A exploração de " + getNome() + " foi interrompida");
            // apos lancar a execao, restauro o estado de interrupcao da thread.
            Thread.currentThread().interrupt();
        }
        finally
        {
           /*
             * A permissão somente pode ser devolvida se tiver sido
             * adquirida com sucesso.
             */
            if (permissao) 
            {
                getSemaforo().release();

                System.out.println
                (
                        getNome() + " liberou uma permissão."
                );
            }

            System.out.println();
        }
          
        }
        
    }

