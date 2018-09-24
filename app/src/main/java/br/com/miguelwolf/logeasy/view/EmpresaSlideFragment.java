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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;

import br.com.miguelwolf.logeasy.R;
import br.com.miguelwolf.logeasy.adapter.TarefaAdapter;
import br.com.miguelwolf.logeasy.interfaces.RecyclerViewOnClickListenerHack;
import br.com.miguelwolf.logeasy.model.Pesquisa;
import br.com.miguelwolf.logeasy.utils.Constants;


public class EmpresaSlideFragment extends Fragment implements RecyclerViewOnClickListenerHack, SwipeRefreshLayout.OnRefreshListener {

    private AsyncTask asyncTarefas;

    private int ativ;

    private List<Pesquisa> mListTarefas = new ArrayList<>();
    private List<Pesquisa> mListTarefasJSON;

    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;

    private String msgErro;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private TarefaAdapter adapter;

    private View view;


    public EmpresaSlideFragment() {}


    public static EmpresaSlideFragment newInstance() {
        EmpresaSlideFragment fragment = new EmpresaSlideFragment();
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

        mRecyclerView = view.findViewById(R.id.tarefa_slide_rv_list);
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

                if (mListTarefas.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    List<Pesquisa> listAux = getSetTarefaList(10);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mListTarefas.size());
                    }
                }

            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);


        mSwipeRefreshLayout = view.findViewById(R.id.tarefa_slide_sr);
        mSwipeRefreshLayout.setOnRefreshListener(this);


        asyncTarefas = new CarregarItensTarefa(view).execute();

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
        asyncTarefas = new CarregarItensTarefa(view).execute();
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
    public List<Pesquisa> getSetTarefaList(int qtd){

        List<Pesquisa> listAux = new ArrayList<>();

        int posicaoPartida = 0;

        if (mListTarefas.size() != 0) {
            posicaoPartida = mListTarefas.size();
        }

        qtd += posicaoPartida;

        for (int i = posicaoPartida; i < qtd && i < mListTarefasJSON.size(); i++) {
            listAux.add(mListTarefasJSON.get(i));
        }

        return (listAux);

    }


    @Override
    public void onClickListener(View v, int position) {
        Toast.makeText(getActivity(), "Position: "+position, Toast.LENGTH_SHORT).show();

        adapter = (TarefaAdapter) mRecyclerView.getAdapter();

        FragmentTransaction fragmentPerfil = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentPerfil.replace(R.id.main_frame, new PerfilFragment());
        fragmentPerfil.addToBackStack(null);
        fragmentPerfil.commit();

        //adapter.removeListItem(position);
    }

    @Override
    public void onRefresh() {
        asyncTarefas = new CarregarItensTarefa(view).execute();
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }



    @SuppressLint("StaticFieldLeak")
    private class CarregarItensTarefa extends AsyncTask<Void, Void, Void> {

        private ProgressBar progressBar;
        private View v;

        public CarregarItensTarefa(View v) {
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

                carregarCarrinho();

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

                                mListTarefas = getSetTarefaList(15);

                                adapter = new TarefaAdapter(getActivity(), mListTarefas);
                                adapter.setmRecyclerViewOnClickListenerHack(EmpresaSlideFragment.this);


                                TextView tv = v.findViewById(R.id.tarefa_slide_tv_sem_tarefas);

                                if (mListTarefas.size() == 0)
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

        private void carregarCarrinho() {

            int[] ids = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};

            int[] grau = new int[]{2,2,2,1,2,0,0,0,0,2,2,2,1,2,2,2,2,1,2,2,2,2,1,2,2};

            int[] fotos = new int[]{R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario,R.drawable.usuario};

            String[] nome = new String[]{"João",
                    "Carlos",
                    "Beto",
                    "Miguel",
                    "Samuel",
                    "notificacao",
                    "Lucas",
                    "Alberto",
                    "Jesus",
                    "Silva",
                    "Renato",
                    "Oswaldo",
                    "notificacao",
                    "Renan",
                    "Marcelo",
                    "Kleber",
                    "Ricardo",
                    "Celso",
                    "Vitas",
                    "notificacao",
                    "Mutley",
                    "Ana",
                    "Oséias",
                    "notificacao",
                    "Neymar"};

            String[] descricao = new String[]{"Cliente JOÃO solicitou uma nova carga",
                    "Motorista CARLOS concluiu entrega",
                    "Motorista BETO saiu para entrega",
                    "Administrador MIGUEL atribuiu nova tarefa para você",
                    "Cliente SAMUEL solicitou uma nova carga",
                    "Pedido 7123575 em separação",
                    "Cliente LUCAS solicitou uma nova carga",
                    "Motorista ALBERTO concluiu entrega",
                    "Motorista JESUS saiu para entrega",
                    "Administrador SILVA atribuiu nova tarefa para você",
                    "Cliente RENATO solicitou uma nova carga",
                    "Motorista OSWALDO concluiu entrega",
                    "Pedido 7123575 entregue",
                    "Motorista RENAN saiu para entrega",
                    "Administrador MARCELO atribuiu nova tarefa para você",
                    "Cliente KLEBER solicitou uma nova carga",
                    "Motorista RICARDO concluiu entrega",
                    "Motorista CELSO saiu para entrega",
                    "Administrador VITAS atribuiu nova tarefa para você",
                    "Pedido 7123542 cancelado",
                    "Cliente MUTLEY solicitou uma nova carga",
                    "Motorista ANA concluiu entrega",
                    "Motorista OSÉIAS saiu para entrega",
                    "Pedido 7123575 em trânsito",
                    "Cliente NEYMAR solicitou uma nova carga"};

            mListTarefas = new ArrayList<>();
            mListTarefasJSON = new ArrayList<>();

            for (int i = 0; i < ids.length; i++) {
                Pesquisa p = new Pesquisa(ids[i % ids.length], fotos[i % fotos.length], nome[i % nome.length], descricao[i % descricao.length]);
                mListTarefasJSON.add(p);
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

                    asyncTarefas = new CarregarItensTarefa(v).execute();

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
                    asyncTarefas = new CarregarItensTarefa(v).execute();

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
