package cucumberSteps.ValidationsAPI;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.util.Collection;

public class Hooks {

    public static Scenario scenario;
    public static String hostname;
    private static Collection<String> taggs;
    public static String responseJson;


    @Before
    public void runBeforeWithOther (Scenario scenario){
        Hooks.scenario = scenario;
        keepScenario(scenario);
    }

    private void keepScenario(Scenario scenario){
        setTaggs(scenario.getSourceTagNames());
    }

    private static void setTaggs (Collection<String> taggs) {
        Hooks.taggs = taggs;
    }

    public static void setHostname(String hostname){
        Hooks.hostname = hostname;
    }

}
