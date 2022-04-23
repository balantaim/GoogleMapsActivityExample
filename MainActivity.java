package com.martinatanasov.maps;



public class MainActivity extends AppCompatActivity {


    FloatingActionButton map_button;
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //hide Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        if(isServicesOK()){
            Log.d(TAG, "onCreate: Services Running!");
//            Toast.makeText(this, "Services Running!", Toast.LENGTH_SHORT).show();
        }
        map_button=findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapActivity.class));
            }
        });


        customAdapter= new CustomAdapter(MainActivity.this, this, book_id, book_title, book_author, book_pages);

        //SimpleCallback - Drag and Drop function
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: Check services");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if(available== ConnectionResult.SUCCESS){
            Log.d(TAG, "isServicesOK: Services are OK");
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG, "isServicesOK: There is a problem but it ");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Log.d(TAG, "isServicesOK: There is problem with Google map services");
        }
        return false;
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
