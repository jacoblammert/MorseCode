package com.jacobjacob.morse;

import static com.jacobjacob.morse.MainActivity.textView;
import static com.jacobjacob.morse.Util.ALPHABET;

public class StringConverter {

    public StringConverter() {
    }


    /**
     * Converts a String into morse code with . and - . This is used to control the flash
     * @param input input like: Hello
     * @return Output converted to:  .... . .-.. .-.. ---
     */
    public String stringToMorseCode(String input) {

        StringBuilder stringInput = new StringBuilder();
        stringInput.append(input);

        StringBuilder stringOutput = new StringBuilder();

        for (int i = 0; i < stringInput.length(); i++) {
            for (int j = 0; j < ALPHABET.length; j++) {
                if (ALPHABET[j][0] != null) {
                    if ((ALPHABET[j][0].toLowerCase()).equals((String.valueOf(stringInput.charAt(i))).toLowerCase())) {
                        if (ALPHABET[j][1] != null) {
                            stringOutput.append(ALPHABET[j][1] + " ");
                            break;
                        }
                    }
                }
                if (j == ALPHABET.length-1){
                    stringOutput.append("   ");
                }
            }
        }


        textView.setText(String.valueOf(stringOutput));

        return String.valueOf(stringOutput);
    }
}
