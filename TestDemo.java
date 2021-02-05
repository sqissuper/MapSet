public class TestDemo {
    static class Node {
        public int key;
        public int val;
        public  Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    public Node[] arr;
    public int size;

    public TestDemo() {
        this.arr = new Node[10];
    }


    public void push(int key,int val) {
        Node node = new Node(key,val);
        int index = key % arr.length;
        Node cur = arr[index];
        while(cur != null) {
            if(cur.key == key) {
                cur.val = val;
                return;
            }
            cur = cur.next;
        }
        node.next = arr[index];
        arr[index] = node;
        this.size++;

        if(loadFac() > 0.75) {
            resize();
        }
    }

    public double loadFac() {
        return this.size * 1.0 / arr.length;
    }
    public void resize() {
        Node[] newArr = new Node[2 * arr.length];
        for (int i = 0; i <arr.length ; i++) {
            Node cur = arr[i];
            while(cur != null) {
                Node curNext = cur.next;
                int index = cur.key %newArr.length;
                cur.next = newArr[index];
                newArr[index] = cur;
                cur = curNext;
            }
        }
        arr = newArr;
    }

    public int get(int key) {
        int index = key % arr.length;
        Node cur = arr[index];
        while(cur != null) {
            if(cur.key == key) {
                return cur.val;
            }
            cur = cur.next;
        }
        return -1;
    }

    public static void main(String[] args) {
        TestDemo td = new TestDemo();
        td.push(1,2);
        td.push(5,4);
        td.push(3,4);
        td.push(7,9);
        td.push(1,8);
        td.push(6,9);
        td.push(9,4);
        System.out.println(td.get(1));
    }
}
