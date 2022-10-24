package com.locadora.entity;

public class User {
    private int id;
    private String primeiroNome;
    private String ultimoNome;
    private String cnh;
    private boolean administrador;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }
    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }
    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getCNH() {
        return cnh;
    }
    public void setCNH(String cnh) {
        this.cnh = cnh;
    }

    public boolean isAdministrator() {
        return administrador;
    }
}
