package es.demo.data;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
 
public class FakePricer {
    
    public static Map<Integer,Double> getPrices(double S0,double vol){
    	
        int numberOfDays = 1000; 
        double annualReturn = 0.20; 
        int interval = 1; 
        Random random = new Random();
        
        Map<Integer,Double> priceData = new HashMap<>();
        double a = (annualReturn - (0.5*Math.pow(vol, 2)))*interval*2/numberOfDays;
        double b = vol*Math.sqrt(1000*interval/numberOfDays);

        double lastPrice = S0;
        for (int t = interval; t <= numberOfDays; t += interval) {
            // This is our Geometric Brownian motion..
            lastPrice = lastPrice*Math.exp(a
                    + (b*random.nextGaussian()));
            priceData.put(t,lastPrice);
        }
    	
        return priceData;
    }
}

