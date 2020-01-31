package com.bisboguicompany.chispas.quinela.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.bisboguicompany.chispas.quinela.Models.Clientes;
import com.bisboguicompany.chispas.quinela.Models.Usuario;
import com.bisboguicompany.chispas.quinela.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorVentas extends BaseAdapter implements Filterable {

    LayoutInflater flater;
    List<Clientes> datos;
    Context context;
    private List<Clientes> dataFilter = null;
    private ItemFilter mFilter = new ItemFilter();

    public  AdaptadorVentas(Context contextS, List<Clientes> objetos){

        this.context = contextS;
        this.datos = objetos;
        flater = (LayoutInflater.from(contextS));

        dataFilter = objetos;
    }



    @Override
    public int getCount() {
        //return datos.size();
        return dataFilter.size();
    }

    @Override
    public Object getItem(int position) {
        //return datos.get(position);
        return dataFilter.get(position);
    }

    @Override
    public long getItemId(int position) {
        //return datos.get(position).getIdUser();
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= flater.inflate(R.layout.item_usuario, null);

        TextView apiario = (TextView) convertView.findViewById(R.id.txtNombre);
        TextView colmenas = (TextView) convertView.findViewById(R.id.txtUsuario);

        TextView grande = (TextView) convertView.findViewById(R.id.txtgrande);
        TextView chivita = (TextView) convertView.findViewById(R.id.txtchivita);



        Clientes datitos = dataFilter.get(position);

        apiario.setText("Nombre: "+datitos.getName());
        colmenas.setText("Usuario: " + datitos.getUsername());


        for (int i = 0 ; i < datitos.getList().size(); i++){
            if(datitos.getList().get(i).getTipo() == 1){
                chivita.setText("Creditos Chivita: " + String.valueOf(datitos.getList().get(i).getCantidadchica()));
            }else if(datitos.getList().get(i).getTipo() == 2){
                grande.setText("Creditos Grande: " + String.valueOf(datitos.getList().get(i).getCantidadgrande()) );
            }
        }


        return convertView;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }


    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();


            final List<Clientes> list = datos;
            int count = list.size();
            final ArrayList<Clientes> nlist = new ArrayList<>();

            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i).getName();
                if (filterableString.toLowerCase().contains(filterString)) {

                    //Usuario lecturas_model = new Lecturas_model(list.get(i).getNombrePaciente(),list.get(i).getFechaConsulta(),list.get(i).getPath(),list.get(i).getFechaCreacion());

                    Clientes nuevo =  new Clientes(0,
                            list.get(i).getRol(),
                            list.get(i).getName(),
                            list.get(i).getUsername(),
                            list.get(i).getTelefono(),
                            list.get(i).getMail(),
                            list.get(i).getIdcliente(),
                            list.get(i).getList());

                    nlist.add(nuevo);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataFilter = (ArrayList<Clientes>) results.values;
            notifyDataSetChanged();
        }

    }



}
