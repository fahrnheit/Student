package model;
public abstract class Student{
    private String id;
    private String fullName;
    private Address address;

    public Student(String id, String fullName, Address address){
        this.id = id;
        this.fullName = fullName;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public abstract double calculateAvg();

    @Override
    public String toString() {
        return "Student [id=" + id + ", fullName=" + fullName + ", address=" + address + "]";
    }
}