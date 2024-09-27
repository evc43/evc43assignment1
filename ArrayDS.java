public class ArrayDS<T extends Comparable<? super T>> implements SequenceInterface<T>, ReorderInterface, Comparable<ArrayDS<T>> {
    
    public ArrayDS(ArrayDS<T> other){
         array = new Object[other.size];
        for (int i =0; i<array.length;i++ )
        {
            array[i]=other.array[i];
        }
        size = other.size;

    }
    private Object[] array;
    private int size=0;
    private void doubleCapacity(){
        Object[] tmp = new Object [array.length*2];
        for (int i =0; i<array.length;i++ )
        {
            tmp[i]=array[i];
        }
        array=tmp;
         }

    private boolean full(){
        if(size==array.length)
        {
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayDS(){
        array=new Object[1];

    }

    public void append(T item)
    {
        if (full()){
            doubleCapacity();

        }
        array[size]=item;
        size++;


    }

    public void prefix(T item)
    {
        if (size == array.length)
        {
            doubleCapacity();
        }

        for (int i = size-1; i>=0 ;  i--){
            array[i+1]=array[i];
        }

        array[0] = item;
        size++;

        
    }

    public void insert (T item, int position)
    {
        if(size == array.length)
        {
            doubleCapacity();
        }
        for (int i = size-1; i>=position ;  i--){
            array[i+1]=array[i];
        }

        array[position] = item;
        size++;
    }

    public T itemAt(int position)
    {
        return (T)array[position];

    }

    public boolean isEmpty()
    {
        return size==0;
    }


    public int size(){
        return size;

    }

    public T first(){
        return (T)array[0];
    }

    public T last(){
        return (T)array[size-1];
    }

    public T predecessor(T item){
        for(int i=1; i<size; i++){
            if (array[i].equals(item)){
                return (T)array[i-1];
            }

        }
        return null;

    }

    public int getFrequencyOf(T item){
        int counter=0;
        for(int i=1; i<size; i++){
            if (array[i].equals(item)){
                counter++;
                
            }

        }
        return counter;


    }

    public void clear(){
        while(!isEmpty()){
            deleteTail();
        }

    }

    public int lastOccurrenceOf(T item){
        int counter=-1;
        for(int i=1; i<size; i++){
            if (array[i].equals(item)){
                counter=i;
                
            }

        }
        return counter;

    }

    public T deleteHead(){
        Object delete=array[0];
        for (int i=0; i<size-1;i++)
        {
            array[i]=array[i+1];
            
        }
        size--;
        return (T)delete;
         

    }

    public T deleteTail(){
        if(isEmpty()){
            throw new EmptySequenceException("empty");
        }
        size--;
        return (T)array[size];

    }

    public boolean trim(int numItems){
        if(size<numItems){
            return false;
        
        }
        else{
            size=size-numItems;
            return true;
        }
    }

    public boolean cut(int start, int numItems){
     
        if(size-start<numItems){
            return false;
        }
        for(int i = start; i<this.size-numItems; i++){
            this.array[i] = array[i+numItems];
        }
        return true;

    }

    public SequenceInterface<T> slice(T item){
        ArrayDS<T> newArrayDS = new ArrayDS<T>();
        
        for(int i = 0; i<size; i++){
            if (this.itemAt(i).compareTo(item) <= 0) {
                newArrayDS.append(this.itemAt(i));
            }
        }

        return newArrayDS;
    }

    public SequenceInterface<T> slice(int start, int numItems){
        ArrayDS<T> newArrayDS = new ArrayDS<T>();
        
        for(int i = 0; i<numItems; i++){
            newArrayDS.append(this.itemAt(i+start));
        }

        return newArrayDS;
    }

   
    public void reverse(){
        Object[] newArray= new Object[array.length];
        for (int i=0; i<size; i++){
            newArray[size-i-1]=array[i];
        } 


    }

    public void rotateRight(){
        
        prefix(deleteTail());

    }

    public void rotateLeft(){
        append(deleteHead());

    }

    @Override
    public String toString() {
        if (array[0].toString() == null || isEmpty())  {
            return "";
        }
        String string = "";
        for (int i = 0; i < size; i++) {
            string = string.concat(array[i].toString());
        }
        return string;
    }

    @Override
    public int compareTo(ArrayDS<T> o) {
        if (o.size > this.size) {
            return -1;
        } else if (this.size > o.size) {
            return 1;
        } else {
            for (int i = 0; i < this.size; i++) {
                if (this.itemAt(i).compareTo(o.itemAt(i)) != 0) {
                    return this.itemAt(i).compareTo(o.itemAt(i));
                }
            }
            return 0;
        }
    }
}