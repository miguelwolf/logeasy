package br.com.miguelwolf.logeasy.interfaces;

import android.view.View;

/**
 * Interface responsável por interpretar os cliques no RecyclerView
 *
 * @author Miguel Wolf
 * @since 06/04/2018
 */
public interface RecyclerViewOnClickListenerHack {

    public void onClickListener(View v, int position);

}
