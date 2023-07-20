public class Director extends Worker {
    public Director(String fullName, String post, String phoneNumber, int salary, int age) {
        super(fullName, post, phoneNumber, salary, age);
    }

    public static void changeSalary(Worker[] workers) {
        for (Worker worker : workers) {
            if ((worker.getAge() > 45) && !(worker instanceof Director)) {
                worker.setSalary(worker.getSalary() + 5000);
            }
        }
    }

    public static void changeSalary(Worker[] workers, int minAge, int extraPlus) {
        for (Worker worker : workers) {
            if ((worker.getAge() > minAge) && !(worker instanceof Director)) {
                worker.setSalary(worker.getSalary() + extraPlus);
            }
        }
    }


    public static void main(String[] args) {

        Worker[] workers = new Worker[5];

        Director d1 = new Director("Марина", "Бух", "234", 25, 90);
        Worker w2 = new Worker("Петя", "Бухгалтер", "234354", 20, 60);
        Worker w3 = new Worker("Марина", "Пекарь", "1235", 1, 55);
        Worker w4 = new Worker("Марина", "Учитель", "6878089", 21, 76);
        Worker w5 = new Worker("Марина", "Пекарь 2", "123123", 1, 67);
        workers[0] = d1;
        workers[1] = w2;
        workers[2] = w3;
        workers[3] = w4;
        workers[4] = w5;


        for (Worker worker : workers) {
            System.out.printf("До повышения!");
            System.out.println(worker);
        }

        changeSalary(workers);
        System.out.println();

        for (Worker worker : workers) {
            System.out.printf("После повышения!");
            System.out.println(worker);
        }
    }
}