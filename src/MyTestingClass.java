public class MyTestingClass {
    private int id;
    public MyTestingClass(int id){
        this.id = id;
    }
    @Override
    public int hashCode(){
        return id;
    }
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>();
        table.addRandomElements(10000);
        table.printBucketSizes();

    }
}
