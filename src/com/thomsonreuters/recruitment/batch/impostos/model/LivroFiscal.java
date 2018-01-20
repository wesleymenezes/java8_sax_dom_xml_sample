package com.thomsonreuters.recruitment.batch.impostos.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LivroFiscal {

  private Map<Integer,List<EmitenteNotaFiscal>> registroFiscal;
  
  public LivroFiscal () {
    registroFiscal = new HashMap<Integer,List<EmitenteNotaFiscal>>();
  }
  
  public List<Integer> getMesesLivroContabil(){
    return new ArrayList(registroFiscal.keySet());
  }
  
  public List<EmitenteNotaFiscal> getMesLivroContabil(Integer mes){
    return registroFiscal.get(mes);
  }
  
  public void addEmitenteNotaFiscal(Integer mes, EmitenteNotaFiscal emitenteNotaFiscal){
    //para cada nota atualiza entrada do m�s cont�bil
    //verifica se a lista de emitentes existe para o m�s cont�bil
    if (registroFiscal.get(mes)==null){
      registroFiscal.put(mes, new ArrayList<EmitenteNotaFiscal>());
      registroFiscal.get(mes).add(emitenteNotaFiscal);
    } else {
      //gera entrada de m�s cont�bil para inclus�o do emitente
      try{
        this.getEmitente(mes, emitenteNotaFiscal.getEmitente()).incrementarValorTotalArrecadado(emitenteNotaFiscal.getValorArrecadado());
      } catch (EmitenteNotFoundException enfe ){
        //o emitente n�o existe no livro cont�bil, cria emitente e faz lan�amento de imposto arrecadado no livro fiscal
        registroFiscal.get(mes).add(emitenteNotaFiscal);
      }
    }
    
  }
  
  private EmitenteNotaFiscal getEmitente(Integer mes, String emitente) throws EmitenteNotFoundException {
    EmitenteNotaFiscal emitenteLivroFiscal = null;

      for (EmitenteNotaFiscal emitenteNotaFiscal : registroFiscal.get(mes)) {
        if (emitenteNotaFiscal.getEmitente().equals(emitente)){
          emitenteLivroFiscal = emitenteNotaFiscal; 
        }
      }

      //se n�o existe emitente no livro fiscal, cria entrada????
      if (emitenteLivroFiscal == null) {
        throw new EmitenteNotFoundException();
      }

    return emitenteLivroFiscal;
  }
}