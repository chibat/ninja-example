package conf;

import ninja.Router;
import ninja.application.ApplicationRoutes;
import controllers.Add1Controller;
import controllers.Add2Controller;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {
        router.GET().route("/add1/input").with(Add1Controller.class, "input");
        router.GET().route("/add1/calculate")
                .with(Add1Controller.class, "calculate");

        router.GET().route("/add2/input").with(Add2Controller.class, "input");
        router.POST().route("/add2/calculate")
                .with(Add2Controller.class, "calculate");
    }

}
