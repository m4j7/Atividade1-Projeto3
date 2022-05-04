package br.com.consultorio.entity;

public enum Sexo {

   masculino("masculino"),
   feminino("feminino"),
   outro("outro");

   public final String valor;

      private Sexo(String valor){

         this.valor = valor;
      }
   }




