/**
 * Exceção personalizada usada quando uma tarefa é nula ou vazia.
 */
public class TarefaInvalidaException extends Exception {

    /**
     * Encaminha a mensagem recebida para a classe Exception.
     */
    public TarefaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
