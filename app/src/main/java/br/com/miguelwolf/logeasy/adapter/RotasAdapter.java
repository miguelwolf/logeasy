package br.com.miguelwolf.logeasy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.miguelwolf.logeasy.R;
import br.com.miguelwolf.logeasy.Utils.CircleTransform;
import br.com.miguelwolf.logeasy.interfaces.RecyclerViewOnClickListenerHack;
import br.com.miguelwolf.logeasy.model.Rota;

public class RotasAdapter extends RecyclerView.Adapter<RotasAdapter.MyViewHolder> {

    private List<Rota> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public RotasAdapter(Context c, List<Rota> l) {
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = mLayoutInflater.inflate(R.layout.item_rota, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(mList.get(position).getFoto()).transform(new CircleTransform()).into(holder.ivUsuario);
        holder.tvNome.setText(mList.get(position).getNome());
        holder.tvDestino.setText(mList.get(position).getDestino());
        holder.tvPlaca.setText(mList.get(position).getPlaca());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addListItem(Rota r, int position){
        mList.add(r);
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
        private TextView tvDestino;
        private TextView tvPlaca;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivUsuario = itemView.findViewById(R.id.item_rota_iv_perfil);
            tvNome = itemView.findViewById(R.id.item_rota_tv_nome);
            tvDestino = itemView.findViewById(R.id.item_rota_tv_destino);
            tvPlaca = itemView.findViewById(R.id.item_rota_tv_placa);

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
