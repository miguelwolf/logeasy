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
import br.com.miguelwolf.logeasy.model.Carro;

public class CaminhaoAdapter extends RecyclerView.Adapter<CaminhaoAdapter.MyViewHolder> {

    private List<Carro> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public CaminhaoAdapter(Context c, List<Carro> l) {
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = mLayoutInflater.inflate(R.layout.item_caminhao, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.ivFoto.setImageResource(mList.get(position).getFoto());
        holder.tvMarcaModelo.setText(mList.get(position).getMarca() + " - " + mList.get(position).getModelo());
        holder.tvDescricao.setText(mList.get(position).getPlaca());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addListItem(Carro c, int position){
        mList.add(c);
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

        private ImageView ivFoto;
        private TextView tvMarcaModelo;
        private TextView tvDescricao;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivFoto = itemView.findViewById(R.id.item_caminhao_iv_foto);
            tvMarcaModelo = itemView.findViewById(R.id.item_caminhao_tv_marca_modelo);
            tvDescricao = itemView.findViewById(R.id.item_caminhao_tv_descricao);

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
