package com.limxtop.research.animator.base;

import android.view.View;

/**
 * the implement of subclass of AbstractButtonState all is same, up which we move to super
 * class.
 * Created by limxtop on 4/30/16.
 */
public class AbstracteButtonState implements ButtonState {

    @Override
    public void action(View view, int[] enables, int[] disables) {
        for (int enable : enables) {
            view.findViewById(enable).setEnabled(true);
        }

        for (int disable : disables) {
            view.findViewById(disable).setEnabled(false);
        }
    }
}
