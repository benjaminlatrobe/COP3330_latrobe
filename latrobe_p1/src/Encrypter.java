// written by Benjamin Latrobe
public class Encrypter {

    public String encrypt(String str){
        int[] array = stringToArray(str);

        array = addSevenModTen(array);

        array = swapFirstAndThird(array);

        array = swapSecondAndForth(array);

        return arrayToString(array);
    }

    public String arrayToString(int[] array){

        return String.valueOf(array[0]) + array[1] + array[2] + array[3];
    }

    public int[] swapSecondAndForth(int[] array){

        int temp = array[1];
        array[1] = array[3];
        array[3] = temp;

        return array;
    }

    public int[] swapFirstAndThird(int[] array){

        int temp = array[0];
        array[0] = array[2];
        array[2] = temp;

        return array;
    }

    public int[] addSevenModTen(int[] array){

        array[0] = (array[0]+7)%10;
        array[1] = (array[1]+7)%10;
        array[2] = (array[2]+7)%10;
        array[3] = (array[3]+7)%10;

        return array;
    }

    public int[] stringToArray(String str){
        int[] array = new int[4];

        array[0] = Character.getNumericValue(str.charAt(0));
        array[1] = Character.getNumericValue(str.charAt(1));
        array[2] = Character.getNumericValue(str.charAt(2));
        array[3] = Character.getNumericValue(str.charAt(3));

        return array;
    }
}