/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Drawables;

import Main.Vec3;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private List<ObserverDrawableDecorator> observers = new ArrayList<>();

    public void subscribe(ObserverDrawableDecorator observer) {
        this.observers.add(observer);
    }

    public void notifyAll(Vec3 params) {
        for (ObserverDrawableDecorator observer : this.observers) {
            observer.update(params);
        }
    }
}
