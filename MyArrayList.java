public class MyArrayList<E> implements MyList {
    public static final int num2 = 2; 
    public static final int num5 = 5;
    // create an array of type object that will serve as our array
    Object[] values;
    // initialize a type int length to the valid elements in values
    int length = 0; 

    //Constructors
    public MyArrayList() {
       this.values = new Object[num5];
       length = 0; 
       

    }

    public MyArrayList(int initialCapacity) {
        this.values = new Object[initialCapacity];
        length = 0;

        //throw exception for invalid capacity
        if (initialCapacity < 0){
            throw new NegativeArraySizeException("Capacity Can't Be Less Than 0."); 
        }
    }
    //Constructor that shallow copies array over to new array
    public MyArrayList(E[] arr){ 
        values = arr.clone();  
        for (int i = 0;  i < arr.length; i++) {
            if (arr[i] != null) {
                length++;
            }
        }
    }

    //START OF METHODS

    //expandCapacity method
    public void expandCapacity(int requiredCapacity) {

    

     //Throw exception to check if required capacity is less than inital capacity
     if(requiredCapacity < values.length) { 
         throw new IllegalArgumentException("Capacity Can't Be Less Than Initial Capacity"); 
     } 

    
   
    // If statements that check all possible scenarios for length of arrayList
    int prevCapacity = values.length;
    if(values.length != 0) {
         prevCapacity *= num2; 
    }  
    if (values.length == 0) {
        prevCapacity = num5;
    }  
    if (prevCapacity < requiredCapacity) {
        prevCapacity = requiredCapacity;  
    }
      
    //Create a new array list and loop through to copy over new elements
    Object[] newArr = new Object[prevCapacity];
      for (int i = 0; i < length; i++) {
        newArr[i] = values[i];
      }
      
      values = newArr; 
    }

    //getCapacity method 
    public int getCapacity(){
            return values.length; 
    }

    //insert method
    public void insert(int index, Object element){ 
        //Throw exception to make sure index isn't out of bounds
        if(index < 0 || index > values.length) {  
            throw new IndexOutOfBoundsException("Please enter valid index");
        }
        
        // Setting the object to the specified element
        //First check to see if there is enough space in the array
        if(length == values.length) {
            expandCapacity(values.length); 
        }

        /*For loop that starts from the end of the array and shifts each element 
        up to the given index to the right, in order to make space for new element. */
        for(int i = values.length - 1 ; i > index; i--) {
           values[i] = values[i - 1];
        }
            //Setting the element to that specific index, and increment length
            values[index] = element;
            length++;
        } 
    

    //append method
    public void append(Object element) {
        //Check to see if there is enough space in the array
        if(length == values.length) {
            expandCapacity(values.length); 
        }

        //Add an element to the end of the list, and increment length
        values[length] = element;
        length++;
    }

    //prepend method
    public void prepend(Object element) {
        //Check to see if there is enough space in the array
        if(length == values.length) {
            expandCapacity(values.length); 
        }

        //Shift everything in the array to the right by 1.
        for(int i = values.length - 1 ; i > 0; i--) {
            values[i] = values[i - 1];
         }
        //Add an element to the beginning of the list, and increment length
        values[0] = element;
        length++;
    }

    //get method
    //SuppressWarnings to hide warning 
    @SuppressWarnings("unchecked") 
    public E get(int index) { 

        //Throw Exception to make sure index isn't out of bounds
        if(index < 0 || index >= values.length) {
            throw new IndexOutOfBoundsException("Please enter a valid index");
        }
        //for loop that finds the element at specific index, and returns it
        for(int i = 0; i < values.length; i++) {
            if (i == index) {
                return (E) values[i];
            }
        }
        return null; // SHOULD YOU RETURN NULL IF THE ABOVE STATEMENT DOESNT END UP BEING TRUE?!!!!!!!!!!!!!!1

    }

    //set method
    //SuppressWarnings to hide warning 
    @SuppressWarnings("unchecked") 
    public E set(int index, Object element) {
        
         //Throw Exception to make sure index isn't out of bounds
         if(index < 0 || index >= values.length) {
            throw new IndexOutOfBoundsException("Please enter a valid index");
        }
        
        E old = (E) values[index]; 
        //For loop that sets specific element at the index given, and incrememnts length
        for (int i = 0; i < values.length; i++){
            if (i == index) {
                values[i] = element;
            }
        //Return the new array
        } return old; 
    }

    //remove method
    //SuppressWarnings to hide warning 
    @SuppressWarnings("unchecked") 
    public E remove(int index) {

        //Throw Exception to make sure index isn't out of bounds
        if(index < 0 || index >= values.length) {
            throw new IndexOutOfBoundsException("Please enter a valid index");
        }
        
        //For loop that removes element at the index given, decrements the array
        for(int i = 0; i < values.length; i++) {
            if(i == index) {
                values[i] = null;
                length --; 
            }
        } 
        //Return the new array
        return (E) values; 
    }   

    //size method 
    public int size(){
        return length; 
    }

    //rotate method 
    public void rotate(int index){

        //Throw Exception to make sure index isn't out of bounds
        if(index < 0 || index >= values.length) {
            throw new IndexOutOfBoundsException("Please enter a valid index");
        }

        //Rotate every element to the left by the index amount given
        //First if statement checks if the index is 0
        if (index == 0) {
            return;
        }
        //Create a new temporary array to store in values that come in before the index
        Object[] temp = new Object[values.length];
        /*First for loop transfers over the values from the old array into the new one,
         but only the ones that are before index. */
        for (int i = 0; i < index; i++){
            temp[i] = values[i];
        } 
        //Second for loop shifts everything remaining after index to the left
        for (int j = index; j < length; j++) {
            values[j - index] = values[j];
        }
        //Third for loop transfers the values that came before index to the end
        int count = 0;
        for (int i = length - index; i < length; i++ ) {
            values[i] = temp[count]; 
            count ++;
        }

    }

    //find method
    public int find(Object element){
        //for loop that finds the index that the specific element is in, and returns it
        for (int i = 0; i < values.length; i++){
            if(values[i] == element){        
                 return  i;
            } 
    } 
    //If element is not found, return -1. 
    return -1; 
}
    public static void main(String[] args) {
        
        
    }

    

}
