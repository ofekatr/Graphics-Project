/* This file was created by: Ofek Atar*/
/*
 Ofek Atar 209373802
*/
package Main;

import Drawables.ObserverDrawableDecorator;
import InputHandlers.CameraInputHandler;

public class CameraInputHandlerObservablesManager extends CameraInputHandler {

    private ObservablesManager observablesManager = new ObservablesManager();

    public CameraInputHandlerObservablesManager(Camera camera) {
        super(camera);
    }

    public void subscribeTrans(ObserverDrawableDecorator obs) {
        this.observablesManager.subscribeTrans(obs);
    }

    public void subscribeScale(ObserverDrawableDecorator obs) {
        this.observablesManager.subscribeScale(obs);
    }

    public void subscribeRot(ObserverDrawableDecorator obs) {
        this.observablesManager.subscribeRot(obs);
    }

    public void unsubscribeTrans(ObserverDrawableDecorator obs) {
        this.observablesManager.unsubscribeTrans(obs);
    }

    public void unsubscribeScale(ObserverDrawableDecorator obs) {
        this.observablesManager.unsubscribeScale(obs);
    }

    public void unsubscribeRot(ObserverDrawableDecorator obs) {
        this.observablesManager.unsubscribeRot(obs);
    }

    public void notifyTrans(Vec3... params) {
        this.observablesManager.notifyTrans(params);
    }

    public void notifyScale(Vec3... params) {
        this.observablesManager.notifyScale(params);
    }

    public void notifyRot(Vec3... params) {
        this.observablesManager.notifyRot(params);
    }


}
