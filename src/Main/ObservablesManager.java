/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Main;

import Drawables.Observable;
import Drawables.ObserverDrawableDecorator;


public class ObservablesManager {

    private Observable transObservable;
    private Observable scaleObservable;
    private Observable rotObservable;

    public ObservablesManager(Observable transObservable, Observable scaleObservable, Observable rotObservable) {
        this.transObservable = transObservable;
        this.scaleObservable = scaleObservable;
        this.rotObservable = rotObservable;
    }

    public ObservablesManager() {
        this.transObservable = new Observable();
        this.scaleObservable = new Observable();
        this.rotObservable = new Observable();
    }

    public void subscribeTrans(ObserverDrawableDecorator obs) {
        this.transObservable.subscribe(obs);
    }

    public void subscribeScale(ObserverDrawableDecorator obs) {
        this.scaleObservable.subscribe(obs);
    }

    public void subscribeRot(ObserverDrawableDecorator obs) {
        this.rotObservable.subscribe(obs);
    }

    public void unsubscribeTrans(ObserverDrawableDecorator obs) {
        this.transObservable.unsubscribe(obs);
    }

    public void unsubscribeScale(ObserverDrawableDecorator obs) {
        this.scaleObservable.unsubscribe(obs);
    }

    public void unsubscribeRot(ObserverDrawableDecorator obs) {
        this.rotObservable.unsubscribe(obs);
    }

    public void notifyTrans(Vec3... params) {
        this.transObservable.notifyAll(params);
    }

    public void notifyScale(Vec3... params) {
        this.scaleObservable.notifyAll(params);
    }

    public void notifyRot(Vec3... params) {
        this.rotObservable.notifyAll(params);
    }

}
