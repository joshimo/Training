package hashCode_and_equals;

import java.util.List;
import java.util.ArrayList;

public class SomeClass {

    private String name;

    private Integer i1;
    private Double d1;
    private String s1;

    private int i = 1;
    private double d = 1.0;
    private long l = 1l;
    private boolean b = true;

    public SomeClass(String name) {
        this.name = name;
    }

    public Integer getI1() {
        return i1;
    }

    public Double getD1() {
        return d1;
    }

    public String getS1() {
        return s1;
    }


    public SomeClass setI1(Integer i1) {
        this.i1 = i1;
        return this;
    }

    public SomeClass setD1(Double d1) {
        this.d1 = d1;
        return this;
    }

    public SomeClass setS1(String s1) {
        this.s1 = s1;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SomeClass someClass = (SomeClass) o;

        if (i != someClass.i) return false;
        if (Double.compare(someClass.d, d) != 0) return false;
        if (l != someClass.l) return false;
        if (b != someClass.b) return false;
        if (!name.equals(someClass.name)) return false;
        if (getI1() != null ? !getI1().equals(someClass.getI1()) : someClass.getI1() != null) return false;
        if (getD1() != null ? !getD1().equals(someClass.getD1()) : someClass.getD1() != null) return false;
        return getS1() != null ? getS1().equals(someClass.getS1()) : someClass.getS1() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + (getI1() != null ? getI1().hashCode() : 0);
        result = 31 * result + (getD1() != null ? getD1().hashCode() : 0);
        result = 31 * result + (getS1() != null ? getS1().hashCode() : 0);
        result = 31 * result + i;
        temp = Double.doubleToLongBits(d);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (l ^ (l >>> 32));
        result = 31 * result + (b ? 1 : 0);
        return result;
    }

    public int getHashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return name + " {" +
                " i1=" + i1 +
                " d1=" + d1 +
                " s1='" + s1 + '\'' +
                " }" +
        "\nhashCode()=" + this.hashCode() +
                "\ndefault hashCode()=" + this.getHashCode() + "\n";
    }



    public static void main(String... args) {

        SomeClass someClass1 = new SomeClass("someClass1");
        someClass1.setI1(2).setD1(1.0).setS1("abc");

        SomeClass someClass2 = new SomeClass("someClass1");
        someClass2.setI1(1).setD1(1.0).setS1("abc");

        System.out.println(someClass1.toString());
        System.out.println(someClass2.toString());

    }
}
