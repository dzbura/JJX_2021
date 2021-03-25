public class CitizenWrapper {
    public String name;
    public String pesel;

    public CitizenWrapper(String name) {
        this.name = name;
    }

    public Boolean validatePesel() {
        long S = 0;
        if (this.pesel.length() == 11 && this.pesel.matches("-?\\d+(\\d+)?")) {
            for (Integer i = 0; i < 10; i++) {
                S += Util.indexToWagaPeselMap.get(i + 1) * Character.getNumericValue(pesel.charAt(i));
            }
            if ((10 - (S % 10)) == Character.getNumericValue(pesel.charAt(10))) {
                System.out.println("The PESEL is valid :>");
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
