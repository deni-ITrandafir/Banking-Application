/** Clasa pentru o excepție personalizată în aplicație, utilizată pentru a semnala situațiile
 * în care un card nu are suficiente fonduri pentru a finaliza o tranzacție.
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */


package com.example.Banking.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
