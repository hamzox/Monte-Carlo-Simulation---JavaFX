/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pi_Estimation.ui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author hii
 */
public class table {
    
    private IntegerProperty replication = new SimpleIntegerProperty();
    private IntegerProperty sampleSize = new SimpleIntegerProperty();
    private IntegerProperty hitCount = new SimpleIntegerProperty();
    private DoubleProperty estimatePi = new SimpleDoubleProperty();
    private DoubleProperty variance = new SimpleDoubleProperty();
    private DoubleProperty finalVariance = new SimpleDoubleProperty();
    private DoubleProperty standardDev = new SimpleDoubleProperty();
    private DoubleProperty clib = new SimpleDoubleProperty();
    private DoubleProperty club = new SimpleDoubleProperty();

    public table() {
    }
    
    public table(int replication , int sampleSize , int hitCount , double estimatePi , double variance , double finalVariance , double standardVariance , 
            double clib, double club)
    {
        super();
        this.replication.set(replication);
        this.sampleSize.set(sampleSize);
        this.hitCount.set(hitCount);
        this.estimatePi.set(estimatePi);
        this.variance.set(variance);
        this.finalVariance.set(finalVariance);
        this.standardDev.set(standardVariance);
        this.clib.set(clib);
        this.club.set(club);
    }

    public int getReplication() {
        return replication.get();
    }

    public void setReplication(int replication) {
        this.replication.set(replication);
    }

    public int getSampleSize() {
        return sampleSize.get();
    }

    public void setSampleSize(int sampleSize) {
        this.sampleSize.set(sampleSize);
    }

    public int getHitCount() {
        return hitCount.get();
    }

    public void setHitCount(int hitCount) {
        this.hitCount.set(hitCount);
    }

    public double getEstimatePi() {
        return estimatePi.get();
    }

    public void setEstimatePi(double estimatePi) {
        this.estimatePi.set(estimatePi);
    }

    public double getVariance() {
        return variance.get();
    }

    public void setVariance(double variance) {
        this.variance.set(variance);
    }

    public double getFinalVariance() {
        return finalVariance.get();
    }

    public void setFinalVariance(double finalVariance) {
        this.finalVariance.set(finalVariance);
    }

    public double getStandardDev() {
        return standardDev.get();
    }

    public void setStandardDev(double standardDev) {
        this.standardDev.set(standardDev);
    }

    public double getClib() {
        return clib.get();
    }

    public void setClib(double clib) {
        this.clib.set(clib);
    }

    public double getClub() {
        return club.get();
    }

    public void setClub(double club) {
        this.club.set(club);
    }
    
    
    
}
