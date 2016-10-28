package com.mac.fireflies.wgt.moviematch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mac.fireflies.wgt.moviematch.api.oracleofbacon.ArtistMoviesConnection;
import com.mac.fireflies.wgt.moviematch.api.oracleofbacon.PojoArtistMoviesConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FindConnectionsArtistFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FindConnectionsArtistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindConnectionsArtistFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static SuggestedListView listView;
    AutoCompleteTextView textViewArtist1, textViewArtist2;

    public FindConnectionsArtistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindConnectionsArtistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FindConnectionsArtistFragment newInstance(String param1, String param2) {
        FindConnectionsArtistFragment fragment = new FindConnectionsArtistFragment();
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
        listView = (SuggestedListView) v.findViewById(R.id.listView);
        Button button = (Button) v.findViewById(R.id.button);
        textViewArtist1 = (AutoCompleteTextView) v.findViewById(R.id.editText1) ;
        textViewArtist2 = (AutoCompleteTextView) v.findViewById(R.id.editText2) ;


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.getId() == R.id.movie_listview_layout){
                    if (listView.suggestedNames != null){
                        if (listView.suggestedNames.isNameFirst){
                            textViewArtist1.setText(((TextView)view.findViewById(R.id.movie_textView)).getText());
                        }
                        else{

                            textViewArtist2.setText(((TextView)view.findViewById(R.id.movie_textView)).getText());
                        }
                    }
                    else if(position % 2 != 0){

                        Intent i = new Intent(getContext(), MovieOverview.class);
//                        if (FindConnectionsArtistFragment.listView != null){
//                            PojoSearchMovie aux = FindConnectionsArtistFragment.listView.findMoviebyURI(((HintImageView)view.findViewById(R.id.movie_imageView)).hint);
//                            if (aux != null)
//                                Navigation.movie = new Movie(aux);
//                        }
                        startActivity(i);
                    }
                }

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final FindConnectionsArtistFragment.AdapterArtistMovieConnection adapter = new FindConnectionsArtistFragment.AdapterArtistMovieConnection(view.getContext(),
                        android.R.layout.simple_list_item_1,
                        new ArrayList<String>());

                textViewArtist1.clearFocus();
                textViewArtist2.clearFocus();
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
//                listView.setDivider(null);
//                listView.setDividerHeight(0);
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
        PojoArtistMoviesConnection pojoArtMovie;
        public AdapterArtistMovieConnection(Context context, int resource, List<String> conn) {
            super(context, resource);
            connections = conn;



        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
            );
            view = inflater.inflate(R.layout.movie_listview, null);

            TextView textView = (TextView) view.findViewById(R.id.movie_textView);
            HintImageView imageView = (HintImageView) view.findViewById(R.id.movie_imageView);

            if (position %2 !=0) {

                textView.setVisibility(View.INVISIBLE);
                Glide
                        .with(FindConnectionsArtistFragment.this)
                        .load(getItem(position))
                        .into(imageView);
                imageView.setHint(getItem(position));
            }
            else{
                textView.setText(getItem(position));
                imageView.setVisibility(View.INVISIBLE);

            }



            return view;
        }



    }
}
