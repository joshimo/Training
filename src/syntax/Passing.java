package syntax;

/**
 * Created by Golota on 28.12.2017.
 */
public class Passing {

    class SomeClass {

        String classString = "Initial value";
        StringBuilder classSB = new StringBuilder("Initial value");
        Integer classInteger = 1;
        int classInt= 1;


        public void setClassString(String classString) {
            this.classString = classString;
        }

        public void setClassSB(StringBuilder classSB) {
            this.classSB = classSB;
        }

        public void setClassInteger(Integer classInteger) {
            this.classInteger = classInteger;
        }

        public void setClassInt(int classInt) {
            this.classInt = classInt;
        }

        @Override
        public String toString() {
            return "SomeClass \n{ " +
                    "String = '" + classString + '\'' +
                    ", StringBuilder = '" + classSB + '\'' +
                    ", Integer = " + classInteger +
                    ", int = " + classInt +
                    " }";
        }
    }

    class OtherClass {

        void change(SomeClass someClass, int i, String s, StringBuilder sb) {
            someClass.setClassString("Changed value");
            someClass.setClassSB(new StringBuilder("Changed value"));
            someClass.setClassInteger(100);
            someClass.setClassInt(200);

            i = 200;
            s = new String("Changed value");
            sb = new StringBuilder("Changed value");
        }

    }

    Passing() {
        SomeClass someClass = new SomeClass();
        OtherClass otherClass = new OtherClass();

        int i = 1;
        String string = "Initial value";
        StringBuilder sb = new StringBuilder("Initial value");

        System.out.println("Before: " + someClass + "\ni = " + i + "\nString = " + string + "\nStringBuilder = " + sb);
        otherClass.change(someClass, i, string, sb);
        System.out.println("\nAfter: " + someClass + "\ni = " + i + "\nString = " + string + "\nStringBuilder = " + sb);
    }

    public static void main(String... args) {
        Passing passing = new Passing();

    }

}
