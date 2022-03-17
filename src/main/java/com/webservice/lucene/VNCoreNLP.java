package com.webservice.lucene;

import java.io.IOException;
import vn.pipeline.Annotation;
import vn.pipeline.VnCoreNLP;
import vn.pipeline.Word;

public class VNCoreNLP {
    public static String useVNCoreNLP(String str) throws IOException {
        //String[] annotators = {"wseg", "pos", "ner", "parse"};
        String[] annotators = {"wseg"};
        VnCoreNLP pipeline = new VnCoreNLP(annotators);

        Annotation annotation = new Annotation(str);
        pipeline.annotate(annotation);
        String strThenVNCore = "";
        for (Word i : annotation.getWords()) {
            {
                if(i.toString().length()!=0)
                strThenVNCore = strThenVNCore + " " +i.getForm();
            }
        }
        strThenVNCore = strThenVNCore + " ";
        return strThenVNCore;
    }
}
