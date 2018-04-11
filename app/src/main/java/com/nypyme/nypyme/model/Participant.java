package com.nypyme.nypyme.model;

import com.squareup.moshi.Json;

/*
 * Created by Acer on 08/04/2018.
 */

public class Participant {
  @Json(name = "nome") private String nome;
  @Json(name = "cpf") private String cpf;
  @Json(name = "pontos") private String pontos;

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getPontos() {
    return pontos;
  }

  public void setPontos(String pontos) {
    this.pontos = pontos;
  }
}
