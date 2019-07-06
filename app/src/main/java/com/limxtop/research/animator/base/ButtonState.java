package com.limxtop.research.animator.base;

import android.view.View;

/**
 * Created by limxtop on 4/30/16.
 */
public interface ButtonState {

    /**
     * this version of ButtonState need to know the ids of buttons, which is not decouple completely.
     *
     * @param view the parent view of buttons
     */
//    void action(View view);

    /**
     *
     * @param view the parent view of buttons
     * @param enables the ids of buttons to enable
     * @param disables the ids of buttons to disable
     */
    void action(View view, int[] enables, int[] disables);
}
