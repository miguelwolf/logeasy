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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

import br.com.miguelwolf.logeasy.R;
import br.com.miguelwolf.logeasy.adapter.CaminhaoAdapter;
import br.com.miguelwolf.logeasy.interfaces.RecyclerViewOnClickListenerHack;
import br.com.miguelwolf.logeasy.model.Carro;
import br.com.miguelwolf.logeasy.utils.Constants;


public class EmpresaCaminhoesSlideFragment extends Fragment implements RecyclerViewOnClickListenerHack, SwipeRefreshLayout.OnRefreshListener {

    private AsyncTask asyncCaminhao;


    private Button btnAdicionar;


    private int ativ;


    private List<Carro> mListCaminhao = new ArrayList<>();
    private List<Carro> mListCaminhaoJSON;

    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;

    private String msgErro;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private CaminhaoAdapter adapterCaminhao;

    private View view;


    public EmpresaCaminhoesSlideFragment() {}


    public static EmpresaCaminhoesSlideFragment newInstance() {
        EmpresaCaminhoesSlideFragment fragment = new EmpresaCaminhoesSlideFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_empresa_slide, container, false);

        this.view = view;


        btnAdicionar = view.findViewById(R.id.tarefa_slide_btn);


        mRecyclerView = view.findViewById(R.id.tarefa_slide_rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        System.out.println("The RecyclerView is not scrolling");
                        break;

                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        System.out.println("Scrolling now");
                        btnAdicionar.animate().setDuration(150).translationY(120);
                        break;

                    case RecyclerView.SCROLL_STATE_SETTLING:
                        System.out.println("Scroll Settling");
                        break;

                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy < 0) {
                    btnAdicionar.animate().setDuration(250).translationY(0);
                }

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                CaminhaoAdapter adapter = (CaminhaoAdapter) mRecyclerView.getAdapter();

                if (mListCaminhao.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Carro> listAux = getSetCarroList(10);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mListCaminhao.size());
                    }
                }

            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);


        mSwipeRefreshLayout = view.findViewById(R.id.tarefa_slide_sr);
        mSwipeRefreshLayout.setOnRefreshListener(this);


        asyncCaminhao = new CarregarCaminhoes(view).execute();

        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        asyncCaminhao = new CarregarCaminhoes(view).execute();
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
    public List<Carro> getSetCarroList(int qtd){

        List<Carro> listAux = new ArrayList<>();

        int posicaoPartida = 0;

        if (mListCaminhao.size() != 0) {
            posicaoPartida = mListCaminhao.size();
        }

        qtd += posicaoPartida;

        for (int i = posicaoPartida; i < qtd && i < mListCaminhaoJSON.size(); i++) {
            listAux.add(mListCaminhaoJSON.get(i));
        }

        return (listAux);

    }


    /**
     * Método para geração de itens para a lista de pesquisa
     * @param qtd, utlizado para definir o tanto de elementos eu quero exibir, 5 itens, 9 itens, 25 itens.
     * @return
     */
    public List<Carro> getSetCaminhoesList(int qtd){

        List<Carro> listAux = new ArrayList<>();

        int posicaoPartida = 0;

        if (mListCaminhao.size() != 0) {
            posicaoPartida = mListCaminhao.size();
        }

        qtd += posicaoPartida;

        for (int i = posicaoPartida; i < qtd && i < mListCaminhaoJSON.size(); i++) {
            listAux.add(mListCaminhaoJSON.get(i));
        }

        return (listAux);

    }


    @Override
    public void onClickListener(View v, int position) {

        adapterCaminhao = (CaminhaoAdapter) mRecyclerView.getAdapter();

        FragmentTransaction fragmentCaminhao = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentCaminhao.replace(R.id.main_frame, new CarroFragment());
        fragmentCaminhao.addToBackStack(null);
        fragmentCaminhao.commit();

        //adapter.removeListItem(position);
    }

    @Override
    public void onRefresh() {
        asyncCaminhao = new CarregarCaminhoes(view).execute();
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }



    @SuppressLint("StaticFieldLeak")
    private class CarregarCaminhoes extends AsyncTask<Void, Void, Void> {

        private ProgressBar progressBar;
        private View v;

        public CarregarCaminhoes(View v) {
            this.v = v;
        }

        @Override
        protected void onPreExecute() {

            try {

                progressBar = v.findViewById(R.id.tarefa_slide_pb);
                progressBar.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.INVISIBLE);

            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }

        }

        @Override
        protected Void doInBackground(Void... v) {

            try {

                carregarCaminhoes();

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

                                mListCaminhao = getSetCaminhoesList(15);

                                adapterCaminhao = new CaminhaoAdapter(getContext(), mListCaminhao);
                                adapterCaminhao.setmRecyclerViewOnClickListenerHack(EmpresaCaminhoesSlideFragment.this);


                                TextView tv = v.findViewById(R.id.tarefa_slide_tv_sem_tarefas);

                                if (mListCaminhao.size() == 0)
                                    tv.setVisibility(View.VISIBLE);
                                else
                                    tv.setVisibility(View.GONE);


                                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                                llm.setOrientation(LinearLayoutManager.VERTICAL);
                                mRecyclerView.setLayoutManager(llm);
                                mRecyclerView.setAdapter(adapterCaminhao);
                                mRecyclerView.getAdapter().notifyDataSetChanged();

                                if (mSwipeRefreshLayout.isRefreshing()) {
                                    mSwipeRefreshLayout.setRefreshing(false);
                                }

                                btnAdicionar.animate().setDuration(150).translationY(0);


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
                    ProblemServerCarro(v);
                    break;

                case Constants.ERRO_INTERNET:
                    InternetCarro(v);
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

        private void carregarCaminhoes() {

            int[] ids = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};

            int[] fotos = new int[]{R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao,R.drawable.caminhao};

            String[] modelo = new String[]{"R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440",
                    "R440"};

            String[] marca = new String[]{"SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA",
                    "SCANIA"};

            String[] placa = new String[]{"XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852",
                    "XYZ-4852"};

            mListCaminhao = new ArrayList<>();
            mListCaminhaoJSON = new ArrayList<>();

            for (int i = 0; i < ids.length; i++) {
                Carro c = new Carro(ids[i % ids.length], fotos[i % fotos.length], marca[i % marca.length], modelo[i % modelo.length] , null, placa[i % placa.length], null, 0);
                mListCaminhaoJSON.add(c);
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



    public void ProblemServerCarro(View v) {

        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle(getString(R.string.alerta));
        alertDialog.setMessage(getString(R.string.problema_conexao_novamente));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.tentar_novamente),
                (dialog, which) -> {
                    dialog.dismiss();

                    asyncCaminhao = new CarregarCaminhoes(v).execute();

                });
        alertDialog.setCancelable(false);
        alertDialog.show();

    }


    public void InternetCarro(View v) {

        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle(getString(R.string.alerta));
        alertDialog.setMessage(getString(R.string.problema_conexao_novamente));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.tentar_novamente),
                (dialog, which) -> {
                    dialog.dismiss();
                    asyncCaminhao = new CarregarCaminhoes(v).execute();

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
