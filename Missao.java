/**
 * Representa uma missão que será atribuída a um explorador.
 *
 * A classe é imutável: seus dados não podem ser alterados
 * depois que o objeto é criado.
 */
public final class Missao {

    // Os atributos final recebem valor apenas uma vez.
    private final String descricao;
    private final String local;
    private final int dificuldade;

    /**
     * Inicializa todos os dados da missão.
     */
    public Missao(
            String descricao,
            String local,
            int dificuldade
    ) {
        this.descricao = descricao;
        this.local = local;
        this.dificuldade = dificuldade;
    }

    // A classe possui apenas getters e não possui setters.
    public String getDescricao() {
        return descricao;
    }

    public String getLocal() {
        return local;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    /**
     * Retorna os dados da missão em formato legível.
     */
    @Override
    public String toString() {
        return descricao
                + " em "
                + local
                + " (Dificuldade "
                + dificuldade
                + ")";
    }
}