
/**
 * Representa a tarefa imutavel que sera executada pelo explorador
 * Tarefa
 */
public final class Tarefa 
{
    //atributos final so podem receber valor uma vez
    private final String descricao;
    private final String local;
    private final int dificuldade;

    //inicia todos os dados da tarefa
    public Tarefa(String descricao,String local,int dificuldade)
    {
        this.descricao = descricao;
        this.local = local;
        this.dificuldade = dificuldade;
    }

    // a classe deve possuir somente getters
    public String getDescricao(){return descricao;}
    public String getLocal(){return local;}
    public int getDificuldade(){return dificuldade;}

    //retorna a representacao clara da tarefa
    @Override
    public String toString()
    {
        return descricao  + " em " + local + " (Dificuldade " + dificuldade + ")";        
    }
}
  