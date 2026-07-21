import java.util.concurrent.Semaphore; // implementando semaforo 

/**
 * Define os dados e comportamentos comuns a todos os exploradores.
 */
public abstract class Explorador {

    // Os atributos privados garantem o encapsulamento dos dados.
    private String nome;
    private String especialidade;
    private int nivel;
    private int prioridade;
    private Tarefa tarefa;

    //todos os exploradores receberao o mesmo semaforo:
    private Semaphore semaforo;

    /**
     * Inicializa todos os dados de um explorador.
     */
    public Explorador
    (
        String nome, String especialidade, 
        int nivel, int prioridade,
        Tarefa tarefa,Semaphore semaforo
    ) 
    {
        this.nome = nome;
        this.especialidade = especialidade;
        this.nivel = nivel;
        this.prioridade = prioridade;
        this.tarefa = tarefa;
        this.semaforo = semaforo;
    }

    /**
     * Obriga cada subclasse a definir sua própria forma de explorar.
     *
     * @throws TarefaInvalidaException quando a tarefa não puder ser executada
     */
    public abstract void executarTarefa() throws TarefaInvalidaException;

    /**
     * Exibe os dados e o status inicial do explorador.
     */
    public void exibirStatus() {
        System.out.println("Explorador: " + nome);
        System.out.println("Especialidade: " + especialidade);
        System.out.println("Nivel: " + nivel);
        System.out.println("Prioridade: " + prioridade);
        System.out.println("Tarefa: " + 
                            tarefa.getDescricao() + " em " + 
                            tarefa.getLocal() + " Dificuldade " +
                            tarefa.getDificuldade());
       
    }

    // Os getters permitem a leitura sem expor diretamente os atributos.
    public String getNome() {return nome;}

    public String getEspecialidade() {return especialidade;}

    public int getNivel() {return nivel;}

    public int getPrioridade() {return prioridade;}

    // implementando getters do desafio aventureiro
    public Tarefa getTarefa(){return tarefa;}

    public Semaphore getSemaforo(){return semaforo;}
}   
    

