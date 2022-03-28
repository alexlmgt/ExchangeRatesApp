package com.example.exchangeratesapp;

public class Valute {
    private String valute_n;
    private String name_n;
    private String value_n;

    public String getValute_n() {
        return valute_n;
    }

    public void setValute_n(String valute_n) {
        this.valute_n = valute_n;
    }

    public String getName_n() {
        return name_n;
    }

    public void setName_n(String name_n) {
        this.name_n = name_n;
    }

    public String getValue_n() {
        return value_n;
    }

    public void setValue_n(String value_n) {
        this.value_n = value_n;
    }

    public Valute (String valute_n, String name_n, String value_n) {
        this.valute_n = valute_n;
        this.name_n = name_n;
        this.value_n = value_n;
    }
}
