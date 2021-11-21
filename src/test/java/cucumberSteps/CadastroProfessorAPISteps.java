package cucumberSteps;

import br.com.sportsfree.utils.HttpClient;
import cucumberSteps.ValidationsAPI.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cucumberSteps.ValidationsAPI.Hooks.scenario;



public class CadastroProfessorAPISteps {


    private static String rota;
    private static String path = "";
    private static String apigeeVersion = "";
    private static StringEntity entity;
    private static String jsonBodyString;
    private static HttpResponse response;
    private static String hostschema = "https";
    private static Integer hostport = null;
    private static List<Header> headers = new ArrayList<>();
    private static List<NameValuePair> parameters = new ArrayList<>();
    private static LinkedHashMap<String, String> userParameters = new LinkedHashMap<String, String>();
    private static Boolean allowAll = true;
    private static String resultado;


    @Given("que eu uso o json")
    public void queEuUsoOJson( String json) throws Throwable {
        CadastroProfessorAPISteps.jsonBodyString = replaceVariablesValues(json);
    }

    @And("executo os testes no endpoint {string}")
    public void executoOsTestesNoEndpoint(String endpoint) {
         if (endpoint.equals("DEVELOPMENT") ) {
             Hooks.setHostname("sportsfree-dev.herokuapp.com");
         }
    }

    @And("uso a rota {string}")
    public void usoARota(String rota) throws Throwable {
        CadastroProfessorAPISteps.path = apigeeVersion + replaceVariablesValues(rota);
        CadastroProfessorAPISteps.path = CadastroProfessorAPISteps.path.replaceAll(" ", "%20");
        CadastroProfessorAPISteps.path = org.apache.commons.lang3.StringUtils.stripAccents(CadastroProfessorAPISteps.path);
    }

    @When("eu envio a requisição POST")
    public void euEnvioARequisicaoPOST() throws Throwable {
        HttpClient http= new HttpClient();
        if (jsonBodyString == null || jsonBodyString == ""){
            jsonBodyString = "{}";
        }
        entity = new StringEntity(jsonBodyString, ContentType.APPLICATION_JSON);
        response = http.sendPost(hostschema, Hooks.hostname, hostport, path, parameters, headers, entity, allowAll);
        extractJsonResponse();
    }

    @Then("Http response should be {int}")
    public void httpResponseShouldBe(int responseCode) throws  Throwable {
        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(responseCode, statusCode);

    }

    @When("eu envio a requisição PUT")
    public void euEnvioARequisiçãoPUT() throws Throwable {
        HttpClient http= new HttpClient();

        entity = new StringEntity(jsonBodyString, ContentType.APPLICATION_JSON);
        response = http.sendPut(hostschema, Hooks.hostname, hostport, path,  parameters, headers, entity, allowAll);
        extractJsonResponse();
    }

    @When("eu envio a requisição GET")
    public void euEnvioARequisiçãoGET() throws Throwable {
        HttpClient http= new HttpClient();

        //entity = new StringEntity(jsonBodyString, ContentType.APPLICATION_JSON);
        response = http.sendGet(hostschema, Hooks.hostname, hostport, path, parameters, headers, entity, allowAll);
        extractJsonResponse();
    }

    @When("eu envio a requisição DELETE")
    public void euEnvioARequisicaoDELETE() throws Throwable {
        HttpClient http= new HttpClient();

        //entity = new StringEntity(jsonBodyString, ContentType.APPLICATION_JSON);
        response = http.sendDelete(hostschema, Hooks.hostname, hostport, path, parameters, headers, entity, allowAll);

    }


    @Given("que eu queira pesquisar por um professor")
    public void queEuQueiraPesquisarPorUmProfessor() {
        System.out.println("Buscar um professor");
    }

    @Given("que eu queira listar todos os professores")
    public void queEuQueiraListarTodosOsProfessores() {
        System.out.println("listar professores");
    }


    public static String replaceVariablesValues(String texto) throws Throwable{
        String rx = "(\\$\\{[^}]+})";

        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(rx);
        Matcher m = p.matcher(texto);

        while(m.find()){
            String repString = userParameters.get(m.group(1));
            if (repString != null)
                m.appendReplacement(sb,repString);
        }
        m.appendTail(sb);

        return sb.toString();
    }

    private static void extractJsonResponse() throws Throwable {
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        Hooks.responseJson = result.toString();
        resultado = result.toString();

    }

    @Given("que eu queira deletar todos os professores")
    public void queEuQueiraDeletarTodosOsProfessores() {
        System.out.println("deletar professor");
    }


//    @And("eu tenho um novo id")
//    public void euTenhoUmNovoId() {
//        String originalResultado = "";
//
//        try{
//        ObjectMapper mapper = new ObjectMapper();
//        Object json = mapper.readValue(Hooks.responseJson, Object.class);
//
//    }
}
