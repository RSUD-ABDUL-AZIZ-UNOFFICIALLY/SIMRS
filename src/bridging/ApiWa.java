package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.koneksiDB;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author userpc
 */

public class ApiWa {
    public String Url = "", Telp = "", Message = "", URL = "", json = "";
    private SSLContext sslContext;
    private SSLSocketFactory sslFactory;
 
    private Scheme scheme;
    private HttpComponentsClientHttpRequestFactory factory;
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private ObjectMapper mapper = new ObjectMapper();

    public ApiWa() {
        super();
        try {
            Url = koneksiDB.URLWAPI();
            System.out.println(Url);
        } catch (Exception e) {
            System.out.println("E : " + e);
        }
    }
   public RestTemplate getRest() throws NoSuchAlgorithmException, KeyManagementException {
        sslContext = SSLContext.getInstance("SSL");
        TrustManager[] trustManagers= {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {return null;}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
            }
        };
         sslContext.init(null,trustManagers , new SecureRandom());
        sslFactory=new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        scheme=new Scheme("https",443,sslFactory);
        factory=new HttpComponentsClientHttpRequestFactory();
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        return new RestTemplate(factory);
    }
    public void seedWa(String Message, String Telp) {
        Message = Message;
        Telp = Telp;
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            requestEntity = new HttpEntity(headers);
            json = "{" +
                    "\"message\": \"" + Message + "\"," +
                    "\"telp\": \"" + Telp + "\"" +
                    "}";
//            System.out.println("JSON : " + json);
//            System.out.println("Message : " + Message);
//            System.out.println("Url : " + Url);
//            System.out.println("requestEntity : " + requestEntity);
            
            requestEntity = new HttpEntity(json, headers);
            root = mapper.readTree(getRest().exchange(Url, HttpMethod.POST, requestEntity, String.class).getBody());
//          root = mapper.readTree(getRest().exchange("http://tick.spairum.my.id/api/auth/ipsaya", HttpMethod.GET, requestEntity, String.class).getBody());
            System.out.println("Respon : "+root);            
        } catch (Exception e) {
            System.out.println("E : " + e);
        }
    }
}
