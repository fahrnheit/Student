package model;

public class BizStudent extends Student{
    private double acc;
    private double mkt;

    public BizStudent(String id, String fullName, Address address, double acc, double mkt) {
        super(id, fullName, address);
        this.acc = acc;
        this.mkt = mkt;
    }

    public double getAcc() {
        return acc;
    }

    public void setAcc(double acc) {
        this.acc = acc;
    }

    public double getMkt() {
        return mkt;
    }

    public void setMkt(double mkt) {
        this.mkt = mkt;
    }
    @Override
    public double calculateAvg(){
        return (mkt * 2  + acc) / 3;
    }

    @Override
    public String toString() {
        return "BizStudent\t" + super.getId() + "\t" + super.getFullName() + "\t" 
        + super.getAddress().getCountry() + "\t\t" + super.getAddress().getCity() + "\t\t" +
        super.getAddress().getDistrict() + "\t" + super.getAddress().getStreet() + "\t" +
        this.acc + "\t\t" + this.mkt;
    }  
}
