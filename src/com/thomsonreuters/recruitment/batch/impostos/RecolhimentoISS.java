package com.thomsonreuters.recruitment.batch.impostos;

import java.util.List;

import com.thomsonreuters.recruitment.batch.impostos.dao.NotaFiscalDAO;
import com.thomsonreuters.recruitment.batch.impostos.dao.NotaFiscalDAOException;
import com.thomsonreuters.recruitment.batch.impostos.dao.NotaFiscalDAOFactory;
import com.thomsonreuters.recruitment.batch.impostos.model.LivroFiscal;
import com.thomsonreuters.recruitment.batch.impostos.model.NotaFiscal;
import com.thomsonreuters.recruitment.batch.impostos.params.LeituraParametrosException;
import com.thomsonreuters.recruitment.batch.impostos.service.CalculoArrecadacaoService;
import com.thomsonreuters.recruitment.batch.impostos.service.CalculoArrecadacaoServiceFactory;

/**
 * Classe principal do sistema de cálculo para recolhimento do ISS.
 * 
 * @author Wesley Menezes
 */
public class RecolhimentoISS {

  public static void main(String[] args) throws LeituraParametrosException, NotaFiscalDAOException {

    // carregar arquivos recebidos para processamento
    NotaFiscalDAOFactory nfdf = new NotaFiscalDAOFactory();
    NotaFiscalDAO notaFiscalDAO = nfdf.getNotaFiscalDAO(NotaFiscalDAOFactory._XML);
    List<NotaFiscal> notas = notaFiscalDAO.findNotaFiscalPendente();

    // realizar cálculo de imposto arrecadado segundo regras TR
    CalculoArrecadacaoServiceFactory casf = new CalculoArrecadacaoServiceFactory();
    CalculoArrecadacaoService casISS = casf.getCalculoArrecadacaoService(CalculoArrecadacaoServiceFactory._ISS);
    LivroFiscal livroFiscal = casISS.gerarLivroFiscal(notas);

    // gravar resultado de processamento
    notaFiscalDAO.createLivroFiscal(livroFiscal);
  }
}
