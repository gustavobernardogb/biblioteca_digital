package br.com.bibliotecadigital.service;

import java.time.LocalDate;

public class EmprestimoService {

    public LocalDate calcularDataDevolucao(LocalDate dataEmprestimo) {
        return dataEmprestimo.plusDays(10);
    }

    public double calcularMulta(LocalDate dataPrevista, LocalDate dataReal) {

        if (dataReal.isAfter(dataPrevista)) {
            return 10.0;
        }

        return 0.0;
    }
}