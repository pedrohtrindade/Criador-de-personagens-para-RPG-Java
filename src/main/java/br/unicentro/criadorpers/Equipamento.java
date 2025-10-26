package br.unicentro.criadorpers;

public class Equipamento {
    private int id;
    private String nome;
    private String tipo;
    private String descricao;
    private int bonusAtaque;
    private int bonusDefesa;

    public Equipamento(int id, String nome, String tipo, String descricao, int bonusAtaque, int bonusDefesa) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.bonusAtaque = bonusAtaque;
        this.bonusDefesa = bonusDefesa;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public String getDescricao() { return descricao; }
    public int getBonusAtaque() { return bonusAtaque; }
    public int getBonusDefesa() { return bonusDefesa; }

    @Override
    public String toString() { return nome; }
}
