package br.com.miguelwolf.logeasy.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.miguelwolf.logeasy.adapter.PesquisaAdapter;
import br.com.miguelwolf.logeasy.R;
import br.com.miguelwolf.logeasy.interfaces.RecyclerViewOnClickListenerHack;
import br.com.miguelwolf.logeasy.model.Pesquisa;


/**
 * Fragment utilizado para que o usuário possa pesquisar por motoristas, clientes e lugares.
 *
 * @author Miguel Wolf
 * @since 05/04/2018
 */
public class PesquisarFragment extends Fragment implements RecyclerViewOnClickListenerHack{

    private RecyclerView mRecyclerView;
    private List<Pesquisa> mList;

    private OnFragmentInteractionListener mListener;

    public PesquisarFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        View v = inflater.inflate(R.layout.fragment_pesquisar, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.pesquisar_rv_list);
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
                PesquisaAdapter adapter = (PesquisaAdapter) mRecyclerView.getAdapter();

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Pesquisa> listAux = getSetPesquisaList(10);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }

            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mList = getSetPesquisaList(10);
        PesquisaAdapter adapter = new PesquisaAdapter(getActivity(), mList );
        adapter.setmRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);

        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_pesquisa, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = MenuItemCompat.getActionProvider(searchItem);
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
    public List<Pesquisa> getSetPesquisaList(int qtd){
        
        int[] ids = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};

        int[] fotos = new int[]{R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario};

        String[] nomes = new String[]{"Aguinaldo Parracho",
                "Alípio Peralta",
                "Amandio Lamego",
                "Arachane Tristán",
                "Astolfo Rangel",
                "Basilio Hierro",
                "Capitolina Lins",
                "Conrado Menna",
                "Cristiano Almeyda",
                "Cândida Bulhosa",
                "Floripes Campello",
                "Flávio Mourato",
                "Flávio Tabosa",
                "Fábia Siebra",
                "Ismael Marreiro",
                "Josefa Beltrán",
                "Lurdes Santana",
                "Magda Baía",
                "Marli Domingues",
                "Maura Gomes",
                "Melinda Amaro",
                "Micael Mexia",
                "Micaela Godoi",
                "Olívia Muniz",
                "Simão Cordeiro"};

        String[] descricao = new String[]{"Ford 150","24250 - Volks",
                "8150 - Volks",
                "710 - Mercedes",
                "9150 - Volks",
                "13180 - Volks",
                "FH 460 - Volvo",
                "Atego 2425 - Mercedes",
                "Accelo 815 - Mercedes",
                "VM 260 - Volvo",
                "R440 - Scania",
                "Ford 150","24250 - Volks",
                "8150 - Volks",
                "710 - Mercedes",
                "9150 - Volks",
                "13180 - Volks",
                "FH 460 - Volvo",
                "Atego 2425 - Mercedes",
                "Accelo 815 - Mercedes",
                "VM 260 - Volvo",
                "R440 - Scania",
                "VM 260 - Volvo",
                "R440 - Scania",
                "Ford 150","24250 - Volks",
                "8150 - Volks",
                "710 - Mercedes",
                "9150 - Volks",
                "13180 - Volks",
                "FH 460 - Volvo"};

        List<Pesquisa> listAux = new ArrayList<>();

        for (int i = 0; i < qtd; i++) {
            Pesquisa p = new Pesquisa(ids[i % ids.length], fotos[i % fotos.length], nomes[i % nomes.length], descricao[i % descricao.length]);
            listAux.add(p);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.action_search){

            return true;
        }

        return super.onOptionsItemSelected(item);
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
