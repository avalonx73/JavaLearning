package classes;

public class Service extends AbstractService {

    public void search() {
        super.search();
        System.out.println("Service.search");
        matching();
    }

    private void matching() {

        System.out.println("Service.matching");
    }
}
