package br.com.miguelwolf.logeasy.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.miguelwolf.logeasy.R;


public class TarefaDetalheFragment extends Fragment {

    private static final String ARG_PARAMCODIGO = "";

    private String mParamCodigo;

    private OnFragmentInteractionListener mListener;


    public TarefaDetalheFragment() {}


    public static TarefaDetalheFragment newInstance(String paramCodigo) {
        TarefaDetalheFragment fragment = new TarefaDetalheFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAMCODIGO, paramCodigo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamCodigo = getArguments().getString(ARG_PARAMCODIGO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tarefa_detalhe, container, false);



        return v;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
