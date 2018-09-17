package br.com.miguelwolf.logeasy.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import br.com.miguelwolf.logeasy.view.LoginActivity;
import br.com.miguelwolf.logeasy.view.MainActivity;

public class AppPrefs {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
//    private final static String URL = "https://app-vo.grupohinode.com";

    //URL

//    public String getGlobalURL() {
//        return URL;
//    }

//    public String getBR() {
//        return BRASIL;
//    }

    //Salva as Infos

    @SuppressLint("CommitPrefEdits")
    public AppPrefs(Context context){
        //this.ctx = context;
        prefs = context.getSharedPreferences("VO_Hinode", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedin(boolean logggedin){
        editor.putBoolean("loggedInmode",logggedin);
        editor.commit();
    }

    public boolean loggedin(){
        return prefs.getBoolean("loggedInmode", false);
    }

    //Infomações Login
    private String nome, destinatario, rede_usuario, binario, titulo, tipoPessoa,
            rede_hierarquia, primeiroTID, segundoTID, bankID, bankIDV, photoID,
            contractID, passID, rede_usuario_principal, idioma, hinode_cd, intl_pts_min_dinamic, intl_cd, msgAtv;

    private int tipoAtiv, classificacao, qtdMsg, pais, ext, intl_pais, intl_cep, intl_mostra_numero, intl_disabled_endereco, intl_retirar_franquia, primeiroPedido, msgRede, graficoPg, bloqueadoCompra;

//    public String getNome() {
//        return prefs.getString(Constants.login_nome, nome);
//    }
//
//    public void setNome(String nome) {
//        editor.putString(Constants.login_nome, nome);
//        editor.commit();
//    }
//
//    public String getDestinatario() {
//        return prefs.getString(Constants.login_destinatario, destinatario);
//    }
//
//    public void setDestinatario(String destinatario) {
//        editor.putString(Constants.login_destinatario, destinatario);
//        editor.commit();
//    }
//
//    public String getTitulo() {
//        return prefs.getString(Constants.login_titulo, titulo);
//    }
//
//    public void setTitulo(String titulo) {
//        editor.putString(Constants.login_titulo, titulo);
//        editor.commit();
//    }
//
//    public String getRede_usuario() {
//        return prefs.getString(Constants.login_rede_usuario, rede_usuario);
//    }
//
//    public void setRede_usuario(String rede_usuario) {
//        editor.putString(Constants.login_rede_usuario, rede_usuario);
//        editor.commit();
//    }
//
//    public String getBinario() {
//        return prefs.getString(Constants.login_binario, binario);
//    }
//
//    public void setBinario(String binario) {
//        editor.putString(Constants.login_binario, binario);
//        editor.commit();
//    }
//
//    public String getTipoPessoa() {
//        return prefs.getString(Constants.login_tipoPessoa, tipoPessoa);
//    }
//
//    public void setTipoPessoa(String tipoPessoa) {
//        editor.putString(Constants.login_tipoPessoa, tipoPessoa);
//        editor.commit();
//    }
//
//    public String getRede_hierarquia() {
//        return prefs.getString(Constants.login_rede_hierarquia, rede_hierarquia);
//    }
//
//    public void setRede_hierarquia(String rede_hierarquia) {
//        editor.putString(Constants.login_rede_hierarquia, rede_hierarquia);
//        editor.commit();
//    }
//
//    public String getPrimeiroTID() {
//        return prefs.getString(Constants.titular_primeiroTID, primeiroTID);
//    }
//
//    public void setPrimeiroTID(String primeiroTID) {
//        editor.putString(Constants.titular_primeiroTID, primeiroTID);
//        editor.commit();
//    }
//
//    public String getSegundoTID() {
//        return prefs.getString(Constants.titular_segundoTID, segundoTID);
//    }
//
//    public void setSegundoTID(String segundoTID) {
//        editor.putString(Constants.titular_segundoTID, segundoTID);
//        editor.commit();
//    }
//
//    public String getBankID() {
//        return prefs.getString(Constants.titular_bankID, bankID);
//    }
//
//    public void setBankID(String bankID) {
//        editor.putString(Constants.titular_bankID, bankID);
//        editor.commit();
//    }
//
//    public String getBankIDV() {
//        return prefs.getString(Constants.titular_bankIDV, bankIDV);
//    }
//
//    public void setBankIDV(String bankIDV) {
//        editor.putString(Constants.titular_bankIDV, bankIDV);
//        editor.commit();
//    }
//
//    public String getPhotoID() {
//        return prefs.getString(Constants.titular_photoID, photoID);
//    }
//
//    public void setPhotoID(String photoID) {
//        editor.putString(Constants.titular_photoID, photoID);
//        editor.commit();
//    }
//
//    public String getContractID() {
//        return prefs.getString(Constants.titular_contractID, contractID);
//    }
//
//    public void setContractID(String contractID) {
//        editor.putString(Constants.titular_contractID, contractID);
//        editor.commit();
//    }
//
//    public String getPassID() {
//        return prefs.getString(Constants.titular_passID, passID);
//    }
//
//    public void setPassID(String passID) {
//        editor.putString(Constants.titular_passID, passID);
//        editor.commit();
//    }
//
//    public int getTipoAtiv() {
//        return prefs.getInt(Constants.login_tipoAtv, tipoAtiv);
//    }
//
//    public void setTipoAtiv(int tipoAtiv) {
//        editor.putInt(Constants.login_tipoAtv, tipoAtiv);
//        editor.commit();
//    }
//
//    public String getRede_usuario_principal() {
//        return prefs.getString(Constants.login_rede_usuario_principal, rede_usuario_principal);
//    }
//
//    public void setRede_usuario_principal(String rede_usuario_principal) {
//        editor.putString(Constants.login_rede_usuario_principal, rede_usuario_principal);
//        editor.commit();
//    }
//
//    public String getIdioma() {
//        return prefs.getString(Constants.login_idioma, idioma);
//    }
//
//    public void setIdioma(String idioma) {
//        editor.putString(Constants.login_idioma, idioma);
//        editor.commit();
//    }
//
//    public String getHinode_cd() {
//        return prefs.getString(Constants.login_hinode_cd, hinode_cd);
//    }
//
//    public void setHinode_cd(String hinode_cd) {
//        editor.putString(Constants.login_hinode_cd, hinode_cd);
//        editor.commit();
//    }
//
//    public String getIntl_pts_min_dinamic() {
//        return prefs.getString(Constants.login_intl_pts_min_dinamic, intl_pts_min_dinamic);
//    }
//
//    public void setIntl_pts_min_dinamic(String intl_pts_min_dinamic) {
//        editor.putString(Constants.login_intl_pts_min_dinamic, intl_pts_min_dinamic);
//        editor.commit();
//    }
//
//    public String getIntl_cd() {
//        return prefs.getString(Constants.login_intl_cd, intl_cd);
//    }
//
//    public void setIntl_cd(String intl_cd) {
//        editor.putString(Constants.login_intl_cd, intl_cd);
//        editor.commit();
//    }
//
//    public String getMsgAtv() {
//        return prefs.getString(Constants.login_msgAtv, msgAtv);
//    }
//
//    public void setMsgAtv(String msgAtv) {
//        editor.putString(Constants.login_msgAtv, msgAtv);
//        editor.commit();
//    }
//
//    public int getClassificacao() {
//        return prefs.getInt(Constants.login_classificacao, classificacao);
//    }
//
//    public void setClassificacao(int classificacao) {
//        editor.putInt(Constants.login_classificacao, classificacao);
//        editor.commit();
//    }
//
//    public int getQtdMsg() {
//        return prefs.getInt(Constants.login_qtdMsg, qtdMsg);
//    }
//
//    public void setQtdMsg(int qtdMsg) {
//        editor.putInt(Constants.login_qtdMsg, qtdMsg);
//        editor.commit();
//    }
//
//    public int getPais() {
//        return prefs.getInt(Constants.login_pais, pais);
//    }
//
//    public void setPais(int pais) {
//        editor.putInt(Constants.login_pais, pais);
//        editor.commit();
//    }
//
//    public int getExt() {
//        return prefs.getInt(Constants.login_ext, ext);
//    }
//
//    public void setExt(int ext) {
//        editor.putInt(Constants.login_ext, ext);
//        editor.commit();
//    }
//
//    public int getIntl_pais() {
//        return prefs.getInt(Constants.login_intl_pais, intl_pais);
//    }
//
//    public void setIntl_pais(int intl_pais) {
//        editor.putInt(Constants.login_intl_pais, intl_pais);
//        editor.commit();
//    }
//
//    public int getIntl_cep() {
//        return prefs.getInt(Constants.login_intl_cep, intl_cep);
//    }
//
//    public void setIntl_cep(int intl_cep) {
//        editor.putInt(Constants.login_intl_cep, intl_cep);
//        editor.commit();
//    }
//
//    public int getIntl_mostra_numero() {
//        return prefs.getInt(Constants.login_intl_mostra_numero, intl_mostra_numero);
//    }
//
//    public void setIntl_mostra_numero(int intl_mostra_numero) {
//        editor.putInt(Constants.login_intl_mostra_numero, intl_mostra_numero);
//        editor.commit();
//    }
//
//    public int getIntl_disabled_endereco() {
//        return prefs.getInt(Constants.login_intl_disabled_endereco, intl_disabled_endereco);
//    }
//
//    public void setIntl_disabled_endereco(int intl_disabled_endereco) {
//        editor.putInt(Constants.login_intl_disabled_endereco, intl_disabled_endereco);
//        editor.commit();
//    }
//
//    public int getIntl_retirar_franquia() {
//        return prefs.getInt(Constants.login_intl_retirar_franquia, intl_retirar_franquia);
//    }
//
//    public void setIntl_retirar_franquia(int intl_retirar_franquia) {
//        editor.putInt(Constants.login_intl_retirar_franquia, intl_retirar_franquia);
//        editor.commit();
//    }
//
//    public int getPrimeiroPedido() {
//        return prefs.getInt(Constants.login_primeiroPedido, primeiroPedido);
//    }
//
//    public void setPrimeiroPedido(int primeiroPedido) {
//        editor.putInt(Constants.login_primeiroPedido, primeiroPedido);
//        editor.commit();
//    }
//
//    public int getMsgRede() {
//        return prefs.getInt(Constants.login_msgRede, msgRede);
//    }
//
//    public void setMsgRede(int msgRede) {
//        editor.putInt(Constants.login_msgRede, msgRede);
//        editor.commit();
//    }
//
//    public int getGraficoPg() {
//        return prefs.getInt(Constants.login_graficoPg, graficoPg);
//    }
//
//    public void setGraficoPg(int graficoPg) {
//        editor.putInt(Constants.login_graficoPg, graficoPg);
//        editor.commit();
//    }
//
//    public int getBloqueadoCompra() {
//        return prefs.getInt(Constants.login_bloqueadoCompra, bloqueadoCompra);
//    }
//
//    public void setBloqueadoCompra(int bloqueadoCompra) {
//        editor.putInt(Constants.login_bloqueadoCompra, bloqueadoCompra);
//        editor.commit();
//    }
//
//    public void RestartRegister(Activity Actx){
//
//        setPrimeiroTID(null);
//        setSegundoTID(null);
//        setBankIDV(null);
//        setPhotoID(null);
//        setContractID(null);
//        setPassID(null);
//        setTipoAtiv(0);
//
//        if (loggedin()){
//            Actx.startActivity(new Intent(Actx, MainActivity.class).putExtra(MainActivity.NOVO_CADASTRO, 1)
//                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//        }else{
//            Actx.startActivity(new Intent(Actx, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//            setRede_usuario(null);
//        }
//    }
//
//    public void FinishRegister(Activity Actx, String id){
//
//        setPrimeiroTID(null);
//        setSegundoTID(null);
//        setBankIDV(null);
//        setPhotoID(null);
//        setContractID(null);
//        setPassID(null);
//        setTipoAtiv(0);
//
//        if (loggedin()){
//            Actx.startActivity(new Intent(Actx, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//        }else{
//            Actx.startActivity(new Intent(Actx, LoginActivity.class).putExtra("id", id).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//            setRede_usuario(null);
//        }
//
//    }
//
//
//    public void logOut(Activity Actx){
//
//        setLoggedin(false);
//
//        setNome(null);
//        setDestinatario(null);
//        setRede_usuario(null);
//        setBinario(null);
//        setTitulo(null);
//        setTipoPessoa(null);
//        setRede_hierarquia(null);
//        setPrimeiroTID(null);
//        setSegundoTID(null);
//        setBankID(null);
//        setBankIDV(null);
//        setPhotoID(null);
//        setContractID(null);
//        setPassID(null);
//        setRede_usuario_principal(null);
//        setIdioma(null);
//        setHinode_cd(null);
//        setIntl_pts_min_dinamic(null);
//        setIntl_cd(null);
//        setMsgAtv(null);
//
//        setTipoAtiv(0);
//        setClassificacao(0);
//        setQtdMsg(0);
//        setPais(0);
//        setExt(0);
//        setIntl_pais(0);
//        setIntl_cep(0);
//        setIntl_mostra_numero(0);
//        setIntl_disabled_endereco(0);
//        setIntl_retirar_franquia(0);
//        setPrimeiroPedido(0);
//        setMsgRede(0);
//        setGraficoPg(0);
//        setBloqueadoCompra(0);
//
//        Intent intent = new Intent(Actx, LoginActivity.class);
//        Actx.startActivity(intent);
//        Actx.finish();
//
//    }

}
