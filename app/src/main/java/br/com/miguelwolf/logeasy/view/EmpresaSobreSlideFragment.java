package br.com.miguelwolf.logeasy.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;

import br.com.miguelwolf.logeasy.R;
import br.com.miguelwolf.logeasy.adapter.TarefaAdapter;
import br.com.miguelwolf.logeasy.interfaces.RecyclerViewOnClickListenerHack;
import br.com.miguelwolf.logeasy.model.Pesquisa;
import br.com.miguelwolf.logeasy.utils.Constants;


public class EmpresaSobreSlideFragment extends Fragment  {

    private AsyncTask asyncTarefas;

    private int ativ;

    private OnFragmentInteractionListener mListener;

    private View view;


    public EmpresaSobreSlideFragment() {}


    public static EmpresaSobreSlideFragment newInstance() {
        EmpresaSobreSlideFragment fragment = new EmpresaSobreSlideFragment();
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

        View view = inflater.inflate(R.layout.fragment_empresa_sobre_slide, container, false);

        this.view = view;

//        asyncTarefas = new CarregarItensTarefa(view).execute();

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

            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }

        }

        @Override
        protected Void doInBackground(Void... v) {

            try {

                carregarEmpresaDados();

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

//                                mListTarefas = getSetTarefaList(15);
//
//                                adapter = new TarefaAdapter(getActivity(), mListTarefas);
//                                adapter.setmRecyclerViewOnClickListenerHack(EmpresaSobreSlideFragment.this);
//
//
//                                TextView tv = v.findViewById(R.id.tarefa_slide_tv_sem_tarefas);
//
//                                if (mListTarefas.size() == 0)
//                                    tv.setVisibility(View.VISIBLE);
//                                else
//                                    tv.setVisibility(View.GONE);
//
//
//                                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//                                llm.setOrientation(LinearLayoutManager.VERTICAL);
//                                mRecyclerView.setLayoutManager(llm);
//                                mRecyclerView.setAdapter(adapter);
//                                mRecyclerView.getAdapter().notifyDataSetChanged();
//
//                                if (mSwipeRefreshLayout.isRefreshing()) {
//                                    mSwipeRefreshLayout.setRefreshing(false);
//                                }


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
//                    WrongData(msgErro);
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
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        private void carregarEmpresaDados() {

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

        FancyToast.makeText(getContext(), msg, FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
    }
}
