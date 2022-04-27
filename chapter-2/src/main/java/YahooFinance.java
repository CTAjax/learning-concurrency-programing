import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author ajax
 */
public class YahooFinance {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        final List<Callable<Double>> partitions = new ArrayList<>();
        List<Future<Double>> feature = executor.invokeAll(partitions);
        double sum = feature.stream().mapToDouble(doubleFuture -> {
            try {
                return doubleFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return 0.0d;
        }).sum();
        System.out.println(sum);
    }
}
