package br.com.miguelwolf.logeasy.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.miguelwolf.logeasy.R;
import br.com.miguelwolf.logeasy.adapter.TarefaAdapter;
import br.com.miguelwolf.logeasy.model.Tarefa;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TarefaSlideFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TarefaSlideFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TarefaSlideFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;
    private List<Tarefa> mList;

    public TarefaSlideFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TarefaSlideFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TarefaSlideFragment newInstance(String param1, String param2) {
        TarefaSlideFragment fragment = new TarefaSlideFragment();
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

        View view = inflater.inflate(R.layout.fragment_tarefa_slide, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.tarefa_slide_rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                TarefaAdapter adapter = (TarefaAdapter) mRecyclerView.getAdapter();

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Tarefa> listAux = getSetTarefaList(10);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }

            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mList = getSetTarefaList(10);
        TarefaAdapter adapter = new TarefaAdapter(getActivity(), mList );
        adapter.setmRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);

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

    /**
     * Método para geração de itens para a lista de pesquisa
     * @param qtd, utlizado para definir o tanto de elementos eu quero exibir, 5 itens, 9 itens, 25 itens.
     * @return
     */
    public List<Tarefa> getSetTarefaList(int qtd){

        int[] ids = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};

        int[] grau = new int[]{2,2,2,1,2,0,0,0,0,2,2,2,1,2,2,2,2,1,2,2,2,2,1,2,2};

        int[] fotos = new int[]{R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario};

        String[] descricao = new String[]{"Cliente JOÃO solicitou uma nova carga",
                "Motorista CARLOS concluiu entrega",
                "Motorista BETO saiu para entrega",
                "Administrador MIGUEL atribuiu nova tarefa para você",
                "Cliente SAMUEL solicitou uma nova carga",
                "Pedido 7123575 em separação",
                "Pedido 7123575 em trânsito",
                "Pedido 7123575 entregue",
                "Pedido 7123542 cancelado",
                "Cliente LUCAS solicitou uma nova carga",
                "Motorista ALBERTO concluiu entrega",
                "Motorista JESUS saiu para entrega",
                "Administrador SILVA atribuiu nova tarefa para você",
                "Cliente RENATO solicitou uma nova carga",
                "Motorista OSWALDO concluiu entrega",
                "Motorista RENAN saiu para entrega",
                "Administrador MARCELO atribuiu nova tarefa para você",
                "Cliente KLEBER solicitou uma nova carga",
                "Motorista RICARDO concluiu entrega",
                "Motorista CELSO saiu para entrega",
                "Administrador VITAS atribuiu nova tarefa para você",
                "Cliente MUTLEY solicitou uma nova carga",
                "Motorista ANA concluiu entrega",
                "Motorista OSÉIAS saiu para entrega",
                "Administrador ASDF atribuiu nova tarefa para você",
                "Cliente NEYMAR solicitou uma nova carga",};

        List<Tarefa> listAux = new ArrayList<>();

        for (int i = 0; i < qtd; i++) {
            Tarefa t = new Tarefa(ids[i % ids.length], fotos[i % fotos.length], descricao[i % descricao.length]);
            listAux.add(t);
        }

        return (listAux);

    }

    @Override
    public void onClickListener(View v, int position) {
        Toast.makeText(getActivity(), "Position: "+position, Toast.LENGTH_SHORT).show();

        PesquisaAdapter adapter = (PesquisaAdapter) mRecyclerView.getAdapter();

        FragmentTransaction fragmentPerfil = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentPerfil.replace(R.id.main_frame, new PerfilFragment());
        fragmentPerfil.addToBackStack("Back");
        fragmentPerfil.commit();

        //adapter.removeListItem(position);
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
