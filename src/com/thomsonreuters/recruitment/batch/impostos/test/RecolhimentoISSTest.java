package com.thomsonreuters.recruitment.batch.impostos.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.thomsonreuters.recruitment.batch.impostos.dao.NotaFiscalDAO;
import com.thomsonreuters.recruitment.batch.impostos.dao.NotaFiscalDAOException;
import com.thomsonreuters.recruitment.batch.impostos.dao.NotaFiscalDAOFactory;
import com.thomsonreuters.recruitment.batch.impostos.model.EmitenteNotaFiscal;
import com.thomsonreuters.recruitment.batch.impostos.model.LivroFiscal;
import com.thomsonreuters.recruitment.batch.impostos.model.NotaFiscal;
import com.thomsonreuters.recruitment.batch.impostos.params.LeituraParametrosException;
import com.thomsonreuters.recruitment.batch.impostos.service.CalculoArrecadacaoService;
import com.thomsonreuters.recruitment.batch.impostos.service.CalculoArrecadacaoServiceFactory;

/**
 * QA mínimo para validar cálculo de alíquota
 * 
 * @author Wesley Menezes
 * */
public class RecolhimentoISSTest {

  @Test
  public void recolhimentoISS() throws NotaFiscalDAOException, LeituraParametrosException {
    // carrega arquivos recebidos para processamento
    NotaFiscalDAOFactory nfdf = new NotaFiscalDAOFactory();
    NotaFiscalDAO notaFiscalDAO = nfdf.getNotaFiscalDAO(NotaFiscalDAOFactory._XML);
    List<NotaFiscal> notas = notaFiscalDAO.findNotaFiscalPendente();

    CalculoArrecadacaoServiceFactory casf = new CalculoArrecadacaoServiceFactory();
    CalculoArrecadacaoService casISS = casf.getCalculoArrecadacaoService(CalculoArrecadacaoServiceFactory._ISS);
    LivroFiscal livroFiscal = casISS.gerarLivroFiscal(notas);
    
    //obtem entradas livro fiscal para validação com base em massa de controle Thomson Reuters
    List<EmitenteNotaFiscal> emitentesFevereiro = livroFiscal.getMesLivroContabil(2);
    List<EmitenteNotaFiscal> emitentesMarco = livroFiscal.getMesLivroContabil(3);
    
    Double arrecadacaoFevereiro01234567890123 = 0.0d;
    Double arrecadacaoFevereiro98765432101234 = 0.0d;
    Double arrecadacaoMarco01234567890123 = 0.0d;
    for (EmitenteNotaFiscal emitenteNotaFiscal : emitentesFevereiro) {
      if (emitenteNotaFiscal.getEmitente().equals("01234567890123")){
        arrecadacaoFevereiro01234567890123 = emitenteNotaFiscal.getValorArrecadado();
      }
    }
    for (EmitenteNotaFiscal emitenteNotaFiscal : emitentesFevereiro) {
      if (emitenteNotaFiscal.getEmitente().equals("98765432101234")){
        arrecadacaoFevereiro98765432101234 = emitenteNotaFiscal.getValorArrecadado();
      }
    }
    for (EmitenteNotaFiscal emitenteNotaFiscal : emitentesMarco) {
      if (emitenteNotaFiscal.getEmitente().equals("01234567890123")){
        arrecadacaoMarco01234567890123 = emitenteNotaFiscal.getValorArrecadado();
      }
    }
    
    // assert statements
    assertEquals(50.0d, arrecadacaoFevereiro01234567890123.doubleValue(), 0.0d); //"Mês 2 Fevereiro Emitente 01234567890123"
    assertEquals(49.8824d, arrecadacaoFevereiro98765432101234.doubleValue(), 0.0d); //"Mês 2 Fevereiro Emitente 98765432101234" 
    assertEquals(225.0d, arrecadacaoMarco01234567890123.doubleValue(), 0.0d); //"Mês 3 Marco Emitente 01234567890123"
  }

}
