package com.scaler.productservice.repositary.projection;

import org.apache.logging.log4j.util.Strings;

public interface ProductProjection {
    // assume there are fields from product model here.
    // so we are defining getters here.
    String getTitle();

    Integer getId();

    Strings getDescription();
}
