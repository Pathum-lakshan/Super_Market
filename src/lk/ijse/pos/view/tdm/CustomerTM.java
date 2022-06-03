package lk.ijse.pos.view.tdm;



public class CustomerTM implements Comparable<CustomerTM> {
    private String id;
    private String name;
    private String address;
    private String nic;
    private String PhoneNumber;

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public CustomerTM() {
    }

    public CustomerTM(String id, String name, String address, String nic, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.nic = nic;
        PhoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "CustomerTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                '}';
    }

    @Override
    public int compareTo(CustomerTM o) {
        return id.compareTo(o.getId());
    }
}
