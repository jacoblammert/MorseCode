package com.jacobjacob.morse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


import static com.jacobjacob.morse.Util.ALPHABET;
import static com.jacobjacob.morse.Util.CONTEXT;


public class Morse {

    public Morse() {
        initializeMorseAlphabet();
        //print();
    }

    public void blink(){
        for (int i = 0; i < 3; i++) {
            pulse(500);
            delay(500);
            pulse(1000);
            delay(500);
            pulse(500);
            delay(500);
        }
        for (int i = 0; i < 20; i++) {
            pulse(200);
            delay(200);
        }
    }

    public void initializeMorseAlphabet() {

        InputStream inputStream = CONTEXT.getResources().openRawResource(R.raw.morse);

        int pos = 0;

        ALPHABET = new String[59][];

        try {

            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");


                String[] parts = text.split(" ");

                ALPHABET[pos] = parts;
                pos++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void print() {
        for (int i = 0; i < ALPHABET.length; i++) {
            for (int j = 0; j < ALPHABET[i].length; j++) {
                System.out.println(ALPHABET[i][j]);
            }
        }
    }


    public void delay(int delaytime) {
        long currentTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < currentTime + delaytime) {}
    }

    public void pulse(int pulsetime) {
        MainActivity.switchFlashLight(true);
        delay(pulsetime);
        MainActivity.switchFlashLight(false);
    }

    /**
     * Gets the already converted morse code as input and flashes it
     * @param morsecode the morse code as String
     */
    public void pulseCode(String morsecode){

        StringBuilder sb = new StringBuilder();
        sb.append(morsecode);

        String dot = ".";
        String line = "-";

        int pulseTime = 200;


        for (int i = 0; i < sb.length(); i++) {
            System.out.println(sb.charAt(i));
            if (dot.equals(String.valueOf(sb.charAt(i)))){
                pulse(pulseTime);

            }else if (line.equals(String.valueOf(sb.charAt(i)))){
                pulse(pulseTime*2);
            }else {
                delay(500);
            }
            delay(250);
        }

    }

}

