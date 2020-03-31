package Human;

public class Student {
    private int id;
    private String department;
    private String family;
    private String name;
    private double averageCheck;


    public Student() {
    }

    public Student(int id, String department, String family, String name, double averageCheck) {
        this.id = id;
        this.department = department;
        this.family = family;
        this.name = name;
        this.averageCheck = averageCheck;
    }

    public Student(String department, String family, String name, double averageCheck) {
        this.department = department;
        this.family = family;
        this.name = name;
        this.averageCheck = averageCheck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAverageCheck() {
        return averageCheck;
    }

    public void setAverageCheck(double averageCheck) {
        this.averageCheck = averageCheck;
    }
}
