public class CaesarCipherTwo
{
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int keyMain1;
    private int keyMain2;

    public CaesarCipherTwo(int key1, int key2) //...constructor...//
    {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        keyMain1 = key1;
        keyMain2 = key2;
    }

    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i <encrypted.length();i+=2)
        {
            char currChar = encrypted.charAt(i);
            if ((i %2 == 0) && (Character.isLowerCase(currChar)))
            {
                int idx = alphabet.indexOf(currChar);
                if (idx!= 0)
                {
                    char newChar = shiftedAlphabet1.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }
            }
            else if ((i %2 == 0) && (Character.isUpperCase(currChar)))
            {
                int idx = alphabet.indexOf(currChar);
                if (idx != 0)
                {
                    char newChar = shiftedAlphabet1.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }
            }
        }
        for (int i = 1; i <encrypted.length();i+=2)
        {
            char currChar = encrypted.charAt(i);
            if ((i %2 != 0) && (Character.isLowerCase(currChar)))
            {
                int idx = alphabet.indexOf(currChar);
                if (idx != 0)
                {
                    char newChar = shiftedAlphabet2.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }
            }
            else if ((i %2 != 0) && (Character.isUpperCase(currChar)))
            {
                int idx = alphabet.indexOf(currChar);
                if (idx != 0)
                {
                    char newChar = shiftedAlphabet2.charAt(idx);
                    encrypted.setCharAt(i,newChar);
                }
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input)
    {
        CaesarCipherTwo CIPHER = new CaesarCipherTwo(26-keyMain1,  26-keyMain2);
        return CIPHER.encrypt(input);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public static void main (String[] args)
    {
        CaesarCipherTwo CIPHER = new CaesarCipherTwo(21, 8);
        String ENCRYPT_ME = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = CIPHER.encrypt(ENCRYPT_ME); //...encrypting with key...//
        String decrypted = CIPHER.decrypt(encrypted); //...decrypting with 26-key...//
        System.out.println("Real Message: " + ENCRYPT_ME);
        System.out.println("Encrypted Message: " + encrypted);
        System.out.println("Decrypted Message: " + decrypted);
    }
}
