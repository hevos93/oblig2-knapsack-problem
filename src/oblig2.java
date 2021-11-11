import java.util.Random;
import java.util.Scanner;

public class oblig2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); //scanner object to get user input
        Random rnd = new Random(); //random object to create information

        System.out.println("How many objects do you wish to use?");
        int length = input.nextInt(); //the number of objects
        int maxWeight = 30;

        Item[] items = new Item[length]; //custom object class for the required object with weight and cost

        for (int i = 0; i < items.length; i++) { //filling an array with objects with random costs and weights
            int rndWeight = rnd.nextInt(10) + 1; //weight between 1 and 10
            int rndCost = rnd.nextInt(41) + 10; //cost between 10 and 50

            Item myObj = new Item(rndWeight, rndCost); //creates object
            items[i] = myObj; //inserts into array
        }//for

        int[] taken = weightSort(items, maxWeight); //weightSort method
        greedyImprovement(items, maxWeight, taken); //greedyImprovement method
    }//method main

    public static int[] weightSort(Item[] items, int maxWeight){
        //Sort objects by increasing weight
        long startTime = System.nanoTime();//creating a start timestamp
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
             if (weight>maxWeight) { //checks if the weight is over the maxweight regarding the latest update
                 //System.out.println(weight);
                 weight = weight - oldWeight; //removes the latest weight
                 taken[index]=0; //marks as not taken
                 break; //exits the loop
             }//if
         }while(weight<maxWeight); //do and while
          //System.out.println(weight);
        //System.out.println(costCalculation(taken, items));

        long endTime = System.nanoTime();//creating the end
        long ns = endTime-startTime;
        long ms = ns/100000;
        long s = ns/1000000000;

        System.out.println("WEIGHT SORT:" +
                "\n\tCost: "+costCalculation(taken,items)+
                "\n\tWeight: "+weightCalculation(taken,items)+
                " \n\tTotal Time: "+ns+"ns, or "+ms+"ms, or "+s+"s.");
          return taken;
    }//method weightSort

    public static void greedyImprovement(Item[] items, int maxWeight, int[] taken){
        //Greedy improvement for weight solution
        long startTime = System.nanoTime();//creating a start timestamp
        int oldCost = costCalculation(taken, items); //calculates the cost for the knapsack
        Random rnd = new Random(); //object to create random numbers

        int tries = items.length*10; //how many tries to do

        for (int i = 0; i<tries;i++) {
            int index = rnd.nextInt(taken.length); //new random index
            while (taken[index] == 0) //checks that the index is for an object inside the knapsack
                index = rnd.nextInt(taken.length);

            int index2 = rnd.nextInt(taken.length); //new random index
            while (taken[index2] == 1) //checks that the index is for an object not inside of the knapsack
                index2 = rnd.nextInt(taken.length);

            taken[index] = 0; //the old switcharoo
            taken[index2] = 1;

            int newCost = costCalculation(taken, items); //calculates cost for the new contents
            int newWeight = weightCalculation(taken, items); //calculates weight for the new contents

            if (newCost > oldCost && newWeight <= maxWeight) { //checks if the newcost is more and the weight is no more than maxweight
                //System.out.println("New solution found!");
                oldCost=newCost;
            }
            else {
                //System.out.println("Reverting solution"); //reverts the solution if the check fails
                taken[index] = 1;
                taken[index2] = 0;
            }//else
        }//for

        long endTime = System.nanoTime();//creating the end timestamp
        long ns = endTime-startTime;
        long ms = ns/100000;
        long s = ns/1000000000;

        System.out.println("GREEDY IMPROVEMENT:" +
                "\n\tCost: "+costCalculation(taken,items)+
                "\n\tWeight: "+weightCalculation(taken,items)+
                " \n\tTotal Time: "+ns+"ns, or "+ms+"ms, or "+s+"s.");
    }//method greedyImprovement


    public static int costCalculation(int[]taken, Item[] items){
        //calculates cost
        int cost = 0;
        for(int i = 0; i< taken.length;i++){
            if (taken[i]==1)
                cost = cost +items[i].cost;
        }
        return cost;
    }
    public static int weightCalculation(int[]taken, Item[] items){
        //calculates weight
        int weight = 0;
        for(int i = 0; i< taken.length;i++){
            if (taken[i]==1)
                weight = weight +items[i].weight;
        }
        return weight;
    }
}//class oblig2