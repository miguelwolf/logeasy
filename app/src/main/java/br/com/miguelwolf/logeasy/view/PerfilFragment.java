package br.com.miguelwolf.logeasy.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import br.com.miguelwolf.logeasy.DAO.PessoaDAO;
import br.com.miguelwolf.logeasy.R;
import br.com.miguelwolf.logeasy.utils.CircleTransform;
import br.com.miguelwolf.logeasy.utils.Preferences;
import br.com.miguelwolf.logeasy.model.Pessoa;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PerfilFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ImageView ivPerfil;
    private ImageView ivCarro;

    private Button btnEditarPerfil;
    private Button btnAtribuirPermissao;
    private Button btnAtribuirCarro;

    private RelativeLayout rlDados;
    private RelativeLayout rlCarro;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Pessoa pessoa;
    private PessoaDAO pessoaDAO;

    private OnFragmentInteractionListener mListener;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Miguel Wolf");
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        ivPerfil = view.findViewById(R.id.perfil_iv_foto);
        ivCarro = view.findViewById(R.id.perfil_iv_carro);

        btnEditarPerfil = view.findViewById(R.id.perfil_btn_editar);
        btnAtribuirCarro = view.findViewById(R.id.perfil_btn_carro);
        btnAtribuirPermissao = view.findViewById(R.id.perfil_btn_permissoes);

        rlDados = view.findViewById(R.id.perfil_rl_dados);
        rlCarro = view.findViewById(R.id.perfil_rl_carro);

        pessoa = new Pessoa();
        pessoaDAO = new PessoaDAO();

        pessoa.setCodigo(0);

        SharedPreferences preferences =
                getActivity().getSharedPreferences(Preferences.PREFERENCIA, MODE_PRIVATE);

        if (preferences.contains(Preferences.ID_PESSOA)) {
            if (preferences.getInt(Preferences.ID_PESSOA,0) == pessoa.getCodigo()) {
                btnEditarPerfil.setVisibility(View.VISIBLE);
            } else {
                btnEditarPerfil.setVisibility(View.GONE);
            }
        } else {
            getActivity().finish();
        }

        btnAtribuirPermissao.setVisibility(View.GONE);
        btnAtribuirCarro.setVisibility(View.GONE);
        rlDados.setVisibility(View.GONE);


        if (preferences.contains(Preferences.TIPO_PESSOA)) {

            if (preferences.getInt(Preferences.TIPO_PESSOA,0) == Pessoa.COMUM && preferences.getInt(Preferences.ID_PESSOA,0) != pessoa.getCodigo()) {
                rlDados.setVisibility(View.INVISIBLE);

            } else if (preferences.getInt(Preferences.TIPO_PESSOA,0) == Pessoa.COMUM && preferences.getInt(Preferences.ID_PESSOA,0) == pessoa.getCodigo()) {
                rlDados.setVisibility(View.VISIBLE);

            } else if (preferences.getInt(Preferences.TIPO_PESSOA,0) == Pessoa.CAMINHONEIRO && pessoa.getTipo() != Pessoa.ADMINISTRADOR ) {
                rlDados.setVisibility(View.VISIBLE);
                Picasso.get().load(R.drawable.caminhao).into(ivCarro);

            } else if (preferences.getInt(Preferences.TIPO_PESSOA,0) == Pessoa.CAMINHONEIRO && pessoa.getTipo() == Pessoa.ADMINISTRADOR ) {
                Picasso.get().load(R.drawable.caminhao).into(ivCarro);

            } else if (preferences.getInt(Preferences.TIPO_PESSOA,0) == Pessoa.ADMINISTRADOR) {
                rlDados.setVisibility(View.VISIBLE);
                btnAtribuirCarro.setVisibility(View.VISIBLE);
                btnAtribuirPermissao.setVisibility(View.VISIBLE);
                Picasso.get().load(R.drawable.caminhao).into(ivCarro);

            } else {
                getActivity().finish();
            }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
//        Toast.makeText(getActivity(), "Aqui", Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                getFragmentManager().popBackStack();
                if (getFragmentManager().getBackStackEntryCount() > 0 ){
                    getFragmentManager().popBackStack();
                } else {
                    getActivity().onBackPressed();
                }
                break;
            default:break;
        }
        return true;
    }

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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
