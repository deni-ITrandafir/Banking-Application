/** Clasa pentru o excepție personalizată utilizată în aplicație pentru a semnala situațiile
 * în care o tranzacție între conturi sau carduri implică monede diferite și nu poate fi procesată.
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */


package com.example.Banking.exception;

public class InvalidCurrencyException extends RuntimeException {
    public InvalidCurrencyException(String message) {
        super(message);
    }
}
