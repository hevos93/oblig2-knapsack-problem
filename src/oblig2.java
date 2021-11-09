import java.util.Random;

public class oblig2 {
    public static void main(String[] args) {
        int length = 10; //the number of objects
        Random rnd = new Random(); //random object to create information
        Item[] items = new Item[length]; //custom object class for the required object with weight and cost


        for (int i = 0; i < items.length; i++) { //filling an array with objects with random costs and weights
            int rndWeight = rnd.nextInt(10) + 1; //weight between 1 and 10
            int rndCost = rnd.nextInt(41) + 10; //cost between 10 and 50

            Item myObj = new Item(rndWeight, rndCost, false); //creates object
            items[i] = myObj; //inserts into array
        }//for

//        for (int i = 0; i < items.length; i++) { //test print
//            System.out.println("Weight: " + items[i].weight + " Cost:" + items[i].cost);
//        }//for

        weightSort(items);
    }//method main

    public static void weightSort(Item items[]){
        //Sort objects by increasing weight
        int oldWeight;
        int index;
        int weight = 0;

        for (int i = 0; i< items.length;i++) {
           oldWeight = 11;//11 because no object is going to weigh more
           index = 0;
            for (int j = 0; j < items.length; j++) {
                int newWeight = items[j].weight;

                if (items[i].taken == false && oldWeight > newWeight) { //TODO fix this
                    System.out.println("New!: " + newWeight);
                    index = j;
                    oldWeight = newWeight;
                }//if
            }//for
            items[index].taken= true;
            weight=weight+oldWeight;
            System.out.println(weight);
        }
        for (int i = 0; i< items.length;i++)
            System.out.println(items[i].taken);
    }//method weightSort
}//class oblig2