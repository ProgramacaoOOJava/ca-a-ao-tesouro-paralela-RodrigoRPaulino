/**
 * Define os dados e comportamentos comuns a todos os exploradores.
 */
public abstract class Explorador {

    // Os atributos privados garantem o encapsulamento dos dados.
    private String nome;
    private String tipo;
    private int prioridade;
    private String tarefa;

    /**
     * Inicializa todos os dados de um explorador.
     */
    public Explorador(String nome, String tipo, int prioridade, String tarefa) {
        this.nome = nome;
        this.tipo = tipo;
        this.prioridade = prioridade;
        this.tarefa = tarefa;
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
        System.out.println("Tipo: " + tipo);
        System.out.println("Prioridade: " + prioridade);
        System.out.println("Tarefa: " + tarefa);
        System.out.println("Status: Iniciando exploração...");
    }

    // Os getters permitem a leitura sem expor diretamente os atributos.
    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public String getTarefa() {
        return tarefa;
    }
}
