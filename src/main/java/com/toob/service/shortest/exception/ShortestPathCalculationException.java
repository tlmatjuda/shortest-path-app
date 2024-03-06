package com.toob.service.shortest.exception;

/**
 * To be used when calculating the Shortest Path.
 * @author : Thabo Matjuda
 */
public class ShortestPathCalculationException extends RuntimeException {

    public ShortestPathCalculationException(String message) {
        super(message);
    }
}
