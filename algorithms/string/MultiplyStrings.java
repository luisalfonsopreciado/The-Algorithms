package algorithms.string;

public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        int carry = 0;
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < num1.length() * num2.length(); i++) {
            out.append('0');
        }

        int outPtr = num1.length() * num2.length() - 1;
        int shift = 0;
        
        for (int n1Ptr = num1.length() - 1; n1Ptr >= 0; n1Ptr--) {
            int n1 = getInt(num1.charAt(n1Ptr));

            for (int n2Ptr = num2.length() - 1; n2Ptr >= 0; n2Ptr--) {
                int n2 = getInt(num2.charAt(n2Ptr));
                int outNum = getInt(out.charAt(outPtr));
                int tot = n1 * n2 + carry + outNum;
                out.setCharAt(outPtr, (char) (tot % 10 + '0'));
                tot /= 10;

                carry = tot % 10;
                outPtr--;
            }
            if(carry > 0){
                if(outPtr < 0){
                    out.insert(0, '0');
                    outPtr = 0;
                }
                out.setCharAt(outPtr, (char) (carry + '0'));
                carry = 0;
            }
            shift++;

            outPtr = num1.length() * num2.length() - 1 - shift;
        }

        while (out.length() > 1 && out.charAt(0) == '0') {
            out.deleteCharAt(0);
        }

        return out.toString();

    }

    public int getInt(char c) {
        return Character.getNumericValue(c);
    }

    public static void main(String[] args) {
        MultiplyStrings ms = new MultiplyStrings();
        System.out.println(ms.multiply("2", "3"));
        System.out.println(ms.multiply("22", "3"));
        System.out.println(ms.multiply("2294", "3"));
    }
}
