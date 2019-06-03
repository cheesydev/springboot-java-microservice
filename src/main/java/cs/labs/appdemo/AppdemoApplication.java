package cs.labs.appdemo;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static cs.labs.appdemo.config.Metrics.addPercentilesToProductsEndpointTimers;

@SpringBootApplication
public class AppdemoApplication {

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config()
                .commonTags("app", "SpringBootApp")                      // add "app" tag to all metrics
                .meterFilter(addPercentilesToProductsEndpointTimers());  // include percentiles to products* metrics
    }

    public static void main(String[] args) {
        SpringApplication.run(AppdemoApplication.class, args);
    }

}
