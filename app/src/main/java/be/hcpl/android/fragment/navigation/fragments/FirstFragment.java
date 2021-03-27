package be.hcpl.android.fragment.navigation.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import be.hcpl.android.fragment.navigation.MainActivity;
import be.hcpl.android.fragment.navigation.R;

import static com.mikepenz.iconics.Iconics.getApplicationContext;

/**
 * An example fragment that can be loaded in place
 */
public class FirstFragment extends TemplateFragment {
    private ImageView image;
    private Button buttonTeste;

    public static FirstFragment createInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // this is how we load a specific layout
        return inflater.inflate(R.layout.first_fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // and here our view is loaded
        buttonTeste = (Button) view.findViewById(R.id.buttonTeste);
        ((TextView) view.findViewById(R.id.txtOnTop)).setText(new StringBuilder("You're now on ").append(this.getClass().getSimpleName()).toString());

        view.findViewById(R.id.btnFirstFragment).setEnabled(false);
        view.findViewById(R.id.btnSecondFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).switchContent(SecondFragment.createInstance());
            }
        });
        view.findViewById(R.id.btnThirdFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).switchContent(ThirdFragment.createInstance());

            }
        });

        buttonTeste.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getActivity() , "teste",  Toast.LENGTH_LONG).show();

                if(ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getActivity() , "Permissão ainda não cedida",  Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(
                getActivity(),
                permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat
                    .requestPermissions(
                            getActivity(),
                            new String[] { permission },
                            requestCode);
        }
        else {
            Toast
                    .makeText(getActivity(),
                            "Permission already granted",
                            Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
