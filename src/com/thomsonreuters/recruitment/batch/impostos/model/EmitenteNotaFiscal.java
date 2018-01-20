package com.thomsonreuters.recruitment.batch.impostos.model;

public interface EmitenteNotaFiscal {

  String getEmitente();

  void setEmitente(String emitente);

  Double getValorArrecadado();

  void setValorArrecadado(Double valorTotalArrecadado);
  
  void incrementarValorTotalArrecadado(Double valorArrecadao);

}