package com.thomsonreuters.recruitment.batch.impostos.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AliquotaISS {
  private static final double _0_05 = 0.05;
  private static final double _0_04 = 0.04;
  private static final double _0_03 = 0.03;

  private static final Map<Integer, Double> aliquota = createMap();

  private static Map<Integer, Double> createMap() {
    Map<Integer, Double> result = new HashMap<Integer, Double>();
    result.put(1001, _0_05);
    result.put(1002, _0_05);
    result.put(1003, _0_05);
    result.put(2001, _0_03);
    result.put(2002, _0_04);

    return Collections.unmodifiableMap(result);
  }

  public static Double getAliquota(Integer codigoServico) {
    return aliquota.get(codigoServico);
  }

}
