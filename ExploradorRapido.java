/**
 * Explorador que realiza sua tarefa de forma rápida.
 * Runnable permite que o objeto seja executado por uma Thread.
 */
public class ExploradorRapido extends Explorador implements Runnable {

    /**
     * Exploradores rápidos usam a prioridade máxima definida por Thread.
     */
    public ExploradorRapido(String nome, String tarefa) {
        super(nome, "Rápido", Thread.MAX_PRIORITY, tarefa);
    }

    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        // trim() também identifica textos compostos apenas por espaços.
        if (getTarefa() == null || getTarefa().trim().isEmpty()) {
            throw new TarefaInvalidaException("Tarefa inválida para " + getNome());
        }

        exibirStatus();
        System.out.println(getNome() + " vasculhou rapidamente: " + getTarefa());
        System.out.println();
    }

    /**
     * A própria thread trata uma possível falha em sua tarefa.
     */
    @Override
    public void run() {
        try {
            executarTarefa();
        } catch (TarefaInvalidaException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
