/**
 * Explorador que realiza sua tarefa com atenção aos detalhes.
 * Runnable permite que o objeto seja executado por uma Thread.
 */
public class ExploradorCuidadoso extends Explorador implements Runnable {

    /**
     * Exploradores cuidadosos usam a prioridade mínima definida por Thread.
     */
    public ExploradorCuidadoso(String nome, String tarefa) {
        super(nome, "Cuidadoso", Thread.MIN_PRIORITY, tarefa);
    }

    @Override
    public void executarTarefa() throws TarefaInvalidaException {
        // A tarefa não pode ser nula, vazia ou conter somente espaços.
        if (getTarefa() == null || getTarefa().trim().isEmpty()) {
            throw new TarefaInvalidaException("Tarefa inválida para " + getNome());
        }

        exibirStatus();
        System.out.println(getNome() + " analisou cuidadosamente o ambiente.");
        System.out.println(getNome() + " concluiu com cautela: " + getTarefa());
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
