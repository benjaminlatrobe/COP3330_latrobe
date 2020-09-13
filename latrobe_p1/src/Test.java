public class Test {
    public static void main(String[] args) {
        Encrypter e = new Encrypter();
        System.out.println(e.encrypt("1234"));

        Decrypter d = new Decrypter();
        System.out.println(d.decrypt("0189"));
    }
}
