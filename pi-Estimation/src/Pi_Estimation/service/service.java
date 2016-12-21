/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pi_Estimation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import Pi_Estimation.ui.particle;
import Pi_Estimation.ui.table;
import javafx.scene.paint.Paint;

public class service {

    public List<particle> getParticles(int sampleSize, double radius, Canvas canvas,Circle circle) {
        List<particle> particles = new ArrayList<>();

        double rectWidth = 2 * radius;
        double rectHeight = 2 * radius;

        double upperRightX = canvas.getWidth() / 2 - rectWidth / 2;
        double upperRightY = canvas.getHeight() / 2 - rectHeight / 2;

        for (int i = 0; i < sampleSize; i++) {
            double aOnXAxis = upperRightX;
            double bOnXAxis = upperRightX + rectWidth;
   
            double x = aOnXAxis + Math.random() * (bOnXAxis - aOnXAxis);

            double aOnYAxis = upperRightY;
            double bOnYAxis = upperRightY + rectHeight;
            
            double y = aOnYAxis + Math.random() * (bOnYAxis - aOnYAxis);

            
            
            particle p = new particle(Color.WHITE, x, y);
            
            
            particles.add(p);

        }
        
        if (!particles.isEmpty()) {
            for (particle particle1 : particles) {
                if (circle.contains(new Point2D(particle1.getX(), particle1.getY()))) {
                    
                    particle1.fillProperty().setValue(Color.RED);
                }
            }
        }
        return particles;

    }

    public int getHitCount(List<particle> particles, Circle circle) {
        int hitCount = 0;
        if (!particles.isEmpty()) {
            for (particle particle1 : particles) {
                if (circle.contains(new Point2D(particle1.getX(), particle1.getY()))) {
                    hitCount++;
                }
            }
        }
        return hitCount;
    }

    public table getTableData(int replication, int sampleSize, int hitCount) {
        double pi = 4 * ((double)hitCount/sampleSize) ;
        String estimatePi = String.valueOf(pi);
        Double pii = Double.valueOf(estimatePi);
        
        Double bernoauliException = Math.PI / 4.0 ;
        Double firstTerm = ((1-bernoauliException)*(1-bernoauliException)) * hitCount;
        Double secondTerm = (sampleSize - hitCount) * ((0- bernoauliException) * (0- bernoauliException));
        
        Double variance = (firstTerm + secondTerm)/ (sampleSize-1);
        Double finalVariacne = variance * (16.0/ sampleSize);
        
        Double standartDev = Math.sqrt(finalVariacne);
        Double temp = (2.85 * standartDev) / Math.sqrt(sampleSize);
        Double clib = pii - temp;
        Double club = pii + temp ;
        
        
        return new table(replication, sampleSize, hitCount, pii, variance, finalVariacne, standartDev, clib, club);
    }
    
    public List<table> getTableData(Circle circle , Map<Integer,List<particle>> sampleMap)
    {
        List<table> tablet = null ;
        if(!sampleMap.isEmpty())
        {
            tablet= new ArrayList<>();
            int replication = 1 ;
            for (Map.Entry<Integer,List<particle>> entry : sampleMap.entrySet()) {
                int sample = entry.getKey();
                List<particle> particle = entry.getValue();
                int hitCount = getHitCount(particle, circle);
                table tableData = getTableData(replication++, sample, hitCount);
                if (tableData != null) {
                    tablet.add(tableData);
                    
                }
                
            }
            
        }   
        
    return tablet;
    }
}
