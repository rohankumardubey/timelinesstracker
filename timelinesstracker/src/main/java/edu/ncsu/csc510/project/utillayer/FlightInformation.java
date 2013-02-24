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
public class FlightInformation {

    public String status;
    public String route, duration;
    public String equipment, ontimeRating;
    
    public static class FlightInformationBuilder {
        String status;
        String route, duration;
        String equipment, ontimeRating;

        public FlightInformationBuilder() {
            
        }   

        public FlightInformationBuilder status(String status) {
            this.status = status;
            return this;
        }

        public FlightInformationBuilder route(String route) {
            this.route = route;
            return this;
        }

        public FlightInformationBuilder duration(String duration) {
            this.duration = duration;
            return this;
        }

        public FlightInformationBuilder equipment(String equipment) {
            this.equipment = equipment;
            return this;
        }

        public FlightInformationBuilder ontimeRating(String ontimeRating) {
            this.ontimeRating = ontimeRating;
            return this;
        }

        public FlightInformation build() {
            return new FlightInformation(this);
        }
    }

    private FlightInformation(FlightInformationBuilder builder) {
        status = builder.status;
        route = builder.route;
        duration = builder.duration;
        equipment = builder.equipment;
        ontimeRating = builder.ontimeRating;
    }
}
