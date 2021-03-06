package br.com.miguelwolf.logeasy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.miguelwolf.logeasy.R;
import br.com.miguelwolf.logeasy.interfaces.RecyclerViewOnClickListenerHack;
import br.com.miguelwolf.logeasy.model.Pessoa;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.MyViewHolder> {

    private List<Pessoa> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public PessoaAdapter(Context c, List<Pessoa> l) {
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = mLayoutInflater.inflate(R.layout.item_pessoa, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.ivUsuario.setImageResource(mList.get(position).getFoto());
        holder.tvNome.setText(mList.get(position).getNome());
        holder.tvDescricao.setText(mList.get(position).getCarro().getMarca() + " - " + mList.get(position).getCarro().getModelo());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addListItem(Pessoa p, int position){
        mList.add(p);
        notifyItemInserted(position);
    }

    public void removeListItem(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public RecyclerViewOnClickListenerHack getmRecyclerViewOnClickListenerHack() {
        return mRecyclerViewOnClickListenerHack;
    }

    public void setmRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack) {
        this.mRecyclerViewOnClickListenerHack = mRecyclerViewOnClickListenerHack;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView ivUsuario;
        private TextView tvNome;
        private TextView tvDescricao;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivUsuario = itemView.findViewById(R.id.item_pessoa_iv_foto);
            tvNome = itemView.findViewById(R.id.item_pessoa_tv_marca_modelo);
            tvDescricao = itemView.findViewById(R.id.item_pessoa_tv_descricao);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onClickListener(view, getLayoutPosition());
            }
        }
    }
}
