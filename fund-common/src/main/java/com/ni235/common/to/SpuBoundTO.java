package com.ni235.common.to;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SpuBoundTO implements Serializable {
    private Long supId;
    private BigDecimal buyBounds;
    private BigDecimal growBounds;
}
