package models;

public class Restricao {
    private int linha1, coluna1;
    private int linha2, coluna2;
    private char tipo; // '=' para igualdade, 'x' para oposição

    public Restricao(int linha1, int coluna1, int linha2, int coluna2, char tipo) {
        this.linha1 = linha1;
        this.coluna1 = coluna1;
        this.linha2 = linha2;
        this.coluna2 = coluna2;
        this.tipo = tipo;
    }

    public int getLinha1() {
       return linha1;
    }

    public void setLinha1(int linha1) {
       this.linha1 = linha1;
    }

    public int getColuna1() {
       return coluna1;
    }

    public void setColuna1(int coluna1) {
       this.coluna1 = coluna1;
    }

    public int getLinha2() {
       return linha2;
    }

    public void setLinha2(int linha2) {
       this.linha2 = linha2;
    }

    public int getColuna2() {
       return coluna2;
    }

    public void setColuna2(int coluna2) {
       this.coluna2 = coluna2;
    }

    public char getTipo() {
       return tipo;
    }

    public void setTipo(char tipo) {
       this.tipo = tipo;
    }

    

}