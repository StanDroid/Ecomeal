package de.ecomeal.interfaces;

import android.support.v4.app.Fragment;

/**
 * Created by LS on 26.05.2016.
 */
public interface ChangeFragmentListener {

    void shouldReplace(Fragment fragment, boolean addToBackStack);
}
