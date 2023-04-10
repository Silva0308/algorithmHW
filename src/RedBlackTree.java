public class RedBlackTree {

    public static void main(String[] args) {

    }
    private Node root; //элемент с которого начинаем работу

    public boolean add(int value) {
        if (root != null) {
            boolean result = addNode(root, value);
            root = rebalnce(root);
            return result;
        } else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }

    /**
     * Метод добавления новых нод. Метод возвращает boolean потому что все значания в нашем дереве
     * должны быть уникальными
     *
     * @param node
     * @param value
     * @return
     */
    private boolean addNode(Node node, int value) {
        if (node.value == value) {
            return false;
        } else {
            if (node.value > value) {
                if (node.leftChild != null) {
                    //используется рекурсивные вызовы
                    boolean result = addNode(node.leftChild, value);
                    node.leftChild = rebalnce(node.leftChild);
                    return result;
                } else {
                    //если ноды не существует, то создаём ноду
                    node.leftChild = new Node();
                    node.leftChild.color = Color.RED;
                    node.leftChild.value = value;
                    return true;
                }
            } else {
                //всё аналогично для правой ноды
                if (node.rightChild != null) {
                    boolean result = addNode(node.rightChild, value);
                    node.rightChild = rebalnce(node.rightChild);
                    return result;
                } else {
                    node.rightChild = new Node();
                    node.rightChild.color = Color.RED;
                    node.rightChild.value = value;
                    return true;
                }
            }
        }
    }

    /**
     * Метод ребалансировки
     *
     * @param node
     * @return
     */
    private Node rebalnce(Node node) {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            //Если правый красный, а левый чёрный, то правый поворот
            if (result.rightChild != null && result.rightChild.color == Color.RED &&
                    (result.leftChild == null || result.leftChild.color == Color.BLACK)) {
                needRebalance = true;
                result = rightSwap(result);
            }
            //Если левый ребёнок красный и у него есть ещё свой ребёнок и он тоже красный,
            //Тогда левый поворот
            if (result.leftChild != null && result.leftChild.color == Color.RED &&
                    result.leftChild.leftChild == null || result.leftChild.leftChild.color == Color.RED) {
                needRebalance = true;
                result = leftSwap(result);
            }
            //Если и правый и левый имеют красный цвет, тогда производим смену цвета
            if (result.leftChild != null && result.leftChild.color == Color.RED &&
                    result.rightChild != null && result.rightChild.color == Color.RED) {
                needRebalance = true;
                result = leftSwap(result);
            }
        }
        while (needRebalance);
        return result;
    }

    /**
     * Правостороний перевод
     *
     * @param node
     * @return
     */
    private Node rightSwap(Node node) {
        Node rightChild = node.rightChild;
        //промежуточный элемент betweenChild для обмена
        Node betweenChild = rightChild.leftChild;
        rightChild.leftChild = node;
        node.rightChild = betweenChild;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }

    /**
     * Левосторонний перевод
     *
     * @param node
     * @return
     */
    private Node leftSwap(Node node) {
        Node leftChild = node.leftChild;
        //промежуточный элемент betweenChild для обмена
        Node betweenChild = leftChild.rightChild;
        leftChild.rightChild = node;
        node.leftChild = betweenChild;
        leftChild.color = node.color;
        node.color = Color.RED;
        return leftChild;
    }

    /**
     * Метод смены цвета ноды Дети становятся чёрными, а сама ноды становится красной
     *
     * @param node
     */
    private void colorSwap(Node node) {
        node.rightChild.color = Color.BLACK;
        node.leftChild.color = Color.BLACK;
        node.color = Color.RED;
    }

    private class Node {

        private int value;
        private Color color;
        private Node leftChild;
        private Node rightChild;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", color=" + color +
                    '}';
        }
    }

    private enum Color {
        RED, BLACK
    }
}