package controller;

import com.amazonaws.util.json.Jackson;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetImagem {

    public static void getImg(String imagem) {

        String busca = new String("https://api.unsplash.com/search/photos?query={0}&client_id=44552ee7fbc2b428d6a7468058bbbe5163478869855d8f9dc7f968b876466bd8");

        URL url = null;

        try {
            url = new URL(busca.replace("{0}", imagem));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("user-agent", "Mozilla/5.0");

            BufferedReader bR = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String json = bR.readLine();
            JsonNode jsonNode = Jackson.jsonNodeOf(json);
            JsonNode results = jsonNode.findValue("results");
            String imgUrl = results.get(0).findValue("urls").findValue("raw").asText();
            salvarImagem(imgUrl, imagem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void salvarImagem(String urlImg, String assunto) throws IOException {
        URL url = new URL(urlImg);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1!=(n=in.read(buf)))
        {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();
        FileOutputStream fos = new FileOutputStream("C:\\Users\\DTI-GSAMPAIO\\Desktop\\Java\\teste\\"+assunto+".png");
        fos.write(response);
        fos.close();
    }

}
