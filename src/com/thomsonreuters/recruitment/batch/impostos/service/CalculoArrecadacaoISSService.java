package com.thomsonreuters.recruitment.batch.impostos.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thomsonreuters.recruitment.batch.impostos.model.Emitente;
import com.thomsonreuters.recruitment.batch.impostos.model.EmitenteNotaFiscal;
import com.thomsonreuters.recruitment.batch.impostos.model.LivroFiscal;
import com.thomsonreuters.recruitment.batch.impostos.model.NotaFiscal;

/**
 * Gera livro fiscal para imposto ISS
 * 
 * @author Wesley Menezes
 */
public class CalculoArrecadacaoISSService implements CalculoArrecadacaoService {
  
  @Override
  public LivroFiscal gerarLivroFiscal(List<NotaFiscal> notas) {
    LivroFiscal livroFiscal = new LivroFiscal();
    
    //percorre as notas fiscais
    for (NotaFiscal notaFiscal : notas) {
      //calcula imposto por nota recebida
      Double incrementoValorArrecadado = notaFiscal.getValorTotal()*AliquotaISS.getAliquota(notaFiscal.getCodigoServico());
      EmitenteNotaFiscal emitente = new Emitente(notaFiscal.getEmitente(), incrementoValorArrecadado);
      livroFiscal.addEmitenteNotaFiscal(notaFiscal.getDataEmissao().getMonthValue(), emitente);
    }
    return livroFiscal;   
  }
  

  
  private Map<String, Emitente> emitentesMes = new HashMap<String, Emitente>();
  
  public EmitenteNotaFiscal getEmitente(EmitenteNotaFiscal emitente) {
    return emitentesMes.get(emitente);
  }
}
