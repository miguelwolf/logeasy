package br.com.miguelwolf.logeasy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.miguelwolf.logeasy.R;
import br.com.miguelwolf.logeasy.interfaces.RecyclerViewOnClickListenerHack;
import br.com.miguelwolf.logeasy.model.Pesquisa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder> {

    private Context context;
    private List<Pesquisa> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public TarefaAdapter(Context c, List<Pesquisa> l) {
        context = c;
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = mLayoutInflater.inflate(R.layout.item_tarefa, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (!mList.get(position).getNome().equals("notificacao")) {
            holder.tvNome.setText(mList.get(position).getNome());
            holder.ivUsuario.setImageResource(mList.get(position).getFoto());
            holder.clAlerta.setVisibility(View.GONE);

        } else {
            holder.tvNome.setText(context.getResources().getString(R.string.notificacao));
            holder.clAlerta.setVisibility(View.VISIBLE);
        }

        holder.tvDescricao.setText(mList.get(position).getIdentificacao());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addListItem(Pesquisa p, int position){
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
        private ConstraintLayout clAlerta;
        private TextView tvNome;
        private TextView tvDescricao;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivUsuario = itemView.findViewById(R.id.item_tarefa_ivUsuario);
            clAlerta = itemView.findViewById(R.id.item_tarefa_clAlert);
            tvNome = itemView.findViewById(R.id.item_tarefa_tvNome);
            tvDescricao = itemView.findViewById(R.id.item_tarefa_tvDescricao);

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
