package com.thomsonreuters.recruitment.batch.impostos.dao.parser;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.thomsonreuters.recruitment.batch.impostos.model.LivroFiscal;

/**
 * Classe responsável por fazer serialização XML do Livro Fiscal
 * 
 * @author Wesley Menezes
 * */
@XmlRootElement( name = "livro_fiscal" )
@XmlSeeAlso(FormatadorEscritaMovimentoContabil.class)
public class FormatadorEscritaLivroFiscal implements ControladorLivroFiscal {

  private LivroFiscal livroFiscal;
  private List movimento;
  
  //construtor default para JAXB
  public FormatadorEscritaLivroFiscal(){
    
  }
  
  public FormatadorEscritaLivroFiscal(LivroFiscal livroFiscal) {
    this.livroFiscal = livroFiscal;
    this.movimento = new ArrayList<>();
    List<Integer> meses = livroFiscal.getMesesLivroContabil();
    for (Integer mes : meses) {
      movimento.add(new FormatadorEscritaMovimentoContabil(mes, livroFiscal.getMesLivroContabil(mes)));
    }
  }

  @XmlElement( name = "movimento", type = FormatadorEscritaMovimentoContabil.class )
  public List getMovimentos( ){
    return this.movimento;    
  }


}
