package br.com.bibliotecadigital.service;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class EmprestimoServiceTest {

    private final EmprestimoService service = new EmprestimoService();

    @Test
    public void deveCalcularDataDevolucaoCom10DiasDePrazo() {
        LocalDate dataEmprestimo = LocalDate.of(2026, 6, 1);
        LocalDate dataPrevista = service.calcularDataDevolucao(dataEmprestimo);
        assertEquals(LocalDate.of(2026, 6, 11), dataPrevista);
    }

    @Test
    public void deveCobrarMultaDe10ReaisQuandoAtrasar() {
        LocalDate dataPrevista = LocalDate.of(2026, 6, 11);
        LocalDate dataReal = LocalDate.of(2026, 6, 12);
        double multa = service.calcularMulta(dataPrevista, dataReal);
        assertEquals(10.0, multa, 0.001);
    }

    @Test
    public void naoDeveCobrarMultaQuandoDevolverNoPrazo() {
        LocalDate dataPrevista = LocalDate.of(2026, 6, 11);
        LocalDate dataReal = LocalDate.of(2026, 6, 11);
        double multa = service.calcularMulta(dataPrevista, dataReal);
        assertEquals(0.0, multa, 0.001);
    }

    @Test
    public void naoDeveCobrarMultaQuandoDevolverAdiantado() {
        LocalDate dataPrevista = LocalDate.of(2026, 6, 11);
        LocalDate dataReal = LocalDate.of(2026, 6, 10);
        double multa = service.calcularMulta(dataPrevista, dataReal);
        assertEquals(0.0, multa, 0.001);
    }
}
