package com.realty.drake.bidjepeyim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class PDF_Viewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);


        PDFView pdfView = (PDFView)findViewById(R.id.pdfView);
        pdfView.fromAsset("loi_passation_de_marchev2.pdf").load();
    }
}
