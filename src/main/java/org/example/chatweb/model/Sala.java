package org.example.chatweb.model;

/**
 * As 6 salas fixas de bate-papo, uma para cada periodo do IFPE.
 * Nao ha persistencia de salas nem de mensagens: a lista e estatica em memoria.
 */
public enum Sala {

    PERIODO_1("1", "1º Período"),
    PERIODO_2("2", "2º Período"),
    PERIODO_3("3", "3º Período"),
    PERIODO_4("4", "4º Período"),
    PERIODO_5("5", "5º Período"),
    PERIODO_6("6", "6º Período");

    private final String id;
    private final String nome;

    Sala(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static boolean existe(String id) {
        for (Sala sala : values()) {
            if (sala.id.equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static Sala porId(String id) {
        for (Sala sala : values()) {
            if (sala.id.equals(id)) {
                return sala;
            }
        }
        throw new IllegalArgumentException("Sala inexistente: " + id);
    }
}
