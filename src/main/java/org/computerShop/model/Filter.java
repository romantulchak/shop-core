package org.computerShop.model;

public class Filter {

    private String[] brands;
    private String[] cpus;


    public Filter(String[] brands, String[] cpus){
        this.brands = brands;
        this.cpus = cpus;
    }

    public String[] getBrands() {
        return brands;
    }

    public void setBrands(String[] brands) {
        this.brands = brands;
    }

    public String[] getCpus() {
        return cpus;
    }

    public void setCpus(String[] cpus) {
        this.cpus = cpus;
    }
}
