/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc510.project.utillayer;

import java.util.Date;

/**
 *
 * @author svpendse1
 */
public class FlightStatusData {
    public String status;
    public String schedDeparture, schedArrival;
    public String actDeparture, estArrival;
    public String gate, baggageClaim;

    public static class FlightStatusBuilder {
        String status;
        String schedDeparture, schedArrival;
        String actDeparture, estArrival;
        String arrivalGate, baggageClaim;

        public FlightStatusBuilder() {
            
        }   

        public FlightStatusBuilder status(String status) {
            this.status = status;
            return this;
        }
        public FlightStatusBuilder schedDeparture(String schedDeparture) {
            this.schedDeparture = schedDeparture;
            return this;
        }
    
        public FlightStatusBuilder schedArrival(String schedArrival) {
            this.schedArrival = schedArrival;
            return this;
        }

        public FlightStatusBuilder actDeparture(String actDeparture) {
            this.actDeparture = actDeparture;
            return this;
        }   

        public FlightStatusBuilder estArrival(String estArrival) {
            this.estArrival = estArrival;
            return this;
        }

        public FlightStatusBuilder gate(String arrivalGate) {
            this.arrivalGate = arrivalGate;
            return this;
        }

        public FlightStatusBuilder baggageClaim(String baggageClaim) {
            this.baggageClaim = baggageClaim;
            return this;
        }

        public FlightStatusData build() {
            return new FlightStatusData(this);
        }
    }

    private FlightStatusData(FlightStatusBuilder builder) {
        status = builder.status;
        schedDeparture = builder.schedDeparture;
        schedArrival = builder.schedArrival;
        actDeparture = builder.actDeparture;
        estArrival = builder.estArrival;
        gate = builder.arrivalGate;
        baggageClaim = builder.baggageClaim;
    }
}
