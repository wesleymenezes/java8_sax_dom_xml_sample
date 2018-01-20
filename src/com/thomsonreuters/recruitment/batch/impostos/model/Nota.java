package com.thomsonreuters.recruitment.batch.impostos.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Nota implements NotaFiscal {
  private String emitente;
  private String numero;
  private LocalDate dataEmissao;
  private Integer codigoServico;
  private Double valorTotal;

  /* (non-Javadoc)
   * @see com.thomsonreuters.recruitment.iss.model.NotaFiscal#getEmitente()
   */
  @Override
  public String getEmitente() {
    return emitente;
  }
  /* (non-Javadoc)
   * @see com.thomsonreuters.recruitment.iss.model.NotaFiscal#setEmitente(java.lang.String)
   */
  @Override
  public void setEmitente(String emitente) {
    this.emitente = emitente;
  }
  /* (non-Javadoc)
   * @see com.thomsonreuters.recruitment.iss.model.NotaFiscal#getNumero()
   */
  @Override
  public String getNumero() {
    return numero;
  }
  /* (non-Javadoc)
   * @see com.thomsonreuters.recruitment.iss.model.NotaFiscal#setNumero(java.lang.String)
   */
  @Override
  public void setNumero(String numero) {
    this.numero = numero;
  }
  /* (non-Javadoc)
   * @see com.thomsonreuters.recruitment.iss.model.NotaFiscal#getDataEmissao()
   */
  public LocalDate getDataEmissao() {
    return dataEmissao;
  }
  /* (non-Javadoc)
   * @see com.thomsonreuters.recruitment.iss.model.NotaFiscal#setDataEmissao(java.lang.String)
   */
  @Override
  public void setDataEmissao(String dataEmissao) {
    this.dataEmissao = LocalDate.parse(dataEmissao, DateTimeFormatter.ISO_LOCAL_DATE);
  }
  /* (non-Javadoc)
   * @see com.thomsonreuters.recruitment.iss.model.NotaFiscal#getCodigoServico()
   */
  @Override
  public Integer getCodigoServico() {
    return codigoServico;
  }
  /* (non-Javadoc)
   * @see com.thomsonreuters.recruitment.iss.model.NotaFiscal#setCodigoServico(java.lang.String)
   */
  @Override
  public void setCodigoServico(Integer codigoServico) {
    this.codigoServico = codigoServico;
  }
  /* (non-Javadoc)
   * @see com.thomsonreuters.recruitment.iss.model.NotaFiscal#getValorTotal()
   */
  @Override
  public Double getValorTotal() {
    return valorTotal;
  }
  /* (non-Javadoc)
   * @see com.thomsonreuters.recruitment.iss.model.NotaFiscal#setValorTotal(java.lang.Double)
   */
  @Override
  public void setValorTotal(Double valorTotal) {
    this.valorTotal = valorTotal;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((emitente == null) ? 0 : emitente.hashCode());
    result = prime * result + ((numero == null) ? 0 : numero.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Nota other = (Nota) obj;
    if (emitente == null) {
      if (other.emitente != null)
        return false;
    } else if (!emitente.equals(other.emitente))
      return false;
    if (numero == null) {
      if (other.numero != null)
        return false;
    } else if (!numero.equals(other.numero))
      return false;
    return true;
  }
  
}
