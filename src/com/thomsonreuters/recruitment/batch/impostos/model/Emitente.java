package com.thomsonreuters.recruitment.batch.impostos.model;

public class Emitente implements EmitenteNotaFiscal {
  private String emitente;
  private Double valorArrecadado;
  
  public Emitente(String emitente, Double valorArrecadado){
    this.emitente = emitente;
    this.valorArrecadado = valorArrecadado;
  }
  
  /* (non-Javadoc)
   * @see com.thomsonreuters.recruitment.iss.model.EmitenteNotaFiscal#getEmitente()
   */
  @Override
  public String getEmitente() {
    return emitente;
  }
  /* (non-Javadoc)
   * @see com.thomsonreuters.recruitment.iss.model.EmitenteNotaFiscal#setEmitente(java.lang.String)
   */
  @Override
  public void setEmitente(String emitente) {
    this.emitente = emitente;
  }
  /* (non-Javadoc)
   * @see com.thomsonreuters.recruitment.iss.model.EmitenteNotaFiscal#getValorArrecadado()
   */
  @Override
  public Double getValorArrecadado() {
    return valorArrecadado;
  }
  /* (non-Javadoc)
   * @see com.thomsonreuters.recruitment.iss.model.EmitenteNotaFiscal#setValorArrecadado(java.lang.Double)
   */
  @Override
  public void setValorArrecadado(Double valorTotalArrecadado) {
    this.valorArrecadado = valorTotalArrecadado;
  }
  /* (non-Javadoc)
   * @see com.thomsonreuters.recruitment.iss.model.EmitenteNotaFiscal#setValorArrecadado(java.lang.Double)
   */
  @Override
  public void incrementarValorTotalArrecadado(Double valorArrecadado) {
    this.valorArrecadado += valorArrecadado;
  }
}
