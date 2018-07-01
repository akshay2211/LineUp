package com.fxn.utilities

import android.os.Build
import android.support.annotation.RequiresApi
import android.transition.ChangeBounds
import android.transition.ChangeTransform
import android.transition.TransitionSet

/**
 * Created by akshay on 14/05/18.
 */

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
class DetailsTransition
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
constructor() : TransitionSet() {
    init {
        addTransition(ChangeBounds()).addTransition(ChangeTransform())
    }

}
