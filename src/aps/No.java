package aps;

public class No {
    private int valor;
    private No pai;
    private No esquerda;
    private No direita;

    public No(int valor) {
        this.valor = valor;
    }

    public No(int valor, No pai) {
        this.valor = valor;
        this.pai = pai;
    }

    public int getValor() {
        return valor;
    }

    public No getPai() {
        return pai;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }
    
    
}
