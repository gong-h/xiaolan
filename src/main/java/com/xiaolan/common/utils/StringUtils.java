package com.xiaolan.common.utils;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;

public class StringUtils {
    /**
     * String expressionString = "100*10-(200+300)";
     */
    public static double calculateToDouble(String expressionString) {
        JexlEngine jexlEngine = new JexlBuilder().create();
        JexlExpression jexlExpression = jexlEngine.createExpression(expressionString);
        Object evaluate = jexlExpression.evaluate(null);
        return evaluate instanceof Double ? (Double) evaluate : (Integer) evaluate;
    }

    /**
     * String expressionString = "100*10-(200+300)";
     */
    public static int calculateToInteger(String expressionString) {
        return (int) calculateToDouble(expressionString);
    }
}
