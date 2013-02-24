/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ncsu.csc510.project.utillayer;

/**
 *
 * @author svpendse1
 */
public class WeatherData {

    public String conditions, temperature;
    public String dewpoint, visibility;
    public String wind, skyconditions;
    
    public static class WeatherBuilder {
        String conditions, temperature;
        String dewpoint, visibility;
        String wind, skyconditions;

        public WeatherBuilder() {
            
        }   

        public WeatherBuilder conditions(String conditions) {
            this.conditions = conditions;
            return this;
        }

        public WeatherBuilder temperature(String temperature) {
            this.temperature = temperature;
            return this;
        }

        public WeatherBuilder dewpoint(String dewpoint) {
            this.dewpoint = dewpoint;
            return this;
        }

        public WeatherBuilder visibility(String visibility) {
            this.visibility = visibility;
            return this;
        }

        public WeatherBuilder wind(String wind) {
            this.wind = wind;
            return this;
        }

        public WeatherBuilder skyconditions(String skyconditions) {
            this.skyconditions = skyconditions;
            return this;
        }
        
        public WeatherData build() {
            return new WeatherData(this);
        }
    }
    
    private WeatherData(WeatherBuilder builder) {
        conditions = builder.conditions;
        temperature = builder.temperature;
        dewpoint = builder.dewpoint;
        visibility = builder.visibility;
        wind = builder.wind;
        skyconditions = builder.skyconditions;
    }
}
