package br.unicentro.criadorpers;

import java.util.List;

public class Character {
    private int id;
    private String nome;
    private int nivel;
    private int forca;
    private int destreza;
    private int constituicao;
    private int inteligencia;
    private int sabedoria;
    private int carisma;
    private Race raca;
    private Classe classe;
    private List<Habilidade> habilidades;
    private List<Equipamento> equipamentos;

    public Character(int id, String nome, int nivel, int forca, int destreza, int constituicao,
                     int inteligencia, int sabedoria, int carisma,
                     Race raca, Classe classe,
                     List<Habilidade> habilidades,
                     List<Equipamento> equipamentos) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;
        this.forca = forca;
        this.destreza = destreza;
        this.constituicao = constituicao;
        this.inteligencia = inteligencia;
        this.sabedoria = sabedoria;
        this.carisma = carisma;
        this.raca = raca;
        this.classe = classe;
        this.habilidades = habilidades;
        this.equipamentos = equipamentos;
    }

    
    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getNivel() { return nivel; }
    public int getForca() { return forca; }
    public int getDestreza() { return destreza; }
    public int getConstituicao() { return constituicao; }
    public int getInteligencia() { return inteligencia; }
    public int getSabedoria() { return sabedoria; }
    public int getCarisma() { return carisma; }
    public Race getRaca() { return raca; }
    public Classe getClasse() { return classe; }
    public List<Habilidade> getHabilidades() { return habilidades; }
    public List<Equipamento> getEquipamentos() { return equipamentos; }
}
