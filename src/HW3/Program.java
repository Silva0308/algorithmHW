package HW3;

public class Program {
    public static void main(String[] args) {
        MyList list = new MyList();
        list.add(1);
        list.add(2);
        list.add(3);

        list.showList();
        list.revert();
        System.out.println();
        list.showList();
    }
}
