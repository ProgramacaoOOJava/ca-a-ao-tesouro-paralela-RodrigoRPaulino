import java.util.concurrent.Semaphore;
/**
 * Explorador que realiza sua tarefa com atenção aos detalhes.
 *
 */
public class ExploradorCuidadoso extends Explorador implements Runnable {

    /**
     * inicia os dados.
     */
    public ExploradorCuidadoso
    (
        String nome, int nivel,
        int prioridade, Tarefa tarefa,
        Semaphore semaforo
    ) 
    {
        super(nome, "Cuidadoso", nivel,prioridade,tarefa,semaforo );
    }

    @Override
    public void executarTarefa()
    {
        exibirStatus();
        System.out.println
        (getNome() + " esta analisando cuidadosamente o local" + getTarefa().getLocal() + "." );
    }

    /**
     * Controla a execução da tarefa usando o semáforo compartilhado.
     */
    @Override
    public void run() 
    {
        boolean permissao = false;
        try 
        {
            System.out.println(getNome() + " esta aguardando permissao");

            getSemaforo().acquire();
            permissao = true;

            System.out.println(getNome()+ " adquiriu uma permissão");
            executarTarefa();
            Thread.sleep(2000);

            System.out.println(getNome() + " concluiu a tarefa com cuidado!");
        } 
        catch (InterruptedException e) 
        {
            System.err.println(" A exploração de : " + getNome() + " foi interrompida");
        }
        finally
        {
            if(permissao)
            {
                getSemaforo().release();

                System.out.println(getNome() + " liberou uma permissão ");
            }
        }
    }
}
