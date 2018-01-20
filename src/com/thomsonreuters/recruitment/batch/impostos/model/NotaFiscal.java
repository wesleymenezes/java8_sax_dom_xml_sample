package com.thomsonreuters.recruitment.batch.impostos.model;

import java.time.LocalDate;

public interface NotaFiscal {

  String getEmitente();

  void setEmitente(String emitente);

  String getNumero();

  void setNumero(String numero);

  LocalDate getDataEmissao();

  void setDataEmissao(String dataEmissao);

  Integer getCodigoServico();

  void setCodigoServico(Integer codigoServico);

  Double getValorTotal();

  void setValorTotal(Double valorTotal);

}