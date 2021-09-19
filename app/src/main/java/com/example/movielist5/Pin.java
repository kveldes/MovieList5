package com.example.movielist5;

public class Pin {
    private int pin_id;
    private int pin;

    public Pin(int pin) {
        this.pin = pin;
    }

    public int getPin_id() {
        return pin_id;
    }

    public void setPin_id(int pin_id) {
        this.pin_id = pin_id;
    }

    public Pin(String toString) {
        this.pin = Integer.parseInt(toString);
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }



}
