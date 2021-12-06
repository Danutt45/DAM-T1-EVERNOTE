package com.example.evernote;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.evernote.userentity.Account;

import java.io.Serializable;

public class LoginTabFragment extends Fragment {
    EditText email;
    EditText password;
    Account ac;

    Button login;

    final int acc_validation = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);
        login = root.findViewById(R.id.btnlogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ac != null) {
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    intent.putExtra("AccountData",ac);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getContext(), "Va rog sa va inregistrati", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }


    void DisplayData(Account mesage) {
        ac = new Account(mesage.getId(), mesage.getMail(), mesage.getPassword());
        email.setText(ac.getMail());
        password.setText(ac.getPassword());
    }

}
