package enums;

public enum Simbolo {
    VAZIO('.'),
    SOL('S'),
    LUA('L');

    private final char caractere;

    Simbolo(char caractere) {
        this.caractere = caractere;
    }

    public char getCaractere() {
        return caractere;
    }
}