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
    //para cada nota atualiza entrada do mês contábil
    //verifica se a lista de emitentes existe para o mês contábil
    if (registroFiscal.get(mes)==null){
      registroFiscal.put(mes, new ArrayList<EmitenteNotaFiscal>());
      registroFiscal.get(mes).add(emitenteNotaFiscal);
    } else {
      //gera entrada de mês contábil para inclusão do emitente
      try{
        this.getEmitente(mes, emitenteNotaFiscal.getEmitente()).incrementarValorTotalArrecadado(emitenteNotaFiscal.getValorArrecadado());
      } catch (EmitenteNotFoundException enfe ){
        //o emitente não existe no livro contábil, cria emitente e faz lançamento de imposto arrecadado no livro fiscal
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

      //se não existe emitente no livro fiscal, cria entrada????
      if (emitenteLivroFiscal == null) {
        throw new EmitenteNotFoundException();
      }

    return emitenteLivroFiscal;
  }
}