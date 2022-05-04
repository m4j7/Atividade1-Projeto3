package br.com.consultorio.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum StAgendamento {


        pendente("Pendente"),
        aprovado("Aprovado"),
        cancelado("Cancelado"),
        compareceu("Compareceu"),
        ncompareceu("Não Compareceu"),
        rejeitado("Rejeitado");

        public final String valor;

        private StAgendamento(String valor) {
            this.valor = valor;
        }
    }

