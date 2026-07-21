
import java.util.concurrent.Callable;
/**
 * Representa a estrutura comum dos exploradores do nível Mestre.
 */
public abstract class Explorador implements Callable<Double>{

    // Dados encapsulados do explorador.
    private String nome;
    private String especialidade;
    private int nivel;
    private int energia;
    private Missao tarefa;

    /**
     * Inicializa todos os atributos do explorador.
     */
    public Explorador(
            String nome,
            String especialidade,
            int nivel,
            int energia,
            Missao tarefa
    ) {
        this.nome = nome;
        this.especialidade = especialidade;
        this.nivel = nivel;
        this.energia = energia;
        this.tarefa = tarefa;
    }

    /**
     * Cada subclasse implementará uma fórmula diferente de pontuação.
     *
     * @return quantidade de pontos obtidos na missão
     */
    public abstract Double executarMissao();

    /**
     * Exibe todos os dados do explorador e da missão.
     */
    public void exibirStatus() {
        System.out.println("Explorador: " + nome);
        System.out.println("Especialidade: " + especialidade);
        System.out.println("Nível: " + nivel);
        System.out.println("Energia: " + energia);
        System.out.println("Missão: " + tarefa);
    }

    // Getters para acesso controlado aos atributos.
    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public int getNivel() {
        return nivel;
    }

    public int getEnergia() {
        return energia;
    }

    public Missao getTarefa() {
        return tarefa;
    }
}