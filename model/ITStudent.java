package model;

public class ITStudent extends Student{
    private double java;
    private double css;

    public ITStudent(String id, String fullName, Address address, double java, double css) {
        super(id, fullName, address);
        this.java = java;
        this.css = css;
    }

    public double getJava() {
        return java;
    }

    public void setJava(double java) {
        this.java = java;
    }

    public double getCss() {
        return css;
    }

    public void setCss(double css) {
        this.css = css;
    }

    @Override
    public double calculateAvg(){
        return  (3 * java + css) / 4;
    }

    @Override
    public String toString() {
        return "ITStudent\t" + super.getId() + "\t" + super.getFullName() + "\t" 
        + super.getAddress().getCountry() + "\t\t" + super.getAddress().getCity() + "\t\t" +
        super.getAddress().getDistrict() + "\t" + super.getAddress().getStreet() + "\t" +
        this.java + "\t\t" + this.css;
    }
}
