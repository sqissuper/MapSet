import java.util.Arrays;
import java.util.Objects;

public class TestDemo1 <K,V> {
    static class Node<K,V> {
        public K key;
        public V val;
        public Node<K,V> next;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    public Node<K,V>[] arr;
    public int size;

    public TestDemo1() {
        this.arr = new Node[10];
    }

    @Override
    public String toString() {
        return "TestDemo1{" +
                "arr=" + Arrays.toString(arr) +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestDemo1<?, ?> testDemo1 = (TestDemo1<?, ?>) o;
        return size == testDemo1.size &&
                Arrays.equals(arr, testDemo1.arr);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(arr);
        return result;
    }

    public void push(K key, V val) {
        Node<K,V> node = new Node<K,V>(key,val);
        int hash = key.hashCode();
        int index = hash % arr.length;
        Node<K,V> cur = arr[index];
        while(cur != null) {
            if(cur.key.equals(key)) {
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
        Node<K,V>[] newArr = new Node[2 * arr.length];
        for (int i = 0; i < arr.length ; i++) {
            Node<K,V> cur = arr[i];
            while(cur != null) {
                Node<K,V> curNext = cur.next;
                int index = cur.hashCode() % newArr.length;
                cur.next = newArr[index];
                newArr[index] = cur;
                cur = curNext;
            }
        }
        arr = newArr;
    }
    public V get(K key) {
        int index = key.hashCode() % arr.length;
        Node<K,V> cur = arr[index];
        while(cur != null) {
            if(cur.key == key) {
                return cur.val;
            }
            cur = cur.next;
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
