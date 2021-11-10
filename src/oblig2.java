import java.util.Random;

public class oblig2 {
    public static void main(String[] args) {
        int length = 10; //the number of objects
        int maxWeight = 30;
        Random rnd = new Random(); //random object to create information
        Item[] items = new Item[length]; //custom object class for the required object with weight and cost


        for (int i = 0; i < items.length; i++) { //filling an array with objects with random costs and weights
            int rndWeight = rnd.nextInt(10) + 1; //weight between 1 and 10
            int rndCost = rnd.nextInt(41) + 10; //cost between 10 and 50

            Item myObj = new Item(rndWeight, rndCost); //creates object
            items[i] = myObj; //inserts into array
        }//for

        weightSort(items, maxWeight);
    }//method main

    public static void weightSort(Item[] items, int maxWeight){
        //Sort objects by increasing weight
        int []taken = new int[items.length]; //creates a new array
        for(int i = 0; i< items.length;i++) //fills the taken array with zeroes
            taken[i]=0;
        int oldWeight;
        int index;
        int weight = 0;

         do {
             oldWeight = 11;//11 because no object is going to weigh more
             index = 0;
             for (int j = 0; j < items.length; j++) {
                 //System.out.println("Round "+j);
                 int newWeight = items[j].weight;

                 if (oldWeight > newWeight && taken[j] == 0) { //finds the object with the lowest weight that is not taken
                     //System.out.println("New!: " + newWeight);
                     index = j;
                     oldWeight = newWeight; //updates with the new weight
                 }//if
             }//for
             weight = weight + oldWeight; //updates the total weight
             taken[index]=1; //marks the object as taken
             if (weight>maxWeight) { //checks if the weight is over the maxweight regarding the latest upadte
                 //System.out.println(weight);
                 weight = weight - oldWeight; //removes the latest weight
                 taken[index]=0; //marks as not taken
                 break; //exits the loop
             }//if
         }while(weight<maxWeight); //do and while
          System.out.println(weight);
    }//method weightSort
}//class oblig2