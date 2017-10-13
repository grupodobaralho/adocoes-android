package br.pucrs.ages.adocoes.Funcionalidades.MenorDetails;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.app.AlertDialog;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.content.DialogInterface;

import br.pucrs.ages.adocoes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenorDetailsButtonsFragment extends Fragment {

    public MenorDetailsButtonsFragment() {
        // Required empty public constructor
    }

    public static MenorDetailsButtonsFragment newInstance() { return new MenorDetailsButtonsFragment(); }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menor_details_buttons, container, false);

        Button btnAdotar = (Button) view.findViewById(R.id.btnAdotar);
        Button btnApadrinhar = (Button) view.findViewById(R.id.btnApadrinhar);

        btnAdotar.setOnClickListener(new OnClickListener() {
            public void onClick(View v)  {

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                alert.setTitle("Atenção");
                alert.setMessage("Você realmente deseja adotar este menor?");

                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        Toast.makeText(getActivity(),  "Sim" , Toast.LENGTH_SHORT ).show();
                    }
                });

                alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        Toast.makeText(getActivity(),  "Não" , Toast.LENGTH_SHORT ).show();
                    }
                });

                AlertDialog dialog = alert.create();
                alert.show();
            }
        });

        btnApadrinhar.setOnClickListener(new OnClickListener() {
            public void onClick(View v)  {

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                alert.setTitle("Atenção");
                alert.setMessage("Você realmente deseja apadrinhar este menor?");

                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        Toast.makeText(getActivity(),  "Sim" , Toast.LENGTH_SHORT ).show();
                    }
                });

                alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        Toast.makeText(getActivity(),  "Não" , Toast.LENGTH_SHORT ).show();
                    }
                });

                AlertDialog dialog = alert.create();
                alert.show();
            }
        });

        return view;
    }

}
