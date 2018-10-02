package br.com.miguelwolf.logeasy.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.miguelwolf.logeasy.DAO.PessoaDAO;
import br.com.miguelwolf.logeasy.R;
import br.com.miguelwolf.logeasy.utils.AppPrefs;
import br.com.miguelwolf.logeasy.utils.CircleTransform;
import br.com.miguelwolf.logeasy.utils.Constants;
import br.com.miguelwolf.logeasy.utils.Preferences;
import br.com.miguelwolf.logeasy.model.Pessoa;

import static android.content.Context.MODE_PRIVATE;



public class PerfilFragment extends Fragment {

    private AppPrefs session;

    private ImageView ivPerfil;
    private ImageView ivCarro;

    private Button btnEditarPerfil;
    private Button btnAtribuirPermissao;
    private Button btnAtribuirCarro;

    private RelativeLayout rlDados;
    private ConstraintLayout clCarro;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Pessoa pessoa;
    private PessoaDAO pessoaDAO;

    private OnFragmentInteractionListener mListener;

    public PerfilFragment() {
        // Required empty public constructor
    }


    public static PerfilFragment newInstance() {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new AppPrefs(getActivity());

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Miguel Wolf");
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();

            setHasOptionsMenu(true);

        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);


        /** ================================= TOOLBAR ================================= **/
        try {
            TextView tv = view.findViewById(R.id.toolbar_perfil_tv_descricao);
            Toolbar toolbar = view.findViewById(R.id.perfil_toolbar);
            toolbar.setTitle("");
            tv.setText("miguel_wolf");


            ImageView ivBtnOpcoes = toolbar.findViewById(R.id.toolbar_perfil_iv_opcoes);
            ivBtnOpcoes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    FragmentTransaction fragmentOpcoes = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentOpcoes.replace(R.id.main_frame, new OpcoesFragment());
                    fragmentOpcoes.addToBackStack(null);
                    fragmentOpcoes.commit();

                }
            });

            ImageView ivBtnVoltar = toolbar.findViewById(R.id.toolbar_perfil_iv_voltar);
            ivBtnVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    getFragmentManager().popBackStack();
                    if (getFragmentManager().getBackStackEntryCount() > 0) {
                        getFragmentManager().popBackStack();
                    } else {
                        getActivity().onBackPressed();
                    }

                }
            });


            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        /** ================================= TOOLBAR ================================= **/


        ivPerfil = view.findViewById(R.id.perfil_iv_foto);
        ivCarro = view.findViewById(R.id.perfil_iv_carro);

        btnEditarPerfil = view.findViewById(R.id.perfil_btn_editar);
        btnAtribuirCarro = view.findViewById(R.id.perfil_btn_carro);
        btnAtribuirPermissao = view.findViewById(R.id.perfil_btn_permissoes);

        rlDados = view.findViewById(R.id.perfil_rl_dados);
        clCarro = view.findViewById(R.id.perfil_cl_carro);

        pessoa = new Pessoa();
        pessoaDAO = new PessoaDAO();

        pessoa.setCodigo("0");



        if (session.getId().equals(pessoa.getCodigo())) {
            btnEditarPerfil.setVisibility(View.VISIBLE);
        } else {
            btnEditarPerfil.setVisibility(View.GONE);
        }

        btnAtribuirPermissao.setVisibility(View.GONE);
        btnAtribuirCarro.setVisibility(View.GONE);
        rlDados.setVisibility(View.GONE);



        if (session.getTipoPessoa() == Constants.CLIENTE && !session.getId().equals(pessoa.getCodigo())) {
            rlDados.setVisibility(View.INVISIBLE);

        } else if (session.getTipoPessoa() == Constants.CLIENTE && !session.getId().equals(pessoa.getCodigo())) {
            rlDados.setVisibility(View.VISIBLE);

        } else if (session.getTipoPessoa() == Constants.CAMINHONEIRO && pessoa.getTipo() != Constants.ADMINISTRADOR) {
            rlDados.setVisibility(View.VISIBLE);
            Picasso.get().load(R.drawable.caminhao).into(ivCarro);

        } else if (session.getTipoPessoa() == Constants.CAMINHONEIRO && pessoa.getTipo() == Constants.ADMINISTRADOR) {
            Picasso.get().load(R.drawable.caminhao).into(ivCarro);

        } else if (session.getTipoPessoa() == Constants.ADMINISTRADOR) {
            rlDados.setVisibility(View.VISIBLE);
            btnAtribuirCarro.setVisibility(View.VISIBLE);
            btnAtribuirPermissao.setVisibility(View.VISIBLE);
            Picasso.get().load(R.drawable.caminhao).into(ivCarro);

        } else {
            getActivity().finish();
        }

        Picasso.get().load(R.drawable.perfil).transform(new CircleTransform()).into(ivPerfil);

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        Toast.makeText(getActivity(), "aqui", Toast.LENGTH_SHORT).show();
//
//        switch (item.getItemId()) {
//            case android.R.id.home:
//
//                break;
//
//            case R.id.perfil_iv_opcoes:
//
//                break;
//
//            default:break;
//        }
//        return true;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
