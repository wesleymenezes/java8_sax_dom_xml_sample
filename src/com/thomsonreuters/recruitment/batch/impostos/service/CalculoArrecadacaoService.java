package com.thomsonreuters.recruitment.batch.impostos.service;

import java.util.List;

import com.thomsonreuters.recruitment.batch.impostos.model.LivroFiscal;
import com.thomsonreuters.recruitment.batch.impostos.model.NotaFiscal;

/**
 * @author Wesley Menezes
 * */
public interface CalculoArrecadacaoService {

  public LivroFiscal gerarLivroFiscal(List<NotaFiscal> notas);

}