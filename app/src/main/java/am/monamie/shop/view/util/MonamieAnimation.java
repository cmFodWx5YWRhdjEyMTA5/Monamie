package am.monamie.shop.view.util;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MonamieAnimation {
    private static String TAG = MonamieAnimation.class.getName();
    public static void dialogShowAnimation(Context context, View view, int animPath){
        Animation dialogAnimation = AnimationUtils.loadAnimation(context, animPath);
        dialogAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(TAG, "onAnimationEnd: ");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i(TAG, "onAnimationRepeat: ");
            }
        });
        view.startAnimation(dialogAnimation);
    }
}
