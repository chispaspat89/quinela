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

/**
 * Created by rdms on 24/10/2019.
 */

public class AdaptadorVendedor  extends BaseAdapter implements Filterable {


    LayoutInflater flater;
    List<Usuario> datos;
    Context context;
    private List<Usuario> dataFilter = null;
    private ItemFilter mFilter = new ItemFilter();

    public  AdaptadorVendedor(Context contextS, List<Usuario> objetos){

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
        convertView= flater.inflate(R.layout.item_vendedor, null);

        TextView apiario = (TextView) convertView.findViewById(R.id.txtNombre);
        TextView colmenas = (TextView) convertView.findViewById(R.id.txtUsuario);



        Usuario datitos = dataFilter.get(position);

        apiario.setText("Nombre: "+datitos.getNombre());
        colmenas.setText("Usuario: " + datitos.getNombreUsuario());

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


            final List<Usuario> list = datos;
            int count = list.size();
            final ArrayList<Usuario> nlist = new ArrayList<>();

            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i).getNombre();
                if (filterableString.toLowerCase().contains(filterString)) {

                    Usuario nuevo =  new Usuario(0,
                            list.get(i).getNombre(),
                            list.get(i).getApellidoPaterno(),
                            list.get(i).getApellidoMaterno(),
                            list.get(i).getNombreUsuario(),
                            list.get(i).getCorreo(),
                            list.get(i).getTipoUsuario(),
                            list.get(i).getTipoUsuario(),
                            list.get(i).getContrasenia(),
                            list.get(i).getUsuarioId());
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
            dataFilter = (ArrayList<Usuario>) results.values;
            notifyDataSetChanged();
        }

    }


}
