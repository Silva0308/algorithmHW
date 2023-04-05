package HW3;

public class MyList {
    Node head;
    Node tall;
    // Метод добавления
    public void add(int value){
        Node node = new Node();
        node.value = value;
        if (head == null){
            head = node;
        } else {
            tall.next = node;
            node.previews = tall;
        }
        tall = node;
    }
    // Метод для разворота двунаправленного массива
    public void revert(){
        Node currentNode = head;
        while (currentNode != null){
            Node next = currentNode.next;
            Node previews = currentNode.previews;
            currentNode.next = previews;
            currentNode.previews = next;
            if (previews == null){
                tall = currentNode;
            }
            if (next == null){
                head = currentNode;
            }
            currentNode = next;
        }
    }
    // Метод поиска элемента связного списка по value
    public Node find(int value){
        Node currentNode = head;
        while (currentNode != null){
            if (currentNode.value == value){
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }
    // Метод для отображения элементов связного списка
    public void showList(){
        Node currentNode = head;
        while (currentNode != null){
            System.out.print(currentNode.value + " ");
            currentNode = currentNode.next;
        }
    }

    public class Node{
        int value;
        Node next;
        Node previews;
    }
}
