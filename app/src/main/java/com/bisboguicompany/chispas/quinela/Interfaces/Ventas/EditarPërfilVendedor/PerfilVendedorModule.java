package com.bisboguicompany.chispas.quinela.Interfaces.Ventas.EditarPërfilVendedor;


import com.bisboguicompany.chispas.quinela.DI.ActivityScoped;
import com.bisboguicompany.chispas.quinela.DI.FragmentScoped;
import com.bisboguicompany.chispas.quinela.Interfaces.Ventas.VentasContract;
import com.bisboguicompany.chispas.quinela.Interfaces.Ventas.VentasFragment;
import com.bisboguicompany.chispas.quinela.Interfaces.Ventas.VentasPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PerfilVendedorModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract PerfilVendedorFragment tasksFragment();

    @ActivityScoped
    @Binds
    abstract PerfilVendedorContract.Presenter taskPresenter(PerfilVendedorPresenter presenter);


}
