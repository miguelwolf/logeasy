package br.com.miguelwolf.logeasy.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

import br.com.miguelwolf.logeasy.adapter.PesquisaAdapter;
import br.com.miguelwolf.logeasy.R;
import br.com.miguelwolf.logeasy.interfaces.RecyclerViewOnClickListenerHack;
import br.com.miguelwolf.logeasy.model.Pesquisa;
import br.com.miguelwolf.logeasy.utils.Constants;


/**
 * Fragment utilizado para que o usuário possa pesquisar por motoristas, clientes e lugares.
 *
 * @author Miguel Wolf
 * @since 05/04/2018
 */
public class PesquisarFragment extends Fragment implements RecyclerViewOnClickListenerHack, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private AsyncTask asyncPesquisa;

    private EditText editTextPesquisa;

    private int ativ;

    private List<Pesquisa> mListPesquisa = new ArrayList<>();
    private List<Pesquisa> mListPesquisaJSON;

    private OnFragmentInteractionListener mListener;

    private PesquisaAdapter adapter;

    private RecyclerView mRecyclerView;

    private String msgErro;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private View view;


    public PesquisarFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pesquisar, container, false);

        view = v;

        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        }catch (NullPointerException npe){
            npe.printStackTrace();
        }

        v.findViewById(R.id.pesquisa_iv_procurar).setOnClickListener(this);

        editTextPesquisa = v.findViewById(R.id.pesquisa_et_pesquisar);

        mRecyclerView = v.findViewById(R.id.pesquisa_rv_list);
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

                if (mListPesquisa.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Pesquisa> listAux = getSetPesquisaList(15);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mListPesquisa.size());
                    }
                }

            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);


        mSwipeRefreshLayout = v.findViewById(R.id.pesquisa_slide_sr);
        mSwipeRefreshLayout.setOnRefreshListener(this);


        asyncPesquisa = new CarregarItensPesquisa(v, "").execute();

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

        List<Pesquisa> listAux = new ArrayList<>();

        int posicaoPartida = 0;

        if (mListPesquisa.size() != 0) {
            posicaoPartida = mListPesquisa.size();
        }

        qtd += posicaoPartida;

        for (int i = posicaoPartida; i < qtd && i < mListPesquisaJSON.size(); i++) {
            listAux.add(mListPesquisaJSON.get(i));
        }

        return (listAux);

    }



    @Override
    public void onClickListener(View v, int position) {
        Toast.makeText(getActivity(), "Position: "+position, Toast.LENGTH_SHORT).show();

        PesquisaAdapter adapter = (PesquisaAdapter) mRecyclerView.getAdapter();


        FragmentTransaction fragmentPerfil = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentPerfil.replace(R.id.main_frame, new PerfilFragment());
        fragmentPerfil.addToBackStack(null);
        fragmentPerfil.commit();

        //adapter.removeListItem(position);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.pesquisa_iv_procurar){

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onRefresh() {
        asyncPesquisa = new CarregarItensPesquisa(view, editTextPesquisa.getText().toString()).execute();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.pesquisa_iv_procurar:
                asyncPesquisa = new CarregarItensPesquisa(v, editTextPesquisa.getText().toString()).execute();
                break;

            default:
                break;

        }

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


    @SuppressLint("StaticFieldLeak")
    private class CarregarItensPesquisa extends AsyncTask<Void, Void, Void> {

        private ProgressBar progressBar;
        private String pesquisa;
        private View v;

        public CarregarItensPesquisa(View v, String pesquisa) {
            this.pesquisa = pesquisa;
            this.v = v;
        }

        @Override
        protected void onPreExecute() {

            try {

                progressBar = v.findViewById(R.id.pesquisa_slide_pb);
                progressBar.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.INVISIBLE);

            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }

        }

        @Override
        protected Void doInBackground(Void... v) {

            try {

                carregarPesquisa(pesquisa);

            } catch (NullPointerException npe) {
                ativ = Constants.ERRO_APP;
                Log.e("Erro", "Erro interno: " + npe);
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {

            switch (ativ) {

                case Constants.SUCESSO:

                    try {
                        getActivity().runOnUiThread(() -> {

                            try {

                                mListPesquisa = getSetPesquisaList(15);

                                adapter = new PesquisaAdapter(getActivity(), mListPesquisa);
                                adapter.setmRecyclerViewOnClickListenerHack(PesquisarFragment.this);


                                TextView tv = v.findViewById(R.id.pesquisa_slide_tv_sem_tarefas);

                                if (mListPesquisa.size() == 0)
                                    tv.setVisibility(View.VISIBLE);
                                else
                                    tv.setVisibility(View.GONE);


                                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                                llm.setOrientation(LinearLayoutManager.VERTICAL);
                                mRecyclerView.setLayoutManager(llm);
                                mRecyclerView.setAdapter(adapter);
                                mRecyclerView.getAdapter().notifyDataSetChanged();

                                if (mSwipeRefreshLayout.isRefreshing()) {
                                    mSwipeRefreshLayout.setRefreshing(false);
                                }


                            } catch (NullPointerException | IndexOutOfBoundsException npe) {
                                ativ = Constants.ERRO_APP;
                                Log.e("Erro", "Erro interno: " + npe);
                                onPostExecute(null);
                            }

                        });
                    } catch (NullPointerException npe) {
                        npe.printStackTrace();
                    }

                    break;

                case Constants.ERRO_BANCO:
                    WrongData(msgErro);
                    break;

                case Constants.ERRO_JSON:
                    ProblemServerCarrinho(v);
                    break;

                case Constants.ERRO_INTERNET:
                    InternetCarrinho(v);
                    break;

                case Constants.ERRO_APP:
                    WrongData(getString(R.string.algo_inesperado));
                    break;

            }

            try {
                progressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        private void carregarPesquisa(String pesquisa) {

            int[] ids = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};

            int[] fotos = new int[]{R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario};

            String[] nome = new String[]{"Aguinaldo Parracho",
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


            mListPesquisa = new ArrayList<>();
            mListPesquisaJSON = new ArrayList<>();

            for (int i = 0; i < ids.length; i++) {

//                String nomeString = "."+nome[i % nome.length]+".";
//                String descricaoString = "."+descricao[i % descricao.length]+".";

//                if (pesquisa.matches(nomeString) || pesquisa.matches(descricaoString)) {
                    Pesquisa p = new Pesquisa(ids[i % ids.length], fotos[i % fotos.length], nome[i % nome.length], descricao[i % descricao.length]);
                    mListPesquisaJSON.add(p);
//                }
            }

            ativ = Constants.SUCESSO;

//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            String base64 = Base64Custom.codificarBase64(String.valueOf(timestamp.getTime()));
//            String pass = Base64Custom.codificarBase64(base64);
//
//            String resposta;
//
//            try {
//                RequestBody formBody = new FormBody.Builder()
//                        .add(Constants.lista_carrinho_param_carsessao, carsessao)
//                        .add(Constants.lista_carrinho_param_integracao, integracao)
//                        .add(Constants.lista_carrinho_param_emitente, emitente)
//                        .add(Constants.lista_carrinho_param_destinatario, destinatario)
//                        .add(Constants.lista_carrinho_param_tipo_envio, tipoEnvio)
//                        .add(Constants.lista_carrinho_param_tipo_endereco, tipoEndereco)
//                        .add(Constants.pass, pass)
//                        .build();
//
//                OkHttpClient client = new OkHttpClient();
//                Request request = new Request.Builder()
//                        .url(session.getGlobalURL() + Constants.lista_carrinho_wb)
//                        .post(formBody)
//                        .build();
//
//                Response response = client.newCall(request).execute();
//
//                String json = response.body().string();
//
//                try {
//
//                    JSONObject responseFull = new JSONObject(json);
//
//                    resposta = responseFull.getString("response");
//
//                    if (resposta.equals("true")) {
//
//                        mListCarrinho = new ArrayList<>();
//                        mListCarrinhoJSON = new ArrayList<>();
//                        JSONArray array = responseFull.getJSONArray(Constants.lista_carrinho_carrinho);
//
//                        Carrinho carrinho = new Carrinho();
//                        carrinho.setTotalPontos(responseFull.optString(Constants.lista_carrinho_total_pontos));
//                        carrinho.setTotal(responseFull.optString(Constants.lista_carrinho_total));
//                        carrinho.setTipoAtv(responseFull.optString(Constants.lista_carrinho_atvTipo));
//                        carrinho.setMsgAtivo(responseFull.optString(Constants.lista_carrinho_msgAtivo));
//
//                        for (int i = 0; i < array.length(); i++) {
//
//                            JSONObject obj = array.getJSONObject(i);
//
//                            Carrinho c = new Carrinho();
//                            c.setCodigoProduto(obj.optString(Constants.lista_carrinho_car_prod_codigo));
//                            c.setNomeProduto(obj.optString(Constants.lista_carrinho_car_prod_nome).replace("<br>", " "));
//                            c.setValorTotalProduto(obj.optString(Constants.lista_carrinho_car_prod_valor_tot));
//                            c.setValorUnitarioProduto(obj.optString(Constants.lista_carrinho_car_prod_valor_unt));
//                            c.setQuantidadeProduto(obj.optString(Constants.lista_carrinho_car_prod_qtd));
//                            c.setPontuacaoProduto(obj.optString(Constants.lista_carrinho_car_prod_pontuacao));
//                            c.setFoto(obj.optString(Constants.lista_carrinho_car_foto));
//                            c.setTotal(carrinho.getTotal());
//                            c.setTotalPontos(carrinho.getTotalPontos());
//                            c.setTipoAtv(carrinho.getTipoAtv());
//                            c.setMsgAtivo(carrinho.getMsgAtivo());
//
//                            mListCarrinhoJSON.add(c);
//                        }
//
//                        ativ = Constants.SUCESSO;
//
//
//                    } else if (resposta.equals("false")) {
//
//                        msgErro = responseFull.optString("msg");
//
//                        ativ = Constants.ERRO_BANCO;
//                    }
//
//                } catch (JSONException e) {
//                    ativ = Constants.ERRO_JSON;
//                    Log.e("ERRO", "Não Encontrou Nenhum JSON", e);
//                }
//
//            } catch (IOException e) {
//                ativ = Constants.ERRO_INTERNET;
//                e.printStackTrace();
//            }
        }
    }

    public void ProblemServerCarrinho(View v) {

        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle(getString(R.string.alerta));
        alertDialog.setMessage(getString(R.string.problema_conexao_novamente));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.tentar_novamente),
                (dialog, which) -> {
                    dialog.dismiss();

                    asyncPesquisa = new CarregarItensPesquisa(v, editTextPesquisa.getText().toString()).execute();

                });
        alertDialog.setCancelable(false);
        alertDialog.show();

    }


    public void InternetCarrinho(View v) {

        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle(getString(R.string.alerta));
        alertDialog.setMessage(getString(R.string.problema_conexao_novamente));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.tentar_novamente),
                (dialog, which) -> {
                    dialog.dismiss();
                    asyncPesquisa = new CarregarItensPesquisa(v, editTextPesquisa.getText().toString()).execute();

                });
        alertDialog.setCancelable(true);
        alertDialog.show();
    }


    public void WrongData(String msg) {

        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        FancyToast.makeText(getContext(), msg, FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
    }

}
