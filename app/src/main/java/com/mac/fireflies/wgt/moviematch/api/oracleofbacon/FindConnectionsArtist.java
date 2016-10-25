package com.mac.fireflies.wgt.moviematch.api.oracleofbacon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.mac.fireflies.wgt.moviematch.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FindConnectionsArtist.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FindConnectionsArtist#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindConnectionsArtist extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ListView listView;

    public FindConnectionsArtist() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindConnectionsArtist.
     */
    // TODO: Rename and change types and number of parameters
    public static FindConnectionsArtist newInstance(String param1, String param2) {
        FindConnectionsArtist fragment = new FindConnectionsArtist();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_find_connections_artist, container, false);
        matchArtist(v);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    private void matchArtist(View v) {
        //ListView with data
        listView = (ListView) v.findViewById(R.id.listView);
        Button button = (Button) v.findViewById(R.id.button);
        final AutoCompleteTextView textViewArtist1 = (AutoCompleteTextView) v.findViewById(R.id.editText1) ;
        final AutoCompleteTextView textViewArtist2 = (AutoCompleteTextView) v.findViewById(R.id.editText2) ;
        textViewArtist1.setText("Tom Cruise");
        textViewArtist2.setText("Eminem");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final FindConnectionsArtist.AdapterArtistMovieConnection adapter = new FindConnectionsArtist.AdapterArtistMovieConnection(v.getContext(),
                        android.R.layout.simple_list_item_1,
                        new ArrayList<String>());
                ArtistMoviesConnection
                        .findConnection(textViewArtist1.getText().toString(), textViewArtist2.getText().toString(), adapter);


//                ArrayAdapter<String> adapter = new ArrayAdapter<ArtistMoviesConnection>(getApplicationContext(), android.R.layout.simple_list_item_1,
//                        new LinkedList<ArtistMoviesConnection>()){
//                    @NonNull
//                    @Override
//                    public View getView(int position, View convertView, ViewGroup parent) {
//                        return super.getView(position, convertView, parent);
//                    }
//                };

                listView.setAdapter(adapter);
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public class AdapterArtistMovieConnection extends ArrayAdapter<String> {
        List<String> connections;
        public AdapterArtistMovieConnection(Context context, int resource, List<String> conn) {
            super(context, resource);
            connections = conn;


        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return super.getView(position, convertView, parent);
        }
    }
}
