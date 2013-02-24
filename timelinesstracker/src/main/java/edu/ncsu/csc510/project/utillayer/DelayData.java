/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc510.project.utillayer;

/**
 *
 * @author svpendse1
 */
public class DelayData {

    public String delay, trend, change;
    
    public static class DelayBuilder {

        String delay, trend, change;
        public DelayBuilder() {
            
        }   

        public DelayBuilder delay(String delay) {
            this.delay = delay;
            return this;
        }
       
        public DelayBuilder trend(String trend) {
            this.trend = trend;
            return this;
        }

        public DelayBuilder change(String change) {
            this.change = change;
            return this;
        }
        
        public DelayData build() {
            return new DelayData(this);
        }
    }
    
    private DelayData(DelayBuilder builder) {
        delay = builder.delay;
        trend = builder.trend;
        change = builder.change;
    }
}
