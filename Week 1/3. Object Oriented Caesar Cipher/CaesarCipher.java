
public class CaesarCipher
{
    private String alphabet;
    private String shiftedAlphabet;
    private int keyMain;

    public CaesarCipher(int key) //...constructor...//
    {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        keyMain = key;
    }
    public String encrypt(String input)
    {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i=0; i<encrypted.length(); i++)
        {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);

            if(idx != -1) //...currChar is an ALPHABET...//
            {
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }

            else //...for lowercase...//
            {
                currChar = Character.toUpperCase(currChar);
                idx = alphabet.indexOf(currChar);
                if(idx != -1)
                {
                    char newChar = shiftedAlphabet.charAt(idx);
                    newChar = Character.toLowerCase(newChar);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input)
    {
        CaesarCipher CIPHER = new CaesarCipher(26-keyMain);
        return CIPHER.encrypt(input);
    }



//////////////////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////////////////
    public static void main (String[] args)
    {
        CaesarCipher CIPHER = new CaesarCipher(15);
        String ENCRYPT_ME = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = CIPHER.encrypt(ENCRYPT_ME); //...encrypting with key...//
        String decrypted = CIPHER.decrypt(encrypted); //...decrypting with 26-key...//
        System.out.println("Real Message: " + ENCRYPT_ME);
        System.out.println("Encrypted Message: " + encrypted);
        System.out.println("Decrypted Message: " + decrypted);
    }
}
