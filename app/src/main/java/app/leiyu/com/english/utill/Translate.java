package app.leiyu.com.english.utill;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class Translate {

    public static String get_trans(String words){
        String res="error";
        URL url=null;
        try {
            url = new URL("http://fanyi.youdao.com/openapi.do");

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.addRequestProperty("encoding", "UTF-8");

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            OutputStream os = connection.getOutputStream();

            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write("keyfrom=English-leiyu&key=648956049&type=data&doctype=json&version=1.1&q="+words);
            bw.flush();
            osw.close();
            bw.close();


            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String line=null;
            StringBuilder builder = new StringBuilder();
            while((line = br.readLine()) != null){
                builder.append(line);
            }



            os.close();
            br.close();
            isr.close();
            is.close();
            res=builder.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block

            return res;
        }

        if(res.equals("error")){
            return  "error";
        }

        else{

            return res.split(",")[0].split(":")[1];
        }
    }


}
