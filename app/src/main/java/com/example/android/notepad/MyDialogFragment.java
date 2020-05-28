package com.example.android.notepad;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MyDialogFragment extends DialogFragment {
    private Button cancel,search;
    private EditText mEditText;
    private String keywords=new String();
    private static final String[] PROJECTION = new String[] {
            NotePad.Notes._ID, // 0

    };
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_dialog,null);
        cancel=(Button) view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        mEditText=(EditText)view.findViewById(R.id.editText1);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                keywords=charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        search=(Button)view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (keywords.length()>0){
                    Cursor cursor = getActivity().managedQuery(
                            getActivity().getIntent().getData(),            // Use the default content URI for the provider.
                            PROJECTION,                       // Return the note ID and title for each note.
                            NotePad.Notes.COLUMN_NAME_TITLE+" like ?",                             // No where clause, return all records.
                            new String[]{keywords},                             // No where clause, therefore no where column values.
                            NotePad.Notes.DEFAULT_SORT_ORDER  // Use the default sort order.
                    );
                    if (cursor.getCount()>0){
                        startActivity(SearchResult.newIntent(getContext(),keywords));
                        dismiss();
                    }else {
                        Toast.makeText(getContext(),"nothing to show",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return new AlertDialog.Builder(getContext()).setView(view).show();
    }
}
