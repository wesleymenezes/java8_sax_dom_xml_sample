package com.thomsonreuters.recruitment.batch.impostos.service;

/**
 * @author Wesley Menezes
 * */
public class CalculoArrecadacaoServiceFactory {
  
  public static final int _ISS = 1;

  public CalculoArrecadacaoService getCalculoArrecadacaoService(Integer tipoImposto){
    CalculoArrecadacaoService calculoArrecadacaoService = null;
    
    switch (tipoImposto) {
    case _ISS:
      calculoArrecadacaoService = new CalculoArrecadacaoISSService(); 
      break;

    default:
      break;
    }
   
    return calculoArrecadacaoService;
  }

}
