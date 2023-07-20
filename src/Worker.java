import java.util.Comparator;

public class Worker {
    private String fullName;
    private String post;
    private String phoneNumber;
    private int salary;
    private int age;

    public Worker(String fullName, String post, String phoneNumber, int salary, int age) {
        this.fullName = fullName;
        this.post = post;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "fullName='" + fullName + '\'' +
                ", post='" + post + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
    public static float averageAge(Worker[] workers) {
        int sum = 0;

        for (Worker worker : workers) {
            sum += worker.getAge();
        }
        return (float) sum / workers.length;
    }

}
