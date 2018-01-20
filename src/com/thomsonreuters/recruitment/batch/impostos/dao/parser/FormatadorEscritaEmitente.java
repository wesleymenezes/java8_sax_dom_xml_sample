package com.thomsonreuters.recruitment.batch.impostos.dao.parser;

import javax.xml.bind.annotation.XmlElement;

import com.thomsonreuters.recruitment.batch.impostos.model.EmitenteNotaFiscal;

/**
 * Classe responsável por fazer serialização XML do Emitente da NF
 * 
 * @author Wesley Menezes
 * */
public class FormatadorEscritaEmitente {
  private EmitenteNotaFiscal emitenteNotaFiscal;
  
  //construtor default para JAXB
  public FormatadorEscritaEmitente () {
    
  }
  
  public FormatadorEscritaEmitente(EmitenteNotaFiscal emitenteNotaFiscal) {
    this.emitenteNotaFiscal = emitenteNotaFiscal;
  }
  
  @XmlElement( name = "emitente", type=String.class )
  public String getEmitenteNotaFiscal(){
    return this.emitenteNotaFiscal.getEmitente();
  }

  @XmlElement( name = "valor_arrecadado", type=Double.class )
  public Double getValorArrecadado(){
    return this.emitenteNotaFiscal.getValorArrecadado();
  }

  
}
