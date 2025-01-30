/** Clasa pentru o excepție personalizată utilizată pentru a semnala situațiile în
 * care un PIN introdus este incorect
 * @author Trandafir Denisa Ioana
 * @version 6 Ianuarie 2024
 */


package com.example.Banking.exception;

public class InvalidPinException extends RuntimeException {
    public InvalidPinException(String message) {
        super(message);
    }
}
