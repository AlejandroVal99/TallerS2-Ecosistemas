package com.example.tallers2;

public class Pregunta {

    protected int enunciado1;
    protected int enunciado2;
    protected int respuesta;

    public Pregunta(int enunciado1, int enunciado2) {

        this.enunciado1 = enunciado1;
        this.enunciado2 = enunciado2;
        respuesta = enunciado1 * enunciado2;

    }

    public int getEnunciado1() {
        return enunciado1;
    }

    public void setEnunciado1(int enunciado1) {
        this.enunciado1 = enunciado1;
    }

    public int getEnunciado2() {
        return enunciado2;
    }

    public void setEnunciado2(int enunciado2) {
        this.enunciado2 = enunciado2;
    }

    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }
}
