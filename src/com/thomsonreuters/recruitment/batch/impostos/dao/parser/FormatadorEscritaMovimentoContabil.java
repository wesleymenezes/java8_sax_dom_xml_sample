package com.thomsonreuters.recruitment.batch.impostos.dao.parser;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.thomsonreuters.recruitment.batch.impostos.model.EmitenteNotaFiscal;

/**
 * Classe responsável por fazer serialização XML do Movimento Contábil
 * 
 * @author Wesley Menezes
 * */
@XmlRootElement( name = "movimento" )
@XmlSeeAlso(FormatadorEscritaEmitente.class)
public class FormatadorEscritaMovimentoContabil {

  private Integer mes;
  private List<FormatadorEscritaEmitente> emitentesEscrita;
  
  //construtor default para JAXB
  public FormatadorEscritaMovimentoContabil(){
    
  }
  
  public FormatadorEscritaMovimentoContabil(Integer mes, List<EmitenteNotaFiscal> emitentesNotaFiscal) {
    this.mes = mes;
    this.emitentesEscrita = new ArrayList<>();
    for (EmitenteNotaFiscal emitenteNotaFisca : emitentesNotaFiscal) {
      this.emitentesEscrita.add(new FormatadorEscritaEmitente(emitenteNotaFisca));
    }
//    this.emitentesNotaFiscal = emitenteNotaFiscal;
  }

  @XmlElement( name = "mes", type=Integer.class )
  public Integer getMes(){
    return this.mes;
  }

  @XmlElement( name = "emitentes", type = FormatadorEscritaEmitente.class )
  public List<FormatadorEscritaEmitente> getEmitentesNotaFiscal(){
    return this.emitentesEscrita;
  }
  
  
}
